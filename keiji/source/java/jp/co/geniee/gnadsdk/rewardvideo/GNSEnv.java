package jp.co.geniee.gnadsdk.rewardvideo;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;

public class GNSEnv {
    private static GNSEnv a = null;
    private boolean b = false;
    private String c;
    private boolean d = false;
    private String e;

    public static synchronized GNSEnv a() {
        GNSEnv gNSEnv;
        synchronized (GNSEnv.class) {
            if (a == null) {
                a = new GNSEnv();
            }
            gNSEnv = a;
        }
        return gNSEnv;
    }

    public GNSEnv a(Activity activity) {
        Context applicationContext = activity.getApplicationContext();
        if (applicationContext != null) {
            try {
                GNSLogger a = GNSLogger.a();
                a.d("Env", "GNSEnv setManifestMetaData()");
                ApplicationInfo applicationInfo = applicationContext.getPackageManager().getApplicationInfo(applicationContext.getPackageName(), 128);
                if (applicationInfo.metaData != null) {
                    a.d("Env", "GNSEnv setManifestMetaData() metaData exists");
                    if (applicationInfo.metaData.get("jp.co.geniee.gnadsdk.rewardvideo.gnsenv.test_mode") != null) {
                        a(applicationInfo.metaData.getBoolean("jp.co.geniee.gnadsdk.rewardvideo.gnsenv.test_mode", false));
                        a.d("Env", "gnsenv.test_mode=" + b());
                    }
                    if (applicationInfo.metaData.get("jp.co.geniee.gnadsdk.rewardvideo.gnsenv.test_sdk_base_url") != null) {
                        a(applicationInfo.metaData.getString("jp.co.geniee.gnadsdk.rewardvideo.gnsenv.test_sdk_base_url"));
                        a.d("Env", "gnsenv.test_sdk_base_url=" + c());
                    }
                    if (applicationInfo.metaData.get("jp.co.geniee.gnadsdk.rewardvideo.gnsenv.adnw_test_mode") != null) {
                        b(applicationInfo.metaData.getBoolean("jp.co.geniee.gnadsdk.rewardvideo.gnsenv.adnw_test_mode", false));
                        a.d("Env", "gnsenv.adnw_test_mode=" + d());
                    }
                    if (applicationInfo.metaData.get("jp.co.geniee.gnadsdk.rewardvideo.gnsenv.connection_type") != null) {
                        b(applicationInfo.metaData.getString("jp.co.geniee.gnadsdk.rewardvideo.gnsenv.connection_type"));
                        a.d("Env", "gnsenv.connection_type=" + e());
                    }
                }
            } catch (NameNotFoundException e) {
                e.printStackTrace();
            }
        }
        return this;
    }

    public boolean b() {
        return this.b;
    }

    public GNSEnv a(boolean z) {
        this.b = z;
        return this;
    }

    public String c() {
        return this.c;
    }

    public GNSEnv a(String str) {
        if (str != null && str.length() > 0) {
            this.c = str;
        }
        return this;
    }

    public boolean d() {
        return this.d;
    }

    public GNSEnv b(boolean z) {
        this.d = z;
        return this;
    }

    public String e() {
        return this.e;
    }

    public GNSEnv b(String str) {
        if (str != null && str.length() > 0) {
            this.e = str;
        }
        return this;
    }
}
