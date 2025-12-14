package com.greenpaperuj.wuziqi.service;

import com.greenpaperuj.wuziqi.pojo.User;

import java.util.List;

public interface UserService {
    void signup(User user);

    User login(String username, String password);

    List<User> list();

    User selectById(Integer id);
}
