package com.adcolony.sdk;

import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import com.adcolony.sdk.a.a;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.tapjoy.TJAdUnitConstants.String;
import com.tapjoy.TapjoyConstants;
import com.unity3d.ads.metadata.MediationMetaData;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import jp.co.geniee.gnsrewardadapter.GNSAdapterAdColonyRewardVideoAd;
import jp.co.geniee.gnsrewardadapter.GNSAdapterVungleRewardVideoAd;
import org.cocos2dx.lib.BuildConfig;
import org.cocos2dx.lib.GameControllerDelegate;
import org.json.JSONObject;

class aq implements a {
    private String A = "https://adc3-launch.adcolony.com/v4/launch";
    private String B;
    private String C;
    private String D;
    private boolean E;
    private boolean F;
    private boolean G;
    private boolean H;
    private boolean I;
    private boolean J;
    private boolean K;
    private boolean L;
    private boolean M;
    private boolean N;
    private int O;
    private int P = 1;
    private final int Q = 120;
    private ActivityLifecycleCallbacks R;
    as a;
    s b;
    boolean c;
    private ar d;
    private p e;
    private au f;
    private v g;
    private am h;
    private at i;
    private aw j;
    private z k;
    private x l;
    private bf m;
    private al n;
    private ae o;
    private g p;
    private l q;
    private HashMap<String, f> r = new HashMap();
    private d s;
    private o t;
    private boolean u;
    private o v;
    private JSONObject w;
    private HashMap<String, m> x = new HashMap();
    private HashMap<Integer, ad> y = new HashMap();
    private String z;

    aq() {
    }

