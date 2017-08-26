package com.b.a.c;

import a.a.a.a.a.f.a;
import a.a.a.a.c;
import java.io.File;

/* compiled from: CrashlyticsFileMarker */
class h {
    private final String a;
    private final a b;

    public h(String str, a aVar) {
        this.a = str;
        this.b = aVar;
    }

    public boolean a() {
        boolean z = false;
        try {
            z = d().createNewFile();
        } catch (Throwable e) {
            c.h().e("CrashlyticsCore", "Error creating marker: " + this.a, e);
        }
        return z;
    }

    public boolean b() {
        return d().exists();
    }

    public boolean c() {
        return d().delete();
    }

    private File d() {
        return new File(this.b.a(), this.a);
    }
}
