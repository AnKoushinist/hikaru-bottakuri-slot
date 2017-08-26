package com.igaworks.adbrix.model;

import java.util.List;

public class Schedule {
    private List<Engagement> Engagement;
    private Media Media;
    private List<Promotion> Promotion;
    private ReEngagement ReEngagement;
    private List<RealReward> RealRewards;
    private List<Space> Space;
    private List<ViralCPIModel> ViralCPIs;

    public List<RealReward> getRealRewards() {
        return this.RealRewards;
    }

    public List<Engagement> getEngagements() {
        return this.Engagement;
    }

    public List<Promotion> getPromotions() {
        return this.Promotion;
    }

    public List<Space> getSpaces() {
        return this.Space;
    }

    public Media getMedia() {
        return this.Media;
    }

    public List<ViralCPIModel> getViralCPIs() {
        return this.ViralCPIs;
    }

    public ReEngagement getReEngagement() {
        return this.ReEngagement;
    }
}
