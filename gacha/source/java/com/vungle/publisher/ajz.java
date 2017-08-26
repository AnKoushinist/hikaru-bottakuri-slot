package com.vungle.publisher;

import java.security.AccessController;
import java.security.PrivilegedAction;

/* compiled from: vungle */
public final class ajz {
    private static final int a;
    private static final boolean b;

    static {
        int c = c();
        a = c;
        b = c != 0;
    }

    public static boolean a() {
        return b;
    }

    public static int b() {
        return a;
    }

    private static int c() {
        try {
            return ((Integer) Class.forName("android.os.Build$VERSION", true, System.getSecurityManager() == null ? ClassLoader.getSystemClassLoader() : (ClassLoader) AccessController.doPrivileged(new PrivilegedAction<ClassLoader>() {
                public final /* synthetic */ Object run() {
                    return ClassLoader.getSystemClassLoader();
                }
            })).getField("SDK_INT").get(null)).intValue();
        } catch (Exception e) {
            return 0;
        }
    }
}
