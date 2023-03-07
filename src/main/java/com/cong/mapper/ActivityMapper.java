package com.cong.mapper;

import com.cong.pojo.Activity;
import org.apache.ibatis.annotations.Mapper;
import org.joda.time.DateTime;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ActivityMapper {

    List<Activity> queryActivityList();

    List<Activity> queryActivityByStatus(Integer status);

    List<Activity> queryActivityByName(String name);

    List<Activity> queryAvailableActivityByTime(DateTime currentTime);

    List<Activity> queryGoingActivityByTime(DateTime currentTime);

    List<Activity> queryFinishedActivityByTime(DateTime currentTime);

    int addActivity(Activity activity);

    int updateActivity(Activity activity);

    int deleteActivity(Integer id);
}
