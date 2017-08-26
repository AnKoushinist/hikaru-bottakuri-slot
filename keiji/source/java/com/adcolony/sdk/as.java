package com.adcolony.sdk;

import android.app.ActivityManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import com.tapjoy.TapjoyConstants;
import com.unity3d.ads.metadata.MediationMetaData;
import java.util.Locale;
import jp.co.geniee.gnsrewardadapter.GNSAdapterVungleRewardVideoAd;
import org.cocos2dx.lib.BuildConfig;
import org.json.JSONArray;
import org.json.JSONObject;
import twitter4j.TwitterResponse;

class as {
    String a = BuildConfig.FLAVOR;
    boolean b;
    boolean c;
    boolean d;
    int e = 2;
    String f = BuildConfig.FLAVOR;
    private String g = TapjoyConstants.TJC_DEVICE_PLATFORM_TYPE;
    private String h = "android_native";
    private JSONArray i = bb.b();

    class a extends AsyncTask<Void, Void, JSONObject> {
        o a;
        as b;
        boolean c;
        final /* synthetic */ as d;

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return a((Void[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            a((JSONObject) obj);
        }

        a(as asVar, o oVar, as asVar2, boolean z) {
            this.d = asVar;
            this.a = oVar;
            this.b = asVar2;
            this.c = z;
        }

        protected JSONObject a(Void... voidArr) {
            if (VERSION.SDK_INT < 14) {
                return null;
            }
            return this.d.a(this.b);
        }

        protected void a(JSONObject jSONObject) {
            if (this.c) {
                new o("Device.update_info", 1, jSONObject).a();
            } else {
                this.a.a(jSONObject).a();
            }
        }
    }

    as() {
    }

    String a() {
        if (n.d()) {
            return Secure.getString(n.c().getContentResolver(), TapjoyConstants.TJC_ANDROID_ID);
        }
        return BuildConfig.FLAVOR;
    }

    String b() {
        return this.a;
    }

    String c() {
        return System.getProperty("os.arch").toLowerCase();
    }

    void d() {
        this.b = false;
        n.a("Device.get_info", new q(this) {
            final /* synthetic */ as a;

            {
                this.a = r1;
            }

            public void a(o oVar) {
                if (this.a.n() < 14) {
                    new a(this.a, oVar, this.a, false).execute(new Void[0]);
                } else {
                    new a(this.a, oVar, this.a, false).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
                }
            }
        });
        n.a("Device.application_exists", new q(this) {
            final /* synthetic */ as a;

            {
                this.a = r1;
            }

            public void a(o oVar) {
                JSONObject a = bb.a();
                bb.a(a, "result", ab.a(bb.a(oVar.b(), MediationMetaData.KEY_NAME)));
                bb.a(a, "success", true);
                oVar.a(a).a();
            }
        });
    }

    String e() {
        return o() ? "tablet" : "phone";
    }

    boolean f() {
        return this.c;
    }

    String g() {
        if (!n.d()) {
            return BuildConfig.FLAVOR;
        }
        String networkOperatorName = ((TelephonyManager) n.c().getSystemService("phone")).getNetworkOperatorName();
        if (networkOperatorName.length() == 0) {
            return "unknown";
        }
        return networkOperatorName;
    }

    int h() {
        if (n.d()) {
            return ((ActivityManager) n.c().getApplicationContext().getSystemService("activity")).getMemoryClass();
        }
        return 0;
    }

    long i() {
        Runtime runtime = Runtime.getRuntime();
        return (runtime.totalMemory() - runtime.freeMemory()) / ((long) 1048576);
    }

    float j() {
        if (n.d()) {
            return n.c().getResources().getDisplayMetrics().density;
        }
        return 0.0f;
    }

    String k() {
        if (n.d()) {
            return aj.a(n.c());
        }
        return BuildConfig.FLAVOR;
    }

    int l() {
        if (!n.d()) {
            return 0;
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        n.c().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    int m() {
        if (!n.d()) {
            return 0;
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        n.c().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    int n() {
        return VERSION.SDK_INT;
    }

    boolean o() {
        if (!n.d()) {
            return false;
        }
        DisplayMetrics displayMetrics = n.c().getResources().getDisplayMetrics();
        float f = ((float) displayMetrics.widthPixels) / displayMetrics.xdpi;
        float f2 = ((float) displayMetrics.heightPixels) / displayMetrics.ydpi;
        if (Math.sqrt((double) ((f2 * f2) + (f * f))) >= 6.0d) {
            return true;
        }
        return false;
    }

    String p() {
        return Locale.getDefault().getLanguage();
    }

    String q() {
        return Locale.getDefault().getCountry();
    }

    String r() {
        return BuildConfig.FLAVOR;
    }

    String s() {
        return Build.MANUFACTURER;
    }

    String t() {
        return Build.MODEL;
    }

    String u() {
        return VERSION.RELEASE;
    }

    JSONArray v() {
        return this.i;
    }

    int w() {
        if (!n.d()) {
            return 2;
        }
        switch (n.c().getResources().getConfiguration().orientation) {
            case TwitterResponse.READ /*1*/:
                return 0;
            case TwitterResponse.READ_WRITE /*2*/:
                return 1;
            default:
                return 2;
        }
    }

    String x() {
        return "3.1.2";
    }

    boolean y() {
        if (!n.d()) {
            return false;
        }
        int w = w();
        switch (w) {
            case TwitterResponse.NONE /*0*/:
                if (this.e != 1) {
                    return false;
                }
                bd.d.b((Object) "Sending device info update");
                this.e = w;
                if (n() < 14) {
                    new a(this, null, this, true).execute(new Void[0]);
                } else {
                    new a(this, null, this, true).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
                }
                return true;
            case TwitterResponse.READ /*1*/:
                if (this.e != 0) {
                    return false;
                }
                bd.d.b((Object) "Sending device info update");
                this.e = w;
                if (n() < 14) {
                    new a(this, null, this, true).execute(new Void[0]);
                } else {
                    new a(this, null, this, true).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
                }
                return true;
            default:
                return false;
        }
    }

    JSONObject a(as asVar) {
        JSONObject a = bb.a();
        aq a2 = n.a();
        bb.a(a, TapjoyConstants.TJC_CARRIER_NAME, asVar.g());
        bb.a(a, "data_path", n.a().j().e());
        bb.b(a, "device_api", asVar.n());
        bb.a(a, "device_id", asVar.k());
        bb.b(a, "display_width", asVar.l());
        bb.b(a, "display_height", asVar.m());
        bb.b(a, "screen_width", asVar.l());
        bb.b(a, "screen_height", asVar.m());
        bb.a(a, TapjoyConstants.TJC_DEVICE_TYPE_NAME, asVar.e());
        bb.a(a, "locale_language_code", asVar.p());
        bb.a(a, "ln", asVar.p());
        bb.a(a, "locale_country_code", asVar.q());
        bb.a(a, "locale", asVar.q());
        bb.a(a, TapjoyConstants.TJC_DEVICE_MAC_ADDRESS, asVar.r());
        bb.a(a, "manufacturer", asVar.s());
        bb.a(a, "device_brand", asVar.s());
        bb.a(a, "media_path", n.a().j().d());
        bb.a(a, "temp_storage_path", n.a().j().f());
        bb.b(a, "memory_class", asVar.h());
        bb.b(a, "network_speed", 20);
        bb.a(a, "memory_used_mb", asVar.i());
        bb.a(a, "model", asVar.t());
        bb.a(a, "device_model", asVar.t());
        bb.a(a, TapjoyConstants.TJC_SDK_TYPE, "android_native");
        bb.a(a, "sdk_version", asVar.x());
        bb.a(a, "network_type", a2.b.c());
        bb.a(a, TapjoyConstants.TJC_DEVICE_OS_VERSION_NAME, asVar.u());
        bb.a(a, "os_name", TapjoyConstants.TJC_DEVICE_PLATFORM_TYPE);
        bb.a(a, TapjoyConstants.TJC_PLATFORM, TapjoyConstants.TJC_DEVICE_PLATFORM_TYPE);
        bb.a(a, "arch", asVar.c());
        bb.a(a, "user_id", bb.a(a2.a().d, "user_id"));
        bb.a(a, GNSAdapterVungleRewardVideoAd.APP_ID_COLUMN_NAME, a2.a().a);
        bb.a(a, "immersion", this.d);
        bb.a(a, "app_bundle_name", ab.b());
        this.e = asVar.w();
        bb.b(a, "current_orientation", this.e);
        JSONArray b = bb.b();
        if (ab.a("com.android.vending")) {
            b.put("google");
        }
        if (ab.a("com.amazon.venezia")) {
            b.put("amazon");
        }
        bb.a(a, "available_stores", b);
        this.i = ab.b(n.c());
        bb.a(a, "permissions", this.i);
        int i = 40;
        while (!asVar.b && i > 0) {
            try {
                Thread.sleep(50);
                i--;
            } catch (Exception e) {
            }
        }
        bb.a(a, "advertiser_id", asVar.b());
        bb.a(a, "limit_tracking", asVar.f());
        if (asVar.b() == null || asVar.b().equals(BuildConfig.FLAVOR)) {
            bb.a(a, "android_id_sha1", ab.c(asVar.a()));
        }
        return a;
    }
}
