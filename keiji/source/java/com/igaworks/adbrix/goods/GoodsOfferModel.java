package com.igaworks.adbrix.goods;

public class GoodsOfferModel {
    private int conversionKey;
    private boolean isDailyEvent;
    private boolean isTest;
    private String mainImg;
    private String midText;
    private boolean noCondition;
    private int realRewardKey;
    private String titleImg;
    private int type;

    public boolean isDailyEvent() {
        return this.isDailyEvent;
    }

    public void setDailyEvent(boolean z) {
        this.isDailyEvent = z;
    }

    public boolean isNoCondition() {
        return this.noCondition;
    }

    public void setNoCondition(boolean z) {
        this.noCondition = z;
    }

    public boolean isTest() {
        return this.isTest;
    }

    public void setTest(boolean z) {
        this.isTest = z;
    }

    public int getConversionKey() {
        return this.conversionKey;
    }

    public void setConversionKey(int i) {
        this.conversionKey = i;
    }

    public int getRealRewardKey() {
        return this.realRewardKey;
    }

    public void setRealRewardKey(int i) {
        this.realRewardKey = i;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }

    public String getTitleImg() {
        return this.titleImg;
    }

    public void setTitleImg(String str) {
        this.titleImg = str;
    }

    public String getMainImg() {
        return this.mainImg;
    }

    public void setMainImg(String str) {
        this.mainImg = str;
    }

    public String getMidText() {
        return this.midText;
    }

    public void setMidText(String str) {
        this.midText = str;
    }
}
