package com.example.mybatisGen.entity;

public class Person {
    private Long id;

    private String userName;

    private String userLocal;

    private String userSex;

    private String nickName;

    private String showName;

    public Person(Long id, String userName, String userLocal, String userSex, String nickName, String showName) {
        this.id = id;
        this.userName = userName;
        this.userLocal = userLocal;
        this.userSex = userSex;
        this.nickName = nickName;
        this.showName = showName;
    }

    public Person() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserLocal() {
        return userLocal;
    }

    public void setUserLocal(String userLocal) {
        this.userLocal = userLocal == null ? null : userLocal.trim();
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex == null ? null : userSex.trim();
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName == null ? null : showName.trim();
    }
}