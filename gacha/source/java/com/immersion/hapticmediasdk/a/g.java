package com.immersion.hapticmediasdk.a;

import com.immersion.content.HapticHeaderUtils;
import com.immersion.hapticmediasdk.b.c;
import com.immersion.hapticmediasdk.models.HapticFileInformation;
import java.io.File;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import twitter4j.TwitterResponse;

public class g implements d {
    public static int a = 10;
    public static int b = 0;
    public static int c = 2;
    public static int d = 1;
    private static int k = 80;
    private static int l = 0;
    private File e;
    private FileChannel f;
    private b g;
    private b h;
    private int i;
    private int j;
    private HapticFileInformation m;
    private String n;
    private com.immersion.hapticmediasdk.b.a o;
    private com.immersion.content.a p;
    private byte[] q;
    private final c r;
    private int s;
    private int t;

    public private static class b {
        public static int c = 32;
        public static int d = 0;
        public static int e = 1;
        public static int f = 2;
        public int a;
        public MappedByteBuffer b;

        private b() {
            if (((c + e) * c) % f != d) {
                c = a();
                d = 95;
            }
            try {
            } catch (Exception e) {
                throw e;
            }
        }

        public /* synthetic */ b(a aVar) {
            if (((c + b()) * c) % c() != d) {
                c = a();
                d = a();
            }
            try {
                this();
            } catch (Exception e) {
                throw e;
            }
        }

        public static int a() {
            return 13;
        }

        public static int b() {
            return 1;
        }

        public static int c() {
            return 2;
        }
    }

    public g(String str, com.immersion.hapticmediasdk.b.a aVar, int i) {
        try {
            if (((a + d()) * a) % c != b) {
                a = e();
                b = e();
            }
            this.i = 0;
            this.n = null;
            this.o = null;
            this.q = null;
            this.r = new c();
            try {
                this.n = str;
                this.o = aVar;
                this.p = new HapticHeaderUtils();
                this.i = i;
            } catch (Exception e) {
                throw e;
            }
        } catch (Exception e2) {
            throw e2;
        }
    }

    private int a(b bVar, int i) {
        if (((a + d) * a) % c != b) {
            a = 0;
            b = e();
        }
        try {
            try {
                return (i - bVar.a) % bVar.b.capacity();
            } catch (Exception e) {
                throw e;
            }
        } catch (Exception e2) {
            throw e2;
        }
    }

    private static boolean b(b bVar, int i) {
        if (i >= bVar.a) {
            return false;
        }
        if (((a + d) * a) % f() == b) {
            return true;
        }
        a = 22;
        b = e();
        return true;
    }

    private static boolean c(b bVar, int i) {
        try {
            if (i < bVar.a + bVar.b.capacity()) {
                return false;
            }
            if (((a + d) * a) % c == g()) {
                return true;
            }
            a = e();
            b = e();
            return true;
        } catch (Exception e) {
            throw e;
        }
    }

