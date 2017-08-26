package com.applovin.impl.adview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.applovin.adview.AppLovinInterstitialActivity;
import com.applovin.adview.AppLovinInterstitialAdDialog;
import com.applovin.impl.sdk.AppLovinAdImpl;
import com.applovin.impl.sdk.AppLovinAdImpl.AdTarget;
import com.applovin.impl.sdk.AppLovinSdkImpl;
import com.applovin.impl.sdk.n;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdVideoPlaybackListener;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkUtils;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ah implements AppLovinInterstitialAdDialog {
    public static volatile boolean a = false;
    public static volatile boolean b = false;
    private static final Map c = Collections.synchronizedMap(new HashMap());
    private static volatile boolean o;
    private final String d;
    private final AppLovinSdkImpl e;
    private final WeakReference f;
    private volatile AppLovinAdLoadListener g;
    private volatile AppLovinAdDisplayListener h;
    private volatile AppLovinAdVideoPlaybackListener i;
    private volatile AppLovinAdClickListener j;
    private volatile AppLovinAdImpl k;
    private volatile AdTarget l;
    private volatile w m;
    private volatile String n;

    ah(AppLovinSdk appLovinSdk, Activity activity) {
        if (appLovinSdk == null) {
            throw new IllegalArgumentException("No sdk specified");
        } else if (activity == null) {
            throw new IllegalArgumentException("No activity specified");
        } else {
            this.e = (AppLovinSdkImpl) appLovinSdk;
            this.d = UUID.randomUUID().toString();
            a = true;
            b = false;
            this.f = new WeakReference(activity);
        }
    }

    private void a(int i) {
        Activity l = l();
        if (l != null) {
            l.runOnUiThread(new al(this, i));
        } else {
            this.e.h().e("InterstitialAdDialogWrapper", "Failed to notify load listener: activity reference is stale");
        }
    }

    private void a(Activity activity) {
        Object xVar = new x(this.e, activity);
        xVar.a(this);
        this.m = xVar;
        xVar.a(this.k, this.n);
    }

    private void a(AppLovinAd appLovinAd) {
        if (this.h != null) {
            this.h.adHidden(appLovinAd);
        }
    }

    public static ah b(String str) {
        return (ah) c.get(str);
    }

    private void b(Activity activity) {
        Intent intent = new Intent(activity, AppLovinInterstitialActivity.class);
        intent.putExtra("com.applovin.interstitial.wrapper_id", this.d);
        AppLovinInterstitialActivity.a = this;
        activity.startActivity(intent);
        a(true);
    }

    private void b(AppLovinAd appLovinAd) {
        Activity l = l();
        if (l != null) {
            l.runOnUiThread(new ak(this, appLovinAd));
        } else {
            this.e.h().e("InterstitialAdDialogWrapper", "Failed to notify load listener: activity reference is stale");
        }
    }

    private Activity l() {
        return this.f != null ? (Activity) this.f.get() : null;
    }

    public void a() {
        a(null);
    }

    public void a(w wVar) {
        this.m = wVar;
    }

    public void a(AppLovinAd appLovinAd, String str) {
        if (h()) {
            this.e.h().e("AppLovinInterstitialAdDialog", "Attempted to show an interstitial while one is already displayed; ignoring.");
            return;
        }
        c.put(this.d, this);
        this.k = (AppLovinAdImpl) appLovinAd;
        this.n = str;
        this.l = this.k != null ? this.k.d() : AdTarget.DEFAULT;
        Context l = l();
        if (l == null) {
            this.e.h().d("InterstitialAdDialogWrapper", "Failed to show interstitial: stale activity reference provided");
            a(appLovinAd);
        } else if (!AppLovinSdkUtils.d(this.k.k()) || this.e.o().a(this.k.k(), l)) {
            boolean a = n.a(AppLovinInterstitialActivity.class, l);
            boolean z = this.l == AdTarget.ACTIVITY_LANDSCAPE || this.l == AdTarget.ACTIVITY_PORTRAIT;
            l.runOnUiThread(new aj(this, a, z, l));
        } else {
            a(appLovinAd);
        }
    }

    public void a(AppLovinAdClickListener appLovinAdClickListener) {
        this.j = appLovinAdClickListener;
    }

    public void a(AppLovinAdDisplayListener appLovinAdDisplayListener) {
        this.h = appLovinAdDisplayListener;
    }

    public void a(AppLovinAdVideoPlaybackListener appLovinAdVideoPlaybackListener) {
        this.i = appLovinAdVideoPlaybackListener;
    }

    public void a(String str) {
        this.e.e().a(AppLovinAdSize.c, new ai(this, str));
    }

    public void a(boolean z) {
        o = z;
    }

    public void b() {
        Activity l = l();
        if (l == null) {
            this.e.h().e("InterstitialAdDialogWrapper", "Failed to notify load listener: activity reference is stale");
        } else if (this.m != null) {
            l.runOnUiThread(new am(this));
        }
    }

    public AppLovinSdk c() {
        return this.e;
    }

    public AppLovinAd d() {
        return this.k;
    }

    public AppLovinAdVideoPlaybackListener e() {
        return this.i;
    }

    public AppLovinAdDisplayListener f() {
        return this.h;
    }

    public AppLovinAdClickListener g() {
        return this.j;
    }

    public boolean h() {
        return o;
    }

    public AdTarget i() {
        return this.l;
    }

    public String j() {
        return this.n;
    }

    public void k() {
        a = false;
        b = true;
        c.remove(this.d);
    }
}
