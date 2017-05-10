package com.imp.lf.service.impl;

import com.baomidou.framework.service.impl.SuperServiceImpl;
import com.imp.lf.dao.UserMapper;
import com.imp.lf.entities.User;
import com.imp.lf.service.UserService;
import com.imp.lf.util.TokenBuilder;
import org.springframework.stereotype.Service;

/**
 * Created by (IMP)郑和明
 * Date is 2017/1/19
 */

@Service
public class UserServiceImpl extends SuperServiceImpl<UserMapper, User> implements UserService {


    @Override
    public User findByPhoneAndPassword(String phone, String password) {
        return baseMapper.findByPhoneAndPassword(phone, password);
    }

    @Override
    public String updateTokenById(Long id) {
        String token = TokenBuilder.tokenBuilder();
        baseMapper.updateTokenById(token, id);
        return token;
    }

    @Override
    public String findTokenById(Long id) {
        return baseMapper.findTokenById(id);
    }

    @Override
    public boolean isExistPhone(String phoneNumber) {
        return baseMapper.isExistPhone(phoneNumber)!=null;
    }

    @Override
    public boolean isExistName(String name) {
        return baseMapper.isExistName(name)!= null;
    }



    @Override
    public void updateUserData(Long id, String data, int type) {

        User user = baseMapper.selectById(id);
        switch (type) {
            case 0://修改名称
                user.setName(data);
                break;
            case 1://修改地址
                user.setLocation(data);
                break;
            case 2://修改密码
                user.setPassword(data);
                break;
            case 3://头像
                user.setPortrait(data);
                break;
        }

        baseMapper.updateById(user);

    }
}
