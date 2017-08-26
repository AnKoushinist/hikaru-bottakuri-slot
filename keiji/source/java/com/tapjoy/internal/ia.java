package com.tapjoy.internal;

import java.util.Arrays;

final class ia extends hu {
    final transient byte[][] f;
    final transient int[] g;

    ia(hr hrVar, int i) {
        int i2 = 0;
        super(null);
        ie.a(hrVar.b, 0, (long) i);
        hy hyVar = hrVar.a;
        int i3 = 0;
        int i4 = 0;
        while (i4 < i) {
            if (hyVar.c == hyVar.b) {
                throw new AssertionError("s.limit == s.pos");
            }
            i4 += hyVar.c - hyVar.b;
            i3++;
            hyVar = hyVar.f;
        }
        this.f = new byte[i3][];
        this.g = new int[(i3 * 2)];
        hy hyVar2 = hrVar.a;
        i4 = 0;
        while (i2 < i) {
            this.f[i4] = hyVar2.a;
            int i5 = (hyVar2.c - hyVar2.b) + i2;
            if (i5 > i) {
                i5 = i;
            }
            this.g[i4] = i5;
            this.g[this.f.length + i4] = hyVar2.b;
            hyVar2.d = true;
            i4++;
            hyVar2 = hyVar2.f;
            i2 = i5;
        }
    }

    public final String a() {
        return e().a();
    }

    public final String b() {
        return e().b();
    }

    public final hu a(int i, int i2) {
        return e().a(i, i2);
    }

    public final byte a(int i) {
        ie.a((long) this.g[this.f.length - 1], (long) i, 1);
        int b = b(i);
        return this.f[b][(i - (b == 0 ? 0 : this.g[b - 1])) + this.g[this.f.length + b]];
    }

    private int b(int i) {
        int binarySearch = Arrays.binarySearch(this.g, 0, this.f.length, i + 1);
        return binarySearch >= 0 ? binarySearch : binarySearch ^ -1;
    }

    public final int c() {
        return this.g[this.f.length - 1];
    }

    public final byte[] d() {
        int i = 0;
        Object obj = new byte[this.g[this.f.length - 1]];
        int length = this.f.length;
        int i2 = 0;
        while (i < length) {
            int i3 = this.g[length + i];
            int i4 = this.g[i];
            System.arraycopy(this.f[i], i3, obj, i2, i4 - i2);
            i++;
            i2 = i4;
        }
        return obj;
    }

    final void a(hr hrVar) {
        int i = 0;
        int length = this.f.length;
        int i2 = 0;
        while (i < length) {
            int i3 = this.g[length + i];
            int i4 = this.g[i];
            hy hyVar = new hy(this.f[i], i3, (i3 + i4) - i2);
            if (hrVar.a == null) {
                hyVar.g = hyVar;
                hyVar.f = hyVar;
                hrVar.a = hyVar;
            } else {
                hrVar.a.g.a(hyVar);
            }
            i++;
            i2 = i4;
        }
        hrVar.b = ((long) i2) + hrVar.b;
    }

    public final boolean a(int i, byte[] bArr, int i2, int i3) {
        if (i < 0 || i > c() - i3 || i2 < 0 || i2 > bArr.length - i3) {
            return false;
        }
        int b = b(i);
        while (i3 > 0) {
            int i4 = b == 0 ? 0 : this.g[b - 1];
            int min = Math.min(i3, ((this.g[b] - i4) + i4) - i);
            if (!ie.a(this.f[b], (i - i4) + this.g[this.f.length + b], bArr, i2, min)) {
                return false;
            }
            i += min;
            i2 += min;
            i3 -= min;
            b++;
        }
        return true;
    }

    private hu e() {
        return new hu(d());
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof hu) || ((hu) obj).c() != c()) {
            return false;
        }
        boolean z;
        hu huVar = (hu) obj;
        int c = c();
        if (c() - c < 0) {
            z = false;
        } else {
            int i = c;
            int i2 = 0;
            int i3 = 0;
            c = b(0);
            while (i > 0) {
                int i4 = c == 0 ? 0 : this.g[c - 1];
                int min = Math.min(i, ((this.g[c] - i4) + i4) - i3);
                if (!huVar.a(i2, this.f[c], (i3 - i4) + this.g[this.f.length + c], min)) {
                    z = false;
                    break;
                }
                i3 += min;
                i2 += min;
                i -= min;
                c++;
            }
            z = true;
        }
        if (z) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        int i = this.d;
        if (i == 0) {
            i = 1;
            int length = this.f.length;
            int i2 = 0;
            int i3 = 0;
            while (i2 < length) {
                byte[] bArr = this.f[i2];
                int i4 = this.g[length + i2];
                int i5 = this.g[i2];
                i3 = (i5 - i3) + i4;
                int i6 = i4;
                i4 = i;
                for (i = i6; i < i3; i++) {
                    i4 = (i4 * 31) + bArr[i];
                }
                i2++;
                i3 = i5;
                i = i4;
            }
            this.d = i;
        }
        return i;
    }

    public final String toString() {
        return e().toString();
    }
}
