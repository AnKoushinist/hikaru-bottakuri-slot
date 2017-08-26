package com.moat.analytics.mobile.tjy;

import android.media.MediaPlayer;
import android.view.View;
import com.moat.analytics.mobile.tjy.base.functional.a;
import java.lang.reflect.Method;
import java.util.Map;

class ai implements bc {
    private static final a a;
    private static final a b;
    private static final a c;
    private static final a d;
    private static final a e;

    static {
        a a;
        Exception e;
        a a2 = a.a();
        a a3 = a.a();
        a a4 = a.a();
        a a5 = a.a();
        a a6 = a.a();
        try {
            Class cls = NativeVideoTracker.class;
            Method method = cls.getMethod("setDebug", new Class[]{Boolean.TYPE});
            Method method2 = cls.getMethod("trackVideoAd", new Class[]{Map.class, MediaPlayer.class, View.class});
            Method method3 = cls.getMethod("changeTargetView", new Class[]{View.class});
            Method method4 = cls.getMethod("dispatchEvent", new Class[]{Map.class});
            Method method5 = cls.getMethod("dispatchEvent", new Class[]{Map.class});
            a2 = a.a(method);
            a3 = a.a(method2);
            a4 = a.a(method3);
            a = a.a(method4);
            try {
                a5 = a.a(method5);
            } catch (NoSuchMethodException e2) {
                e = e2;
                com.moat.analytics.mobile.tjy.base.exception.a.a(e);
                a = a2;
                b = a3;
                c = a4;
                d = a;
                e = a5;
            }
        } catch (Exception e3) {
            Exception exception = e3;
            a = a6;
            e = exception;
            com.moat.analytics.mobile.tjy.base.exception.a.a(e);
            a = a2;
            b = a3;
            c = a4;
            d = a;
            e = a5;
        }
        a = a2;
        b = a3;
        c = a4;
        d = a;
        e = a5;
    }

    ai() {
    }

    public Class a() {
        return NativeVideoTracker.class;
    }
}
