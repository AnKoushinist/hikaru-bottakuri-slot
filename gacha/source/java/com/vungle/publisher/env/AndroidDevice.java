package com.vungle.publisher.env;

import android.app.KeyguardManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.webkit.WebView;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.vungle.log.Logger;
import com.vungle.publisher.agl;
import com.vungle.publisher.ago;
import com.vungle.publisher.agr;
import com.vungle.publisher.inject.Injector;
import com.vungle.publisher.lr;
import com.vungle.publisher.pj;
import com.vungle.publisher.pn;
import com.vungle.publisher.ql;
import java.io.File;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.cocos2dx.lib.BuildConfig;

@Singleton
/* compiled from: vungle */
public final class AndroidDevice implements pn {
    static int a = 5000;
    final AtomicBoolean b = new AtomicBoolean();
    public boolean c;
    public String d;
    public String e;
    public String f;
    @Inject
    public lr g;
    @Inject
    public WindowManager h;
    @Inject
    public Context i;
    @Inject
    public ql j;
    @Inject
    public SharedPreferences k;
    @Inject
    public DeviceIdStrategy l;
    @Inject
    public String m;
    private final String n = VERSION.RELEASE;

    /* compiled from: vungle */
    public static abstract class DeviceIdStrategy {
        public abstract void c(AndroidDevice androidDevice);
    }

