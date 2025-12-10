package com.greenpaperuj.wuziqi.config;

import com.greenpaperuj.wuziqi.interceptor.LoginCheckInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final static List<String> loginAndSignup = List.of("/wuziqi/login/**", "/wuziqi/signup/**");

    @Autowired
    private LoginCheckInterceptor loginCheckInterceptor;

    @Override
    public void addInterceptors (InterceptorRegistry registry) {
        registry.addInterceptor(loginCheckInterceptor).addPathPatterns("/wuziqi/**").excludePathPatterns(loginAndSignup);
    }
}
