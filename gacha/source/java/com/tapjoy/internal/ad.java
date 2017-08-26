package com.tapjoy.internal;

public final class ad {
    public static Object a(bf bfVar) {
        int i = 1;
        while (true) {
            try {
                break;
            } catch (OutOfMemoryError e) {
                if (i >= 10) {
                    throw e;
                }
                System.gc();
                i++;
            }
        }
        return bfVar.call();
    }
}
