package com.jirbo.adcolony;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import org.cocos2dx.lib.BuildConfig;
import twitter4j.HttpResponseCode;

class a {
    static boolean A = false;
    static boolean B = false;
    static boolean C = false;
    static boolean D = d;
    static boolean E = true;
    static int F = g;
    static double G = 1.0d;
    static boolean H = d;
    static boolean I = d;
    static boolean J = d;
    static boolean K = d;
    static boolean L = true;
    static boolean M = false;
    static boolean N = false;
    static boolean O = d;
    static Activity P = null;
    static boolean Q = false;
    static boolean R = false;
    static h S = null;
    static AdColonyAd T = null;
    static ADCVideo U = null;
    static ADCVideo V = null;
    static a W = null;
    static b X = null;
    static boolean Y = false;
    static boolean Z = false;
    public static final boolean a = false;
    static boolean aa = false;
    static boolean ab = false;
    static int ac = 0;
    static String ad = null;
    static String ae = null;
    static String af = null;
    static String ag = null;
    static String ah = null;
    static ArrayList<String> ai = new ArrayList();
    static c aj = new c();
    static boolean ak = false;
    static long al = 0;
    static int am = g;
    static ArrayList<Bitmap> an = new ArrayList();
    static ArrayList<AdColonyV4VCListener> ao = new ArrayList();
    static ArrayList<AdColonyAdAvailabilityListener> ap = new ArrayList();
    static ArrayList<AdColonyNativeAdView> aq = new ArrayList();
    static HashMap ar = null;
    public static final boolean b = false;
    public static final boolean c = false;
    public static final boolean d = false;
    public static String e = null;
    public static final String f = null;
    public static final int g = 0;
    public static final int h = 1;
    public static final int i = 2;
    public static final int j = 3;
    static final String k = "AdColony";
    static d l = new d();
    static boolean m;
    static int n = i;
    static boolean o;
    static boolean p;
    static double q;
    static boolean r;
    static boolean s;
    static long t;
    static long u;
    static AdColonyAd v;
    static boolean w;
    static boolean x;
    static boolean y;
    static boolean z;

    static class a extends Handler {
        AdColonyAd a;

        a() {
        }

        public void a(AdColonyAd adColonyAd) {
            if (adColonyAd == null) {
                this.a = a.T;
            } else {
                this.a = adColonyAd;
            }
            sendMessage(obtainMessage(a.h));
        }

