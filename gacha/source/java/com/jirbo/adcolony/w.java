package com.jirbo.adcolony;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

class w extends InputStream {
    InputStream a;
    byte[] b = new byte[1024];
    int c;
    int d;
    int e;
    int f;
    int g;
    int h;

    w(String str) {
        if (a.n != 0) {
            this.h = 23;
            this.g = 23;
        }
        this.c = (int) new File(str).length();
        this.a = new FileInputStream(str);
    }

    public int available() {
        return (this.e - this.f) + this.a.available();
    }

    public void close() {
        this.a.close();
    }

    public void mark(int i) {
    }

    public boolean markSupported() {
        return false;
    }

    public int read() {
        if (this.d == this.c) {
            return -1;
        }
        if (this.f >= this.e) {
            a();
        }
        this.d++;
        byte[] bArr = this.b;
        int i = this.f;
        this.f = i + 1;
        return bArr[i];
    }

    public int read(byte[] bArr) {
        return read(bArr, 0, bArr.length);
    }

    public int read(byte[] bArr, int i, int i2) {
        if (this.d == this.c) {
            return -1;
        }
        int i3 = this.c - this.d;
        if (i2 > i3) {
            i2 = i3;
        }
        int i4 = 0;
        int i5 = i2;
        int i6 = i;
        while (i5 > 0) {
            if (this.f == this.e) {
                a();
            }
            i3 = i5 < this.e ? i5 : this.e;
            int i7 = i6;
            i6 = 0;
            while (i6 < i3) {
                int i8 = i7 + 1;
                byte[] bArr2 = this.b;
                int i9 = this.f;
                this.f = i9 + 1;
                bArr[i7] = bArr2[i9];
                i6++;
                i7 = i8;
            }
            i5 -= i3;
            i4 += i3;
            this.d = i3 + this.d;
            i6 = i7;
        }
        return i4;
    }

    public void reset() {
        throw new IOException("ADCStreamReader does not support reset().");
    }

    public long skip(long j) {
        throw new IOException("ADCStreamReader does not support skip().");
    }

    void a() {
        this.e = 0;
        while (this.e == 0) {
            this.e = this.a.read(this.b, 0, 1024);
        }
        for (int i = 0; i < this.e; i++) {
            this.b[i] = (byte) (this.b[i] ^ this.g);
            this.g += this.h;
        }
        this.f = 0;
    }
}
