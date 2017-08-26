package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdType;
import java.util.Map;

class j extends ca {
    final /* synthetic */ AppLovinAdServiceImpl a;
    private final AppLovinAdSize b;

    public j(AppLovinAdServiceImpl appLovinAdServiceImpl, AppLovinAdSize appLovinAdSize) {
        this.a = appLovinAdServiceImpl;
        super("UpdateAdTask", appLovinAdServiceImpl.d);
        this.b = appLovinAdSize;
    }

    public void run() {
        Object obj = 1;
        i iVar = (i) ((Map) this.a.g.get(AppLovinAdType.a)).get(this.b);
        synchronized (iVar.b) {
            boolean a = this.a.a(this.b);
            boolean e = this.a.a();
            Object obj2 = !iVar.f.isEmpty() ? 1 : null;
            if (System.currentTimeMillis() <= iVar.d) {
                obj = null;
            }
            if (!(!a || obj2 == null || r1 == null || !e || iVar.e)) {
                iVar.e = true;
                this.a.b(this.b, AppLovinAdType.a, new h(this.a, iVar, null));
            }
        }
    }
}
