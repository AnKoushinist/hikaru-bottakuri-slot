package com.igaworks.adbrix.model;

public class ViralCPIModel {
    private int CampaignKey;
    private String CheckRewardText;
    private String CloseBtnColorCode;
    private String CloseBtnTextColorCode;
    private String CompleteCopyText;
    private int ConversionKey;
    private boolean IsTrackingURLSetting = false;
    private String ItemName;
    private String ItemQuantity;
    private String ItemURL;
    private String NoMoreShowColorCode;
    private long ParentConversionKey;
    private String RewardDetailText;
    private String RewardText;
    private String SharingMessage;
    private String SharingTitle;
    private String TopbarColorCode;
    private String TopbarTitleTextColorCode;
    private String ViralConfirmBtnColorCode;
    private String ViralConfirmBtnText;
    private String ViralConfirmBtnTextColorCode;
    private String ViralInfoDialogBGColorCode;
    private String ViralInfoTitle;
    private String ViralMessage;

    public boolean isIsTrackingURLSetting() {
        return this.IsTrackingURLSetting;
    }

    public void setIsTrackingURLSetting(boolean z) {
        this.IsTrackingURLSetting = z;
    }

    public String getItemURL() {
        return this.ItemURL;
    }

    public void setItemURL(String str) {
        this.ItemURL = str;
    }

    public String getItemName() {
        return this.ItemName;
    }

    public void setItemName(String str) {
        this.ItemName = str;
    }

    public String getItemQuantity() {
        return this.ItemQuantity;
    }

    public void setItemQuantity(String str) {
        this.ItemQuantity = str;
    }

    public int getConversionKey() {
        return this.ConversionKey;
    }

    public long getParentConversionKey() {
        return this.ParentConversionKey;
    }

    public int getCampaignKey() {
        return this.CampaignKey;
    }

    public String getViralMessage() {
        return this.ViralMessage;
    }

    public void setViralMessage(String str) {
        this.ViralMessage = str;
    }

    public String getViralInfoTitle() {
        return this.ViralInfoTitle;
    }

    public String getRewardText() {
        return this.RewardText;
    }

    public String getRewardDetailText() {
        return this.RewardDetailText;
    }

    public String getCheckRewardText() {
        return this.CheckRewardText;
    }

    public String getViralConfirmBtnText() {
        return this.ViralConfirmBtnText;
    }

    public String getSharingTitle() {
        return this.SharingTitle;
    }

    public String getSharingMessage() {
        return this.SharingMessage;
    }

    public String getCompleteCopyText() {
        return this.CompleteCopyText;
    }

    public String getTopbarColorCode() {
        return this.TopbarColorCode;
    }

    public String getTopbarTitleTextColorCode() {
        return this.TopbarTitleTextColorCode;
    }

    public String getViralInfoDialogBGColorCode() {
        return this.ViralInfoDialogBGColorCode;
    }

    public String getViralConfirmBtnColorCode() {
        return this.ViralConfirmBtnColorCode;
    }

    public String getViralConfirmBtnTextColorCode() {
        return this.ViralConfirmBtnTextColorCode;
    }

    public String getCloseBtnColorCode() {
        return this.CloseBtnColorCode;
    }

    public String getCloseBtnTextColorCode() {
        return this.CloseBtnTextColorCode;
    }

    public String getNoMoreShowColorCode() {
        return this.NoMoreShowColorCode;
    }
}
