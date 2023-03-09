package com.cong.controller;

import com.cong.dao.ActivityDao;
import com.cong.dao.CommentDao;
import com.cong.dao.UserDao;
import com.cong.pojo.Activity;
import io.swagger.models.auth.In;
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

    @Resource
    private UserDao userDao;

    @Resource
    private CommentDao commentDao;

    @RequestMapping("/toActivitySquare")
    public String toDashboard(Model model) {
        Collection<Activity> activities = activityDao.getAll();
        model.addAttribute("activities", activities);
        model.addAttribute("pageName", "activitySquare.html");
        return "home_page/activitySquare";
    }

    @RequestMapping("/toActivityPage/activityId={id}")
    public String toActivityPage(@PathVariable Integer id, Model model, HttpSession session) {
        Activity activity = activityDao.getActivityByActivityId(id);
        addActivityInfo(model, session, activity, id);
        return "activityPage/activity";
    }

    @RequestMapping("/toActivityParticipate")
    public String toActivityParticipate(Model model, HttpSession session) {
        Collection<Activity> activities = activityDao.getActivityByUserEmail((String)session.getAttribute("loginEmail"));
        model.addAttribute("activities", activities);
        model.addAttribute("pageName", "activityParticipate.html");
        return "home_page/activitySquare";
    }

    @RequestMapping("/toActivityEnshrined")
    public String toActivityEnshrined(Model model, HttpSession session) {
        Collection<Activity> activities = activityDao.getFavouriteActivityByUserEmail((String)session.getAttribute("loginEmail"));
        model.addAttribute("activities", activities);
        model.addAttribute("pageName", "activityEnshrined.html");
        return "home_page/activitySquare";
    }

    @RequestMapping("/toJoin")
    public String toJoin(Model model, HttpSession session) {
        Integer activityId = (Integer)session.getAttribute("activityId");
        Activity activity = activityDao.getActivityByActivityId(activityId);
        userDao.userParticipateActivities(userDao.getUserByEmail((String)session.getAttribute("loginEmail")).getId(), (Integer)session.getAttribute("activityId"));
        addActivityInfo(model, session, activity, activityId);
        return "activityPage/activity";
    }

    public void addActivityInfo(Model model, HttpSession session, Activity activity, Integer id) {
        model.addAttribute("activity", activity);
        model.addAttribute("activityId", id);
        model.addAttribute("comments", commentDao.getCommentByActivityId(id));
        model.addAttribute("msg", null);
        session.setAttribute("activityId", id);
        if (activityDao.getActivityByUserId(userDao.getUserByEmail((String)session.getAttribute("loginEmail")).getId()).contains(id)) {
            model.addAttribute("msg", "not participate");
        }
    }
}
