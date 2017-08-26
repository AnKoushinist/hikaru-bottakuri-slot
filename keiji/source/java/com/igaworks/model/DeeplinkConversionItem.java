package com.igaworks.model;

public class DeeplinkConversionItem {
    private String commerceClickID;
    private int conversionKey;
    private int key;
    private String linkParams;
    private int retryCnt;

    public DeeplinkConversionItem(int i, int i2, String str, String str2, int i3) {
        this.key = i;
        this.conversionKey = i2;
        this.commerceClickID = str;
        this.linkParams = str2;
        this.retryCnt = i3;
    }

    public int getRetryCnt() {
        return this.retryCnt;
    }

    public int getKey() {
        return this.key;
    }

    public int getConversionKey() {
        return this.conversionKey;
    }

    public String getCommerceClickID() {
        return this.commerceClickID;
    }

    public String getLinkParams() {
        return this.linkParams;
    }
}
