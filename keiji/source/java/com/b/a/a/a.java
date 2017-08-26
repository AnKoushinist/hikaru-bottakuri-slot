package com.b.a.a;

import a.a.a.a.a.g.q;
import a.a.a.a.a.g.t;
import a.a.a.a.c;
import a.a.a.a.i;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build.VERSION;
import java.io.File;

/* compiled from: Answers */
public class a extends i<Boolean> {
    q a;

    protected /* synthetic */ Object e() {
        return d();
    }

    public void a(a.a.a.a.a.b.j.a aVar) {
        if (this.a != null) {
            this.a.a(aVar.a());
        }
    }

    @SuppressLint({"NewApi"})
    protected boolean l_() {
        try {
            long j;
            Context E = E();
            PackageManager packageManager = E.getPackageManager();
            String packageName = E.getPackageName();
            PackageInfo packageInfo = packageManager.getPackageInfo(packageName, 0);
            String num = Integer.toString(packageInfo.versionCode);
            String str = packageInfo.versionName == null ? "0.0" : packageInfo.versionName;
            if (VERSION.SDK_INT >= 9) {
                j = packageInfo.firstInstallTime;
            } else {
                j = new File(packageManager.getApplicationInfo(packageName, 0).sourceDir).lastModified();
            }
            this.a = q.a(this, E, D(), num, str, j);
            this.a.b();
            return true;
        } catch (Throwable e) {
            c.h().e("Answers", "Error retrieving app properties", e);
            return false;
        }
    }

    protected Boolean d() {
        try {
            t b = q.a().b();
            if (b == null) {
                c.h().e("Answers", "Failed to retrieve settings");
                return Boolean.valueOf(false);
            } else if (b.d.d) {
                c.h().a("Answers", "Analytics collection enabled");
                this.a.a(b.e, f());
                return Boolean.valueOf(true);
            } else {
                c.h().a("Answers", "Analytics collection disabled");
                this.a.c();
                return Boolean.valueOf(false);
            }
        } catch (Throwable e) {
            c.h().e("Answers", "Error dealing with settings", e);
            return Boolean.valueOf(false);
        }
    }

    public String b() {
        return "com.crashlytics.sdk.android:answers";
    }

    public String a() {
        return "1.3.6.97";
    }

    String f() {
        return a.a.a.a.a.b.i.b(E(), "com.crashlytics.ApiEndpoint");
    }
}
