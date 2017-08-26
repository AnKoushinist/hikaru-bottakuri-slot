package com.tapjoy.internal;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;

public final class c {
    public static Activity a(Context context) {
        for (Context context2 = context; context2 instanceof ContextWrapper; context2 = ((ContextWrapper) context2).getBaseContext()) {
            if (context2 instanceof Activity) {
                return (Activity) context2;
            }
        }
        return null;
    }
}