    void a(d dVar, boolean z) {
        boolean z2 = true;
        this.G = z;
        this.s = dVar;
        this.e = new p();
        this.d = new ar();
        this.f = new au();
        this.f.a();
        this.g = new v();
        this.g.a();
        this.h = new am();
        this.h.a();
        this.i = new at();
        this.j = new aw();
        this.j.a();
        this.m = new bf();
        bf bfVar = this.m;
        bf.a();
        this.l = new x();
        this.l.a();
        this.k = new z();
        this.k.a();
        this.a = new as();
        this.a.d();
        this.b = new s();
        this.z = this.b.c();
        if (!z) {
            boolean z3;
            this.J = new File(this.l.g() + "026ae9c9824b3e483fa6c71fa88f57ae27816141").exists();
            this.K = new File(this.l.g() + "7bf3a1e7bbd31e612eda3310c2cdb8075c43c6b5").exists();
            if (this.J && this.K) {
                String a = bb.a(bb.c(this.l.g() + "026ae9c9824b3e483fa6c71fa88f57ae27816141"), "sdkVersion");
                as asVar = this.a;
                if (a.equals("3.1.2")) {
                    z3 = true;
                    this.I = z3;
                    e(this.I);
                    x();
                    if (new File(this.l.g() + "026ae9c9824b3e483fa6c71fa88f57ae27816141").exists()) {
                        this.w = bb.c(this.l.g() + "026ae9c9824b3e483fa6c71fa88f57ae27816141");
                        b(this.w);
                    }
                }
            }
            z3 = false;
            this.I = z3;
            e(this.I);
            x();
            if (new File(this.l.g() + "026ae9c9824b3e483fa6c71fa88f57ae27816141").exists()) {
                this.w = bb.c(this.l.g() + "026ae9c9824b3e483fa6c71fa88f57ae27816141");
                b(this.w);
            }
        }
        n.a("Module.load", new q(this) {
            final /* synthetic */ aq a;

            {
                this.a = r1;
            }

            public void a(o oVar) {
                this.a.a(oVar);
            }
        });
        n.a("Module.unload", new q(this) {
            final /* synthetic */ aq a;

            {
                this.a = r1;
            }

            public void a(o oVar) {
                this.a.e(oVar);
            }
        });
        n.a("AdColony.on_configured", new q(this) {
            final /* synthetic */ aq a;

            {
                this.a = r1;
            }

            public void a(o oVar) {
                this.a.H = true;
                if (this.a.M) {
                    JSONObject a = bb.a();
                    JSONObject a2 = bb.a();
                    bb.a(a2, TapjoyConstants.TJC_APP_VERSION_NAME, ab.a());
                    bb.a(a, "app_bundle_info", a2);
                    new o("AdColony.on_update", 1, a).a();
                    this.a.M = false;
                }
                if (this.a.N) {
                    new o("AdColony.on_update", 1).a();
                }
                if (bf.d != null) {
                    bf.d.b(bb.a(oVar.b(), "app_session_id"));
                }
            }
        });
        n.a("AdColony.get_app_info", new q(this) {
            final /* synthetic */ aq a;

            {
                this.a = r1;
            }

            public void a(o oVar) {
                this.a.f(oVar);
            }
        });
        n.a("AdColony.v4vc_reward", new q(this) {
            final /* synthetic */ aq a;

            {
                this.a = r1;
            }

            public void a(o oVar) {
                this.a.c(oVar);
            }
        });
        n.a("AdColony.zone_info", new q(this) {
            final /* synthetic */ aq a;

            {
                this.a = r1;
            }

            public void a(o oVar) {
                this.a.d(oVar);
            }
        });
        n.a("AdColony.probe_launch_server", new q(this) {
            final /* synthetic */ aq a;

            {
                this.a = r1;
            }

            public void a(o oVar) {
                this.a.a(true, true);
            }
        });
        n.a("Crypto.sha1", new q(this) {
            final /* synthetic */ aq a;

            {
                this.a = r1;
            }

            public void a(o oVar) {
                JSONObject a = bb.a();
                bb.a(a, "sha1", ab.c(bb.a(oVar.b(), String.DATA)));
                oVar.a(a).a();
            }
        });
        n.a("Crypto.crc32", new q(this) {
            final /* synthetic */ aq a;

            {
                this.a = r1;
            }

            public void a(o oVar) {
                JSONObject a = bb.a();
                bb.b(a, "crc32", ab.b(bb.a(oVar.b(), String.DATA)));
                oVar.a(a).a();
            }
        });
        n.a("Crypto.uuid", new q(this) {
            final /* synthetic */ aq a;

            {
                this.a = r1;
            }

            public void a(o oVar) {
                int b = bb.b(oVar.b(), "number");
                JSONObject a = bb.a();
                bb.a(a, "uuids", ab.a(b));
                oVar.a(a).a();
            }
        });
        n.a("Device.query_advertiser_info", new q(this) {
            final /* synthetic */ aq a;

            {
                this.a = r1;
            }

            public void a(final o oVar) {
                if (n.d()) {
                    ab.a.execute(new Runnable(this) {
                        final /* synthetic */ AnonymousClass13 b;

                        public void run() {
                            this.b.a.a(n.c(), oVar);
                        }
                    });
                }
            }
        });
        n.a("AdColony.controller_version", new q(this) {
            final /* synthetic */ aq a;

            {
                this.a = r1;
            }

            public void a(o oVar) {
                this.a.a.f = bb.a(oVar.b(), MediationMetaData.KEY_VERSION);
                if (bf.d != null) {
                    bf.d.a(this.a.a.f);
                }
                bd.d.b("Controller version: " + this.a.a.f);
            }
        });
        int a2 = ab.a(this.l);
        this.M = a2 == 1;
        if (a2 != 2) {
            z2 = false;
        }
        this.N = z2;
    }

    private boolean u() {
        if (this.G || !this.a.c().contains("arm") || ADCNative.nativeNeonSupported()) {
            return true;
        }
        bd.g.b((Object) "ARM architechture without NEON support. Disabling AdColony.");
        a(true);
        return false;
    }

    private boolean e(boolean z) {
        return a(z, false);
    }

