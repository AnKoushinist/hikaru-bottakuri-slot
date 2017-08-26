package com.e.a.a.a;

import android.app.Activity;
import android.app.Application;
import android.util.Log;
import com.e.a.a.a.a.a.a;
import java.lang.ref.WeakReference;

class aj implements f {
    private final WeakReference a;
    private final WeakReference b;
    private boolean c;
    private final n d;
    private boolean e;

    aj(Activity activity, n nVar) {
        a.a(activity);
        if (nVar.b()) {
            Log.d("MoatActivityState", "Listening to Activity: " + (activity != null ? activity.getClass() + "@" + activity.hashCode() : "null"));
        }
        this.a = new WeakReference(activity.getApplication());
        this.b = new WeakReference(activity);
        this.d = nVar;
        this.c = false;
    }

    public boolean a() {
        return this.e;
    }

    public void b() {
        if (!this.c) {
            ((Application) this.a.get()).registerActivityLifecycleCallbacks(new al());
        }
    }

    public Activity c() {
        return (Activity) this.b.get();
    }
}
