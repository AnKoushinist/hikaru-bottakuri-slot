package com.adcolony.sdk;

import android.content.Intent;
import com.moat.analytics.mobile.tjy.MoatAdEvent;
import jp.co.geniee.gnsrewardadapter.GNSAdapterAdColonyRewardVideoAd;
import org.json.JSONObject;

public class g {
    private h a;
    private al b;
    private c c;
    private int d;
    private String e;
    private int f;
    private String g;
    private boolean h;
    private boolean i;

    g(String str, h hVar, String str2) {
        this.a = hVar;
        this.g = str2;
        this.e = str;
    }

    public boolean a() {
        if (!n.b()) {
            return false;
        }
        aq a = n.a();
        if (this.i) {
            bd.e.b((Object) "This ad object has already been shown. Please request a new ad via AdColony.requestInterstitial.");
            return false;
        } else if (this.h) {
            bd.e.b((Object) "This ad object has expired. Please request a new ad via AdColony.requestInterstitial.");
            return false;
        } else if (a.q()) {
            bd.e.b((Object) "Can not show ad while an interstitial is already active.");
            return false;
        } else if (a((m) a.b().get(this.g))) {
            bd.d.b((Object) "Skipping show()");
            return false;
        } else {
            JSONObject a2 = bb.a();
            bb.a(a2, GNSAdapterAdColonyRewardVideoAd.ZONE_ID_COLUMN_NAME, this.g);
            bb.b(a2, MoatAdEvent.EVENT_TYPE, 0);
            bb.a(a2, "id", this.e);
            if (this.c != null) {
                bb.a(a2, "pre_popup", this.c.a);
                bb.a(a2, "post_popup", this.c.b);
            }
            m mVar = (m) a.b().get(this.g);
            if (mVar != null && mVar.b() && a.e() == null) {
                bd.e.b((Object) "Rewarded ad: show() called with no reward listener set.");
            }
            new o("AdSession.launch_ad_unit", 1, a2).a();
            return true;
        }
    }

    boolean a(m mVar) {
        if (mVar == null) {
            return true;
        }
        if (mVar.c() <= 1) {
            return false;
        }
        if (mVar.d() == 0) {
            mVar.a(mVar.c() - 1);
            return false;
        }
        mVar.a(mVar.d() - 1);
        return true;
    }

    public h b() {
        return this.a;
    }

    public void a(h hVar) {
        this.a = hVar;
    }

    public String c() {
        return this.g;
    }

    boolean d() {
        if (!n.d() || !n.b()) {
            return false;
        }
        n.a().c(true);
        n.a().a(this.b);
        n.a().a(this);
        bd.b.b((Object) "Launching fullscreen Activity via AdColonyInterstitial's launch method.");
        n.c().startActivity(new Intent(n.c(), AdColonyInterstitialActivity.class));
        this.i = true;
        return true;
    }

    void a(al alVar) {
        this.b = alVar;
    }

    void a(boolean z) {
        this.h = z;
    }

    void a(c cVar) {
        this.c = cVar;
    }

    void a(int i) {
        this.f = i;
    }

    void b(int i) {
        this.d = i;
    }

    al e() {
        return this.b;
    }

    int f() {
        return this.d;
    }

    String g() {
        return this.e;
    }
}
