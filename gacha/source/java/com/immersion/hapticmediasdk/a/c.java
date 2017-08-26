package com.immersion.hapticmediasdk.a;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import com.immersion.content.EndpointWarp;
import java.util.ArrayList;
import java.util.Iterator;
import jp.tjkapp.adfurikunsdk.moviereward.ApiAccessUtil;
import twitter4j.TwitterResponse;

public class c extends Thread {
    public static int d = 86;
    public static int e = 1;
    public static int f = 2;
    public static int g;
    private boolean A;
    private boolean B;
    private ArrayList C;
    private com.immersion.hapticmediasdk.b.d D;
    private boolean E;
    private com.immersion.hapticmediasdk.b.a F;
    private final Runnable G;
    private final Runnable H;
    public Context a;
    public volatile boolean b;
    public volatile boolean c;
    private int h = 0;
    private final String i;
    private Handler j;
    private final Handler k;
    private b l;
    private Looper m;
    private d n;
    private EndpointWarp o;
    private final com.immersion.hapticmediasdk.b.c p = new com.immersion.hapticmediasdk.b.c();
    private Object q = new Object();
    private Object r = new Object();
    private int s;
    private int t;
    private int u;
    private long v;
    private int w;
    private int x;
    private int y;
    private long z;

    public private class a implements Runnable {
        public static int b = 45;
        public static int c = 1;
        public static int d = 2;
        public final /* synthetic */ c a;
        private final byte[] e;
        private final long f;
        private final long g;
        private final int h;
        private final long i;

        public a(c cVar, long j, long j2, byte[] bArr, int i, long j3) {
            try {
                this.a = cVar;
                this.e = bArr;
                this.f = j;
                int i2 = b;
                switch ((i2 * (c + i2)) % d) {
                    case TwitterResponse.NONE /*0*/:
                        break;
                    default:
                        b = 15;
                        c = a();
                        break;
                }
                try {
                    this.g = j2;
                    this.h = i;
                    this.i = j3;
                } catch (Exception e) {
                    throw e;
                }
            } catch (Exception e2) {
                throw e2;
            }
        }

        public static int a() {
            return 32;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
            r8 = this;
            r0 = r8.a;
            r0 = com.immersion.hapticmediasdk.a.c.l(r0);
            if (r0 == 0) goto L_0x007f;
        L_0x0008:
            r0 = r8.a;
            r1 = r0.r;
            monitor-enter(r1);
            r0 = r8.a;	 Catch:{ all -> 0x0083 }
            r0 = com.immersion.hapticmediasdk.a.c.n(r0);	 Catch:{ all -> 0x0083 }
            r0.remove(r8);	 Catch:{ all -> 0x0083 }
            monitor-exit(r1);	 Catch:{ all -> 0x0083 }
            r0 = r8.f;
            r2 = r8.g;
            r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
            if (r0 < 0) goto L_0x0070;
        L_0x0021:
            r0 = r8.a;
            r0 = com.immersion.hapticmediasdk.a.c.o(r0);
            r0 = r0.b();
            if (r0 == 0) goto L_0x0049;
        L_0x002d:
            r0 = r8.a;
            r1 = com.immersion.hapticmediasdk.a.c.p(r0);
        L_0x0033:
            r0 = 1;
            switch(r0) {
                case 0: goto L_0x0033;
                case 1: goto L_0x003c;
                default: goto L_0x0037;
            };
        L_0x0037:
            r0 = 0;
            switch(r0) {
                case 0: goto L_0x003c;
                case 1: goto L_0x0033;
                default: goto L_0x003b;
            };
        L_0x003b:
            goto L_0x0037;
        L_0x003c:
            r2 = r8.e;
            r0 = r8.e;
            r3 = r0.length;
            r4 = r8.i;
            r0 = r8.h;
            r6 = (long) r0;
            r1.a(r2, r3, r4, r6);
        L_0x0049:
            r0 = r8.a;
            r1 = com.immersion.hapticmediasdk.a.c.q(r0);
            monitor-enter(r1);
            r0 = r8.a;	 Catch:{ all -> 0x0080 }
            r2 = r8.a;	 Catch:{ all -> 0x0080 }
            r2 = r2.h;	 Catch:{ all -> 0x0080 }
            com.immersion.hapticmediasdk.a.c.e(r0, r2);	 Catch:{ all -> 0x0080 }
            r0 = r8.a;	 Catch:{ all -> 0x0080 }
            r2 = r8.a;	 Catch:{ all -> 0x0080 }
            r2 = com.immersion.hapticmediasdk.a.c.r(r2);	 Catch:{ all -> 0x0080 }
            com.immersion.hapticmediasdk.a.c.f(r0, r2);	 Catch:{ all -> 0x0080 }
            r0 = r8.a;	 Catch:{ all -> 0x0080 }
            r2 = android.os.SystemClock.uptimeMillis();	 Catch:{ all -> 0x0080 }
            com.immersion.hapticmediasdk.a.c.a(r0, r2);	 Catch:{ all -> 0x0080 }
            monitor-exit(r1);	 Catch:{ all -> 0x0080 }
        L_0x0070:
            r0 = r8.a;
            r0 = com.immersion.hapticmediasdk.a.c.b(r0);
            r1 = r8.a;
            r1 = com.immersion.hapticmediasdk.a.c.s(r1);
            r0.post(r1);
        L_0x007f:
            return;
        L_0x0080:
            r0 = move-exception;
            monitor-exit(r1);	 Catch:{ all -> 0x0080 }
            throw r0;
        L_0x0083:
            r0 = move-exception;
            monitor-exit(r1);	 Catch:{ all -> 0x0083 }
            throw r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.a.c.a.run():void");
        }
    }

