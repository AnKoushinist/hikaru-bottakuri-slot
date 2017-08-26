package jp.gmotech.smaad.video.ad.a;

import java.util.concurrent.Future;

public class i {
    private a a = null;
    private String b = String.valueOf(System.nanoTime());
    private Future c = null;
    private h d = null;
    private boolean e = false;

    public void a(Future future) {
        this.c = future;
    }

    public void a(a aVar) {
        this.a = aVar;
    }

    public void a(h hVar) {
        this.d = hVar;
    }

    public boolean a() {
        return this.e;
    }

    public String b() {
        return this.b;
    }

    public a c() {
        return this.a;
    }

    public void d() {
        this.d.a(this.a.a(), this.b, this.a.b(), this.a.c());
    }

    public void e() {
        this.d.b(this.a.a(), this.b, this.a.b(), this.a.c());
    }
}
