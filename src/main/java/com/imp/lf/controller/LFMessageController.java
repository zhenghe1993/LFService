package com.imp.lf.controller;

import com.alibaba.fastjson.JSON;
import com.imp.lf.dto.ResultTemplate;
import com.imp.lf.entities.Image;
import com.imp.lf.entities.LFMessage;
import com.imp.lf.entities.User;
import com.imp.lf.service.ImageService;
import com.imp.lf.service.LFMessageService;
import com.imp.lf.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by (IMP)郑和明
 * Date is 2017/1/24
 */
@Controller
public class LFMessageController {


    private Logger logger = LoggerFactory.getLogger(LFMessageController.class);

    @Autowired
    private LFMessageService lfMessageService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private UserService userService;

    //插入数据
    @ResponseBody
    @RequestMapping(value = "/LFMessage/addLFMessage", method = RequestMethod.POST)
    public Object addLFMessage(LFMessage lfMessage,
                               @RequestParam("image") MultipartFile[] images,
                               @RequestParam("userId") Long userId,
                               @RequestParam("token") String token,
                               HttpServletRequest request) throws IOException {


        if (lfMessage == null || images == null || userId == null) {
            return new ResultTemplate("参数缺失",ResultTemplate.PARAMETER_LOST);
        }

        //异地登陆
        if (!userService.findTokenById(userId).equals(token)) {
            return new ResultTemplate("异地登陆",ResultTemplate.TOKEN_ERROR);
        }

        User user = userService.selectById(userId);
        if (user == null) {
            return new ResultTemplate("无此用户",ResultTemplate.USER_ERROR);
        }

        lfMessage.setUser(user);

        lfMessageService.insertLFMessage(lfMessage);

        Long dataId = lfMessage.getId();


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
        token = userService.updateTokenById(userId);

        return new ResultTemplate(token,ResultTemplate.SUCCESS);
    }


    //读取数据
    @ResponseBody
    @RequestMapping(value = "/LFMessage/readLFMessage", method = RequestMethod.POST)
    public Object readLFMessage(@RequestParam("id") Long userId,
                                @RequestParam("type") String type,
                                @RequestParam("kind") String kind,
                                @RequestParam("location") String location,
                                @RequestParam("currentPage") Integer currentPage) {

        if (currentPage == null) {
            return new ResultTemplate("参数缺失",ResultTemplate.PARAMETER_LOST);
        }

        List<LFMessage> lfMessages = lfMessageService.findLFMessageList(userId, type, kind, location, currentPage);
        if (lfMessages == null) {
            return new ResultTemplate("查询失败",ResultTemplate.QUERY_ERROR);
        }
        return new ResultTemplate(lfMessages,ResultTemplate.SUCCESS);
    }

    //删除数据
    @ResponseBody
    @RequestMapping(value = "/LFMessage/deleteLFMessage", method = RequestMethod.POST)
    public Object deleteLFMessage(@RequestParam("id") Long userId,
                                  @RequestParam("token") String token,
                                  @RequestParam("dataId") Long dataId) {
        if (userId == null || token == null || dataId == null) {
            return new ResultTemplate("参数缺失",ResultTemplate.PARAMETER_LOST);
        }
        //异地登陆
        if (!userService.findTokenById(userId).equals(token)) {
            return new ResultTemplate("异地登陆",ResultTemplate.TOKEN_ERROR);
        }

        lfMessageService.deleteLFMessageById(dataId);

        return new ResultTemplate(userService.updateTokenById(userId),ResultTemplate.TOKEN_ERROR);
    }

    //更新分享数
    @ResponseBody
    @RequestMapping(value = "/LFMessage/updateShareCount", method = RequestMethod.POST)
    public Object updateShareCount(@RequestParam("id") Long userId,
                                   @RequestParam("token") String token,
                                   @RequestParam("dataId") Long dataId) {


        if (userId == null || token == null || dataId == null) {
            return new ResultTemplate("参数缺失",ResultTemplate.PARAMETER_LOST);
        }
        //异地登陆
        if (!userService.findTokenById(userId).equals(token)) {
            return new ResultTemplate("异地登陆",ResultTemplate.TOKEN_ERROR);
        }

        lfMessageService.addLFMessageShareCount(dataId);

        return new ResultTemplate(userService.updateTokenById(userId),ResultTemplate.TOKEN_ERROR);
    }

    @InitBinder
    public void initBinder(ServletRequestDataBinder binder) {
        /**
         * 自动转换日期类型的字段格式
         */
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }
}
