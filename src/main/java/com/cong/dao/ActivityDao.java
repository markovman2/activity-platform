package com.cong.dao;

import com.cong.mapper.ActivityMapper;
import com.cong.pojo.Activity;
import com.cong.pojo.User;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Collection;

@Repository
public class ActivityDao {

    private static int initId = 2;

    @Resource
    private ActivityMapper activityMapper;

    public void save(Activity activity) {
        if (activity.getId() == null) {
            activity.setId(initId++);
        }
        System.out.println(activity);
        activityMapper.addActivity(activity);
    }

    public Collection<Activity> getAll() {
        return activityMapper.queryActivityList();
    }

    public Collection<Activity> getActivityByStatus(Integer status) {
        return activityMapper.queryActivityByStatus(status);
    }

    public Collection<Activity> getActivityByName(String name) {
        return activityMapper.queryActivityByName(name);
    }

    public Collection<Activity> getActivityByTime(Integer type) {
        switch (type) {
            case 0:  //search the activity which hasn't started.
                return activityMapper.queryAvailableActivityByTime(new Timestamp(System.currentTimeMillis()));
            case 1:  //search the activity which is going on.
                return activityMapper.queryGoingActivityByTime(new Timestamp(System.currentTimeMillis()));
            default:  //search the activity which has been finished.
                return activityMapper.queryFinishedActivityByTime(new Timestamp(System.currentTimeMillis()));
        }
    }

    public Collection<Activity> getActivityByUserEmail(String email) {
        return activityMapper.querySelfActivity(email);
    }

    public void updateActivity(Activity activity) {
        activityMapper.updateActivity(activity);
    }

    public void deleteActivityById(Integer id) {
        activityMapper.deleteActivity(id);
    }
}
