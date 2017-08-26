package com.tapjoy.internal;

import java.security.MessageDigest;

public final class cl {
    public static byte[] a(byte[] bArr) {
        try {
            return MessageDigest.getInstance("SHA-1").digest(bArr);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
