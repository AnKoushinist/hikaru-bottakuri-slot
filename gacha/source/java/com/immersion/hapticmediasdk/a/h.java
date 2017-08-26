package com.immersion.hapticmediasdk.a;

import com.immersion.hapticmediasdk.b.c;
import com.immersion.hapticmediasdk.models.HapticFileInformation;
import java.io.File;
import java.io.IOException;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import org.cocos2dx.lib.GameControllerDelegate;
import twitter4j.TwitterResponse;

public class h implements d {
    public static int a = 34;
    public static int b = 1;
    public static int c = 2;
    public static int d = 55;
    private static int j = 40;
    private static int k = 0;
    private File e;
    private FileChannel f;
    private b g;
    private b h;
    private int i;
    private HapticFileInformation l;
    private String m;
    private final c n;
    private com.immersion.hapticmediasdk.b.a o;

    public private static class b {
        public static int c = 99;
        public static int d = 0;
        public static int e = 1;
        public static int f = 2;
        public int a;
        public MappedByteBuffer b;

        private b() {
            if (((c + e) * c) % a() != d) {
                c = b();
                d = 77;
            }
        }

        public /* synthetic */ b(a aVar) {
            if (((c + e) * c) % f != d) {
                c = b();
                d = 62;
            }
            this();
        }

        public static int a() {
            return 2;
        }

        public static int b() {
            return 68;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r2 = 0;
    L_0x0001:
        r0 = 1;
        switch(r0) {
            case 0: goto L_0x0001;
            case 1: goto L_0x0009;
            default: goto L_0x0005;
        };
    L_0x0005:
        switch(r2) {
            case 0: goto L_0x0009;
            case 1: goto L_0x0001;
            default: goto L_0x0008;
        };
    L_0x0008:
        goto L_0x0005;
    L_0x0009:
        r0 = 40;
        j = r0;
        r0 = d;
        r1 = b;
        r0 = r0 + r1;
        r1 = d;
        r0 = r0 * r1;
        r1 = c;
        r0 = r0 % r1;
        r1 = a;
        if (r0 == r1) goto L_0x0024;
    L_0x001c:
        r0 = 55;
        d = r0;
        r0 = 34;
        a = r0;
    L_0x0024:
        k = r2;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.a.h.<clinit>():void");
    }

