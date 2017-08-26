package jp.maio.sdk.android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import com.tapjoy.TapjoyConstants;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Timer;
import java.util.TimerTask;
import org.cocos2dx.lib.BuildConfig;
import org.cocos2dx.lib.GameControllerDelegate;

public class MaioAds {
    private static final MaioAds b = new MaioAds();
    private static boolean c = false;
    private final String a = "MaioAds";
    private final HashMap d = new HashMap();
    private boolean e;
    private boolean f;
    private Context g;
    private String h;
    private az i;
    private az j;
    private boolean k;
    private Timer l;
    private Timer m;
    private TimerTask n;
    private TimerTask o;
    private MaioAdsListenerInterface p;
    private MaioAdsListenerInterface q;

    private MaioAds() {
    }

    private void a(long j) {
        if (this.m == null) {
            this.m = new Timer();
            i();
            this.m.schedule(this.o, j, j);
        }
    }

    private void a(Activity activity, String str, MaioAdsListenerInterface maioAdsListenerInterface) {
        if (this.g == null) {
            bc.a("MaioAds#init", BuildConfig.FLAVOR, BuildConfig.FLAVOR, null);
            try {
                this.g = activity.getApplicationContext();
                this.h = str;
                v.a(this.g);
                ay.a(this.g);
                aa.a();
                bk.a(this.g);
                a(maioAdsListenerInterface);
            } catch (bd e) {
                bp.a(e.a, null);
            }
            this.i = aq.a(this.h, this.e);
            if (this.i != null) {
                c();
                aq.b(this.i);
            }
            bp.a();
            c = true;
            startStatusTimer();
            if (this.i == null) {
                a((long) TapjoyConstants.PAID_APP_TIME);
            } else {
                a((long) (this.i.b.h * GameControllerDelegate.THUMBSTICK_LEFT_X));
            }
        }
    }

    private void a(MaioAdsListenerInterface maioAdsListenerInterface) {
        if (this.g != null) {
            MaioAdsListenerInterface amVar = new am(this);
            bp.a(maioAdsListenerInterface, amVar);
            this.p = maioAdsListenerInterface;
            this.q = amVar;
        }
    }

    private boolean a(String str) {
        if (!b() || this.i == null) {
            return false;
        }
        if (str == null) {
            str = this.i.c;
        }
        bc.a("MaioAds#canShow.", "zoneEid=" + str, null);
        if (!this.i.f.containsKey(str)) {
            return false;
        }
        bo boVar = (bo) this.i.f.get(str);
        return boVar == null ? false : boVar.a();
    }

    private void b(String str) {
        boolean z = true;
        if (canShow(str)) {
            this.f = true;
            bc.a("playing locked", BuildConfig.FLAVOR, BuildConfig.FLAVOR, null);
            if (str == null) {
                str = this.i.c;
            }
            bc.a("MaioAds#show.", "zoneEid=" + str, null);
            Serializable c = c(str);
            x f = c.f();
            if (f != null) {
                Serializable baVar = new ba(c, this.i.b, this.i.c, this.i.d);
                Intent intent = new Intent(this.g, AdFullscreenActivity.class);
                intent.addFlags(268435456);
                intent.putExtra("media", baVar);
                intent.putExtra("zone", c);
                intent.putExtra("creative", f);
                if (aq.a() <= ((long) this.i.e)) {
                    z = false;
                }
                this.k = z;
                this.g.startActivity(intent);
                if (!this.k) {
                    aq.a(this.i, f.f, f.b);
                }
            }
        }
    }

    private static boolean b() {
        return VERSION.SDK_INT >= 16;
    }

    private bo c(String str) {
        return (bo) this.i.f.get(str);
    }

    private void c() {
        for (bo boVar : this.i.f.values()) {
            if (!this.d.containsKey(boVar.b)) {
                this.d.put(boVar.b, BuildConfig.FLAVOR);
            }
        }
    }

    public static boolean canShow() {
        return !c ? false : b.d();
    }

    public static boolean canShow(String str) {
        return !c ? false : b.a(str);
    }

    private boolean d() {
        return (this.i == null || !c) ? false : b.a(this.i.c);
    }

    private void e() {
        if (this.i != null) {
            b.b(this.i.c);
        }
    }

    private void f() {
        if (this.i != null) {
            for (Entry entry : this.d.entrySet()) {
                System.out.println(entry.getKey() + " = " + entry.getValue());
                boolean canShow = canShow(entry.getKey().toString());
                if (!entry.getValue().toString().equals(String.valueOf(canShow))) {
                    entry.setValue(String.valueOf(canShow));
                    bp.a(entry.getKey().toString(), canShow);
                }
            }
        }
    }

    private void g() {
        if (this.f) {
            bc.a("updating campaign info unlocked", BuildConfig.FLAVOR, BuildConfig.FLAVOR, null);
            return;
        }
        try {
            this.j = aq.a(this.h, this.e);
            if (this.j != null) {
                c();
                bc.a("MaioAdsupdating zone status locked", BuildConfig.FLAVOR, BuildConfig.FLAVOR, null);
                aq.a(this.j);
                this.i = this.j;
                this.j = null;
                f();
                bc.a("updating campaign info unlocked", BuildConfig.FLAVOR, BuildConfig.FLAVOR, null);
            }
        } catch (Exception e) {
            bp.a(FailNotificationReason.UNKNOWN, e.getMessage());
        } finally {
            f();
            bc.a("updating campaign info unlocked", BuildConfig.FLAVOR, BuildConfig.FLAVOR, null);
        }
    }

    public static String getSdkVersion() {
        return "1.0.5";
    }

    private void h() {
        this.n = new ao(this);
    }

    private void i() {
        this.o = new ap(this);
    }

    public static void init(Activity activity, String str, MaioAdsListenerInterface maioAdsListenerInterface) {
        if (b()) {
            bg.b.execute(new al(activity, str, maioAdsListenerInterface));
        }
    }

    public static void setAdTestMode(boolean z) {
        b.e = z;
    }

    public static void setMaioAdsListener(MaioAdsListenerInterface maioAdsListenerInterface) {
        b.a(maioAdsListenerInterface);
    }

    public static void show() {
        b.e();
    }

    public static void show(String str) {
        b.b(str);
    }

    public void startStatusTimer() {
        if (this.l == null) {
            this.l = new Timer();
            h();
            this.l.schedule(this.n, 0, 60000);
        }
    }
}
