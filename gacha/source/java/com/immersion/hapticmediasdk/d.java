package com.immersion.hapticmediasdk;

import android.content.Context;
import android.os.Handler;
import com.immersion.hapticmediasdk.a.c;
import com.immersion.hapticmediasdk.a.f;
import com.immersion.hapticmediasdk.b.b;
import twitter4j.TwitterResponse;

public class d implements Runnable {
    public static int a = 37;
    public static int b = 1;
    public static int c = 2;
    public static int d;
    private final Object e;
    private final Object f;
    private long g;
    private long h;
    private Handler i;
    private volatile com.immersion.hapticmediasdk.a.a j;
    private f k;
    private String l;
    private boolean m;
    private Context n;
    private com.immersion.hapticmediasdk.b.d o;

    public static /* synthetic */ class a {
        public static final /* synthetic */ int[] a = new int[com.immersion.hapticmediasdk.a.a.values().length];
        public static int b = 57;
        public static int c = 26;
        public static int d = 2;

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        static {
            /*
            r3 = 1;
            r0 = com.immersion.hapticmediasdk.a.a.values();
            r0 = r0.length;
            r0 = new int[r0];
            a = r0;
            r0 = a;	 Catch:{ NoSuchFieldError -> 0x0080 }
            r1 = com.immersion.hapticmediasdk.a.a.NOT_INITIALIZED;	 Catch:{ NoSuchFieldError -> 0x0080 }
            r1 = r1.ordinal();	 Catch:{ NoSuchFieldError -> 0x0080 }
            r2 = 1;
            r0[r1] = r2;	 Catch:{ NoSuchFieldError -> 0x0080 }
        L_0x0015:
            r0 = a;	 Catch:{ NoSuchFieldError -> 0x007e }
            r1 = com.immersion.hapticmediasdk.a.a.INITIALIZED;	 Catch:{ NoSuchFieldError -> 0x007e }
            r1 = r1.ordinal();	 Catch:{ NoSuchFieldError -> 0x007e }
            r2 = 2;
            r0[r1] = r2;	 Catch:{ NoSuchFieldError -> 0x007e }
        L_0x0020:
            switch(r3) {
                case 0: goto L_0x0020;
                case 1: goto L_0x0028;
                default: goto L_0x0023;
            };
        L_0x0023:
            r0 = 0;
            switch(r0) {
                case 0: goto L_0x0028;
                case 1: goto L_0x0020;
                default: goto L_0x0027;
            };
        L_0x0027:
            goto L_0x0023;
        L_0x0028:
            r0 = a;	 Catch:{ NoSuchFieldError -> 0x007c }
            r1 = com.immersion.hapticmediasdk.a.a.PLAYING;	 Catch:{ NoSuchFieldError -> 0x007c }
            r1 = r1.ordinal();	 Catch:{ NoSuchFieldError -> 0x007c }
            r2 = 3;
            r0[r1] = r2;	 Catch:{ NoSuchFieldError -> 0x007c }
        L_0x0033:
            r0 = a;	 Catch:{ NoSuchFieldError -> 0x007a }
            r1 = com.immersion.hapticmediasdk.a.a.PAUSED;	 Catch:{ NoSuchFieldError -> 0x007a }
            r1 = r1.ordinal();	 Catch:{ NoSuchFieldError -> 0x007a }
            r2 = 4;
            r0[r1] = r2;	 Catch:{ NoSuchFieldError -> 0x007a }
            r0 = b;
            r1 = c;
            r1 = r1 + r0;
            r0 = r0 * r1;
            r1 = d;
            r0 = r0 % r1;
            switch(r0) {
                case 0: goto L_0x0052;
                default: goto L_0x004a;
            };
        L_0x004a:
            r0 = 57;
            b = r0;
            r0 = 26;
            c = r0;
        L_0x0052:
            r0 = a;	 Catch:{ NoSuchFieldError -> 0x0074 }
            r1 = com.immersion.hapticmediasdk.a.a.PAUSED_DUE_TO_TIMEOUT;	 Catch:{ NoSuchFieldError -> 0x0074 }
            r1 = r1.ordinal();	 Catch:{ NoSuchFieldError -> 0x0074 }
            r2 = 5;
            r0[r1] = r2;	 Catch:{ NoSuchFieldError -> 0x0074 }
        L_0x005d:
            r0 = a;	 Catch:{ NoSuchFieldError -> 0x0076 }
            r1 = com.immersion.hapticmediasdk.a.a.h;	 Catch:{ NoSuchFieldError -> 0x0076 }
            r1 = r1.ordinal();	 Catch:{ NoSuchFieldError -> 0x0076 }
            r2 = 6;
            r0[r1] = r2;	 Catch:{ NoSuchFieldError -> 0x0076 }
        L_0x0068:
            r0 = a;	 Catch:{ NoSuchFieldError -> 0x0078 }
            r1 = com.immersion.hapticmediasdk.a.a.STOPPED;	 Catch:{ NoSuchFieldError -> 0x0078 }
            r1 = r1.ordinal();	 Catch:{ NoSuchFieldError -> 0x0078 }
            r2 = 7;
            r0[r1] = r2;	 Catch:{ NoSuchFieldError -> 0x0078 }
        L_0x0073:
            return;
        L_0x0074:
            r0 = move-exception;
            goto L_0x005d;
        L_0x0076:
            r0 = move-exception;
            goto L_0x0068;
        L_0x0078:
            r0 = move-exception;
            goto L_0x0073;
        L_0x007a:
            r0 = move-exception;
            goto L_0x0052;
        L_0x007c:
            r0 = move-exception;
            goto L_0x0033;
        L_0x007e:
            r0 = move-exception;
            goto L_0x0028;
        L_0x0080:
            r0 = move-exception;
            goto L_0x0015;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.d.a.<clinit>():void");
        }
    }

    public d(Handler handler, Context context, com.immersion.hapticmediasdk.b.d dVar) {
        if (((a + b) * a) % c != a()) {
            a = 47;
            b = b();
        }
        try {
            this.e = new Object();
            try {
                this.f = new Object();
                this.j = com.immersion.hapticmediasdk.a.a.NOT_INITIALIZED;
                this.i = handler;
                this.n = context;
                this.o = dVar;
            } catch (Exception e) {
                throw e;
            }
        } catch (Exception e2) {
            throw e2;
        }
    }

    public static int a() {
        return 0;
    }

    public static int b() {
        return 54;
    }

    private int b(com.immersion.hapticmediasdk.a.a aVar) {
        int i = a;
        switch ((i * (b + i)) % c) {
            case TwitterResponse.NONE /*0*/:
                break;
            default:
                a = 19;
                b = b();
                break;
        }
        try {
            try {
                this.i.removeCallbacks(this);
                this.j = aVar;
                if (this.l == null) {
                    return -4;
                }
                this.k = new f(this.i.getLooper(), this);
                Handler b = this.k.b();
                this.k.a(new c(this.n, this.l, b, this.m, this.o));
                return 0;
            } catch (Exception e) {
                throw e;
            }
        } catch (Exception e2) {
            throw e2;
        }
    }

