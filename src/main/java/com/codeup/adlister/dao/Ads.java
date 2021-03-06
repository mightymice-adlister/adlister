package com.codeup.adlister.dao;

import com.codeup.adlister.models.Ad;

import java.util.List;

public interface Ads {
    // get a list of all the ads
    List<Ad> all();
    // insert a new ad and return the new ad's id
    Long insert(Ad ad);
    Long delete(Long adId);
    Long edit(Long adId, String title, String description);
    Ad viewOneAd(Long id);
    List<Ad> searchAll(String terms);
    List<Ad> viewAdsByUser(Long userId);
    String getCatNameById(Long catId);
    List<String> getCatNamesArray(List<Long> adIds);
}
