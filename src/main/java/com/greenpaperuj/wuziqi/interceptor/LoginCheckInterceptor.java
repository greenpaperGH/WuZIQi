package com.greenpaperuj.wuziqi.interceptor;

import com.alibaba.fastjson2.JSON;
import com.greenpaperuj.wuziqi.pojo.Result;
import com.greenpaperuj.wuziqi.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handle) throws Exception {
        log.info("请求的url: {}" ,request.getRequestURI());
        String jwt = request.getHeader("token");

        if(!StringUtils.hasLength(jwt)) {
            log.info("请求头token为空");
            Result error = Result.error("Not login.");
            String notLogin = JSON.toJSONString(error);
            response.getWriter().write(notLogin);
            return false;
        }

        try {
            JwtUtils.parseToken(jwt);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("解析错误");
            Result error = Result.error("Not login.");
            String notLogin = JSON.toJSONString(error);
            response.getWriter().write(notLogin);
            return false;
        }

        log.info("令牌合法");
        return true;
    }
}
