package com.tapjoy.internal;

import java.io.OutputStream;
import java.util.Collection;

public abstract class dh implements du {
    public int a = 0;

    public static abstract class a implements com.tapjoy.internal.du.a {
        public abstract a a();

        public /* synthetic */ Object clone() {
            return a();
        }

        public static void a(Iterable iterable, Collection collection) {
            if (iterable instanceof ds) {
                a(((ds) iterable).a());
                collection.addAll((Collection) iterable);
            } else if (iterable instanceof Collection) {
                a(iterable);
                collection.addAll((Collection) iterable);
            } else {
                for (Object next : iterable) {
                    if (next == null) {
                        throw new NullPointerException();
                    }
                    collection.add(next);
                }
            }
        }

        private static void a(Iterable iterable) {
            for (Object obj : iterable) {
                if (obj == null) {
                    throw new NullPointerException();
                }
            }
        }
    }

    public final byte[] a() {
        try {
            byte[] bArr = new byte[b()];
            dm dmVar = new dm(bArr, bArr.length);
            a(dmVar);
            if (dmVar.c != null) {
                throw new UnsupportedOperationException("spaceLeft() can only be called on CodedOutputStreams that are writing to a flat array.");
            } else if (dmVar.a - dmVar.b == 0) {
                return bArr;
            } else {
                throw new IllegalStateException("Did not write as much data as expected.");
            }
        } catch (Throwable e) {
            throw new RuntimeException("Serializing to a byte array threw an IOException (should never happen).", e);
        }
    }

    public final void a(OutputStream outputStream) {
        int i = 4096;
        int b = b();
        if (b <= 4096) {
            i = b;
        }
        dm a = dm.a(outputStream, i);
        a(a);
        a.a();
    }
}
