package com.tapjoy.internal;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public final class q extends o {
    private final String c = null;

    public q(SharedPreferences sharedPreferences, String str) {
        super(sharedPreferences, str);
    }

    public final String a() {
        return this.a.getString(this.b, this.c);
    }

    public final void a(String str) {
        this.a.edit().putString(this.b, str).commit();
    }

    public final Editor a(Editor editor, String str) {
        return str != null ? editor.putString(this.b, str) : editor.remove(this.b);
    }
}
