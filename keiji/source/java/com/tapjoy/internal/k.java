package com.tapjoy.internal;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public final class k extends o {
    private final double c = 0.0d;

    public k(SharedPreferences sharedPreferences, String str) {
        super(sharedPreferences, str);
    }

    public final double a() {
        String string = this.a.getString(this.b, null);
        if (string != null) {
            try {
                return Double.parseDouble(string);
            } catch (NumberFormatException e) {
            }
        }
        return this.c;
    }

    public final Editor a(Editor editor) {
        return editor.remove(this.b);
    }

    public final Editor a(Editor editor, double d) {
        return editor.putString(this.b, Double.toString(d));
    }
}
