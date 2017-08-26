package com.b.a.a;

import a.a.a.a.a.b;
import android.app.Activity;
import android.os.Bundle;

/* compiled from: AnswersLifecycleCallbacks */
class d extends b {
    private final q a;
    private final g b;

    public d(q qVar, g gVar) {
        this.a = qVar;
        this.b = gVar;
    }

    public void a(Activity activity, Bundle bundle) {
    }

    public void a(Activity activity) {
        this.a.a(activity, b.START);
    }

    public void b(Activity activity) {
        this.a.a(activity, b.RESUME);
        this.b.a();
    }

    public void c(Activity activity) {
        this.a.a(activity, b.PAUSE);
        this.b.b();
    }

    public void d(Activity activity) {
        this.a.a(activity, b.STOP);
    }

    public void b(Activity activity, Bundle bundle) {
    }

    public void e(Activity activity) {
    }
}
