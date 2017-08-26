package com.b.a.a;

import java.util.HashSet;
import java.util.Set;

/* compiled from: SamplingEventFilter */
class n implements j {
    static final Set<b> b = new HashSet<b>() {
        {
            add(b.START);
            add(b.RESUME);
            add(b.PAUSE);
            add(b.STOP);
        }
    };
    final int a;

    public n(int i) {
        this.a = i;
    }

    public boolean a(s sVar) {
        boolean z;
        if (b.contains(sVar.c) && sVar.a.g == null) {
            z = true;
        } else {
            z = false;
        }
        boolean z2;
        if (Math.abs(sVar.a.c.hashCode() % this.a) != 0) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z && r3) {
            return true;
        }
        return false;
    }
}
