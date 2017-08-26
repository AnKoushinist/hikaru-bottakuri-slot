package com.tapjoy.internal;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public final class n extends o {
    private final long c = 0;

    public n(SharedPreferences sharedPreferences, String str) {
        super(sharedPreferences, str);
    }

    public final long a() {
        return this.a.getLong(this.b, this.c);
    }

    public final void a(long j) {
        this.a.edit().putLong(this.b, j).commit();
    }

    public final Editor a(Editor editor) {
        return editor.remove(this.b);
    }

    public final Editor a(Editor editor, long j) {
        return editor.putLong(this.b, j);
    }
}
