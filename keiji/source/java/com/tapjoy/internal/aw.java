package com.tapjoy.internal;

import java.util.Iterator;
import java.util.LinkedHashMap;

public final class aw extends av {
    private final LinkedHashMap a = new LinkedHashMap(0, 0.75f, true);
    private int b = 10;

    private void a() {
        int size = this.a.size() - this.b;
        if (size > 0) {
            Iterator it = this.a.entrySet().iterator();
            while (size > 0 && it.hasNext()) {
                size--;
                it.next();
                it.remove();
            }
        }
    }

    public final void a(Object obj, Object obj2) {
        super.a(obj, obj2);
        a();
    }

    protected final at a(Object obj, boolean z) {
        ar arVar = (ar) this.a.get(obj);
        if (arVar != null || !z) {
            return arVar;
        }
        at arVar2 = new ar(obj);
        this.a.put(obj, arVar2);
        a();
        return arVar2;
    }
}
