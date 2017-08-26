package com.b.a.c;

import com.d.a.a.c;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

/* compiled from: ByteString */
final class b {
    public static final b a = new b(new byte[0]);
    private final byte[] b;
    private volatile int c = 0;

    private b(byte[] bArr) {
        this.b = bArr;
    }

    public int a() {
        return this.b.length;
    }

    public static b a(byte[] bArr, int i, int i2) {
        Object obj = new byte[i2];
        System.arraycopy(bArr, i, obj, 0, i2);
        return new b(obj);
    }

    public static b a(String str) {
        try {
            return new b(str.getBytes(c.DEFAULT_CHARSET));
        } catch (Throwable e) {
            throw new RuntimeException("UTF-8 not supported.", e);
        }
    }

    public void a(byte[] bArr, int i, int i2, int i3) {
        System.arraycopy(this.b, i, bArr, i2, i3);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        int length = this.b.length;
        if (length != bVar.b.length) {
            return false;
        }
        byte[] bArr = this.b;
        byte[] bArr2 = bVar.b;
        for (int i = 0; i < length; i++) {
            if (bArr[i] != bArr2[i]) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        int i = this.c;
        if (i == 0) {
            byte[] bArr = this.b;
            int length = this.b.length;
            int i2 = 0;
            i = length;
            while (i2 < length) {
                int i3 = bArr[i2] + (i * 31);
                i2++;
                i = i3;
            }
            if (i == 0) {
                i = 1;
            }
            this.c = i;
        }
        return i;
    }

    public InputStream b() {
        return new ByteArrayInputStream(this.b);
    }
}
