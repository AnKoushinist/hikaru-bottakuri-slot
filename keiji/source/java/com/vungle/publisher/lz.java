package com.vungle.publisher;

import android.content.pm.PackageInfo;
import com.vungle.log.Logger;
import com.vungle.publisher.bt.b;
import com.vungle.publisher.ly.a;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.json.JSONObject;

@Singleton
/* compiled from: vungle */
public final class lz {
    @Inject
    pv a;
    @Inject
    agt b;
    @Inject
    a c;
    @Inject
    wr d;
    @Inject
    bt e;
    @Inject
    gm.a f;

    @Inject
    lz() {
    }

    public final void a() {
        try {
            long j = this.a.g;
            if (j > 0) {
                Logger.v(Logger.DATA_TAG, "app fingerprinting allowed by server");
                if (j + this.a.f < System.currentTimeMillis()) {
                    this.e.a(new Runnable(this) {
                        final /* synthetic */ lz a;

                        {
                            this.a = r1;
                        }

                        public final void run() {
                            try {
                                Logger.d(Logger.DATA_TAG, "creating and sending app fingerprint");
                                wr wrVar = this.a.d;
                                a aVar = this.a.c;
                                List<PackageInfo> installedPackages = aVar.c.getPackageManager().getInstalledPackages(0);
                                JSONObject jSONObject = new JSONObject();
                                for (PackageInfo packageInfo : installedPackages) {
                                    if (packageInfo != null) {
                                        jSONObject.put(packageInfo.packageName, true);
                                    }
                                }
                                ly lyVar = (ly) aVar.d.get();
                                lyVar.a = jSONObject;
                                lyVar.b = System.currentTimeMillis();
                                lyVar.c = aVar.a.a();
                                lyVar.d = aVar.a.c();
                                lyVar.e = aVar.a.i();
                                wrVar.a(lyVar);
                            } catch (Throwable e) {
                                this.a.f.a(Logger.DATA_TAG, "exception while creating/ sending app fingerprint", e);
                            }
                        }
                    }, b.appFingerprint);
                    return;
                } else {
                    Logger.d(Logger.DATA_TAG, "throttled fingerprint request");
                    return;
                }
            }
            Logger.v(Logger.DATA_TAG, "app fingerprinting not allowed by server");
        } catch (Throwable e) {
            this.f.a(Logger.DATA_TAG, "exception while throttling app fingerprint", e);
        }
    }
}
