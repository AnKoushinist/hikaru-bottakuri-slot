package com.applovin.impl.sdk;

import com.applovin.nativeAds.AppLovinNativeAdLoadListener;
import com.applovin.sdk.AppLovinLogger;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

abstract class bv implements w, AppLovinNativeAdLoadListener {
    protected final AppLovinSdkImpl a;
    protected final AppLovinLogger b;
    protected final Object c = new Object();
    protected final Map d = a();
    protected final Map e = new HashMap();
    protected final Set f = new HashSet();

    bv(AppLovinSdkImpl appLovinSdkImpl) {
        this.a = appLovinSdkImpl;
        this.b = appLovinSdkImpl.h();
    }

    private bw g(c cVar) {
        return (bw) this.d.get(cVar);
    }

    abstract c a(bd bdVar);

    abstract ca a(c cVar);

    abstract Map a();

    abstract void a(Object obj, bd bdVar);

    abstract void a(Object obj, c cVar, int i);

    public boolean a(c cVar, Object obj) {
        boolean z;
        synchronized (this.c) {
            if (f(cVar)) {
                z = false;
            } else {
                b(cVar, obj);
                z = true;
            }
        }
        return z;
    }

    public bd b(c cVar) {
        bd e;
        synchronized (this.c) {
            e = g(cVar).e();
        }
        return e;
    }

    void b(bd bdVar) {
        e(a(bdVar));
    }

    protected void b(c cVar, int i) {
        this.b.a("PreloadManager", "Failed to pre-load an ad of spec " + cVar + ", error code " + i);
        synchronized (this.c) {
            Object remove = this.e.remove(cVar);
            this.f.add(cVar);
        }
        if (remove != null) {
            try {
                a(remove, cVar, i);
            } catch (Throwable th) {
                this.a.h().c("PreloadManager", "Encountered exception while invoking user callback", th);
            }
        }
    }

    public void b(c cVar, Object obj) {
        synchronized (this.c) {
            if (this.e.containsKey(cVar)) {
                this.b.c("PreloadManager", "Possibly missing prior registered preload callback.");
            }
            this.e.put(cVar, obj);
        }
    }

    protected void c(bd bdVar) {
        synchronized (this.c) {
            c a = a(bdVar);
            Object obj = this.e.get(a);
            this.e.remove(a);
            this.f.add(a);
            if (obj == null) {
                g(a).a(bdVar);
                this.b.a("PreloadManager", "Ad enqueued: " + bdVar);
            } else {
                this.b.a("PreloadManager", "Additional callback found, skipping enqueue.");
            }
        }
        if (obj != null) {
            this.b.a("PreloadManager", "Called additional callback regarding " + bdVar);
            try {
                a(obj, bdVar);
            } catch (Throwable th) {
                this.a.h().c("PreloadManager", "Encountered throwable while notifying user callback", th);
            }
            b(bdVar);
        }
        this.b.a("PreloadManager", "Pulled ad from network and saved to preload cache: " + bdVar);
    }

    public boolean c(c cVar) {
        boolean c;
        synchronized (this.c) {
            c = g(cVar).c();
        }
        return c;
    }

    public void d(c cVar) {
        int i = 0;
        if (cVar != null) {
            int b;
            synchronized (this.c) {
                bw g = g(cVar);
                b = g != null ? g.b() - g.a() : 0;
            }
            if (b > 0) {
                while (i < b) {
                    e(cVar);
                    i++;
                }
            }
        }
    }

    public void e(c cVar) {
        if (((Boolean) this.a.a(cb.A)).booleanValue() && !c(cVar)) {
            this.b.a("PreloadManager", "Preloading ad for spec " + cVar + "...");
            this.a.m().a(a(cVar), cw.MAIN, 500);
        }
    }

    boolean f(c cVar) {
        boolean contains;
        synchronized (this.c) {
            contains = this.f.contains(cVar);
        }
        return contains;
    }
}
