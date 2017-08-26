package com.igaworks.adbrix.model;

public class RealRewardResultModel {
    private String FailMsg;
    private boolean Result;
    private int ResultCode;
    private String ResultMessage;
    private String RewardImage;
    private String RewardName;
    private int RewardQuantity;
    private long SessionNo;
    private int StatusCodes;
    private String SuccessMsg;
    private String type;

    public int getStatusCodes() {
        return this.StatusCodes;
    }

    public void setStatusCodes(int i) {
        this.StatusCodes = i;
    }

    public void setFailMsg(String str) {
        this.FailMsg = str;
    }

    public String getRewardName() {
        return this.RewardName;
    }

    public void setRewardName(String str) {
        this.RewardName = str;
    }

    public void setRewardQuantity(int i) {
        this.RewardQuantity = i;
    }

    public String getRewardImage() {
        return this.RewardImage;
    }

    public void setRewardImage(String str) {
        this.RewardImage = str;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public String getSuccessMsg() {
        return this.SuccessMsg;
    }

    public void setSuccessMsg(String str) {
        this.SuccessMsg = str;
    }

    public boolean isResult() {
        return this.Result;
    }

    public void setResult(boolean z) {
        this.Result = z;
    }

    public int getResultCode() {
        return this.ResultCode;
    }

    public void setResultCode(int i) {
        this.ResultCode = i;
    }

    public String getResultMessage() {
        return this.ResultMessage;
    }

    public void setResultMessage(String str) {
        this.ResultMessage = str;
    }

    public long getSessionNo() {
        return this.SessionNo;
    }

    public void setSessionNo(long j) {
        this.SessionNo = j;
    }
}
