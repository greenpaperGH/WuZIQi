package com.greenpaperuj.wuziqi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @RequestMapping("/wuziqi/hello")
    public String helloWorld() {
        return "Hello world";
    }
}
