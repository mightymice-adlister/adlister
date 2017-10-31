package com.codeup.adlister.dao;

import com.codeup.adlister.models.AdIdAndCatId;

public interface AdAndCats {
    void insert(AdIdAndCatId AandC);
    AdIdAndCatId getCategoriesByAdId(Long adId);
}
