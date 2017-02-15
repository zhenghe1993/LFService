package com.imp.lf.controller;

import com.alibaba.fastjson.JSON;
import com.imp.lf.entities.Discover;
import com.imp.lf.entities.Image;
import com.imp.lf.entities.LFMessage;
import com.imp.lf.entities.User;
import com.imp.lf.service.DiscoverService;
import com.imp.lf.service.ImageService;
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
public class DiscoverController {
    @Autowired
    private UserService userService;

    @Autowired
    private DiscoverService discoverService;

    @Autowired
    private ImageService imageService;

    //插入
    @ResponseBody
    @RequestMapping(value = "/discover/addDiscover", method = RequestMethod.POST)
    public String addDiscover(Discover discover,
                              @RequestParam("image") MultipartFile[] images,
                              @RequestParam("userId") Long userId,
                              @RequestParam("token") String token,
                              HttpServletRequest request) throws IOException {

        if (discover == null || images == null || userId == null) {
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

        discover.setUser(user);

        Long dataId = discoverService.insertDiscover(discover);


        for (MultipartFile image : images) {
            String root = request.getSession().getServletContext().getRealPath("/") ;
            String filePath="image/" + image.getOriginalFilename();
            image.transferTo(new File(root+filePath));
            Image imageTemp = new Image();
            imageTemp.setDataId(dataId);
            imageTemp.setImageUrl(filePath);
            imageTemp.setType("DIS");
            imageService.insertImage(imageTemp);
        }
        return userService.updateTokenById(userId);

    }

    //读取
    @ResponseBody
    @RequestMapping(value = "/discover/readDiscover", method = RequestMethod.POST)
    public String readDiscover(@RequestParam("id") Long userId,
                               @RequestParam("currentPage") Integer currentPage) {


        if (currentPage == null) {
            return "Incomplete";
        }

        List<Discover> discovers = discoverService.findDiscoverList(userId, currentPage);


        if (discovers == null) {
            return "error";
        }

        return JSON.toJSONString(discovers);
    }

    //删除

    @ResponseBody
    @RequestMapping(value = "/discover/deleteDiscover", method = RequestMethod.POST)
    public String deleteDiscover(@RequestParam("id") Long userId,
                                  @RequestParam("token")String token,
                                  @RequestParam("dataId")Long dataId) {
        if(userId==null||token==null||dataId==null){
            return "Incomplete";
        }
        //异地登陆
        if (!userService.findTokenById(userId).equals(token)) {
            return "Remote";
        }

        discoverService.deleteDiscoverById(dataId);

        return "true";
    }


    //更新分享数
    @ResponseBody
    @RequestMapping(value = "/discover/updateShareCount", method = RequestMethod.POST)
    public String updateShareCount(@RequestParam("id")Long userId,
                                   @RequestParam("token")String token,
                                   @RequestParam("dataId")Long dataId) {


        if(userId==null||token==null||dataId==null){
            return "Incomplete";
        }
        //异地登陆
        if (!userService.findTokenById(userId).equals(token)) {
            return "Remote";
        }

        discoverService.addDiscoverShareCount(dataId);
        return "true";
    }


}
