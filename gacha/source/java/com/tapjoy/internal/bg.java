package com.tapjoy.internal;

import java.io.FilterInputStream;
import java.io.InputStream;

public final class bg extends FilterInputStream {
    public bg(InputStream inputStream) {
        super(inputStream);
    }

    public final int read(byte[] bArr, int i, int i2) {
        int i3 = 0;
        while (i3 < i2) {
            int read = super.read(bArr, i + i3, i2 - i3);
            if (read == -1) {
                return i3 != 0 ? i3 : -1;
            } else {
                i3 += read;
            }
        }
        return i3;
    }

    public final int read(byte[] bArr) {
        int i = 0;
        while (i < bArr.length) {
            int read = super.read(bArr, i, bArr.length - i);
            if (read == -1) {
                return i != 0 ? i : -1;
            } else {
                i += read;
            }
        }
        return i;
    }

    public final long skip(long j) {
        long j2 = 0;
        while (j2 < j) {
            long skip = super.skip(j - j2);
            if (skip == 0) {
                if (super.read() < 0) {
                    break;
                }
                skip++;
            }
            j2 = skip + j2;
        }
        return j2;
    }
}