    public class b implements Runnable {
        public static int b = 48;
        public static int c = 0;
        public static int d = 1;
        public static int e = 2;
        public final /* synthetic */ c a;

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public b(com.immersion.hapticmediasdk.a.c r3) {
            /*
            r2 = this;
            r2.a = r3;
            r0 = b;
            r1 = d;
            r0 = r0 + r1;
            r1 = b;
            r0 = r0 * r1;
            r1 = a();
            r0 = r0 % r1;
            r1 = c;
            if (r0 == r1) goto L_0x001f;
        L_0x0013:
            r0 = b();
            b = r0;
            r0 = b();
            c = r0;
        L_0x001f:
            r0 = 0;
            switch(r0) {
                case 0: goto L_0x0028;
                case 1: goto L_0x001f;
                default: goto L_0x0023;
            };
        L_0x0023:
            r0 = 1;
            switch(r0) {
                case 0: goto L_0x001f;
                case 1: goto L_0x0028;
                default: goto L_0x0027;
            };
        L_0x0027:
            goto L_0x0023;
        L_0x0028:
            r2.<init>();
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.a.c.b.<init>(com.immersion.hapticmediasdk.a.c):void");
        }

        public static int a() {
            return 2;
        }

        public static int b() {
            return 15;
        }

        public void run() {
            try {
                c cVar = this.a;
                if (((b + d) * b) % e != c) {
                    b = b();
                    c = 40;
                }
                try {
                    c.k(cVar);
                } catch (Exception e) {
                    throw e;
                }
            } catch (Exception e2) {
                throw e2;
            }
        }
    }

    public private class c extends Handler {
        public static int b = 16;
        public static int c = 0;
        public static int d = 1;
        public final /* synthetic */ c a;

        private c(c cVar) {
            int i = 0;
            while (true) {
                try {
                    int[] iArr = new int[-1];
                } catch (Exception e) {
                    while (true) {
                        try {
                            i /= 0;
                        } catch (Exception e2) {
                            try {
                                this.a = cVar;
                                try {
                                    return;
                                } catch (Exception e3) {
                                    throw e3;
                                }
                            } catch (Exception e32) {
                                throw e32;
                            }
                        }
                    }
                }
            }
        }

        public static int a() {
            return 2;
        }

        public static int b() {
            return 85;
        }

        public void handleMessage(Message message) {
            int i = 0;
            String str = null;
            switch (message.what) {
                case TwitterResponse.READ /*1*/:
                    c.b(this.a).removeCallbacks(c.a(this.a));
                    c.a(this.a, message.arg1);
                    c.b(this.a, message.arg2);
                    c.c(this.a, 0);
                    c.c(this.a);
                    return;
                case TwitterResponse.READ_WRITE /*2*/:
                    Bundle data = message.getData();
                    c.a(this.a, data.getInt("playback_timecode"), data.getLong("playback_uptime"));
                    return;
                case TwitterResponse.READ_WRITE_DIRECTMESSAGES /*3*/:
                    if (c.d(this.a) == null) {
                        c.a(this.a, a.a(this.a.i, c.f(this.a)));
                    }
                    if (c.d(this.a) != null && this.a.h == 0) {
                        c.d(this.a, c.d(this.a).a());
                    }
                    if (c.d(this.a) != null) {
                        c.d(this.a).a(message.arg1);
                        return;
                    }
                    return;
                case ApiAccessUtil.SERVER_TYPE_AMAZON /*4*/:
                    c.h(this.a);
                    while (true) {
                        try {
                            i /= 0;
                        } catch (Exception e) {
                            while (true) {
                                try {
                                    str.length();
                                } catch (Exception e2) {
                                    return;
                                }
                            }
                        }
                    }
                case AdInfo.BANNER_KIND_BANNER5 /*5*/:
                    c.i(this.a);
                    return;
                case AdInfo.BANNER_KIND_BANNER8 /*8*/:
                    c.a(this.a, message);
                    return;
                case AdInfo.BANNER_KIND_INTERSTITIAL1 /*9*/:
                    c.j(this.a);
                    return;
                default:
                    return;
            }
        }
    }

    public class d implements Runnable {
        public static int b = 2;
        public static int c = 1;
        public static int d = 2;
        public static int e;
        public final /* synthetic */ c a;

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public d(com.immersion.hapticmediasdk.a.c r3) {
            /*
            r2 = this;
            r0 = 0;
        L_0x0001:
            switch(r0) {
                case 0: goto L_0x0008;
                case 1: goto L_0x0001;
                default: goto L_0x0004;
            };
        L_0x0004:
            switch(r0) {
                case 0: goto L_0x0008;
                case 1: goto L_0x0001;
                default: goto L_0x0007;
            };
        L_0x0007:
            goto L_0x0004;
        L_0x0008:
            r0 = b;
            r1 = c;
            r1 = r1 + r0;
            r0 = r0 * r1;
            r1 = d;
            r0 = r0 % r1;
            switch(r0) {
                case 0: goto L_0x0020;
                default: goto L_0x0014;
            };
        L_0x0014:
            r0 = a();
            b = r0;
            r0 = a();
            c = r0;
        L_0x0020:
            r2.a = r3;
            r2.<init>();
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.a.c.d.<init>(com.immersion.hapticmediasdk.a.c):void");
        }

        public static int a() {
            return 54;
        }

        public void run() {
            c.c(this.a);
        }
    }

    public c(Context context, String str, Handler handler, boolean z, com.immersion.hapticmediasdk.b.d dVar) {
        super("HapticPlaybackThread");
        int i = d;
        switch ((i * (e + i)) % f) {
            case TwitterResponse.NONE /*0*/:
                break;
            default:
                d = a();
                e = a();
                break;
        }
        this.A = false;
        this.B = false;
        this.b = false;
        this.c = false;
        this.E = false;
        this.G = new d(this);
        this.H = new b(this);
        this.i = str;
        this.k = handler;
        this.a = context;
        this.E = z;
        this.F = new com.immersion.hapticmediasdk.b.a(context);
        this.D = dVar;
        this.C = new ArrayList();
    }

