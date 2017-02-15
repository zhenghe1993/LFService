package com.imp.lf.controller;

import com.alibaba.fastjson.JSON;
import com.imp.lf.entities.ReceiveTemplate;
import com.imp.lf.entities.User;
import com.imp.lf.service.UserService;
import com.imp.lf.util.DesHelper;
import com.imp.lf.util.TokenBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import java.util.Date;

/**
 * DesUtils
 * Created by (IMP)郑和明
 * Date is 2017/1/22
 */
@Controller
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;


    /**
     * login
     *
     * @return token
     */
    @ResponseBody
    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    public String login(@RequestParam("pn") String phoneNumber, @RequestParam("pw") String password) {

        if (phoneNumber == null || password == null) {
            return "Incomplete";
        }

        password = DesHelper.getEncode(password);
        phoneNumber = DesHelper.getDecode(phoneNumber);
        System.out.println(phoneNumber);
        System.out.println(password);
        boolean flag = userService.isExist(phoneNumber);
        if (!flag) {
            return "noUser";
        }
        User user = userService.findByPhoneAndPassword(phoneNumber, password);
        System.out.println("" + user);
        if (user == null) {
            return "errorPassword";
        } else {
            user.setToken(userService.updateTokenById(user.getId()));
            return JSON.toJSONString(user);
        }
    }

    /**
     * register
     * <p>
     * phoneNumber string
     * name String
     * portrait inputStream
     * location string
     * password String
     *
     * @return user
     */
    @ResponseBody
    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    public String register(@RequestParam("name") String name,
                           @RequestParam("location") String location,
                           @RequestParam("phoneNumber") String phoneNumber,
                           @RequestParam("password") String password,
                           @RequestParam("portrait") MultipartFile portrait, HttpServletRequest request) throws IOException {
        if (name == null || location == null || phoneNumber == null || password == null || portrait.isEmpty()) {
            return "Incomplete";
        }
        if (userService.isExist(phoneNumber)) {
            return "Repeat";
        }

        password = DesHelper.getEncode(password);
        phoneNumber = DesHelper.getDecode(phoneNumber);
        String root = request.getSession().getServletContext().getRealPath("/");
        String filePath = "image/" + portrait.getOriginalFilename();
        portrait.transferTo(new File(root + filePath));
        User user = new User();
        user.setName(name);
        user.setPhoneNumber(phoneNumber);
        user.setLocation(location);
        user.setCreateTime(new Date());
        user.setToken(TokenBuilder.tokenBuilder());
        user.setPortrait(filePath);
        user.setPassword(password);
        log.warn(user.toString());
        userService.insert(user);
        return JSON.toJSONString(user);
    }

    /**
     * alert
     * <p>
     * phoneNumber string
     * name String
     * portrait inputStream
     * location string
     * password String
     *
     * @return user
     */
    @ResponseBody
    @RequestMapping(value = "/user/alertUser", method = RequestMethod.POST)
    public String alertUser(@RequestParam("id") Long id,
                            @RequestParam("name") String name,
                            @RequestParam("location") String location,
                            @RequestParam("password") String password,
                            @RequestParam("token") String token,
                            @RequestParam("portrait") MultipartFile portrait, HttpServletRequest request) throws IOException {


        ReceiveTemplate<String> template = new ReceiveTemplate<>();

        if (id == null || token == null || (name.equals("1") && location.equals("1") && password.equals("1") && portrait.isEmpty())) {
            template.setData("Incomplete");
            return JSON.toJSONString(template);
        }

        //异地登陆
        if (!userService.findTokenById(id).equals(token)) {
            template.setData("Remote");
            return JSON.toJSONString(template);
        }

        if (!name.equals("1")) {
            userService.updateUserData(id, name, 0);
            template.setData(name);
        } else if (!location.equals("1")) {
            userService.updateUserData(id, location, 1);
            template.setData(location);
        } else if (!password.equals("1")) {
            password = DesHelper.getEncode(password);
            userService.updateUserData(id, password, 2);
            template.setData(password);
        } else if (!portrait.isEmpty()) {
            String root = request.getSession().getServletContext().getRealPath("/");
            String filePath = "image/" + portrait.getOriginalFilename();
            portrait.transferTo(new File(root + filePath));
            userService.updateUserData(id, filePath, 3);
            template.setData(filePath);
        }
        token = userService.updateTokenById(id);
        template.setToken(token);
        return JSON.toJSONString(template);
    }

    @ResponseBody
    @RequestMapping(value = "/user/isRegister", method = RequestMethod.POST)
    public String isRegisterPhoneNumber(@RequestParam("phone_number") String phoneNumber) {
        if (phoneNumber == null) {
            return "Incomplete";
        }
        if (userService.isExist(phoneNumber)) {
            return "Repeat";
        }
        return "no";
    }


}