    private boolean a(boolean z, boolean z2) {
        if (!n.d()) {
            return false;
        }
        ab.e(n.c().getFilesDir().getAbsolutePath() + "/../");
        bd.b.a(">").b(n.c().getFilesDir().getAbsolutePath() + "/../");
        File file = new File(this.l.c() + "/../lib/libImmEndpointWarpJ.so");
        this.a.d = file.exists();
        this.L = z2;
        this.I = z;
        if (z && !z2) {
            try {
                System.loadLibrary("js");
                System.loadLibrary("adcolony");
                this.e.a();
                u();
            } catch (UnsatisfiedLinkError e) {
                bd.g.b((Object) "Expecting libadcolony.so in libs directory but it was not found.");
                a(true);
                return false;
            }
        }
        v();
        return true;
    }

    private void v() {
        new Thread(new Runnable(this) {
            final /* synthetic */ aq a;

            {
                this.a = r1;
            }

            public void run() {
                JSONObject a = bb.a();
                bb.a(a, String.URL, this.a.A);
                bb.a(a, "content_type", "application/json");
                bb.a(a, "content", this.a.a.a(this.a.a).toString());
                bd.b.b("Launch: " + this.a.a.a(this.a.a).toString());
                bd.d.a("Saving Launch to ").a(this.a.l.g()).b((Object) "026ae9c9824b3e483fa6c71fa88f57ae27816141");
                a aVar = new a(new o("WebServices.post", 0, a), this.a);
            }
        }).start();
    }

    private boolean a(JSONObject jSONObject) {
        if (!this.I) {
            bd.f.b((Object) "Non-standard launch. Downloading new controller.");
            return true;
        } else if (this.w != null && bb.a(bb.e(this.w, "controller"), "sha1").equals(bb.a(bb.e(jSONObject, "controller"), "sha1"))) {
            return false;
        } else {
            bd.f.b((Object) "Controller sha1 does not match, downloading new controller.");
            return true;
        }
    }

    private void e(o oVar) {
        a(bb.b(oVar.b(), "id"));
    }

    void a(boolean z) {
        this.G = z;
    }

    private void f(o oVar) {
        JSONObject jSONObject = this.s.d;
        bb.a(jSONObject, GNSAdapterVungleRewardVideoAd.APP_ID_COLUMN_NAME, this.s.a);
        bb.a(jSONObject, "zone_ids", this.s.c);
        JSONObject a = bb.a();
        bb.a(a, "options", jSONObject);
        oVar.a(a).a();
    }

    boolean a(final o oVar) {
        if (!n.d()) {
            return false;
        }
        try {
            int b;
            if (oVar.b().has("id")) {
                b = bb.b(oVar.b(), "id");
            } else {
                b = 0;
            }
            if (b <= 0) {
                b = this.e.d();
            }
            a(b);
            boolean c = bb.c(oVar.b(), "is_webview");
            final boolean c2 = bb.c(oVar.b(), "is_display_module");
            if (c) {
                ab.a(new Runnable(this) {
                    final /* synthetic */ aq c;

                    public void run() {
                        r adVar = new ad(n.c().getApplicationContext(), this.c.e.d(), c2);
                        ((ad) adVar).a(true, oVar);
                        this.c.y.put(Integer.valueOf(adVar.a()), (ad) adVar);
                    }
                });
            } else {
                final ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
                newSingleThreadExecutor.submit(new Runnable(this) {
                    final /* synthetic */ aq d;

                    public void run() {
                        JSONObject e = bb.e(oVar.b(), String.VIDEO_INFO);
                        aq a = n.a();
                        if (b == 1 && a.a() != null) {
                            bb.a(e, "options", a.a().e());
                        }
                        this.d.e.a(new ADCVMModule(n.c(), b, bb.a(oVar.b(), "filepath"), e, newSingleThreadExecutor));
                    }
                });
                JSONObject a = bb.a();
                bb.a(a, "success", true);
                bb.b(a, "id", b);
                oVar.a(a).a();
            }
            return true;
        } catch (RuntimeException e) {
            bd.h.b("Failed to create AdUnit file://" + bb.a(oVar.b(), "filepath"));
            bd.h.b(e.toString());
            e.printStackTrace();
            return false;
        }
    }

