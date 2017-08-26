package com.jirbo.adcolony;

import android.graphics.Bitmap;
import com.tapjoy.mraid.controller.Abstract;

public class AdColonyInterstitialAd extends AdColonyAd {
    AdColonyNativeAdListener C;
    boolean D;

    public AdColonyInterstitialAd() {
        a.D = false;
        a.e();
        this.k = "interstitial";
        this.l = Abstract.FULL_SCREEN;
        this.D = false;
        this.m = aa.b();
    }

    public AdColonyInterstitialAd(String str) {
        this.k = "interstitial";
        this.l = Abstract.FULL_SCREEN;
        a.e();
        this.h = str;
        this.D = false;
        this.m = aa.b();
    }

    boolean b() {
        return false;
    }

    public AdColonyInterstitialAd withListener(AdColonyAdListener adColonyAdListener) {
        this.y = adColonyAdListener;
        return this;
    }

    boolean a(boolean z) {
        boolean z2 = false;
        if (this.h == null) {
            this.h = a.l.e();
            if (this.h == null) {
                return false;
            }
        }
        d dVar = a.l;
        String str = this.h;
        if (!z) {
            z2 = true;
        }
        return dVar.a(str, z, z2);
    }

    public boolean isReady() {
        if (this.h == null) {
            this.h = a.l.e();
            if (this.h == null) {
                return false;
            }
        }
        if (!AdColony.isZoneNative(this.h)) {
            return a.l.e(this.h);
        }
        a.am = 12;
        return false;
    }

    public void show() {
        if (this.D) {
            l.d.b((Object) "Show attempt on out of date ad object. Please instantiate a new ad object for each ad attempt.");
            return;
        }
        a.am = 0;
        this.k = "interstitial";
        this.l = Abstract.FULL_SCREEN;
        if (isReady()) {
            this.g = a.am;
            this.D = true;
            if (a.E) {
                AnonymousClass2 anonymousClass2 = new j(this, a.l) {
                    final /* synthetic */ AdColonyInterstitialAd a;

                    void a() {
                        this.o.d.a(this.a.h, this.a);
                    }
                };
                a.E = false;
                c();
                a.T = this;
                if (!a.l.b(this)) {
                    if (this.y != null) {
                        this.y.onAdColonyAdAttemptFinished(this);
                    }
                    a.E = true;
                    return;
                } else if (this.y != null) {
                    this.y.onAdColonyAdStarted(this);
                }
            }
            this.f = 4;
            return;
        }
        this.g = a.am;
        AnonymousClass1 anonymousClass1 = new j(this, a.l) {
            final /* synthetic */ AdColonyInterstitialAd a;

            void a() {
                if (this.a.h != null) {
                    this.o.d.a(this.a.h, this.a);
                }
            }
        };
        this.f = 2;
        if (this.y != null) {
            this.y.onAdColonyAdAttemptFinished(this);
        }
        this.D = true;
    }

    void a() {
        this.k = "interstitial";
        this.l = Abstract.FULL_SCREEN;
        if (this.y != null && !this.w) {
            this.y.onAdColonyAdAttemptFinished(this);
        } else if (this.C != null) {
            if (canceled()) {
                this.x.I = true;
            } else {
                this.x.I = false;
            }
            this.C.onAdColonyNativeAdFinished(true, this.x);
        }
        System.gc();
        if (!(a.D || AdColonyBrowser.B)) {
            for (int i = 0; i < a.an.size(); i++) {
                ((Bitmap) a.an.get(i)).recycle();
            }
            a.an.clear();
        }
        this.w = true;
        a.U = null;
        a.E = true;
    }
}
