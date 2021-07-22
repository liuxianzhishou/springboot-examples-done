package com.example.mybatisGen.entity;

public class Rank {
    private long id;

    private String userName;

    public Rank()
    {
        super();
    }
    public Rank(long id, String userName) {
        this.id = id;
        this.userName = userName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
