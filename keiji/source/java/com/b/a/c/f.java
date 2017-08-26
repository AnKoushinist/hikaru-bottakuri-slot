package com.b.a.c;

import a.a.a.a.a.b.g;
import a.a.a.a.a.b.n;
import a.a.a.a.a.c.d;
import a.a.a.a.a.c.l;
import a.a.a.a.a.c.m;
import a.a.a.a.a.e.e;
import a.a.a.a.a.g.o;
import a.a.a.a.a.g.p;
import a.a.a.a.a.g.q;
import a.a.a.a.a.g.t;
import a.a.a.a.i;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.pm.PackageInfo;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import com.tapjoy.TapjoyConnectCore;
import java.io.File;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@d(a = {com.b.a.c.a.a.class})
/* compiled from: CrashlyticsCore */
public class f extends i<Void> {
    private g A;
    private com.b.a.c.a.a B;
    private final long a;
    private final ConcurrentHashMap<String, String> b;
    private File c;
    private a.a.a.a.a.f.a d;
    private h j;
    private h k;
    private i l;
    private l m;
    private String n;
    private String o;
    private String p;
    private String q;
    private String r;
    private String s;
    private String t;
    private String u;
    private String v;
    private float w;
    private boolean x;
    private final x y;
    private e z;

    /* compiled from: CrashlyticsCore */
    private static final class a implements Callable<Boolean> {
        private final h a;

        public /* synthetic */ Object call() {
            return a();
        }

        public a(h hVar) {
            this.a = hVar;
        }

        public Boolean a() {
            if (!this.a.b()) {
                return Boolean.FALSE;
            }
            a.a.a.a.c.h().a("CrashlyticsCore", "Found previous crash marker.");
            this.a.c();
            return Boolean.TRUE;
        }
    }

    /* compiled from: CrashlyticsCore */
    private static final class b implements i {
        private b() {
        }

        public void a() {
        }
    }

    /* compiled from: CrashlyticsCore */
    private static class c {
        private boolean a;
        private final CountDownLatch b;

        private c() {
            this.a = false;
            this.b = new CountDownLatch(1);
        }

        void a(boolean z) {
            this.a = z;
            this.b.countDown();
        }

        boolean a() {
            return this.a;
        }

        void b() {
            try {
                this.b.await();
            } catch (InterruptedException e) {
            }
        }
    }

    protected /* synthetic */ Object e() {
        return d();
    }

    public f() {
        this(TapjoyConnectCore.DEFAULT_CURRENCY_MULTIPLIER, null, null, false);
    }

    f(float f, i iVar, x xVar, boolean z) {
        this(f, iVar, xVar, z, n.a("Crashlytics Exception Handler"));
    }

    f(float f, i iVar, x xVar, boolean z, ExecutorService executorService) {
        this.n = null;
        this.o = null;
        this.p = null;
        this.w = f;
        if (iVar == null) {
            iVar = new b();
        }
        this.l = iVar;
        this.y = xVar;
        this.x = z;
        this.A = new g(executorService);
        this.b = new ConcurrentHashMap();
        this.a = System.currentTimeMillis();
    }

    protected boolean l_() {
        return a(super.E());
    }

    boolean a(Context context) {
        if (this.x) {
            return false;
        }
        this.s = new g().a(context);
        if (this.s == null) {
            return false;
        }
        a.a.a.a.c.h().c("CrashlyticsCore", "Initializing Crashlytics " + a());
        this.d = new a.a.a.a.a.f.b(this);
        this.k = new h("crash_marker", this.d);
        this.j = new h("initialization_marker", this.d);
        try {
            a(context, this.s);
            ad uVar = new u(context, h());
            boolean u = u();
            K();
            a(uVar);
            if (!u || !a.a.a.a.a.b.i.n(context)) {
                return true;
            }
            J();
            return false;
        } catch (Throwable e) {
            throw new m(e);
        } catch (Throwable e2) {
            a.a.a.a.c.h().e("CrashlyticsCore", "Crashlytics was not started due to an exception during initialization", e2);
            return false;
        }
    }

    private void a(Context context, String str) {
        a.a.a.a.a.e.g kVar = this.y != null ? new k(this.y) : null;
        this.z = new a.a.a.a.a.e.b(a.a.a.a.c.h());
        this.z.a(kVar);
        this.r = context.getPackageName();
        this.t = D().j();
        a.a.a.a.c.h().a("CrashlyticsCore", "Installer package name is: " + this.t);
        PackageInfo packageInfo = context.getPackageManager().getPackageInfo(this.r, 0);
        this.u = Integer.toString(packageInfo.versionCode);
        this.v = packageInfo.versionName == null ? "0.0" : packageInfo.versionName;
        this.q = a.a.a.a.a.b.i.m(context);
        a(this.q, b(context)).a(str, this.r);
    }

