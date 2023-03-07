package com.cong.controller;

import com.cong.dao.ActivityDao;
import com.cong.dao.UserDao;
import com.cong.pojo.Activity;
import com.cong.pojo.User;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Collection;


@Controller
@Service
public class LoginController {

    @Resource
    private UserDao userDao;

    @Resource
    private ActivityDao activityDao;

    @ApiOperation("接收邮箱和密码")
    @PostMapping("/user/login")
    public String login(@RequestParam @ApiParam(value = "email")String email, @RequestParam @ApiParam(value = "password")String password, Model model, HttpSession session) {
        User user = userDao.getUserByEmail(email);
        BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();
        if (StringUtils.hasLength(email) && user != null && bcryptPasswordEncoder.matches(password, user.getPassword())) {
            Collection<Activity> activities = activityDao.getAll();
            session.setAttribute("loginEmail", email);
            session.setAttribute("loginNickname", user.getUserNickname());
            model.addAttribute("activities", activities);
            return "home_page/activitySquare";
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