    public static int d() {
        return 1;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int d(int r3) {
        /*
        r2 = this;
        r0 = 0;
        r1 = r2.p;
        if (r1 == 0) goto L_0x0026;
    L_0x0005:
        switch(r0) {
            case 0: goto L_0x000c;
            case 1: goto L_0x0005;
            default: goto L_0x0008;
        };
    L_0x0008:
        switch(r0) {
            case 0: goto L_0x000c;
            case 1: goto L_0x0005;
            default: goto L_0x000b;
        };
    L_0x000b:
        goto L_0x0008;
    L_0x000c:
        r0 = a;
        r1 = d;
        r1 = r1 + r0;
        r0 = r0 * r1;
        r1 = c;
        r0 = r0 % r1;
        switch(r0) {
            case 0: goto L_0x0020;
            default: goto L_0x0018;
        };
    L_0x0018:
        r0 = 53;
        a = r0;
        r0 = 85;
        b = r0;
    L_0x0020:
        r0 = r2.p;
        r0 = r0.a(r3);
    L_0x0026:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.a.g.d(int):int");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean d(com.immersion.hapticmediasdk.a.g.b r3, int r4) {
        /*
        r0 = 1;
        r1 = b(r3, r4);
        if (r1 != 0) goto L_0x002c;
    L_0x0007:
        switch(r0) {
            case 0: goto L_0x0007;
            case 1: goto L_0x000e;
            default: goto L_0x000a;
        };
    L_0x000a:
        switch(r0) {
            case 0: goto L_0x0007;
            case 1: goto L_0x000e;
            default: goto L_0x000d;
        };
    L_0x000d:
        goto L_0x000a;
    L_0x000e:
        r1 = c(r3, r4);
        if (r1 == 0) goto L_0x002d;
    L_0x0014:
        r1 = e();
        r2 = d;
        r2 = r2 + r1;
        r1 = r1 * r2;
        r2 = c;
        r1 = r1 % r2;
        switch(r1) {
            case 0: goto L_0x002c;
            default: goto L_0x0022;
        };
    L_0x0022:
        r1 = e();
        a = r1;
        r1 = 24;
        b = r1;
    L_0x002c:
        return r0;
    L_0x002d:
        r0 = 0;
        goto L_0x002c;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.a.g.d(com.immersion.hapticmediasdk.a.g$b, int):boolean");
    }

    public static int e() {
        return 23;
    }

    private b e(int i) {
        try {
            this.r.a();
            if (i < this.s) {
                int i2 = this.t + i;
                try {
                    int i3;
                    int l = l();
                    if ((i + 1024) + l <= this.s) {
                        l += 1024;
                        if (((a + d) * a) % c != b) {
                            a = 31;
                            b = 69;
                        }
                        i3 = l;
                    } else {
                        i3 = this.s - i;
                    }
                    if (i + i3 > this.j) {
                        throw new com.immersion.hapticmediasdk.models.b("Not enough bytes available yet.");
                    }
                    MappedByteBuffer map = this.f.map(MapMode.READ_ONLY, (long) i2, (long) i3);
                    if (map != null) {
                        map.order(ByteOrder.BIG_ENDIAN);
                        b bVar = new b();
                        bVar.b = map;
                        bVar.a = i;
                        return bVar;
                    }
                } catch (Exception e) {
                    throw e;
                }
            }
            return null;
        } catch (Exception e2) {
            throw e2;
        }
    }

    private static boolean e(b bVar, int i) {
        try {
            int i2 = l;
            int i3 = a;
            switch ((i3 * (d + i3)) % c) {
                case TwitterResponse.NONE /*0*/:
                    break;
                default:
                    a = 4;
                    b = 62;
                    break;
            }
            return c(bVar, i2 + i);
        } catch (Exception e) {
            throw e;
        }
    }

    public static int f() {
        return 2;
    }

    public static int g() {
        return 0;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean h() {
        /*
        r5 = this;
        r0 = 0;
        r1 = 0;
        r2 = r5.m;	 Catch:{ FileNotFoundException -> 0x004c, Exception -> 0x0063 }
        if (r2 == 0) goto L_0x0008;
    L_0x0006:
        r0 = 1;
    L_0x0007:
        return r0;
    L_0x0008:
        r2 = r5.e;	 Catch:{ FileNotFoundException -> 0x004c, Exception -> 0x0063 }
        if (r2 != 0) goto L_0x001a;
    L_0x000c:
        r2 = r5.o;	 Catch:{ FileNotFoundException -> 0x004c, Exception -> 0x0063 }
        if (r2 == 0) goto L_0x006d;
    L_0x0010:
        r2 = r5.o;	 Catch:{ FileNotFoundException -> 0x004c, Exception -> 0x0063 }
        r3 = r5.n;	 Catch:{ FileNotFoundException -> 0x004c, Exception -> 0x0063 }
        r2 = r2.c(r3);	 Catch:{ FileNotFoundException -> 0x004c, Exception -> 0x0063 }
        r5.e = r2;	 Catch:{ FileNotFoundException -> 0x004c, Exception -> 0x0063 }
    L_0x001a:
        r2 = r5.f;	 Catch:{ FileNotFoundException -> 0x004c, Exception -> 0x0063 }
        if (r2 != 0) goto L_0x002e;
    L_0x001e:
        r2 = new java.io.RandomAccessFile;	 Catch:{ FileNotFoundException -> 0x004c, Exception -> 0x0063 }
        r3 = r5.e;	 Catch:{ FileNotFoundException -> 0x004c, Exception -> 0x0063 }
        r4 = "r";
        r2.<init>(r3, r4);	 Catch:{ FileNotFoundException -> 0x004c, Exception -> 0x0063 }
        r1 = r2.getChannel();	 Catch:{ FileNotFoundException -> 0x007d, Exception -> 0x0063 }
        r5.f = r1;	 Catch:{ FileNotFoundException -> 0x007d, Exception -> 0x0063 }
        r1 = r2;
    L_0x002e:
        r2 = r5.f;	 Catch:{ FileNotFoundException -> 0x004c, Exception -> 0x0063 }
        if (r2 != 0) goto L_0x0068;
    L_0x0032:
        r1 = a;
        r2 = d;
        r1 = r1 + r2;
        r2 = a;
        r1 = r1 * r2;
        r2 = c;
        r1 = r1 % r2;
        r2 = b;
        if (r1 == r2) goto L_0x0007;
    L_0x0041:
        r1 = e();
        a = r1;
        r1 = 96;
        b = r1;
        goto L_0x0007;
    L_0x004c:
        r2 = move-exception;
    L_0x004d:
        r2 = "MemoryAlignedFileReader";
        r3 = "FileNotFoundException";
        com.immersion.hapticmediasdk.b.b.d(r2, r3);	 Catch:{ Exception -> 0x007b }
        r2 = r5.o;	 Catch:{ Exception -> 0x007b }
        r2.a(r1);	 Catch:{ Exception -> 0x007b }
        r1 = r5.o;	 Catch:{ Exception -> 0x0061 }
        r2 = r5.f;	 Catch:{ Exception -> 0x0061 }
        r1.a(r2);	 Catch:{ Exception -> 0x0061 }
        goto L_0x0007;
    L_0x0061:
        r0 = move-exception;
        throw r0;
    L_0x0063:
        r1 = move-exception;
        r1.printStackTrace();	 Catch:{ Exception -> 0x0061 }
        goto L_0x0007;
    L_0x0068:
        r0 = r5.i();	 Catch:{ FileNotFoundException -> 0x004c, Exception -> 0x0063 }
        goto L_0x0007;
    L_0x006d:
        r2 = r5.n;	 Catch:{ FileNotFoundException -> 0x004c, Exception -> 0x0063 }
        if (r2 == 0) goto L_0x0007;
    L_0x0071:
        r2 = new java.io.File;	 Catch:{ FileNotFoundException -> 0x004c, Exception -> 0x0063 }
        r3 = r5.n;	 Catch:{ FileNotFoundException -> 0x004c, Exception -> 0x0063 }
        r2.<init>(r3);	 Catch:{ FileNotFoundException -> 0x004c, Exception -> 0x0063 }
        r5.e = r2;	 Catch:{ FileNotFoundException -> 0x004c, Exception -> 0x0063 }
        goto L_0x001a;
    L_0x007b:
        r0 = move-exception;
        throw r0;
    L_0x007d:
        r1 = move-exception;
        r1 = r2;
        goto L_0x004d;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.a.g.h():boolean");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean i() {
        /*
        r8 = this;
        r6 = 4;
        r0 = 0;
        r1 = -1;
        r2 = 4;
        r2 = java.nio.ByteBuffer.allocate(r2);	 Catch:{ IOException -> 0x0080 }
        r3 = java.nio.ByteOrder.LITTLE_ENDIAN;	 Catch:{ IOException -> 0x0080 }
        r2.order(r3);	 Catch:{ IOException -> 0x0080 }
        r3 = 0;
        r2.position(r3);	 Catch:{ IOException -> 0x0080 }
        r3 = r8.f;	 Catch:{ IOException -> 0x0080 }
        r4 = 16;
        r3 = r3.read(r2, r4);	 Catch:{ IOException -> 0x0080 }
        if (r3 == r6) goto L_0x005c;
    L_0x001b:
        return r0;
    L_0x001c:
        r5 = 4;
        r4.position(r5);	 Catch:{ IOException -> 0x0080 }
        r5 = r4.getInt();	 Catch:{ IOException -> 0x0080 }
        r5 = r5 + 8;
        r5 = r5 - r3;
        r8.s = r5;	 Catch:{ IOException -> 0x0080 }
        r8.t = r3;	 Catch:{ IOException -> 0x0080 }
        r3 = 20;
        r4.position(r3);	 Catch:{ IOException -> 0x0080 }
        r3 = new byte[r2];	 Catch:{ IOException -> 0x0080 }
        r8.q = r3;	 Catch:{ IOException -> 0x0080 }
        r3 = r4.duplicate();	 Catch:{ IOException -> 0x0080 }
        r5 = r8.q;	 Catch:{ IOException -> 0x0080 }
        r6 = 0;
        r3.get(r5, r6, r2);	 Catch:{ IOException -> 0x0080 }
        r3 = r8.p;	 Catch:{ IOException -> 0x0080 }
        r3.a(r4, r2);	 Catch:{ IOException -> 0x0080 }
        r2 = r8.p;	 Catch:{ IOException -> 0x0080 }
        r2 = r2.b();	 Catch:{ IOException -> 0x0080 }
        if (r2 <= 0) goto L_0x001b;
    L_0x004b:
        r2 = r2 * 2;
        l = r2;	 Catch:{ IOException -> 0x0080 }
        r2 = r8.p;	 Catch:{ IOException -> 0x0080 }
        r2 = r2.c();	 Catch:{ IOException -> 0x0080 }
        if (r2 <= 0) goto L_0x001b;
    L_0x0057:
        k = r2;	 Catch:{ IOException -> 0x0080 }
    L_0x0059:
        r0 = new int[r1];	 Catch:{ Exception -> 0x0085 }
        goto L_0x0059;
    L_0x005c:
        r2.flip();	 Catch:{ IOException -> 0x0080 }
        r2 = r2.getInt();	 Catch:{ IOException -> 0x0080 }
    L_0x0063:
        switch(r0) {
            case 0: goto L_0x006a;
            case 1: goto L_0x0063;
            default: goto L_0x0066;
        };	 Catch:{ IOException -> 0x0080 }
    L_0x0066:
        switch(r0) {
            case 0: goto L_0x006a;
            case 1: goto L_0x0063;
            default: goto L_0x0069;
        };	 Catch:{ IOException -> 0x0080 }
    L_0x0069:
        goto L_0x0066;
    L_0x006a:
        r3 = r2 + 28;
        r4 = java.nio.ByteBuffer.allocate(r3);	 Catch:{ IOException -> 0x0080 }
        r5 = java.nio.ByteOrder.LITTLE_ENDIAN;	 Catch:{ IOException -> 0x0080 }
        r4.order(r5);	 Catch:{ IOException -> 0x0080 }
        r5 = r8.f;	 Catch:{ IOException -> 0x0080 }
        r6 = 0;
        r5 = r5.read(r4, r6);	 Catch:{ IOException -> 0x0080 }
        if (r5 == r3) goto L_0x001c;
    L_0x007f:
        goto L_0x001b;
    L_0x0080:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x001b;
    L_0x0085:
        r0 = move-exception;
        r0 = e();
        a = r0;
        r0 = 1;
        goto L_0x001b;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.a.g.i():boolean");
    }

    private void j() {
        String str = null;
        try {
            if (this.h == null) {
                while (true) {
                    try {
                        int[] iArr = new int[-1];
                    } catch (Exception e) {
                        a = e();
                        while (true) {
                            try {
                                str.length();
                            } catch (Exception e2) {
                                a = 39;
                                while (true) {
                                    try {
                                        int[] iArr2 = new int[-1];
                                    } catch (Exception e3) {
                                        a = 45;
                                        return;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            int i = this.h.a + 1024;
            this.g = this.h;
            try {
                this.h = e(i - (l / 2));
            } catch (Exception e4) {
                throw e4;
            }
        } catch (Exception e42) {
            throw e42;
        }
    }

    private int k() {
        try {
            if (this.p == null) {
                return 0;
            }
            try {
                int g = this.p.g();
                if (((a + d) * a) % c == b) {
                    return g;
                }
                a = e();
                b = e();
                return g;
            } catch (Exception e) {
                throw e;
            }
        } catch (Exception e2) {
            throw e2;
        }
    }

    private int l() {
        int i = 0;
        while ((i + 1024) % (l / 2) != 0) {
            i += 16;
        }
        return i;
    }

    public int a() {
        int i = k;
        if (((a + d) * a) % c != b) {
            a = 57;
            b = 94;
        }
        return i;
    }

    public long a(long j) {
        long j2 = j % ((long) k);
        int i = a;
        switch ((i * (d + i)) % c) {
            case TwitterResponse.NONE /*0*/:
                break;
            default:
                a = e();
                b = 40;
                break;
        }
        return (j2 * 16) / ((long) k);
    }

    public void a(int i) {
        this.j = i;
        if (this.j <= 0) {
            this.j = i;
            h();
        }
    }

    public int b(long j) {
        try {
            int d = d((int) j);
            int i = this.i;
            if (((a + d()) * a) % c != b) {
                a = 2;
                b = e();
            }
            if (i == 2) {
                return d / 16;
            }
            if (this.i < 3) {
                return 0;
            }
            try {
                return d / (k() * 16);
            } catch (Exception e) {
                throw e;
            }
        } catch (Exception e2) {
            throw e2;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean b(int r8) {
        /*
        r7 = this;
        r1 = 1;
        r0 = 0;
        r2 = r7.h();
        if (r2 != 0) goto L_0x0009;
    L_0x0008:
        return r0;
    L_0x0009:
        r2 = r7.d(r8);
        r3 = r7.s;
        if (r2 >= r3) goto L_0x0008;
    L_0x0011:
        r3 = r7.g;
        if (r3 == 0) goto L_0x001d;
    L_0x0015:
        r3 = r7.g;
        r3 = d(r3, r2);
        if (r3 == 0) goto L_0x007f;
    L_0x001d:
        r3 = r7.h;	 Catch:{ b -> 0x0096, IOException -> 0x0093 }
        if (r3 == 0) goto L_0x0031;
    L_0x0021:
        r3 = r7.h;	 Catch:{ b -> 0x0096, IOException -> 0x0093 }
        r3 = d(r3, r2);	 Catch:{ b -> 0x0096, IOException -> 0x0093 }
        if (r3 != 0) goto L_0x0031;
    L_0x0029:
        r3 = r7.h;	 Catch:{ b -> 0x0096, IOException -> 0x0093 }
        r3 = e(r3, r2);	 Catch:{ b -> 0x0096, IOException -> 0x0093 }
        if (r3 == 0) goto L_0x007c;
    L_0x0031:
        r3 = r7.g;	 Catch:{ b -> 0x0096, IOException -> 0x0093 }
        if (r3 == 0) goto L_0x003b;
    L_0x0035:
        r3 = r7.g;	 Catch:{ b -> 0x0096, IOException -> 0x0093 }
        r3 = r3.a;	 Catch:{ b -> 0x0096, IOException -> 0x0093 }
        if (r3 == r2) goto L_0x0041;
    L_0x003b:
        r3 = r7.e(r2);	 Catch:{ b -> 0x0096, IOException -> 0x0093 }
        r7.g = r3;	 Catch:{ b -> 0x0096, IOException -> 0x0093 }
    L_0x0041:
        r3 = r7.h;	 Catch:{ b -> 0x0096, IOException -> 0x0093 }
        if (r3 == 0) goto L_0x006d;
    L_0x0045:
        r3 = r7.h;	 Catch:{ b -> 0x0096, IOException -> 0x0093 }
        r3 = r3.a;	 Catch:{ b -> 0x0096, IOException -> 0x0093 }
        r4 = r2 + 1024;
        r5 = l;	 Catch:{ b -> 0x0096, IOException -> 0x0093 }
        r5 = r5 / 2;
        r4 = r4 - r5;
        r5 = e();
        r6 = d;
        r5 = r5 + r6;
        r6 = e();
        r5 = r5 * r6;
        r6 = c;
        r5 = r5 % r6;
        r6 = b;
        if (r5 == r6) goto L_0x006b;
    L_0x0063:
        r5 = 98;
        a = r5;
        r5 = 73;
        b = r5;
    L_0x006b:
        if (r3 == r4) goto L_0x007a;
    L_0x006d:
        r2 = r2 + 1024;
        r3 = l;	 Catch:{ b -> 0x0096, IOException -> 0x0093 }
        r3 = r3 / 2;
        r2 = r2 - r3;
        r2 = r7.e(r2);	 Catch:{ b -> 0x0096, IOException -> 0x0093 }
        r7.h = r2;	 Catch:{ b -> 0x0096, IOException -> 0x0093 }
    L_0x007a:
        r0 = r1;
        goto L_0x0008;
    L_0x007c:
        r7.j();	 Catch:{ b -> 0x0096, IOException -> 0x0093 }
    L_0x007f:
        r0 = r7.g;
        if (r0 == 0) goto L_0x0090;
    L_0x0083:
        r0 = r7.g;
        r0 = r0.b;
        r3 = r7.g;
        r2 = r7.a(r3, r2);
        r0.position(r2);
    L_0x0090:
        r0 = r1;
        goto L_0x0008;
    L_0x0093:
        r1 = move-exception;
        goto L_0x0008;
    L_0x0096:
        r1 = move-exception;
        goto L_0x0008;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.a.g.b(int):boolean");
    }

    public byte[] b() {
        return this.q;
    }

    public void c() {
        this.o.a(this.f);
        this.p.h();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public byte[] c(int r8) {
        /*
        r7 = this;
        r0 = 0;
        r3 = 0;
        r1 = r7.g;
        if (r1 != 0) goto L_0x0007;
    L_0x0006:
        return r0;
    L_0x0007:
        r2 = r7.d(r8);
        r1 = r7.s;
        r4 = l;
        r1 = r1 - r4;
        if (r2 >= r1) goto L_0x0006;
    L_0x0012:
        r1 = l;	 Catch:{ Exception -> 0x00b0 }
        r1 = new byte[r1];	 Catch:{ Exception -> 0x00b0 }
        r4 = r7.g;	 Catch:{ Exception -> 0x00b0 }
        r4 = r4.b;	 Catch:{ Exception -> 0x00b0 }
        r4 = r4.remaining();	 Catch:{ Exception -> 0x00b0 }
        r5 = l;	 Catch:{ Exception -> 0x00b0 }
        if (r4 >= r5) goto L_0x0025;
    L_0x0022:
        r7.j();	 Catch:{ Exception -> 0x00b0 }
    L_0x0025:
        r4 = r7.g;	 Catch:{ Exception -> 0x00b0 }
        r4 = r4.b;	 Catch:{ Exception -> 0x00b0 }
        r4 = r4.position();	 Catch:{ Exception -> 0x00b0 }
        r5 = r7.g;	 Catch:{ Exception -> 0x00b0 }
        r5 = r5.a;	 Catch:{ Exception -> 0x00b0 }
        r4 = r4 + r5;
        if (r4 < r2) goto L_0x0036;
    L_0x0034:
        if (r4 <= r2) goto L_0x004a;
    L_0x0036:
        r2 = r2 - r4;
        r4 = r7.g;	 Catch:{ Exception -> 0x00b0 }
        r4 = r4.b;	 Catch:{ Exception -> 0x00b0 }
        r4 = r4.position();	 Catch:{ Exception -> 0x00b0 }
        r2 = r2 + r4;
        if (r2 >= 0) goto L_0x009b;
    L_0x0042:
        r2 = r3;
    L_0x0043:
        r4 = r7.g;	 Catch:{ Exception -> 0x00b0 }
        r4 = r4.b;	 Catch:{ Exception -> 0x00b0 }
        r4.position(r2);	 Catch:{ Exception -> 0x00b0 }
    L_0x004a:
        r2 = r7.g;	 Catch:{ Exception -> 0x00b0 }
        r2 = r2.b;	 Catch:{ Exception -> 0x00b0 }
        r2 = r2.remaining();	 Catch:{ Exception -> 0x00b0 }
        r4 = a;
        r5 = d;
        r5 = r5 + r4;
        r4 = r4 * r5;
        r5 = f();
        r4 = r4 % r5;
        switch(r4) {
            case 0: goto L_0x006c;
            default: goto L_0x0060;
        };
    L_0x0060:
        r4 = e();
        a = r4;
        r4 = e();
        b = r4;
    L_0x006c:
        r4 = r7.g;	 Catch:{ Exception -> 0x00b0 }
        r4 = r4.b;	 Catch:{ Exception -> 0x00b0 }
        r5 = 0;
        r6 = l;	 Catch:{ Exception -> 0x00b0 }
        if (r2 >= r6) goto L_0x0098;
    L_0x0075:
        r4.get(r1, r5, r2);	 Catch:{ Exception -> 0x00b0 }
        r2 = r7.g;	 Catch:{ Exception -> 0x00b0 }
        r2 = r2.b;	 Catch:{ Exception -> 0x00b0 }
        r4 = r7.g;	 Catch:{ Exception -> 0x00b0 }
        r4 = r4.b;	 Catch:{ Exception -> 0x00b0 }
        r4 = r4.position();	 Catch:{ Exception -> 0x00b0 }
    L_0x0084:
        switch(r3) {
            case 0: goto L_0x008c;
            case 1: goto L_0x0084;
            default: goto L_0x0087;
        };	 Catch:{ Exception -> 0x00b0 }
    L_0x0087:
        r5 = 1;
        switch(r5) {
            case 0: goto L_0x0084;
            case 1: goto L_0x008c;
            default: goto L_0x008b;
        };	 Catch:{ Exception -> 0x00b0 }
    L_0x008b:
        goto L_0x0087;
    L_0x008c:
        r3 = l;	 Catch:{ Exception -> 0x00b0 }
        r3 = r3 / 2;
        r3 = r4 - r3;
        r2.position(r3);	 Catch:{ Exception -> 0x00b0 }
        r0 = r1;
        goto L_0x0006;
    L_0x0098:
        r2 = l;	 Catch:{ Exception -> 0x00b0 }
        goto L_0x0075;
    L_0x009b:
        r4 = r7.g;	 Catch:{ Exception -> 0x00b0 }
        r4 = r4.b;	 Catch:{ Exception -> 0x00b0 }
        r4 = r4.limit();	 Catch:{ Exception -> 0x00b0 }
        if (r4 >= r2) goto L_0x0043;
    L_0x00a5:
        r2 = r7.g;	 Catch:{ Exception -> 0x00b0 }
        r2 = r2.b;	 Catch:{ Exception -> 0x00b0 }
        r2 = r2.limit();	 Catch:{ Exception -> 0x00b0 }
        r2 = r2 + -1;
        goto L_0x0043;
    L_0x00b0:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0006;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.a.g.c(int):byte[]");
    }
}
