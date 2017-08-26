package com.tapjoy.internal;

import java.util.AbstractMap;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

public final class bd extends AbstractMap {
    private final HashMap a = new HashMap();
    private final cb b = new cb();

    public final int size() {
        b();
        return this.a.size();
    }

    public final void clear() {
        this.a.clear();
        do {
        } while (this.b.a() != null);
    }

    public final boolean containsKey(Object obj) {
        b();
        return this.a.containsKey(obj);
    }

    public final boolean containsValue(Object obj) {
        b();
        for (ca caVar : this.a.values()) {
            if (obj.equals(caVar.get())) {
                return true;
            }
        }
        return false;
    }

    public final Object get(Object obj) {
        b();
        return a((ca) this.a.get(obj));
    }

    public final Object put(Object obj, Object obj2) {
        b();
        return a((ca) this.a.put(obj, new ca(obj, obj2, this.b)));
    }

    public final Object remove(Object obj) {
        b();
        return a((ca) this.a.remove(obj));
    }

    private static Object a(ca caVar) {
        return caVar != null ? caVar.get() : null;
    }

    public final Set entrySet() {
        b();
        throw new UnsupportedOperationException();
    }

    public final Set keySet() {
        b();
        return this.a.keySet();
    }

    public final Collection values() {
        b();
        throw new UnsupportedOperationException();
    }

    public final boolean equals(Object obj) {
        b();
        throw new UnsupportedOperationException();
    }

    public final int hashCode() {
        b();
        throw new UnsupportedOperationException();
    }

    public final String toString() {
        b();
        throw new UnsupportedOperationException();
    }

    private void b() {
        while (true) {
            ca a = this.b.a();
            if (a != null) {
                this.a.remove(a.a);
            } else {
                return;
            }
        }
    }

    public static bd a() {
        return new bd();
    }
}
