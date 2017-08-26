package com.tapjoy.internal;

import android.content.Context;
import com.tapjoy.TJPlacementListener;

abstract class fb {
    private static final fb a;
    private static fb b;

    public abstract Object a(Context context, String str, TJPlacementListener tJPlacementListener);

    fb() {
    }

    static {
        fb fcVar = new fc();
        a = fcVar;
        b = fcVar;
    }

    static fb a() {
        return b;
    }
}
