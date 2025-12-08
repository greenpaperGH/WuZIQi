package com.greenpaperuj.wuziqi.service;

import com.greenpaperuj.wuziqi.pojo.User;

public interface UserService {
    void signup(User user);

    User login(String username, String password);
}
