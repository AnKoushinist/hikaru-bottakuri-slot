package com.glossomads;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Handler;
import com.glossomads.Listener.GlossomAdsAdAvailabilityListener;
import com.glossomads.Listener.GlossomAdsAdListener;
import com.glossomads.Listener.GlossomAdsAdRewardListener;
import com.glossomads.Model.GlossomAdsAdReward;
import com.glossomads.View.GlossomAdsFullScreen;
import com.glossomads.View.SugarAdView;
import com.glossomads.View.t;
import com.glossomads.d.d;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import org.cocos2dx.lib.BuildConfig;

public class s {
    private static s g = null;
    private static boolean h = false;
    private static boolean i = false;
    private static int j = 5000;
    private static int k = 2;
    private static Activity l;
    private static Handler m = new Handler();
    List<GlossomAdsAdAvailabilityListener> a = new ArrayList();
    GlossomAdsAdRewardListener b;
    private String c;
    private boolean d;
    private boolean e;
    private ConcurrentHashMap<String, b> f = new ConcurrentHashMap();

    public enum a {
        NONE,
        SUCCESS,
        FAILURE
    }

    public enum b {
        WAIT,
        READY
    }

    private s() {
    }

    public static s a() {
        if (g == null) {
            g = new s();
            g.d = false;
            g.e = true;
        }
        return g;
    }

    public boolean b() {
        return this.d;
    }

    public boolean c() {
        return this.e;
    }

    public Activity d() {
        return l;
    }

    public void a(Activity activity) {
        l = activity;
    }

    public static Context e() {
        if (l != null) {
            return l;
        }
        return null;
    }

    public static Context f() {
        if (l != null) {
            return l.getApplicationContext();
        }
        return null;
    }

    public void a(GlossomAdsAdAvailabilityListener glossomAdsAdAvailabilityListener) {
        if (glossomAdsAdAvailabilityListener != null && !this.a.contains(glossomAdsAdAvailabilityListener)) {
            if (this.f != null) {
                this.f.clear();
            }
            this.a.add(glossomAdsAdAvailabilityListener);
        }
    }

    public void b(GlossomAdsAdAvailabilityListener glossomAdsAdAvailabilityListener) {
        if (glossomAdsAdAvailabilityListener != null) {
            this.a.remove(glossomAdsAdAvailabilityListener);
        }
    }

    public void a(GlossomAdsAdRewardListener glossomAdsAdRewardListener) {
        this.b = glossomAdsAdRewardListener;
    }

    public void a(Activity activity, String str, String str2, String... strArr) {
        b(activity, str, str2, strArr);
    }

    public void b(Activity activity, String str, String str2, String... strArr) {
        this.c = str2;
        if (16 > VERSION.SDK_INT) {
            com.glossomads.Logger.a.e(com.glossomads.Logger.a.a.minSdkVersionError, new String[0]);
        } else if (i) {
            com.glossomads.Logger.a.e(com.glossomads.Logger.a.a.configureOverlapError, new String[0]);
        } else if (activity == null) {
            com.glossomads.Logger.a.e(com.glossomads.Logger.a.a.configureActivityIsNull, new String[0]);
        } else if (m.E()) {
            com.glossomads.Logger.a.e(com.glossomads.Logger.a.a.configureKindleFire, new String[0]);
        } else {
            a(activity);
            if (!o()) {
                g(str);
                List<String> formattedList = SugarUtil.formattedList(strArr);
                if (formattedList.size() >= 10) {
                    com.glossomads.Logger.a.e(com.glossomads.Logger.a.a.configureLimitZoneIdError, new String[0]);
                    return;
                }
                Boolean bool;
                List arrayList = new ArrayList();
                Boolean valueOf = Boolean.valueOf(false);
                if (formattedList.size() == 0) {
                    com.glossomads.Logger.a.e(com.glossomads.Logger.a.a.configureEmptyZoneIdError, new String[0]);
                    bool = valueOf;
                } else {
                    for (String str3 : formattedList) {
                        if (arrayList.contains(str3)) {
                            com.glossomads.Logger.a.e(com.glossomads.Logger.a.a.configureDuplicateZoneIdError, str3);
                        } else if (str3.length() >= 100) {
                            com.glossomads.Logger.a.e(com.glossomads.Logger.a.a.configureZoneIdLengthError, str3);
                            bool = Boolean.valueOf(true);
                            break;
                        } else {
                            arrayList.add(str3);
                        }
                    }
                    bool = valueOf;
                }
                if (!bool.booleanValue() && arrayList.size() != 0) {
                    com.glossomads.Logger.a.f(com.glossomads.Logger.a.a.sugarLibraryVersion, m.w(), arrayList.toString());
                    g.a().b();
                    m.c();
                    d.b();
                    v.b();
                    v.a(arrayList);
                    o.a().b();
                    i = true;
                }
            }
        }
    }

