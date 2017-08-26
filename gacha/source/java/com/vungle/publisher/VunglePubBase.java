package com.vungle.publisher;

import android.content.Context;
import android.os.Build.VERSION;
import android.os.SystemClock;
import com.vungle.log.Logger;
import com.vungle.publisher.a.AnonymousClass2;
import com.vungle.publisher.bt.b;
import com.vungle.publisher.inject.EndpointModule;
import com.vungle.publisher.inject.Injector;
import com.vungle.publisher.mt.a;
import com.vungle.publisher.mt.a.AnonymousClass1;
import dagger.a.e;
import javax.inject.Inject;

/* compiled from: vungle */
public abstract class VunglePubBase {
    public static final String VERSION = "VungleDroid/4.0.3";
    @Inject
    a a;
    @Inject
    InitializationEventListener b;
    @Inject
    qu c;
    @Inject
    cf d;
    @Inject
    Demographic e;
    @Inject
    protected pn f;
    @Inject
    ql g;
    @Inject
    AdConfig h;
    @Inject
    q i;
    @Inject
    pv j;
    @Inject
    py k;
    @Inject
    pp l;
    @Inject
    Context m;
    @Inject
    a n;
    private boolean o;
    private boolean p;

    protected VunglePubBase() {
    }

    public boolean init(Context context, String str) {
        boolean z = this.p;
        if (z) {
            try {
                Logger.d(MovieReward_6006.ADNETWORK_NAME, "already initialized");
                return z;
            } catch (Throwable e) {
                Logger.e(MovieReward_6006.ADNETWORK_NAME, "VunglePub initialization failed", e);
                return z;
            }
        }
        int i = VERSION.SDK_INT;
        pj pjVar = pj.HONEYCOMB;
        boolean a = agl.a(pjVar);
        if (a) {
            Logger.d(Logger.DEVICE_TAG, "Device Android API level " + i);
        } else {
            Logger.w(Logger.DEVICE_TAG, "Device Android API level " + VERSION.SDK_INT + " does not meet required minimum " + pjVar);
        }
        if (a) {
            Object obj;
            if (this.o) {
                Logger.d(MovieReward_6006.ADNETWORK_NAME, "already injected");
            } else {
                Injector instance = Injector.getInstance();
                try {
                    if (instance.a) {
                        Logger.d(Logger.INJECT_TAG, "already initialized");
                    } else {
                        Logger.d(Logger.INJECT_TAG, "initializing");
                        re a2 = instance.a();
                        if (a2.g) {
                            Logger.d(Logger.INJECT_TAG, "publisher module already initialized");
                        } else {
                            Logger.d(Logger.INJECT_TAG, "initializing publisher module");
                            a2.a = context.getApplicationContext();
                            a2.b = str;
                            a2.g = true;
                        }
                        rw.a a3 = rw.a();
                        a3.a = (re) e.a(instance.a());
                        if (instance.b == null) {
                            instance.b = new EndpointModule();
                        }
                        a3.c = (EndpointModule) e.a(instance.b);
                        if (instance.c == null) {
                            instance.c = new rz();
                        }
                        a3.b = (rz) e.a(instance.c);
                        if (a3.a == null) {
                            a3.a = new re();
                        }
                        if (a3.b == null) {
                            a3.b = new rz();
                        }
                        if (a3.c == null) {
                            a3.c = new EndpointModule();
                        }
                        instance.d = new rw(a3);
                        instance.a = true;
                    }
                } catch (Throwable e2) {
                    Logger.e(Logger.INJECT_TAG, "error initializing injector", e2);
                }
                Injector.b().a(this);
                Logger.d(MovieReward_6006.ADNETWORK_NAME, "injection successful");
                this.o = true;
            }
            if (agr.b(this.m).length == 0) {
                obj = 1;
            } else {
                obj = null;
            }
            if (obj != null) {
                Logger.i(MovieReward_6006.ADNETWORK_NAME, "VungleDroid/4.0.3 init(" + str + ")");
                qu quVar = this.c;
                Logger.d(Logger.FILE_TAG, "deleting old ad temp directory");
                qu.a((String) quVar.b.get());
                this.b.register();
                cf cfVar = this.d;
                cfVar.d.a(new Runnable(cfVar) {
                    final /* synthetic */ cf a;

                    {
                        this.a = r1;
                    }

                    public final void run() {
                        Logger.d(Logger.DATABASE_TAG, "initializing database vungle");
                        this.a.getWritableDatabase();
                        Logger.d(Logger.DATABASE_TAG, "done initializing database vungle");
                        this.a.b.a(new ci());
                    }
                }, b.databaseWrite);
                this.f.q();
                a aVar = this.n;
                Context context2 = this.m;
                bw bwVar = aVar.c;
                bwVar.a.post(new AnonymousClass1(aVar, context2));
                Logger.v(MovieReward_6006.ADNETWORK_NAME, "initialization successful");
                this.p = true;
                return true;
            }
            Logger.w(MovieReward_6006.ADNETWORK_NAME, "initialization failed");
        }
        return z;
    }

    public String[] getMissingPermissions() {
        String[] strArr = null;
        try {
            if (a()) {
                strArr = agr.b(this.m);
            }
        } catch (Throwable e) {
            Logger.e(MovieReward_6006.ADNETWORK_NAME, "error getting missing permissions", e);
        }
        return strArr;
    }

