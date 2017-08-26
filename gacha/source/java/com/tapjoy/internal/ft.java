package com.tapjoy.internal;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public abstract class ft {
    long h;
    boolean i;
    public ff j;

    public abstract void a();

    public abstract void a(fn fnVar);

    public boolean b() {
        return true;
    }

    static void a(Context context, String str) {
        if (!cr.c(str)) {
            try {
                context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
            } catch (Exception e) {
            }
        }
    }
}
