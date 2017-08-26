package com.b.a.a;

import a.a.a.a.a.f.c;
import a.a.a.a.a.f.d;
import android.annotation.SuppressLint;
import android.content.Context;

/* compiled from: AnswersPreferenceManager */
class e {
    private final c a;

    public static e a(Context context) {
        return new e(new d(context, "settings"));
    }

    e(c cVar) {
        this.a = cVar;
    }

    @SuppressLint({"CommitPrefEdits"})
    public void a() {
        this.a.a(this.a.b().putBoolean("analytics_launched", true));
    }

    @SuppressLint({"CommitPrefEdits"})
    public boolean b() {
        return this.a.a().getBoolean("analytics_launched", false);
    }
}
