package com.immersion.hapticmediasdk;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import com.immersion.hapticmediasdk.b.b;
import com.immersion.hapticmediasdk.b.d;
import twitter4j.TwitterResponse;

public abstract class a {
    public static int d = 25;
    public static int e = 0;
    public static int f = 1;
    public static int g = 2;
    public a a = a.NOT_INITIALIZED;
    public boolean b = false;
    public d c;
    private HandlerThread h;
    private Handler i;
    private Context j;
    private d k;

    public enum a {
        NOT_INITIALIZED,
        INITIALIZED,
        PLAYING,
        STOPPED,
        STOPPED_DUE_TO_ERROR,
        PAUSED,
        PAUSED_DUE_TO_TIMEOUT;
        
        public static int j = 6;
        public static int k = 1;
        public static int l = 2;
        public static int m;

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        static {
            /*
            r7 = 4;
            r6 = 3;
            r5 = 2;
            r4 = 1;
            r3 = 0;
            r0 = new com.immersion.hapticmediasdk.a$a;
            r1 = "NOT_INITIALIZED";
            r0.<init>(r1, r3);
            NOT_INITIALIZED = r0;
            r0 = new com.immersion.hapticmediasdk.a$a;
            r1 = "INITIALIZED";
            r0.<init>(r1, r4);
            INITIALIZED = r0;
            r0 = new com.immersion.hapticmediasdk.a$a;
            r1 = "PLAYING";
            r0.<init>(r1, r5);
            PLAYING = r0;
            r0 = new com.immersion.hapticmediasdk.a$a;
            r1 = "STOPPED";
            r0.<init>(r1, r6);
            STOPPED = r0;
            r0 = new com.immersion.hapticmediasdk.a$a;
            r1 = "STOPPED_DUE_TO_ERROR";
            r0.<init>(r1, r7);
            STOPPED_DUE_TO_ERROR = r0;
            r0 = new com.immersion.hapticmediasdk.a$a;
            r1 = "PAUSED";
            r2 = 5;
            r0.<init>(r1, r2);
            PAUSED = r0;
            r0 = new com.immersion.hapticmediasdk.a$a;
            r1 = "PAUSED_DUE_TO_TIMEOUT";
            r2 = 6;
            r0.<init>(r1, r2);
            PAUSED_DUE_TO_TIMEOUT = r0;
            r0 = new com.immersion.hapticmediasdk.a$a;
            r1 = "PAUSED_DUE_TO_BUFFERING";
            r2 = 7;
            r0.<init>(r1, r2);
        L_0x004e:
            switch(r4) {
                case 0: goto L_0x004e;
                case 1: goto L_0x0055;
                default: goto L_0x0051;
            };
        L_0x0051:
            switch(r3) {
                case 0: goto L_0x0055;
                case 1: goto L_0x004e;
                default: goto L_0x0054;
            };
        L_0x0054:
            goto L_0x0051;
        L_0x0055:
            h = r0;
            r0 = new com.immersion.hapticmediasdk.a$a;
            r1 = a();
            r2 = k;
            r1 = r1 + r2;
            r2 = a();
            r1 = r1 * r2;
            r2 = l;
            r1 = r1 % r2;
            r2 = m;
            if (r1 == r2) goto L_0x0078;
        L_0x006c:
            r1 = a();
            j = r1;
            r1 = a();
            m = r1;
        L_0x0078:
            r1 = "DISPOSED";
            r2 = 8;
            r0.<init>(r1, r2);
            i = r0;
            r0 = 9;
            r0 = new com.immersion.hapticmediasdk.a.a[r0];
            r1 = NOT_INITIALIZED;
            r0[r3] = r1;
            r1 = INITIALIZED;
            r0[r4] = r1;
            r1 = PLAYING;
            r0[r5] = r1;
            r1 = STOPPED;
            r0[r6] = r1;
            r1 = STOPPED_DUE_TO_ERROR;
            r0[r7] = r1;
            r1 = 5;
            r2 = PAUSED;
            r0[r1] = r2;
            r1 = 6;
            r2 = PAUSED_DUE_TO_TIMEOUT;
            r0[r1] = r2;
            r1 = 7;
            r2 = h;
            r0[r1] = r2;
            r1 = 8;
            r2 = i;
            r0[r1] = r2;
            n = r0;
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.a.a.<clinit>():void");
        }

        public static int a() {
            return 37;
        }

        public static int b() {
            return 1;
        }
    }

    public a(int i, Context context) {
        this.j = context;
        if (((d + f) * d) % g != e) {
            d = 24;
            e = a();
        }
        this.k = new d();
    }

    public static int a() {
        return 96;
    }

    public static int e() {
        return 2;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int a(long r6) {
        /*
        r5 = this;
        r3 = 1;
        r0 = r5.c();
        r1 = d;
        r2 = f;
        r2 = r2 + r1;
        r1 = r1 * r2;
        r2 = g;
        r1 = r1 % r2;
        switch(r1) {
            case 0: goto L_0x001b;
            default: goto L_0x0011;
        };
    L_0x0011:
        r1 = a();
        d = r1;
        r1 = 98;
        e = r1;
    L_0x001b:
        r1 = com.immersion.hapticmediasdk.a.a.PLAYING;
        if (r0 == r1) goto L_0x0023;
    L_0x001f:
        r1 = com.immersion.hapticmediasdk.a.a.PAUSED_DUE_TO_TIMEOUT;
        if (r0 != r1) goto L_0x003a;
    L_0x0023:
        r0 = r5.c;
        r0.a(r6);
        r0 = r5.c;
        r1 = com.immersion.hapticmediasdk.a.a.PLAYING;
        r0 = r0.a(r1);
    L_0x0030:
        return r0;
    L_0x0031:
        r0 = r5.c;
        r0.a(r6);
        r0 = 0;
        goto L_0x0030;
    L_0x0038:
        r0 = -1;
        goto L_0x0030;
    L_0x003a:
        r1 = com.immersion.hapticmediasdk.a.a.PAUSED;
        if (r0 == r1) goto L_0x0042;
    L_0x003e:
        r1 = com.immersion.hapticmediasdk.a.a.h;
        if (r0 != r1) goto L_0x0038;
    L_0x0042:
        switch(r3) {
            case 0: goto L_0x0042;
            case 1: goto L_0x0031;
            default: goto L_0x0045;
        };
    L_0x0045:
        switch(r3) {
            case 0: goto L_0x0042;
            case 1: goto L_0x0031;
            default: goto L_0x0048;
        };
    L_0x0048:
        goto L_0x0045;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.a.a(long):int");
    }

    public abstract int a(String str);

    public int b() {
        try {
            if (this.j.getPackageManager().checkPermission("android.permission.VIBRATE", this.j.getPackageName()) == 0) {
                this.h = new HandlerThread("SDK Monitor");
                this.h.start();
                try {
                    this.i = new Handler(this.h.getLooper());
                    Handler handler = this.i;
                    int i = d;
                    switch ((i * (f + i)) % g) {
                        case TwitterResponse.NONE /*0*/:
                            break;
                        default:
                            d = a();
                            e = 93;
                            break;
                    }
                    this.c = new d(handler, this.j, this.k);
                    return 0;
                } catch (Exception e) {
                    throw e;
                }
            }
            b.d("HapticContentSDK", "Failed to create a Haptic Content SDK instance.Vibrate permission denied.");
            return -3;
        } catch (Exception e2) {
            throw e2;
        }
    }

    public final a c() {
        return this.b ? a.i : this.c.g();
    }

    public final int d() {
        try {
            a c = c();
            if (c != a.INITIALIZED && c != a.STOPPED) {
                return -1;
            }
            this.c.a(0);
            d dVar = this.c;
            a aVar = a.PLAYING;
            if (((a() + f) * a()) % e() != e) {
                d = a();
                e = a();
            }
            try {
                return dVar.a(aVar);
            } catch (Exception e) {
                throw e;
            }
        } catch (Exception e2) {
            throw e2;
        }
    }

    public final int f() {
        a c = c();
        if (!(c == a.PAUSED || c == a.PLAYING)) {
            if (c != a.STOPPED) {
                return -1;
            }
            while (true) {
                try {
                    int[] iArr = new int[-1];
                } catch (Exception e) {
                    d = 99;
                }
            }
        }
        this.c.f();
        return this.c.a(a.PLAYING);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void finalize() {
        /*
        r2 = this;
        r2.i();	 Catch:{ Throwable -> 0x000c }
        super.finalize();
        return;
    L_0x0007:
        r0 = move-exception;
        super.finalize();
        throw r0;
    L_0x000c:
        r0 = move-exception;
        throw r0;	 Catch:{ all -> 0x0007 }
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.a.finalize():void");
    }

    public final int g() {
        String str = null;
        try {
            a c = c();
            if (c != a.i) {
                if (c == a.STOPPED_DUE_TO_ERROR) {
                    while (true) {
                        try {
                            str.length();
                        } catch (Exception e) {
                            d = 21;
                        }
                    }
                } else {
                    try {
                        return this.c.a(a.PAUSED);
                    } catch (Exception e2) {
                        throw e2;
                    }
                }
            }
            return -1;
        } catch (Exception e22) {
            throw e22;
        }
    }

    public final int h() {
        a c = c();
        if (c == a.i || c == a.NOT_INITIALIZED) {
            return -1;
        }
        int a = this.c.a(a.STOPPED);
        while (true) {
            switch (1) {
                case TwitterResponse.NONE /*0*/:
                    break;
                case TwitterResponse.READ /*1*/:
                    return a;
                default:
                    while (true) {
                        switch (1) {
                            case TwitterResponse.NONE /*0*/:
                                break;
                            case TwitterResponse.READ /*1*/:
                                return a;
                            default:
                        }
                    }
            }
        }
    }

    public final void i() {
        if (c() != a.i) {
            this.c.a(a.NOT_INITIALIZED);
            this.h.quit();
            this.h = null;
            this.c = null;
            this.b = true;
        }
    }
}
