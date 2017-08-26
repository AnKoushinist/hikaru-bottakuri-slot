package jp.co.geniee.gnadsdk.rewardvideo;

import android.app.Activity;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class GNSRewardVideoAd {
    private String a;
    private Activity b;
    private GNSVideoMediator c;
    private GNSRewardVideoAdListener d;
    private GNSLogger e = GNSLogger.a();

    public GNSRewardVideoAd(String str, Activity activity) {
        this.a = str;
        this.b = activity;
        GNSEnv.a().a(activity);
        GNSLogger.a().a(activity);
        this.e.a("RewardVideo", "GNAdSDK ver=2.2.3");
        this.e.a("RewardVideo", "zoneId=" + this.a);
        this.c = new GNSVideoMediator(this.b, this.a, GNSPrefUtil.c(this.b));
    }

    public void a(GNSRewardVideoAdListener gNSRewardVideoAdListener) {
        this.d = gNSRewardVideoAdListener;
        this.c.a(gNSRewardVideoAdListener);
    }

    public void a() {
        this.e.a("RewardVideo", "****************************************************");
        this.e.a("RewardVideo", "\u30ed\u30fc\u30c9\u30ea\u30af\u30a8\u30b9\u30c8\u958b\u59cb");
        this.e.a("RewardVideo", "****************************************************");
        this.c.f();
    }

    public boolean b() {
        return !this.c.a().isEmpty();
    }

    public synchronized void c() {
        this.e.a("RewardVideo", "----------------------------------------------------");
        this.e.a("RewardVideo", "\u5e83\u544a\u8868\u793a\u958b\u59cb");
        this.e.a("RewardVideo", "----------------------------------------------------");
        GNSAdaptee gNSAdaptee = null;
        try {
            if (this.c.a) {
                throw new GNSVideoRewardException("Geniee", 10521);
            } else if (!b()) {
                throw new GNSVideoRewardException("Geniee", 10511);
            } else if (d()) {
                this.c.a = true;
                gNSAdaptee = this.c.b();
                if (gNSAdaptee == null) {
                    this.e.a("RewardVideo", "RewardVideoAd: \u5e83\u544a\u306e\u53d6\u5f97\u306b\u5931\u6557\u3002");
                }
                this.e.d("RewardVideo", "[" + this.a + "] playstart: " + gNSAdaptee.getAdnetworkName());
                gNSAdaptee.show(this.c);
                this.c.g();
            } else {
                throw new GNSVideoRewardException("Geniee", 10401);
            }
        } catch (GNSVideoRewardException e) {
            this.d.a(e);
        } catch (Exception e2) {
            this.e.e("RewardVideo", e2.getMessage());
            this.c.a = false;
            if (this.d != null) {
                String str;
                String str2 = "Geniee";
                if (gNSAdaptee != null) {
                    str = gNSAdaptee.getVideoRewardData().a;
                } else {
                    str = str2;
                }
                this.d.a(new GNSVideoRewardException(str, 90001, e2.getMessage()));
            }
        }
        return;
    }

    protected boolean d() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.b.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void e() {
        this.e.d("RewardVideo", "onStart()");
        if (this.c != null) {
            this.c.h();
        } else {
            this.e.c("RewardVideo", "onStart() mMediator instance does not exist.");
        }
    }

    public void f() {
        this.e.d("RewardVideo", "onResume()");
        if (this.c != null) {
            this.c.i();
        } else {
            this.e.c("RewardVideo", "onResume() mMediator instance does not exist.");
        }
    }

    public void g() {
        this.e.d("RewardVideo", "onPause()");
        if (this.c != null) {
            this.c.j();
        } else {
            this.e.c("RewardVideo", "onPause() mMediator instance does not exist.");
        }
    }

    public void h() {
        this.e.d("RewardVideo", "onStop()");
        if (this.c != null) {
            this.c.k();
        } else {
            this.e.c("RewardVideo", "onStop() mMediator instance does not exist.");
        }
    }

    public void i() {
        this.e.d("RewardVideo", "onDestroy()");
        try {
            if (this.c != null) {
                this.c.a((GNSRewardVideoAdListener) null);
                this.c.l();
                this.c = null;
            }
        } catch (Exception e) {
        }
    }
}
