package com.jirbo.adcolony;

import com.tapjoy.TJAdUnitConstants.String;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import org.cocos2dx.lib.BuildConfig;

public abstract class AdColonyAd implements Serializable {
    static final int a = 0;
    static final int b = 1;
    static final int c = 2;
    static final int d = 3;
    static final int e = 4;
    HashMap<String, String> A = new HashMap();
    HashMap<String, ArrayList<String>> B = new HashMap();
    int f = a;
    int g;
    String h;
    ad i;
    a j;
    String k = BuildConfig.FLAVOR;
    String l = BuildConfig.FLAVOR;
    String m = BuildConfig.FLAVOR;
    String n = BuildConfig.FLAVOR;
    String o = BuildConfig.FLAVOR;
    double p = 0.0d;
    double q = 0.0d;
    int r;
    boolean s;
    boolean t;
    boolean u;
    boolean v;
    boolean w;
    AdColonyNativeAdView x;
    AdColonyAdListener y;
    AdColonyIAPEngagement z = AdColonyIAPEngagement.NONE;

    abstract void a();

    abstract boolean a(boolean z);

    abstract boolean b();

    abstract boolean isReady();

    public boolean shown() {
        return this.f == e;
    }

    public boolean notShown() {
        return this.f != e;
    }

    public boolean canceled() {
        return this.f == b;
    }

    public boolean noFill() {
        return this.f == c;
    }

    public boolean skipped() {
        return this.f == d;
    }

    public boolean iapEnabled() {
        return this.v;
    }

    public AdColonyIAPEngagement iapEngagementType() {
        return this.z;
    }

    public String iapProductID() {
        return this.n;
    }

    public int getAvailableViews() {
        if (isReady() && c()) {
            return this.i.f();
        }
        return a;
    }

    boolean c() {
        return b(false);
    }

    boolean b(boolean z) {
        if (this.f == e) {
            return true;
        }
        if (!isReady() && !z) {
            return false;
        }
        if (!a(true) && z) {
            return false;
        }
        this.i = a.l.g(this.h);
        this.j = z ? this.i.l() : this.i.k();
        if (this.j == null) {
            return false;
        }
        this.A.put("replay", this.j.x.a);
        this.A.put("card_shown", this.j.x.b);
        this.A.put("html5_interaction", this.j.x.c);
        this.A.put("cancel", this.j.x.d);
        this.A.put("download", this.j.x.e);
        this.A.put("skip", this.j.x.f);
        this.A.put(String.VIDEO_INFO, this.j.x.g);
        this.A.put("custom_event", this.j.x.h);
        this.A.put(String.VIDEO_MIDPOINT, this.j.x.i);
        this.A.put("card_dissolved", this.j.x.j);
        this.A.put(String.VIDEO_START, this.j.x.k);
        this.A.put("third_quartile", this.j.x.l);
        this.A.put(String.VIDEO_COMPLETE, this.j.x.m);
        this.A.put("continue", this.j.x.n);
        this.A.put("in_video_engagement", this.j.x.o);
        this.A.put("reward_v4vc", this.j.x.p);
        this.A.put("v4iap", this.j.x.q);
        this.A.put("first_quartile", this.j.x.r);
        this.A.put("video_expanded", this.j.x.s);
        this.A.put("sound_mute", this.j.x.t);
        this.A.put("sound_unmute", this.j.x.u);
        this.A.put("video_paused", this.j.x.v);
        this.A.put("video_resumed", this.j.x.w);
        this.A.put("native_start", this.j.x.x);
        this.A.put("native_first_quartile", this.j.x.y);
        this.A.put("native_midpoint", this.j.x.z);
        this.A.put("native_third_quartile", this.j.x.A);
        this.A.put("native_complete", this.j.x.B);
        this.A.put("native_overlay_click", this.j.x.C);
        this.B.put("replay", this.j.u.a);
        this.B.put("card_shown", this.j.u.b);
        this.B.put("html5_interaction", this.j.u.c);
        this.B.put("cancel", this.j.u.d);
        this.B.put("download", this.j.u.e);
        this.B.put("skip", this.j.u.f);
        this.B.put(String.VIDEO_MIDPOINT, this.j.u.h);
        this.B.put("card_dissolved", this.j.u.i);
        this.B.put(String.VIDEO_START, this.j.u.j);
        this.B.put("third_quartile", this.j.u.k);
        this.B.put(String.VIDEO_COMPLETE, this.j.u.l);
        this.B.put("continue", this.j.u.m);
        this.B.put("in_video_engagement", this.j.u.n);
        this.B.put("reward_v4vc", this.j.u.o);
        this.B.put("v4iap", this.j.u.q);
        this.B.put("first_quartile", this.j.u.p);
        this.B.put("video_expanded", this.j.u.r);
        this.B.put("sound_mute", this.j.u.s);
        this.B.put("sound_unmute", this.j.u.t);
        this.B.put("video_paused", this.j.u.u);
        this.B.put("video_resumed", this.j.u.v);
        this.B.put("native_start", this.j.u.w);
        this.B.put("native_first_quartile", this.j.u.x);
        this.B.put("native_midpoint", this.j.u.y);
        this.B.put("native_third_quartile", this.j.u.z);
        this.B.put("native_complete", this.j.u.A);
        this.B.put("native_overlay_click", this.j.u.B);
        return true;
    }

    public String getZoneID() {
        if (this.h == null) {
            return BuildConfig.FLAVOR;
        }
        return this.h;
    }
}