    private void t() {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find block by offset: 0x006b in list [B:35:0x0064]
	at jadx.core.utils.BlockUtils.getBlockByOffset(BlockUtils.java:42)
	at jadx.core.dex.instructions.IfNode.initBlocks(IfNode.java:60)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.initBlocksInIfNodes(BlockFinish.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.visit(BlockFinish.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:286)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:173)
*/
        /*
        r6 = this;
        r0 = r6.b;	 Catch:{ all -> 0x003f }
        r0 = r0.get();	 Catch:{ all -> 0x003f }
        if (r0 != 0) goto L_0x005e;	 Catch:{ all -> 0x003f }
    L_0x0008:
        r0 = java.lang.System.currentTimeMillis();	 Catch:{ all -> 0x003f }
        r2 = a;	 Catch:{ all -> 0x003f }
        r2 = (long) r2;	 Catch:{ all -> 0x003f }
        r0 = r0 + r2;	 Catch:{ all -> 0x003f }
        r2 = r6.b;	 Catch:{ all -> 0x003f }
        monitor-enter(r2);	 Catch:{ all -> 0x003f }
        r3 = "VungleDevice";	 Catch:{ InterruptedException -> 0x0033 }
        r4 = "waiting for device ID";	 Catch:{ InterruptedException -> 0x0033 }
        com.vungle.log.Logger.d(r3, r4);	 Catch:{ InterruptedException -> 0x0033 }
    L_0x001a:
        r3 = r6.b;	 Catch:{ InterruptedException -> 0x0033 }
        r3 = r3.get();	 Catch:{ InterruptedException -> 0x0033 }
        if (r3 != 0) goto L_0x004e;	 Catch:{ InterruptedException -> 0x0033 }
    L_0x0022:
        r4 = java.lang.System.currentTimeMillis();	 Catch:{ InterruptedException -> 0x0033 }
        r3 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1));
        if (r3 >= 0) goto L_0x004e;
    L_0x002a:
        r3 = r6.b;	 Catch:{ InterruptedException -> 0x0033 }
        r4 = a;	 Catch:{ InterruptedException -> 0x0033 }
        r4 = (long) r4;	 Catch:{ InterruptedException -> 0x0033 }
        r3.wait(r4);	 Catch:{ InterruptedException -> 0x0033 }
        goto L_0x001a;
    L_0x0033:
        r3 = move-exception;
        r3 = "VungleDevice";	 Catch:{ InterruptedException -> 0x0033 }
        r4 = "interrupted while awaiting device ID";	 Catch:{ InterruptedException -> 0x0033 }
        com.vungle.log.Logger.v(r3, r4);	 Catch:{ InterruptedException -> 0x0033 }
        goto L_0x001a;	 Catch:{ InterruptedException -> 0x0033 }
    L_0x003c:
        r0 = move-exception;	 Catch:{ InterruptedException -> 0x0033 }
        monitor-exit(r2);	 Catch:{ InterruptedException -> 0x0033 }
        throw r0;	 Catch:{ all -> 0x003f }
    L_0x003f:
        r0 = move-exception;
        r1 = r6.f();
        if (r1 != 0) goto L_0x004d;
    L_0x0046:
        r1 = "VungleDevice";
        r2 = "no device ID available";
        com.vungle.log.Logger.w(r1, r2);
    L_0x004d:
        throw r0;
    L_0x004e:
        monitor-exit(r2);	 Catch:{ InterruptedException -> 0x0033 }
        r0 = r6.b;	 Catch:{ all -> 0x003f }
        r0 = r0.get();	 Catch:{ all -> 0x003f }
        if (r0 == 0) goto L_0x006c;	 Catch:{ all -> 0x003f }
    L_0x0057:
        r0 = "VungleDevice";	 Catch:{ all -> 0x003f }
        r1 = "obtained device ID";	 Catch:{ all -> 0x003f }
        com.vungle.log.Logger.d(r0, r1);	 Catch:{ all -> 0x003f }
    L_0x005e:
        r0 = r6.f();
        if (r0 != 0) goto L_0x006b;
    L_0x0064:
        r0 = "VungleDevice";
        r1 = "no device ID available";
        com.vungle.log.Logger.w(r0, r1);
    L_0x006b:
        return;
    L_0x006c:
        r0 = new com.vungle.publisher.po;	 Catch:{ all -> 0x003f }
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x003f }
        r2 = "timeout after ";	 Catch:{ all -> 0x003f }
        r1.<init>(r2);	 Catch:{ all -> 0x003f }
        r2 = a;	 Catch:{ all -> 0x003f }
        r1 = r1.append(r2);	 Catch:{ all -> 0x003f }
        r2 = " ms";	 Catch:{ all -> 0x003f }
        r1 = r1.append(r2);	 Catch:{ all -> 0x003f }
        r1 = r1.toString();	 Catch:{ all -> 0x003f }
        r0.<init>(r1);	 Catch:{ all -> 0x003f }
        throw r0;	 Catch:{ all -> 0x003f }
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.env.AndroidDevice.t():void");
    }

    @Inject
    public AndroidDevice() {
        Injector.b().a(this);
    }

    public final String a() {
        t();
        return this.d;
    }

    public final boolean b() {
        return !TextUtils.isEmpty(this.d);
    }

    public final String c() {
        t();
        String str = this.e;
        if (!a(str) || !b()) {
            return str;
        }
        Logger.w(Logger.DEVICE_TAG, "have advertising and Android ID");
        d();
        return null;
    }

    public final void d() {
        Logger.i(Logger.DEVICE_TAG, "clearing Android ID");
        this.e = null;
    }

    public static boolean a(String str) {
        return !TextUtils.isEmpty(str);
    }

    public final void e() {
        if (!this.b.getAndSet(true)) {
            synchronized (this.b) {
                this.b.notifyAll();
            }
        }
    }

    public final boolean f() {
        return b() || a(this.e) || l();
    }

    public final String g() {
        return this.n;
    }

    public final DisplayMetrics h() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        try {
            this.h.getDefaultDisplay().getMetrics(displayMetrics);
        } catch (Throwable e) {
            Logger.d(Logger.DEVICE_TAG, "error getting display metrics", e);
        }
        return displayMetrics;
    }

    public final boolean i() {
        return this.c;
    }

    public final String j() {
        String str = this.f;
        if (!l() || !b()) {
            return str;
        }
        Logger.w(Logger.DEVICE_TAG, "have advertising and mac address");
        k();
        return null;
    }

    public final void k() {
        Logger.i(Logger.DEVICE_TAG, "clearing MAC address");
        this.f = null;
    }

    public final boolean l() {
        return !TextUtils.isEmpty(this.f);
    }

    public final String m() {
        try {
            return (Build.MANUFACTURER == null ? BuildConfig.FLAVOR : Build.MANUFACTURER) + "," + (Build.MODEL == null ? BuildConfig.FLAVOR : Build.MODEL);
        } catch (Throwable e) {
            Logger.d(Logger.DEVICE_TAG, "error getting device model", e);
            return null;
        }
    }

    public final Float n() {
        Float f = null;
        try {
            f = Float.valueOf(this.g.c());
        } catch (Throwable e) {
            Logger.d(Logger.DEVICE_TAG, "error getting volume info", e);
        }
        return f;
    }

    public final boolean o() {
        boolean equals = "mounted".equals(Environment.getExternalStorageState());
        boolean a = agr.a(this.i);
        if (a && equals) {
            Logger.v(Logger.DEVICE_TAG, "external storage writable");
        } else {
            Logger.w(Logger.DEVICE_TAG, "external storage not writable");
        }
        return a && equals;
    }

    public final boolean b(String str) {
        boolean z;
        Throwable e;
        Throwable th;
        try {
            int isGooglePlayServicesAvailable = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this.i);
            z = isGooglePlayServicesAvailable == 0;
            if (!z) {
                try {
                    Logger.i(str, "Google Play Services not available: " + GooglePlayServicesUtil.getErrorString(isGooglePlayServicesAvailable));
                } catch (IllegalStateException e2) {
                    e = e2;
                    Logger.w(Logger.CONFIG_TAG, ago.a(e));
                    return z;
                } catch (NoClassDefFoundError e3) {
                    e = e3;
                    Logger.d(str, e.getClass().getSimpleName() + ": " + e.getMessage());
                    Logger.v(str, e);
                    return z;
                }
            }
        } catch (Throwable e4) {
            th = e4;
            z = false;
            e = th;
            Logger.w(Logger.CONFIG_TAG, ago.a(e));
            return z;
        } catch (Throwable e42) {
            th = e42;
            z = false;
            e = th;
            Logger.d(str, e.getClass().getSimpleName() + ": " + e.getMessage());
            Logger.v(str, e);
            return z;
        }
        return z;
    }

    public final String p() {
        if (b(Logger.DEVICE_TAG)) {
            return Integer.toString(4030500);
        }
        return null;
    }

    public final void q() {
        this.l.c(this);
    }

    public final String r() {
        return this.k.getString("defaultUserAgent", null);
    }

    public final void a(WebView webView) {
        this.k.edit().putString("defaultUserAgent", webView.getSettings().getUserAgentString()).commit();
    }

    public final boolean a(Context context) {
        return ((KeyguardManager) context.getSystemService("keyguard")).inKeyguardRestrictedInputMode();
    }

    public final Long s() {
        try {
            StatFs statFs = new StatFs(new File(this.m).getPath());
            if (agl.a(pj.JELLY_BEAN_MR2)) {
                return Long.valueOf(statFs.getAvailableBlocksLong() * statFs.getBlockSizeLong());
            }
            return Long.valueOf((long) (statFs.getAvailableBlocks() * statFs.getBlockSize()));
        } catch (Exception e) {
            return null;
        }
    }
}
