package com.tapjoy.internal;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public abstract class gg {
    long h;
    boolean i;
    public fs j;
    public String k;

    public abstract void a();

    public abstract void a(ga gaVar, ev evVar);

    public boolean b() {
        return true;
    }

    static void a(Context context, String str) {
        if (!ct.c(str)) {
            try {
                context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
            } catch (Exception e) {
            }
        }
    }
}