    public static int a() {
        return 41;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static /* synthetic */ int a(com.immersion.hapticmediasdk.a.c r2, int r3) {
        /*
    L_0x0000:
        r0 = 1;
        switch(r0) {
            case 0: goto L_0x0000;
            case 1: goto L_0x0009;
            default: goto L_0x0004;
        };
    L_0x0004:
        r0 = 0;
        switch(r0) {
            case 0: goto L_0x0009;
            case 1: goto L_0x0000;
            default: goto L_0x0008;
        };
    L_0x0008:
        goto L_0x0004;
    L_0x0009:
        r0 = d;
        r1 = e;
        r1 = r1 + r0;
        r0 = r0 * r1;
        r1 = f;
        r0 = r0 % r1;
        switch(r0) {
            case 0: goto L_0x001f;
            default: goto L_0x0015;
        };
    L_0x0015:
        r0 = 74;
        d = r0;
        r0 = a();
        g = r0;
    L_0x001f:
        r2.s = r3;
        return r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.a.c.a(com.immersion.hapticmediasdk.a.c, int):int");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static /* synthetic */ long a(com.immersion.hapticmediasdk.a.c r3, long r4) {
        /*
        r0 = 1;
    L_0x0001:
        switch(r0) {
            case 0: goto L_0x0001;
            case 1: goto L_0x0008;
            default: goto L_0x0004;
        };
    L_0x0004:
        switch(r0) {
            case 0: goto L_0x0001;
            case 1: goto L_0x0008;
            default: goto L_0x0007;
        };
    L_0x0007:
        goto L_0x0004;
    L_0x0008:
        r0 = d;
        r1 = e;
        r0 = r0 + r1;
        r1 = d;
        r0 = r0 * r1;
        r1 = f;
        r0 = r0 % r1;
        r1 = g;
        if (r0 == r1) goto L_0x0021;
    L_0x0017:
        r0 = a();
        d = r0;
        r0 = 90;
        g = r0;
    L_0x0021:
        r3.z = r4;
        return r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.a.c.a(com.immersion.hapticmediasdk.a.c, long):long");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static /* synthetic */ com.immersion.hapticmediasdk.a.d a(com.immersion.hapticmediasdk.a.c r2, com.immersion.hapticmediasdk.a.d r3) {
        /*
        r0 = 0;
    L_0x0001:
        switch(r0) {
            case 0: goto L_0x0008;
            case 1: goto L_0x0001;
            default: goto L_0x0004;
        };
    L_0x0004:
        switch(r0) {
            case 0: goto L_0x0008;
            case 1: goto L_0x0001;
            default: goto L_0x0007;
        };
    L_0x0007:
        goto L_0x0004;
    L_0x0008:
        r0 = d;
        r1 = e;
        r0 = r0 + r1;
        r1 = d;
        r0 = r0 * r1;
        r1 = f;
        r0 = r0 % r1;
        r1 = g;
        if (r0 == r1) goto L_0x0023;
    L_0x0017:
        r0 = a();
        d = r0;
        r0 = a();
        g = r0;
    L_0x0023:
        r2.n = r3;
        return r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.a.c.a(com.immersion.hapticmediasdk.a.c, com.immersion.hapticmediasdk.a.d):com.immersion.hapticmediasdk.a.d");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static /* synthetic */ java.lang.Runnable a(com.immersion.hapticmediasdk.a.c r3) {
        /*
        r0 = 1;
    L_0x0001:
        switch(r0) {
            case 0: goto L_0x0001;
            case 1: goto L_0x0008;
            default: goto L_0x0004;
        };
    L_0x0004:
        switch(r0) {
            case 0: goto L_0x0001;
            case 1: goto L_0x0008;
            default: goto L_0x0007;
        };
    L_0x0007:
        goto L_0x0004;
    L_0x0008:
        r0 = r3.G;
        r1 = d;
        r2 = e;
        r1 = r1 + r2;
        r2 = d;
        r1 = r1 * r2;
        r2 = f;
        r1 = r1 % r2;
        r2 = b();
        if (r1 == r2) goto L_0x0025;
    L_0x001b:
        r1 = a();
        d = r1;
        r1 = 18;
        g = r1;
    L_0x0025:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.a.c.a(com.immersion.hapticmediasdk.a.c):java.lang.Runnable");
    }

    private void a(Message message) {
        this.A = true;
        Message obtainMessage = this.k.obtainMessage(8);
        obtainMessage.setData(message.getData());
        this.k.sendMessage(obtainMessage);
        if (((d + e) * d) % f != g) {
            d = 41;
            g = a();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static /* synthetic */ void a(com.immersion.hapticmediasdk.a.c r2, int r3, long r4) {
        /*
        r0 = d;
        r1 = e;
        r0 = r0 + r1;
        r1 = d;
        r0 = r0 * r1;
        r1 = f;
        r0 = r0 % r1;
        r1 = g;
        if (r0 == r1) goto L_0x0019;
    L_0x000f:
        r0 = a();
        d = r0;
        r0 = 45;
        g = r0;
    L_0x0019:
        r0 = 1;
        switch(r0) {
            case 0: goto L_0x0019;
            case 1: goto L_0x0022;
            default: goto L_0x001d;
        };
    L_0x001d:
        r0 = 0;
        switch(r0) {
            case 0: goto L_0x0022;
            case 1: goto L_0x0019;
            default: goto L_0x0021;
        };
    L_0x0021:
        goto L_0x001d;
    L_0x0022:
        r2.c(r3, r4);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.a.c.a(com.immersion.hapticmediasdk.a.c, int, long):void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static /* synthetic */ void a(com.immersion.hapticmediasdk.a.c r2, android.os.Message r3) {
        /*
    L_0x0000:
        r0 = 1;
        switch(r0) {
            case 0: goto L_0x0000;
            case 1: goto L_0x0009;
            default: goto L_0x0004;
        };
    L_0x0004:
        r0 = 0;
        switch(r0) {
            case 0: goto L_0x0009;
            case 1: goto L_0x0000;
            default: goto L_0x0008;
        };
    L_0x0008:
        goto L_0x0004;
    L_0x0009:
        r0 = d;
        r1 = e();
        r1 = r1 + r0;
        r0 = r0 * r1;
        r1 = f;
        r0 = r0 % r1;
        switch(r0) {
            case 0: goto L_0x001f;
            default: goto L_0x0017;
        };
    L_0x0017:
        r0 = 56;
        d = r0;
        r0 = 92;
        g = r0;
    L_0x001f:
        r2.a(r3);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.a.c.a(com.immersion.hapticmediasdk.a.c, android.os.Message):void");
    }

    public static int b() {
        return 0;
    }

    public static /* synthetic */ int b(c cVar, int i) {
        if (((d + e) * d) % i() != g) {
            d = 8;
            g = a();
        }
        try {
            cVar.t = i;
            return i;
        } catch (Exception e) {
            throw e;
        }
    }

    public static /* synthetic */ Handler b(c cVar) {
        try {
            Handler handler = cVar.j;
            int i = d;
            switch ((i * (e + i)) % f) {
                case TwitterResponse.NONE /*0*/:
                    break;
                default:
                    d = 9;
                    g = 62;
                    break;
            }
            return handler;
        } catch (Exception e) {
            throw e;
        }
    }

    public static /* synthetic */ int c(c cVar, int i) {
        if (((d + e) * d) % f != g) {
            d = a();
            g = 87;
        }
        cVar.u = i;
        return i;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void c(int r9, long r10) {
        /*
        r8 = this;
        r4 = 0;
        r6 = 1;
        r0 = r8.B;
        if (r0 != 0) goto L_0x0045;
    L_0x0006:
        r0 = r8.n;	 Catch:{ Error -> 0x001f }
        if (r0 != 0) goto L_0x000b;
    L_0x000a:
        return;
    L_0x000b:
        r0 = r8.o;	 Catch:{ Error -> 0x001f }
        if (r0 != 0) goto L_0x0040;
    L_0x000f:
        r0 = r8.n;	 Catch:{ Error -> 0x001f }
        r0 = r0.b();	 Catch:{ Error -> 0x001f }
        if (r0 != 0) goto L_0x002a;
    L_0x0017:
        r0 = "HapticPlaybackThread";
        r1 = "corrupted hapt file or unsupported format";
        com.immersion.hapticmediasdk.b.b.d(r0, r1);	 Catch:{ Error -> 0x001f }
        goto L_0x000a;
    L_0x001f:
        r0 = move-exception;
        r1 = "HapticPlaybackThread";
        r0 = r0.getMessage();
        com.immersion.hapticmediasdk.b.b.d(r1, r0);
        goto L_0x000a;
    L_0x002a:
        r1 = new com.immersion.content.EndpointWarp;	 Catch:{ Error -> 0x001f }
        r2 = r8.a;	 Catch:{ Error -> 0x001f }
        r3 = r0.length;	 Catch:{ Error -> 0x001f }
        r1.<init>(r2, r0, r3);	 Catch:{ Error -> 0x001f }
        r8.o = r1;	 Catch:{ Error -> 0x001f }
        r0 = r8.o;	 Catch:{ Error -> 0x001f }
        if (r0 != 0) goto L_0x0040;
    L_0x0038:
        r0 = "HapticPlaybackThread";
        r1 = "Error creating endpointwarp";
        com.immersion.hapticmediasdk.b.b.a(r0, r1);	 Catch:{ Error -> 0x001f }
        goto L_0x000a;
    L_0x0040:
        r0 = r8.o;	 Catch:{ Error -> 0x001f }
        r0.c();	 Catch:{ Error -> 0x001f }
    L_0x0045:
        r8.c = r4;
        r8.B = r6;
        r8.y = r4;
        r1 = r8.q;
        monitor-enter(r1);
        r8.x = r9;	 Catch:{ all -> 0x0069 }
        r0 = r8.x;	 Catch:{ all -> 0x0069 }
        r8.w = r0;	 Catch:{ all -> 0x0069 }
        r2 = r8.z;	 Catch:{ all -> 0x0069 }
        r4 = 0;
        r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r0 == 0) goto L_0x0062;
    L_0x005c:
        r2 = android.os.SystemClock.uptimeMillis();	 Catch:{ all -> 0x0069 }
        r8.z = r2;	 Catch:{ all -> 0x0069 }
    L_0x0062:
        monitor-exit(r1);	 Catch:{ all -> 0x0069 }
        r8.v = r10;
        r8.s();
        goto L_0x000a;
    L_0x0069:
        r0 = move-exception;
    L_0x006a:
        switch(r6) {
            case 0: goto L_0x006a;
            case 1: goto L_0x0071;
            default: goto L_0x006d;
        };
    L_0x006d:
        switch(r6) {
            case 0: goto L_0x006a;
            case 1: goto L_0x0071;
            default: goto L_0x0070;
        };
    L_0x0070:
        goto L_0x006d;
    L_0x0071:
        monitor-exit(r1);	 Catch:{ all -> 0x0069 }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.a.c.c(int, long):void");
    }

    public static /* synthetic */ void c(c cVar) {
        cVar.o();
        int i = d;
        switch ((i * (e + i)) % f) {
            case TwitterResponse.NONE /*0*/:
                return;
            default:
                d = a();
                g = a();
                return;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static /* synthetic */ int d(com.immersion.hapticmediasdk.a.c r3, int r4) {
        /*
        r2 = 0;
        r0 = d;
        r1 = e;
        r0 = r0 + r1;
        r1 = d;
        r0 = r0 * r1;
        r1 = f;
        r0 = r0 % r1;
        r1 = g;
        if (r0 == r1) goto L_0x001a;
    L_0x0010:
        r0 = a();
        d = r0;
        r0 = 23;
        g = r0;
    L_0x001a:
        switch(r2) {
            case 0: goto L_0x0021;
            case 1: goto L_0x001a;
            default: goto L_0x001d;
        };
    L_0x001d:
        switch(r2) {
            case 0: goto L_0x0021;
            case 1: goto L_0x001a;
            default: goto L_0x0020;
        };
    L_0x0020:
        goto L_0x001d;
    L_0x0021:
        r3.h = r4;
        return r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.a.c.d(com.immersion.hapticmediasdk.a.c, int):int");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static /* synthetic */ com.immersion.hapticmediasdk.a.d d(com.immersion.hapticmediasdk.a.c r2) {
        /*
        r0 = d;
        r1 = e;
        r1 = r1 + r0;
        r0 = r0 * r1;
        r1 = f;
        r0 = r0 % r1;
        switch(r0) {
            case 0: goto L_0x0016;
            default: goto L_0x000c;
        };
    L_0x000c:
        r0 = a();
        d = r0;
        r0 = 19;
        g = r0;
    L_0x0016:
        r0 = r2.n;
    L_0x0018:
        r1 = 0;
        switch(r1) {
            case 0: goto L_0x0021;
            case 1: goto L_0x0018;
            default: goto L_0x001c;
        };
    L_0x001c:
        r1 = 1;
        switch(r1) {
            case 0: goto L_0x0018;
            case 1: goto L_0x0021;
            default: goto L_0x0020;
        };
    L_0x0020:
        goto L_0x001c;
    L_0x0021:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.a.c.d(com.immersion.hapticmediasdk.a.c):com.immersion.hapticmediasdk.a.d");
    }

    public static int e() {
        return 1;
    }

    public static /* synthetic */ int e(c cVar, int i) {
        int i2 = cVar.x + i;
        if (((d + e) * d) % f != g) {
            d = 55;
            g = 1;
        }
        cVar.x = i2;
        return i2;
    }

    public static /* synthetic */ int f(c cVar, int i) {
        int[] iArr;
        while (true) {
            try {
                iArr = new int[-1];
            } catch (Exception e) {
                d = a();
                while (true) {
                    try {
                        iArr = new int[-1];
                    } catch (Exception e2) {
                        d = a();
                        try {
                            cVar.w = i;
                            return i;
                        } catch (Exception e3) {
                            throw e3;
                        }
                    }
                }
            }
        }
    }

    public static /* synthetic */ com.immersion.hapticmediasdk.b.a f(c cVar) {
        while (true) {
            try {
                int[] iArr = new int[-1];
            } catch (Exception e) {
                d = 90;
                return cVar.F;
            }
        }
    }

    public static /* synthetic */ void h(c cVar) {
        int a = a();
        switch ((a * (e + a)) % f) {
            case TwitterResponse.NONE /*0*/:
                break;
            default:
                d = a();
                g = a();
                break;
        }
        cVar.q();
    }

    public static int i() {
        return 2;
    }

    public static /* synthetic */ void i(c cVar) {
        int i = d;
        switch ((i * (e + i)) % f) {
            case TwitterResponse.NONE /*0*/:
                break;
            default:
                d = 25;
                g = 36;
                break;
        }
        try {
            cVar.r();
        } catch (Exception e) {
            throw e;
        }
    }

    public static /* synthetic */ void j(c cVar) {
        String str = null;
        cVar.p();
        while (true) {
            try {
                str.length();
            } catch (Exception e) {
                d = a();
                return;
            }
        }
    }

    public static /* synthetic */ void k(c cVar) {
        cVar.s();
        while (true) {
            try {
                int[] iArr = new int[-1];
            } catch (Exception e) {
                d = a();
                return;
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void l() {
        /*
        r2 = this;
    L_0x0000:
        r0 = r2.l;
        r0 = r0.isAlive();
        if (r0 == 0) goto L_0x003d;
    L_0x0008:
        r0 = d;
        r1 = e;
        r0 = r0 + r1;
        r1 = d;
        r0 = r0 * r1;
        r1 = f;
        r0 = r0 % r1;
        r1 = b();
        if (r0 == r1) goto L_0x0023;
    L_0x0019:
        r0 = a();
        d = r0;
        r0 = 65;
        e = r0;
    L_0x0023:
        r0 = r2.l;
        r0.b();
        r0 = r2.l;
        r0.interrupt();
        java.lang.Thread.currentThread();
    L_0x0030:
        r0 = 0;
        switch(r0) {
            case 0: goto L_0x0039;
            case 1: goto L_0x0030;
            default: goto L_0x0034;
        };
    L_0x0034:
        r0 = 1;
        switch(r0) {
            case 0: goto L_0x0030;
            case 1: goto L_0x0039;
            default: goto L_0x0038;
        };
    L_0x0038:
        goto L_0x0034;
    L_0x0039:
        java.lang.Thread.yield();
        goto L_0x0000;
    L_0x003d:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.a.c.l():void");
    }

    public static /* synthetic */ boolean l(c cVar) {
        try {
            boolean z = cVar.B;
            if (((d + e) * d) % f != g) {
                d = a();
                g = a();
            }
            return z;
        } catch (Exception e) {
            throw e;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m() {
        /*
        r4 = this;
        r3 = 0;
        r0 = r4.l;
        if (r0 == 0) goto L_0x000a;
    L_0x0005:
        r4.l();
        r4.l = r3;
    L_0x000a:
        r1 = r4.r;
        monitor-enter(r1);
        r0 = r4.j;	 Catch:{ all -> 0x0049 }
        r2 = 0;
        r0.removeCallbacksAndMessages(r2);	 Catch:{ all -> 0x0049 }
        monitor-exit(r1);	 Catch:{ all -> 0x0049 }
        r0 = r4.m;
        if (r0 == 0) goto L_0x001f;
    L_0x0018:
        r0 = r4.m;
        r0.quit();
        r4.m = r3;
    L_0x001f:
        r0 = r4.n;
        if (r0 == 0) goto L_0x002a;
    L_0x0023:
        r0 = r4.n;
        r0.c();
        r4.n = r3;
    L_0x002a:
        r0 = r4.o;
        if (r0 == 0) goto L_0x0043;
    L_0x002e:
        r0 = r4.o;
        r0.d();
        r0 = r4.o;
        r0.e();
    L_0x0038:
        r0 = 0;
        switch(r0) {
            case 0: goto L_0x0041;
            case 1: goto L_0x0038;
            default: goto L_0x003c;
        };
    L_0x003c:
        r0 = 1;
        switch(r0) {
            case 0: goto L_0x0038;
            case 1: goto L_0x0041;
            default: goto L_0x0040;
        };
    L_0x0040:
        goto L_0x003c;
    L_0x0041:
        r4.o = r3;
    L_0x0043:
        r0 = r4.F;
        r0.c();
        return;
    L_0x0049:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0049 }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.a.c.m():void");
    }

    public static /* synthetic */ ArrayList n(c cVar) {
        if (((d + e) * d) % f != g) {
            d = a();
            g = 92;
        }
        try {
            return cVar.C;
        } catch (Exception e) {
            throw e;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void n() {
        /*
        r1 = this;
        r0 = 0;
        monitor-enter(r1);
    L_0x0002:
        switch(r0) {
            case 0: goto L_0x0009;
            case 1: goto L_0x0002;
            default: goto L_0x0005;
        };
    L_0x0005:
        switch(r0) {
            case 0: goto L_0x0009;
            case 1: goto L_0x0002;
            default: goto L_0x0008;
        };
    L_0x0008:
        goto L_0x0005;
    L_0x0009:
        r1.notifyAll();	 Catch:{ all -> 0x000e }
        monitor-exit(r1);	 Catch:{ all -> 0x000e }
        return;
    L_0x000e:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x000e }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.a.c.n():void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static /* synthetic */ com.immersion.hapticmediasdk.b.d o(com.immersion.hapticmediasdk.a.c r2) {
        /*
        r0 = d;
        r1 = e;
        r1 = r1 + r0;
        r0 = r0 * r1;
        r1 = f;
        r0 = r0 % r1;
        switch(r0) {
            case 0: goto L_0x0016;
            default: goto L_0x000c;
        };
    L_0x000c:
        r0 = a();
        d = r0;
        r0 = 64;
        g = r0;
    L_0x0016:
        r0 = 1;
        switch(r0) {
            case 0: goto L_0x0016;
            case 1: goto L_0x001f;
            default: goto L_0x001a;
        };
    L_0x001a:
        r0 = 0;
        switch(r0) {
            case 0: goto L_0x001f;
            case 1: goto L_0x0016;
            default: goto L_0x001e;
        };
    L_0x001e:
        goto L_0x001a;
    L_0x001f:
        r0 = r2.D;
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.a.c.o(com.immersion.hapticmediasdk.a.c):com.immersion.hapticmediasdk.b.d");
    }

    private void o() {
        if (((d + e) * d) % f != g) {
            d = 74;
            g = 21;
        }
        if (!this.A) {
            int i = this.u;
            this.u = i + 1;
            if (i == 5) {
                this.k.sendMessage(this.k.obtainMessage(7, this.s, 0));
                this.j.postDelayed(this.G, 100);
            } else if (this.n == null || !this.n.b(this.s)) {
                this.j.postDelayed(this.G, 100);
            } else if (this.t != Integer.MIN_VALUE) {
                this.k.sendMessage(this.k.obtainMessage(6, this.s, this.t));
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static /* synthetic */ com.immersion.content.EndpointWarp p(com.immersion.hapticmediasdk.a.c r4) {
        /*
        r3 = 0;
        r1 = -1;
        r0 = 4;
        r2 = 0;
    L_0x0004:
        r0 = r0 / r2;
        goto L_0x0004;
    L_0x0006:
        r0 = move-exception;
        r0 = a();
        d = r0;
        r0 = r4.o;
    L_0x000f:
        switch(r3) {
            case 0: goto L_0x0016;
            case 1: goto L_0x000f;
            default: goto L_0x0012;
        };
    L_0x0012:
        switch(r3) {
            case 0: goto L_0x0016;
            case 1: goto L_0x000f;
            default: goto L_0x0015;
        };
    L_0x0015:
        goto L_0x0012;
    L_0x0016:
        return r0;
    L_0x0017:
        r0 = move-exception;
        r0 = a();
        d = r0;
    L_0x001e:
        r0 = new int[r1];	 Catch:{ Exception -> 0x0006 }
        goto L_0x001e;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.a.c.p(com.immersion.hapticmediasdk.a.c):com.immersion.content.EndpointWarp");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void p() {
        /*
        r5 = this;
        r4 = 0;
        r0 = 0;
        r5.m();	 Catch:{ Exception -> 0x0030 }
    L_0x0005:
        r0.length();	 Catch:{ Exception -> 0x0009 }
        goto L_0x0005;
    L_0x0009:
        r1 = move-exception;
        r1 = a();
        d = r1;
    L_0x0010:
        r0.length();	 Catch:{ Exception -> 0x0014 }
        goto L_0x0010;
    L_0x0014:
        r1 = move-exception;
        r1 = a();
        d = r1;
    L_0x001b:
        r0.length();	 Catch:{ Exception -> 0x001f }
        goto L_0x001b;
    L_0x001f:
        r0 = move-exception;
        r0 = 38;
        d = r0;
        r5.b = r4;
        r5.n();
    L_0x0029:
        return;
    L_0x002a:
        r5.b = r4;
        r5.n();
        goto L_0x0029;
    L_0x0030:
        r0 = move-exception;
        r1 = "HapticPlaybackThread";
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0055 }
        r2.<init>();	 Catch:{ all -> 0x0055 }
        r3 = "quit() : ";
        r2 = r2.append(r3);	 Catch:{ all -> 0x0055 }
        r0 = r0.getMessage();	 Catch:{ all -> 0x0055 }
        r0 = r2.append(r0);	 Catch:{ all -> 0x0055 }
        r0 = r0.toString();	 Catch:{ all -> 0x0055 }
        com.immersion.hapticmediasdk.b.b.d(r1, r0);	 Catch:{ all -> 0x0055 }
    L_0x004d:
        switch(r4) {
            case 0: goto L_0x002a;
            case 1: goto L_0x004d;
            default: goto L_0x0050;
        };
    L_0x0050:
        r0 = 1;
        switch(r0) {
            case 0: goto L_0x004d;
            case 1: goto L_0x002a;
            default: goto L_0x0054;
        };
    L_0x0054:
        goto L_0x0050;
    L_0x0055:
        r0 = move-exception;
        r5.b = r4;
        r5.n();
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.a.c.p():void");
    }

    public static /* synthetic */ Object q(c cVar) {
        if (((d + e) * d) % f != g) {
            d = 18;
            g = a();
        }
        try {
            return cVar.q;
        } catch (Exception e) {
            throw e;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void q() {
        /*
        r8 = this;
        r6 = 0;
        r5 = 1;
        r4 = 0;
        r8.B = r4;
        r0 = r8.o;
        if (r0 == 0) goto L_0x000f;
    L_0x000a:
        r0 = r8.o;
        r0.d();
    L_0x000f:
        switch(r4) {
            case 0: goto L_0x0016;
            case 1: goto L_0x000f;
            default: goto L_0x0012;
        };
    L_0x0012:
        switch(r5) {
            case 0: goto L_0x000f;
            case 1: goto L_0x0016;
            default: goto L_0x0015;
        };
    L_0x0015:
        goto L_0x0012;
    L_0x0016:
        r0 = r8.j;
        r1 = r8.G;
        r0.removeCallbacks(r1);
        r8.j();
        r1 = r8.q;
        monitor-enter(r1);
        r0 = 0;
        r8.x = r0;	 Catch:{ all -> 0x0035 }
        r0 = 0;
        r8.w = r0;	 Catch:{ all -> 0x0035 }
        r2 = 0;
        r8.z = r2;	 Catch:{ all -> 0x0035 }
        monitor-exit(r1);	 Catch:{ all -> 0x0035 }
        r8.y = r4;
        r8.v = r6;
        r8.c = r5;
        return;
    L_0x0035:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0035 }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.a.c.q():void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static /* synthetic */ int r(com.immersion.hapticmediasdk.a.c r2) {
        /*
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
        r0 = a();
        r1 = e;
        r0 = r0 + r1;
        r1 = a();
        r0 = r0 * r1;
        r1 = f;
        r0 = r0 % r1;
        r1 = g;
        if (r0 == r1) goto L_0x0028;
    L_0x001c:
        r0 = a();
        d = r0;
        r0 = a();
        g = r0;
    L_0x0028:
        r0 = r2.x;
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.a.c.r(com.immersion.hapticmediasdk.a.c):int");
    }

    private void r() {
        this.B = false;
        j();
        int i = d;
        switch ((i * (e() + i)) % f) {
            case TwitterResponse.NONE /*0*/:
                return;
            default:
                d = a();
                g = a();
                return;
        }
    }

    public static /* synthetic */ Runnable s(c cVar) {
        if (((a() + e) * a()) % f != g) {
            d = a();
            g = a();
        }
        try {
            return cVar.H;
        } catch (Exception e) {
            throw e;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void s() {
        /*
        r13 = this;
        r12 = 0;
        r0 = r13.B;
        if (r0 == 0) goto L_0x0057;
    L_0x0005:
        r1 = r13.q;
        monitor-enter(r1);
        r2 = r13.x;	 Catch:{ all -> 0x0058 }
        r4 = r13.w;	 Catch:{ all -> 0x0058 }
        monitor-exit(r1);	 Catch:{ all -> 0x0058 }
        r0 = r13.n;	 Catch:{ b -> 0x005b }
        r6 = r0.c(r2);	 Catch:{ b -> 0x005b }
        r0 = r13.n;	 Catch:{ b -> 0x005b }
        r8 = (long) r2;	 Catch:{ b -> 0x005b }
        r7 = r0.b(r8);	 Catch:{ b -> 0x005b }
        r0 = r13.n;	 Catch:{ b -> 0x005b }
        r8 = (long) r2;	 Catch:{ b -> 0x005b }
        r8 = r0.a(r8);	 Catch:{ b -> 0x005b }
        if (r6 == 0) goto L_0x006e;
    L_0x0023:
        r0 = r13.v;
        r3 = r13.y;
        r10 = (long) r3;
        r10 = r10 + r0;
        r0 = new com.immersion.hapticmediasdk.a.c$a;
        r2 = (long) r2;
        r4 = (long) r4;
        r1 = r13;
        r0.<init>(r1, r2, r4, r6, r7, r8);
        r1 = r13.r;
    L_0x0033:
        r2 = 1;
        switch(r2) {
            case 0: goto L_0x0033;
            case 1: goto L_0x003b;
            default: goto L_0x0037;
        };
    L_0x0037:
        switch(r12) {
            case 0: goto L_0x003b;
            case 1: goto L_0x0033;
            default: goto L_0x003a;
        };
    L_0x003a:
        goto L_0x0037;
    L_0x003b:
        monitor-enter(r1);
        r2 = r13.C;	 Catch:{ all -> 0x006b }
        r2.add(r0);	 Catch:{ all -> 0x006b }
        monitor-exit(r1);	 Catch:{ all -> 0x006b }
        r1 = r13.j;
        r2 = r13.h;
        r2 = (long) r2;
        r2 = r2 + r10;
        r1.postAtTime(r0, r2);
        r0 = r13.y;
        r1 = r13.h;
        r0 = r0 + r1;
        r13.y = r0;
        r0 = r13.p;
        r0.b();
    L_0x0057:
        return;
    L_0x0058:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0058 }
        throw r0;
    L_0x005b:
        r0 = move-exception;
        r13.B = r12;
        r0 = r13.k;
        r1 = r13.k;
        r3 = 7;
        r1 = r1.obtainMessage(r3, r2, r12);
        r0.sendMessage(r1);
        goto L_0x0057;
    L_0x006b:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x006b }
        throw r0;
    L_0x006e:
        r1 = r13.q;
        monitor-enter(r1);
        r0 = 0;
        r13.x = r0;	 Catch:{ all -> 0x0081 }
        r0 = 0;
        r13.w = r0;	 Catch:{ all -> 0x0081 }
        monitor-exit(r1);	 Catch:{ all -> 0x0081 }
        r13.y = r12;
        r0 = 0;
        r13.v = r0;
        r13.B = r12;
        goto L_0x0057;
    L_0x0081:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0081 }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.a.c.s():void");
    }

    public void a(int i, int i2) {
        this.j.removeMessages(1);
        this.j.sendMessage(this.j.obtainMessage(1, i, i2));
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(int r7, long r8) {
        /*
        r6 = this;
        r5 = 2;
        r0 = 0;
        r1 = 0;
        r2 = 0;
        r6.j();
        r3 = r6.j;
        r3.removeMessages(r5);
        r3 = new android.os.Bundle;
    L_0x000e:
        switch(r0) {
            case 0: goto L_0x0015;
            case 1: goto L_0x000e;
            default: goto L_0x0011;
        };
    L_0x0011:
        switch(r0) {
            case 0: goto L_0x0015;
            case 1: goto L_0x000e;
            default: goto L_0x0014;
        };
    L_0x0014:
        goto L_0x0011;
    L_0x0015:
        r3.<init>();
        r4 = "playback_timecode";
        r3.putInt(r4, r7);
        r4 = "playback_uptime";
        r3.putLong(r4, r8);
        r4 = r6.j;
        r4 = r4.obtainMessage(r5);
    L_0x0028:
        r2.length();	 Catch:{ Exception -> 0x002c }
        goto L_0x0028;
    L_0x002c:
        r5 = move-exception;
        r5 = 75;
        d = r5;
    L_0x0031:
        r0 = r0 / r1;
        goto L_0x0031;
    L_0x0033:
        r0 = move-exception;
        r0 = 38;
        d = r0;
        r4.setData(r3);
        r0 = r6.j;
        r0.sendMessage(r4);
        return;
    L_0x0041:
        r0 = move-exception;
        r0 = a();
        d = r0;
    L_0x0048:
        r2.length();	 Catch:{ Exception -> 0x0033 }
        goto L_0x0048;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.a.c.a(int, long):void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void b(int r11, long r12) {
        /*
        r10 = this;
        r8 = 1;
        r1 = r10.q;
        monitor-enter(r1);
        r2 = android.os.SystemClock.uptimeMillis();	 Catch:{ all -> 0x003f }
        r4 = (long) r11;	 Catch:{ all -> 0x003f }
        r6 = r2 - r12;
        r4 = r4 + r6;
        r0 = (int) r4;	 Catch:{ all -> 0x003f }
        r4 = r10.x;	 Catch:{ all -> 0x003f }
        r6 = r10.z;	 Catch:{ all -> 0x003f }
        r2 = r2 - r6;
        r2 = (int) r2;	 Catch:{ all -> 0x003f }
        r2 = r2 + r4;
        r2 = r0 - r2;
        r3 = 50;
        r4 = java.lang.Math.abs(r2);	 Catch:{ all -> 0x003f }
        if (r3 >= r4) goto L_0x003d;
    L_0x001e:
        r3 = r10.x;	 Catch:{ all -> 0x003f }
        r2 = r2 + r3;
        r10.x = r2;	 Catch:{ all -> 0x003f }
    L_0x0023:
        r2 = 0;
        switch(r2) {
            case 0: goto L_0x002b;
            case 1: goto L_0x0023;
            default: goto L_0x0027;
        };	 Catch:{ all -> 0x003f }
    L_0x0027:
        switch(r8) {
            case 0: goto L_0x0023;
            case 1: goto L_0x002b;
            default: goto L_0x002a;
        };	 Catch:{ all -> 0x003f }
    L_0x002a:
        goto L_0x0027;
    L_0x002b:
        r2 = r10.x;	 Catch:{ all -> 0x003f }
        r10.w = r2;	 Catch:{ all -> 0x003f }
        r2 = r10.j;	 Catch:{ all -> 0x003f }
        r3 = r10.j;	 Catch:{ all -> 0x003f }
        r4 = 1;
        r5 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r0 = r3.obtainMessage(r4, r0, r5);	 Catch:{ all -> 0x003f }
        r2.sendMessage(r0);	 Catch:{ all -> 0x003f }
    L_0x003d:
        monitor-exit(r1);	 Catch:{ all -> 0x003f }
        return;
    L_0x003f:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x003f }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.a.c.b(int, long):void");
    }

    public boolean c() {
        boolean z = this.b;
        int i = d;
        switch ((i * (e + i)) % f) {
            case TwitterResponse.NONE /*0*/:
                break;
            default:
                d = 69;
                g = a();
                break;
        }
        return z;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean d() {
        /*
        r3 = this;
        r1 = 0;
        r0 = r3.c;
    L_0x0003:
        switch(r1) {
            case 0: goto L_0x000a;
            case 1: goto L_0x0003;
            default: goto L_0x0006;
        };
    L_0x0006:
        switch(r1) {
            case 0: goto L_0x000a;
            case 1: goto L_0x0003;
            default: goto L_0x0009;
        };
    L_0x0009:
        goto L_0x0006;
    L_0x000a:
        r1 = d;
        r2 = e();
        r2 = r2 + r1;
        r1 = r1 * r2;
        r2 = f;
        r1 = r1 % r2;
        switch(r1) {
            case 0: goto L_0x0022;
            default: goto L_0x0018;
        };
    L_0x0018:
        r1 = 11;
        d = r1;
        r1 = a();
        g = r1;
    L_0x0022:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.a.c.d():boolean");
    }

    public Handler f() {
        try {
            Handler handler = this.j;
            if (((d + e) * d) % f != g) {
                d = a();
                g = 58;
            }
            return handler;
        } catch (Exception e) {
            throw e;
        }
    }

    public void g() {
        try {
            this.j.sendEmptyMessage(4);
            int i = d;
            switch ((i * (e + i)) % f) {
                case TwitterResponse.NONE /*0*/:
                    return;
                default:
                    d = 35;
                    g = 24;
                    return;
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public void h() {
        if (((d + e) * d) % i() != b()) {
            d = 98;
            g = 68;
        }
        this.j.sendEmptyMessage(5);
        while (true) {
            switch (1) {
                case TwitterResponse.NONE /*0*/:
                    break;
                case TwitterResponse.READ /*1*/:
                    return;
                default:
                    while (true) {
                        switch (1) {
                            case TwitterResponse.NONE /*0*/:
                                break;
                            case TwitterResponse.READ /*1*/:
                                return;
                            default:
                        }
                    }
            }
        }
    }

    public void j() {
        synchronized (this.r) {
            Iterator it = this.C.iterator();
            while (it.hasNext()) {
                this.j.removeCallbacks((a) it.next());
            }
            this.C.clear();
        }
    }

    public void k() {
        try {
            if (!this.j.sendEmptyMessage(9)) {
                if (((d + e()) * d) % f != g) {
                    d = a();
                    g = 16;
                }
                this.b = false;
                try {
                    n();
                } catch (Exception e) {
                    throw e;
                }
            }
        } catch (Exception e2) {
            throw e2;
        }
    }

    public void run() {
        String str = null;
        Process.setThreadPriority(-19);
        Looper.prepare();
        this.m = Looper.myLooper();
        this.j = new c();
        while (true) {
            try {
                str.length();
            } catch (Exception e) {
                d = a();
                this.l = new b(this.i, this.j, this.E, this.F);
                this.l.start();
                this.b = true;
                n();
                Looper.loop();
                return;
            }
        }
    }
}
