package com.glossomads.View;

import com.glossomads.View.r.b;

class s implements Runnable {
    final /* synthetic */ b a;

    s(b bVar) {
        this.a = bVar;
    }

    public void run() {
        this.a.b.e = this.a.b.e + this.a.b.d;
        if (this.a.b.g != null) {
            this.a.b.g.a(this.a.b.f());
        }
        if (this.a.b.e >= this.a.b.f) {
            this.a.b.b.shutdownNow();
        }
    }
}
