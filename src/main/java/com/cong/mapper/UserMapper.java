package com.cong.mapper;

import com.cong.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface UserMapper {

    List<User> queryUserList();

    List<User> queryUserByAccountStatus(Integer accountStatus);

    List<User> queryUserByName(String name);

    User queryUserById(Integer id);

    User queryUserByEmail(String email);

    String queryAuthorityByEmail(String email);

    int addUser(User user);

    int updateUser(User user);

    int deleteUser(Integer id);

    int userParticipateActivities(@RequestParam("userId") Integer userId, @RequestParam("activityId") Integer activityId);

}
