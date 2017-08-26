package com.tapjoy.internal;

import android.content.Context;
import android.content.Intent;
import android.opengl.GLSurfaceView;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Base64;
import com.tapjoy.TapjoyConstants;
import com.tapjoy.internal.gd.c;
import com.tapjoy.internal.gd.i;
import com.tapjoy.internal.gd.n;
import com.tapjoy.internal.gd.p;
import com.tapjoy.internal.gd.x;
import com.tapjoy.internal.gd.x.a;
import java.io.File;
import java.net.URL;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executors;
import twitter4j.HttpResponseCode;

public final class fm {
    private static final fm q;
    private static fm r;
    private static Handler w;
    private static File x;
    public final fu a = new fu(this);
    public fv b;
    public boolean c = false;
    public String d = null;
    public Context e;
    public fp f;
    public fl g;
    public fz h;
    public fk i;
    public String j;
    public boolean k;
    public String l;
    public String m;
    public boolean n = false;
    public String o;
    public fn p = fn.a(null);
    private boolean s = false;
    private boolean t = false;
    private String u;
    private String v;

    static {
        fm fmVar = new fm();
        q = fmVar;
        r = fmVar;
    }

    public static fm a() {
        return r;
    }

    private fm() {
    }

    final synchronized void b(Context context) {
        if (this.e == null) {
            Context applicationContext = context.getApplicationContext();
            this.e = applicationContext;
            this.f = fp.a(applicationContext);
            File file = new File(c(applicationContext), "events2");
            if (this.i == null) {
                this.i = new fk(file);
            }
            this.g = new fl(this.f, this.i);
            this.h = new fz(this.g);
            this.b = new fv(applicationContext);
        }
    }

    public final n a(boolean z) {
        if (z) {
            this.f.a();
        }
        return this.f.b();
    }

    public final synchronized void b() {
        if (this.k) {
            fo.b(this.e).e(this.d);
            a(null);
        }
    }

    public final synchronized void a(String str) {
        if (this.k) {
            if (str == null && this.o != null) {
                str = this.o;
            }
            this.o = null;
            if (str != null) {
                fj.a("GCM registration id of device {} updated for sender {}: {}", this.f.b().c.h(), this.d, str);
                new gs(r0, str).a(new cj(this) {
                    final /* synthetic */ fm b;

                    public final /* synthetic */ void a(ce ceVar, Object obj) {
                        r b = fo.b(this.b.e);
                        String str = str;
                        long currentTimeMillis = 0 != 0 ? System.currentTimeMillis() + 0 : 0;
                        if (str.equals(b.b.b(b.a))) {
                            b.b.b(b.a, true);
                            if (currentTimeMillis != 0) {
                                new Object[1][0] = new Timestamp(currentTimeMillis);
                            }
                            b.b.a(b.a, currentTimeMillis);
                            return;
                        }
                        new Object[1][0] = str;
                    }

                    public final void a(ce ceVar) {
                    }
                }, ce.a);
            }
        } else if (str != null) {
            this.o = str;
        }
    }

    public static void a(GLSurfaceView gLSurfaceView) {
        if (fj.a((Object) gLSurfaceView, "setGLSurfaceView: The given GLSurfaceView was null")) {
            fe.a(gLSurfaceView);
        }
    }

    public final Set c() {
        if (c("getUserTags")) {
            return this.f.e();
        }
        return new HashSet();
    }

    public final void a(Set set) {
        if (c("setUserTags")) {
            Iterable iterable;
            if (!(set == null || set.isEmpty())) {
                Set hashSet = new HashSet();
                for (String str : set) {
                    String str2;
                    if (str2 != null) {
                        str2 = str2.trim();
                        if (!str2.isEmpty() && str2.length() <= HttpResponseCode.OK) {
                            hashSet.add(str2);
                            if (hashSet.size() >= HttpResponseCode.OK) {
                                break;
                            }
                        }
                    }
                }
                iterable = hashSet;
            }
            fp fpVar = this.f;
            synchronized (fpVar) {
                if (iterable != null) {
                    if (!iterable.isEmpty()) {
                        a e = x.e();
                        e.e();
                        dh.a.a(iterable, e.b);
                        x d = e.d();
                        if (d.c()) {
                            fpVar.c.z.a(Base64.encodeToString(d.a(), 2));
                            fpVar.b.y();
                            fpVar.b.a(iterable);
                        } else {
                            throw new dz();
                        }
                    }
                }
                fpVar.c.z.c();
                fpVar.b.y();
            }
        }
    }

