package com.tapjoy.internal;

public final class ad {
    public static Object a(bg bgVar) {
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
        return bgVar.call();
    }
}
