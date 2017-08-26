package com.jirbo.adcolony;

import android.graphics.Bitmap;
import com.tapjoy.mraid.controller.Abstract;
import org.cocos2dx.lib.BuildConfig;

public final class AdColonyV4VCAd extends AdColonyAd {
    boolean C = false;
    boolean D = false;
    boolean E;

    public AdColonyV4VCAd() {
        a.D = false;
        a.e();
        this.k = "v4vc";
        this.E = false;
        this.l = Abstract.FULL_SCREEN;
        this.m = aa.b();
    }

    public AdColonyV4VCAd(String str) {
        a.e();
        this.h = str;
        this.k = "v4vc";
        this.E = false;
        this.l = Abstract.FULL_SCREEN;
        this.m = aa.b();
    }

    public AdColonyV4VCAd withListener(AdColonyAdListener adColonyAdListener) {
        this.y = adColonyAdListener;
        return this;
    }

    public AdColonyV4VCAd withConfirmationDialog(boolean z) {
        this.C = z;
        return this;
    }

    public AdColonyV4VCAd withResultsDialog(boolean z) {
        this.D = z;
        a.D = this.D;
        return this;
    }

    public AdColonyV4VCAd withConfirmationDialog() {
        return withConfirmationDialog(true);
    }

    public AdColonyV4VCAd withResultsDialog() {
        return withResultsDialog(true);
    }

    boolean b() {
        return true;
    }

    boolean a(boolean z) {
        return false;
    }

    public boolean isReady() {
        if (this.h == null) {
            this.h = a.l.f();
            if (this.h == null) {
                return false;
            }
        }
        return a.l.f(this.h);
    }

    public String getRewardName() {
        if (c()) {
            return this.i.n.d;
        }
        return BuildConfig.FLAVOR;
    }

    public int getRewardAmount() {
        if (c()) {
            return this.i.n.c;
        }
        return 0;
    }

    public int getViewsPerReward() {
        if (c()) {
            return this.i.n.f;
        }
        return 0;
    }

    public int getRemainingViewsUntilReward() {
        if (c()) {
            return this.i.n.f - this.i.s;
        }
        return 0;
    }

    public void show() {
        if (this.E) {
            l.d.b((Object) "Show attempt on out of date ad object. Please instantiate a new ad object for each ad attempt.");
            return;
        }
        a.am = 0;
        if (isReady()) {
            this.g = a.am;
            if (a.E) {
                AnonymousClass2 anonymousClass2 = new j(this, a.l) {
                    final /* synthetic */ AdColonyV4VCAd a;

                    void a() {
                        this.o.d.a(this.a.h, this.a);
                    }
                };
                a.E = false;
                c();
                a.T = this;
                a.l.a(this);
                if (this.C) {
                    a("Confirmation");
                    return;
                }
                this.E = true;
                c(true);
                return;
            }
            return;
        }
        this.g = a.am;
        AnonymousClass1 anonymousClass1 = new j(this, a.l) {
            final /* synthetic */ AdColonyV4VCAd a;

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
        this.E = true;
    }

    void c(boolean z) {
        if (!z) {
            this.f = 1;
        } else if (a.l.b(this)) {
            a.o = false;
            if (this.y != null) {
                this.y.onAdColonyAdStarted(this);
            }
            this.f = 4;
        } else {
            this.f = 3;
        }
        if (this.f != 4) {
            a.E = true;
            if (this.y != null) {
                this.y.onAdColonyAdAttemptFinished(this);
            }
        }
    }

    void a() {
        if (this.f == 4 && this.D) {
            a("Result");
        }
        if (!(this.y == null || this.w)) {
            this.y.onAdColonyAdAttemptFinished(this);
        }
        if (!(a.D || AdColonyBrowser.B)) {
            for (int i = 0; i < a.an.size(); i++) {
                ((Bitmap) a.an.get(i)).recycle();
            }
            a.an.clear();
        }
        a.U = null;
        this.w = true;
        if (!this.D) {
            a.E = true;
        }
        System.gc();
    }

    void a(String str) {
        String rewardName = getRewardName();
        String str2 = BuildConfig.FLAVOR;
        rewardName = (BuildConfig.FLAVOR + getRewardAmount()) + " " + rewardName;
        if (str.equals("Confirmation")) {
            a.S = new ab(rewardName, this);
        } else {
            a.S = new ac(rewardName, this);
        }
    }
}