    private void a(ad adVar) {
        try {
            a.a.a.a.c.h().a("CrashlyticsCore", "Installing exception handler...");
            this.m = new l(Thread.getDefaultUncaughtExceptionHandler(), this.A, D(), adVar, this.d, this);
            this.m.b();
            Thread.setDefaultUncaughtExceptionHandler(this.m);
            a.a.a.a.c.h().a("CrashlyticsCore", "Successfully installed exception handler.");
        } catch (Throwable e) {
            a.a.a.a.c.h().e("CrashlyticsCore", "There was a problem installing the exception handler.", e);
        }
    }

    protected Void d() {
        s();
        this.m.g();
        try {
            t b = q.a().b();
            if (b == null) {
                a.a.a.a.c.h().d("CrashlyticsCore", "Received null settings, skipping initialization!");
            } else if (b.d.c) {
                this.m.c();
                n a = a(b);
                if (a == null) {
                    a.a.a.a.c.h().d("CrashlyticsCore", "Unable to create a call to upload reports.");
                    t();
                } else {
                    new aa(a).a(this.w);
                    t();
                }
            } else {
                a.a.a.a.c.h().a("CrashlyticsCore", "Collection of crash reports disabled in Crashlytics settings.");
                t();
            }
        } catch (Throwable e) {
            a.a.a.a.c.h().e("CrashlyticsCore", "Crashlytics encountered a problem during asynchronous initialization.", e);
        } finally {
            t();
        }
        return null;
    }

    public String b() {
        return "com.crashlytics.sdk.android.crashlytics-core";
    }

    public String a() {
        return "2.3.8.97";
    }

    public static f f() {
        return (f) a.a.a.a.c.a(f.class);
    }

    static void a(String str) {
        com.b.a.a.a aVar = (com.b.a.a.a) a.a.a.a.c.a(com.b.a.a.a.class);
        if (aVar != null) {
            aVar.a(new a.a.a.a.a.b.j.a(str));
        }
    }

    Map<String, String> g() {
        return Collections.unmodifiableMap(this.b);
    }

    a a(String str, boolean z) {
        return new a(str, z);
    }

    String h() {
        return this.r;
    }

    String i() {
        return this.s;
    }

    String j() {
        return this.t;
    }

    String k() {
        return this.v;
    }

    String l() {
        return this.u;
    }

    String m() {
        return a.a.a.a.a.b.i.b(E(), "com.crashlytics.ApiEndpoint");
    }

    String n() {
        return this.q;
    }

    l o() {
        return this.m;
    }

    String p() {
        return D().a() ? this.n : null;
    }

    String q() {
        return D().a() ? this.o : null;
    }

    String r() {
        return D().a() ? this.p : null;
    }

    private void J() {
        Callable anonymousClass1 = new a.a.a.a.a.c.g<Void>(this) {
            final /* synthetic */ f a;

            {
                this.a = r1;
            }

            public /* synthetic */ Object call() {
                return a();
            }

            public Void a() {
                return this.a.d();
            }

            public a.a.a.a.a.c.e b() {
                return a.a.a.a.a.c.e.IMMEDIATE;
            }
        };
        for (l a : I()) {
            anonymousClass1.a(a);
        }
        Future submit = F().f().submit(anonymousClass1);
        a.a.a.a.c.h().a("CrashlyticsCore", "Crashlytics detected incomplete initialization on previous app launch. Will initialize synchronously.");
        try {
            submit.get(4, TimeUnit.SECONDS);
        } catch (Throwable e) {
            a.a.a.a.c.h().e("CrashlyticsCore", "Crashlytics was interrupted during initialization.", e);
        } catch (Throwable e2) {
            a.a.a.a.c.h().e("CrashlyticsCore", "Problem encountered during Crashlytics initialization.", e2);
        } catch (Throwable e22) {
            a.a.a.a.c.h().e("CrashlyticsCore", "Crashlytics timed out during initialization.", e22);
        }
    }

    void s() {
        this.A.a(new Callable<Void>(this) {
            final /* synthetic */ f a;

            {
                this.a = r1;
            }

            public /* synthetic */ Object call() {
                return a();
            }

            public Void a() {
                this.a.j.a();
                a.a.a.a.c.h().a("CrashlyticsCore", "Initialization marker file created.");
                return null;
            }
        });
    }

    void t() {
        this.A.b(new Callable<Boolean>(this) {
            final /* synthetic */ f a;

            {
                this.a = r1;
            }

            public /* synthetic */ Object call() {
                return a();
            }

            public Boolean a() {
                try {
                    boolean c = this.a.j.c();
                    a.a.a.a.c.h().a("CrashlyticsCore", "Initialization marker file removed: " + c);
                    return Boolean.valueOf(c);
                } catch (Throwable e) {
                    a.a.a.a.c.h().e("CrashlyticsCore", "Problem encountered deleting Crashlytics initialization marker.", e);
                    return Boolean.valueOf(false);
                }
            }
        });
    }

    boolean u() {
        return ((Boolean) this.A.a(new Callable<Boolean>(this) {
            final /* synthetic */ f a;

            {
                this.a = r1;
            }

            public /* synthetic */ Object call() {
                return a();
            }

            public Boolean a() {
                return Boolean.valueOf(this.a.j.b());
            }
        })).booleanValue();
    }

