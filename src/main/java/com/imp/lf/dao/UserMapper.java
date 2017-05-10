package com.imp.lf.dao;

import com.baomidou.mybatisplus.mapper.AutoMapper;
import com.imp.lf.entities.User;
import org.springframework.stereotype.Component;

/**
 * Created by (IMP)郑和明
 * Date is 2017/1/19
 */

public interface UserMapper extends AutoMapper<User> {

    User findByPhoneAndPassword(String phone, String password);

    void updateTokenById(String token, Long id);

    String findTokenById(Long id);

    Long isExistPhone(String phoneNumber);

    Long isExistName(String name);
}
