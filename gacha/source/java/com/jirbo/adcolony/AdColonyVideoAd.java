package com.jirbo.adcolony;

public class AdColonyVideoAd extends AdColonyInterstitialAd {
    public AdColonyVideoAd(String str) {
        super(str);
    }

    public AdColonyVideoAd withListener(AdColonyAdListener adColonyAdListener) {
        this.y = adColonyAdListener;
        return this;
    }
}
