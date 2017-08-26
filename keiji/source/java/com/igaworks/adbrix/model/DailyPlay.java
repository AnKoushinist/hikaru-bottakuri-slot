package com.igaworks.adbrix.model;

public class DailyPlay {
    private int ConversionKey;
    private int ParentConversionKey;
    private int PlayTime;

    public int getPlayTime() {
        return this.PlayTime;
    }

    public int getConversionKey() {
        return this.ConversionKey;
    }

    public int getParentConversionKey() {
        return this.ParentConversionKey;
    }
}
