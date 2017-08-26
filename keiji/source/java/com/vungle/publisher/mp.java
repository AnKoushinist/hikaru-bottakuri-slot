package com.vungle.publisher;

import javax.inject.Singleton;
import org.json.JSONObject;

@Singleton
/* compiled from: vungle */
public final class mp extends abe {
    public final /* synthetic */ Object b() {
        return a();
    }

    public final JSONObject a() {
        JSONObject a = super.a();
        a.put("privacyPolicyEnabled", true);
        return a;
    }
}
