package com.greenpaperuj.wuziqi.controller;

import com.greenpaperuj.wuziqi.pojo.Result;
import com.greenpaperuj.wuziqi.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wuziqi/user")
@Slf4j
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping
    public Result list() {
        log.info("查询所有用户.");
        return Result.success(userService.list());
    }

    @GetMapping("/{id}")
    public Result selectById(@PathVariable Integer id) {
        log.info("根据id查询用户.");
        return Result.success(userService.selectById(id));
    }
}
