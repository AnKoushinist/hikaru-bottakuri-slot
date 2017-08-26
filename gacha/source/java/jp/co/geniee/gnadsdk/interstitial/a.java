package jp.co.geniee.gnadsdk.interstitial;

import android.content.Context;
import android.os.Handler;

/* compiled from: GNInterstitial */
public class a implements c {
    protected static b a = null;
    private static a b = null;
    private b c;
    private a d;
    private final jp.co.geniee.gnadsdk.a.a e;
    private boolean f;
    private boolean g;
    private boolean h;
    private Context i;

    /* compiled from: GNInterstitial */
    public interface a {
        void a();

        void a(int i);
    }

    /* compiled from: GNInterstitial */
    public interface b {
        void b();

        void c();
    }

    public void a() {
        a = null;
        a = new b(this.i, this.e, null, null);
    }

    private void g() {
        this.h = false;
        this.g = false;
        if (d() != null) {
            d().c();
        }
    }

    private void h() {
        this.h = true;
        this.g = false;
        if (d() != null) {
            d().b();
        }
    }

    public static synchronized a b() {
        a aVar;
        synchronized (a.class) {
            aVar = b;
        }
        return aVar;
    }

    public static synchronized b c() {
        b bVar;
        synchronized (a.class) {
            bVar = a;
        }
        return bVar;
    }

    public b d() {
        return this.c;
    }

    public a e() {
        return this.d;
    }

    public void a(boolean z) {
        this.f = z;
        if (this.c != null && (this.c instanceof jp.co.geniee.gnadsdk.video.a)) {
            ((jp.co.geniee.gnadsdk.video.a) this.c).a(z);
        }
    }

    public void f() {
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

    public void a(int i, String str) {
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
