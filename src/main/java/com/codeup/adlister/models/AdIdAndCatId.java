package com.codeup.adlister.models;

import java.util.List;

public class AdIdAndCatId {
    private Long adId;
    private List<Long> catIds;

    public AdIdAndCatId(Long adId, List<Long> catIds){
        this.adId = adId;
        this.catIds = catIds;
    }

    public Long getAdId() {
        return adId;
    }

    public List<Long> getCatIds() {
        return catIds;
    }

    public void setAdId(Long adId) {
        this.adId = adId;
    }

    public void setCatIds(List<Long> catIds) {
        this.catIds = catIds;
    }
}
