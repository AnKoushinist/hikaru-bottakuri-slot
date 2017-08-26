package com.tapjoy.internal;

import java.util.AbstractMap;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

public final class be extends AbstractMap {
    private final HashMap a = new HashMap();
    private final cc b = new cc();

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
        for (cb cbVar : this.a.values()) {
            if (obj.equals(cbVar.get())) {
                return true;
            }
        }
        return false;
    }

    public final Object get(Object obj) {
        b();
        return a((cb) this.a.get(obj));
    }

    public final Object put(Object obj, Object obj2) {
        b();
        return a((cb) this.a.put(obj, new cb(obj, obj2, this.b)));
    }

    public final Object remove(Object obj) {
        b();
        return a((cb) this.a.remove(obj));
    }

    private static Object a(cb cbVar) {
        return cbVar != null ? cbVar.get() : null;
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
            cb a = this.b.a();
            if (a != null) {
                this.a.remove(a.a);
            } else {
                return;
            }
        }
    }

    public static be a() {
        return new be();
    }
}
