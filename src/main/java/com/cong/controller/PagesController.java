package com.cong.controller;

import com.cong.dao.ActivityDao;
import com.cong.pojo.Activity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;
import java.util.Collection;

@Controller
@Service
public class PagesController {

    @Resource
    private ActivityDao activityDao;

    @PostMapping("/toActivitySquare")
    public String toDashboard(Model model) {
        Collection<Activity> activities = activityDao.getAll();
        model.addAttribute("activities", activities);
        return "home_page/activitySquare";
    }
}
