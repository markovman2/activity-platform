package com.cong.controller;

import com.cong.dao.ActivityDao;
import com.cong.dao.CommentDao;
import com.cong.dao.UserDao;
import com.cong.pojo.Activity;
import com.cong.pojo.Comment;
import com.cong.pojo.User;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
public class CommentController {

    @Resource
    private UserDao userDao;

    @Resource
    private ActivityDao activityDao;

    @Resource
    private CommentDao commentDao;

    @GetMapping("/toActivityPage/activityId={id}/comment")
    public String toCommentPage(@PathVariable("id")Integer id, Model model, HttpSession session) {
        model.addAttribute("activityId", id);
        session.setAttribute("activityId", id);
        return "activityPage/comment";
    }

    @ApiOperation("完成评论功能")
    @PostMapping("/toActivityPage/comment")
    public String toComment(@RequestParam @ApiParam(value = "activityComment")String activityComment,
                            @RequestParam @ApiParam(value = "activityStar")Integer activityStar,
                            Model model,
                            HttpSession session) {
        User user = userDao.getUserByEmail((String)session.getAttribute("loginEmail"));
        Activity activity = activityDao.getActivityByActivityId((Integer)session.getAttribute("activityId"));
        commentDao.addComment(new Comment(user.getId(), user.getUserNickname(), activity.getId(), activity.getName(), activityComment, activityStar));
        Integer activityId = (Integer) session.getAttribute("activityId");
        model.addAttribute("activity", activity);
        model.addAttribute("activityId", activityId);
        model.addAttribute("comments", commentDao.getCommentByActivityId(activityId));
        model.addAttribute("msg", null);
        if (activityDao.getActivityByUserId(userDao.getUserByEmail((String)session.getAttribute("loginEmail")).getId()).contains(activityId)) {
            model.addAttribute("msg", "not participate");
        }
        return "activityPage/activity";
    }

}
