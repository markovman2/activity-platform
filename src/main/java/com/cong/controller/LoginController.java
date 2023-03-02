package com.cong.controller;

import com.cong.dao.UserDao;
import com.cong.mapper.UserMapper;
import com.cong.pojo.User;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Resource
    private UserDao userDao;

    @ApiOperation("接收邮箱和密码")
    @PostMapping("/user/login")
    public String login(@RequestParam @ApiParam(value = "email")String email, @RequestParam @ApiParam(value = "password")String password, Model model, HttpSession session) {
        User user = userDao.getUserByEmail(email);
        String encryptedPassword = DigestUtils.md5DigestAsHex(password.getBytes());
        if (StringUtils.hasLength(email) && user != null && encryptedPassword.equals(user.getPassword())) {
            model.addAttribute("msg", "用户名或者密码正确");
            session.setAttribute("loginEmail", email);
            return "index";
        }
        else {
            model.addAttribute("msg", "用户名或者密码错误");
            return "index";
        }
    }

    @RequestMapping("/user/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/index.html";
    }
}