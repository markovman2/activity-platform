package com.cong.controller;

import com.cong.dao.ActivityDao;
import com.cong.pojo.Activity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Collection;

@Controller
public class PagesController {

    @Resource
    private ActivityDao activityDao;

    @RequestMapping("/toActivitySquare")
    public String toDashboard(Model model) {
        Collection<Activity> activities = activityDao.getAll();
        model.addAttribute("activities", activities);
        return "home_page/activitySquare";
    }

    @RequestMapping("/toActivityParticipate")
    public String toActivityParticipate(Model model, HttpSession session) {
        Collection<Activity> activities = activityDao.getActivityByUserEmail((String)session.getAttribute("loginEmail"));
        model.addAttribute("activities", activities);
        return "home_page/activityParticipate";
    }
}
