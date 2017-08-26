package com.tapjoy.internal;

import android.app.Activity;
import android.opengl.GLSurfaceView;

public final class fr {
    public static final bf a = new bf() {
        public final boolean a(Runnable runnable) {
            GLSurfaceView gLSurfaceView = (GLSurfaceView) fr.c.a();
            if (gLSurfaceView == null) {
                return false;
            }
            gLSurfaceView.queueEvent(runnable);
            return true;
        }
    };
    private static Activity b;
    private static final cd c = new cd();
    private static final cd d = new cd();

    static void a(GLSurfaceView gLSurfaceView) {
        new Object[1][0] = gLSurfaceView;
        c.a(gLSurfaceView);
        gLSurfaceView.queueEvent(new Runnable() {
            public final void run() {
                Thread currentThread = Thread.currentThread();
                new Object[1][0] = currentThread;
                fr.d.a(currentThread);
            }
        });
    }

    public static Activity a() {
        Activity activity = b;
        if (activity == null) {
            return d.a();
        }
        return activity;
    }

    public static Thread b() {
        return (Thread) d.a();
    }
}
