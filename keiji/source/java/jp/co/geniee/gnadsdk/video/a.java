package jp.co.geniee.gnadsdk.video;

import android.os.Handler;
import jp.co.geniee.gnadsdk.interstitial.a.b;

/* compiled from: GNAdVideo */
public class a implements jp.co.geniee.gnadsdk.interstitial.a.a, b {
    private static a a = null;
    private a b;
    private boolean c;
    private boolean d;
    private c e;
    private boolean f;

    /* compiled from: GNAdVideo */
    public interface a {
        void a();

        void a(int i);

        void b();

        void c();
    }

    private void g() {
        this.f = false;
        this.d = false;
        if (e() != null) {
            e().b();
        }
    }

    private void h() {
        this.f = true;
        this.d = false;
        if (e() != null) {
            e().a();
        }
    }

    static synchronized a d() {
        a aVar;
        synchronized (a.class) {
            aVar = a;
        }
        return aVar;
    }

    public a e() {
        return this.b;
    }

    public void a(boolean z) {
        this.c = z;
    }

    protected c f() {
        return this.e;
    }

    public void a(int i) {
        if (e() != null) {
            e().a(i);
        }
        a(false);
    }

    public void a() {
        if (e() != null) {
            e().c();
        }
        a(false);
    }

    public void b() {
        new Handler().post(new Runnable(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.h();
            }
        });
    }

    public void c() {
        new Handler().post(new Runnable(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.g();
            }
        });
    }
}
