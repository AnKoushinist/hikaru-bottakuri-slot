package com.glossomads;

import android.app.Activity;
import com.glossomads.Listener.GlossomAdsAdAvailabilityListener;
import com.glossomads.Listener.GlossomAdsAdListener;
import com.glossomads.Listener.GlossomAdsAdRewardListener;
import com.glossomads.View.SugarAdView;

public class GlossomAds {
    public static void addAdAvailabilityListener(GlossomAdsAdAvailabilityListener glossomAdsAdAvailabilityListener) {
        s.a().a(glossomAdsAdAvailabilityListener);
    }

    public static void removeAdAvailabilityListener(GlossomAdsAdAvailabilityListener glossomAdsAdAvailabilityListener) {
        s.a().b(glossomAdsAdAvailabilityListener);
    }

    public static void setSugarAdRewardListener(GlossomAdsAdRewardListener glossomAdsAdRewardListener) {
        s.a().a(glossomAdsAdRewardListener);
    }

    public static void configure(Activity activity, String str, String str2, String... strArr) {
        s.a().b(activity, str, str2, strArr);
    }

    public static void configureForUnityPlugin(Activity activity, String str, String str2, String... strArr) {
        s.a().a(activity, str, str2, strArr);
    }

    public static boolean showVideo(String str, GlossomAdsAdListener glossomAdsAdListener) {
        return s.a().a(str, glossomAdsAdListener);
    }

    public static void setCustomID(String str) {
        s.a().a(str);
    }

    public static String getCustomID() {
        return s.a().h();
    }

    public static boolean showRewardVideo(String str, GlossomAdsAdListener glossomAdsAdListener) {
        return s.a().b(str, glossomAdsAdListener);
    }

    public static SugarAdView getAdView(Activity activity, String str, int i, int i2) {
        return s.a().a(activity, str, i, i2);
    }

    public static void onPause() {
        s.a().j();
    }

    public static void onResume() {
        s.a().k();
    }

    public static boolean isReady(String str) {
        return s.a().b(str);
    }

    public static boolean isReadyForInterstitial(String str) {
        return s.a().a(str, "interstitial");
    }

    public static boolean isReadyForReward(String str) {
        return s.a().a(str, "v4vc");
    }

    public static void addTestDevice(String str) {
        s.a().f(str);
    }
}
