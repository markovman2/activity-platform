package com.cong.mapper;

import com.cong.pojo.Activity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Mapper
@Repository
public interface ActivityMapper {

    List<Activity> queryActivityList();

    List<Activity> queryActivityByStatus(Integer status);

    List<Activity> queryActivityByName(String name);

    List<Activity> queryAvailableActivityByTime(Timestamp currentTime);

    List<Activity> queryGoingActivityByTime(Timestamp currentTime);

    List<Activity> queryFinishedActivityByTime(Timestamp currentTime);

    List<Activity> querySelfActivity(String email);

    List<Activity> querySelfFavouriteActivity(String email);

    int addActivity(Activity activity);

    int updateActivity(Activity activity);

    int deleteActivity(Integer id);
}
