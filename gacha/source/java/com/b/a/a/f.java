package com.b.a.a;

import b.a.a.a.a.c.a.b;
import b.a.a.a.a.c.a.c;
import b.a.a.a.a.c.a.e;
import java.io.File;
import java.util.List;

/* compiled from: AnswersRetryFilesSender */
class f implements b.a.a.a.a.d.f {
    private final p a;
    private final m b;

    public static f a(p pVar) {
        return new f(pVar, new m(new e(new l(new c(1000, 8), 0.1d), new b(5))));
    }

    f(p pVar, m mVar) {
        this.a = pVar;
        this.b = mVar;
    }

    public boolean a(List<File> list) {
        long nanoTime = System.nanoTime();
        if (!this.b.a(nanoTime)) {
            return false;
        }
        if (this.a.a(list)) {
            this.b.a();
            return true;
        }
        this.b.b(nanoTime);
        return false;
    }
}
