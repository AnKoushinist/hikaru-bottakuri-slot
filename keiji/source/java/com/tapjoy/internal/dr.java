package com.tapjoy.internal;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.RandomAccess;

final class dr extends AbstractList implements Serializable, RandomAccess {
    private final ArrayList a;

    dr(List list) {
        this.a = new ArrayList(list);
    }

    public final int size() {
        return this.a.size();
    }

    public final Object get(int i) {
        return this.a.get(i);
    }

    public final Object[] toArray() {
        return this.a.toArray();
    }
}
