package com.immersion.hapticmediasdk.a;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import com.immersion.hapticmediasdk.b.c;
import com.immersion.hapticmediasdk.d;
import java.util.concurrent.atomic.AtomicInteger;
import twitter4j.TwitterResponse;

public class f {
    public static int a = 35;
    public static int b = 0;
    public static int c = 1;
    public static int d = 2;
    private AtomicInteger e;
    private AtomicInteger f;
    private Handler g;
    private c h;
    private c i;
    private d j;
    private Runnable k;

    public private class a extends Handler {
        public static int b = 1;
        public static int c = 0;
        public static int d = 1;
        public static int e = 2;
        public final /* synthetic */ f a;

        public a(f fVar, Looper looper) {
            this.a = fVar;
            super(looper);
        }

        public static int a() {
            return 35;
        }

        public void handleMessage(Message message) {
            try {
                switch (message.what) {
                    case AdInfo.BANNER_KIND_BANNER6 /*6*/:
                        if (f.a(this.a).get() == message.arg1 && f.b(this.a).get() == message.arg2) {
                            try {
                                d c = f.c(this.a);
                                int i = b;
                                switch ((i * (d + i)) % e) {
                                    case TwitterResponse.NONE /*0*/:
                                        break;
                                    default:
                                        b = 55;
                                        c = a();
                                        break;
                                }
                                if (c.g() == com.immersion.hapticmediasdk.a.a.h) {
                                    f.c(this.a).a(com.immersion.hapticmediasdk.a.a.PLAYING);
                                    return;
                                }
                                f.a(this.a, f.a(this.a).get(), SystemClock.uptimeMillis());
                                this.a.e();
                                return;
                            } catch (Exception e) {
                                throw e;
                            }
                        }
                        return;
                    case AdInfo.BANNER_KIND_BANNER7 /*7*/:
                        this.a.b(message.arg1);
                        return;
                    case AdInfo.BANNER_KIND_BANNER8 /*8*/:
                        f.a(this.a, message);
                        return;
                    default:
                        return;
                }
            } catch (Exception e2) {
                throw e2;
            }
        }
    }

    public class b implements Runnable {
        public static int b = 92;
        public static int c = 0;
        public static int d = 1;
        public static int e = 2;
        public final /* synthetic */ f a;

        public b(f fVar) {
            if (((b + d) * b) % e != c) {
                b = 15;
                c = 7;
            }
            try {
                this.a = fVar;
                try {
                } catch (Exception e) {
                    throw e;
                }
            } catch (Exception e2) {
                throw e2;
            }
        }

        public static int a() {
            return 6;
        }

        public void run() {
            if (this.a.i() && f.d(this.a) != null) {
                f.d(this.a).b(this.a.g(), this.a.h());
                f fVar = this.a;
                int i = b;
                switch ((i * (d + i)) % e) {
                    case TwitterResponse.NONE /*0*/:
                        break;
                    default:
                        b = a();
                        c = 99;
                        break;
                }
                f.d(fVar).f().removeCallbacks(this);
                f.d(this.a).f().postDelayed(this, 1000);
            }
        }
    }

    public f(Looper looper, d dVar) {
        try {
            this.e = new AtomicInteger();
            this.f = new AtomicInteger();
            if (((a + c) * a) % d != b) {
                a = a();
                b = a();
            }
            try {
                this.i = new c();
                this.k = new b(this);
                this.j = dVar;
                this.g = new a(this, looper);
            } catch (Exception e) {
                throw e;
            }
        } catch (Exception e2) {
            throw e2;
        }
    }

    public static int a() {
        return 5;
    }

