package com.tapjoy.internal;

import android.app.Activity;
import java.io.IOException;
import java.util.Locale;
import java.util.Map;

public final class fa {
    public static void a(boolean z) {
        Object obj;
        fm a = fm.a();
        if (fj.a != z) {
            fj.a = z;
            if (z) {
                fj.a("The debug mode has been enabled");
            } else {
                fj.a("The debug mode has been disabled");
            }
            obj = 1;
        } else {
            obj = null;
        }
        if (obj != null && z && a.k) {
            a.i.a();
        }
    }

    public static void a(Activity activity) {
        fm a = fm.a();
        if (fj.a((Object) activity, "onActivityStart: The given activity was null")) {
            fj.c("onActivityStart");
            d.a(activity.getApplication());
            d.a(activity);
            if (a.b("onActivityStart") && a.e()) {
                fg.b(activity);
            }
        }
    }

    public static void b(Activity activity) {
        fm a = fm.a();
        if (fj.a((Object) activity, "onActivityStop: The given activity was null")) {
            fj.c("onActivityStop");
            d.b(activity);
            if (a.b("onActivityStop") && !d.b()) {
                a.h.a();
            }
        }
    }

    public static void a() {
        fm a = fm.a();
        if (a.b("startSession") && a.e()) {
            fg.b(null);
        }
    }

    public static void b() {
        fm a = fm.a();
        if (a.b("endSession")) {
            a.h.a();
        }
    }

    public static void a(String str, String str2, String str3, String str4, long j) {
        fm a = fm.a();
        if (a.c("trackEvent") && fj.a((Object) str2, "trackEvent: name was null")) {
            Map map = null;
            if (j != 0) {
                map = cv.b();
                map.put("value", Long.valueOf(j));
            }
            a.g.a(str, str2, str3, str4, map);
            fj.a("trackEvent category:{}, name:{}, p1:{}, p2:{}, values:{} called", str, str2, str3, str4, map);
        }
    }

    public static void a(String str, String str2, String str3, String str4, String str5, long j, String str6, long j2, String str7, long j3) {
        fm a = fm.a();
        if (a.c("trackEvent") && fj.a((Object) str2, "trackEvent: name was null")) {
            Map b = cv.b();
            if (!(str5 == null || j == 0)) {
                b.put(str5, Long.valueOf(j));
            }
            if (!(str6 == null || j2 == 0)) {
                b.put(str6, Long.valueOf(j2));
            }
            if (!(str7 == null || j3 == 0)) {
                b.put(str7, Long.valueOf(j3));
            }
            if (b.isEmpty()) {
                b = null;
            }
            a.g.a(str, str2, str3, str4, b);
            fj.a("trackEvent category:{}, name:{}, p1:{}, p2:{}, values:{} called", str, str2, str3, str4, b);
        }
    }

    public static void a(String str, String str2, String str3, String str4) {
        fm a = fm.a();
        if (a.b("trackPurchase")) {
            try {
                g gVar = new g(str);
                String b = fh.b(gVar.a);
                String b2 = fh.b(gVar.f);
                if (b == null || b2 == null) {
                    fj.a("trackPurchase", "skuDetails", "insufficient fields");
                } else if (b2.length() != 3) {
                    fj.a("trackPurchase", "skuDetails", "invalid currency code");
                } else {
                    String b3 = fh.b(str2);
                    String b4 = fh.b(str3);
                    if (b3 != null) {
                        if (b4 != null) {
                            try {
                                h hVar = new h(b3);
                                if (cr.c(hVar.a) || cr.c(hVar.b) || cr.c(hVar.c) || hVar.d == 0) {
                                    fj.a("trackPurchase", "purchaseData", "insufficient fields");
                                }
                            } catch (IOException e) {
                                fj.a("trackPurchase", "purchaseData", "invalid PurchaseData JSON");
                            }
                        } else {
                            fj.a("trackPurchase", "dataSignature", "is null, skipping purchase validation");
                        }
                    } else if (b4 != null) {
                        fj.a("trackPurchase", "purchaseData", "is null. skipping purchase validation");
                    }
                    a.g.a(b, b2.toUpperCase(Locale.US), ((double) gVar.g) / 1000000.0d, b3, b4, fh.b(str4));
                    if (b3 == null || b4 == null) {
                        fj.a("trackPurchase without purchaseData called");
                    } else {
                        fj.a("trackPurchase with purchaseData called");
                    }
                }
            } catch (IOException e2) {
                fj.a("trackPurchase", "skuDetails", "invalid SkuDetails JSON");
            }
        }
    }
}
