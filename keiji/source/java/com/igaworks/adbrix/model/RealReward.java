package com.igaworks.adbrix.model;

public class RealReward {
    private int ConversionKey;
    private boolean IsDailyEvent;
    private boolean IsTest;
    private String MissionText;
    private boolean NoCondition;
    private long ProgressValidTime;
    private String RealRewardImageUrl;
    private int RealRewardKey;

    public boolean isIsDailyEvent() {
        return this.IsDailyEvent;
    }

    public boolean isNoCondition() {
        return this.NoCondition;
    }

    public boolean isIsTest() {
        return this.IsTest;
    }

    public int getConversionKey() {
        return this.ConversionKey;
    }

    public String getRealRewardImageUrl() {
        return this.RealRewardImageUrl;
    }

    public String getMissionText() {
        return this.MissionText;
    }

    public int getRealRewardKey() {
        return this.RealRewardKey;
    }

    public long getProgressValidTime() {
        return this.ProgressValidTime;
    }
}
