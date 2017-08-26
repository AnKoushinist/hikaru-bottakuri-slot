package com.glossomads.View;

import com.glossomads.SugarUtil;
import com.glossomads.View.l.a;

public class f {
    private String a;
    private int b;
    private boolean c;
    private boolean d;
    private boolean e;
    private a f;

    public f() {
        a();
    }

    public void a(com.glossomads.Model.a aVar) {
        if (!aVar.l().equals(this.a)) {
            a();
        }
        this.a = aVar.l();
    }

    public void a() {
        this.f = a.PREPARING;
        this.b = 0;
        this.c = false;
        this.a = null;
        this.d = false;
        this.e = false;
    }

    public void a(a aVar, int i, boolean z) {
        this.f = aVar;
        this.b = i;
        this.c = z;
    }

    public boolean a(String str) {
        if (SugarUtil.isEmptyValue(this.a) || !this.a.equals(str)) {
            return false;
        }
        return this.c;
    }

    public int b(String str) {
        if (SugarUtil.isEmptyValue(this.a) || !this.a.equals(str)) {
            return 0;
        }
        return this.b;
    }

    public a c(String str) {
        if (SugarUtil.isEmptyValue(this.a) || !this.a.equals(str)) {
            return a.PREPARING;
        }
        return this.f;
    }

    public boolean d(String str) {
        if (SugarUtil.isEmptyValue(this.a) || !this.a.equals(str)) {
            return false;
        }
        return this.d;
    }

    public void a(boolean z) {
        this.d = z;
    }

    public boolean e(String str) {
        if (SugarUtil.isEmptyValue(this.a) || !this.a.equals(str)) {
            return false;
        }
        return this.e;
    }

    public void b(boolean z) {
        this.e = z;
    }
}
