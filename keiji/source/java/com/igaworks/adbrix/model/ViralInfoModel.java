package com.igaworks.adbrix.model;

public class ViralInfoModel {
    private String imageURL;
    private boolean isTest;
    private String itemName;
    private String itemQuantity;
    private boolean result;
    private int resultCode;
    private String resultMsg;

    public void setTest(boolean z) {
        this.isTest = z;
    }

    public boolean isResult() {
        return this.result;
    }

    public void setResult(boolean z) {
        this.result = z;
    }

    public int getResultCode() {
        return this.resultCode;
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

    public String getImageURL() {
        return this.imageURL;
    }

    public void setImageURL(String str) {
        this.imageURL = str;
    }

    public String getItemName() {
        return this.itemName;
    }

    public void setItemName(String str) {
        this.itemName = str;
    }

    public String getItemQuantity() {
        return this.itemQuantity;
    }

    public void setItemQuantity(String str) {
        this.itemQuantity = str;
    }
}
