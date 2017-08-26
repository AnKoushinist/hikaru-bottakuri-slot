package com.applovin.impl.sdk;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Build.VERSION;
import android.util.Log;
import com.applovin.nativeAds.AppLovinNativeAdService;
import com.applovin.sdk.AppLovinAdService;
import com.applovin.sdk.AppLovinEventService;
import com.applovin.sdk.AppLovinLogger;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkSettings;
import com.applovin.sdk.AppLovinTargetingData;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;
import org.cocos2dx.lib.BuildConfig;

public class AppLovinSdkImpl extends AppLovinSdk {
    private String a;
    private AppLovinSdkSettings b;
    private Context c;
    private AppLovinLogger d;
    private cv e;
    private ce f;
    private o g;
    private cg h;
    private y i;
    private b j;
    private bg k;
    private r l;
    private m m;
    private AppLovinAdServiceImpl n;
    private bi o;
    private PostbackServiceImpl p;
    private EventServiceImpl q;
    private br r;
    private boolean s = true;
    private boolean t = false;
    private boolean u = false;
    private boolean v = false;
    private boolean w = false;
    private boolean x = false;

    private static boolean A() {
        return (VERSION.RELEASE.startsWith("1.") || VERSION.RELEASE.startsWith("2.0") || VERSION.RELEASE.startsWith("2.1")) ? false : true;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void d(android.content.Context r7) {
        /*
        r6 = this;
        r5 = 632; // 0x278 float:8.86E-43 double:3.122E-321;
        r1 = android.preference.PreferenceManager.getDefaultSharedPreferences(r7);
        r0 = "com.applovin.sdk.impl.lastKnownVersionCode";
        r2 = 0;
        r0 = r1.getInt(r0, r2);	 Catch:{ Exception -> 0x003a }
        if (r0 >= r5) goto L_0x0032;
    L_0x000f:
        r0 = "AppLovinSdkImpl";
        r2 = "SDK has been updated since last run. Continuing...";
        android.util.Log.i(r0, r2);	 Catch:{ Exception -> 0x003a }
        r0 = r6.i();	 Catch:{ Exception -> 0x003a }
        r0.d();	 Catch:{ Exception -> 0x003a }
        r0 = r6.i();	 Catch:{ Exception -> 0x003a }
        r0.b();	 Catch:{ Exception -> 0x003a }
    L_0x0024:
        r0 = r1.edit();
        r1 = "com.applovin.sdk.impl.lastKnownVersionCode";
        r0 = r0.putInt(r1, r5);
        r0.apply();
    L_0x0031:
        return;
    L_0x0032:
        r0 = "AppLovinSdkImpl";
        r2 = "SDK has not been updated since last run. Continuing...";
        android.util.Log.d(r0, r2);	 Catch:{ Exception -> 0x003a }
        goto L_0x0024;
    L_0x003a:
        r0 = move-exception;
        r2 = r6.h();	 Catch:{ all -> 0x0054 }
        r3 = "AppLovinSdkImpl";
        r4 = "Unable to check for SDK update";
        r2.b(r3, r4, r0);	 Catch:{ all -> 0x0054 }
        r0 = r1.edit();
        r1 = "com.applovin.sdk.impl.lastKnownVersionCode";
        r0 = r0.putInt(r1, r5);
        r0.apply();
        goto L_0x0031;
    L_0x0054:
        r0 = move-exception;
        r1 = r1.edit();
        r2 = "com.applovin.sdk.impl.lastKnownVersionCode";
        r1 = r1.putInt(r2, r5);
        r1.apply();
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.applovin.impl.sdk.AppLovinSdkImpl.d(android.content.Context):void");
    }

    Object a(cd cdVar) {
        return this.f.a(cdVar);
    }

    public String a() {
        return this.a;
    }

    public void a(String str, AppLovinSdkSettings appLovinSdkSettings, Context context) {
        this.a = str;
        this.b = appLovinSdkSettings;
        this.c = context;
        try {
            k kVar = new k();
            this.d = kVar;
            this.f = new ce(this);
            this.e = new cv(this);
            this.g = new o(this);
            this.h = new cg(this);
            this.i = new y(this);
            this.l = new r(this);
            this.n = new AppLovinAdServiceImpl(this);
            this.o = new bi(this);
            this.p = new PostbackServiceImpl(this);
            this.q = new EventServiceImpl(this);
            this.r = new br(this);
            this.j = new b(this);
            this.k = new bg(this);
            this.m = new m(this);
            if (!A()) {
                this.v = true;
                Log.e("AppLovinSdk", "Unable to initalize AppLovin SDK: Android SDK version has to be at least level 8");
            }
            if (str == null || str.length() < 1) {
                this.w = true;
                Log.e("AppLovinSdk", "Unable to find AppLovin SDK key. Please add     meta-data android:name=\"applovin.sdk.key\" android:value=\"YOUR_SDK_KEY_HERE\" into AndroidManifest.xml.");
                Writer stringWriter = new StringWriter();
                new Throwable(BuildConfig.FLAVOR).printStackTrace(new PrintWriter(stringWriter));
                Log.e("AppLovinSdk", "Called with an invalid SDK key from: " + stringWriter.toString());
            }
            if (d()) {
                b(false);
                return;
            }
            kVar.a(this.f);
            if (appLovinSdkSettings instanceof bb) {
                kVar.a(((bb) appLovinSdkSettings).a());
            }
            d(context);
            this.f.c();
            if (((Boolean) this.f.a(cb.b)).booleanValue()) {
                this.f.a(appLovinSdkSettings);
                this.f.b();
            }
            y();
        } catch (Throwable th) {
            Log.e("AppLovinSdk", "Failed to load AppLovin SDK, ad serving will be disabled", th);
            b(false);
        }
    }

    public void a(boolean z) {
        this.x = z;
    }

    public boolean a(Context context) {
        try {
            h().a("AppLovinSdk", "Checking if sdk is initialized in main activity...");
            Intent intent = new Intent("android.intent.action.MAIN");
            intent.addCategory("android.intent.category.LAUNCHER");
            intent.setPackage(context.getPackageName());
            String stackTraceString = Log.getStackTraceString(new Throwable());
            List<ResolveInfo> queryIntentActivities = context.getPackageManager().queryIntentActivities(intent, 0);
            if (queryIntentActivities != null) {
                h().a("AppLovinSdk", "Found " + queryIntentActivities.size() + " main activities for this application");
                for (ResolveInfo resolveInfo : queryIntentActivities) {
                    if (stackTraceString.contains(resolveInfo.activityInfo.name)) {
                        return true;
                    }
                }
            }
            h().c("AppLovinSdk", "AppLovin SDK was initialized too late in session; SDK should always be initialized within main activity and/or any relevant entry points");
            h().c("AppLovinSdk", "Initialization instead happened from: " + stackTraceString);
        } catch (Throwable th) {
            h().b("AppLovinSdk", "Error checking if sdk is initialized in main activity...", th);
        }
        return false;
    }

    public AppLovinSdkSettings b() {
        return this.b;
    }

    void b(boolean z) {
        this.s = false;
        this.t = z;
        this.u = true;
    }

    public boolean c() {
        return this.t;
    }

    public boolean d() {
        return this.v || this.w;
    }

    public AppLovinAdService e() {
        return this.n;
    }

    public AppLovinTargetingData f() {
        return this.m;
    }

    public boolean g() {
        return this.x;
    }

    public AppLovinLogger h() {
        return this.d;
    }

    public ce i() {
        return this.f;
    }

    public Context j() {
        return this.c;
    }

    public o k() {
        return this.g;
    }

    public void l() {
    }

    cv m() {
        return this.e;
    }

    cg n() {
        return this.h;
    }

    public y o() {
        return this.i;
    }

    b p() {
        return this.j;
    }

    bg q() {
        return this.k;
    }

    public PostbackServiceImpl r() {
        return this.p;
    }

    public AppLovinNativeAdService s() {
        return this.o;
    }

    public AppLovinEventService t() {
        return this.q;
    }

    public br u() {
        return this.r;
    }

    public r v() {
        return this.l;
    }

    boolean w() {
        return this.s;
    }

    boolean x() {
        return this.u;
    }

    void y() {
        this.s = true;
        this.e.a(new cu(this), 0);
    }

    void z() {
        this.f.d();
        this.f.b();
        this.h.a();
    }
}
