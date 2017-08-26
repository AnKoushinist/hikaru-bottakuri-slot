package com.adcolony.sdk;

import android.app.Activity;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Looper;
import android.telephony.TelephonyManager;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import jp.co.geniee.gnsrewardadapter.GNSAdapterAdColonyRewardVideoAd;
import org.cocos2dx.lib.BuildConfig;
import org.json.JSONObject;

public class b {
    static ExecutorService a = Executors.newSingleThreadExecutor();

    public static boolean a(Activity activity, String str, String... strArr) {
        return a(activity, null, str, strArr);
    }

    public static boolean a(Activity activity, d dVar, String str, String... strArr) {
        if (t.a(0, null)) {
            bd.e.b((Object) "Cannot configure AdColony; configuration mechanism requires 5 seconds between attempts.");
            return false;
        }
        if (Looper.myLooper() == null) {
            Looper.prepare();
        }
        if (dVar == null) {
            dVar = new d();
        }
        if (n.b() && !bb.c(n.a().a().e(), "reconfigurable")) {
            aq a = n.a();
            if (!a.a().b().equals(str)) {
                bd.e.b((Object) "Ignoring call to AdColony.configure, as the app id does not match what was used during the initial configuration.");
                return false;
            } else if (ab.a(strArr, a.a().c())) {
                bd.e.b((Object) "Ignoring call to AdColony.configure, as the same zone ids were used during the previous configuration.");
                return false;
            }
        }
        dVar.b(str);
        dVar.a(strArr);
        dVar.f();
        a(activity, dVar);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss:SSS");
        long currentTimeMillis = System.currentTimeMillis();
        String format = simpleDateFormat.format(new Date(currentTimeMillis));
        int i = 0;
        boolean z = true;
        while (i < strArr.length) {
            if (!(strArr[i] == null || strArr[i].equals(BuildConfig.FLAVOR))) {
                z = false;
            }
            i++;
        }
        if (str.equals(BuildConfig.FLAVOR) || r2) {
            bd.g.b((Object) "AdColony.configure() called with an empty app or zone id String.");
            return false;
        }
        n.a = true;
        if (VERSION.SDK_INT < 14) {
            bd.e.b((Object) "The minimum API level for the AdColony SDK is 14.");
            n.a(activity, dVar, true);
        } else {
            n.a(activity, dVar, false);
        }
        String str2 = n.a().j().c() + "/adc3/AppInfo";
        JSONObject a2 = bb.a();
        if (new File(str2).exists()) {
            a2 = bb.c(str2);
        }
        JSONObject a3 = bb.a();
        if (bb.a(a2, "appId").equals(str)) {
            bb.a(a3, "zoneIds", bb.a(bb.f(a2, "zoneIds"), strArr, true));
            bb.a(a3, "appId", str);
        } else {
            bb.a(a3, "zoneIds", bb.a(strArr));
            bb.a(a3, "appId", str);
        }
        bb.g(a3, str2);
        bd.f.b("Configure: Total Time (ms): " + (System.currentTimeMillis() - currentTimeMillis) + " and started at " + format);
        return true;
    }

    public static boolean a(l lVar) {
        if (n.e()) {
            n.a().a(lVar);
            return true;
        }
        bd.e.b((Object) "Ignoring call to AdColony.setRewardListener() as AdColony has not yet been configured.");
        return false;
    }

    public static boolean a(String str, h hVar) {
        return a(str, hVar, null);
    }

    public static boolean a(final String str, final h hVar, final c cVar) {
        if (n.e()) {
            Bundle bundle = new Bundle();
            bundle.putString(GNSAdapterAdColonyRewardVideoAd.ZONE_ID_COLUMN_NAME, str);
            if (t.a(1, bundle)) {
                m mVar = (m) n.a().b().get(str);
                if (mVar == null) {
                    mVar = new m(str);
                    bd.b.b("Zone info for " + str + " doesn't exist in hashmap");
                }
                hVar.onRequestNotFilled(mVar);
                return false;
            }
            try {
                a.execute(new Runnable() {
                    public void run() {
                        aq a = n.a();
                        if (a.c() || a.d()) {
                            b.b();
                            b.a(hVar, str);
                        } else if (b.a() || !n.d()) {
                            m mVar = (m) a.b().get(str);
                            if (mVar == null) {
                                mVar = new m(str);
                                bd.b.b("Zone info for " + str + " doesn't exist in hashmap");
                            }
                            if (mVar.a() != 2) {
                                a.h().a(str, hVar, cVar);
                            } else if (n.d()) {
                                n.c().runOnUiThread(new Runnable(this) {
                                    final /* synthetic */ AnonymousClass1 b;

                                    public void run() {
                                        hVar.onRequestNotFilled(mVar);
                                    }
                                });
                            }
                        } else {
                            b.a(hVar, str);
                        }
                    }
                });
                return true;
            } catch (RejectedExecutionException e) {
                a(hVar, str);
                return false;
            }
        }
        bd.e.b((Object) "Ignoring call to AdColony.requestInterstitial as AdColony has not yet been configured.");
        hVar.onRequestNotFilled(new m(str));
        return false;
    }

    static boolean a() {
        a aVar = new a(15.0d);
        aq a = n.a();
        while (!a.t() && !aVar.a()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
        }
        return a.t();
    }

    static boolean a(final h hVar, final String str) {
        if (hVar != null && n.d()) {
            ab.a(new Runnable() {
                public void run() {
                    m mVar = (m) n.a().b().get(str);
                    if (mVar == null) {
                        mVar = new m(str);
                    }
                    hVar.onRequestNotFilled(mVar);
                }
            });
        }
        return false;
    }

    static void a(Activity activity, d dVar) {
        if (dVar != null && activity != null) {
            String a = ab.a(activity);
            HashMap hashMap = new HashMap();
            hashMap.put("sessionId", "unknown");
            hashMap.put("packageName", a);
            hashMap.put("appId", BuildConfig.FLAVOR + dVar.b());
            hashMap.put("zoneIds", dVar.d().toString());
            hashMap.put("controllerVersion", "unknown");
            hashMap.put("apiLevel", Integer.valueOf(VERSION.SDK_INT));
            hashMap.put("manufacturer", Build.MANUFACTURER);
            hashMap.put("model", Build.MODEL);
            hashMap.put("osVersion", VERSION.RELEASE);
            hashMap.put("carrier", ((TelephonyManager) activity.getSystemService("phone")).getNetworkOperatorName());
            hashMap.put("advertisingId", "unknown");
            hashMap.put("locale", Locale.getDefault().getCountry());
            bf.a(hashMap);
        }
    }

    static void b() {
        bd.g.b((Object) "The AdColony API is not available while AdColony is disabled.");
    }
}