    public static int c() {
        return 1;
    }

    private int h() {
        try {
            this.i.removeCallbacks(this);
            if (!(this.k == null || k() == 0)) {
                b.d("MediaTaskManager", "Could not dispose haptics, reset anyway.");
            }
            try {
                this.l = null;
                this.g = 0;
                this.j = com.immersion.hapticmediasdk.a.a.NOT_INITIALIZED;
                int i = a;
                switch ((i * (c() + i)) % c) {
                    case TwitterResponse.NONE /*0*/:
                        break;
                    default:
                        a = b();
                        b = 55;
                        break;
                }
                return 0;
            } catch (Exception e) {
                throw e;
            }
        } catch (Exception e2) {
            throw e2;
        }
    }

    private int i() {
        this.i.removeCallbacks(this);
        int f = this.k.f();
        if (f == 0) {
            this.j = com.immersion.hapticmediasdk.a.a.PLAYING;
            Handler handler = this.i;
            int i = a;
            switch ((i * (b + i)) % c) {
                case TwitterResponse.NONE /*0*/:
                    break;
                default:
                    a = b();
                    d = 68;
                    break;
            }
            handler.postDelayed(this, 1500);
        }
        return f;
    }

    private int j() {
        try {
            this.i.removeCallbacks(this);
            this.g = 0;
            if (((a + b) * a) % c != d) {
                a = b();
                d = b();
            }
            try {
                int k = this.k.k();
                if (k == 0) {
                    this.j = com.immersion.hapticmediasdk.a.a.STOPPED;
                }
                return k;
            } catch (Exception e) {
                throw e;
            }
        } catch (Exception e2) {
            throw e2;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int k() {
        /*
        r3 = this;
        r0 = r3.j();
        if (r0 != 0) goto L_0x0030;
    L_0x0006:
        r1 = 0;
        switch(r1) {
            case 0: goto L_0x000f;
            case 1: goto L_0x0006;
            default: goto L_0x000a;
        };
    L_0x000a:
        r1 = 1;
        switch(r1) {
            case 0: goto L_0x0006;
            case 1: goto L_0x000f;
            default: goto L_0x000e;
        };
    L_0x000e:
        goto L_0x000a;
    L_0x000f:
        r1 = r3.k;
        r2 = r3.i;
        r1.a(r2);
        r1 = a;
        r2 = b;
        r1 = r1 + r2;
        r2 = a;
        r1 = r1 * r2;
        r2 = c;
        r1 = r1 % r2;
        r2 = d;
        if (r1 == r2) goto L_0x002d;
    L_0x0025:
        r1 = 80;
        a = r1;
        r1 = 44;
        d = r1;
    L_0x002d:
        r1 = 0;
        r3.k = r1;
    L_0x0030:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.d.k():int");
    }

    private int l() {
        int i = 2;
        try {
            this.i.removeCallbacks(this);
            try {
                int c = this.k.c();
                if (c == 0) {
                    while (true) {
                        try {
                            i /= 0;
                        } catch (Exception e) {
                            a = b();
                            this.j = com.immersion.hapticmediasdk.a.a.PAUSED;
                        }
                    }
                }
                return c;
            } catch (Exception e2) {
                throw e2;
            }
        } catch (Exception e22) {
            throw e22;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int m() {
        /*
        r4 = this;
        r0 = 0;
        r1 = r4.i;
        r1.removeCallbacks(r4);
        r1 = r4.i;
        r2 = 1500; // 0x5dc float:2.102E-42 double:7.41E-321;
        r1 = r1.postDelayed(r4, r2);
        if (r1 == 0) goto L_0x0034;
    L_0x0010:
        switch(r0) {
            case 0: goto L_0x0018;
            case 1: goto L_0x0010;
            default: goto L_0x0013;
        };
    L_0x0013:
        r1 = 1;
        switch(r1) {
            case 0: goto L_0x0010;
            case 1: goto L_0x0018;
            default: goto L_0x0017;
        };
    L_0x0017:
        goto L_0x0013;
    L_0x0018:
        r1 = b();
        r2 = b;
        r1 = r1 + r2;
        r2 = b();
        r1 = r1 * r2;
        r2 = c;
        r1 = r1 % r2;
        r2 = d;
        if (r1 == r2) goto L_0x0033;
    L_0x002b:
        r1 = 70;
        a = r1;
        r1 = 50;
        d = r1;
    L_0x0033:
        return r0;
    L_0x0034:
        r0 = -1;
        goto L_0x0033;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.d.m():int");
    }

    private int n() {
        try {
            int c = this.k.c();
            if (c == 0) {
                if (((a + b) * a) % c != a()) {
                    a = 64;
                    d = 32;
                }
                this.j = com.immersion.hapticmediasdk.a.a.PAUSED_DUE_TO_TIMEOUT;
            }
            return c;
        } catch (Exception e) {
            throw e;
        }
    }

    private int o() {
        try {
            int c = this.k.c();
            if (c == 0) {
                this.j = com.immersion.hapticmediasdk.a.a.h;
            }
            if (((a + b) * a) % c != d) {
                a = 29;
                d = b();
            }
            return c;
        } catch (Exception e) {
            throw e;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int p() {
        /*
        r2 = this;
        r0 = a;
        r1 = b;
        r0 = r0 + r1;
        r1 = a;
        r0 = r0 * r1;
        r1 = c;
        r0 = r0 % r1;
        r1 = d;
        if (r0 == r1) goto L_0x0017;
    L_0x000f:
        r0 = 78;
        a = r0;
        r0 = 14;
        d = r0;
    L_0x0017:
        r0 = r2.i();
        if (r0 != 0) goto L_0x0021;
    L_0x001d:
        r0 = r2.m();
    L_0x0021:
        r1 = 0;
        switch(r1) {
            case 0: goto L_0x002a;
            case 1: goto L_0x0021;
            default: goto L_0x0025;
        };
    L_0x0025:
        r1 = 1;
        switch(r1) {
            case 0: goto L_0x0021;
            case 1: goto L_0x002a;
            default: goto L_0x0029;
        };
    L_0x0029:
        goto L_0x0025;
    L_0x002a:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.d.p():int");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int a(com.immersion.hapticmediasdk.a.a r7) {
        /*
        r6 = this;
        r1 = 0;
        r0 = -1;
        r2 = r6.e;
        monitor-enter(r2);
        r3 = com.immersion.hapticmediasdk.a.a.NOT_INITIALIZED;	 Catch:{ all -> 0x001e }
        if (r7 != r3) goto L_0x000f;
    L_0x0009:
        r0 = r6.h();	 Catch:{ all -> 0x001e }
        monitor-exit(r2);	 Catch:{ all -> 0x001e }
    L_0x000e:
        return r0;
    L_0x000f:
        r3 = com.immersion.hapticmediasdk.d.a.a;	 Catch:{ all -> 0x001e }
        r4 = r6.j;	 Catch:{ all -> 0x001e }
        r4 = r4.ordinal();	 Catch:{ all -> 0x001e }
        r3 = r3[r4];	 Catch:{ all -> 0x001e }
        switch(r3) {
            case 1: goto L_0x0021;
            case 2: goto L_0x002a;
            case 3: goto L_0x0067;
            case 4: goto L_0x0091;
            case 5: goto L_0x00dd;
            case 6: goto L_0x00f2;
            case 7: goto L_0x0119;
            default: goto L_0x001c;
        };	 Catch:{ all -> 0x001e }
    L_0x001c:
        monitor-exit(r2);	 Catch:{ all -> 0x001e }
        goto L_0x000e;
    L_0x001e:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x001e }
        throw r0;
    L_0x0021:
        r1 = com.immersion.hapticmediasdk.a.a.INITIALIZED;	 Catch:{ all -> 0x001e }
        if (r7 != r1) goto L_0x001c;
    L_0x0025:
        r0 = r6.b(r7);	 Catch:{ all -> 0x001e }
        goto L_0x001c;
    L_0x002a:
        r1 = com.immersion.hapticmediasdk.a.a.PLAYING;	 Catch:{ all -> 0x001e }
        if (r7 != r1) goto L_0x0051;
    L_0x002e:
        r0 = r6.p();	 Catch:{ all -> 0x001e }
        goto L_0x001c;
    L_0x0033:
        r1 = com.immersion.hapticmediasdk.a.a.STOPPED_DUE_TO_ERROR;	 Catch:{ all -> 0x001e }
        if (r7 != r1) goto L_0x001c;
    L_0x0037:
        r0 = r6.j();	 Catch:{ all -> 0x001e }
        r1 = com.immersion.hapticmediasdk.a.a.STOPPED_DUE_TO_ERROR;	 Catch:{ all -> 0x001e }
        r6.j = r1;	 Catch:{ all -> 0x001e }
        goto L_0x001c;
    L_0x0040:
        r3 = com.immersion.hapticmediasdk.a.a.PLAYING;	 Catch:{ all -> 0x001e }
        if (r7 != r3) goto L_0x00f9;
    L_0x0044:
        r0 = r6.k;	 Catch:{ all -> 0x001e }
        r4 = r6.g;	 Catch:{ all -> 0x001e }
        r1 = (int) r4;	 Catch:{ all -> 0x001e }
        r0.a(r1);	 Catch:{ all -> 0x001e }
        r0 = r6.p();	 Catch:{ all -> 0x001e }
        goto L_0x001c;
    L_0x0051:
        r1 = com.immersion.hapticmediasdk.a.a.STOPPED;	 Catch:{ all -> 0x001e }
        if (r7 != r1) goto L_0x005a;
    L_0x0055:
        r0 = r6.j();	 Catch:{ all -> 0x001e }
        goto L_0x001c;
    L_0x005a:
        r1 = com.immersion.hapticmediasdk.a.a.STOPPED_DUE_TO_ERROR;	 Catch:{ all -> 0x001e }
        if (r7 != r1) goto L_0x001c;
    L_0x005e:
        r0 = r6.j();	 Catch:{ all -> 0x001e }
        r1 = com.immersion.hapticmediasdk.a.a.STOPPED_DUE_TO_ERROR;	 Catch:{ all -> 0x001e }
        r6.j = r1;	 Catch:{ all -> 0x001e }
        goto L_0x001c;
    L_0x0067:
        r3 = 1;
        switch(r3) {
            case 0: goto L_0x0067;
            case 1: goto L_0x006f;
            default: goto L_0x006b;
        };	 Catch:{ all -> 0x001e }
    L_0x006b:
        switch(r1) {
            case 0: goto L_0x006f;
            case 1: goto L_0x0067;
            default: goto L_0x006e;
        };	 Catch:{ all -> 0x001e }
    L_0x006e:
        goto L_0x006b;
    L_0x006f:
        r1 = com.immersion.hapticmediasdk.a.a.PLAYING;	 Catch:{ all -> 0x001e }
        if (r7 != r1) goto L_0x0078;
    L_0x0073:
        r0 = r6.m();	 Catch:{ all -> 0x001e }
        goto L_0x001c;
    L_0x0078:
        r1 = com.immersion.hapticmediasdk.a.a.PAUSED;	 Catch:{ all -> 0x001e }
        if (r7 != r1) goto L_0x0081;
    L_0x007c:
        r0 = r6.l();	 Catch:{ all -> 0x001e }
        goto L_0x001c;
    L_0x0081:
        r1 = com.immersion.hapticmediasdk.a.a.PAUSED_DUE_TO_TIMEOUT;	 Catch:{ all -> 0x001e }
        if (r7 != r1) goto L_0x00aa;
    L_0x0085:
        r0 = "MediaTaskManager";
        r1 = "Haptic playback is paused due to update time-out. Call update() to resume playback";
        com.immersion.hapticmediasdk.b.b.c(r0, r1);	 Catch:{ all -> 0x001e }
        r0 = r6.n();	 Catch:{ all -> 0x001e }
        goto L_0x001c;
    L_0x0091:
        r3 = com.immersion.hapticmediasdk.a.a.PLAYING;	 Catch:{ all -> 0x001e }
        if (r7 != r3) goto L_0x00a3;
    L_0x0095:
        r0 = r6.k;	 Catch:{ all -> 0x001e }
        r4 = r6.g;	 Catch:{ all -> 0x001e }
        r1 = (int) r4;	 Catch:{ all -> 0x001e }
        r0.a(r1);	 Catch:{ all -> 0x001e }
        r0 = r6.p();	 Catch:{ all -> 0x001e }
        goto L_0x001c;
    L_0x00a3:
        r3 = com.immersion.hapticmediasdk.a.a.PAUSED;	 Catch:{ all -> 0x001e }
        if (r7 != r3) goto L_0x00c5;
    L_0x00a7:
        r0 = r1;
        goto L_0x001c;
    L_0x00aa:
        r1 = com.immersion.hapticmediasdk.a.a.h;	 Catch:{ all -> 0x001e }
        if (r7 != r1) goto L_0x00bb;
    L_0x00ae:
        r0 = r6.o();	 Catch:{ all -> 0x001e }
        r1 = "MediaTaskManager";
        r3 = "Haptic playback is paused due to slow data buffering...";
        com.immersion.hapticmediasdk.b.b.c(r1, r3);	 Catch:{ all -> 0x001e }
        goto L_0x001c;
    L_0x00bb:
        r1 = com.immersion.hapticmediasdk.a.a.STOPPED;	 Catch:{ all -> 0x001e }
        if (r7 != r1) goto L_0x0033;
    L_0x00bf:
        r0 = r6.j();	 Catch:{ all -> 0x001e }
        goto L_0x001c;
    L_0x00c5:
        r1 = com.immersion.hapticmediasdk.a.a.STOPPED;	 Catch:{ all -> 0x001e }
        if (r7 != r1) goto L_0x00cf;
    L_0x00c9:
        r0 = r6.j();	 Catch:{ all -> 0x001e }
        goto L_0x001c;
    L_0x00cf:
        r1 = com.immersion.hapticmediasdk.a.a.STOPPED_DUE_TO_ERROR;	 Catch:{ all -> 0x001e }
        if (r7 != r1) goto L_0x001c;
    L_0x00d3:
        r0 = r6.j();	 Catch:{ all -> 0x001e }
        r1 = com.immersion.hapticmediasdk.a.a.STOPPED_DUE_TO_ERROR;	 Catch:{ all -> 0x001e }
        r6.j = r1;	 Catch:{ all -> 0x001e }
        goto L_0x001c;
    L_0x00dd:
        r3 = com.immersion.hapticmediasdk.a.a.PAUSED_DUE_TO_TIMEOUT;	 Catch:{ all -> 0x001e }
        if (r7 != r3) goto L_0x0040;
    L_0x00e1:
        r0 = r1;
        goto L_0x001c;
    L_0x00e4:
        r1 = com.immersion.hapticmediasdk.a.a.STOPPED_DUE_TO_ERROR;	 Catch:{ all -> 0x001e }
        if (r7 != r1) goto L_0x001c;
    L_0x00e8:
        r0 = r6.j();	 Catch:{ all -> 0x001e }
        r1 = com.immersion.hapticmediasdk.a.a.STOPPED_DUE_TO_ERROR;	 Catch:{ all -> 0x001e }
        r6.j = r1;	 Catch:{ all -> 0x001e }
        goto L_0x001c;
    L_0x00f2:
        r3 = com.immersion.hapticmediasdk.a.a.h;	 Catch:{ all -> 0x001e }
        if (r7 != r3) goto L_0x0123;
    L_0x00f6:
        r0 = r1;
        goto L_0x001c;
    L_0x00f9:
        r3 = com.immersion.hapticmediasdk.a.a.PAUSED;	 Catch:{ all -> 0x001e }
        if (r7 != r3) goto L_0x0104;
    L_0x00fd:
        r0 = com.immersion.hapticmediasdk.a.a.PAUSED;	 Catch:{ all -> 0x001e }
        r6.j = r0;	 Catch:{ all -> 0x001e }
        r0 = r1;
        goto L_0x001c;
    L_0x0104:
        r1 = com.immersion.hapticmediasdk.a.a.STOPPED;	 Catch:{ all -> 0x001e }
        if (r7 != r1) goto L_0x00e4;
    L_0x0108:
        r0 = r6.j();	 Catch:{ all -> 0x001e }
        goto L_0x001c;
    L_0x010e:
        r3 = com.immersion.hapticmediasdk.a.a.PAUSED;	 Catch:{ all -> 0x001e }
        if (r7 != r3) goto L_0x0135;
    L_0x0112:
        r0 = com.immersion.hapticmediasdk.a.a.PAUSED;	 Catch:{ all -> 0x001e }
        r6.j = r0;	 Catch:{ all -> 0x001e }
        r0 = r1;
        goto L_0x001c;
    L_0x0119:
        r3 = com.immersion.hapticmediasdk.a.a.PLAYING;	 Catch:{ all -> 0x001e }
        if (r7 != r3) goto L_0x014d;
    L_0x011d:
        r0 = r6.p();	 Catch:{ all -> 0x001e }
        goto L_0x001c;
    L_0x0123:
        r3 = com.immersion.hapticmediasdk.a.a.PLAYING;	 Catch:{ all -> 0x001e }
        if (r7 != r3) goto L_0x010e;
    L_0x0127:
        r0 = r6.k;	 Catch:{ all -> 0x001e }
        r4 = r6.g;	 Catch:{ all -> 0x001e }
        r1 = (int) r4;	 Catch:{ all -> 0x001e }
        r0.a(r1);	 Catch:{ all -> 0x001e }
        r0 = r6.p();	 Catch:{ all -> 0x001e }
        goto L_0x001c;
    L_0x0135:
        r1 = com.immersion.hapticmediasdk.a.a.STOPPED;	 Catch:{ all -> 0x001e }
        if (r7 != r1) goto L_0x013f;
    L_0x0139:
        r0 = r6.j();	 Catch:{ all -> 0x001e }
        goto L_0x001c;
    L_0x013f:
        r1 = com.immersion.hapticmediasdk.a.a.STOPPED_DUE_TO_ERROR;	 Catch:{ all -> 0x001e }
        if (r7 != r1) goto L_0x001c;
    L_0x0143:
        r0 = r6.j();	 Catch:{ all -> 0x001e }
        r1 = com.immersion.hapticmediasdk.a.a.STOPPED_DUE_TO_ERROR;	 Catch:{ all -> 0x001e }
        r6.j = r1;	 Catch:{ all -> 0x001e }
        goto L_0x001c;
    L_0x014d:
        r3 = com.immersion.hapticmediasdk.a.a.STOPPED;	 Catch:{ all -> 0x001e }
        if (r7 != r3) goto L_0x001c;
    L_0x0151:
        r0 = r1;
        goto L_0x001c;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.d.a(com.immersion.hapticmediasdk.a$a):int");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(long r6) {
        /*
        r5 = this;
        r4 = 1;
        r1 = r5.f;
        monitor-enter(r1);
        r0 = r5.j;	 Catch:{ all -> 0x0020 }
        r2 = com.immersion.hapticmediasdk.a.a.STOPPED;	 Catch:{ all -> 0x0020 }
        if (r0 != r2) goto L_0x000f;
    L_0x000a:
        r0 = r5.k;	 Catch:{ all -> 0x0020 }
        r0.d();	 Catch:{ all -> 0x0020 }
    L_0x000f:
        r2 = android.os.SystemClock.uptimeMillis();	 Catch:{ all -> 0x0020 }
        r5.h = r2;	 Catch:{ all -> 0x0020 }
    L_0x0015:
        switch(r4) {
            case 0: goto L_0x0015;
            case 1: goto L_0x001c;
            default: goto L_0x0018;
        };	 Catch:{ all -> 0x0020 }
    L_0x0018:
        switch(r4) {
            case 0: goto L_0x0015;
            case 1: goto L_0x001c;
            default: goto L_0x001b;
        };	 Catch:{ all -> 0x0020 }
    L_0x001b:
        goto L_0x0018;
    L_0x001c:
        r5.g = r6;	 Catch:{ all -> 0x0020 }
        monitor-exit(r1);	 Catch:{ all -> 0x0020 }
        return;
    L_0x0020:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0020 }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.d.a(long):void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(java.lang.String r4, boolean r5) {
        /*
        r3 = this;
        r2 = 0;
        r1 = r3.e;
        monitor-enter(r1);
        r3.l = r4;	 Catch:{ all -> 0x000a }
        r3.m = r5;	 Catch:{ all -> 0x000a }
        monitor-exit(r1);	 Catch:{ all -> 0x000a }
        return;
    L_0x000a:
        r0 = move-exception;
    L_0x000b:
        switch(r2) {
            case 0: goto L_0x0012;
            case 1: goto L_0x000b;
            default: goto L_0x000e;
        };	 Catch:{ all -> 0x000a }
    L_0x000e:
        switch(r2) {
            case 0: goto L_0x0012;
            case 1: goto L_0x000b;
            default: goto L_0x0011;
        };	 Catch:{ all -> 0x000a }
    L_0x0011:
        goto L_0x000e;
    L_0x0012:
        monitor-exit(r1);	 Catch:{ all -> 0x000a }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.d.a(java.lang.String, boolean):void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public long d() {
        /*
        r4 = this;
        r1 = r4.f;
        monitor-enter(r1);
        r2 = r4.g;	 Catch:{ all -> 0x0007 }
        monitor-exit(r1);	 Catch:{ all -> 0x0007 }
        return r2;
    L_0x0007:
        r0 = move-exception;
    L_0x0008:
        r2 = 1;
        switch(r2) {
            case 0: goto L_0x0008;
            case 1: goto L_0x0011;
            default: goto L_0x000c;
        };	 Catch:{ all -> 0x0007 }
    L_0x000c:
        r2 = 0;
        switch(r2) {
            case 0: goto L_0x0011;
            case 1: goto L_0x0008;
            default: goto L_0x0010;
        };	 Catch:{ all -> 0x0007 }
    L_0x0010:
        goto L_0x000c;
    L_0x0011:
        monitor-exit(r1);	 Catch:{ all -> 0x0007 }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.d.d():long");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public long e() {
        /*
        r4 = this;
        r1 = r4.f;
        monitor-enter(r1);
        r2 = r4.h;	 Catch:{ all -> 0x0007 }
        monitor-exit(r1);	 Catch:{ all -> 0x0007 }
        return r2;
    L_0x0007:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0007 }
    L_0x0009:
        r1 = 0;
        switch(r1) {
            case 0: goto L_0x0012;
            case 1: goto L_0x0009;
            default: goto L_0x000d;
        };
    L_0x000d:
        r1 = 1;
        switch(r1) {
            case 0: goto L_0x0009;
            case 1: goto L_0x0012;
            default: goto L_0x0011;
        };
    L_0x0011:
        goto L_0x000d;
    L_0x0012:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.d.e():long");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void f() {
        /*
        r4 = this;
        r1 = r4.f;
        monitor-enter(r1);
        r0 = r4.j;	 Catch:{ all -> 0x001f }
    L_0x0005:
        r2 = 1;
        switch(r2) {
            case 0: goto L_0x0005;
            case 1: goto L_0x000e;
            default: goto L_0x0009;
        };	 Catch:{ all -> 0x001f }
    L_0x0009:
        r2 = 0;
        switch(r2) {
            case 0: goto L_0x000e;
            case 1: goto L_0x0005;
            default: goto L_0x000d;
        };	 Catch:{ all -> 0x001f }
    L_0x000d:
        goto L_0x0009;
    L_0x000e:
        r2 = com.immersion.hapticmediasdk.a.a.STOPPED;	 Catch:{ all -> 0x001f }
        if (r0 != r2) goto L_0x0017;
    L_0x0012:
        r0 = r4.k;	 Catch:{ all -> 0x001f }
        r0.d();	 Catch:{ all -> 0x001f }
    L_0x0017:
        r2 = android.os.SystemClock.uptimeMillis();	 Catch:{ all -> 0x001f }
        r4.h = r2;	 Catch:{ all -> 0x001f }
        monitor-exit(r1);	 Catch:{ all -> 0x001f }
        return;
    L_0x001f:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x001f }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.d.f():void");
    }

    public com.immersion.hapticmediasdk.a.a g() {
        com.immersion.hapticmediasdk.a.a aVar;
        synchronized (this.e) {
            aVar = this.j;
        }
        return aVar;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
        r3 = this;
        r2 = 1;
        java.lang.System.currentTimeMillis();
        r0 = a;
        r1 = b;
        r0 = r0 + r1;
        r1 = a;
        r0 = r0 * r1;
        r1 = c;
        r0 = r0 % r1;
        r1 = d;
        if (r0 == r1) goto L_0x001b;
    L_0x0013:
        r0 = 91;
        a = r0;
        r0 = 30;
        d = r0;
    L_0x001b:
        switch(r2) {
            case 0: goto L_0x001b;
            case 1: goto L_0x0022;
            default: goto L_0x001e;
        };
    L_0x001e:
        switch(r2) {
            case 0: goto L_0x001b;
            case 1: goto L_0x0022;
            default: goto L_0x0021;
        };
    L_0x0021:
        goto L_0x001e;
    L_0x0022:
        r0 = com.immersion.hapticmediasdk.a.a.PAUSED_DUE_TO_TIMEOUT;
        r3.a(r0);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.d.run():void");
    }
}
