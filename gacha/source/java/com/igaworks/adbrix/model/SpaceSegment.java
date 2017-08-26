package com.igaworks.adbrix.model;

import java.util.List;

public class SpaceSegment {
    private int CampaignType;
    private List<Segment> Segments;

    public int getCampaignType() {
        return this.CampaignType;
    }

    public List<Segment> getSegments() {
        return this.Segments;
    }
}
