package com.vungle.publisher;

import android.content.Context;
import android.location.Location;
import com.vungle.log.Logger;
import com.vungle.publisher.inject.Injector;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.inject.Inject;

/* compiled from: vungle */
public final class sk implements sj {
    boolean a = true;
    boolean b = true;
    @Inject
    Context c;
    private final AtomicBoolean d = new AtomicBoolean(false);
    private Location e;
    private final sj f;
    private final sj g;

    @Inject
    sk() {
        sn snVar;
        sj sjVar;
        Object obj;
        Throwable th;
        sj sjVar2 = null;
        Injector.b().a(this);
        try {
            snVar = new sn(this.c);
            try {
                sjVar2 = new so(this.c);
                sjVar = snVar;
            } catch (NoClassDefFoundError e) {
                Logger.i(Logger.LOCATION_TAG, "GoogleLocationServicesDetailedLocationProvider not found: " + e);
                obj = snVar;
            } catch (Throwable th2) {
                Throwable th3 = th2;
                obj = snVar;
                th = th3;
                Logger.i(Logger.LOCATION_TAG, "error initializing detailed location providers ", th);
            }
        } catch (NoClassDefFoundError e2) {
            Logger.i(Logger.LOCATION_TAG, "GoogleLocationClientDetailedLocationProvider not found: " + e2);
            snVar = null;
        } catch (Throwable th22) {
            th = th22;
            sjVar = null;
            Logger.i(Logger.LOCATION_TAG, "error initializing detailed location providers ", th);
            this.f = sjVar;
            this.g = sjVar2;
        }
        this.f = sjVar;
        this.g = sjVar2;
    }

    public final Location b() {
        if (this.d.compareAndSet(false, true)) {
            Object obj = null;
            try {
                if (this.b) {
                    obj = this.g;
                    if (obj != null) {
                        this.e = obj.b();
                    }
                }
            } catch (Throwable e) {
                Logger.i(Logger.LOCATION_TAG, "permanent error obtaining detailed location " + obj, e);
                this.b = false;
            } catch (Throwable e2) {
                Logger.i(Logger.LOCATION_TAG, "error obtaining detailed location " + obj, e2);
            }
            if (this.a && this.e == null) {
                try {
                    sj sjVar = this.f;
                    if (sjVar != null) {
                        this.e = sjVar.b();
                    }
                } catch (Throwable e22) {
                    Logger.i(Logger.LOCATION_TAG, "permanent error obtaining detailed location " + obj, e22);
                    this.a = false;
                } catch (Throwable e222) {
                    Logger.i(Logger.LOCATION_TAG, "error obtaining detailed location " + obj, e222);
                }
            }
        }
        return this.e;
    }
}