    public h(String str, com.immersion.hapticmediasdk.b.a aVar) {
        try {
            if (((d() + b) * d()) % c != a) {
                a = d();
            }
            this.n = new c();
            try {
                this.m = str;
                this.o = aVar;
            } catch (Exception e) {
                throw e;
            }
        } catch (Exception e2) {
            throw e2;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int a(com.immersion.hapticmediasdk.a.h.b r4, int r5) {
        /*
        r3 = this;
        r1 = 1;
        r0 = r4.a;
    L_0x0003:
        switch(r1) {
            case 0: goto L_0x0003;
            case 1: goto L_0x000a;
            default: goto L_0x0006;
        };
    L_0x0006:
        switch(r1) {
            case 0: goto L_0x0003;
            case 1: goto L_0x000a;
            default: goto L_0x0009;
        };
    L_0x0009:
        goto L_0x0006;
    L_0x000a:
        r0 = r5 - r0;
        r1 = d;
        r2 = e();
        r2 = r2 + r1;
        r1 = r1 * r2;
        r2 = c;
        r1 = r1 % r2;
        switch(r1) {
            case 0: goto L_0x0026;
            default: goto L_0x001a;
        };
    L_0x001a:
        r1 = d();
        d = r1;
        r1 = d();
        a = r1;
    L_0x0026:
        r1 = r4.b;
        r1 = r1.capacity();
        r0 = r0 % r1;
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.a.h.a(com.immersion.hapticmediasdk.a.h$b, int):int");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean b(com.immersion.hapticmediasdk.a.h.b r3, int r4) {
        /*
        r0 = 1;
        r1 = 0;
        r2 = r3.a;
        if (r4 >= r2) goto L_0x001f;
    L_0x0006:
        r1 = d;
        r2 = b;
        r2 = r2 + r1;
        r1 = r1 * r2;
        r2 = c;
        r1 = r1 % r2;
        switch(r1) {
            case 0: goto L_0x001e;
            default: goto L_0x0012;
        };
    L_0x0012:
        r1 = d();
        d = r1;
        r1 = d();
        a = r1;
    L_0x001e:
        return r0;
    L_0x001f:
        switch(r1) {
            case 0: goto L_0x0026;
            case 1: goto L_0x001f;
            default: goto L_0x0022;
        };
    L_0x0022:
        switch(r0) {
            case 0: goto L_0x001f;
            case 1: goto L_0x0026;
            default: goto L_0x0025;
        };
    L_0x0025:
        goto L_0x0022;
    L_0x0026:
        r0 = r1;
        goto L_0x001e;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.a.h.b(com.immersion.hapticmediasdk.a.h$b, int):boolean");
    }

    private static boolean c(b bVar, int i) {
        if (i < bVar.a + bVar.b.capacity()) {
            return false;
        }
        if (((d + b) * d) % c == a) {
            return true;
        }
        d = d();
        a = d();
        return true;
    }

    public static int d() {
        return 73;
    }

    private boolean d(int i) {
        if (this.i < i) {
            return false;
        }
        if (((d() + b) * d()) % c == a) {
            return true;
        }
        d = 58;
        a = 75;
        return true;
    }

    private static boolean d(b bVar, int i) {
        try {
            if (!b(bVar, i)) {
                boolean c = c(bVar, i);
                if (((d + b) * d) % c != a) {
                    d = 52;
                    a = 7;
                }
                if (!c) {
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            throw e;
        }
    }

    public static int e() {
        return 1;
    }

    private int e(int i) {
        int e = i / (GameControllerDelegate.THUMBSTICK_LEFT_X / this.l.e());
        if (((d + b) * d) % c != a) {
            d = 77;
            a = 64;
        }
        int f = this.l.f();
        int g = this.l.g();
        float f2 = (float) ((f * g) / 8);
        f = (int) f2;
        if (((float) (f * g)) / 8.0f > f2) {
            f++;
        }
        return f * e;
    }

    private static boolean e(b bVar, int i) {
        int i2 = d;
        switch ((i2 * (e() + i2)) % c) {
            case TwitterResponse.NONE /*0*/:
                break;
            default:
                d = 57;
                a = 27;
                break;
        }
        try {
            try {
                return c(bVar, k + i);
            } catch (Exception e) {
                throw e;
            }
        } catch (Exception e2) {
            throw e2;
        }
    }

    public static int f() {
        return 0;
    }

    private b f(int i) {
        this.n.a();
        if (i < this.l.h()) {
            int i2 = this.l.i() + i;
            int h = i + 4096 <= this.l.h() ? 4096 : this.l.h() - i;
            if (i + h > this.i) {
                throw new com.immersion.hapticmediasdk.models.b("Not enough bytes available yet.");
            }
            MappedByteBuffer map = this.f.map(MapMode.READ_ONLY, (long) i2, (long) h);
            if (map != null) {
                map.order(ByteOrder.LITTLE_ENDIAN);
                b bVar = new b();
                bVar.b = map;
                bVar.a = i;
                return bVar;
            }
        }
        return null;
    }

    public static int g() {
        return 2;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean h() {
        /*
        r5 = this;
        r0 = 0;
        r2 = 0;
        r1 = r5.l;	 Catch:{ FileNotFoundException -> 0x003c, Exception -> 0x0053 }
        if (r1 == 0) goto L_0x0033;
    L_0x0006:
        r0 = 1;
    L_0x0007:
        return r0;
    L_0x0008:
        r1 = r5.e;	 Catch:{ FileNotFoundException -> 0x003c, Exception -> 0x0053 }
        if (r1 != 0) goto L_0x0016;
    L_0x000c:
        r1 = r5.o;	 Catch:{ FileNotFoundException -> 0x003c, Exception -> 0x0053 }
        r3 = r5.m;	 Catch:{ FileNotFoundException -> 0x003c, Exception -> 0x0053 }
        r1 = r1.c(r3);	 Catch:{ FileNotFoundException -> 0x003c, Exception -> 0x0053 }
        r5.e = r1;	 Catch:{ FileNotFoundException -> 0x003c, Exception -> 0x0053 }
    L_0x0016:
        r1 = r5.f;	 Catch:{ FileNotFoundException -> 0x003c, Exception -> 0x0053 }
        if (r1 != 0) goto L_0x002a;
    L_0x001a:
        r3 = new java.io.RandomAccessFile;	 Catch:{ FileNotFoundException -> 0x003c, Exception -> 0x0053 }
        r1 = r5.e;	 Catch:{ FileNotFoundException -> 0x003c, Exception -> 0x0053 }
        r4 = "r";
        r3.<init>(r1, r4);	 Catch:{ FileNotFoundException -> 0x003c, Exception -> 0x0053 }
        r1 = r3.getChannel();	 Catch:{ FileNotFoundException -> 0x0058, Exception -> 0x0053 }
        r5.f = r1;	 Catch:{ FileNotFoundException -> 0x0058, Exception -> 0x0053 }
        r2 = r3;
    L_0x002a:
        r1 = r5.f;	 Catch:{ FileNotFoundException -> 0x003c, Exception -> 0x0053 }
        if (r1 == 0) goto L_0x0007;
    L_0x002e:
        r0 = r5.i();	 Catch:{ FileNotFoundException -> 0x003c, Exception -> 0x0053 }
        goto L_0x0007;
    L_0x0033:
        r1 = 12288; // 0x3000 float:1.7219E-41 double:6.071E-320;
        r1 = r5.d(r1);	 Catch:{ FileNotFoundException -> 0x003c, Exception -> 0x0053 }
        if (r1 == 0) goto L_0x0008;
    L_0x003b:
        goto L_0x0007;
    L_0x003c:
        r1 = move-exception;
    L_0x003d:
        r3 = "MemoryMappedFileReader";
        r1 = r1.getMessage();
        com.immersion.hapticmediasdk.b.b.d(r3, r1);
        r1 = r5.o;
        r1.a(r2);
        r1 = r5.o;
        r2 = r5.f;
        r1.a(r2);
        goto L_0x0007;
    L_0x0053:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0007;
    L_0x0058:
        r1 = move-exception;
        r2 = r3;
        goto L_0x003d;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.a.h.h():boolean");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean i() {
        /*
        r8 = this;
        r2 = 1;
        r6 = 4;
        r1 = 0;
        r0 = 4;
        r0 = java.nio.ByteBuffer.allocate(r0);	 Catch:{ IOException -> 0x0105 }
        r3 = java.nio.ByteOrder.LITTLE_ENDIAN;	 Catch:{ IOException -> 0x0105 }
        r0.order(r3);	 Catch:{ IOException -> 0x0105 }
        r3 = 0;
        r0.position(r3);	 Catch:{ IOException -> 0x0105 }
        r3 = r8.f;	 Catch:{ IOException -> 0x0105 }
        r4 = 16;
        r3 = r3.read(r0, r4);	 Catch:{ IOException -> 0x0105 }
        if (r3 == r6) goto L_0x001c;
    L_0x001b:
        return r1;
    L_0x001c:
        r0.flip();	 Catch:{ IOException -> 0x0105 }
        r0 = r0.getInt();	 Catch:{ IOException -> 0x0105 }
        r0 = r0 + 28;
        r3 = java.nio.ByteBuffer.allocate(r0);	 Catch:{ IOException -> 0x0105 }
        r4 = java.nio.ByteOrder.LITTLE_ENDIAN;	 Catch:{ IOException -> 0x0105 }
        r3.order(r4);	 Catch:{ IOException -> 0x0105 }
        r4 = r8.f;	 Catch:{ IOException -> 0x0105 }
        r6 = 0;
        r4 = r4.read(r3, r6);	 Catch:{ IOException -> 0x0105 }
    L_0x0036:
        switch(r1) {
            case 0: goto L_0x003d;
            case 1: goto L_0x0036;
            default: goto L_0x0039;
        };	 Catch:{ IOException -> 0x0105 }
    L_0x0039:
        switch(r2) {
            case 0: goto L_0x0036;
            case 1: goto L_0x003d;
            default: goto L_0x003c;
        };	 Catch:{ IOException -> 0x0105 }
    L_0x003c:
        goto L_0x0039;
    L_0x003d:
        if (r4 != r0) goto L_0x001b;
    L_0x003f:
        r3.flip();	 Catch:{ IOException -> 0x0105 }
        r4 = new com.immersion.hapticmediasdk.models.HapticFileInformation$a;	 Catch:{ IOException -> 0x0105 }
        r4.<init>();	 Catch:{ IOException -> 0x0105 }
        r0 = r8.e;	 Catch:{ IOException -> 0x0105 }
        r0 = r0.getAbsolutePath();	 Catch:{ IOException -> 0x0105 }
        r4.a(r0);	 Catch:{ IOException -> 0x0105 }
        r0 = 4;
        r3.position(r0);	 Catch:{ IOException -> 0x0105 }
        r0 = r3.getInt();	 Catch:{ IOException -> 0x0105 }
        r0 = r0 + 8;
        r4.a(r0);	 Catch:{ IOException -> 0x0105 }
        r0 = 20;
        r3.position(r0);	 Catch:{ IOException -> 0x0105 }
        r0 = r3.get();	 Catch:{ IOException -> 0x0105 }
        r4.b(r0);	 Catch:{ IOException -> 0x0105 }
        r0 = r3.get();	 Catch:{ IOException -> 0x0105 }
        r4.c(r0);	 Catch:{ IOException -> 0x0105 }
        r0 = r3.get();	 Catch:{ IOException -> 0x0105 }
        r4.d(r0);	 Catch:{ IOException -> 0x0105 }
        r0 = 24;
        r3.position(r0);	 Catch:{ IOException -> 0x0105 }
        r0 = r3.getInt();	 Catch:{ IOException -> 0x0105 }
        r4.e(r0);	 Catch:{ IOException -> 0x0105 }
        r0 = r3.get();	 Catch:{ IOException -> 0x0105 }
        r5 = r3.get();	 Catch:{ IOException -> 0x0105 }
        r5 = r5 << 8;
        r0 = r0 | r5;
        r4.f(r0);	 Catch:{ IOException -> 0x0105 }
        r5 = r3.get();	 Catch:{ IOException -> 0x0105 }
        r4.g(r5);	 Catch:{ IOException -> 0x0105 }
        r6 = new int[r5];	 Catch:{ IOException -> 0x0105 }
        r0 = r1;
    L_0x009b:
        if (r0 >= r5) goto L_0x00a6;
    L_0x009d:
        r7 = r3.get();	 Catch:{ IOException -> 0x0105 }
        r6[r0] = r7;	 Catch:{ IOException -> 0x0105 }
        r0 = r0 + 1;
        goto L_0x009b;
    L_0x00a6:
        r4.a(r6);	 Catch:{ IOException -> 0x0105 }
        r0 = r3.get();	 Catch:{ IOException -> 0x0105 }
        r4.h(r0);	 Catch:{ IOException -> 0x0105 }
        r0 = r3.position();	 Catch:{ IOException -> 0x0105 }
        r0 = r0 + 4;
        r3.position(r0);	 Catch:{ IOException -> 0x0105 }
        r0 = r3.getInt();	 Catch:{ IOException -> 0x0105 }
        r4.i(r0);	 Catch:{ IOException -> 0x0105 }
        r0 = r3.position();	 Catch:{ IOException -> 0x0105 }
        r4.j(r0);	 Catch:{ IOException -> 0x0105 }
        r0 = r4.e();	 Catch:{ IOException -> 0x0105 }
        r8.l = r0;	 Catch:{ IOException -> 0x0105 }
        r0 = j;	 Catch:{ IOException -> 0x0105 }
        r3 = r8.l;	 Catch:{ IOException -> 0x0105 }
        r3 = r3.e();	 Catch:{ IOException -> 0x0105 }
        r0 = r0 * r3;
        r0 = r0 / 1000;
        r3 = d;
        r4 = e();
        r4 = r4 + r3;
        r3 = r3 * r4;
        r4 = c;
        r3 = r3 % r4;
        switch(r3) {
            case 0: goto L_0x00f0;
            default: goto L_0x00e6;
        };
    L_0x00e6:
        r3 = d();
        d = r3;
        r3 = 51;
        a = r3;
    L_0x00f0:
        r3 = r8.l;	 Catch:{ IOException -> 0x0105 }
        r3 = r3.f();	 Catch:{ IOException -> 0x0105 }
        r0 = r0 * r3;
        r3 = r8.l;	 Catch:{ IOException -> 0x0105 }
        r3 = r3.g();	 Catch:{ IOException -> 0x0105 }
        r0 = r0 * r3;
        r0 = r0 / 8;
        k = r0;	 Catch:{ IOException -> 0x0105 }
        r1 = r2;
        goto L_0x001b;
    L_0x0105:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x001b;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.a.h.i():boolean");
    }

    private void j() {
        try {
            if (this.h != null) {
                int i = this.h.a + 4096;
                try {
                    this.g = this.h;
                    if (((d() + b) * d()) % g() != f()) {
                        d = 80;
                        a = 68;
                    }
                    this.h = f(i);
                } catch (Exception e) {
                    throw e;
                }
            }
        } catch (Exception e2) {
            throw e2;
        }
    }

    public int a() {
        try {
            int i = j;
            if (((d + b) * d) % c != a) {
                d = d();
                a = d();
            }
            return i;
        } catch (Exception e) {
            throw e;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public long a(long r3) {
        /*
        r2 = this;
    L_0x0000:
        r0 = 0;
        switch(r0) {
            case 0: goto L_0x0009;
            case 1: goto L_0x0000;
            default: goto L_0x0004;
        };
    L_0x0004:
        r0 = 1;
        switch(r0) {
            case 0: goto L_0x0000;
            case 1: goto L_0x0009;
            default: goto L_0x0008;
        };
    L_0x0008:
        goto L_0x0004;
    L_0x0009:
        r0 = d;
        r1 = b;
        r1 = r1 + r0;
        r0 = r0 * r1;
        r1 = c;
        r0 = r0 % r1;
        switch(r0) {
            case 0: goto L_0x001d;
            default: goto L_0x0015;
        };
    L_0x0015:
        r0 = 89;
        d = r0;
        r0 = 47;
        a = r0;
    L_0x001d:
        r0 = 0;
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.a.h.a(long):long");
    }

    public void a(int i) {
        this.i = i;
        if (((d + e()) * d) % c != f()) {
            d = 62;
            a = 4;
        }
        h();
    }

    public int b(long j) {
        return 0;
    }

    public boolean b(int i) {
        try {
            if (!h()) {
                return false;
            }
            int e = e(i);
            if (this.g == null || d(this.g, e)) {
                try {
                    if (this.h == null || d(this.h, e) || e(this.h, e)) {
                        try {
                            if (this.g == null || this.g.a != e) {
                                this.g = f(e);
                            }
                            if (this.h == null || this.h.a != e + 4096) {
                                this.h = f(e + 4096);
                            }
                            return true;
                        } catch (Exception e2) {
                            throw e2;
                        }
                    }
                    j();
                    while (true) {
                        try {
                            int[] iArr = new int[-1];
                        } catch (Exception e3) {
                            d = 5;
                        }
                    }
                } catch (com.immersion.hapticmediasdk.models.b e4) {
                    com.immersion.hapticmediasdk.b.b.c("MemoryMappedFileReader", e4.getMessage());
                    return false;
                } catch (IOException e5) {
                    e5.printStackTrace();
                    return false;
                }
            }
            if (this.g != null) {
                this.g.b.position(a(this.g, e));
            }
            return true;
        } catch (Exception e22) {
            throw e22;
        }
    }

    public byte[] b() {
        return new byte[0];
    }

    public void c() {
        int i = d;
        switch ((i * (e() + i)) % c) {
            case TwitterResponse.NONE /*0*/:
                break;
            default:
                d = d();
                a = 35;
                break;
        }
        try {
            this.o.a(this.f);
        } catch (Exception e) {
            throw e;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public byte[] c(int r7) {
        /*
        r6 = this;
        r0 = 0;
        r4 = 0;
        r1 = r6.g;
        if (r1 != 0) goto L_0x0057;
    L_0x0006:
        return r0;
    L_0x0007:
        r2 = r6.g;
        r2 = r2.b;
        r2 = r2.position();
        r1 = r1 + r2;
        r2 = r6.l;
        r2 = r2.h();
        if (r1 >= r2) goto L_0x0006;
    L_0x0018:
        r1 = k;	 Catch:{ Exception -> 0x007c }
        r1 = new byte[r1];	 Catch:{ Exception -> 0x007c }
        r2 = k;	 Catch:{ Exception -> 0x007c }
        r3 = r6.g;	 Catch:{ Exception -> 0x007c }
        r3 = r3.b;	 Catch:{ Exception -> 0x007c }
        r3 = r3.remaining();	 Catch:{ Exception -> 0x007c }
        if (r2 < r3) goto L_0x008a;
    L_0x0028:
        r2 = r6.g;	 Catch:{ Exception -> 0x007c }
        r2 = r2.b;	 Catch:{ Exception -> 0x007c }
        r3 = r2.remaining();	 Catch:{ Exception -> 0x007c }
        r2 = k;	 Catch:{ Exception -> 0x007c }
        r2 = r2 - r3;
        r4 = r6.g;	 Catch:{ Exception -> 0x007c }
        r4 = r4.b;	 Catch:{ Exception -> 0x007c }
        r5 = 0;
        r4.get(r1, r5, r3);	 Catch:{ Exception -> 0x007c }
        if (r2 <= 0) goto L_0x0052;
    L_0x003d:
        r4 = r6.h;	 Catch:{ Exception -> 0x007c }
        if (r4 == 0) goto L_0x0052;
    L_0x0041:
        r4 = r6.h;	 Catch:{ Exception -> 0x007c }
        r4 = r4.b;	 Catch:{ Exception -> 0x007c }
        r4 = r4.remaining();	 Catch:{ Exception -> 0x007c }
        if (r4 < r2) goto L_0x0081;
    L_0x004b:
        r4 = r6.h;	 Catch:{ Exception -> 0x007c }
        r4 = r4.b;	 Catch:{ Exception -> 0x007c }
        r4.get(r1, r3, r2);	 Catch:{ Exception -> 0x007c }
    L_0x0052:
        r6.j();	 Catch:{ Exception -> 0x007c }
    L_0x0055:
        r0 = r1;
        goto L_0x0006;
    L_0x0057:
        r1 = r6.g;
        r2 = d;
        r3 = b;
        r2 = r2 + r3;
        r3 = d;
        r2 = r2 * r3;
        r3 = c;
        r2 = r2 % r3;
        r3 = a;
        if (r2 == r3) goto L_0x0072;
    L_0x0068:
        r2 = d();
        d = r2;
        r2 = 47;
        a = r2;
    L_0x0072:
        r1 = r1.a;
    L_0x0074:
        r2 = 1;
        switch(r2) {
            case 0: goto L_0x0074;
            case 1: goto L_0x0007;
            default: goto L_0x0078;
        };
    L_0x0078:
        switch(r4) {
            case 0: goto L_0x0007;
            case 1: goto L_0x0074;
            default: goto L_0x007b;
        };
    L_0x007b:
        goto L_0x0078;
    L_0x007c:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0006;
    L_0x0081:
        r2 = r6.h;	 Catch:{ Exception -> 0x007c }
        r2 = r2.b;	 Catch:{ Exception -> 0x007c }
        r2 = r2.remaining();	 Catch:{ Exception -> 0x007c }
        goto L_0x004b;
    L_0x008a:
        r2 = r6.g;	 Catch:{ Exception -> 0x007c }
        r2 = r2.b;	 Catch:{ Exception -> 0x007c }
        r3 = 0;
        r4 = k;	 Catch:{ Exception -> 0x007c }
        r2.get(r1, r3, r4);	 Catch:{ Exception -> 0x007c }
        goto L_0x0055;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.a.h.c(int):byte[]");
    }
}
