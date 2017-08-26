package com.jirbo.adcolony;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

class x extends ae {
    static final int a = 1024;
    String b;
    OutputStream c;
    byte[] d;
    int e;
    int f;
    int g;

    x(String str) {
        this.d = new byte[a];
        this.e = 0;
        this.b = str;
        if (a.n != 0) {
            this.g = 23;
            this.f = this.g;
        }
        try {
            if (!(a.l == null || a.l.f == null)) {
                a.l.f.b();
            }
            this.c = new FileOutputStream(str);
        } catch (IOException e) {
            a(e);
        }
    }

    x(String str, OutputStream outputStream) {
        this.d = new byte[a];
        this.e = 0;
        this.b = str;
        this.c = outputStream;
    }

    void a(char c) {
        this.d[this.e] = (byte) (this.f ^ c);
        this.f += this.g;
        int i = this.e + 1;
        this.e = i;
        if (i == a) {
            a();
        }
    }

    void a() {
        if (this.e > 0 && this.c != null) {
            try {
                this.c.write(this.d, 0, this.e);
                this.e = 0;
                this.c.flush();
            } catch (IOException e) {
                this.e = 0;
                a(e);
            }
        }
    }

    void b() {
        a();
        try {
            if (this.c != null) {
                this.c.close();
                this.c = null;
            }
        } catch (IOException e) {
            this.c = null;
            a(e);
        }
    }

    void a(IOException iOException) {
        l.d.a("Error writing \"").a(this.b).b((Object) "\":");
        l.d.b(iOException.toString());
        b();
    }

    public static void a(String[] strArr) {
        x xVar = new x("test.txt");
        xVar.b("A king who was mad at the time");
        xVar.b("Declared limerick writing a crime");
        xVar.i += 2;
        xVar.b("So late in the night");
        xVar.b("All the poets would write");
        xVar.i -= 2;
        xVar.b("Verses without any rhyme or meter");
        xVar.d();
        xVar.i += 4;
        xVar.b("David\nGerrold");
        xVar.i += 2;
        xVar.b(4.0d);
        xVar.i += 2;
        xVar.b(0.0d);
        xVar.i += 2;
        xVar.b(-100023.0d);
        xVar.i += 2;
        xVar.c(-6);
        xVar.i += 2;
        xVar.c(0);
        xVar.i += 2;
        xVar.c(234);
        xVar.i += 2;
        xVar.c(Long.MIN_VALUE);
        xVar.i += 2;
        xVar.b(true);
        xVar.i += 2;
        xVar.b(false);
        xVar.i += 2;
        xVar.b();
    }
}
