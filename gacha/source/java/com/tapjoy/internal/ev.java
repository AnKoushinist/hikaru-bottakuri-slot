package com.tapjoy.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public final class ev {
    public String a;
    public boolean b;

    static class a implements ServiceConnection {
        boolean a = false;
        final BlockingQueue b = new LinkedBlockingQueue();

        private a() {
        }

        public static a a(Context context) {
            if (ae.b(context.getPackageManager(), "com.google.android.gms") < 4000000) {
                throw new IOException("Google Play services not available.");
            }
            Object aVar = new a();
            Intent intent = new Intent("com.google.android.gms.ads.identifier.service.START");
            intent.setPackage("com.google.android.gms");
            if (context.bindService(intent, aVar, 1)) {
                return aVar;
            }
            throw new IOException("Connection failure");
        }

        public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                this.b.put(iBinder);
            } catch (InterruptedException e) {
            }
        }

        public final void onServiceDisconnected(ComponentName componentName) {
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean a(android.content.Context r7) {
        /*
        r6 = this;
        r1 = 0;
        r2 = 1;
        r0 = android.os.Looper.myLooper();
        r3 = android.os.Looper.getMainLooper();
        if (r0 != r3) goto L_0x0020;
    L_0x000c:
        r0 = "unknown";
        r2 = android.os.Build.DEVICE;
        r0 = r0.equals(r2);
        if (r0 == 0) goto L_0x0018;
    L_0x0016:
        r0 = r1;
    L_0x0017:
        return r0;
    L_0x0018:
        r0 = new java.lang.IllegalStateException;
        r1 = "Calling this from your main thread can lead to deadlock";
        r0.<init>(r1);
        throw r0;
    L_0x0020:
        r3 = 0;
        r3 = com.tapjoy.internal.ev.a.a(r7);	 Catch:{ IOException -> 0x002f, InterruptedException -> 0x0066, RemoteException -> 0x0085, SecurityException -> 0x0092, Exception -> 0x009f }
        r0 = r3.a;	 Catch:{ IOException -> 0x002f, InterruptedException -> 0x0066, RemoteException -> 0x0085, SecurityException -> 0x0092, Exception -> 0x009f }
        if (r0 == 0) goto L_0x003d;
    L_0x0029:
        r0 = new java.lang.IllegalStateException;	 Catch:{ IOException -> 0x002f, InterruptedException -> 0x0066, RemoteException -> 0x0085, SecurityException -> 0x0092, Exception -> 0x009f }
        r0.<init>();	 Catch:{ IOException -> 0x002f, InterruptedException -> 0x0066, RemoteException -> 0x0085, SecurityException -> 0x0092, Exception -> 0x009f }
        throw r0;	 Catch:{ IOException -> 0x002f, InterruptedException -> 0x0066, RemoteException -> 0x0085, SecurityException -> 0x0092, Exception -> 0x009f }
    L_0x002f:
        r0 = move-exception;
        r4 = 1;
        r4 = new java.lang.Object[r4];	 Catch:{ all -> 0x00ac }
        r5 = 0;
        r4[r5] = r0;	 Catch:{ all -> 0x00ac }
        if (r3 == 0) goto L_0x003b;
    L_0x0038:
        r7.unbindService(r3);	 Catch:{ IllegalArgumentException -> 0x007f, Exception -> 0x00bb }
    L_0x003b:
        r0 = r1;
        goto L_0x0017;
    L_0x003d:
        r0 = 1;
        r3.a = r0;	 Catch:{ IOException -> 0x002f, InterruptedException -> 0x0066, RemoteException -> 0x0085, SecurityException -> 0x0092, Exception -> 0x009f }
        r0 = r3.b;	 Catch:{ IOException -> 0x002f, InterruptedException -> 0x0066, RemoteException -> 0x0085, SecurityException -> 0x0092, Exception -> 0x009f }
        r0 = r0.take();	 Catch:{ IOException -> 0x002f, InterruptedException -> 0x0066, RemoteException -> 0x0085, SecurityException -> 0x0092, Exception -> 0x009f }
        r0 = (android.os.IBinder) r0;	 Catch:{ IOException -> 0x002f, InterruptedException -> 0x0066, RemoteException -> 0x0085, SecurityException -> 0x0092, Exception -> 0x009f }
        r0 = com.tapjoy.internal.ew.a.a(r0);	 Catch:{ IOException -> 0x002f, InterruptedException -> 0x0066, RemoteException -> 0x0085, SecurityException -> 0x0092, Exception -> 0x009f }
        r4 = r0.a();	 Catch:{ IOException -> 0x002f, InterruptedException -> 0x0066, RemoteException -> 0x0085, SecurityException -> 0x0092, Exception -> 0x009f }
        r6.a = r4;	 Catch:{ IOException -> 0x002f, InterruptedException -> 0x0066, RemoteException -> 0x0085, SecurityException -> 0x0092, Exception -> 0x009f }
        r4 = 1;
        r0 = r0.a(r4);	 Catch:{ SecurityException -> 0x005e, IOException -> 0x002f, InterruptedException -> 0x0066, RemoteException -> 0x0085, Exception -> 0x009f }
        r6.b = r0;	 Catch:{ SecurityException -> 0x005e, IOException -> 0x002f, InterruptedException -> 0x0066, RemoteException -> 0x0085, Exception -> 0x009f }
    L_0x0059:
        r7.unbindService(r3);	 Catch:{ IllegalArgumentException -> 0x0079, Exception -> 0x00b9 }
    L_0x005c:
        r0 = r2;
        goto L_0x0017;
    L_0x005e:
        r0 = move-exception;
        r4 = 1;
        r4 = new java.lang.Object[r4];	 Catch:{ IOException -> 0x002f, InterruptedException -> 0x0066, RemoteException -> 0x0085, SecurityException -> 0x0092, Exception -> 0x009f }
        r5 = 0;
        r4[r5] = r0;	 Catch:{ IOException -> 0x002f, InterruptedException -> 0x0066, RemoteException -> 0x0085, SecurityException -> 0x0092, Exception -> 0x009f }
        goto L_0x0059;
    L_0x0066:
        r0 = move-exception;
        r4 = 1;
        r4 = new java.lang.Object[r4];	 Catch:{ all -> 0x00ac }
        r5 = 0;
        r4[r5] = r0;	 Catch:{ all -> 0x00ac }
        if (r3 == 0) goto L_0x003b;
    L_0x006f:
        r7.unbindService(r3);	 Catch:{ IllegalArgumentException -> 0x0073, Exception -> 0x00be }
        goto L_0x003b;
    L_0x0073:
        r0 = move-exception;
        r2 = new java.lang.Object[r2];
        r2[r1] = r0;
        goto L_0x003b;
    L_0x0079:
        r0 = move-exception;
        r3 = new java.lang.Object[r2];
        r3[r1] = r0;
        goto L_0x005c;
    L_0x007f:
        r0 = move-exception;
        r2 = new java.lang.Object[r2];
        r2[r1] = r0;
        goto L_0x003b;
    L_0x0085:
        r0 = move-exception;
        if (r3 == 0) goto L_0x003b;
    L_0x0088:
        r7.unbindService(r3);	 Catch:{ IllegalArgumentException -> 0x008c, Exception -> 0x00c1 }
        goto L_0x003b;
    L_0x008c:
        r0 = move-exception;
        r2 = new java.lang.Object[r2];
        r2[r1] = r0;
        goto L_0x003b;
    L_0x0092:
        r0 = move-exception;
        if (r3 == 0) goto L_0x003b;
    L_0x0095:
        r7.unbindService(r3);	 Catch:{ IllegalArgumentException -> 0x0099, Exception -> 0x00c4 }
        goto L_0x003b;
    L_0x0099:
        r0 = move-exception;
        r2 = new java.lang.Object[r2];
        r2[r1] = r0;
        goto L_0x003b;
    L_0x009f:
        r0 = move-exception;
        if (r3 == 0) goto L_0x003b;
    L_0x00a2:
        r7.unbindService(r3);	 Catch:{ IllegalArgumentException -> 0x00a6, Exception -> 0x00c7 }
        goto L_0x003b;
    L_0x00a6:
        r0 = move-exception;
        r2 = new java.lang.Object[r2];
        r2[r1] = r0;
        goto L_0x003b;
    L_0x00ac:
        r0 = move-exception;
        if (r3 == 0) goto L_0x00b2;
    L_0x00af:
        r7.unbindService(r3);	 Catch:{ IllegalArgumentException -> 0x00b3, Exception -> 0x00ca }
    L_0x00b2:
        throw r0;
    L_0x00b3:
        r3 = move-exception;
        r2 = new java.lang.Object[r2];
        r2[r1] = r3;
        goto L_0x00b2;
    L_0x00b9:
        r0 = move-exception;
        goto L_0x005c;
    L_0x00bb:
        r0 = move-exception;
        goto L_0x003b;
    L_0x00be:
        r0 = move-exception;
        goto L_0x003b;
    L_0x00c1:
        r0 = move-exception;
        goto L_0x003b;
    L_0x00c4:
        r0 = move-exception;
        goto L_0x003b;
    L_0x00c7:
        r0 = move-exception;
        goto L_0x003b;
    L_0x00ca:
        r1 = move-exception;
        goto L_0x00b2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tapjoy.internal.ev.a(android.content.Context):boolean");
    }
}
