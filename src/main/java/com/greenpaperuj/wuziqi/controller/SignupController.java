package com.greenpaperuj.wuziqi.controller;


import com.greenpaperuj.wuziqi.pojo.Result;
import com.greenpaperuj.wuziqi.pojo.User;
import com.greenpaperuj.wuziqi.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/wuziqi/signup")
public class SignupController {
    @Autowired
    UserService userService;

    @PostMapping
    public Result signup(User user) {
        if (user.getUsername() != null && user.getPassword() != null) {
            String username = user.getUsername();
            String password = user.getPassword();
            log.info("根据用户名和密码注册：{},{}",username, password);
            if (password.length() <= 100 && username.length() <= 50) {
                userService.signup(user);
                return Result.success();
            } else {
                return Result.error("Illegal username or illegal password.");
            }
        } else {
            return Result.error("No username or no password.");
        }
    }
}