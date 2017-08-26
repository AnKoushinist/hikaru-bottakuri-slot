package com.tapjoy.internal;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.graphics.Rect;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;
import com.tapjoy.TapjoyConstants;
import com.tapjoy.internal.gd.l;
import com.tapjoy.internal.gd.n;
import com.tapjoy.internal.gd.p;
import com.tapjoy.internal.gd.t;
import com.tapjoy.internal.gd.u;
import com.tapjoy.internal.gd.x;
import com.tapjoy.internal.gd.z;
import com.tapjoy.internal.gd.z.a;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.TimeZone;
import java.util.UUID;
import org.cocos2dx.lib.BuildConfig;
import twitter4j.TwitterResponse;

public final class fp {
    public static final String a = UUID.randomUUID().toString();
    private static fp d;
    public final a b = z.N();
    final fw c;
    private final l.a e = l.J();
    private final gd.a.a f = gd.a.n();
    private final Context g;

    public static synchronized fp a(Context context) {
        fp fpVar;
        synchronized (fp.class) {
            if (d == null) {
                d = new fp(context, fw.a(context));
            }
            fpVar = d;
        }
        return fpVar;
    }

    private fp(Context context, fw fwVar) {
        ga.a();
        l.a c = this.e.c("11.8.2/Android");
        c.b |= 16;
        c.g = "Android";
        String str = VERSION.RELEASE;
        if (str == null) {
            throw new NullPointerException();
        }
        c.b |= 32;
        c.h = str;
        str = Build.MANUFACTURER;
        if (str == null) {
            throw new NullPointerException();
        }
        c.b |= 4;
        c.e = str;
        str = Build.MODEL;
        if (str == null) {
            throw new NullPointerException();
        }
        Object obj;
        c.b |= 8;
        c.f = str;
        c.a(Locale.getDefault().toString()).b(TimeZone.getDefault().getID());
        Context applicationContext = context.getApplicationContext();
        this.g = applicationContext;
        String string = Secure.getString(applicationContext.getContentResolver(), TapjoyConstants.TJC_ANDROID_ID);
        if ("9774d56d682e549c".equals(string)) {
            obj = null;
        } else {
            obj = cr.b(string);
        }
        if (obj == null) {
            File a = w.a(applicationContext);
            obj = aa.a(new File(fm.c(applicationContext), "deviceid"), a != null ? new File(a, ".io.5rocks") : null);
        }
        l.a aVar = this.e;
        if (obj == null) {
            throw new NullPointerException();
        }
        String simCountryIso;
        l.a aVar2;
        aVar.b |= 2;
        aVar.d = obj;
        string = ab.a(applicationContext);
        if (string != null) {
            aVar = this.e;
            string = string.replace(":", BuildConfig.FLAVOR).toLowerCase(Locale.US);
            if (string == null) {
                throw new NullPointerException();
            }
            aVar.b |= 1;
            aVar.c = string;
        }
        PackageManager packageManager = applicationContext.getPackageManager();
        TelephonyManager telephonyManager = (TelephonyManager) applicationContext.getSystemService("phone");
        if (telephonyManager != null) {
            l.a aVar3;
            simCountryIso = telephonyManager.getSimCountryIso();
            if (!cr.c(simCountryIso)) {
                aVar3 = this.e;
                simCountryIso = simCountryIso.toUpperCase(Locale.US);
                if (simCountryIso == null) {
                    throw new NullPointerException();
                }
                aVar3.b |= 16384;
                aVar3.k = simCountryIso;
            }
            simCountryIso = telephonyManager.getNetworkCountryIso();
            if (!cr.c(simCountryIso)) {
                aVar3 = this.e;
                simCountryIso = simCountryIso.toUpperCase(Locale.US);
                if (simCountryIso == null) {
                    throw new NullPointerException();
                }
                aVar3.b |= 32768;
                aVar3.l = simCountryIso;
            }
            if (packageManager.checkPermission("android.permission.READ_PHONE_STATE", applicationContext.getPackageName()) == 0) {
                try {
                    string = telephonyManager.getDeviceId();
                    if (!cr.c(string)) {
                        aVar2 = this.e;
                        if (string == null) {
                            throw new NullPointerException();
                        }
                        aVar2.b |= 65536;
                        aVar2.m = string;
                    }
                } catch (SecurityException e) {
                } catch (RuntimeException e2) {
                }
            }
        }
        str = applicationContext.getPackageName();
        c = this.e;
        if (str == null) {
            throw new NullPointerException();
        }
        c.b |= 2048;
        c.i = str;
        aVar2 = this.e;
        Signature[] e3 = ae.e(packageManager, str);
        if (e3 == null || e3.length <= 0) {
            string = null;
        } else {
            string = Base64.encodeToString(cl.a(e3[0].toByteArray()), 2);
        }
        string = cr.a(string);
        if (string == null) {
            throw new NullPointerException();
        }
        aVar2.b |= 4096;
        aVar2.j = string;
        gd.a.a aVar4 = this.f;
        simCountryIso = ae.a(packageManager, str);
        if (simCountryIso == null) {
            throw new NullPointerException();
        }
        aVar4.b |= 1;
        aVar4.c = simCountryIso;
        this.f.a(ae.b(packageManager, str));
        string = packageManager.getInstallerPackageName(str);
        if (!cr.c(string)) {
            gd.a.a aVar5 = this.f;
            if (string == null) {
                throw new NullPointerException();
            }
            aVar5.b |= 8;
            aVar5.e = string;
        }
        string = a(packageManager, str);
        if (!cr.c(string)) {
            gd.a.a aVar6 = this.f;
            if (string == null) {
                throw new NullPointerException();
            }
            aVar6.b |= 16;
            aVar6.f = string;
        }
        a();
        this.c = fwVar;
        string = this.c.c.a();
        if (string != null && string.length() > 0) {
            this.e.c(string + " 11.8.2/Android");
        }
        string = this.c.b();
        if (string != null) {
            this.b.a(string);
        }
        a aVar7 = this.b;
        fw fwVar2 = this.c;
        long j = fwVar2.b.getLong("it", 0);
        if (j == 0) {
            Context context2 = fwVar2.a;
            j = ae.c(context2.getPackageManager(), context2.getPackageName());
            if (j == 0) {
                j = fm.d(fwVar2.a).lastModified();
                if (j == 0) {
                    applicationContext = fwVar2.a;
                    j = new File(ae.d(applicationContext.getPackageManager(), applicationContext.getPackageName())).lastModified();
                    if (j == 0) {
                        j = System.currentTimeMillis();
                    }
                }
            }
            fwVar2.b.edit().putLong("it", j).commit();
        }
        aVar7.a(j);
        int b = this.c.f.b();
        this.b.a(a(7, b));
        this.b.b(a(30, b));
        b = this.c.h.b();
        if (b > 0) {
            this.b.d(b);
        }
        j = this.c.i.a();
        if (j > 0) {
            this.b.b(j);
        }
        j = this.c.j.a();
        if (j > 0) {
            this.b.c(j);
        }
        j = this.c.k.a();
        if (j > 0) {
            this.b.d(j);
        }
        string = this.c.l.a();
        if (string != null) {
            this.b.b(string);
        }
        b = this.c.m.b();
        if (b > 0) {
            this.b.e(b);
        }
        double a2 = this.c.n.a();
        if (a2 != 0.0d) {
            this.b.a(a2);
        }
        j = this.c.o.a();
        if (j > 0) {
            this.b.e(j);
        }
        a2 = this.c.p.a();
        if (a2 != 0.0d) {
            this.b.b(a2);
        }
        string = this.c.g.a();
        if (string != null) {
            try {
                u a3 = u.a(Base64.decode(string, 2));
                a aVar8 = this.b;
                aVar8.c = Collections.emptyList();
                aVar8.b &= -17;
                aVar8 = this.b;
                Iterable iterable = a3.c;
                aVar8.e();
                dh.a.a(iterable, aVar8.c);
            } catch (IllegalArgumentException e4) {
                this.c.g.c();
            } catch (IOException e5) {
                this.c.g.c();
            }
        }
        string = this.c.q.a();
        if (string != null) {
            this.f.a(string);
        } else {
            this.f.f();
        }
        string = this.c.r.a();
        if (string != null) {
            this.b.d(string);
        } else {
            this.b.j();
        }
        b = this.c.s.a().intValue();
        if (b != -1) {
            this.b.f(b);
        } else {
            this.b.l();
        }
        b = this.c.t.a().intValue();
        if (b != -1) {
            this.b.g(b);
        } else {
            this.b.n();
        }
        string = this.c.u.a();
        if (string != null) {
            this.b.e(string);
        } else {
            this.b.p();
        }
        string = this.c.v.a();
        if (string != null) {
            this.b.f(string);
        } else {
            this.b.r();
        }
        string = this.c.w.a();
        if (string != null) {
            this.b.g(string);
        } else {
            this.b.t();
        }
        string = this.c.x.a();
        if (string != null) {
            this.b.h(string);
        } else {
            this.b.v();
        }
        string = this.c.y.a();
        if (string != null) {
            this.b.i(string);
        } else {
            this.b.x();
        }
        string = this.c.z.a();
        if (string != null) {
            try {
                x a4 = x.a(Base64.decode(string, 2));
                this.b.y();
                this.b.a(a4.c);
            } catch (IllegalArgumentException e6) {
                this.c.z.c();
            } catch (IOException e7) {
                this.c.z.c();
            }
        }
        string = this.c.A.a();
        boolean booleanValue = this.c.B.a().booleanValue();
        if (string != null) {
            this.b.c(string);
            this.b.a(booleanValue);
        } else {
            this.b.g();
            this.b.h();
        }
        this.b.b(this.c.C.b());
        new Thread(new Runnable(this) {
            final /* synthetic */ fp a;

            {
                this.a = r1;
            }

            public final void run() {
                ev evVar = new ev();
                boolean a = evVar.a(this.a.g);
                synchronized (this.a) {
                    if (a) {
                        String a2 = cr.a(evVar.a);
                        boolean z = evVar.b;
                        String a3 = this.a.c.A.a();
                        this.a.b.c(a2);
                        this.a.b.a(z);
                        this.a.c.A.a(a2);
                        this.a.c.B.a(z);
                        ga.a(a2, z);
                        if (!(cr.c(a3) || a2.equals(a3))) {
                            this.a.c.a(false);
                        }
                    } else {
                        this.a.b.g();
                        this.a.b.h();
                        this.a.c.A.a(null);
                        this.a.c.B.a(false);
                        ga.a(null, false);
                    }
                }
            }
        }).start();
    }

