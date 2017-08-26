package com.tapjoy.internal;

import android.content.SharedPreferences;

public final class j extends o {
    private final boolean c = false;

    public j(SharedPreferences sharedPreferences, String str) {
        super(sharedPreferences, str);
    }

    public final Boolean a() {
        return Boolean.valueOf(b());
    }

    public final boolean b() {
        return this.a.getBoolean(this.b, this.c);
    }

    public final void a(boolean z) {
        this.a.edit().putBoolean(this.b, z).commit();
    }
}
