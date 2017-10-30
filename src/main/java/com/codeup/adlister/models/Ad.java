package com.codeup.adlister.models;

import com.codeup.adlister.dao.DaoFactory;

import java.util.List;

public class Ad {
    private long id;
    private long userId;
    private String title;
    private String description;
    private List<Long> catId;
    private List<String> catName;

    public Ad(long id, long userId, String title, String description) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.description = description;

    }

    public Ad(long userId, String title, String description) {
        this.userId = userId;
        this.title = title;
        this.description = description;

    }

//    public Ad(long id, long userId, String title, String description) {
//        this.id = id;
//        this.userId = userId;
//        this.title = title;
//        this.description = description;
//        this.catId = catId;
//        this.catName = DaoFactory.getAdsDao().getCatNameById(catId);
//    }

    public Ad(long userId, String title, String description, List<Long> catId) {
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.catId = catId;
        this.catName = DaoFactory.getAdsDao().getCatNamesArray(catId);
    }

    public List<String> getCatName() {
        return catName;
    }

    public void setCatName(List<String> catName) {
        this.catName = catName;
    }

    public List<Long> getCatId() {
        return catId;
    }

    public void setCatId(List<Long> catId) {
        this.catId = catId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
