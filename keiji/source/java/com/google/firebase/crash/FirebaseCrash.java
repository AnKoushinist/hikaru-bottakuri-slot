package com.google.firebase.crash;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.Keep;
import android.util.Log;
import com.google.android.gms.b.b;
import com.google.android.gms.internal.ov;
import com.google.android.gms.internal.ow;
import com.google.android.gms.internal.oy;
import com.google.android.gms.internal.oz;
import com.google.android.gms.internal.pa;
import com.google.android.gms.internal.pb;
import com.google.android.gms.internal.zzbkp;
import com.google.firebase.a;
import com.google.firebase.iid.c;

public class FirebaseCrash {
    private static final String a = FirebaseCrash.class.getSimpleName();
    private static volatile FirebaseCrash e;
    private boolean b;
    private oy c;
    private ov d;

    FirebaseCrash(a aVar, boolean z) {
        this.b = z;
        Context a = aVar.a();
        if (a == null) {
            Log.w(a, "Application context is missing, disabling api");
            this.b = false;
        }
        if (this.b) {
            try {
                zzbkp com_google_android_gms_internal_zzbkp = new zzbkp(aVar.c().b(), aVar.c().a());
                oz.a().a(a);
                this.c = oz.a().b();
                this.c.a(b.a(a), com_google_android_gms_internal_zzbkp);
                this.d = new ov(a);
                f();
                String str = a;
                String str2 = "FirebaseCrash reporting initialized ";
                String valueOf = String.valueOf(oz.a().toString());
                Log.i(str, valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
                return;
            } catch (Exception e) {
                str = a;
                str2 = "Failed to initialize crash reporting: ";
                valueOf = String.valueOf(e.getMessage());
                Log.e(str, valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
                this.b = false;
                return;
            }
        }
        Log.i(a, "Crash reporting is disabled");
    }

    public static FirebaseCrash a() {
        if (e == null) {
            synchronized (FirebaseCrash.class) {
                if (e == null) {
                    e = getInstance(a.d());
                }
            }
        }
        return e;
    }

    public static void a(String str, long j, Bundle bundle) {
        try {
            a().b(str, j, bundle);
        } catch (ow e) {
            Log.v(a, e.getMessage());
        }
    }

    private void b() {
        if (d()) {
            this.d.a();
            return;
        }
        throw new ow("Firebase Crash Reporting is disabled.");
    }

    private oy c() {
        return this.c;
    }

    private boolean d() {
        return this.b;
    }

    private static boolean e() {
        return Looper.getMainLooper().getThread().getId() == Thread.currentThread().getId();
    }

    private void f() {
        if (e()) {
            Thread.setDefaultUncaughtExceptionHandler(new pa(Thread.getDefaultUncaughtExceptionHandler(), this));
            return;
        }
        throw new RuntimeException("FirebaseCrash reporting may only be initialized on the main thread (preferably in your app's Application.onCreate)");
    }

    private String g() {
        return c.a().b();
    }

    @Keep
    @Deprecated
    public static FirebaseCrash getInstance(a aVar) {
        pb.a(aVar.a());
        FirebaseCrash firebaseCrash = new FirebaseCrash(aVar, ((Boolean) pb.a.c()).booleanValue());
        synchronized (FirebaseCrash.class) {
            if (e == null) {
                e = firebaseCrash;
                try {
                    e.b();
                } catch (ow e) {
                    Log.d(a, "Cannot register Firebase Analytics listener since Firebase Crash Reporting is not enabled");
                }
            }
        }
        return firebaseCrash;
    }

    public void a(Throwable th) {
        if (d()) {
            oy c = c();
            if (c != null && th != null) {
                try {
                    this.d.a(true, System.currentTimeMillis());
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    c.b(g());
                    c.b(b.a(th));
                    return;
                } catch (Throwable e2) {
                    Log.e(a, "report remoting failed", e2);
                    return;
                }
            }
            return;
        }
        throw new ow("Firebase Crash Reporting is disabled.");
    }

    public void b(String str, long j, Bundle bundle) {
        if (d()) {
            oy c = c();
            if (c != null && str != null) {
                try {
                    c.a(str, j, bundle);
                    return;
                } catch (Throwable e) {
                    Log.e(a, "log remoting failed", e);
                    return;
                }
            }
            return;
        }
        throw new ow("Firebase Crash Reporting is disabled.");
    }
}
