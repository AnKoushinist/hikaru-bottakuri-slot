package com.jirbo.adcolony;

import java.io.File;
import java.io.IOException;

class f {
    static byte[] a = new byte[1024];
    String b;

    f(String str) {
        this.b = a.l.f.d + str;
    }

    x a() {
        return new x(this.b);
    }

    s b() {
        try {
            return new s(new w(this.b));
        } catch (IOException e) {
            return null;
        }
    }

    void a(String str) {
        x a = a();
        int length = str.length();
        for (int i = 0; i < length; i++) {
            a.a(str.charAt(i));
        }
        a.b();
    }

    void c() {
        new File(this.b).delete();
    }
}