    void b(o oVar) {
        this.t = oVar;
    }

    private void w() {
        int i = 120;
        if (n.a().g().f()) {
            this.O++;
            if (this.P * this.O <= 120) {
                i = this.P * this.O;
            }
            this.P = i;
            ab.a(new Runnable(this) {
                final /* synthetic */ aq a;

                {
                    this.a = r1;
                }

                public void run() {
                    new Handler().postDelayed(new Runnable(this) {
                        final /* synthetic */ AnonymousClass18 a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            if (n.a().g().f()) {
                                this.a.a.v();
                            }
                        }
                    }, (long) (this.a.P * GameControllerDelegate.THUMBSTICK_LEFT_X));
                }
            });
            return;
        }
        bd.f.b((Object) "Max launch server download attempts hit, or AdColony is no longer active.");
    }

    public void a(a aVar, o oVar, Map<String, List<String>> map) {
        if (aVar.a.equals(this.A)) {
            if (aVar.e) {
                bd.b.b("Launch: " + aVar.b);
                JSONObject a = bb.a(aVar.b);
                as asVar = this.a;
                bb.a(a, "sdkVersion", "3.1.2");
                if (a != null) {
                    bb.g(a, this.l.g() + "026ae9c9824b3e483fa6c71fa88f57ae27816141");
                    if (c(a)) {
                        if (a(a)) {
                            bd.d.b((Object) "Controller missing or out of date. Downloading new controller.");
                            JSONObject a2 = bb.a();
                            bb.a(a2, String.URL, this.B);
                            bb.a(a2, "filepath", this.l.g() + "7bf3a1e7bbd31e612eda3310c2cdb8075c43c6b5");
                            a aVar2 = new a(new o("WebServices.download", 0, a2), this);
                        }
                        this.w = a;
                        return;
                    } else if (!this.I) {
                        bd.g.b((Object) "Incomplete or disabled launch server response. Disabling AdColony until next launch.");
                        a(true);
                        return;
                    } else {
                        return;
                    }
                }
                return;
            }
            w();
        } else if (!aVar.a.equals(this.B)) {
        } else {
            if (!c(this.C)) {
                bd.e.b((Object) "Downloaded controller sha1 does not match expected value, retrying.");
                w();
            } else if (!this.I && !this.L) {
                ab.a(new Runnable(this) {
                    final /* synthetic */ aq a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        try {
                            System.loadLibrary("js");
                            System.loadLibrary("adcolony");
                        } catch (UnsatisfiedLinkError e) {
                            this.a.a(true);
                            bd.g.b((Object) "Expecting libadcolony.so in libs folder but it was not found. Disabling AdColony until next launch.");
                        }
                        this.a.e.a();
                        this.a.u();
                    }
                });
            }
        }
    }

    private boolean c(String str) {
        if (n.d()) {
            File file = new File(n.c().getFilesDir().getAbsolutePath() + "/adc3/" + "7bf3a1e7bbd31e612eda3310c2cdb8075c43c6b5");
            if (file.exists()) {
                return ab.a(str, file);
            }
        }
        return false;
    }

    boolean a(Context context, o oVar) {
        if (context == null) {
            return false;
        }
        Info info = null;
        try {
            info = AdvertisingIdClient.getAdvertisingIdInfo(context);
        } catch (NoClassDefFoundError e) {
            bd.e.b((Object) "Google Play Services ads dependencies are missing. Collecting Android ID instead of Advertising ID.");
            return false;
        } catch (NoSuchMethodError e2) {
            bd.e.b((Object) "Google Play Services is out of date, please update to GPS 4.0+. Collecting Android ID instead of Advertising ID.");
        } catch (Exception e3) {
            e3.printStackTrace();
            if (Build.MANUFACTURER.equals("Amazon")) {
                return false;
            }
            bd.e.b((Object) "Advertising ID is not available. Collecting Android ID instead of Advertising ID.");
            return false;
        }
        if (info == null) {
            return false;
        }
        this.a.a = info.getId();
        bf.d.e.put("advertisingId", this.a.a);
        this.a.c = info.isLimitAdTrackingEnabled();
        this.a.b = true;
        if (oVar != null) {
            JSONObject a = bb.a();
            bb.a(a, "advertiser_id", this.a.b());
            bb.a(a, "limit_ad_tracking", this.a.f());
            oVar.a(a).a();
        }
        return true;
    }

    void a(d dVar) {
        synchronized (this.h.c()) {
            for (Entry value : this.h.c().entrySet()) {
                g gVar = (g) value.getValue();
                gVar.a(true);
                gVar.b().onExpiring(gVar);
            }
            this.h.c().clear();
        }
        this.H = false;
        a(1);
        this.x.clear();
        this.s = dVar;
        this.e.a();
        a(true, true);
    }

    boolean a(int i) {
        if (this.e.a(i) == null) {
            return false;
        }
        if (this.y.containsKey(Integer.valueOf(i))) {
            ad adVar = (ad) this.y.get(Integer.valueOf(i));
            if (adVar.g()) {
                adVar.loadUrl("about:blank");
                adVar.clearCache(true);
                adVar.removeAllViews();
                adVar.a(true);
            }
            this.y.remove(Integer.valueOf(i));
        }
        if (this.v != null) {
            this.v.a();
            this.v = null;
            this.u = false;
        }
        bd.d.a("Destroying module with id = ").b(i);
        return true;
    }

    private void b(JSONObject jSONObject) {
        boolean z = true;
        if (!ADCVMModule.a) {
            JSONObject e = bb.e(jSONObject, "logging");
            bf.c = bb.a(e, "send_level", 1);
            if (!(bf.a || bb.c(e, "log_private"))) {
                z = false;
            }
            bf.a = z;
            bf.b = bb.a(e, "print_level", 3);
        }
    }

    private boolean c(JSONObject jSONObject) {
        if (jSONObject == null) {
            bd.d.b((Object) "Launch response verification failed - response is null or unknown");
            return false;
        }
        try {
            JSONObject e = bb.e(jSONObject, "controller");
            this.B = bb.a(e, String.URL);
            this.C = bb.a(e, "sha1");
            this.D = bb.a(jSONObject, "status");
            b(jSONObject);
        } catch (Exception e2) {
            try {
                new File(this.l.g() + "026ae9c9824b3e483fa6c71fa88f57ae27816141").delete();
            } catch (Exception e3) {
            }
        }
        if (this.D.equals("disable")) {
            try {
                new File(this.l.g() + "7bf3a1e7bbd31e612eda3310c2cdb8075c43c6b5").delete();
            } catch (Exception e4) {
            }
            bd.f.b((Object) "Launch server response with disabled status. Disabling AdColony until next launch.");
            a(true);
            return false;
        } else if (!this.B.equals(BuildConfig.FLAVOR) && !this.D.equals(BuildConfig.FLAVOR)) {
            return true;
        } else {
            bd.g.b((Object) "Missing controller status or URL. Disabling AdColony until next launch.");
            return false;
        }
    }

    boolean c(final o oVar) {
        if (this.q == null) {
            return false;
        }
        ab.a(new Runnable(this) {
            final /* synthetic */ aq b;

            public void run() {
                this.b.q.onReward(new k(oVar));
            }
        });
        return true;
    }

    void d(o oVar) {
        if (this.G) {
            bd.f.b((Object) "AdColony is disabled. Ignoring zone_info message.");
            return;
        }
        m mVar;
        String a = bb.a(oVar.b(), GNSAdapterAdColonyRewardVideoAd.ZONE_ID_COLUMN_NAME);
        if (this.x.containsKey(a)) {
            mVar = (m) this.x.get(a);
        } else {
            mVar = new m(a);
            this.x.put(a, mVar);
        }
        mVar.a(oVar);
    }

    private void x() {
        if (n.d() && this.R == null) {
            this.R = new ActivityLifecycleCallbacks(this) {
                final /* synthetic */ aq a;

                {
                    this.a = r1;
                }

                public void onActivityResumed(Activity activity) {
                    n.b = true;
                    n.a(activity);
                    if (n.d() && this.a.g.e() && (n.c() instanceof ah) && !((ah) n.c()).i) {
                        bd.d.b((Object) "Ignoring onActivityResumed");
                        return;
                    }
                    bd.d.b((Object) "onActivityResumed() Activity Lifecycle Callback");
                    n.a(activity);
                    if (this.a.t != null) {
                        this.a.t.a(this.a.t.b()).a();
                        this.a.t = null;
                    }
                    this.a.F = false;
                    this.a.g.b(true);
                    this.a.g.c(true);
                    this.a.g.d(false);
                    if (this.a.c && !this.a.g.f()) {
                        this.a.g.a(true);
                    }
                    this.a.i.a();
                    if (bf.d == null || bf.d.c == null || bf.d.c.isShutdown()) {
                        b.a(activity, n.a().s);
                    } else {
                        bf.d.a(5, TimeUnit.SECONDS);
                    }
                }

                public void onActivityPaused(Activity activity) {
                    n.b = false;
                    this.a.g.b(false);
                    this.a.g.c(true);
                    n.a().a.y();
                }

                public void onActivityDestroyed(Activity activity) {
                }

                public void onActivityStarted(Activity activity) {
                }

                public void onActivityStopped(Activity activity) {
                }

                public void onActivityCreated(Activity activity, Bundle bundle) {
                    if (!this.a.g.f()) {
                        this.a.g.a(true);
                    }
                    n.a(activity);
                }

                public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
                }
            };
            n.c().getApplication().registerActivityLifecycleCallbacks(this.R);
        }
    }

    d a() {
        if (this.s == null) {
            this.s = new d();
        }
        return this.s;
    }

    HashMap<String, m> b() {
        return this.x;
    }

    void b(boolean z) {
        this.F = z;
    }

    boolean c() {
        return this.F;
    }

    boolean d() {
        return this.G;
    }

    l e() {
        return this.q;
    }

    void a(l lVar) {
        this.q = lVar;
    }

    aw f() {
        if (this.j == null) {
            this.j = new aw();
            this.j.a();
        }
        return this.j;
    }

    v g() {
        if (this.g == null) {
            this.g = new v();
            this.g.a();
        }
        return this.g;
    }

    am h() {
        if (this.h == null) {
            this.h = new am();
            this.h.a();
        }
        return this.h;
    }

    as i() {
        if (this.a == null) {
            this.a = new as();
            this.a.d();
        }
        return this.a;
    }

    x j() {
        if (this.l == null) {
            this.l = new x();
            this.l.a();
        }
        return this.l;
    }

    p k() {
        if (this.e == null) {
            this.e = new p();
            this.e.a();
        }
        return this.e;
    }

    at l() {
        if (this.i == null) {
            this.i = new at();
        }
        return this.i;
    }

    al m() {
        return this.n;
    }

    void a(al alVar) {
        this.n = alVar;
    }

    ae n() {
        return this.o;
    }

    void a(ae aeVar) {
        this.o = aeVar;
    }

    g o() {
        return this.p;
    }

    void a(g gVar) {
        this.p = gVar;
    }

    String p() {
        return this.z;
    }

    void a(String str) {
        this.z = str;
    }

    boolean q() {
        return this.E;
    }

    void c(boolean z) {
        this.E = z;
    }

    HashMap<Integer, ad> r() {
        return this.y;
    }

    void d(boolean z) {
        this.u = z;
    }

    void b(String str) {
        this.A = str;
    }

    HashMap<String, f> s() {
        return this.r;
    }

    boolean t() {
        return this.H;
    }
}
