package com.tapjoy.internal;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.graphics.Rect;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;
import com.tapjoy.TapjoyConstants;
import com.tapjoy.internal.ek.a;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;
import java.util.TimeZone;
import java.util.UUID;
import org.cocos2dx.lib.BuildConfig;
import twitter4j.TwitterResponse;

public final class gc {
    public static final String a = UUID.randomUUID().toString();
    private static gc d;
    final a b = new a();
    final gj c;
    private final ed.a e = new ed.a();
    private final dx.a f = new dx.a();
    private final Context g;

    public static synchronized gc a(Context context) {
        gc gcVar;
        synchronized (gc.class) {
            if (d == null) {
                d = new gc(context, gj.a(context));
            }
            gcVar = d;
        }
        return gcVar;
    }

    private gc(Context context, gj gjVar) {
        Integer valueOf;
        gn.a();
        Context applicationContext = context.getApplicationContext();
        this.g = applicationContext;
        String string = Secure.getString(applicationContext.getContentResolver(), TapjoyConstants.TJC_ANDROID_ID);
        if ("9774d56d682e549c".equals(string)) {
            string = null;
        } else {
            string = ct.b(string);
        }
        if (string == null) {
            File a = w.a(applicationContext);
            string = aa.a(new File(fz.c(applicationContext), "deviceid"), a != null ? new File(a, ".io.5rocks") : null);
        }
        this.e.d = string;
        string = ab.a(applicationContext);
        if (string != null) {
            this.e.c = string.replace(":", BuildConfig.FLAVOR).toLowerCase(Locale.US);
        }
        PackageManager packageManager = applicationContext.getPackageManager();
        TelephonyManager telephonyManager = (TelephonyManager) applicationContext.getSystemService("phone");
        if (telephonyManager != null) {
            String simCountryIso = telephonyManager.getSimCountryIso();
            if (!ct.c(simCountryIso)) {
                this.e.q = simCountryIso.toUpperCase(Locale.US);
            }
            simCountryIso = telephonyManager.getNetworkCountryIso();
            if (!ct.c(simCountryIso)) {
                this.e.r = simCountryIso.toUpperCase(Locale.US);
            }
            if (ez.b().b("analytics_gather_imei") && packageManager.checkPermission("android.permission.READ_PHONE_STATE", applicationContext.getPackageName()) == 0) {
                try {
                    string = telephonyManager.getDeviceId();
                    if (!ct.c(string)) {
                        this.e.s = string;
                    }
                } catch (SecurityException e) {
                } catch (RuntimeException e2) {
                }
            }
        }
        String packageName = applicationContext.getPackageName();
        this.e.n = packageName;
        ed.a aVar = this.e;
        Signature[] e3 = ae.e(packageManager, packageName);
        if (e3 == null || e3.length <= 0) {
            string = null;
        } else {
            string = Base64.encodeToString(cm.a(e3[0].toByteArray()), 2);
        }
        aVar.o = ct.a(string);
        this.f.c = ae.a(packageManager, packageName);
        this.f.d = Integer.valueOf(ae.b(packageManager, packageName));
        string = packageManager.getInstallerPackageName(packageName);
        if (!ct.c(string)) {
            this.f.f = string;
        }
        string = a(packageManager, packageName);
        if (!ct.c(string)) {
            this.f.g = string;
        }
        a();
        this.c = gjVar;
        string = this.c.c.a();
        if (string != null && string.length() > 0) {
            this.e.p = string + " 11.10.0/Android";
        }
        string = this.c.b();
        if (string != null) {
            this.b.d = string;
        }
        a aVar2 = this.b;
        gj gjVar2 = this.c;
        long j = gjVar2.b.getLong("it", 0);
        if (j == 0) {
            applicationContext = gjVar2.a;
            j = ae.c(applicationContext.getPackageManager(), applicationContext.getPackageName());
            if (j == 0) {
                j = fz.d(gjVar2.a).lastModified();
                if (j == 0) {
                    Context context2 = gjVar2.a;
                    j = new File(ae.d(context2.getPackageManager(), context2.getPackageName())).lastModified();
                    if (j == 0) {
                        j = System.currentTimeMillis();
                    }
                }
            }
            gjVar2.b.edit().putLong("it", j).commit();
        }
        aVar2.c = Long.valueOf(j);
        int b = this.c.f.b();
        this.b.e = Integer.valueOf(a(7, b));
        this.b.f = Integer.valueOf(a(30, b));
        b = this.c.h.b();
        if (b > 0) {
            this.b.h = Integer.valueOf(b);
        }
        j = this.c.i.a();
        if (j > 0) {
            this.b.i = Long.valueOf(j);
        }
        j = this.c.j.a();
        if (j > 0) {
            this.b.j = Long.valueOf(j);
        }
        j = this.c.k.a();
        if (j > 0) {
            this.b.k = Long.valueOf(j);
        }
        string = this.c.l.a();
        if (string != null) {
            this.b.l = string;
        }
        b = this.c.m.b();
        if (b > 0) {
            this.b.m = Integer.valueOf(b);
        }
        double a2 = this.c.n.a();
        if (a2 != 0.0d) {
            this.b.n = Double.valueOf(a2);
        }
        j = this.c.o.a();
        if (j > 0) {
            this.b.o = Long.valueOf(j);
        }
        a2 = this.c.p.a();
        if (a2 != 0.0d) {
            this.b.p = Double.valueOf(a2);
        }
        string = this.c.g.a();
        if (string != null) {
            try {
                ei eiVar = (ei) ei.c.a(Base64.decode(string, 2));
                this.b.g.clear();
                this.b.g.addAll(eiVar.d);
            } catch (IllegalArgumentException e4) {
                this.c.g.c();
            } catch (IOException e5) {
                this.c.g.c();
            }
        }
        this.f.e = this.c.q.a();
        this.b.s = this.c.r.a();
        b = this.c.s.a().intValue();
        a aVar3 = this.b;
        if (b != -1) {
            valueOf = Integer.valueOf(b);
        } else {
            valueOf = null;
        }
        aVar3.t = valueOf;
        b = this.c.t.a().intValue();
        aVar3 = this.b;
        if (b != -1) {
            valueOf = Integer.valueOf(b);
        } else {
            valueOf = null;
        }
        aVar3.u = valueOf;
        this.b.v = this.c.u.a();
        this.b.w = this.c.v.a();
        this.b.x = this.c.w.a();
        this.b.y = this.c.x.a();
        this.b.z = this.c.y.a();
        string = this.c.z.a();
        if (string != null) {
            try {
                ej ejVar = (ej) ej.c.a(Base64.decode(string, 2));
                this.b.A.clear();
                this.b.A.addAll(ejVar.d);
            } catch (IllegalArgumentException e6) {
                this.c.z.c();
            } catch (IOException e7) {
                this.c.z.c();
            }
        }
        string = this.c.A.a();
        boolean booleanValue = this.c.B.a().booleanValue();
        if (string != null) {
            this.b.q = string;
            this.b.r = Boolean.valueOf(booleanValue);
        } else {
            this.b.q = null;
            this.b.r = null;
        }
        this.b.B = this.c.C.a();
        new Thread(new Runnable(this) {
            final /* synthetic */ gc a;

            {
                this.a = r1;
            }

            public final void run() {
                fd fdVar = new fd();
                boolean a = fdVar.a(this.a.g);
                synchronized (this.a) {
                    if (a) {
                        String a2 = ct.a(fdVar.a);
                        boolean z = fdVar.b;
                        String a3 = this.a.c.A.a();
                        this.a.b.q = a2;
                        this.a.b.r = Boolean.valueOf(z);
                        this.a.c.A.a(a2);
                        this.a.c.B.a(z);
                        gn.a(a2, z);
                        if (!(ct.c(a3) || a2.equals(a3))) {
                            this.a.c.a(false);
                        }
                    } else {
                        this.a.b.q = null;
                        this.a.b.r = null;
                        this.a.c.A.a(null);
                        this.a.c.B.a(false);
                        gn.a(null, false);
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
                Activity a = fr.a();
                if (a != null) {
                    Window window = a.getWindow();
                    if (window != null) {
                        int i = displayMetrics.heightPixels;
                        Rect rect = new Rect();
                        window.getDecorView().getWindowVisibleDisplayFrame(rect);
                        displayMetrics.heightPixels = i - rect.top;
                    }
                }
                this.e.i = Integer.valueOf(displayMetrics.densityDpi);
                this.e.j = Integer.valueOf(displayMetrics.widthPixels);
                this.e.k = Integer.valueOf(displayMetrics.heightPixels);
            } catch (Exception e) {
            }
        }
    }

    public final ee b() {
        ee eeVar;
        synchronized (this) {
            this.e.l = Locale.getDefault().toString();
            this.e.m = TimeZone.getDefault().getID();
            Object obj = null;
            long currentTimeMillis = System.currentTimeMillis() - 259200000;
            Iterator it = this.b.g.iterator();
            while (it.hasNext()) {
                Object obj2;
                if (((eh) it.next()).g.longValue() <= currentTimeMillis) {
                    it.remove();
                    obj2 = 1;
                } else {
                    obj2 = obj;
                }
                obj = obj2;
            }
            if (obj != null) {
                g();
            }
            eeVar = new ee(this.e.b(), this.f.b(), this.b.b());
        }
        return eeVar;
    }

    final String c() {
        String a;
        synchronized (this) {
            a = this.c.d.a();
        }
        return a;
    }

    public final ef d() {
        ef efVar = null;
        int i = 1;
        synchronized (this) {
            Calendar instance = Calendar.getInstance();
            int i2 = instance.get(5) + (((instance.get(1) * 10000) + (instance.get(2) * 100)) + 100);
            int intValue = this.c.e.a().intValue();
            if (intValue != i2) {
                if (intValue == 0) {
                    this.b.e = Integer.valueOf(1);
                    this.b.f = Integer.valueOf(1);
                    efVar = new ef("fq7_0_1", "fq30_0_1", null);
                } else {
                    long timeInMillis;
                    int i3;
                    int intValue2 = this.c.f.a().intValue();
                    int a = a(7, intValue2);
                    int a2 = a(30, intValue2);
                    Calendar instance2 = Calendar.getInstance();
                    instance2.set(intValue / 10000, ((intValue / 100) % 100) - 1, intValue % 100);
                    int signum = Integer.signum(instance.get(1) - instance2.get(1));
                    Calendar calendar;
                    switch (signum) {
                        case jp.co.geniee.gnsrewardadapter.BuildConfig.VERSION_CODE /*-1*/:
                            calendar = (Calendar) instance2.clone();
                            calendar.set(instance.get(1), instance.get(2), instance.get(5));
                            instance = calendar;
                            timeInMillis = instance2.getTimeInMillis();
                            break;
                        case TwitterResponse.READ /*1*/:
                            calendar = (Calendar) instance.clone();
                            calendar.set(instance2.get(1), instance2.get(2), instance2.get(5));
                            long timeInMillis2 = instance.getTimeInMillis();
                            instance = calendar;
                            timeInMillis = timeInMillis2;
                            break;
                        default:
                            i3 = instance.get(6) - instance2.get(6);
                            break;
                    }
                    intValue = 0;
                    while (instance.getTimeInMillis() < timeInMillis) {
                        instance.add(5, 1);
                        intValue++;
                    }
                    i3 = signum > 0 ? intValue : -intValue;
                    if (Math.abs(i3) >= 30) {
                        i3 = 0;
                    } else if (i3 >= 0) {
                        i3 = intValue2 << i3;
                    } else {
                        i3 = intValue2 >> (-i3);
                    }
                    i3 |= 1;
                    int a3 = a(7, i3);
                    intValue = a(30, i3);
                    this.b.e = Integer.valueOf(a3);
                    this.b.f = Integer.valueOf(intValue);
                    int i4 = i3;
                    efVar = new ef("fq7_" + a + "_" + a3, "fq30_" + a2 + "_" + intValue, null);
                    i = i4;
                }
                this.c.e.a(i2);
                this.c.f.a(i);
            }
        }
        return efVar;
    }

    private static int a(int i, int i2) {
        return Integer.bitCount(((1 << i) - 1) & i2);
    }

    final boolean a(String str, long j, boolean z) {
        synchronized (this) {
            int size = this.b.g.size();
            int i = 0;
            while (i < size) {
                eh ehVar = (eh) this.b.g.get(i);
                if (!ehVar.f.equals(str)) {
                    i++;
                } else if (z) {
                    eh.a b = ehVar.b();
                    b.d = Long.valueOf(j);
                    this.b.g.set(i, b.b());
                    return true;
                } else {
                    return false;
                }
            }
            this.b.g.add(new eh(str, Long.valueOf(j)));
            g();
            return true;
        }
    }

    private void g() {
        this.c.g.a(Base64.encodeToString(ei.c.b(new ei(this.b.g)), 2));
    }

    public final boolean a(String str) {
        boolean z = true;
        synchronized (this) {
            this.c.q.a(str);
            if (str != null) {
                if (cr.a(this.f.e, str)) {
                    z = false;
                }
                this.f.e = str;
            } else {
                if (this.f.e == null) {
                    z = false;
                }
                this.f.e = null;
            }
        }
        return z;
    }

    public final boolean b(String str) {
        boolean z;
        synchronized (this) {
            this.c.r.a(str);
            z = !cr.a(this.b.s, str);
            if (z) {
                this.b.s = str;
            }
        }
        return z;
    }

    public final boolean a(Integer num) {
        boolean z;
        synchronized (this) {
            this.c.s.a(num);
            z = !cr.a(this.b.t, num);
            if (z) {
                this.b.t = num;
            }
        }
        return z;
    }

    public final boolean b(Integer num) {
        boolean z;
        synchronized (this) {
            this.c.t.a(num);
            z = !cr.a(this.b.u, num);
            if (z) {
                this.b.u = num;
            }
        }
        return z;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean a(int r4, java.lang.String r5) {
        /*
        r3 = this;
        r1 = 1;
        r0 = 0;
        monitor-enter(r3);
        switch(r4) {
            case 1: goto L_0x0008;
            case 2: goto L_0x0024;
            case 3: goto L_0x003d;
            case 4: goto L_0x0056;
            case 5: goto L_0x006f;
            default: goto L_0x0006;
        };
    L_0x0006:
        monitor-exit(r3);	 Catch:{ all -> 0x0021 }
        return r0;
    L_0x0008:
        r2 = r3.c;	 Catch:{ all -> 0x0021 }
        r2 = r2.u;	 Catch:{ all -> 0x0021 }
        r2.a(r5);	 Catch:{ all -> 0x0021 }
        r2 = r3.b;	 Catch:{ all -> 0x0021 }
        r2 = r2.v;	 Catch:{ all -> 0x0021 }
        r2 = com.tapjoy.internal.cr.a(r2, r5);	 Catch:{ all -> 0x0021 }
        if (r2 != 0) goto L_0x001a;
    L_0x0019:
        r0 = r1;
    L_0x001a:
        if (r0 == 0) goto L_0x0006;
    L_0x001c:
        r1 = r3.b;	 Catch:{ all -> 0x0021 }
        r1.v = r5;	 Catch:{ all -> 0x0021 }
        goto L_0x0006;
    L_0x0021:
        r0 = move-exception;
        monitor-exit(r3);	 Catch:{ all -> 0x0021 }
        throw r0;
    L_0x0024:
        r2 = r3.c;	 Catch:{ all -> 0x0021 }
        r2 = r2.v;	 Catch:{ all -> 0x0021 }
        r2.a(r5);	 Catch:{ all -> 0x0021 }
        r2 = r3.b;	 Catch:{ all -> 0x0021 }
        r2 = r2.w;	 Catch:{ all -> 0x0021 }
        r2 = com.tapjoy.internal.cr.a(r2, r5);	 Catch:{ all -> 0x0021 }
        if (r2 != 0) goto L_0x0036;
    L_0x0035:
        r0 = r1;
    L_0x0036:
        if (r0 == 0) goto L_0x0006;
    L_0x0038:
        r1 = r3.b;	 Catch:{ all -> 0x0021 }
        r1.w = r5;	 Catch:{ all -> 0x0021 }
        goto L_0x0006;
    L_0x003d:
        r2 = r3.c;	 Catch:{ all -> 0x0021 }
        r2 = r2.w;	 Catch:{ all -> 0x0021 }
        r2.a(r5);	 Catch:{ all -> 0x0021 }
        r2 = r3.b;	 Catch:{ all -> 0x0021 }
        r2 = r2.x;	 Catch:{ all -> 0x0021 }
        r2 = com.tapjoy.internal.cr.a(r2, r5);	 Catch:{ all -> 0x0021 }
        if (r2 != 0) goto L_0x004f;
    L_0x004e:
        r0 = r1;
    L_0x004f:
        if (r0 == 0) goto L_0x0006;
    L_0x0051:
        r1 = r3.b;	 Catch:{ all -> 0x0021 }
        r1.x = r5;	 Catch:{ all -> 0x0021 }
        goto L_0x0006;
    L_0x0056:
        r2 = r3.c;	 Catch:{ all -> 0x0021 }
        r2 = r2.x;	 Catch:{ all -> 0x0021 }
        r2.a(r5);	 Catch:{ all -> 0x0021 }
        r2 = r3.b;	 Catch:{ all -> 0x0021 }
        r2 = r2.y;	 Catch:{ all -> 0x0021 }
        r2 = com.tapjoy.internal.cr.a(r2, r5);	 Catch:{ all -> 0x0021 }
        if (r2 != 0) goto L_0x0068;
    L_0x0067:
        r0 = r1;
    L_0x0068:
        if (r0 == 0) goto L_0x0006;
    L_0x006a:
        r1 = r3.b;	 Catch:{ all -> 0x0021 }
        r1.y = r5;	 Catch:{ all -> 0x0021 }
        goto L_0x0006;
    L_0x006f:
        r2 = r3.c;	 Catch:{ all -> 0x0021 }
        r2 = r2.y;	 Catch:{ all -> 0x0021 }
        r2.a(r5);	 Catch:{ all -> 0x0021 }
        r2 = r3.b;	 Catch:{ all -> 0x0021 }
        r2 = r2.z;	 Catch:{ all -> 0x0021 }
        r2 = com.tapjoy.internal.cr.a(r2, r5);	 Catch:{ all -> 0x0021 }
        if (r2 != 0) goto L_0x0081;
    L_0x0080:
        r0 = r1;
    L_0x0081:
        if (r0 == 0) goto L_0x0006;
    L_0x0083:
        r1 = r3.b;	 Catch:{ all -> 0x0021 }
        r1.z = r5;	 Catch:{ all -> 0x0021 }
        goto L_0x0006;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tapjoy.internal.gc.a(int, java.lang.String):boolean");
    }

    public final Set e() {
        Set hashSet;
        synchronized (this) {
            hashSet = new HashSet(this.b.A);
        }
        return hashSet;
    }

    public final boolean c(String str) {
        synchronized (this) {
            for (int size = this.b.g.size() - 1; size >= 0; size--) {
                eh ehVar = (eh) this.b.g.get(size);
                if (ehVar.f.equals(str)) {
                    eh.a b = ehVar.b();
                    b.e = Long.valueOf(System.currentTimeMillis());
                    this.b.g.set(size, b.b());
                    g();
                    return true;
                }
            }
            return false;
        }
    }

    public final boolean f() {
        return ((Boolean) cr.b(this.b.B, ek.r)).booleanValue();
    }

    public final boolean a(boolean z) {
        boolean z2;
        synchronized (this) {
            this.c.C.a(z);
            z2 = z != ((Boolean) cr.b(this.b.B, ek.r)).booleanValue();
            this.b.B = Boolean.valueOf(z);
        }
        return z2;
    }
}
