package com.cong.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    /**
     * userId: the id of the user who made the comment.
     * userNickname: the nickname of the user who made the comment.
     * activityId: the id of the activity which is commented on.
     * activityName: the name of the activity which is commented on.
     * comment: the content of the comments.
     * createTime: the creation time of the comment.
     */
    private Integer userId;
    private String userNickname;
    private Integer activityId;
    private String activityName;
    private String comment;
    private Timestamp createTime;

    public Comment(Integer userId, String userNickname, Integer activityId, String activityName, String comment) {
        this.userId = userId;
        this.userNickname = userNickname;
        this.activityId = activityId;
        this.activityName = activityName;
        this.comment = comment;
        this.createTime = new Timestamp(System.currentTimeMillis());
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
}
