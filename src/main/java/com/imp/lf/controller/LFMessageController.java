package com.imp.lf.controller;

import com.alibaba.fastjson.JSON;
import com.imp.lf.entities.Image;
import com.imp.lf.entities.LFMessage;
import com.imp.lf.entities.ReceiveTemplate;
import com.imp.lf.entities.User;
import com.imp.lf.service.ImageService;
import com.imp.lf.service.LFMessageService;
import com.imp.lf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by (IMP)郑和明
 * Date is 2017/1/24
 */
@Controller
public class LFMessageController {

    @Autowired
    private LFMessageService lfMessageService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private UserService userService;

    //插入数据
    @ResponseBody
    @RequestMapping(value = "/LFMessage/addLFMessage", method = RequestMethod.POST)
    public String addLFMessage(LFMessage lfMessage,
                               @RequestParam("image") MultipartFile[] images,
                               @RequestParam("userId") Long userId,
                               @RequestParam("token") String token,
                               HttpServletRequest request) throws IOException {
        if (lfMessage == null || images == null || userId == null) {
            return "Incomplete";
        }

        //异地登陆
        if (!userService.findTokenById(userId).equals(token)) {
            return "Remote";
        }

        User user = userService.selectById(userId);
        if (user == null) {
            return "NoUser";
        }

        lfMessage.setUser(user);

        Long dataId = lfMessageService.insertLFMessage(lfMessage);


        for (MultipartFile image : images) {
            String root = request.getSession().getServletContext().getRealPath("/");
            String filePath = "image/" + image.getOriginalFilename();
            image.transferTo(new File(root + filePath));
            Image imageTemp = new Image();
            imageTemp.setDataId(dataId);
            imageTemp.setImageUrl(filePath);
            imageTemp.setType("LF");
            imageService.insertImage(imageTemp);
        }
        ReceiveTemplate<LFMessage> template = new ReceiveTemplate<>();
        List<LFMessage> lfMessages = lfMessageService.findLFMessageList(dataId, "", null, null, 1);
        if (lfMessages != null) {
            template.setData(lfMessages.get(0));
        }
        token = userService.updateTokenById(userId);
        template.setToken(token);

        return JSON.toJSONString(template);
    }


    //读取数据
    @ResponseBody
    @RequestMapping(value = "/LFMessage/readLFMessage", method = RequestMethod.POST)
    public String readLFMessage(@RequestParam("id") Long userId,
                                @RequestParam("type") String type,
                                @RequestParam("kind") String kind,
                                @RequestParam("location") String location,
                                @RequestParam("currentPage") Integer currentPage) {

        if (currentPage == null) {
            return "Incomplete";
        }

        List<LFMessage> lfMessages = lfMessageService.findLFMessageList(userId, type, kind, location, currentPage);
        if (lfMessages == null) {
            return "error";
        }
        return JSON.toJSONString(lfMessages);
    }

    //删除数据
    @ResponseBody
    @RequestMapping(value = "/LFMessage/deleteLFMessage", method = RequestMethod.POST)
    public String deleteLFMessage(@RequestParam("id") Long userId,
                                  @RequestParam("token") String token,
                                  @RequestParam("dataId") Long dataId) {
        if (userId == null || token == null || dataId == null) {
            return "Incomplete";
        }
        //异地登陆
        if (!userService.findTokenById(userId).equals(token)) {
            return "Remote";
        }

        lfMessageService.deleteLFMessageById(dataId);

        return "true";
    }

    //更新分享数
    @ResponseBody
    @RequestMapping(value = "/LFMessage/updateShareCount", method = RequestMethod.POST)
    public String updateShareCount(@RequestParam("id") Long userId,
                                   @RequestParam("token") String token,
                                   @RequestParam("dataId") Long dataId) {


        if (userId == null || token == null || dataId == null) {
            return "Incomplete";
        }
        //异地登陆
        if (!userService.findTokenById(userId).equals(token)) {
            return "Remote";
        }

        lfMessageService.addLFMessageShareCount(dataId);

        return "true";
    }

}
