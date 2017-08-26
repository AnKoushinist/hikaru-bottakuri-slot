package com.tapjoy.internal;

import com.tapjoy.internal.ff.a;

public final class ev {
    public volatile a a;
    public int b;
    public volatile a c;
    public volatile a d;

    public final void a() {
        a(16);
        a aVar = this.c;
        if (aVar != null) {
            this.c = null;
            aVar.b().c();
        }
    }

    public final synchronized void a(int i) {
        a aVar = this.a;
        if (aVar != null && this.b < i) {
            this.b |= i;
            aVar.a("state", Integer.valueOf(this.b)).b().c();
        }
    }
}
