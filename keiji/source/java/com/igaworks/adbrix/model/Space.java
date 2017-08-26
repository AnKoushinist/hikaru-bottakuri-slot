package com.igaworks.adbrix.model;

import java.util.List;

public class Space {
    private List<Integer> CampaignList;
    private String SpaceKey;
    private List<SpaceSegment> SpaceSegments;

    public String getSpaceKey() {
        return this.SpaceKey;
    }

    public List<SpaceSegment> getSpaceSegments() {
        return this.SpaceSegments;
    }

    public List<Integer> getCampaignList() {
        return this.CampaignList;
    }
}
