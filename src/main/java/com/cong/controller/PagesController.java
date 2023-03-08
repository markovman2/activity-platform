package com.cong.controller;

import com.cong.dao.ActivityDao;
import com.cong.pojo.Activity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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
        return "home_page/activitySquare.html";
    }

    @RequestMapping("/toActivityPage/activityId={id}")
    public String toActivityPage(@PathVariable Integer id, Model model) {
        model.addAttribute("activityId", id);
        return "activityPage/activity";
    }

    @RequestMapping("/toActivityParticipate")
    public String toActivityParticipate(Model model, HttpSession session) {
        Collection<Activity> activities = activityDao.getActivityByUserEmail((String)session.getAttribute("loginEmail"));
        model.addAttribute("activities", activities);
        return "home_page/activityParticipate";
    }


}
