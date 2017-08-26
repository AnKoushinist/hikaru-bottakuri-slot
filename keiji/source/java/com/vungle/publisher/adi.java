package com.vungle.publisher;

import javax.inject.Inject;
import javax.inject.Singleton;
import org.json.JSONObject;

@Singleton
/* compiled from: vungle */
public final class adi extends abe {
    @Inject
    pn a;
    @Inject
    pu b;

    public final /* synthetic */ Object b() {
        return a();
    }

    @Inject
    adi() {
    }

    public final JSONObject a() {
        JSONObject a = super.a();
        a.putOpt("pubAppId", this.b.b());
        a.putOpt("ifa", this.a.a());
        a.putOpt("isu", this.a.c());
        return a;
    }
}
