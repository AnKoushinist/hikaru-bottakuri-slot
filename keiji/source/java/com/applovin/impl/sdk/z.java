package com.applovin.impl.sdk;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.applovin.adview.AppLovinInterstitialAd;
import com.applovin.adview.AppLovinInterstitialAdDialog;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdRewardListener;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdType;
import com.applovin.sdk.AppLovinAdVideoPlaybackListener;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkUtils;
import java.lang.ref.SoftReference;

public class z {
    private static String l = null;
    private final AppLovinSdkImpl a;
    private final AppLovinAdServiceImpl b;
    private AppLovinAdImpl c;
    private String d;
    private SoftReference e;
    private final Handler f;
    private final Object g = new Object();
    private volatile String h;
    private dj i;
    private volatile boolean j = false;
    private SoftReference k;

    public z(AppLovinSdk appLovinSdk) {
        this.a = (AppLovinSdkImpl) appLovinSdk;
        this.b = (AppLovinAdServiceImpl) appLovinSdk.e();
        this.f = new Handler(Looper.getMainLooper());
    }

    public static void a(String str) {
        l = str;
    }

    public static String b() {
        return l;
    }

    private void e() {
        if (this.e != null) {
            AppLovinAdLoadListener appLovinAdLoadListener = (AppLovinAdLoadListener) this.e.get();
            if (appLovinAdLoadListener != null) {
                appLovinAdLoadListener.failedToReceiveAd(-300);
            }
        }
    }

    private AppLovinAdRewardListener f() {
        return new aa(this);
    }

    void a(Activity activity, AppLovinAdRewardListener appLovinAdRewardListener, AppLovinAdVideoPlaybackListener appLovinAdVideoPlaybackListener, AppLovinAdDisplayListener appLovinAdDisplayListener, AppLovinAdClickListener appLovinAdClickListener) {
        if (a()) {
            AppLovinAd appLovinAd = this.c;
            if (appLovinAd.c().equals(AppLovinAdType.b)) {
                AppLovinInterstitialAdDialog a = AppLovinInterstitialAd.a(this.a, activity);
                AppLovinAdRewardListener aeVar = new ae(this, activity, appLovinAdRewardListener, appLovinAdVideoPlaybackListener, appLovinAdDisplayListener, appLovinAdClickListener);
                a.a((AppLovinAdDisplayListener) aeVar);
                a.a((AppLovinAdVideoPlaybackListener) aeVar);
                a.a((AppLovinAdClickListener) aeVar);
                a.a(appLovinAd, this.d);
                this.k = new SoftReference(a);
                a(appLovinAd, aeVar);
                return;
            }
            this.a.h().d("IncentivizedAdController", "Attempted to render an ad of type " + this.c.c() + " in an Incentivized Ad interstitial.");
            appLovinAdVideoPlaybackListener.videoPlaybackEnded(this.c, 0.0d, false);
            return;
        }
        this.a.h().e("IncentivizedAdController", "Skipping incentivized video playback: user attempted to play an incentivized video before one was preloaded.");
        e();
    }

    public void a(Activity activity, String str, AppLovinAdRewardListener appLovinAdRewardListener, AppLovinAdVideoPlaybackListener appLovinAdVideoPlaybackListener, AppLovinAdDisplayListener appLovinAdDisplayListener, AppLovinAdClickListener appLovinAdClickListener) {
        AppLovinAdRewardListener f = appLovinAdRewardListener == null ? f() : appLovinAdRewardListener;
        if (!a()) {
            this.a.h().e("IncentivizedAdController", "Skipping incentivized video playback: user attempted to play an incentivized video before one was preloaded.");
            e();
        } else if (!AppLovinSdkUtils.d(this.c.k()) || this.a.o().a(this.c.k(), (Context) activity)) {
            this.d = str;
            if (((Boolean) this.a.a(cb.Z)).booleanValue()) {
                aq aqVar = new aq(this.a, this);
                aqVar.a(activity);
                aqVar.a(appLovinAdDisplayListener);
                aqVar.a(appLovinAdClickListener);
                aqVar.a(appLovinAdVideoPlaybackListener);
                aqVar.a(f);
                aqVar.a();
                return;
            }
            a(activity, f, appLovinAdVideoPlaybackListener, appLovinAdDisplayListener, appLovinAdClickListener);
        }
    }

    void a(AppLovinAd appLovinAd, AppLovinAdRewardListener appLovinAdRewardListener) {
        this.i = new dj(this.a, appLovinAd, appLovinAdRewardListener);
        this.a.m().a(this.i, cw.BACKGROUND);
    }

    public void a(AppLovinAdLoadListener appLovinAdLoadListener) {
        this.a.h().a("IncentivizedAdController", "User requested preload of incentivized ad...");
        this.e = new SoftReference(appLovinAdLoadListener);
        if (a()) {
            this.a.h().e("IncentivizedAdController", "Attempted to call preloadAndNotify: while an ad was already loaded or currently being played. Do not call preloadAndNotify: again until the last ad has been closed (adHidden).");
            if (appLovinAdLoadListener != null) {
                appLovinAdLoadListener.adReceived(this.c);
                return;
            }
            return;
        }
        this.b.a(AppLovinAdSize.c, AppLovinAdType.b, new ab(this, appLovinAdLoadListener));
    }

    void a(AppLovinAdRewardListener appLovinAdRewardListener) {
        appLovinAdRewardListener.userDeclinedToViewAd(this.c);
    }

    void a(String str, Activity activity) {
        if (str != null && ((Boolean) this.a.a(cb.aa)).booleanValue()) {
            new ao(this.a, activity, str).a();
        }
    }

    public boolean a() {
        return this.c != null;
    }

    void b(String str) {
        synchronized (this.g) {
            this.h = str;
        }
    }

    String c() {
        String str;
        synchronized (this.g) {
            str = this.h;
        }
        return str;
    }

    public void d() {
        if (this.k != null) {
            AppLovinInterstitialAdDialog appLovinInterstitialAdDialog = (AppLovinInterstitialAdDialog) this.k.get();
            if (appLovinInterstitialAdDialog != null) {
                appLovinInterstitialAdDialog.b();
            }
        }
    }
}
