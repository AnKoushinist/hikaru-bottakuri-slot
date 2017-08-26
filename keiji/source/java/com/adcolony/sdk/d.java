package com.adcolony.sdk;

import jp.co.geniee.gnsrewardadapter.GNSAdapterVungleRewardVideoAd;
import org.cocos2dx.lib.BuildConfig;
import org.json.JSONArray;
import org.json.JSONObject;

public class d {
    String a = BuildConfig.FLAVOR;
    String[] b;
    JSONArray c = bb.b();
    JSONObject d = bb.a();

    public d() {
        a("google");
        if (n.b()) {
            aq a = n.a();
            b(a.a().a);
            a(a.a().b);
        }
    }

    public d a(String str, String str2) {
        if (str != null && ab.d(str) && ab.d(str2)) {
            bb.a(this.d, str, str2);
        }
        return this;
    }

    public d a(String str) {
        if (ab.d(str)) {
            a("origin_store", str);
        }
        return this;
    }

    public boolean a() {
        return bb.c(this.d, "multi_window_enabled");
    }

    d b(String str) {
        if (str != null) {
            this.a = str;
            bb.a(this.d, GNSAdapterVungleRewardVideoAd.APP_ID_COLUMN_NAME, str);
        }
        return this;
    }

    d a(String... strArr) {
        if (strArr != null) {
            this.b = strArr;
            this.c = bb.b();
            for (String a : strArr) {
                bb.a(this.c, a);
            }
        }
        return this;
    }

    String b() {
        return this.a;
    }

    String[] c() {
        return this.b;
    }

    JSONArray d() {
        return this.c;
    }

    JSONObject e() {
        return this.d;
    }

    void f() {
        if (bb.h(this.d, "use_forced_controller")) {
            ADCVMModule.a = bb.c(this.d, "use_forced_controller");
        }
        if (bb.h(this.d, "use_staging_launch_server")) {
            n.a().b(bb.c(this.d, "use_staging_launch_server") ? "https://adc3-launch-server-staging.herokuapp.com/v4/launch" : "https://adc3-launch.adcolony.com/v4/launch");
        }
    }
}
