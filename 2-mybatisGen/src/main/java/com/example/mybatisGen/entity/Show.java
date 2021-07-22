package com.example.mybatisGen.entity;

public class Show {
    private Long id;

    private String showName;

    private String showYear;

    public Show(Long id, String showName, String showYear) {
        this.id = id;
        this.showName = showName;
        this.showYear = showYear;
    }

    public Show() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName == null ? null : showName.trim();
    }

    public String getShowYear() {
        return showYear;
    }

    public void setShowYear(String showYear) {
        this.showYear = showYear == null ? null : showYear.trim();
    }
}