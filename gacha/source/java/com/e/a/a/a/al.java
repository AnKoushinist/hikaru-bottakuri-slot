package com.e.a.a.a;

import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Bundle;
import android.util.Log;
import com.e.a.a.a.a.b.a;

class al implements ActivityLifecycleCallbacks {
    final /* synthetic */ aj a;

    private al(aj ajVar) {
        this.a = ajVar;
    }

    private boolean a(Activity activity) {
        Activity activity2 = (Activity) this.a.b.get();
        return activity2 != null && activity2.equals(activity);
    }

    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    public void onActivityDestroyed(Activity activity) {
        try {
            if (this.a.d.b()) {
                Log.d("MoatActivityState", "Activity destroyed: " + activity.getClass() + "@" + activity.hashCode());
            }
            if (a(activity)) {
                this.a.e = false;
                ((Application) this.a.a.get()).unregisterActivityLifecycleCallbacks(this);
            }
        } catch (Exception e) {
            a.a(e);
        }
    }

    public void onActivityPaused(Activity activity) {
        if (this.a.d.b()) {
            Log.d("MoatActivityState", "Activity paused: " + activity.getClass() + "@" + activity.hashCode());
        }
        if (a(activity)) {
            this.a.e = true;
        }
    }

    public void onActivityResumed(Activity activity) {
        if (this.a.d.b()) {
            Log.d("MoatActivityState", "Activity resumed: " + activity.getClass() + "@" + activity.hashCode());
        }
        if (a(activity)) {
            this.a.e = false;
        }
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public void onActivityStarted(Activity activity) {
        if (this.a.d.b()) {
            Log.d("MoatActivityState", "Activity started: " + activity.getClass() + "@" + activity.hashCode());
        }
        if (a(activity)) {
            this.a.e = false;
        }
    }

    public void onActivityStopped(Activity activity) {
        if (this.a.d.b()) {
            Log.d("MoatActivityState", "Activity stopped: " + activity.getClass() + "@" + activity.hashCode());
        }
        if (a(activity)) {
            this.a.e = true;
        }
    }
}
