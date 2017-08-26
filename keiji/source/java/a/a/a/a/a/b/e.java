package a.a.a.a.a.b;

import a.a.a.a.c;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Parcel;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/* compiled from: AdvertisingInfoServiceStrategy */
class e implements f {
    private final Context a;

    /* compiled from: AdvertisingInfoServiceStrategy */
    private static final class a implements ServiceConnection {
        private boolean a;
        private final LinkedBlockingQueue<IBinder> b;

        private a() {
            this.a = false;
            this.b = new LinkedBlockingQueue(1);
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                this.b.put(iBinder);
            } catch (InterruptedException e) {
            }
        }

        public void onServiceDisconnected(ComponentName componentName) {
            this.b.clear();
        }

        public IBinder a() {
            if (this.a) {
                c.h().e("Fabric", "getBinder already called");
            }
            this.a = true;
            try {
                return (IBinder) this.b.poll(200, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                return null;
            }
        }
    }

    /* compiled from: AdvertisingInfoServiceStrategy */
    private static final class b implements IInterface {
        private final IBinder a;

        public boolean b() {
            /* JADX: method processing error */
/*
Error: java.lang.NullPointerException: Attempt to invoke virtual method 'int java.util.BitSet.nextSetBit(int)' on a null object reference
	at jadx.core.dex.visitors.ssa.SSATransform.placePhi(SSATransform.java:82)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:50)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:286)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:173)
*/
            /*
            r7 = this;
            r0 = 1;
            r1 = 0;
            r2 = android.os.Parcel.obtain();
            r3 = android.os.Parcel.obtain();
            r4 = "com.google.android.gms.ads.identifier.internal.IAdvertisingIdService";	 Catch:{ Exception -> 0x002c, all -> 0x0040 }
            r2.writeInterfaceToken(r4);	 Catch:{ Exception -> 0x002c, all -> 0x0040 }
            r4 = 1;	 Catch:{ Exception -> 0x002c, all -> 0x0040 }
            r2.writeInt(r4);	 Catch:{ Exception -> 0x002c, all -> 0x0040 }
            r4 = r7.a;	 Catch:{ Exception -> 0x002c, all -> 0x0040 }
            r5 = 2;	 Catch:{ Exception -> 0x002c, all -> 0x0040 }
            r6 = 0;	 Catch:{ Exception -> 0x002c, all -> 0x0040 }
            r4.transact(r5, r2, r3, r6);	 Catch:{ Exception -> 0x002c, all -> 0x0040 }
            r3.readException();	 Catch:{ Exception -> 0x002c, all -> 0x0040 }
            r4 = r3.readInt();	 Catch:{ Exception -> 0x002c, all -> 0x0040 }
            if (r4 == 0) goto L_0x002a;
        L_0x0023:
            r3.recycle();
            r2.recycle();
        L_0x0029:
            return r0;
        L_0x002a:
            r0 = r1;
            goto L_0x0023;
        L_0x002c:
            r0 = move-exception;
            r0 = a.a.a.a.c.h();	 Catch:{ Exception -> 0x002c, all -> 0x0040 }
            r4 = "Fabric";	 Catch:{ Exception -> 0x002c, all -> 0x0040 }
            r5 = "Could not get parcel from Google Play Service to capture Advertising limitAdTracking";	 Catch:{ Exception -> 0x002c, all -> 0x0040 }
            r0.a(r4, r5);	 Catch:{ Exception -> 0x002c, all -> 0x0040 }
            r3.recycle();
            r2.recycle();
            r0 = r1;
            goto L_0x0029;
        L_0x0040:
            r0 = move-exception;
            r3.recycle();
            r2.recycle();
            throw r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: a.a.a.a.a.b.e.b.b():boolean");
        }

        public b(IBinder iBinder) {
            this.a = iBinder;
        }

        public IBinder asBinder() {
            return this.a;
        }

        public String a() {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            String str = null;
            try {
                obtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                this.a.transact(1, obtain, obtain2, 0);
                obtain2.readException();
                str = obtain2.readString();
            } catch (Exception e) {
                c.h().a("Fabric", "Could not get parcel from Google Play Service to capture AdvertisingId");
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
            return str;
        }
    }

    public e(Context context) {
        this.a = context.getApplicationContext();
    }

    public b a() {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            c.h().a("Fabric", "AdvertisingInfoServiceStrategy cannot be called on the main thread");
            return null;
        }
        try {
            this.a.getPackageManager().getPackageInfo("com.android.vending", 0);
            ServiceConnection aVar = new a();
            Intent intent = new Intent("com.google.android.gms.ads.identifier.service.START");
            intent.setPackage("com.google.android.gms");
            try {
                if (this.a.bindService(intent, aVar, 1)) {
                    b bVar = new b(aVar.a());
                    b bVar2 = new b(bVar.a(), bVar.b());
                    this.a.unbindService(aVar);
                    return bVar2;
                }
                c.h().a("Fabric", "Could not bind to Google Play Service to capture AdvertisingId");
                return null;
            } catch (Throwable e) {
                c.h().d("Fabric", "Exception in binding to Google Play Service to capture AdvertisingId", e);
                this.a.unbindService(aVar);
                return null;
            } catch (Throwable e2) {
                c.h().a("Fabric", "Could not bind to Google Play Service to capture AdvertisingId", e2);
                return null;
            }
        } catch (NameNotFoundException e3) {
            c.h().a("Fabric", "Unable to find Google Play Services package name");
            return null;
        } catch (Throwable e22) {
            c.h().a("Fabric", "Unable to determine if Google Play Services is available", e22);
            return null;
        }
    }
}
