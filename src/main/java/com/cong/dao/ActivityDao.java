package com.cong.dao;

import com.cong.mapper.ActivityMapper;
import com.cong.pojo.Activity;
import org.joda.time.DateTime;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
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
                return activityMapper.queryAvailableActivityByTime(DateTime.now());
            case 1:  //search the activity which is going on.
                return activityMapper.queryGoingActivityByTime(DateTime.now());
            default:  //search the activity which has been finished.
                return activityMapper.queryFinishedActivityByTime(DateTime.now());
        }
    }

    public void updateActivity(Activity activity) {
        activityMapper.updateActivity(activity);
    }

    public void deleteActivityById(Integer id) {
        activityMapper.deleteActivity(id);
    }
}