    public static /* synthetic */ AtomicInteger a(f fVar) {
        try {
            AtomicInteger atomicInteger = fVar.e;
            if (((a + c) * a) % d != l()) {
                a = 20;
                b = 78;
            }
            return atomicInteger;
        } catch (Exception e) {
            throw e;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(int r5, long r6) {
        /*
        r4 = this;
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
        r0 = r4.h;
        r1 = a;
        r2 = c;
        r1 = r1 + r2;
        r2 = a;
        r1 = r1 * r2;
        r2 = d;
        r1 = r1 % r2;
        r2 = b;
        if (r1 == r2) goto L_0x0021;
    L_0x001a:
        r1 = 10;
        a = r1;
        r1 = 4;
        b = r1;
    L_0x0021:
        r0.a(r5, r6);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.a.f.a(int, long):void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(android.os.Message r6) {
        /*
        r5 = this;
        r4 = 0;
        r0 = r6.getData();
        r1 = "haptic_download_exception";
        r0 = r0.getSerializable(r1);
        r0 = (java.lang.Exception) r0;
        r1 = r0 instanceof com.immersion.hapticmediasdk.models.a;
        if (r1 == 0) goto L_0x0037;
    L_0x0011:
        r1 = r0;
        r1 = (com.immersion.hapticmediasdk.models.a) r1;
        r2 = "MediaController";
        r3 = new java.lang.StringBuilder;
    L_0x0018:
        switch(r4) {
            case 0: goto L_0x001f;
            case 1: goto L_0x0018;
            default: goto L_0x001b;
        };
    L_0x001b:
        switch(r4) {
            case 0: goto L_0x001f;
            case 1: goto L_0x0018;
            default: goto L_0x001e;
        };
    L_0x001e:
        goto L_0x001b;
    L_0x001f:
        r3.<init>();
        r4 = "caught HttpUnsuccessfulExcetion http status code = ";
        r3 = r3.append(r4);
        r1 = r1.a();
        r1 = r3.append(r1);
        r1 = r1.toString();
        com.immersion.hapticmediasdk.b.b.d(r2, r1);
    L_0x0037:
        r1 = "MediaController";
        r2 = a;
        r3 = c;
        r2 = r2 + r3;
        r3 = a;
        r2 = r2 * r3;
        r3 = d;
        r2 = r2 % r3;
        r3 = b;
        if (r2 == r3) goto L_0x0052;
    L_0x0048:
        r2 = a();
        a = r2;
        r2 = 98;
        b = r2;
    L_0x0052:
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "HapticDownloadError: ";
        r2 = r2.append(r3);
        r0 = r0.getMessage();
        r0 = r2.append(r0);
        r0 = r0.toString();
        com.immersion.hapticmediasdk.b.b.d(r1, r0);
        r0 = r5.j;
        r1 = com.immersion.hapticmediasdk.a.a.STOPPED_DUE_TO_ERROR;
        r0.a(r1);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.a.f.a(android.os.Message):void");
    }

    public static /* synthetic */ void a(f fVar, int i, long j) {
        int i2 = a;
        switch ((i2 * (c + i2)) % d) {
            case TwitterResponse.NONE /*0*/:
                break;
            default:
                a = 79;
                b = 74;
                break;
        }
        try {
            fVar.a(i, j);
        } catch (Exception e) {
            throw e;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static /* synthetic */ void a(com.immersion.hapticmediasdk.a.f r2, android.os.Message r3) {
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
        r0 = a;
        r1 = c;
        r0 = r0 + r1;
        r1 = a;
        r0 = r0 * r1;
        r1 = d;
        r0 = r0 % r1;
        r1 = l();
        if (r0 == r1) goto L_0x0021;
    L_0x001a:
        r0 = 31;
        a = r0;
        r0 = 2;
        b = r0;
    L_0x0021:
        r2.a(r3);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.a.f.a(com.immersion.hapticmediasdk.a.f, android.os.Message):void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(boolean r7) {
        /*
        r6 = this;
        r2 = 0;
        r0 = r6.h;
        r0 = r0.c();
        r1 = r2;
    L_0x0008:
        if (r7 == 0) goto L_0x0030;
    L_0x000a:
        if (r0 != 0) goto L_0x002c;
    L_0x000c:
        r3 = r6.h;
        monitor-enter(r3);
        r0 = r6.h;	 Catch:{ InterruptedException -> 0x0033 }
        r4 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        r0.wait(r4);	 Catch:{ InterruptedException -> 0x0033 }
    L_0x0016:
        r0 = 1;
        switch(r0) {
            case 0: goto L_0x0016;
            case 1: goto L_0x001e;
            default: goto L_0x001a;
        };
    L_0x001a:
        switch(r2) {
            case 0: goto L_0x001e;
            case 1: goto L_0x0016;
            default: goto L_0x001d;
        };
    L_0x001d:
        goto L_0x001a;
    L_0x001e:
        monitor-exit(r3);	 Catch:{ all -> 0x002d }
        r0 = r6.h;
        r0 = r0.c();
        r1 = r1 + 1;
        if (r7 != 0) goto L_0x0008;
    L_0x0029:
        r3 = 5;
        if (r1 < r3) goto L_0x0008;
    L_0x002c:
        return;
    L_0x002d:
        r0 = move-exception;
        monitor-exit(r3);	 Catch:{ all -> 0x002d }
        throw r0;
    L_0x0030:
        if (r0 == 0) goto L_0x002c;
    L_0x0032:
        goto L_0x000c;
    L_0x0033:
        r0 = move-exception;
        goto L_0x0016;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.a.f.a(boolean):void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static /* synthetic */ java.util.concurrent.atomic.AtomicInteger b(com.immersion.hapticmediasdk.a.f r2) {
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
        r0 = a;
        r1 = c;
        r1 = r1 + r0;
        r0 = r0 * r1;
        r1 = d;
        r0 = r0 % r1;
        switch(r0) {
            case 0: goto L_0x001d;
            default: goto L_0x0015;
        };
    L_0x0015:
        r0 = 31;
        a = r0;
        r0 = 73;
        b = r0;
    L_0x001d:
        r0 = r2.f;
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.a.f.b(com.immersion.hapticmediasdk.a.f):java.util.concurrent.atomic.AtomicInteger");
    }

    private void b(int i) {
        this.e.set(i);
        this.j.a(com.immersion.hapticmediasdk.a.a.h);
    }

    public static /* synthetic */ d c(f fVar) {
        d dVar = fVar.j;
        if (((a + c) * a) % d != b) {
            a = 52;
            b = 73;
        }
        return dVar;
    }

    public static /* synthetic */ c d(f fVar) {
        int i = a;
        switch ((i * (c + i)) % d) {
            case TwitterResponse.NONE /*0*/:
                break;
            default:
                a = 93;
                b = a();
                break;
        }
        try {
            return fVar.h;
        } catch (Exception e) {
            throw e;
        }
    }

    public static int l() {
        return 0;
    }

    public static int m() {
        return 2;
    }

    private int n() {
        this.h.h();
        return 0;
    }

    public void a(int i) {
        try {
            AtomicInteger atomicInteger = this.e;
            int a = a();
            switch ((a * (c + a)) % d) {
                case TwitterResponse.NONE /*0*/:
                    break;
                default:
                    a = 8;
                    b = a();
                    break;
            }
            atomicInteger.set(i);
        } catch (Exception e) {
            throw e;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(android.os.Handler r4) {
        /*
        r3 = this;
        r1 = 0;
        r0 = r3.h;
        if (r0 == 0) goto L_0x0017;
    L_0x0005:
        r0 = r3.h;
        r0.k();
        r3.a(r1);
    L_0x000d:
        switch(r1) {
            case 0: goto L_0x0014;
            case 1: goto L_0x000d;
            default: goto L_0x0010;
        };
    L_0x0010:
        switch(r1) {
            case 0: goto L_0x0014;
            case 1: goto L_0x000d;
            default: goto L_0x0013;
        };
    L_0x0013:
        goto L_0x0010;
    L_0x0014:
        r0 = 0;
        r3.h = r0;
    L_0x0017:
        r0 = r3.j;
        r1 = a;
        r2 = c;
        r1 = r1 + r2;
        r2 = a;
        r1 = r1 * r2;
        r2 = d;
        r1 = r1 % r2;
        r2 = b;
        if (r1 == r2) goto L_0x0032;
    L_0x0028:
        r1 = a();
        a = r1;
        r1 = 29;
        b = r1;
    L_0x0032:
        r4.removeCallbacks(r0);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.a.f.a(android.os.Handler):void");
    }

    public void a(c cVar) {
        try {
            this.h = cVar;
            this.h.start();
            if (((a + c) * a) % d != b) {
                a = a();
                b = 24;
            }
            try {
                a(true);
            } catch (Exception e) {
                throw e;
            }
        } catch (Exception e2) {
            throw e2;
        }
    }

    public Handler b() {
        if (((a + c) * a) % d != b) {
            a = 97;
            b = 45;
        }
        return this.g;
    }

    public int c() {
        int n = n();
        int i = a;
        switch ((i * (c + i)) % d) {
            case TwitterResponse.NONE /*0*/:
                break;
            default:
                a = 39;
                b = 73;
                break;
        }
        return n;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void d() {
        /*
        r6 = this;
        r1 = 0;
        r0 = r6.h;
        r0 = r0.d();
        r2 = r0;
        r0 = r1;
    L_0x0009:
        if (r2 != 0) goto L_0x002d;
    L_0x000b:
        r2 = 5;
        if (r0 >= r2) goto L_0x002d;
    L_0x000e:
        r2 = r6.h;
        monitor-enter(r2);
        r3 = r6.h;	 Catch:{ InterruptedException -> 0x002e }
        r4 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        r3.wait(r4);	 Catch:{ InterruptedException -> 0x002e }
    L_0x0018:
        monitor-exit(r2);	 Catch:{ all -> 0x0022 }
        r2 = r6.h;
        r2 = r2.d();
        r0 = r0 + 1;
        goto L_0x0009;
    L_0x0022:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x0022 }
    L_0x0024:
        r2 = 1;
        switch(r2) {
            case 0: goto L_0x0024;
            case 1: goto L_0x002c;
            default: goto L_0x0028;
        };
    L_0x0028:
        switch(r1) {
            case 0: goto L_0x002c;
            case 1: goto L_0x0024;
            default: goto L_0x002b;
        };
    L_0x002b:
        goto L_0x0028;
    L_0x002c:
        throw r0;
    L_0x002d:
        return;
    L_0x002e:
        r3 = move-exception;
        goto L_0x0018;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.a.f.d():void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void e() {
        /*
        r4 = this;
        r1 = 1;
        r0 = r4.h;
        if (r0 == 0) goto L_0x0032;
    L_0x0005:
        switch(r1) {
            case 0: goto L_0x0005;
            case 1: goto L_0x000c;
            default: goto L_0x0008;
        };
    L_0x0008:
        switch(r1) {
            case 0: goto L_0x0005;
            case 1: goto L_0x000c;
            default: goto L_0x000b;
        };
    L_0x000b:
        goto L_0x0008;
    L_0x000c:
        r0 = r4.h;
        r1 = a;
        r2 = c;
        r2 = r2 + r1;
        r1 = r1 * r2;
        r2 = d;
        r1 = r1 % r2;
        switch(r1) {
            case 0: goto L_0x0026;
            default: goto L_0x001a;
        };
    L_0x001a:
        r1 = a();
        a = r1;
        r1 = a();
        b = r1;
    L_0x0026:
        r0 = r0.f();
        r1 = r4.k;
        r2 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        r0.postDelayed(r1, r2);
    L_0x0031:
        return;
    L_0x0032:
        r0 = "MediaController";
        r1 = "Can't start periodic sync since haptic playback thread stopped.";
        com.immersion.hapticmediasdk.b.b.d(r0, r1);
        goto L_0x0031;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.a.f.e():void");
    }

    public int f() {
        return j();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int g() {
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
        r0 = a;
        r1 = c;
        r0 = r0 + r1;
        r1 = a;
        r0 = r0 * r1;
        r1 = d;
        r0 = r0 % r1;
        r1 = b;
        if (r0 == r1) goto L_0x001f;
    L_0x0017:
        r0 = 51;
        a = r0;
        r0 = 63;
        b = r0;
    L_0x001f:
        r0 = r2.j;
        r0 = r0.d();
        r0 = (int) r0;
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.a.f.g():int");
    }

    public long h() {
        int i = a;
        switch ((i * (c + i)) % d) {
            case TwitterResponse.NONE /*0*/:
                break;
            default:
                a = a();
                b = 38;
                break;
        }
        try {
            return this.j.e();
        } catch (Exception e) {
            throw e;
        }
    }

    public boolean i() {
        try {
            return this.j.g() == com.immersion.hapticmediasdk.a.a.PLAYING;
        } catch (Exception e) {
            throw e;
        }
    }

    public int j() {
        try {
            this.i.a();
            try {
                c cVar = this.h;
                int i = this.e.get();
                int i2 = a;
                switch ((i2 * (c + i2)) % d) {
                    case TwitterResponse.NONE /*0*/:
                        break;
                    default:
                        a = a();
                        b = 98;
                        break;
                }
                cVar.a(i, this.f.incrementAndGet());
                return 0;
            } catch (Exception e) {
                throw e;
            }
        } catch (Exception e2) {
            throw e2;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int k() {
        /*
        r4 = this;
        r3 = 0;
        r0 = r4.e;
        r0.set(r3);
        r0 = r4.h;
    L_0x0008:
        r1 = 1;
        switch(r1) {
            case 0: goto L_0x0008;
            case 1: goto L_0x0010;
            default: goto L_0x000c;
        };
    L_0x000c:
        switch(r3) {
            case 0: goto L_0x0010;
            case 1: goto L_0x0008;
            default: goto L_0x000f;
        };
    L_0x000f:
        goto L_0x000c;
    L_0x0010:
        r0.g();
        r0 = r4.h;
        r1 = a;
        r2 = c;
        r1 = r1 + r2;
        r2 = a;
        r1 = r1 * r2;
        r2 = d;
        r1 = r1 % r2;
        r2 = b;
        if (r1 == r2) goto L_0x002c;
    L_0x0024:
        r1 = 48;
        a = r1;
        r1 = 63;
        b = r1;
    L_0x002c:
        r0 = r0.f();
        r1 = r4.k;
        r0.removeCallbacks(r1);
        return r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.a.f.k():int");
    }
}
