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
    private av i;
    private av j;
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
            ax.a("MaioAds#init", BuildConfig.FLAVOR, BuildConfig.FLAVOR, null);
            try {
                this.g = activity.getApplicationContext();
                this.h = str;
                t.a(this.g);
                au.a(this.g);
                x.a();
                bf.a(this.g);
                a(maioAdsListenerInterface);
            } catch (ay e) {
                bk.a(e.a, null);
            }
            this.i = am.a(this.h, this.e);
            if (this.i != null) {
                c();
                am.a(this.i);
            }
            bk.a();
            c = true;
            startStatusTimer();
            if (this.i == null) {
                a((long) TapjoyConstants.PAID_APP_TIME);
            } else {
                a((long) (this.i.b.g * GameControllerDelegate.THUMBSTICK_LEFT_X));
            }
        }
    }

    private void a(MaioAdsListenerInterface maioAdsListenerInterface) {
        if (this.g != null) {
            MaioAdsListenerInterface ajVar = new aj(this);
            bk.a(maioAdsListenerInterface, ajVar);
            this.p = maioAdsListenerInterface;
            this.q = ajVar;
        }
    }

    private boolean a(String str) {
        if (!b() || this.i == null) {
            return false;
        }
        if (str == null) {
            str = this.i.c;
        }
        ax.a("MaioAds#canShow.", "zoneEid=" + str, null);
        if (!this.i.e.containsKey(str)) {
            return false;
        }
        bj bjVar = (bj) this.i.e.get(str);
        return bjVar == null ? false : bjVar.a();
    }

    private void b(String str) {
        boolean z = true;
        if (canShow(str)) {
            this.f = true;
            ax.a("playing locked", BuildConfig.FLAVOR, BuildConfig.FLAVOR, null);
            if (str == null) {
                str = this.i.c;
            }
            ax.a("MaioAds#show.", "zoneEid=" + str, null);
            Serializable c = c(str);
            v f = c.f();
            if (f != null) {
                Intent intent = new Intent(this.g, AdFullscreenActivity.class);
                intent.addFlags(268435456);
                intent.putExtra("media", this.i);
                intent.putExtra("zone", c);
                intent.putExtra("creative", f);
                if (am.a() <= 30) {
                    z = false;
                }
                this.k = z;
                this.g.startActivity(intent);
                if (!this.k) {
                    am.a(this.i, f.f, f.b);
                }
            }
        }
    }

    private static boolean b() {
        return VERSION.SDK_INT >= 16;
    }

    private bj c(String str) {
        return (bj) this.i.e.get(str);
    }

    private void c() {
        for (bj bjVar : this.i.e.values()) {
            if (!this.d.containsKey(bjVar.b)) {
                this.d.put(bjVar.b, BuildConfig.FLAVOR);
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
                    bk.a(entry.getKey().toString(), canShow);
                }
            }
        }
    }

    private void g() {
        if (this.f) {
            ax.a("updating campaign info unlocked", BuildConfig.FLAVOR, BuildConfig.FLAVOR, null);
            return;
        }
        try {
            this.j = am.a(this.h, this.e);
            if (this.j != null) {
                c();
                ax.a("MaioAdsupdating zone status locked", BuildConfig.FLAVOR, BuildConfig.FLAVOR, null);
                am.a(this.j);
                this.i = this.j;
                this.j = null;
                f();
                ax.a("updating campaign info unlocked", BuildConfig.FLAVOR, BuildConfig.FLAVOR, null);
            }
        } catch (Exception e) {
            bk.a(FailNotificationReason.UNKNOWN, e.getMessage());
        } finally {
            f();
            ax.a("updating campaign info unlocked", BuildConfig.FLAVOR, BuildConfig.FLAVOR, null);
        }
    }

    public static String getSdkVersion() {
        return "1.0.3";
    }

    private void h() {
        this.n = new ak(this);
    }

    private void i() {
        this.o = new al(this);
    }

    public static void init(Activity activity, String str, MaioAdsListenerInterface maioAdsListenerInterface) {
        if (b()) {
            bb.b.execute(new ai(activity, str, maioAdsListenerInterface));
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