    private boolean a(boolean z, String str) {
        boolean z2 = this.p;
        if (z2) {
            Logger.v(MovieReward_6006.ADNETWORK_NAME, "VunglePub was initialized");
        } else if (z) {
            Logger.w(MovieReward_6006.ADNETWORK_NAME, "Please call VunglePub.init() before " + str);
        }
        return z2;
    }

    private boolean a() {
        boolean z = this.o;
        if (!z) {
            Logger.d(MovieReward_6006.ADNETWORK_NAME, "VunglePub not injected");
        }
        return z;
    }

    public Demographic getDemographic() {
        try {
            a();
        } catch (Throwable e) {
            Logger.e(MovieReward_6006.ADNETWORK_NAME, "error getting demographic info", e);
        }
        return this.e;
    }

    public void addEventListeners(EventListener... eventListenerArr) {
        try {
            if (a()) {
                this.j.a(eventListenerArr);
            }
        } catch (Throwable e) {
            Logger.e(MovieReward_6006.ADNETWORK_NAME, "error adding event listeners", e);
        }
    }

    public void setEventListeners(EventListener... eventListenerArr) {
        try {
            if (a()) {
                pv pvVar = this.j;
                pvVar.a();
                pvVar.a(eventListenerArr);
            }
        } catch (Throwable e) {
            Logger.e(MovieReward_6006.ADNETWORK_NAME, "error setting event listeners", e);
        }
    }

    public void clearEventListeners() {
        try {
            if (a()) {
                this.j.a();
            }
        } catch (Throwable e) {
            Logger.e(MovieReward_6006.ADNETWORK_NAME, "error clearing event listeners", e);
        }
    }

    public void removeEventListeners(EventListener... eventListenerArr) {
        try {
            if (a()) {
                pv pvVar = this.j;
                if (eventListenerArr != null) {
                    for (Object obj : eventListenerArr) {
                        qn qnVar = (qn) pvVar.a.remove(obj);
                        if ((qnVar != null ? 1 : null) != null) {
                            Logger.d(Logger.CONFIG_TAG, "removing event listener " + obj);
                            qnVar.unregister();
                        } else {
                            Logger.d(Logger.CONFIG_TAG, "event listener not found for remove " + obj);
                        }
                    }
                }
            }
        } catch (Throwable e) {
            Logger.e(MovieReward_6006.ADNETWORK_NAME, "error removing event listeners", e);
        }
    }

    public AdConfig getGlobalAdConfig() {
        try {
            a();
        } catch (Throwable e) {
            Logger.e(MovieReward_6006.ADNETWORK_NAME, "error getting globalAdConfig", e);
        }
        return this.h;
    }

    public void onResume() {
        try {
            if (a(false, "onResume()")) {
                py pyVar = this.k;
                Logger.d(Logger.AD_TAG, "onDeveloperActivityResume()");
                pyVar.a(true);
            }
        } catch (Throwable e) {
            Logger.e(MovieReward_6006.ADNETWORK_NAME, "error onResume()", e);
        }
    }

    public void onPause() {
        try {
            if (a(false, "onPause()")) {
                py pyVar = this.k;
                Logger.d(Logger.AD_TAG, "onDeveloperActivityPause()");
                pyVar.b();
            }
        } catch (Throwable e) {
            Logger.e(MovieReward_6006.ADNETWORK_NAME, "error onPause()", e);
        }
    }

    public boolean isAdPlayable() {
        boolean z = false;
        try {
            if (a(true, "isAdPlayable()")) {
                z = this.a.a();
            }
        } catch (Throwable e) {
            Logger.e(MovieReward_6006.ADNETWORK_NAME, "error returning ad playable", e);
        }
        return z;
    }

    public void playAd() {
        playAd(null);
    }

    public void playAd(AdConfig adConfig) {
        int i = 1;
        int i2 = 0;
        try {
            Logger.d(Logger.AD_TAG, "VunglePub.playAd()");
            if (a(true, "playAd()")) {
                pp ppVar = this.l;
                if (!ppVar.a()) {
                    ppVar.eventBus.a(new bk((int) ((SystemClock.elapsedRealtime() - ppVar.b()) / 1000), ppVar.c()));
                    i = 0;
                } else if (!ppVar.d.compareAndSet(false, true)) {
                    Logger.d(Logger.AD_TAG, "ad already playing");
                    ppVar.eventBus.a(new bg());
                    i = 0;
                }
                if (i != 0) {
                    ppVar.register();
                }
            } else {
                if (this.o) {
                    this.g.a(new bj());
                }
                i = 0;
            }
            if (i != 0) {
                a aVar = this.a;
                AdConfig[] adConfigArr = new AdConfig[]{this.h, adConfig};
                m[] mVarArr = new m[2];
                int i3 = 0;
                while (i3 < 2) {
                    AdConfig adConfig2 = adConfigArr[i3];
                    if (adConfig2 != null) {
                        i = i2 + 1;
                        mVarArr[i2] = adConfig2.getDelegateAdConfig();
                    } else {
                        i = i2;
                    }
                    i3++;
                    i2 = i;
                }
                aVar.g.a(new AnonymousClass2(aVar, new p(mVarArr)), b.otherTask);
            }
        } catch (Throwable e) {
            Logger.e(Logger.AD_TAG, "error playing ad", e);
            if (this.o) {
                this.g.a(new bi());
            }
        }
    }
}
