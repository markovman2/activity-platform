package com.cong.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    /**
     * id: unique for each user
     * password: the password of the account(can be the same, should be encrypted)
     * userName: the real name of users
     * userNickname: the name given by users themselves(can be the same)
     * gender: 0 for female, 1 for male, 2 for unknown
     * email: the email address used by users for registration(cannot be the same)
     * accountStatus: 0 for normalization, 1 for prohibition
     * createTime: the actual time of users creating their accounts
     */
    private Integer id;
    private String email;
    private String password;
    private String userName;
    private String userNickname;
    private Integer gender;
    private Integer accountStatus;
    private String createTime;

    public User(String email, String password, String userName, String userNickname, Integer gender) {
        this.email = email;
        this.password = password;
        this.userName = userName;
        this.userNickname = userNickname;
        this.gender = gender;
        this.accountStatus = 0;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.createTime = formatter.format(new Date());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(Integer accountStatus) {
        this.accountStatus = accountStatus;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
