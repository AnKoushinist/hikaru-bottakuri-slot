package com.moat.analytics.mobile.tjy;

import android.app.Activity;
import android.app.Application;
import android.util.Log;
import com.moat.analytics.mobile.tjy.base.asserts.a;
import java.lang.ref.WeakReference;

class c implements a {
    private final WeakReference a;
    private final WeakReference b;
    private boolean c;
    private final ap d;
    private boolean e;

    c(Activity activity, ap apVar) {
        a.a(activity);
        if (apVar.b()) {
            Log.d("MoatActivityState", "Listening to Activity: " + (activity != null ? activity.getClass() + "@" + activity.hashCode() : "null"));
        }
        this.a = new WeakReference(activity.getApplication());
        this.b = new WeakReference(activity);
        this.d = apVar;
        this.c = false;
    }

    public boolean a() {
        return this.e;
    }

    public void b() {
        if (!this.c) {
            ((Application) this.a.get()).registerActivityLifecycleCallbacks(new e());
        }
    }

    public Activity c() {
        return (Activity) this.b.get();
    }
}