    private void g(String str) {
        if (SugarUtil.isNotEmptyValue(str)) {
            for (String split : str.split(",")) {
                String[] split2 = split.split(":");
                if (split2.length == 2) {
                    Object obj = split2[0];
                    String str2 = split2[1];
                    if ("adfully_ver".equals(obj)) {
                        m.a(str2);
                    } else if ("app_category".equals(obj)) {
                        m.c(str2);
                    } else if ("app_name".equals(obj)) {
                        m.d(str2);
                    } else {
                        com.glossomads.Logger.a.e(com.glossomads.Logger.a.a.configureOptionFormatError, obj);
                    }
                } else {
                    com.glossomads.Logger.a.e(com.glossomads.Logger.a.a.configureOptionFormatError, split);
                }
            }
        }
    }

    public boolean a(String str, GlossomAdsAdListener glossomAdsAdListener) {
        if (!e.c()) {
            com.glossomads.Logger.a.b(str, "interstitial");
            return false;
        } else if (!v.d(str)) {
            return false;
        } else {
            com.glossomads.Model.a b = v.b(str, "interstitial");
            if (b == null) {
                return false;
            }
            e.a(b.l(), glossomAdsAdListener);
            a(str, b);
            return true;
        }
    }

    public boolean b(String str, GlossomAdsAdListener glossomAdsAdListener) {
        if (!e.c()) {
            com.glossomads.Logger.a.b(str, "v4vc");
            return false;
        } else if (!v.c(str)) {
            return false;
        } else {
            com.glossomads.Model.a b = v.b(str, "v4vc");
            if (b == null) {
                return false;
            }
            e.a(b.l(), glossomAdsAdListener);
            a(str, b);
            return true;
        }
    }

    private void a(String str, com.glossomads.Model.a aVar) {
        e.a().b();
        Serializable tVar = new t(str, aVar, com.glossomads.View.t.a.FULL_SCREEN);
        Context d = a().d();
        Intent intent = new Intent(d, GlossomAdsFullScreen.class);
        intent.putExtra("sugarHolder", tVar);
        d.startActivity(intent);
    }

    public SugarAdView a(Activity activity, String str, int i, int i2) {
        if (activity != null) {
            GlossomAdsAdListener glossomAdsAdListener = (GlossomAdsAdListener) activity;
        } else {
            Object obj = null;
        }
        if (!v.e(str)) {
            return null;
        }
        com.glossomads.Model.a b = v.b(str, "feed");
        if (b == null) {
            return null;
        }
        e.a(b.l(), glossomAdsAdListener);
        t tVar = new t(str, b, com.glossomads.View.t.a.NATIVE_SCREEN);
        tVar.a(i);
        tVar.b(i2);
        SugarAdView sugarAdView = new SugarAdView(tVar);
        sugarAdView.prepare();
        e.a(sugarAdView);
        return sugarAdView;
    }

    public GlossomAdsAdRewardListener g() {
        return this.b;
    }

