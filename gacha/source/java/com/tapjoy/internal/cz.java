package com.tapjoy.internal;

import java.nio.CharBuffer;

public final class cz {
    private static long a(Readable readable, Appendable appendable) {
        CharSequence allocate = CharBuffer.allocate(2048);
        long j = 0;
        while (true) {
            int read = readable.read(allocate);
            if (read == -1) {
                return j;
            }
            allocate.flip();
            appendable.append(allocate, 0, read);
            j += (long) read;
        }
    }

    public static String a(Readable readable) {
        Appendable stringBuilder = new StringBuilder();
        a(readable, stringBuilder);
        return stringBuilder.toString();
    }
}
