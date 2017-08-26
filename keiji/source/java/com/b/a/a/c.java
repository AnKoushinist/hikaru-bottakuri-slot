package com.b.a.a;

import a.a.a.a.a.b.s;
import a.a.a.a.a.d.g;
import a.a.a.a.a.f.a;
import android.content.Context;
import android.os.Looper;

/* compiled from: AnswersFilesManagerProvider */
class c {
    final Context a;
    final a b;

    public c(Context context, a aVar) {
        this.a = context;
        this.b = aVar;
    }

    public o a() {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            throw new IllegalStateException("AnswersFilesManagerProvider cannot be called on the main thread");
        }
        return new o(this.a, new u(), new s(), new g(this.a, this.b.a(), "session_analytics.tap", "session_analytics_to_send"));
    }
}
