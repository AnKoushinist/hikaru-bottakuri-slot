package com.igaworks.adbrix.model;

public class ViralUrlModel {
    private boolean isTest;
    private boolean result;
    private int resultCode;
    private String resultMsg;
    private String trackingURL;

    public void setTest(boolean z) {
        this.isTest = z;
    }

    public boolean isResult() {
        return this.result;
    }

    public void setResult(boolean z) {
        this.result = z;
    }

    public void setResultCode(int i) {
        this.resultCode = i;
    }

    public String getResultMsg() {
        return this.resultMsg;
    }

    public void setResultMsg(String str) {
        this.resultMsg = str;
    }

    public String getTrackingURL() {
        return this.trackingURL;
    }

    public void setTrackingURL(String str) {
        this.trackingURL = str;
    }
}
