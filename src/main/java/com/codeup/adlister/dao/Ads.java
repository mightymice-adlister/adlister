package com.codeup.adlister.dao;

import com.codeup.adlister.models.Ad;

import java.util.List;

public interface Ads {
    // get a list of all the ads
    List<Ad> all();
    // insert a new ad and return the new ad's id
    Long insert(Ad ad);
    Ad viewOneAd(Long id);
    List<Ad> searchAll(String terms);
    List<Ad> viewAdsByUser(Long userId);
    String getCatNameById(Long catId);
}
