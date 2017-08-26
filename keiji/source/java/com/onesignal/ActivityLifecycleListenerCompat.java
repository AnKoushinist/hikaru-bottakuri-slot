package com.onesignal;

import android.app.Activity;
import android.app.Instrumentation;
import android.app.Instrumentation.ActivityMonitor;
import android.app.OnActivityPausedListener;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

class ActivityLifecycleListenerCompat {
    static void startListener() {
        try {
            Class cls = Class.forName("android.app.ActivityThread");
            Object invoke = cls.getMethod("currentActivityThread", new Class[0]).invoke(null, new Object[0]);
            Field declaredField = cls.getDeclaredField("mInstrumentation");
            declaredField.setAccessible(true);
            startMonitorThread(cls, invoke, ((Instrumentation) declaredField.get(invoke)).addMonitor((String) null, null, false));
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private static void startMonitorThread(final Class cls, final Object obj, final ActivityMonitor activityMonitor) {
        new Thread(new Runnable() {
            public void run() {
                try {
                    AnonymousClass1 anonymousClass1 = new OnActivityPausedListener() {
                    };
                    Method method = cls.getMethod("registerOnActivityPausedListener", new Class[]{Activity.class, OnActivityPausedListener.class});
                    while (true) {
                        Activity waitForActivity = activityMonitor.waitForActivity();
                        if (!waitForActivity.isFinishing()) {
                            ActivityLifecycleHandler.onActivityResumed(waitForActivity);
                            method.invoke(obj, new Object[]{waitForActivity, anonymousClass1});
                        }
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        }).start();
    }
}
