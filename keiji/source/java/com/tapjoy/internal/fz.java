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
import com.tapjoy.internal.dy.a;
import java.io.File;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executors;
import twitter4j.HttpResponseCode;

public final class fz {
    private static final fz q;
    private static fz r;
    private static Handler w;
    private static File x;
    public final gh a = new gh(this);
    public gi b;
    public boolean c = false;
    public String d = null;
    public Context e;
    public gc f;
    public fy g;
    public gm h;
    public fx i;
    public String j;
    public boolean k;
    public String l;
    public String m;
    public boolean n = false;
    public String o;
    public ga p = ga.a(null);
    private boolean s = false;
    private boolean t = false;
    private String u;
    private String v;

    static {
        fz fzVar = new fz();
        q = fzVar;
        r = fzVar;
    }

    public static fz a() {
        return r;
    }

    private fz() {
    }

    final synchronized void b(Context context) {
        if (this.e == null) {
            Context applicationContext = context.getApplicationContext();
            this.e = applicationContext;
            ez.a().a(applicationContext);
            this.f = gc.a(applicationContext);
            File file = new File(c(applicationContext), "events2");
            if (this.i == null) {
                this.i = new fx(file);
            }
            this.g = new fy(this.f, this.i);
            this.h = new gm(this.g);
            this.b = new gi(applicationContext);
            ff.a(new fh(new File(c(applicationContext), "usages"), this.g));
            gt gtVar = gt.a;
            gtVar.b = applicationContext.getApplicationContext();
            gtVar.c = applicationContext.getSharedPreferences("tapjoyCacheDataMMF2E", 0);
            gtVar.d = applicationContext.getSharedPreferences("tapjoyCacheDataMMF2U", 0);
            gtVar.a();
        }
    }

    public final ee a(boolean z) {
        if (z) {
            this.f.a();
        }
        return this.f.b();
    }

    public final synchronized void b() {
        if (this.k) {
            gb.b(this.e).e(this.d);
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
                fw.a("GCM registration id of device {} updated for sender {}: {}", this.f.b().d.h, this.d, str);
                new hl(r0, str).a(new ck(this) {
                    final /* synthetic */ fz b;

                    public final /* synthetic */ void a(cf cfVar, Object obj) {
                        r b = gb.b(this.b.e);
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

                    public final void a(cf cfVar) {
                    }
                }, cf.a);
            }
        } else if (str != null) {
            this.o = str;
        }
    }

