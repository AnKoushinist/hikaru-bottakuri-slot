package com.tapjoy.internal;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class z {
    private static final ThreadLocal a = new ThreadLocal() {
        protected final /* synthetic */ Object initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ssZ");
        }
    };
    private static final ThreadLocal b = new ThreadLocal() {
        protected final /* synthetic */ Object initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH.mm.ss");
        }
    };

    public static String a(Date date) {
        return ((DateFormat) a.get()).format(date);
    }
}