    public final synchronized void a(Context context, String str, String str2, String str3, String str4, String str5) {
        Object obj = 1;
        synchronized (this) {
            if (!this.k) {
                boolean z;
                b(context);
                if (this.e != null) {
                    z = true;
                } else {
                    z = false;
                }
                if (fj.a(z, "The given context was null")) {
                    Object obj2;
                    if (str4 != null && str4.length() == 24 && str4.matches("[0-9a-f]{24}")) {
                        obj2 = 1;
                    } else {
                        fj.b("Invalid App ID: {}", str4);
                        obj2 = null;
                    }
                    if (obj2 != null) {
                        if (str5 != null && str5.length() == 20 && str5.matches("[0-9A-Za-z\\-_]{20}")) {
                            obj2 = 1;
                        } else {
                            fj.b("Invalid App Key: {}", str5);
                            obj2 = null;
                        }
                        if (obj2 != null) {
                            this.l = str;
                            this.m = str2;
                            this.u = str4;
                            this.v = str5;
                            try {
                                URL url = new URL(str3);
                                ch ciVar = new ci("TapjoySDK" + " " + str2 + " (" + Build.MODEL + "; Android " + VERSION.RELEASE + "; " + Locale.getDefault() + ")", url);
                                ce.b = ciVar;
                                ce.a = Executors.newCachedThreadPool();
                                fk fkVar = this.i;
                                fkVar.b = ciVar;
                                fkVar.a();
                                new Object[1][0] = str3;
                                this.k = true;
                                fq fqVar = new fq(d(this.e));
                                if (fqVar.b() == null) {
                                    obj = null;
                                }
                                if (obj == null && fqVar.a()) {
                                    fl flVar = this.g;
                                    flVar.a(flVar.a(i.APP, "install"));
                                }
                                fp fpVar = this.f;
                                if (!(cr.c(str4) || str4.equals(fpVar.c.D.a()))) {
                                    fpVar.c.D.a(str4);
                                    fpVar.c.a(false);
                                }
                                b();
                            } catch (Throwable e) {
                                throw new IllegalArgumentException(e);
                            }
                        }
                    }
                }
            }
        }
    }

    public final boolean b(String str) {
        if ((this.k || this.j != null) && this.e != null) {
            return true;
        }
        if (fj.a) {
            fj.b(str + ": Should be called after initializing the SDK");
        }
        return false;
    }

    public final boolean c(String str) {
        if (this.e != null) {
            return true;
        }
        if (fj.a) {
            fj.b(str + ": Should be called after initializing the SDK");
        }
        return false;
    }

    public final boolean d() {
        return this.h != null && this.h.b.get();
    }

    public final boolean e() {
        boolean z;
        fz fzVar = this.h;
        if (fzVar.c != null) {
            fzVar.c.cancel(false);
            fzVar.c = null;
        }
        if (fzVar.b.compareAndSet(false, true)) {
            fj.a("New session started");
            fl flVar = fzVar.a;
            p d = flVar.a.d();
            fp fpVar = flVar.a;
            synchronized (fpVar) {
                int b = fpVar.c.h.b() + 1;
                fpVar.c.h.a(b);
                fpVar.b.d(b);
            }
            c.a a = flVar.a(i.APP, "bootup");
            flVar.c = SystemClock.elapsedRealtime();
            if (d != null) {
                if (d == null) {
                    throw new NullPointerException();
                }
                a.l = d;
                a.b |= 65536;
            }
            flVar.a(a);
            eo.c.notifyObservers();
            z = true;
        } else {
            z = false;
        }
        if (!z) {
            return false;
        }
        fu fuVar = this.a;
        synchronized (fuVar) {
            fuVar.b = null;
        }
        return true;
    }

    final void a(Map map) {
        fl flVar = this.g;
        c.a a = flVar.a(i.CAMPAIGN, "impression");
        if (map != null) {
            a.a(bl.a((Object) map));
        }
        flVar.a(a);
    }

    final void a(Map map, long j) {
        fl flVar = this.g;
        c.a d = flVar.a(i.CAMPAIGN, "view").d(j);
        if (map != null) {
            d.a(bl.a((Object) map));
        }
        flVar.a(d);
    }

    final void a(Map map, String str) {
        fl flVar = this.g;
        c.a a = flVar.a(i.CAMPAIGN, "click");
        Object linkedHashMap = new LinkedHashMap(map);
        linkedHashMap.put("region", str);
        a.a(bl.a(linkedHashMap));
        flVar.a(a);
    }

    public static synchronized void a(Runnable runnable) {
        synchronized (fm.class) {
            if (w == null) {
                w = new Handler(Looper.getMainLooper());
            }
            w.post(runnable);
        }
    }

    static synchronized File c(Context context) {
        File file;
        synchronized (fm.class) {
            if (x == null) {
                x = context.getDir("fiverocks", 0);
            }
            file = x;
        }
        return file;
    }

    static File d(Context context) {
        return new File(c(context), "install");
    }

    public static String a(Context context, Intent intent) {
        String a = f.a(intent);
        if (a != null) {
            fm fmVar = r;
            fmVar.b(context);
            if (cr.c(fmVar.f.c()) || intent.getBooleanExtra("fiverocks:force", false)) {
                fp fpVar = fmVar.f;
                synchronized (fpVar) {
                    fpVar.c.d.a(a);
                    fpVar.b.a(a);
                }
                if (a.length() > 0) {
                    fl flVar = fmVar.g;
                    flVar.a(flVar.a(i.APP, TapjoyConstants.TJC_REFERRER));
                }
            }
        }
        return a;
    }

    public static fm a(Context context) {
        fm fmVar = r;
        fmVar.b(context);
        return fmVar;
    }
}
