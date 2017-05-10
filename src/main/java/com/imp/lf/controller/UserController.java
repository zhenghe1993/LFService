package com.imp.lf.controller;

import com.alibaba.fastjson.JSON;
import com.imp.lf.dto.ResultTemplate;
import com.imp.lf.entities.User;
import com.imp.lf.service.UserService;
import com.imp.lf.util.DesHelper;
import com.imp.lf.util.TokenBuilder;
import org.aspectj.apache.bcel.util.ClassLoaderRepository;
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
import java.util.HashMap;
import java.util.Map;

/**
 * DesUtils
 * Created by (IMP)郑和明
 * Date is 2017/1/22
 */

@RequestMapping(produces = "application/json")
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
    public Object login(@RequestParam(value = "pn") String phoneNumber, @RequestParam(value = "pw") String password) {


        if (phoneNumber.trim().isEmpty() || password .trim().isEmpty()) {
            return new ResultTemplate("参数缺失", ResultTemplate.PARAMETER_LOST);
        }

        password = DesHelper.getEncode(password);
        phoneNumber = DesHelper.getDecode(phoneNumber);

        boolean flag = userService.isExistPhone(phoneNumber);

        if (!flag) {
            return new ResultTemplate("账号错误", ResultTemplate.PHONE_ERROR);
        }
        User user = userService.findByPhoneAndPassword(phoneNumber, password);

        if (user == null) {
            return new ResultTemplate("密码错误", ResultTemplate.PASSWORD_ERROR);
        }

        user.setToken(userService.updateTokenById(user.getId()));

        return new ResultTemplate(user, ResultTemplate.SUCCESS);

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
    public Object register(@RequestParam("name") String name,
                           @RequestParam("location") String location,
                           @RequestParam("phoneNumber") String phoneNumber,
                           @RequestParam("password") String password,
                           @RequestParam("portrait") MultipartFile portrait, HttpServletRequest request) throws IOException {
        if (name .trim().isEmpty()|| location .trim().isEmpty()|| phoneNumber .trim().isEmpty() || password.trim().isEmpty()|| portrait.isEmpty()) {
            return new ResultTemplate("参数缺失",ResultTemplate.PARAMETER_LOST);
        }
        if (userService.isExistPhone(phoneNumber)) {
            return new ResultTemplate("手机号已存在",ResultTemplate.PHONE_REPEAT);
        }
        if (userService.isExistName(name)) {
            return new ResultTemplate("昵称已存在",ResultTemplate.NAME_REPEAT);
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
        log.info("正注册用户信息--->{}",user.toString());
        boolean res=userService.insert(user);

        if(!res){
            return new ResultTemplate("注册错误",ResultTemplate.SQL_ERROR);
        }

        return new ResultTemplate(user,ResultTemplate.SUCCESS);
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
    public Object alertUser(@RequestParam("id") Long id,
                            @RequestParam("name") String name,
                            @RequestParam("location") String location,
                            @RequestParam("password") String password,
                            @RequestParam("token") String token,
                            @RequestParam("portrait") MultipartFile portrait, HttpServletRequest request) throws IOException {




        if (id==0|| token.trim().isEmpty() || (name.trim().isEmpty() && location.trim().isEmpty() && password.trim().isEmpty() && portrait.isEmpty())) {

            return new ResultTemplate("参数缺失",ResultTemplate.PARAMETER_LOST);
        }

        //异地登陆
        if (!userService.findTokenById(id).equals(token)) {
            return new ResultTemplate("异地登陆",ResultTemplate.TOKEN_ERROR);
        }



        if (!name.trim().isEmpty()) {
            userService.updateUserData(id, name, 0);
        } else if (!location.equals("1")) {
            userService.updateUserData(id, location, 1);
        } else if (!password.equals("1")) {
            password = DesHelper.getEncode(password);
            userService.updateUserData(id, password, 2);
        } else if (!portrait.isEmpty()) {
            String root = request.getSession().getServletContext().getRealPath("/");
            String filePath = "image/" + portrait.getOriginalFilename();
            portrait.transferTo(new File(root + filePath));
            userService.updateUserData(id, filePath, 3);
        }
        token = userService.updateTokenById(id);

        User user=userService.selectById(id);

        return new ResultTemplate(user,ResultTemplate.SUCCESS);
    }

    @ResponseBody
    @RequestMapping(value = "/user/isRegister", method = RequestMethod.POST)
    public Object isRegisterPhoneNumber(@RequestParam("phone_number") String phoneNumber) {
        if (phoneNumber.trim().isEmpty()) {
            return new ResultTemplate("参数缺失",ResultTemplate.PARAMETER_LOST);
        }
        if (userService.isExistPhone(phoneNumber)) {
            return new ResultTemplate("已注册",ResultTemplate.SUCCESS);
        }
        return new ResultTemplate("未注册",ResultTemplate.FAILURE);
    }


}
