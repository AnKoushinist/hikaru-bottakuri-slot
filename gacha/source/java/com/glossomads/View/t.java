package com.glossomads.View;

import com.glossomads.b.b;
import java.io.Serializable;

public class t implements Serializable {
    private String a;
    private com.glossomads.Model.a b;
    private a c;
    private int d;
    private int e;
    private b f;
    private boolean g;
    private int h = 17;

    public enum a {
        FULL_SCREEN,
        NATIVE_SCREEN
    }

    public t(String str, com.glossomads.Model.a aVar, a aVar2) {
        this.a = str;
        this.b = aVar;
        this.c = aVar2;
        this.d = 0;
        this.e = 0;
        this.g = false;
    }

    public String a() {
        return this.a;
    }

    public com.glossomads.Model.a b() {
        return this.b;
    }

    public a c() {
        return this.c;
    }

    public int d() {
        return this.d;
    }

    public int e() {
        return this.e;
    }

    public void a(int i) {
        this.d = i;
    }

    public void b(int i) {
        this.e = i;
    }

    public b f() {
        return this.f;
    }

    public void a(b bVar) {
        this.f = bVar;
    }

    public void c(int i) {
        this.h = i;
    }

    public boolean g() {
        return this.g;
    }

    public void a(boolean z) {
        this.g = z;
    }

    public boolean h() {
        if (a.FULL_SCREEN.equals(c())) {
            return true;
        }
        return false;
    }
}
