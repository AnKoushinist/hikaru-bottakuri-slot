package com.vungle.publisher;

import android.location.Location;
import com.google.android.gms.common.ConnectionResult;
import com.vungle.log.Logger;

/* compiled from: vungle */
abstract class si<T> implements sj {
    T a;
    private int b;
    private final Object c = new Object();
    private boolean d;

    protected abstract String a();

    protected abstract boolean a(T t);

    protected abstract void b(T t);

    protected abstract Location c(T t);

    protected abstract T c();

    protected abstract void d(T t);

    si() {
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.location.Location b() {
        /*
        r7 = this;
        r0 = 0;
        r2 = r7.c;
        monitor-enter(r2);
        r1 = r7.e();	 Catch:{ SecurityException -> 0x00df, Exception -> 0x0100 }
        if (r1 == 0) goto L_0x002a;
    L_0x000a:
        r1 = r7.a;	 Catch:{ SecurityException -> 0x00df, Exception -> 0x0100 }
        r0 = r7.c(r1);	 Catch:{ SecurityException -> 0x00df, Exception -> 0x0100 }
        if (r0 != 0) goto L_0x002f;
    L_0x0012:
        r1 = "VungleLocation";
        r3 = new java.lang.StringBuilder;	 Catch:{ SecurityException -> 0x00df, Exception -> 0x0127 }
        r4 = "no location returned from ";
        r3.<init>(r4);	 Catch:{ SecurityException -> 0x00df, Exception -> 0x0127 }
        r4 = r7.a();	 Catch:{ SecurityException -> 0x00df, Exception -> 0x0127 }
        r3 = r3.append(r4);	 Catch:{ SecurityException -> 0x00df, Exception -> 0x0127 }
        r3 = r3.toString();	 Catch:{ SecurityException -> 0x00df, Exception -> 0x0127 }
        com.vungle.log.Logger.d(r1, r3);	 Catch:{ SecurityException -> 0x00df, Exception -> 0x0127 }
    L_0x002a:
        r7.g();	 Catch:{ all -> 0x00fd }
    L_0x002d:
        monitor-exit(r2);	 Catch:{ all -> 0x00fd }
        return r0;
    L_0x002f:
        r1 = "VungleLocation";
        r3 = new java.lang.StringBuilder;	 Catch:{ SecurityException -> 0x00df, Exception -> 0x0127 }
        r4 = "provider: ";
        r3.<init>(r4);	 Catch:{ SecurityException -> 0x00df, Exception -> 0x0127 }
        r4 = r0.getProvider();	 Catch:{ SecurityException -> 0x00df, Exception -> 0x0127 }
        r3 = r3.append(r4);	 Catch:{ SecurityException -> 0x00df, Exception -> 0x0127 }
        r3 = r3.toString();	 Catch:{ SecurityException -> 0x00df, Exception -> 0x0127 }
        com.vungle.log.Logger.v(r1, r3);	 Catch:{ SecurityException -> 0x00df, Exception -> 0x0127 }
        r1 = "VungleLocation";
        r3 = new java.lang.StringBuilder;	 Catch:{ SecurityException -> 0x00df, Exception -> 0x0127 }
        r4 = "latitude: ";
        r3.<init>(r4);	 Catch:{ SecurityException -> 0x00df, Exception -> 0x0127 }
        r4 = r0.getLatitude();	 Catch:{ SecurityException -> 0x00df, Exception -> 0x0127 }
        r3 = r3.append(r4);	 Catch:{ SecurityException -> 0x00df, Exception -> 0x0127 }
        r4 = "\u00b0";
        r3 = r3.append(r4);	 Catch:{ SecurityException -> 0x00df, Exception -> 0x0127 }
        r3 = r3.toString();	 Catch:{ SecurityException -> 0x00df, Exception -> 0x0127 }
        com.vungle.log.Logger.v(r1, r3);	 Catch:{ SecurityException -> 0x00df, Exception -> 0x0127 }
        r1 = "VungleLocation";
        r3 = new java.lang.StringBuilder;	 Catch:{ SecurityException -> 0x00df, Exception -> 0x0127 }
        r4 = "longitude: ";
        r3.<init>(r4);	 Catch:{ SecurityException -> 0x00df, Exception -> 0x0127 }
        r4 = r0.getLongitude();	 Catch:{ SecurityException -> 0x00df, Exception -> 0x0127 }
        r3 = r3.append(r4);	 Catch:{ SecurityException -> 0x00df, Exception -> 0x0127 }
        r4 = "\u00b0";
        r3 = r3.append(r4);	 Catch:{ SecurityException -> 0x00df, Exception -> 0x0127 }
        r3 = r3.toString();	 Catch:{ SecurityException -> 0x00df, Exception -> 0x0127 }
        com.vungle.log.Logger.v(r1, r3);	 Catch:{ SecurityException -> 0x00df, Exception -> 0x0127 }
        r1 = "VungleLocation";
        r3 = new java.lang.StringBuilder;	 Catch:{ SecurityException -> 0x00df, Exception -> 0x0127 }
        r4 = "accuracy: ";
        r3.<init>(r4);	 Catch:{ SecurityException -> 0x00df, Exception -> 0x0127 }
        r4 = r0.getAccuracy();	 Catch:{ SecurityException -> 0x00df, Exception -> 0x0127 }
        r3 = r3.append(r4);	 Catch:{ SecurityException -> 0x00df, Exception -> 0x0127 }
        r4 = " m";
        r3 = r3.append(r4);	 Catch:{ SecurityException -> 0x00df, Exception -> 0x0127 }
        r3 = r3.toString();	 Catch:{ SecurityException -> 0x00df, Exception -> 0x0127 }
        com.vungle.log.Logger.v(r1, r3);	 Catch:{ SecurityException -> 0x00df, Exception -> 0x0127 }
        r1 = "VungleLocation";
        r3 = new java.lang.StringBuilder;	 Catch:{ SecurityException -> 0x00df, Exception -> 0x0127 }
        r4 = "speed: ";
        r3.<init>(r4);	 Catch:{ SecurityException -> 0x00df, Exception -> 0x0127 }
        r4 = r0.getSpeed();	 Catch:{ SecurityException -> 0x00df, Exception -> 0x0127 }
        r3 = r3.append(r4);	 Catch:{ SecurityException -> 0x00df, Exception -> 0x0127 }
        r4 = " m/s";
        r3 = r3.append(r4);	 Catch:{ SecurityException -> 0x00df, Exception -> 0x0127 }
        r3 = r3.toString();	 Catch:{ SecurityException -> 0x00df, Exception -> 0x0127 }
        com.vungle.log.Logger.v(r1, r3);	 Catch:{ SecurityException -> 0x00df, Exception -> 0x0127 }
        r1 = "VungleLocation";
        r3 = new java.lang.StringBuilder;	 Catch:{ SecurityException -> 0x00df, Exception -> 0x0127 }
        r4 = "time: ";
        r3.<init>(r4);	 Catch:{ SecurityException -> 0x00df, Exception -> 0x0127 }
        r4 = r0.getTime();	 Catch:{ SecurityException -> 0x00df, Exception -> 0x0127 }
        r3 = r3.append(r4);	 Catch:{ SecurityException -> 0x00df, Exception -> 0x0127 }
        r4 = " ms";
        r3 = r3.append(r4);	 Catch:{ SecurityException -> 0x00df, Exception -> 0x0127 }
        r3 = r3.toString();	 Catch:{ SecurityException -> 0x00df, Exception -> 0x0127 }
        com.vungle.log.Logger.v(r1, r3);	 Catch:{ SecurityException -> 0x00df, Exception -> 0x0127 }
        goto L_0x002a;
    L_0x00df:
        r1 = move-exception;
        r1 = "VungleLocation";
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0122 }
        r4 = "no location permissions using ";
        r3.<init>(r4);	 Catch:{ all -> 0x0122 }
        r4 = r7.a();	 Catch:{ all -> 0x0122 }
        r3 = r3.append(r4);	 Catch:{ all -> 0x0122 }
        r3 = r3.toString();	 Catch:{ all -> 0x0122 }
        com.vungle.log.Logger.d(r1, r3);	 Catch:{ all -> 0x0122 }
        r7.g();	 Catch:{ all -> 0x00fd }
        goto L_0x002d;
    L_0x00fd:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x00fd }
        throw r0;
    L_0x0100:
        r1 = move-exception;
        r6 = r1;
        r1 = r0;
        r0 = r6;
    L_0x0104:
        r3 = "VungleLocation";
        r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0122 }
        r5 = "error obtaining detailed location using ";
        r4.<init>(r5);	 Catch:{ all -> 0x0122 }
        r5 = r7.a();	 Catch:{ all -> 0x0122 }
        r4 = r4.append(r5);	 Catch:{ all -> 0x0122 }
        r4 = r4.toString();	 Catch:{ all -> 0x0122 }
        com.vungle.log.Logger.w(r3, r4, r0);	 Catch:{ all -> 0x0122 }
        r7.g();	 Catch:{ all -> 0x00fd }
        r0 = r1;
        goto L_0x002d;
    L_0x0122:
        r0 = move-exception;
        r7.g();	 Catch:{ all -> 0x00fd }
        throw r0;	 Catch:{ all -> 0x00fd }
    L_0x0127:
        r1 = move-exception;
        r6 = r1;
        r1 = r0;
        r0 = r6;
        goto L_0x0104;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.si.b():android.location.Location");
    }

    private boolean e() {
        boolean z;
        Throwable th;
        Throwable th2;
        Throwable th3;
        boolean z2 = false;
        Object obj = null;
        Object obj2;
        try {
            synchronized (this.c) {
                try {
                    obj = this.a;
                    boolean e = e(obj);
                    if (e) {
                        Logger.d(Logger.LOCATION_TAG, Thread.currentThread().getName() + " already connected to " + a() + " " + obj);
                        obj2 = obj;
                        z = e;
                    } else {
                        if (obj == null) {
                            obj2 = c();
                            this.a = obj2;
                            try {
                                this.d = false;
                                b(obj2);
                                obj = obj2;
                            } catch (Throwable th4) {
                                th = th4;
                                obj = obj2;
                                z2 = e;
                                try {
                                    throw th;
                                } catch (Throwable th5) {
                                    th2 = th5;
                                    z = z2;
                                    obj2 = obj;
                                    th3 = th2;
                                }
                            }
                        }
                        while (!this.d) {
                            try {
                                Logger.d(Logger.LOCATION_TAG, Thread.currentThread().getName() + " waiting for " + a() + " to connect " + obj);
                                this.c.wait();
                            } catch (InterruptedException e2) {
                                Logger.d(Logger.LOCATION_TAG, Thread.currentThread().getName() + " interrupted while waiting for " + a() + " to connect " + obj);
                            } catch (Throwable th6) {
                                th5 = th6;
                                z2 = e;
                            }
                        }
                        z = e(obj);
                        obj2 = obj;
                    }
                    if (z) {
                        try {
                            this.b++;
                        } catch (Throwable th32) {
                            th2 = th32;
                            obj = obj2;
                            z2 = z;
                            th5 = th2;
                            throw th5;
                        }
                    }
                } catch (Throwable th7) {
                    th5 = th7;
                    throw th5;
                }
            }
        } catch (Throwable th52) {
            th2 = th52;
            z = false;
            obj2 = null;
            th32 = th2;
            Logger.w(Logger.LOCATION_TAG, Thread.currentThread().getName() + " error connecting to " + a() + " " + obj2, th32);
            return z;
        }
    }

    private boolean e(T t) {
        return t != null && a(t);
    }

    protected final void d() {
        Logger.d(Logger.LOCATION_TAG, "connected to " + a() + " " + this.a);
        f();
    }

    protected void onConnectionFailed(ConnectionResult connectionResult) {
        Logger.i(Logger.LOCATION_TAG, "failed to connect " + a() + " " + this.a + "; connection result " + connectionResult);
        f();
    }

    private void f() {
        synchronized (this.c) {
            this.d = true;
            this.c.notifyAll();
        }
    }

    private void g() {
        synchronized (this.c) {
            int i = this.b - 1;
            this.b = i;
            if (i > 0) {
                Logger.v(Logger.LOCATION_TAG, Thread.currentThread().getName() + " not disconnecting from " + a() + " because " + i + " clients still connected " + this.a);
            } else {
                Logger.d(Logger.LOCATION_TAG, Thread.currentThread().getName() + " disconnecting from " + a() + " " + this.a);
                Object obj = this.a;
                if (e(obj)) {
                    d(obj);
                    this.a = null;
                }
            }
        }
    }
}
