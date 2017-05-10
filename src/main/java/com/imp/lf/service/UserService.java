package com.imp.lf.service;

import com.baomidou.framework.service.ISuperService;
import com.imp.lf.entities.User;

/**
 * Created by (IMP)郑和明
 * Date is 2017/1/19
 */
public interface UserService extends ISuperService<User>{


    //登陆
    User findByPhoneAndPassword(String phone,String password);

    //忘记密码，修改密码，修改昵称，修改头像，修改位置
    void updateUserData(Long id,String data,int type);

    //返回token
    String updateTokenById(Long id);
    //返回token
    String findTokenById(Long id);

    boolean isExistPhone(String phoneNumber);
    boolean isExistName(String name);
}
