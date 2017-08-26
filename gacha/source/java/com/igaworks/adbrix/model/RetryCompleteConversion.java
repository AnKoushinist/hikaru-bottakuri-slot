package com.igaworks.adbrix.model;

public class RetryCompleteConversion {
    private int conversionKey;
    private int retryCount;

    public RetryCompleteConversion(int i, int i2) {
        this.conversionKey = i;
        this.retryCount = i2;
    }

    public int getConversionKey() {
        return this.conversionKey;
    }

    public int getRetryCount() {
        return this.retryCount;
    }
}
