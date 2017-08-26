package com.applovin.impl.sdk;

import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.PowerManager;
import com.applovin.adview.AppLovinAdView;
import com.applovin.impl.adview.AdViewControllerImpl;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdService;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdType;
import com.applovin.sdk.AppLovinAdUpdateListener;
import com.applovin.sdk.AppLovinLogger;
import com.applovin.sdk.AppLovinSdkUtils;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class AppLovinAdServiceImpl implements AppLovinAdService {
    public static String a = "/adservice/no_op";
    public static String b = "/adservice/track_click_now";
    public static String c = "/adservice/skip";
    private final AppLovinSdkImpl d;
    private final AppLovinLogger e;
    private Handler f;
    private final Map g;

    AppLovinAdServiceImpl(AppLovinSdkImpl appLovinSdkImpl) {
        if (appLovinSdkImpl == null) {
            throw new IllegalArgumentException("No sdk specified");
        }
        this.d = appLovinSdkImpl;
        this.e = appLovinSdkImpl.h();
        this.f = new Handler(Looper.getMainLooper());
        this.g = new HashMap(2);
        for (AppLovinAdType put : AppLovinAdType.b()) {
            this.g.put(put, new HashMap());
        }
        ((Map) this.g.get(AppLovinAdType.a)).put(AppLovinAdSize.a, new i(AppLovinAdSize.a));
        ((Map) this.g.get(AppLovinAdType.a)).put(AppLovinAdSize.d, new i(AppLovinAdSize.d));
        ((Map) this.g.get(AppLovinAdType.a)).put(AppLovinAdSize.c, new i(AppLovinAdSize.c));
        ((Map) this.g.get(AppLovinAdType.a)).put(AppLovinAdSize.b, new i(AppLovinAdSize.b));
        ((Map) this.g.get(AppLovinAdType.b)).put(AppLovinAdSize.c, new i(AppLovinAdSize.c));
    }

    private void a(Uri uri, AppLovinAdImpl appLovinAdImpl, AppLovinAdView appLovinAdView, AdViewControllerImpl adViewControllerImpl) {
        adViewControllerImpl.h();
        a((AppLovinAd) appLovinAdImpl);
        AppLovinSdkUtils.a(appLovinAdView.getContext(), uri, this.d);
        adViewControllerImpl.j();
    }

    private void a(AppLovinAdImpl appLovinAdImpl, String str) {
        String a = appLovinAdImpl.a(str);
        if (AppLovinSdkUtils.d(a)) {
            this.d.u().a(a, null);
        }
    }

    private boolean a() {
        return ((PowerManager) this.d.j().getSystemService("power")).isScreenOn();
    }

    private boolean a(AppLovinAdSize appLovinAdSize) {
        return appLovinAdSize == AppLovinAdSize.a ? ((Boolean) this.d.a(cb.t)).booleanValue() : appLovinAdSize == AppLovinAdSize.d ? ((Boolean) this.d.a(cb.v)).booleanValue() : appLovinAdSize == AppLovinAdSize.b ? ((Boolean) this.d.a(cb.x)).booleanValue() : false;
    }

    private boolean a(AppLovinAdSize appLovinAdSize, AppLovinAdType appLovinAdType) {
        return !((Boolean) this.d.a(cb.A)).booleanValue() ? false : !b(appLovinAdSize, appLovinAdType) ? false : appLovinAdType.equals(AppLovinAdType.b) ? ((Boolean) this.d.a(cb.av)).booleanValue() : appLovinAdSize.equals(AppLovinAdSize.c) ? ((Boolean) this.d.a(cb.aw)).booleanValue() : false;
    }

    private long b(AppLovinAdSize appLovinAdSize) {
        return appLovinAdSize == AppLovinAdSize.a ? ((Long) this.d.a(cb.u)).longValue() : appLovinAdSize == AppLovinAdSize.d ? ((Long) this.d.a(cb.w)).longValue() : appLovinAdSize == AppLovinAdSize.b ? ((Long) this.d.a(cb.y)).longValue() : 0;
    }

    private void b(AppLovinAdSize appLovinAdSize, AppLovinAdType appLovinAdType, AppLovinAdLoadListener appLovinAdLoadListener) {
        c cVar = new c(appLovinAdSize, appLovinAdType);
        AppLovinAd appLovinAd = (AppLovinAd) this.d.p().b(cVar);
        if (appLovinAd != null) {
            this.e.a("AppLovinAdService", "Using pre-loaded ad: " + appLovinAd + " for size " + appLovinAdSize + " and type " + appLovinAdType);
            appLovinAdLoadListener.adReceived(appLovinAd);
        } else {
            this.d.m().a(new cr(appLovinAdSize, appLovinAdType, appLovinAdLoadListener, this.d), cw.MAIN);
        }
        this.d.p().e(cVar);
    }

    private boolean b(AppLovinAdSize appLovinAdSize, AppLovinAdType appLovinAdType) {
        try {
            return appLovinAdType.equals(AppLovinAdType.b) ? ((Boolean) this.d.a(cb.E)).booleanValue() : ((String) this.d.a(cb.D)).toUpperCase(Locale.ENGLISH).contains(appLovinAdSize.c());
        } catch (Throwable e) {
            this.d.h().b("AppLovinAdService", "Unable to safely test preload merge capability", e);
            return false;
        }
    }

    private void c(AppLovinAdSize appLovinAdSize) {
        long b = b(appLovinAdSize);
        if (b > 0) {
            this.d.m().a(new j(this, appLovinAdSize), cw.MAIN, (b + 2) * 1000);
        }
    }

    public void a(AppLovinAd appLovinAd) {
        if (appLovinAd == null) {
            throw new IllegalArgumentException("No ad specified");
        }
        AppLovinAdImpl appLovinAdImpl = (AppLovinAdImpl) appLovinAd;
        i iVar = (i) ((Map) this.g.get(appLovinAdImpl.c())).get(appLovinAdImpl.b());
        synchronized (iVar.b) {
            iVar.c = null;
            iVar.d = 0;
        }
    }

    public void a(AppLovinAd appLovinAd, String str, AppLovinAdView appLovinAdView, Uri uri) {
        a((AppLovinAdImpl) appLovinAd, str);
        AppLovinSdkUtils.a(appLovinAdView.getContext(), uri, this.d);
    }

    public void a(AppLovinAd appLovinAd, String str, AppLovinAdView appLovinAdView, AdViewControllerImpl adViewControllerImpl, Uri uri) {
        AppLovinAdImpl appLovinAdImpl = (AppLovinAdImpl) appLovinAd;
        a(appLovinAdImpl, str);
        a(uri, appLovinAdImpl, appLovinAdView, adViewControllerImpl);
    }

    public void a(AppLovinAdSize appLovinAdSize, AppLovinAdLoadListener appLovinAdLoadListener) {
        a(appLovinAdSize, AppLovinAdType.a, appLovinAdLoadListener);
    }

    public void a(AppLovinAdSize appLovinAdSize, AppLovinAdType appLovinAdType, AppLovinAdLoadListener appLovinAdLoadListener) {
        Object obj = 1;
        if (appLovinAdSize == null) {
            throw new IllegalArgumentException("No ad size specified");
        } else if (appLovinAdLoadListener == null) {
            throw new IllegalArgumentException("No callback specified");
        } else if (appLovinAdType == null) {
            throw new IllegalArgumentException("No ad type specificed");
        } else {
            AppLovinAd appLovinAd;
            this.d.h().a("AppLovinAdService", "Loading next ad of size " + appLovinAdSize.c() + " and type " + appLovinAdType.a());
            if (appLovinAdSize.equals(AppLovinAdSize.a) || appLovinAdSize.equals(AppLovinAdSize.d) || appLovinAdSize.equals(AppLovinAdSize.b)) {
                this.d.h().e("AppLovinAdService", "Banners, MRecs and Leaderboards are deprecated and will be removed in a future SDK version!");
            }
            i iVar = (i) ((Map) this.g.get(appLovinAdType)).get(appLovinAdSize);
            synchronized (iVar.b) {
                if (System.currentTimeMillis() <= iVar.d) {
                    obj = null;
                }
                if (iVar.c == null || r2 != null) {
                    this.e.a("AppLovinAdService", "Loading next ad...");
                    iVar.g.add(appLovinAdLoadListener);
                    if (!iVar.e) {
                        iVar.e = true;
                        obj = new h(this, (i) ((Map) this.g.get(appLovinAdType)).get(appLovinAdSize), null);
                        if (!a(appLovinAdSize, appLovinAdType)) {
                            this.e.a("AppLovinAdService", "Task merge not necessary.");
                            b(appLovinAdSize, appLovinAdType, obj);
                        } else if (this.d.p().a(new c(appLovinAdSize, appLovinAdType), obj)) {
                            this.e.a("AppLovinAdService", "Attaching load listener to initial preload task...");
                            appLovinAd = null;
                        } else {
                            this.e.a("AppLovinAdService", "Skipped attach of initial preload callback.");
                            b(appLovinAdSize, appLovinAdType, obj);
                            appLovinAd = null;
                        }
                    }
                    appLovinAd = null;
                } else {
                    appLovinAd = iVar.c;
                }
            }
            if (appLovinAd != null) {
                appLovinAdLoadListener.adReceived(appLovinAd);
            }
        }
    }

    public void a(AppLovinAdUpdateListener appLovinAdUpdateListener, AppLovinAdSize appLovinAdSize) {
        if (appLovinAdUpdateListener != null) {
            i iVar = (i) ((Map) this.g.get(AppLovinAdType.a)).get(appLovinAdSize);
            synchronized (iVar.b) {
                iVar.f.remove(appLovinAdUpdateListener);
            }
            this.e.a("AppLovinAdService", "Removed update listener: " + appLovinAdUpdateListener);
        }
    }

    public void b(AppLovinAd appLovinAd, String str, AppLovinAdView appLovinAdView, AdViewControllerImpl adViewControllerImpl, Uri uri) {
        if (appLovinAd == null) {
            throw new IllegalArgumentException("No ad specified");
        }
        AppLovinAdImpl appLovinAdImpl = (AppLovinAdImpl) appLovinAd;
        this.e.a("AppLovinAdService", "Tracking foreground click on an ad...");
        int intValue = ((Integer) this.d.a(cb.bj)).intValue();
        int intValue2 = ((Integer) this.d.a(cb.bk)).intValue();
        int intValue3 = ((Integer) this.d.a(cb.bl)).intValue();
        this.d.r().a(((AppLovinAdImpl) appLovinAd).a(str), null, intValue, intValue2, intValue3, new e(this, adViewControllerImpl, uri, appLovinAdImpl, appLovinAdView));
    }

    public void b(AppLovinAdUpdateListener appLovinAdUpdateListener, AppLovinAdSize appLovinAdSize) {
        if (appLovinAdUpdateListener == null) {
            throw new IllegalArgumentException("No ad listener specified");
        }
        Object obj;
        i iVar = (i) ((Map) this.g.get(AppLovinAdType.a)).get(appLovinAdSize);
        synchronized (iVar.b) {
            if (iVar.f.contains(appLovinAdUpdateListener)) {
                obj = null;
            } else {
                iVar.f.add(appLovinAdUpdateListener);
                obj = 1;
                this.e.a("AppLovinAdService", "Added update listener: " + appLovinAdUpdateListener);
            }
        }
        if (obj != null) {
            this.d.m().a(new j(this, appLovinAdSize), cw.MAIN);
        }
    }
}
