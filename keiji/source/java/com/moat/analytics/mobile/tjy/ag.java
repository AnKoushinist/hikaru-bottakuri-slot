package com.moat.analytics.mobile.tjy;

import com.moat.analytics.mobile.tjy.base.functional.a;
import java.lang.reflect.Method;
import java.util.Map;

class ag implements bc {
    private static final a a;
    private static final a b;

    static {
        a a;
        Exception e;
        a a2 = a.a();
        a a3 = a.a();
        try {
            Method method = NativeDisplayTracker.class.getMethod("track", new Class[]{Map.class});
            Method method2 = NativeDisplayTracker.class.getMethod("stopTracking", new Class[0]);
            a = a.a(method);
            try {
                a3 = a.a(method2);
            } catch (NoSuchMethodException e2) {
                e = e2;
                com.moat.analytics.mobile.tjy.base.exception.a.a(e);
                a = a;
                b = a3;
            }
        } catch (Exception e3) {
            Exception exception = e3;
            a = a2;
            e = exception;
            com.moat.analytics.mobile.tjy.base.exception.a.a(e);
            a = a;
            b = a3;
        }
        a = a;
        b = a3;
    }

    ag() {
    }

    public Class a() {
        return NativeDisplayTracker.class;
    }
}
