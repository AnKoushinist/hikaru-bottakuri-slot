package com.adcolony.sdk;

import android.os.StatFs;
import java.io.File;
import org.cocos2dx.lib.BuildConfig;

class x {
    private String a;
    private String b;
    private String c;
    private String d;
    private File e;
    private File f;
    private File g;

    x() {
    }

    boolean a() {
        bd.d.b((Object) "Configuring storage");
        aq a = n.a();
        this.a = c() + "/adc3/";
        this.b = this.a + "media/";
        this.e = new File(this.b);
        if (!this.e.isDirectory()) {
            this.e.delete();
            this.e.mkdirs();
        }
        if (!this.e.isDirectory()) {
            a.a(true);
            return false;
        } else if (a(this.b) < 2.097152E7d) {
            bd.e.b((Object) "Not enough memory available at media path, disabling AdColony.");
            a.a(true);
            return false;
        } else {
            this.c = c() + "/adc3/data/";
            this.f = new File(this.c);
            if (!this.f.isDirectory()) {
                this.f.delete();
            }
            this.f.mkdirs();
            this.d = this.a + "tmp/";
            this.g = new File(this.d);
            if (!this.g.isDirectory()) {
                this.g.delete();
                this.g.mkdirs();
            }
            return true;
        }
    }

    boolean b() {
        if (this.e == null || this.f == null || this.g == null) {
            return false;
        }
        if (!this.e.isDirectory()) {
            this.e.delete();
        }
        if (!this.f.isDirectory()) {
            this.f.delete();
        }
        if (!this.g.isDirectory()) {
            this.g.delete();
        }
        this.e.mkdirs();
        this.f.mkdirs();
        this.g.mkdirs();
        return true;
    }

    String c() {
        if (n.d()) {
            return n.c().getFilesDir().getAbsolutePath();
        }
        return BuildConfig.FLAVOR;
    }

    double a(String str) {
        try {
            StatFs statFs = new StatFs(str);
            return (double) (((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize()));
        } catch (RuntimeException e) {
            return 0.0d;
        }
    }

    String d() {
        return this.b;
    }

    String e() {
        return this.c;
    }

    String f() {
        return this.d;
    }

    String g() {
        return this.a;
    }
}
