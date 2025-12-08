package com.greenpaperuj.wuziqi.service.impl;

import com.greenpaperuj.wuziqi.mapper.UserMapper;
import com.greenpaperuj.wuziqi.pojo.User;
import com.greenpaperuj.wuziqi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public void signup(User user) {
        userMapper.signup(user);
    }

    @Override
    public User login(String username, String password) {
        return userMapper.selectByUsernameAndPassword(username, password);
    }
}
