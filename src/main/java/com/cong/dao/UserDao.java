package com.cong.dao;

import com.cong.mapper.UserMapper;
import com.cong.pojo.User;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.Collection;

@Repository
public class UserDao {

    private static Integer initId = 2;

    @Resource
    private UserMapper userMapper;

    public void save(User user) {
        if (user.getId() == null) {
            user.setId(initId++);
        }
        userMapper.addUser(user);
    }

    public Collection<User> getAll() {
        return userMapper.queryUserList();
    }

    public Collection<User> getUserByAccountStatus(Integer accountStatus) {
        return userMapper.queryUserByAccountStatus(accountStatus);
    }

    public User getUserById(Integer id) {
        return userMapper.queryUserById(id);
    }

    public User getUserByEmail(String email) {
        return userMapper.queryUserByEmail(email);
    }

    public String getAuthorityByEmail(String email) {
        return userMapper.queryAuthorityByEmail(email);
    }

    public void deleteUserById(Integer id) {
        userMapper.deleteUser(id);
    }

    public void userParticipateActivities(Integer userId, Integer activityId) {
        userMapper.userParticipateActivities(userId, activityId);
    }

}
