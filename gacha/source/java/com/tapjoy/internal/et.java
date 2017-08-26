package com.tapjoy.internal;

import android.content.Context;
import com.tapjoy.TJPlacementListener;

abstract class et {
    private static final et a;
    private static et b;

    public abstract Object a(Context context, String str, TJPlacementListener tJPlacementListener);

    et() {
    }

    static {
        et euVar = new eu();
        a = euVar;
        b = euVar;
    }

    static et a() {
        return b;
    }
}
