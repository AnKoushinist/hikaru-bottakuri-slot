package com.b.a.a;

import android.annotation.SuppressLint;
import android.content.Context;
import b.a.a.a.a.f.c;
import b.a.a.a.a.f.d;
import jp.tjkapp.adfurikunsdk.moviereward.ApiAccessUtil;

/* compiled from: AnswersPreferenceManager */
class e {
    private final c a;

    public static e a(Context context) {
        return new e(new d(context, ApiAccessUtil.WEBAPI_KEY_SETTINGS));
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