    public static void a(GLSurfaceView gLSurfaceView) {
        if (fw.a((Object) gLSurfaceView, "setGLSurfaceView: The given GLSurfaceView was null")) {
            fr.a(gLSurfaceView);
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
                set = hashSet;
            }
            gc gcVar = this.f;
            synchronized (gcVar) {
                if (set != null) {
                    if (!set.isEmpty()) {
                        gcVar.c.z.a(Base64.encodeToString(ej.c.b(new ej(new ArrayList(set))), 2));
                        gcVar.b.A.clear();
                        gcVar.b.A.addAll(set);
                    }
                }
                gcVar.c.z.c();
                gcVar.b.A.clear();
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
                if (fw.a(z, "The given context was null")) {
                    Object obj2;
                    if (str4 != null && str4.length() == 24 && str4.matches("[0-9a-f]{24}")) {
                        obj2 = 1;
                    } else {
                        fw.b("Invalid App ID: {}", str4);
                        obj2 = null;
                    }
                    if (obj2 != null) {
                        if (str5 != null && str5.length() == 20 && str5.matches("[0-9A-Za-z\\-_]{20}")) {
                            obj2 = 1;
                        } else {
                            fw.b("Invalid App Key: {}", str5);
                            obj2 = null;
                        }
                        if (obj2 != null) {
                            this.l = str;
                            this.m = str2;
                            this.u = str4;
                            this.v = str5;
                            try {
                                URL url = new URL(str3);
                                ci cjVar = new cj("TapjoySDK" + " " + str2 + " (" + Build.MODEL + "; Android " + VERSION.RELEASE + "; " + Locale.getDefault() + ")", url);
                                cf.b = cjVar;
                                cf.a = Executors.newCachedThreadPool();
                                fx fxVar = this.i;
                                fxVar.b = cjVar;
                                fxVar.a();
                                new Object[1][0] = str3;
                                this.k = true;
                                gd gdVar = new gd(d(this.e));
                                if (gdVar.b() == null) {
                                    obj = null;
                                }
                                if (obj == null && gdVar.a()) {
                                    fy fyVar = this.g;
                                    fyVar.a(fyVar.a(eb.APP, "install"));
                                }
                                gc gcVar = this.f;
                                if (!(ct.c(str4) || str4.equals(gcVar.c.D.a()))) {
                                    gcVar.c.D.a(str4);
                                    gcVar.c.a(false);
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
        if (fw.a) {
            fw.b(str + ": Should be called after initializing the SDK");
        }
        return false;
    }

    public final boolean c(String str) {
        if (this.e != null) {
            return true;
        }
        if (fw.a) {
            fw.b(str + ": Should be called after initializing the SDK");
        }
        return false;
    }

    public final boolean d() {
        return this.h != null && this.h.b.get();
    }

    public final boolean e() {
        boolean z;
        gm gmVar = this.h;
        if (gmVar.c != null) {
            gmVar.c.cancel(false);
            gmVar.c = null;
        }
        if (gmVar.b.compareAndSet(false, true)) {
            fw.a("New session started");
            fy fyVar = gmVar.a;
            ef d = fyVar.a.d();
            gc gcVar = fyVar.a;
            synchronized (gcVar) {
                int b = gcVar.c.h.b() + 1;
                gcVar.c.h.a(b);
                gcVar.b.h = Integer.valueOf(b);
            }
            a a = fyVar.a(eb.APP, "bootup");
            fyVar.c = SystemClock.elapsedRealtime();
            if (d != null) {
                a.s = d;
            }
            fyVar.a(a);
            et.c.notifyObservers();
            z = true;
        } else {
            z = false;
        }
        if (!z) {
            return false;
        }
        gh ghVar = this.a;
        synchronized (ghVar) {
            ghVar.b = null;
        }
        gt.a.a();
        return true;
    }

    final void a(Map map) {
        fy fyVar = this.g;
        a a = fyVar.a(eb.CAMPAIGN, "impression");
        if (map != null) {
            a.r = bm.a((Object) map);
        }
        fyVar.a(a);
    }

    final void a(Map map, long j) {
        fy fyVar = this.g;
        a a = fyVar.a(eb.CAMPAIGN, "view");
        a.i = Long.valueOf(j);
        if (map != null) {
            a.r = bm.a((Object) map);
        }
        fyVar.a(a);
    }

    final void a(Map map, String str) {
        fy fyVar = this.g;
        a a = fyVar.a(eb.CAMPAIGN, "click");
        Object linkedHashMap = new LinkedHashMap(map);
        linkedHashMap.put("region", str);
        a.r = bm.a(linkedHashMap);
        fyVar.a(a);
    }

    public static synchronized void a(Runnable runnable) {
        synchronized (fz.class) {
            if (w == null) {
                w = new Handler(Looper.getMainLooper());
            }
            w.post(runnable);
        }
    }

    static synchronized File c(Context context) {
        File file;
        synchronized (fz.class) {
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
            fz fzVar = r;
            fzVar.b(context);
            if (ct.c(fzVar.f.c()) || intent.getBooleanExtra("fiverocks:force", false)) {
                gc gcVar = fzVar.f;
                synchronized (gcVar) {
                    gcVar.c.d.a(a);
                    gcVar.b.d = a;
                }
                if (a.length() > 0) {
                    fy fyVar = fzVar.g;
                    fyVar.a(fyVar.a(eb.APP, TapjoyConstants.TJC_REFERRER));
                }
            }
        }
        return a;
    }

    public static fz a(Context context) {
        fz fzVar = r;
        fzVar.b(context);
        return fzVar;
    }
}
