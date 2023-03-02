package com.cong.config;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Array;
import java.util.Arrays;

public class LoginHandlerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //可以不登陆就访问的网站，不会强制跳转
        String[] exclusionWebsite = new String[]{"/toRegister", "/registerEmail", "/registerInformation"};
        //登录成功之后应该有用户的session
        Object loginUser = request.getSession().getAttribute("loginEmail");
        if (loginUser == null && !Arrays.asList(exclusionWebsite).contains(request.getRequestURI())) {
            request.setAttribute("msg", "没有权限，请先登录");
            request.getRequestDispatcher("/index.html").forward(request, response);
            return false;
        }
        else {
            return true;
        }
    }
}
