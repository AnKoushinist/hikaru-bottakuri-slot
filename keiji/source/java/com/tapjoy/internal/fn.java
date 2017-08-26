package com.tapjoy.internal;

import android.app.Activity;
import java.io.IOException;
import java.util.Locale;
import java.util.Map;

public final class fn {
    public static void a(boolean z) {
        Object obj;
        fz a = fz.a();
        if (fw.a != z) {
            fw.a = z;
            if (z) {
                fw.a("The debug mode has been enabled");
            } else {
                fw.a("The debug mode has been disabled");
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
        fz a = fz.a();
        if (fw.a((Object) activity, "onActivityStart: The given activity was null")) {
            fw.c("onActivityStart");
            d.a(activity.getApplication());
            d.b(activity);
            if (a.b("onActivityStart") && a.e()) {
                ft.b(activity);
            }
        }
    }

    public static void b(Activity activity) {
        fz a = fz.a();
        if (fw.a((Object) activity, "onActivityStop: The given activity was null")) {
            fw.c("onActivityStop");
            d.c(activity);
            if (a.b("onActivityStop") && !d.b()) {
                a.h.a();
            }
        }
    }

    public static void a() {
        fz a = fz.a();
        if (a.b("startSession") && a.e()) {
            ft.b(null);
        }
    }

    public static void b() {
        fz a = fz.a();
        if (a.b("endSession")) {
            a.h.a();
        }
    }

    public static void a(String str, String str2, String str3, String str4, long j) {
        fz a = fz.a();
        if (a.c("trackEvent") && fw.a((Object) str2, "trackEvent: name was null")) {
            Map map = null;
            if (j != 0) {
                map = cx.b();
                map.put("value", Long.valueOf(j));
            }
            a.g.a(str, str2, str3, str4, map);
            fw.a("trackEvent category:{}, name:{}, p1:{}, p2:{}, values:{} called", str, str2, str3, str4, map);
        }
    }

    public static void a(String str, String str2, String str3, String str4, String str5, long j, String str6, long j2, String str7, long j3) {
        fz a = fz.a();
        if (a.c("trackEvent") && fw.a((Object) str2, "trackEvent: name was null")) {
            Map b = cx.b();
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
            fw.a("trackEvent category:{}, name:{}, p1:{}, p2:{}, values:{} called", str, str2, str3, str4, b);
        }
    }

    public static void a(String str, String str2, String str3, String str4) {
        fz a = fz.a();
        if (a.b("trackPurchase")) {
            try {
                g gVar = new g(str);
                String b = fu.b(gVar.a);
                String b2 = fu.b(gVar.f);
                if (b == null || b2 == null) {
                    fw.a("trackPurchase", "skuDetails", "insufficient fields");
                } else if (b2.length() != 3) {
                    fw.a("trackPurchase", "skuDetails", "invalid currency code");
                } else {
                    String b3 = fu.b(str2);
                    String b4 = fu.b(str3);
                    if (b3 != null) {
                        if (b4 != null) {
                            try {
                                h hVar = new h(b3);
                                if (ct.c(hVar.a) || ct.c(hVar.b) || ct.c(hVar.c) || hVar.d == 0) {
                                    fw.a("trackPurchase", "purchaseData", "insufficient fields");
                                }
                            } catch (IOException e) {
                                fw.a("trackPurchase", "purchaseData", "invalid PurchaseData JSON");
                            }
                        } else {
                            fw.a("trackPurchase", "dataSignature", "is null, skipping purchase validation");
                        }
                    } else if (b4 != null) {
                        fw.a("trackPurchase", "purchaseData", "is null. skipping purchase validation");
                    }
                    a.g.a(b, b2.toUpperCase(Locale.US), ((double) gVar.g) / 1000000.0d, b3, b4, fu.b(str4));
                    if (b3 == null || b4 == null) {
                        fw.a("trackPurchase without purchaseData called");
                    } else {
                        fw.a("trackPurchase with purchaseData called");
                    }
                }
            } catch (IOException e2) {
                fw.a("trackPurchase", "skuDetails", "invalid SkuDetails JSON");
            }
        }
    }
}
