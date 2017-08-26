package com.vungle.publisher;

import android.os.SystemClock;
import com.vungle.log.Logger;
import com.vungle.publisher.bt.b;
import com.vungle.publisher.vs.c;
import java.util.EnumMap;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: vungle */
public final class we {
    public vs a;
    vz b;
    public xk c;
    b d;
    @Inject
    wi e;

    @Singleton
    /* compiled from: vungle */
    public static class a {
        private static final EnumMap<c, b> a;
        @Inject
        Provider<we> c;

        static {
            EnumMap enumMap = new EnumMap(c.class);
            a = enumMap;
            enumMap.put(c.download, b.downloadLocalAd);
            a.put(c.reportAd, b.reportAd);
            a.put(c.requestConfig, b.requestConfig);
            a.put(c.requestLocalAd, b.requestLocalAd);
            a.put(c.requestStreamingAd, b.requestStreamingAd);
            a.put(c.sessionEnd, b.sessionEnd);
            a.put(c.sessionStart, b.sessionStart);
            a.put(c.trackEvent, b.externalNetworkRequest);
            a.put(c.trackInstall, b.reportInstall);
            a.put(c.unfilledAd, b.unfilledAd);
            a.put(c.appFingerprint, b.appFingerprint);
            a.put(c.reportExceptions, b.reportExceptions);
        }

        public final we a(vs vsVar, vz vzVar) {
            xk xkVar = new xk();
            we weVar = (we) this.c.get();
            weVar.a = vsVar;
            weVar.b = vzVar;
            b bVar = (b) a.get(vsVar.a());
            if (bVar == null) {
                Logger.w(Logger.NETWORK_TAG, "missing mapping for HttpTransaction requestType = " + vsVar.a().toString());
                bVar = b.otherTask;
            }
            weVar.d = bVar;
            weVar.c = xkVar;
            return weVar;
        }
    }

    @Inject
    we() {
    }

    public final void a() {
        xk xkVar = this.c;
        if (xkVar.a <= 0) {
            xkVar.a = SystemClock.elapsedRealtime();
        }
        xkVar.b++;
        xkVar.c++;
        this.b.b(this, this.e.a(this.a));
    }

    public final String toString() {
        return "{" + this.a + ", " + this.c + "}";
    }
}
