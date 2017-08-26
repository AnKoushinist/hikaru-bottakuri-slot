package com.tapjoy.internal;

import com.tapjoy.internal.hj.a;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class gh implements ck {
    public final fz a;
    Set b = null;
    private final Map c = Collections.synchronizedMap(new HashMap());
    private final Map d = cx.a();

    public gh(fz fzVar) {
        this.a = fzVar;
    }

    private void a(cf cfVar, a aVar) {
        if (cfVar instanceof hj) {
            if (aVar.b != null) {
                Iterable iterable = aVar.b;
                synchronized (this) {
                    Set hashSet;
                    if (iterable instanceof Collection) {
                        hashSet = new HashSet(cv.a(iterable));
                    } else {
                        hashSet = cy.a(iterable.iterator());
                    }
                    this.b = hashSet;
                }
            }
            hj hjVar = (hj) cfVar;
            String str = hjVar.c;
            boolean z = hjVar.d;
            this.d.remove(str);
            if (!z) {
                this.c.put(str, aVar.a);
            }
            gg ggVar = aVar.a;
            ga gaVar = this.a.p;
            if (ggVar instanceof gf) {
                fw.a("No content for \"{}\"", str);
                gaVar.a(str);
                return;
            }
            fw.a("New content for \"{}\" is ready", str);
            if (z) {
                ggVar.a(gaVar, new ev());
                return;
            } else {
                gaVar.b(str);
                return;
            }
        }
        throw new IllegalStateException(cfVar.getClass().getName());
    }

    public final void a(cf cfVar) {
        a(cfVar, new a(new gf(), null));
    }
}
