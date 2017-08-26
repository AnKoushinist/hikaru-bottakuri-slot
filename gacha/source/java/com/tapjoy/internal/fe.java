package com.tapjoy.internal;

import android.app.Activity;
import android.opengl.GLSurfaceView;

public final class fe {
    public static final be a = new be() {
        public final boolean a(Runnable runnable) {
            GLSurfaceView gLSurfaceView = (GLSurfaceView) fe.c.a();
            if (gLSurfaceView == null) {
                return false;
            }
            gLSurfaceView.queueEvent(runnable);
            return true;
        }
    };
    private static Activity b;
    private static final cc c = new cc();
    private static final cc d = new cc();

    static void a(GLSurfaceView gLSurfaceView) {
        new Object[1][0] = gLSurfaceView;
        c.a(gLSurfaceView);
        gLSurfaceView.queueEvent(new Runnable() {
            public final void run() {
                Thread currentThread = Thread.currentThread();
                new Object[1][0] = currentThread;
                fe.d.a(currentThread);
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
