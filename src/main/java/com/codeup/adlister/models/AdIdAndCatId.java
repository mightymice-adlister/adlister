package com.codeup.adlister.models;

import java.util.List;

public class AdIdAndCatId {
    private Long adId;
    private List<Long> catIds;

    public AdIdAndCatId(Long adId, List<Long> catIds){
        this.adId = adId;
        this.catIds = catIds;
    }

}
