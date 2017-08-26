package com.igaworks.adbrix.model;

import java.util.List;

public class Promotion {
    private int CampaignKey;
    private int CampaignType;
    private PromotionDisplay Display;
    private boolean IsFilterAlreadyInstalled;
    private List<Segment> Segments;
    private String TargetAppScheme;
    private boolean isVisible;

    public void setVisible(boolean z) {
        this.isVisible = z;
    }

    public int getCampaignType() {
        return this.CampaignType;
    }

    public int getCampaignKey() {
        return this.CampaignKey;
    }

    public List<Segment> getSegments() {
        return this.Segments;
    }

    public PromotionDisplay getDisplay() {
        return this.Display;
    }

    public String getTargetAppScheme() {
        return this.TargetAppScheme;
    }

    public boolean isIsFilterAlreadyInstalled() {
        return this.IsFilterAlreadyInstalled;
    }
}