    private static String a(PackageManager packageManager, String str) {
        try {
            Bundle bundle = packageManager.getApplicationInfo(str, 128).metaData;
            if (bundle != null) {
                Object obj = bundle.get("com.tapjoy.appstore");
                if (obj != null) {
                    return obj.toString().trim();
                }
            }
        } catch (NameNotFoundException e) {
        }
        return null;
    }

    final void a() {
        synchronized (this) {
            try {
                DisplayMetrics displayMetrics = new DisplayMetrics();
                ((WindowManager) this.g.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
                Activity a = fe.a();
                if (a != null) {
                    Window window = a.getWindow();
                    if (window != null) {
                        int i = displayMetrics.heightPixels;
                        Rect rect = new Rect();
                        window.getDecorView().getWindowVisibleDisplayFrame(rect);
                        displayMetrics.heightPixels = i - rect.top;
                    }
                }
                this.e.a(displayMetrics.densityDpi);
                this.e.b(displayMetrics.widthPixels);
                this.e.c(displayMetrics.heightPixels);
            } catch (Exception e) {
            }
        }
    }

    public final n b() {
        n d;
        synchronized (this) {
            this.e.a(Locale.getDefault().toString()).b(TimeZone.getDefault().getID());
            long currentTimeMillis = System.currentTimeMillis() - 259200000;
            Object obj = null;
            for (int f = this.b.f() - 1; f >= 0; f--) {
                if (this.b.c(f).c <= currentTimeMillis) {
                    a aVar = this.b;
                    aVar.e();
                    aVar.c.remove(f);
                    obj = 1;
                }
            }
            if (obj != null) {
                f();
            }
            n.a h = n.h();
            l d2 = this.e.d();
            if (d2.c()) {
                h.c = d2;
                h.b |= 1;
                gd.a d3 = this.f.d();
                if (d3.c()) {
                    h.d = d3;
                    h.b |= 2;
                    z d4 = this.b.d();
                    if (d4.c()) {
                        h.e = d4;
                        h.b |= 4;
                        d = h.d();
                        if (d.c()) {
                        } else {
                            throw new dz();
                        }
                    }
                    throw new dz();
                }
                throw new dz();
            }
            throw new dz();
        }
        return d;
    }

    final String c() {
        String a;
        synchronized (this) {
            a = this.c.d.a();
        }
        return a;
    }

    public final p d() {
        p d;
        int i = 1;
        synchronized (this) {
            Calendar instance = Calendar.getInstance();
            int i2 = (((instance.get(1) * 10000) + (instance.get(2) * 100)) + 100) + instance.get(5);
            int intValue = this.c.e.a().intValue();
            if (intValue != i2) {
                p.a k;
                if (intValue == 0) {
                    this.b.a(1);
                    this.b.b(1);
                    k = p.k();
                    k.a("fq7_0_1");
                    k.b("fq30_0_1");
                } else {
                    Calendar calendar;
                    long timeInMillis;
                    int intValue2 = this.c.f.a().intValue();
                    int a = a(7, intValue2);
                    int a2 = a(30, intValue2);
                    Calendar instance2 = Calendar.getInstance();
                    instance2.set(intValue / 10000, ((intValue / 100) % 100) - 1, intValue % 100);
                    int signum = Integer.signum(instance.get(1) - instance2.get(1));
                    Calendar calendar2;
                    switch (signum) {
                        case Constants.PREF_TESTMODE_NOSETTING /*-1*/:
                            calendar2 = (Calendar) instance2.clone();
                            calendar2.set(instance.get(1), instance.get(2), instance.get(5));
                            calendar = calendar2;
                            timeInMillis = instance2.getTimeInMillis();
                            break;
                        case TwitterResponse.READ /*1*/:
                            calendar2 = (Calendar) instance.clone();
                            calendar2.set(instance2.get(1), instance2.get(2), instance2.get(5));
                            calendar = calendar2;
                            timeInMillis = instance.getTimeInMillis();
                            break;
                        default:
                            i = instance.get(6) - instance2.get(6);
                            break;
                    }
                    int i3 = 0;
                    while (calendar.getTimeInMillis() < timeInMillis) {
                        calendar.add(5, 1);
                        i3++;
                    }
                    i = signum > 0 ? i3 : -i3;
                    if (Math.abs(i) >= 30) {
                        i = 0;
                    } else if (i >= 0) {
                        i = intValue2 << i;
                    } else {
                        i = intValue2 >> (-i);
                    }
                    i |= 1;
                    intValue = a(7, i);
                    i3 = a(30, i);
                    this.b.a(intValue);
                    this.b.b(i3);
                    k = p.k();
                    k.a("fq7_" + a + "_" + intValue);
                    k.b("fq30_" + a2 + "_" + i3);
                }
                this.c.e.a(i2);
                this.c.f.a(i);
                d = k != null ? k.d() : null;
            } else {
                d = null;
            }
        }
        return d;
    }

    private static int a(int i, int i2) {
        return Integer.bitCount(((1 << i) - 1) & i2);
    }

    final boolean a(String str, long j, boolean z) {
        synchronized (this) {
            a aVar;
            int f = this.b.f();
            int i = 0;
            while (i < f) {
                t c = this.b.c(i);
                if (!c.f().equals(str)) {
                    i++;
                } else if (z) {
                    aVar = this.b;
                    t.a a = t.a(c).a(j);
                    aVar.e();
                    aVar.c.set(i, a.d());
                    f();
                    return true;
                } else {
                    return false;
                }
            }
            aVar = this.b;
            t.a i2 = t.i();
            if (str == null) {
                throw new NullPointerException();
            }
            i2.b |= 1;
            i2.c = str;
            i2 = i2.a(j);
            aVar.e();
            aVar.c.add(i2.d());
            f();
            return true;
        }
    }

    private void f() {
        u.a e = u.e();
        Iterable unmodifiableList = Collections.unmodifiableList(this.b.c);
        e.e();
        dh.a.a(unmodifiableList, e.b);
        u d = e.d();
        if (d.c()) {
            this.c.g.a(Base64.encodeToString(d.a(), 2));
            return;
        }
        throw new dz();
    }

    public final boolean a(String str) {
        boolean z;
        synchronized (this) {
            this.c.q.a(str);
            if (str != null) {
                if (this.f.e()) {
                    String str2;
                    gd.a.a aVar = this.f;
                    Object obj = aVar.d;
                    if (obj instanceof String) {
                        str2 = (String) obj;
                    } else {
                        dk dkVar = (dk) obj;
                        String e = dkVar.e();
                        if (dkVar.f()) {
                            aVar.d = e;
                        }
                        str2 = e;
                    }
                    if (str2.equals(str)) {
                        z = false;
                        this.f.a(str);
                    }
                }
                z = true;
                this.f.a(str);
            } else {
                z = this.f.e();
                this.f.f();
            }
        }
        return z;
    }

    public final boolean b(String str) {
        boolean z;
        synchronized (this) {
            this.c.r.a(str);
            if (str != null) {
                if (this.b.i()) {
                    String str2;
                    a aVar = this.b;
                    Object obj = aVar.f;
                    if (obj instanceof String) {
                        str2 = (String) obj;
                    } else {
                        dk dkVar = (dk) obj;
                        String e = dkVar.e();
                        if (dkVar.f()) {
                            aVar.f = e;
                        }
                        str2 = e;
                    }
                    if (str2.equals(str)) {
                        z = false;
                        this.b.d(str);
                    }
                }
                z = true;
                this.b.d(str);
            } else {
                z = this.b.i();
                this.b.j();
            }
        }
        return z;
    }

    public final boolean a(int i) {
        boolean z;
        synchronized (this) {
            this.c.s.a(i);
            if (i != -1) {
                z = (this.b.k() && this.b.g == i) ? false : true;
                this.b.f(i);
            } else {
                z = this.b.k();
                this.b.l();
            }
        }
        return z;
    }

    public final boolean b(int i) {
        boolean z;
        synchronized (this) {
            this.c.t.a(i);
            if (i != -1) {
                z = (this.b.m() && this.b.h == i) ? false : true;
                this.b.g(i);
            } else {
                z = this.b.m();
                this.b.n();
            }
        }
        return z;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean a(int r6, java.lang.String r7) {
        /*
        r5 = this;
        r2 = 1;
        r1 = 0;
        monitor-enter(r5);
        switch(r6) {
            case 1: goto L_0x0009;
            case 2: goto L_0x0052;
            case 3: goto L_0x0099;
            case 4: goto L_0x00e1;
            case 5: goto L_0x0129;
            default: goto L_0x0006;
        };
    L_0x0006:
        r0 = r1;
    L_0x0007:
        monitor-exit(r5);	 Catch:{ all -> 0x003e }
        return r0;
    L_0x0009:
        r0 = r5.c;	 Catch:{ all -> 0x003e }
        r0 = r0.u;	 Catch:{ all -> 0x003e }
        r0.a(r7);	 Catch:{ all -> 0x003e }
        if (r7 == 0) goto L_0x0046;
    L_0x0012:
        r0 = r5.b;	 Catch:{ all -> 0x003e }
        r0 = r0.o();	 Catch:{ all -> 0x003e }
        if (r0 == 0) goto L_0x0037;
    L_0x001a:
        r4 = r5.b;	 Catch:{ all -> 0x003e }
        r0 = r4.i;	 Catch:{ all -> 0x003e }
        r3 = r0 instanceof java.lang.String;	 Catch:{ all -> 0x003e }
        if (r3 != 0) goto L_0x0041;
    L_0x0022:
        r0 = (com.tapjoy.internal.dk) r0;	 Catch:{ all -> 0x003e }
        r3 = r0.e();	 Catch:{ all -> 0x003e }
        r0 = r0.f();	 Catch:{ all -> 0x003e }
        if (r0 == 0) goto L_0x0030;
    L_0x002e:
        r4.i = r3;	 Catch:{ all -> 0x003e }
    L_0x0030:
        r0 = r3;
    L_0x0031:
        r0 = r0.equals(r7);	 Catch:{ all -> 0x003e }
        if (r0 != 0) goto L_0x0044;
    L_0x0037:
        r0 = r2;
    L_0x0038:
        r1 = r5.b;	 Catch:{ all -> 0x003e }
        r1.e(r7);	 Catch:{ all -> 0x003e }
        goto L_0x0007;
    L_0x003e:
        r0 = move-exception;
        monitor-exit(r5);	 Catch:{ all -> 0x003e }
        throw r0;
    L_0x0041:
        r0 = (java.lang.String) r0;	 Catch:{ all -> 0x003e }
        goto L_0x0031;
    L_0x0044:
        r0 = r1;
        goto L_0x0038;
    L_0x0046:
        r0 = r5.b;	 Catch:{ all -> 0x003e }
        r0 = r0.o();	 Catch:{ all -> 0x003e }
        r1 = r5.b;	 Catch:{ all -> 0x003e }
        r1.p();	 Catch:{ all -> 0x003e }
        goto L_0x0007;
    L_0x0052:
        r0 = r5.c;	 Catch:{ all -> 0x003e }
        r0 = r0.v;	 Catch:{ all -> 0x003e }
        r0.a(r7);	 Catch:{ all -> 0x003e }
        if (r7 == 0) goto L_0x008c;
    L_0x005b:
        r0 = r5.b;	 Catch:{ all -> 0x003e }
        r0 = r0.q();	 Catch:{ all -> 0x003e }
        if (r0 == 0) goto L_0x0080;
    L_0x0063:
        r4 = r5.b;	 Catch:{ all -> 0x003e }
        r0 = r4.j;	 Catch:{ all -> 0x003e }
        r3 = r0 instanceof java.lang.String;	 Catch:{ all -> 0x003e }
        if (r3 != 0) goto L_0x0087;
    L_0x006b:
        r0 = (com.tapjoy.internal.dk) r0;	 Catch:{ all -> 0x003e }
        r3 = r0.e();	 Catch:{ all -> 0x003e }
        r0 = r0.f();	 Catch:{ all -> 0x003e }
        if (r0 == 0) goto L_0x0079;
    L_0x0077:
        r4.j = r3;	 Catch:{ all -> 0x003e }
    L_0x0079:
        r0 = r3;
    L_0x007a:
        r0 = r0.equals(r7);	 Catch:{ all -> 0x003e }
        if (r0 != 0) goto L_0x008a;
    L_0x0080:
        r0 = r2;
    L_0x0081:
        r1 = r5.b;	 Catch:{ all -> 0x003e }
        r1.f(r7);	 Catch:{ all -> 0x003e }
        goto L_0x0007;
    L_0x0087:
        r0 = (java.lang.String) r0;	 Catch:{ all -> 0x003e }
        goto L_0x007a;
    L_0x008a:
        r0 = r1;
        goto L_0x0081;
    L_0x008c:
        r0 = r5.b;	 Catch:{ all -> 0x003e }
        r0 = r0.q();	 Catch:{ all -> 0x003e }
        r1 = r5.b;	 Catch:{ all -> 0x003e }
        r1.r();	 Catch:{ all -> 0x003e }
        goto L_0x0007;
    L_0x0099:
        r0 = r5.c;	 Catch:{ all -> 0x003e }
        r0 = r0.w;	 Catch:{ all -> 0x003e }
        r0.a(r7);	 Catch:{ all -> 0x003e }
        if (r7 == 0) goto L_0x00d4;
    L_0x00a2:
        r0 = r5.b;	 Catch:{ all -> 0x003e }
        r0 = r0.s();	 Catch:{ all -> 0x003e }
        if (r0 == 0) goto L_0x00c7;
    L_0x00aa:
        r4 = r5.b;	 Catch:{ all -> 0x003e }
        r0 = r4.k;	 Catch:{ all -> 0x003e }
        r3 = r0 instanceof java.lang.String;	 Catch:{ all -> 0x003e }
        if (r3 != 0) goto L_0x00cf;
    L_0x00b2:
        r0 = (com.tapjoy.internal.dk) r0;	 Catch:{ all -> 0x003e }
        r3 = r0.e();	 Catch:{ all -> 0x003e }
        r0 = r0.f();	 Catch:{ all -> 0x003e }
        if (r0 == 0) goto L_0x00c0;
    L_0x00be:
        r4.k = r3;	 Catch:{ all -> 0x003e }
    L_0x00c0:
        r0 = r3;
    L_0x00c1:
        r0 = r0.equals(r7);	 Catch:{ all -> 0x003e }
        if (r0 != 0) goto L_0x00d2;
    L_0x00c7:
        r0 = r2;
    L_0x00c8:
        r1 = r5.b;	 Catch:{ all -> 0x003e }
        r1.g(r7);	 Catch:{ all -> 0x003e }
        goto L_0x0007;
    L_0x00cf:
        r0 = (java.lang.String) r0;	 Catch:{ all -> 0x003e }
        goto L_0x00c1;
    L_0x00d2:
        r0 = r1;
        goto L_0x00c8;
    L_0x00d4:
        r0 = r5.b;	 Catch:{ all -> 0x003e }
        r0 = r0.s();	 Catch:{ all -> 0x003e }
        r1 = r5.b;	 Catch:{ all -> 0x003e }
        r1.t();	 Catch:{ all -> 0x003e }
        goto L_0x0007;
    L_0x00e1:
        r0 = r5.c;	 Catch:{ all -> 0x003e }
        r0 = r0.x;	 Catch:{ all -> 0x003e }
        r0.a(r7);	 Catch:{ all -> 0x003e }
        if (r7 == 0) goto L_0x011c;
    L_0x00ea:
        r0 = r5.b;	 Catch:{ all -> 0x003e }
        r0 = r0.u();	 Catch:{ all -> 0x003e }
        if (r0 == 0) goto L_0x010f;
    L_0x00f2:
        r4 = r5.b;	 Catch:{ all -> 0x003e }
        r0 = r4.l;	 Catch:{ all -> 0x003e }
        r3 = r0 instanceof java.lang.String;	 Catch:{ all -> 0x003e }
        if (r3 != 0) goto L_0x0117;
    L_0x00fa:
        r0 = (com.tapjoy.internal.dk) r0;	 Catch:{ all -> 0x003e }
        r3 = r0.e();	 Catch:{ all -> 0x003e }
        r0 = r0.f();	 Catch:{ all -> 0x003e }
        if (r0 == 0) goto L_0x0108;
    L_0x0106:
        r4.l = r3;	 Catch:{ all -> 0x003e }
    L_0x0108:
        r0 = r3;
    L_0x0109:
        r0 = r0.equals(r7);	 Catch:{ all -> 0x003e }
        if (r0 != 0) goto L_0x011a;
    L_0x010f:
        r0 = r2;
    L_0x0110:
        r1 = r5.b;	 Catch:{ all -> 0x003e }
        r1.h(r7);	 Catch:{ all -> 0x003e }
        goto L_0x0007;
    L_0x0117:
        r0 = (java.lang.String) r0;	 Catch:{ all -> 0x003e }
        goto L_0x0109;
    L_0x011a:
        r0 = r1;
        goto L_0x0110;
    L_0x011c:
        r0 = r5.b;	 Catch:{ all -> 0x003e }
        r0 = r0.u();	 Catch:{ all -> 0x003e }
        r1 = r5.b;	 Catch:{ all -> 0x003e }
        r1.v();	 Catch:{ all -> 0x003e }
        goto L_0x0007;
    L_0x0129:
        r0 = r5.c;	 Catch:{ all -> 0x003e }
        r0 = r0.y;	 Catch:{ all -> 0x003e }
        r0.a(r7);	 Catch:{ all -> 0x003e }
        if (r7 == 0) goto L_0x0164;
    L_0x0132:
        r0 = r5.b;	 Catch:{ all -> 0x003e }
        r0 = r0.w();	 Catch:{ all -> 0x003e }
        if (r0 == 0) goto L_0x0157;
    L_0x013a:
        r4 = r5.b;	 Catch:{ all -> 0x003e }
        r0 = r4.m;	 Catch:{ all -> 0x003e }
        r3 = r0 instanceof java.lang.String;	 Catch:{ all -> 0x003e }
        if (r3 != 0) goto L_0x015f;
    L_0x0142:
        r0 = (com.tapjoy.internal.dk) r0;	 Catch:{ all -> 0x003e }
        r3 = r0.e();	 Catch:{ all -> 0x003e }
        r0 = r0.f();	 Catch:{ all -> 0x003e }
        if (r0 == 0) goto L_0x0150;
    L_0x014e:
        r4.m = r3;	 Catch:{ all -> 0x003e }
    L_0x0150:
        r0 = r3;
    L_0x0151:
        r0 = r0.equals(r7);	 Catch:{ all -> 0x003e }
        if (r0 != 0) goto L_0x0162;
    L_0x0157:
        r0 = r2;
    L_0x0158:
        r1 = r5.b;	 Catch:{ all -> 0x003e }
        r1.i(r7);	 Catch:{ all -> 0x003e }
        goto L_0x0007;
    L_0x015f:
        r0 = (java.lang.String) r0;	 Catch:{ all -> 0x003e }
        goto L_0x0151;
    L_0x0162:
        r0 = r1;
        goto L_0x0158;
    L_0x0164:
        r0 = r5.b;	 Catch:{ all -> 0x003e }
        r0 = r0.w();	 Catch:{ all -> 0x003e }
        r1 = r5.b;	 Catch:{ all -> 0x003e }
        r1.x();	 Catch:{ all -> 0x003e }
        goto L_0x0007;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tapjoy.internal.fp.a(int, java.lang.String):boolean");
    }

    public final Set e() {
        Set hashSet;
        synchronized (this) {
            hashSet = new HashSet(this.b.n.b());
        }
        return hashSet;
    }

    public final boolean c(String str) {
        synchronized (this) {
            for (int f = this.b.f() - 1; f >= 0; f--) {
                t c = this.b.c(f);
                if (c.f().equals(str)) {
                    c = t.a(c).b(System.currentTimeMillis()).d();
                    a aVar = this.b;
                    if (c == null) {
                        throw new NullPointerException();
                    }
                    aVar.e();
                    aVar.c.set(f, c);
                    f();
                    return true;
                }
            }
            return false;
        }
    }

    public final boolean a(boolean z) {
        boolean z2;
        synchronized (this) {
            this.c.C.a(z);
            z2 = z != this.b.o;
            this.b.b(z);
        }
        return z2;
    }
}
