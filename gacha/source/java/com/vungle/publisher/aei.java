package com.vungle.publisher;

import javax.inject.Inject;
import javax.inject.Singleton;
import org.json.JSONObject;

/* compiled from: vungle */
public final class aei extends abe {
    public Long a;
    public ael b;

    @Singleton
    /* compiled from: vungle */
    public static class a {
        @Inject
        public com.vungle.publisher.ael.a a;

        @Inject
        a() {
        }
    }

    public final /* synthetic */ Object b() {
        return a();
    }

    public final JSONObject a() {
        JSONObject a = this.b == null ? super.a() : this.b.a();
        abf.a("end", this.a);
        a.put("end", this.a);
        return a;
    }
}
