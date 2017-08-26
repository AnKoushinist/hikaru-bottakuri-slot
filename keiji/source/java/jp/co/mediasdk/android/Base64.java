package jp.co.mediasdk.android;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FilterInputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.io.UnsupportedEncodingException;
import java.util.zip.GZIPInputStream;
import twitter4j.TwitterResponse;

public class Base64 {
    static final /* synthetic */ boolean a;
    private static final byte[] b = new byte[]{(byte) 65, (byte) 66, (byte) 67, (byte) 68, (byte) 69, (byte) 70, (byte) 71, (byte) 72, (byte) 73, (byte) 74, (byte) 75, (byte) 76, (byte) 77, (byte) 78, (byte) 79, (byte) 80, (byte) 81, (byte) 82, (byte) 83, (byte) 84, (byte) 85, (byte) 86, (byte) 87, (byte) 88, (byte) 89, (byte) 90, (byte) 97, (byte) 98, (byte) 99, (byte) 100, (byte) 101, (byte) 102, (byte) 103, (byte) 104, (byte) 105, (byte) 106, (byte) 107, (byte) 108, (byte) 109, (byte) 110, (byte) 111, (byte) 112, (byte) 113, (byte) 114, (byte) 115, (byte) 116, (byte) 117, (byte) 118, (byte) 119, (byte) 120, (byte) 121, (byte) 122, (byte) 48, (byte) 49, (byte) 50, (byte) 51, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57, (byte) 43, (byte) 47};
    private static final byte[] c = new byte[]{(byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -5, (byte) -5, (byte) -9, (byte) -9, (byte) -5, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -5, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) 62, (byte) -9, (byte) -9, (byte) -9, (byte) 63, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57, (byte) 58, (byte) 59, (byte) 60, (byte) 61, (byte) -9, (byte) -9, (byte) -9, (byte) -1, (byte) -9, (byte) -9, (byte) -9, (byte) 0, (byte) 1, (byte) 2, (byte) 3, (byte) 4, (byte) 5, (byte) 6, (byte) 7, (byte) 8, (byte) 9, (byte) 10, (byte) 11, (byte) 12, (byte) 13, (byte) 14, (byte) 15, (byte) 16, (byte) 17, (byte) 18, (byte) 19, (byte) 20, (byte) 21, (byte) 22, (byte) 23, (byte) 24, (byte) 25, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) 26, (byte) 27, (byte) 28, (byte) 29, (byte) 30, (byte) 31, (byte) 32, (byte) 33, (byte) 34, (byte) 35, (byte) 36, (byte) 37, (byte) 38, (byte) 39, (byte) 40, (byte) 41, (byte) 42, (byte) 43, (byte) 44, (byte) 45, (byte) 46, (byte) 47, (byte) 48, (byte) 49, (byte) 50, (byte) 51, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9};
    private static final byte[] d = new byte[]{(byte) 65, (byte) 66, (byte) 67, (byte) 68, (byte) 69, (byte) 70, (byte) 71, (byte) 72, (byte) 73, (byte) 74, (byte) 75, (byte) 76, (byte) 77, (byte) 78, (byte) 79, (byte) 80, (byte) 81, (byte) 82, (byte) 83, (byte) 84, (byte) 85, (byte) 86, (byte) 87, (byte) 88, (byte) 89, (byte) 90, (byte) 97, (byte) 98, (byte) 99, (byte) 100, (byte) 101, (byte) 102, (byte) 103, (byte) 104, (byte) 105, (byte) 106, (byte) 107, (byte) 108, (byte) 109, (byte) 110, (byte) 111, (byte) 112, (byte) 113, (byte) 114, (byte) 115, (byte) 116, (byte) 117, (byte) 118, (byte) 119, (byte) 120, (byte) 121, (byte) 122, (byte) 48, (byte) 49, (byte) 50, (byte) 51, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57, (byte) 45, (byte) 95};
    private static final byte[] e = new byte[]{(byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -5, (byte) -5, (byte) -9, (byte) -9, (byte) -5, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -5, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) 62, (byte) -9, (byte) -9, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57, (byte) 58, (byte) 59, (byte) 60, (byte) 61, (byte) -9, (byte) -9, (byte) -9, (byte) -1, (byte) -9, (byte) -9, (byte) -9, (byte) 0, (byte) 1, (byte) 2, (byte) 3, (byte) 4, (byte) 5, (byte) 6, (byte) 7, (byte) 8, (byte) 9, (byte) 10, (byte) 11, (byte) 12, (byte) 13, (byte) 14, (byte) 15, (byte) 16, (byte) 17, (byte) 18, (byte) 19, (byte) 20, (byte) 21, (byte) 22, (byte) 23, (byte) 24, (byte) 25, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) 63, (byte) -9, (byte) 26, (byte) 27, (byte) 28, (byte) 29, (byte) 30, (byte) 31, (byte) 32, (byte) 33, (byte) 34, (byte) 35, (byte) 36, (byte) 37, (byte) 38, (byte) 39, (byte) 40, (byte) 41, (byte) 42, (byte) 43, (byte) 44, (byte) 45, (byte) 46, (byte) 47, (byte) 48, (byte) 49, (byte) 50, (byte) 51, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9};
    private static final byte[] f = new byte[]{(byte) 45, (byte) 48, (byte) 49, (byte) 50, (byte) 51, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57, (byte) 65, (byte) 66, (byte) 67, (byte) 68, (byte) 69, (byte) 70, (byte) 71, (byte) 72, (byte) 73, (byte) 74, (byte) 75, (byte) 76, (byte) 77, (byte) 78, (byte) 79, (byte) 80, (byte) 81, (byte) 82, (byte) 83, (byte) 84, (byte) 85, (byte) 86, (byte) 87, (byte) 88, (byte) 89, (byte) 90, (byte) 95, (byte) 97, (byte) 98, (byte) 99, (byte) 100, (byte) 101, (byte) 102, (byte) 103, (byte) 104, (byte) 105, (byte) 106, (byte) 107, (byte) 108, (byte) 109, (byte) 110, (byte) 111, (byte) 112, (byte) 113, (byte) 114, (byte) 115, (byte) 116, (byte) 117, (byte) 118, (byte) 119, (byte) 120, (byte) 121, (byte) 122};
    private static final byte[] g = new byte[]{(byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -5, (byte) -5, (byte) -9, (byte) -9, (byte) -5, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -5, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) 0, (byte) -9, (byte) -9, (byte) 1, (byte) 2, (byte) 3, (byte) 4, (byte) 5, (byte) 6, (byte) 7, (byte) 8, (byte) 9, (byte) 10, (byte) -9, (byte) -9, (byte) -9, (byte) -1, (byte) -9, (byte) -9, (byte) -9, (byte) 11, (byte) 12, (byte) 13, (byte) 14, (byte) 15, (byte) 16, (byte) 17, (byte) 18, (byte) 19, (byte) 20, (byte) 21, (byte) 22, (byte) 23, (byte) 24, (byte) 25, (byte) 26, (byte) 27, (byte) 28, (byte) 29, (byte) 30, (byte) 31, (byte) 32, (byte) 33, (byte) 34, (byte) 35, (byte) 36, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) 37, (byte) -9, (byte) 38, (byte) 39, (byte) 40, (byte) 41, (byte) 42, (byte) 43, (byte) 44, (byte) 45, (byte) 46, (byte) 47, (byte) 48, (byte) 49, (byte) 50, (byte) 51, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57, (byte) 58, (byte) 59, (byte) 60, (byte) 61, (byte) 62, (byte) 63, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9};

    static class AnonymousClass1 extends ObjectInputStream {
        final /* synthetic */ ClassLoader a;

        public Class<?> resolveClass(ObjectStreamClass objectStreamClass) {
            Class<?> cls = Class.forName(objectStreamClass.getName(), false, this.a);
            if (cls == null) {
                return super.resolveClass(objectStreamClass);
            }
            return cls;
        }
    }

    public static class InputStream extends FilterInputStream {
        private boolean a;
        private int b;
        private byte[] c;
        private int d;
        private int e;
        private int f;
        private boolean g;
        private int h;
        private byte[] i;

        public int read() {
            byte[] bArr;
            if (this.b < 0) {
                int i;
                if (this.a) {
                    bArr = new byte[3];
                    i = 0;
                    int i2 = 0;
                    while (i < 3) {
                        int read = this.in.read();
                        if (read < 0) {
                            break;
                        }
                        bArr[i] = (byte) read;
                        i++;
                        i2++;
                    }
                    if (i2 <= 0) {
                        return -1;
                    }
                    Base64.b(bArr, 0, i2, this.c, 0, this.h);
                    this.b = 0;
                    this.e = 4;
                } else {
                    byte[] bArr2 = new byte[4];
                    int i3 = 0;
                    while (i3 < 4) {
                        do {
                            i = this.in.read();
                            if (i < 0) {
                                break;
                            }
                        } while (this.i[i & 127] <= (byte) -5);
                        if (i < 0) {
                            break;
                        }
                        bArr2[i3] = (byte) i;
                        i3++;
                    }
                    if (i3 == 4) {
                        this.e = Base64.b(bArr2, 0, this.c, 0, this.h);
                        this.b = 0;
                    } else if (i3 == 0) {
                        return -1;
                    } else {
                        throw new IOException("Improperly padded Base64 input.");
                    }
                }
            }
            if (this.b < 0) {
                throw new IOException("Error in Base64 code reading stream.");
            } else if (this.b >= this.e) {
                return -1;
            } else {
                if (this.a && this.g && this.f >= 76) {
                    this.f = 0;
                    return 10;
                }
                this.f++;
                bArr = this.c;
                int i4 = this.b;
                this.b = i4 + 1;
                byte b = bArr[i4];
                if (this.b >= this.d) {
                    this.b = -1;
                }
                return b & 255;
            }
        }

        public int read(byte[] bArr, int i, int i2) {
            int i3 = 0;
            while (i3 < i2) {
                int read = read();
                if (read >= 0) {
                    bArr[i + i3] = (byte) read;
                    i3++;
                } else if (i3 == 0) {
                    return -1;
                } else {
                    return i3;
                }
            }
            return i3;
        }
    }

    public static class OutputStream extends FilterOutputStream {
        private boolean a;
        private int b;
        private byte[] c;
        private int d;
        private int e;
        private boolean f;
        private byte[] g;
        private boolean h;
        private int i;
        private byte[] j;

        public void write(int i) {
            if (this.h) {
                this.out.write(i);
            } else if (this.a) {
                r0 = this.c;
                r1 = this.b;
                this.b = r1 + 1;
                r0[r1] = (byte) i;
                if (this.b >= this.d) {
                    this.out.write(Base64.b(this.g, this.c, this.d, this.i));
                    this.e += 4;
                    if (this.f && this.e >= 76) {
                        this.out.write(10);
                        this.e = 0;
                    }
                    this.b = 0;
                }
            } else if (this.j[i & 127] > (byte) -5) {
                r0 = this.c;
                r1 = this.b;
                this.b = r1 + 1;
                r0[r1] = (byte) i;
                if (this.b >= this.d) {
                    this.out.write(this.g, 0, Base64.b(this.c, 0, this.g, 0, this.i));
                    this.b = 0;
                }
            } else if (this.j[i & 127] != (byte) -5) {
                throw new IOException("Invalid character in Base64 data.");
            }
        }

        public void write(byte[] bArr, int i, int i2) {
            if (this.h) {
                this.out.write(bArr, i, i2);
                return;
            }
            for (int i3 = 0; i3 < i2; i3++) {
                write(bArr[i + i3]);
            }
        }

        public void a() {
            if (this.b <= 0) {
                return;
            }
            if (this.a) {
                this.out.write(Base64.b(this.g, this.c, this.b, this.i));
                this.b = 0;
                return;
            }
            throw new IOException("Base64 input not properly padded.");
        }

        public void close() {
            a();
            super.close();
            this.c = null;
            this.out = null;
        }
    }

    static {
        boolean z;
        if (Base64.class.desiredAssertionStatus()) {
            z = false;
        } else {
            z = true;
        }
        a = z;
    }

    private static final byte[] a(int i) {
        if ((i & 16) == 16) {
            return d;
        }
        if ((i & 32) == 32) {
            return f;
        }
        return b;
    }

    private static final byte[] b(int i) {
        if ((i & 16) == 16) {
            return e;
        }
        if ((i & 32) == 32) {
            return g;
        }
        return c;
    }

    private Base64() {
    }

    private static byte[] b(byte[] bArr, byte[] bArr2, int i, int i2) {
        b(bArr2, 0, i, bArr, 0, i2);
        return bArr;
    }

    private static byte[] b(byte[] bArr, int i, int i2, byte[] bArr2, int i3, int i4) {
        int i5 = 0;
        byte[] a = a(i4);
        int i6 = (i2 > 1 ? (bArr[i + 1] << 24) >>> 16 : 0) | (i2 > 0 ? (bArr[i] << 24) >>> 8 : 0);
        if (i2 > 2) {
            i5 = (bArr[i + 2] << 24) >>> 24;
        }
        i5 |= i6;
        switch (i2) {
            case TwitterResponse.READ /*1*/:
                bArr2[i3] = a[i5 >>> 18];
                bArr2[i3 + 1] = a[(i5 >>> 12) & 63];
                bArr2[i3 + 2] = (byte) 61;
                bArr2[i3 + 3] = (byte) 61;
                break;
            case TwitterResponse.READ_WRITE /*2*/:
                bArr2[i3] = a[i5 >>> 18];
                bArr2[i3 + 1] = a[(i5 >>> 12) & 63];
                bArr2[i3 + 2] = a[(i5 >>> 6) & 63];
                bArr2[i3 + 3] = (byte) 61;
                break;
            case TwitterResponse.READ_WRITE_DIRECTMESSAGES /*3*/:
                bArr2[i3] = a[i5 >>> 18];
                bArr2[i3 + 1] = a[(i5 >>> 12) & 63];
                bArr2[i3 + 2] = a[(i5 >>> 6) & 63];
                bArr2[i3 + 3] = a[i5 & 63];
                break;
        }
        return bArr2;
    }

    private static int b(byte[] bArr, int i, byte[] bArr2, int i2, int i3) {
        if (bArr == null) {
            throw new NullPointerException("Source array was null.");
        } else if (bArr2 == null) {
            throw new NullPointerException("Destination array was null.");
        } else if (i < 0 || i + 3 >= bArr.length) {
            throw new IllegalArgumentException(String.format("Source array with length %d cannot have offset of %d and still process four bytes.", new Object[]{Integer.valueOf(bArr.length), Integer.valueOf(i)}));
        } else if (i2 < 0 || i2 + 2 >= bArr2.length) {
            throw new IllegalArgumentException(String.format("Destination array with length %d cannot have offset of %d and still store three bytes.", new Object[]{Integer.valueOf(bArr2.length), Integer.valueOf(i2)}));
        } else {
            byte[] b = b(i3);
            if (bArr[i + 2] == (byte) 61) {
                bArr2[i2] = (byte) ((((b[bArr[i]] & 255) << 18) | ((b[bArr[i + 1]] & 255) << 12)) >>> 16);
                return 1;
            } else if (bArr[i + 3] == (byte) 61) {
                r0 = (((b[bArr[i]] & 255) << 18) | ((b[bArr[i + 1]] & 255) << 12)) | ((b[bArr[i + 2]] & 255) << 6);
                bArr2[i2] = (byte) (r0 >>> 16);
                bArr2[i2 + 1] = (byte) (r0 >>> 8);
                return 2;
            } else {
                r0 = ((((b[bArr[i]] & 255) << 18) | ((b[bArr[i + 1]] & 255) << 12)) | ((b[bArr[i + 2]] & 255) << 6)) | (b[bArr[i + 3]] & 255);
                bArr2[i2] = (byte) (r0 >> 16);
                bArr2[i2 + 1] = (byte) (r0 >> 8);
                bArr2[i2 + 2] = (byte) r0;
                return 3;
            }
        }
    }

    public static byte[] a(byte[] bArr, int i, int i2, int i3) {
        if (bArr == null) {
            throw new NullPointerException("Cannot decode null source array.");
        } else if (i < 0 || i + i2 > bArr.length) {
            throw new IllegalArgumentException(String.format("Source array with length %d cannot have offset of %d and process %d bytes.", new Object[]{Integer.valueOf(bArr.length), Integer.valueOf(i), Integer.valueOf(i2)}));
        } else if (i2 == 0) {
            return new byte[0];
        } else {
            if (i2 < 4) {
                throw new IllegalArgumentException("Base64-encoded string must have at least four characters, but length specified was " + i2);
            }
            int i4;
            byte[] b = b(i3);
            Object obj = new byte[((i2 * 3) / 4)];
            byte[] bArr2 = new byte[4];
            int i5 = i;
            int i6 = 0;
            int i7 = 0;
            while (i5 < i + i2) {
                byte b2 = b[bArr[i5] & 255];
                if (b2 >= (byte) -5) {
                    if (b2 >= (byte) -1) {
                        i4 = i6 + 1;
                        bArr2[i6] = bArr[i5];
                        if (i4 > 3) {
                            i4 = b(bArr2, 0, obj, i7, i3) + i7;
                            if (bArr[i5] == (byte) 61) {
                                break;
                            }
                            i6 = i4;
                            i4 = 0;
                        } else {
                            i6 = i7;
                        }
                    } else {
                        i4 = i6;
                        i6 = i7;
                    }
                    i5++;
                    i7 = i6;
                    i6 = i4;
                } else {
                    throw new IOException(String.format("Bad Base64 input character decimal %d in array position %d", new Object[]{Integer.valueOf(bArr[i5] & 255), Integer.valueOf(i5)}));
                }
            }
            i4 = i7;
            Object obj2 = new byte[i4];
            System.arraycopy(obj, 0, obj2, 0, i4);
            return obj2;
        }
    }

    public static byte[] a(String str) {
        return a(str, 0);
    }

    public static byte[] a(String str, int i) {
        byte[] bytes;
        ByteArrayOutputStream byteArrayOutputStream;
        IOException e;
        ByteArrayInputStream byteArrayInputStream;
        Throwable th;
        GZIPInputStream gZIPInputStream = null;
        if (str == null) {
            throw new NullPointerException("Input string was null.");
        }
        try {
            bytes = str.getBytes("US-ASCII");
        } catch (UnsupportedEncodingException e2) {
            bytes = str.getBytes();
        }
        bytes = a(bytes, 0, bytes.length, i);
        int i2;
        if ((i & 4) != 0) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        if (bytes != null && bytes.length >= 4 && r1 == 0 && 35615 == ((bytes[0] & 255) | ((bytes[1] << 8) & 65280))) {
            byte[] bArr = new byte[2048];
            ByteArrayInputStream byteArrayInputStream2;
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
                try {
                    byteArrayInputStream2 = new ByteArrayInputStream(bytes);
                } catch (IOException e3) {
                    e = e3;
                    byteArrayInputStream = null;
                    try {
                        e.printStackTrace();
                        try {
                            byteArrayOutputStream.close();
                        } catch (Exception e4) {
                        }
                        try {
                            gZIPInputStream.close();
                        } catch (Exception e5) {
                        }
                        try {
                            byteArrayInputStream.close();
                        } catch (Exception e6) {
                        }
                        return bytes;
                    } catch (Throwable th2) {
                        th = th2;
                        byteArrayInputStream2 = byteArrayInputStream;
                        try {
                            byteArrayOutputStream.close();
                        } catch (Exception e7) {
                        }
                        try {
                            gZIPInputStream.close();
                        } catch (Exception e8) {
                        }
                        try {
                            byteArrayInputStream2.close();
                        } catch (Exception e9) {
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    byteArrayInputStream2 = null;
                    byteArrayOutputStream.close();
                    gZIPInputStream.close();
                    byteArrayInputStream2.close();
                    throw th;
                }
                try {
                    GZIPInputStream gZIPInputStream2 = new GZIPInputStream(byteArrayInputStream2);
                    while (true) {
                        try {
                            int read = gZIPInputStream2.read(bArr);
                            if (read < 0) {
                                break;
                            }
                            byteArrayOutputStream.write(bArr, 0, read);
                        } catch (IOException e10) {
                            e = e10;
                            gZIPInputStream = gZIPInputStream2;
                            byteArrayInputStream = byteArrayInputStream2;
                        } catch (Throwable th4) {
                            th = th4;
                            gZIPInputStream = gZIPInputStream2;
                        }
                    }
                    bytes = byteArrayOutputStream.toByteArray();
                    try {
                        byteArrayOutputStream.close();
                    } catch (Exception e11) {
                    }
                    try {
                        gZIPInputStream2.close();
                    } catch (Exception e12) {
                    }
                    try {
                        byteArrayInputStream2.close();
                    } catch (Exception e13) {
                    }
                } catch (IOException e14) {
                    e = e14;
                    byteArrayInputStream = byteArrayInputStream2;
                    e.printStackTrace();
                    byteArrayOutputStream.close();
                    gZIPInputStream.close();
                    byteArrayInputStream.close();
                    return bytes;
                } catch (Throwable th5) {
                    th = th5;
                    byteArrayOutputStream.close();
                    gZIPInputStream.close();
                    byteArrayInputStream2.close();
                    throw th;
                }
            } catch (IOException e15) {
                e = e15;
                byteArrayOutputStream = null;
                byteArrayInputStream = null;
                e.printStackTrace();
                byteArrayOutputStream.close();
                gZIPInputStream.close();
                byteArrayInputStream.close();
                return bytes;
            } catch (Throwable th6) {
                th = th6;
                byteArrayOutputStream = null;
                byteArrayInputStream2 = null;
                byteArrayOutputStream.close();
                gZIPInputStream.close();
                byteArrayInputStream2.close();
                throw th;
            }
        }
        return bytes;
    }
}