    com.b.a.c.a.a.d v() {
        if (this.B != null) {
            return this.B.a();
        }
        return null;
    }

    File w() {
        if (this.c == null) {
            this.c = new a.a.a.a.a.f.b(this).a();
        }
        return this.c;
    }

    boolean x() {
        return ((Boolean) q.a().a(new a.a.a.a.a.g.q.b<Boolean>(this) {
            final /* synthetic */ f a;

            {
                this.a = r1;
            }

            public /* synthetic */ Object b(t tVar) {
                return a(tVar);
            }

            public Boolean a(t tVar) {
                boolean z = false;
                if (!tVar.d.a) {
                    return Boolean.valueOf(false);
                }
                if (!this.a.y()) {
                    z = true;
                }
                return Boolean.valueOf(z);
            }
        }, Boolean.valueOf(false))).booleanValue();
    }

    boolean y() {
        return new a.a.a.a.a.f.d(this).a().getBoolean("always_send_reports_opt_in", false);
    }

    @SuppressLint({"CommitPrefEdits"})
    void a(boolean z) {
        a.a.a.a.a.f.c dVar = new a.a.a.a.a.f.d(this);
        dVar.a(dVar.b().putBoolean("always_send_reports_opt_in", z));
    }

    boolean z() {
        return ((Boolean) q.a().a(new a.a.a.a.a.g.q.b<Boolean>(this) {
            final /* synthetic */ f a;

            {
                this.a = r1;
            }

            public /* synthetic */ Object b(t tVar) {
                return a(tVar);
            }

            public Boolean a(t tVar) {
                boolean z = true;
                Activity b = this.a.F().b();
                if (!(b == null || b.isFinishing() || !this.a.x())) {
                    z = this.a.a(b, tVar.c);
                }
                return Boolean.valueOf(z);
            }
        }, Boolean.valueOf(true))).booleanValue();
    }

    n a(t tVar) {
        if (tVar != null) {
            return new o(this, m(), tVar.a.d, this.z);
        }
        return null;
    }

    private void K() {
        if (Boolean.TRUE.equals((Boolean) this.A.a(new a(this.k)))) {
            try {
                this.l.a();
            } catch (Throwable e) {
                a.a.a.a.c.h().e("CrashlyticsCore", "Exception thrown by CrashlyticsListener while notifying of previous crash.", e);
            }
        }
    }

    void A() {
        this.k.a();
    }

    private boolean a(Activity activity, o oVar) {
        final q qVar = new q(activity, oVar);
        final c cVar = new c();
        final Activity activity2 = activity;
        final o oVar2 = oVar;
        activity.runOnUiThread(new Runnable(this) {
            final /* synthetic */ f e;

            public void run() {
                Builder builder = new Builder(activity2);
                OnClickListener anonymousClass1 = new OnClickListener(this) {
                    final /* synthetic */ AnonymousClass7 a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                        cVar.a(true);
                        dialogInterface.dismiss();
                    }
                };
                float f = activity2.getResources().getDisplayMetrics().density;
                int a = f.b(f, 5);
                View textView = new TextView(activity2);
                textView.setAutoLinkMask(15);
                textView.setText(qVar.b());
                textView.setTextAppearance(activity2, 16973892);
                textView.setPadding(a, a, a, a);
                textView.setFocusable(false);
                View scrollView = new ScrollView(activity2);
                scrollView.setPadding(f.b(f, 14), f.b(f, 2), f.b(f, 10), f.b(f, 12));
                scrollView.addView(textView);
                builder.setView(scrollView).setTitle(qVar.a()).setCancelable(false).setNeutralButton(qVar.c(), anonymousClass1);
                if (oVar2.d) {
                    builder.setNegativeButton(qVar.e(), new OnClickListener(this) {
                        final /* synthetic */ AnonymousClass7 a;

                        {
                            this.a = r1;
                        }

                        public void onClick(DialogInterface dialogInterface, int i) {
                            cVar.a(false);
                            dialogInterface.dismiss();
                        }
                    });
                }
                if (oVar2.f) {
                    builder.setPositiveButton(qVar.d(), new OnClickListener(this) {
                        final /* synthetic */ AnonymousClass7 a;

                        {
                            this.a = r1;
                        }

                        public void onClick(DialogInterface dialogInterface, int i) {
                            this.a.e.a(true);
                            cVar.a(true);
                            dialogInterface.dismiss();
                        }
                    });
                }
                builder.show();
            }
        });
        a.a.a.a.c.h().a("CrashlyticsCore", "Waiting for user opt-in.");
        cVar.b();
        return cVar.a();
    }

    static p B() {
        t b = q.a().b();
        return b == null ? null : b.b;
    }

    private static boolean b(Context context) {
        return a.a.a.a.a.b.i.a(context, "com.crashlytics.RequireBuildId", true);
    }

    private static int b(float f, int i) {
        return (int) (((float) i) * f);
    }
}
