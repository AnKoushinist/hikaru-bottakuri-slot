package com.tapjoy.internal;

import java.util.Iterator;
import java.util.LinkedHashMap;

public final class av extends au {
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

    protected final as a(Object obj, boolean z) {
        aq aqVar = (aq) this.a.get(obj);
        if (aqVar != null || !z) {
            return aqVar;
        }
        as aqVar2 = new aq(obj);
        this.a.put(obj, aqVar2);
        a();
        return aqVar2;
    }
}