    public void a(String str) {
        if (SugarUtil.isEmptyValue(str)) {
            com.glossomads.Logger.a.e(com.glossomads.Logger.a.a.setCustomIdFailed, "null");
        } else if (str.length() > 100) {
            com.glossomads.Logger.a.e(com.glossomads.Logger.a.a.setCustomIdFailed, "too long");
        } else {
            m.b(str);
        }
    }

    public String h() {
        return m.y();
    }

    public boolean i() {
        if (this.b != null) {
            return true;
        }
        return false;
    }

    public void j() {
    }

    public void k() {
    }

    public void a(String str, boolean z) {
        if (this.a != null) {
            a c = c(str);
            Object obj = z ? a.SUCCESS : a.FAILURE;
            Collection h = v.h(str);
            String obj2 = SugarUtil.isEmptyCollection(h) ? BuildConfig.FLAVOR : h.toString();
            if (c.equals(a.NONE) || !c.equals(obj)) {
                if (z) {
                    e(str);
                } else {
                    d(str);
                }
                for (GlossomAdsAdAvailabilityListener onGlossomAdsAdAvailabilityChange : this.a) {
                    onGlossomAdsAdAvailabilityChange.onGlossomAdsAdAvailabilityChange(str, z);
                }
                if (z) {
                    com.glossomads.Logger.a.c(com.glossomads.Logger.a.a.zoneReady, str, obj2);
                } else {
                    com.glossomads.Logger.a.c(com.glossomads.Logger.a.a.zoneNotReady, str, obj2);
                }
            }
        }
    }

    public boolean b(String str) {
        if (i) {
            return v.f(str);
        }
        return false;
    }

    public boolean a(String str, String str2) {
        if (i) {
            return v.a(str, str2);
        }
        return false;
    }

    public a c(String str) {
        if (this.f.isEmpty() || this.f.get(str) == null) {
            return a.NONE;
        }
        if (this.f.get(str) == b.READY) {
            return a.SUCCESS;
        }
        return a.FAILURE;
    }

    public void d(String str) {
        this.f.put(str, b.WAIT);
    }

    public void e(String str) {
        this.f.put(str, b.READY);
    }

    public void l() {
        a(true);
    }

    public void m() {
        a(false);
    }

    public void a(boolean z) {
        v.a(z);
        o.a(z);
        e.c(z);
    }

    public void a(com.glossomads.d.b.b bVar) {
        e.a(bVar);
    }

    public String n() {
        return this.c;
    }

    public boolean o() {
        Activity d = d();
        if (d == null) {
            return false;
        }
        try {
            d.c(d.getApplicationContext());
            return false;
        } catch (Exception e) {
            com.glossomads.Logger.a.e(com.glossomads.Logger.a.a.configurePermissionError, "ACCESS_NETWORK_STATE");
            return true;
        }
    }

    public void p() {
        if (i) {
            h = false;
            e.g();
            o.a().e();
            v.k();
        }
    }

    public void q() {
        if (i) {
            h = true;
            e.f();
            o.a().d();
            v.j();
        }
    }

    public void b(Activity activity) {
        a(activity);
        if (m.b(activity)) {
            e.g();
        }
    }

    public void c(Activity activity) {
        if (m.b(activity)) {
            e.f();
        }
    }

    public boolean r() {
        return i;
    }

    public void a(GlossomAdsAdReward glossomAdsAdReward) {
        if (glossomAdsAdReward != null && i()) {
            a().g().onGlossomAdsAdReward(glossomAdsAdReward);
        }
        Activity d = d();
        if (d != null && (d instanceof com.glossomads.View.b)) {
            d().finish();
        }
    }

    public void f(String str) {
        if (!SugarUtil.isEmptyValue(str)) {
            if (i) {
                com.glossomads.Logger.a.d(com.glossomads.Logger.a.a.setTestDeviceWarning, m.a(), str);
            }
            m.e(str);
        }
    }
}
