package com.yh.mybatisgenwebsocket.entity;

public class Flow1 {
    private Long id;

    private String speed;

    private String createTime;

    public Flow1(Long id, String speed, String createTime) {
        this.id = id;
        this.speed = speed;
        this.createTime = createTime;
    }

    public Flow1() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed == null ? null : speed.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }
}