package com.jirbo.adcolony;

import android.os.StatFs;
import java.io.File;
import org.cocos2dx.lib.BuildConfig;

public class ADCStorage {
    d a;
    String b;
    String c;
    String d;
    File e;
    File f;

    ADCStorage(d dVar) {
        this.a = dVar;
    }

    void a() {
        l.a.b((Object) "Configuring storage");
        l.b.b((Object) "Using internal storage:");
        this.b = c() + "/adc/";
        this.c = this.b + "media/";
        l.a.b(this.c);
        this.e = new File(this.c);
        if (!this.e.isDirectory()) {
            this.e.delete();
            this.e.mkdirs();
        }
        if (!this.e.isDirectory()) {
            a.a("Cannot create media folder.");
        } else if (a(this.c) < 2.097152E7d) {
            a.a("Not enough space to store temporary files (" + a(this.c) + " bytes available).");
        } else {
            this.d = c() + "/adc/data/";
            if (a.n == 0) {
                this.d = this.b + "data/";
            }
            l.a.a("Internal data path: ").b(this.d);
            this.f = new File(this.d);
            if (!this.f.isDirectory()) {
                this.f.delete();
            }
            this.f.mkdirs();
            f fVar = new f("iap_cache.txt");
            fVar.c();
            k.a(fVar, a.aj);
        }
    }

    void b() {
        if (this.e != null && this.f != null) {
            if (!this.e.isDirectory()) {
                this.e.delete();
            }
            if (!this.f.isDirectory()) {
                this.f.delete();
            }
            this.e.mkdirs();
            this.f.mkdirs();
        }
    }

    String c() {
        if (a.P == null) {
            return BuildConfig.FLAVOR;
        }
        return AdColony.activity().getFilesDir().getAbsolutePath();
    }

    String d() {
        if (a.P == null) {
            return BuildConfig.FLAVOR;
        }
        return AdColony.activity().getExternalFilesDir(null).getAbsolutePath();
    }

    double a(String str) {
        try {
            StatFs statFs = new StatFs(str);
            return (double) (((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize()));
        } catch (RuntimeException e) {
            return 0.0d;
        }
    }
}