        public void b(AdColonyAd adColonyAd) {
            if (adColonyAd == null) {
                this.a = a.T;
            } else {
                this.a = adColonyAd;
            }
            sendMessage(obtainMessage(a.g));
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case a.g /*0*/:
                    a.a("skip", this.a);
                    if (a.T != null) {
                        a.T.f = a.h;
                        a.T.a();
                        return;
                    }
                    return;
                case a.h /*1*/:
                    g gVar = new g();
                    if (a.V.H.Q) {
                        gVar.b("html5_endcard_loading_started", a.V.k);
                    }
                    if (a.V.H.Q) {
                        gVar.b("html5_endcard_loading_finished", a.V.l);
                    }
                    if (a.V.H.Q) {
                        gVar.b("html5_endcard_loading_time", a.V.p);
                    }
                    if (a.V.H.Q) {
                        gVar.b("html5_endcard_loading_timeout", a.V.m);
                    }
                    if (a.V.q < 60000.0d) {
                        gVar.b("endcard_time_spent", a.V.q);
                    }
                    gVar.b("endcard_dissolved", a.V.n);
                    ADCVideo aDCVideo = a.V;
                    gVar.b("replay", ADCVideo.e);
                    gVar.b("reward", a.V.o);
                    a.l.d.a("continue", gVar, this.a);
                    if (a.T != null) {
                        a.T.f = 4;
                        a.T.a();
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    static class b extends Handler {
        b() {
        }

        public void handleMessage(Message message) {
            int i = a.g;
            String str = (String) message.obj;
            int i2 = message.what;
            boolean z = str != null ? true : a.d;
            if (!z) {
                str = BuildConfig.FLAVOR;
            }
            AdColonyV4VCReward adColonyV4VCReward = new AdColonyV4VCReward(z, str, i2);
            while (i < a.ao.size()) {
                ((AdColonyV4VCListener) a.ao.get(i)).onAdColonyV4VCReward(adColonyV4VCReward);
                i += a.h;
            }
        }

        public void a(boolean z, String str, int i) {
            if (!z) {
                str = null;
            }
            sendMessage(obtainMessage(i, str));
        }
    }

    a() {
    }

    static void a(Activity activity) {
        if (activity != P && activity != null) {
            P = activity;
            W = new a();
            X = new b();
            if (!s) {
                a aVar = new a();
            }
        }
    }

    static void b(Activity activity) {
        x = d;
        a(activity);
        S = null;
        m = g.i();
        if (Q) {
            Q = d;
            w = d;
            l = new d();
        }
    }

    static boolean a() {
        if (P == null) {
            return true;
        }
        return d;
    }

    static Activity b() {
        if (P != null) {
            return P;
        }
        throw new AdColonyException("AdColony.configure() must be called before any other AdColony methods. If you have called AdColony.configure(), the Activity reference you passed in via AdColony.configure()/AdColony.resume() is null OR you have not called AdColony.resume() as appropriate.");
    }

    static boolean c() {
        return (Q || y || !w) ? true : d;
    }

    static boolean d() {
        return (!w || Q || y) ? d : true;
    }

    static void a(String str) {
        Q = true;
        e(str);
    }

    static void a(RuntimeException runtimeException) {
        Q = true;
        e(runtimeException.toString());
        runtimeException.printStackTrace();
    }

    static void e() {
        b();
    }

    static void a(int i) {
        boolean z = d;
        n = i;
        l.a.f = i <= 0 ? true : d;
        l.b.f = i <= h ? true : d;
        l.c.f = i <= i ? true : d;
        l lVar = l.d;
        if (i <= j) {
            z = true;
        }
        lVar.f = z;
        if (i <= 0) {
            b("DEVELOPER LOGGING ENABLED");
        }
        if (i <= h) {
            c("DEBUG LOGGING ENABLED");
        }
    }

    static boolean b(int i) {
        return n <= i ? true : d;
    }

    static boolean f() {
        return n <= 0 ? true : d;
    }

    static boolean g() {
        return n <= h ? true : d;
    }

    static void a(int i, String str) {
        if (n <= i) {
            switch (i) {
                case g /*0*/:
                case h /*1*/:
                    Log.d(k, str);
                    return;
                case i /*2*/:
                    Log.i(k, str);
                    return;
                case j /*3*/:
                    Log.e(k, str);
                    return;
                default:
                    return;
            }
        }
    }

    static void b(String str) {
        a((int) g, str);
    }

    static void c(String str) {
        a((int) h, str);
    }

    static void d(String str) {
        a((int) i, str);
    }

    static void e(String str) {
        a((int) j, str);
    }

    static void f(String str) {
        Toast.makeText(b(), str, g).show();
    }

    static double g(String str) {
        return l.a(str);
    }

    static int h(String str) {
        return l.b(str);
    }

    static boolean i(String str) {
        return l.c(str);
    }

    static String j(String str) {
        return l.d(str);
    }

    static void k(String str) {
        l.a(str, null);
    }

    static void a(String str, String str2) {
        l.a(str, str2);
    }

    static void a(String str, AdColonyAd adColonyAd) {
        l.a(str, null, adColonyAd);
    }

    static void a(String str, String str2, AdColonyAd adColonyAd) {
        l.a(str, str2, adColonyAd);
    }

    static void h() {
        if (l != null && ap.size() != 0 && ar != null) {
            for (Entry entry : ar.entrySet()) {
                boolean b;
                boolean z;
                boolean booleanValue = ((Boolean) entry.getValue()).booleanValue();
                if (AdColony.isZoneV4VC((String) entry.getKey())) {
                    b = l.b((String) entry.getKey(), true, d);
                } else {
                    b = l.a((String) entry.getKey(), true, (boolean) d);
                }
                if (!AdColony.isZoneNative((String) entry.getKey()) || P == null) {
                    z = P == null ? d : b;
                } else {
                    z = new AdColonyNativeAdView(b(), (String) entry.getKey(), (int) HttpResponseCode.MULTIPLE_CHOICES, true).b(true);
                }
                if (booleanValue != z) {
                    ar.put(entry.getKey(), Boolean.valueOf(z));
                    for (int i = g; i < ap.size(); i += h) {
                        ((AdColonyAdAvailabilityListener) ap.get(i)).onAdColonyAdAvailabilityChange(z, (String) entry.getKey());
                    }
                }
            }
        }
    }

    static void a(AdColonyAd adColonyAd) {
        v = adColonyAd;
    }

    static void a(AdColonyNativeAdView adColonyNativeAdView) {
        aq.add(adColonyNativeAdView);
    }

    static void a(j jVar) {
        l.a(jVar);
    }
}
