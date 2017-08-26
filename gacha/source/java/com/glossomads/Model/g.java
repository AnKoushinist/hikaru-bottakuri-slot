package com.glossomads.Model;

import com.glossomads.SugarUtil;

public class g {
    private String a;
    private Integer b = null;

    public g(String str) {
        this.a = str;
    }

    public void a(Integer num) {
        this.b = num;
    }

    public String a() {
        return this.a;
    }

    public boolean b() {
        boolean z = true;
        if (this.b == null) {
            return false;
        }
        if (this.b.intValue() != 1) {
            z = false;
        }
        return z;
    }

    public boolean c() {
        if (this.b != null && this.b.intValue() == 3) {
            return true;
        }
        return false;
    }

    public boolean d() {
        if (this.b != null && this.b.intValue() == 2) {
            return true;
        }
        return false;
    }

    public boolean a(String str) {
        if (SugarUtil.isEmptyValue(str)) {
            return false;
        }
        if ("v4vc".equals(str)) {
            return b();
        }
        if ("feed".equals(str)) {
            return d();
        }
        if ("interstitial".equals(str)) {
            return c();
        }
        return false;
    }
}
