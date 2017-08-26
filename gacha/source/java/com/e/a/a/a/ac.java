package com.e.a.a.a;

import android.view.View;
import com.e.a.a.a.a.c.a;
import java.lang.reflect.Method;
import java.util.Map;

class ac implements aa {
    private static final a a;
    private static final a b;
    private static final a c;
    private static final a d;

    static {
        a a;
        Exception e;
        a a2 = a.a();
        a a3 = a.a();
        a a4 = a.a();
        a a5 = a.a();
        try {
            Class cls = d.class;
            Method method = cls.getMethod("setDebug", new Class[]{Boolean.TYPE});
            Method method2 = cls.getMethod("trackVideoAd", new Class[]{Map.class, View.class, View.class});
            Method method3 = cls.getMethod("changeTargetView", new Class[]{View.class});
            Method method4 = cls.getMethod("dispatchEvent", new Class[]{a.class});
            a2 = a.a(method);
            a3 = a.a(method2);
            a = a.a(method3);
            try {
                a5 = a.a(method4);
            } catch (NoSuchMethodException e2) {
                e = e2;
                com.e.a.a.a.a.b.a.a(e);
                a = a2;
                b = a3;
                c = a;
                d = a5;
            }
        } catch (Exception e3) {
            Exception exception = e3;
            a = a4;
            e = exception;
            com.e.a.a.a.a.b.a.a(e);
            a = a2;
            b = a3;
            c = a;
            d = a5;
        }
        a = a2;
        b = a3;
        c = a;
        d = a5;
    }

    ac() {
    }

    public Class a() {
        return d.class;
    }
}
