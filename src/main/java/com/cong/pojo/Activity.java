package com.cong.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Activity {
    /**
     * id: unique for each activity
     * name: the name of the activity
     * introduction: some words to introduce the targeted activity
     * startTime: the start time of the activity
     * endTime: the end time of the activity
     * capacity: the max num of people can join the activity
     * status: 0 for the activity finished, 1 for the activity going on, 2 for the activity hasn't started
     * model: the picture of the activity
     * labels: areas the activity involve in(such as academic、volunteer)
     * comments: the participants commented on the activity.
     * stars: the participants give for the activity.
     */
    private Integer id;
    private String name;
    private String introduction;
    private Timestamp startTime;
    private Timestamp endTime;
    private Integer capacity;
    private Integer status;
    private byte[] model;
    private List<Integer> labels;
    private List<String> comments;
    private List<Integer> stars;

    public Activity(Integer id, String name, String introduction, Timestamp startTime, Timestamp endTime, Integer capacity, byte[] model, List<Integer> labels) {
        this.id = id;
        this.name = name;
        this.introduction = introduction;
        this.startTime = startTime;
        this.endTime = endTime;
        this.capacity = capacity;
        this.status = 2;
        this.model = model;
        this.labels = labels;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public String getStartTimeToString() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(startTime);
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public String getEndTimeToString() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(endTime);
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public String timeToString() {
        String format="YYYY-MM-dd-HH-mm-ss";
        return "活动时间：\n" +
                "开始时间：" + startTime +
                "\n结束时间：" + endTime;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public byte[] getModel() {
        return model;
    }

    public void setModel(byte[] model) {
        this.model = model;
    }

    public List<Integer> getLabels() {
        return labels;
    }

    public void setLabels(List<Integer> labels) {
        this.labels = labels;
    }

    public List<String> getComments() {
        return comments;
    }

    public void setComments(List<String> comments) {
        this.comments = comments;
    }

    public List<Integer> getStars() {
        return stars;
    }

    public void setStars(List<Integer> stars) {
        this.stars = stars;
    }
}
