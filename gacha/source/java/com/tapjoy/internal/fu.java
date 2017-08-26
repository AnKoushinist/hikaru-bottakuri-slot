package com.tapjoy.internal;

import com.tapjoy.internal.gq.a;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class fu implements cj {
    public final fm a;
    Set b = null;
    private final Map c = Collections.synchronizedMap(new HashMap());
    private final Map d = cv.a();

    public fu(fm fmVar) {
        this.a = fmVar;
    }

    private void a(ce ceVar, a aVar) {
        if (ceVar instanceof gq) {
            if (aVar.b != null) {
                Iterable iterable = aVar.b;
                synchronized (this) {
                    Set hashSet;
                    if (iterable instanceof Collection) {
                        hashSet = new HashSet(ct.a(iterable));
                    } else {
                        hashSet = cw.a(iterable.iterator());
                    }
                    this.b = hashSet;
                }
            }
            gq gqVar = (gq) ceVar;
            String str = gqVar.c;
            boolean z = gqVar.d;
            this.d.remove(str);
            if (!z) {
                this.c.put(str, aVar.a);
            }
            ft ftVar = aVar.a;
            fn fnVar = this.a.p;
            if (ftVar instanceof fs) {
                fj.a("No content for \"{}\"", str);
                fnVar.a(str);
                return;
            }
            fj.a("New content for \"{}\" is ready", str);
            if (z) {
                ftVar.a(fnVar);
                return;
            } else {
                fnVar.b(str);
                return;
            }
        }
        throw new IllegalStateException(ceVar.getClass().getName());
    }

    public final void a(ce ceVar) {
        a(ceVar, new a(new fs(), null));
    }
}
