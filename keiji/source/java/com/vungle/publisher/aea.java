package com.vungle.publisher;

import com.vungle.publisher.aed.b;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.json.JSONObject;

/* compiled from: vungle */
public final class aea extends aed {
    public Boolean k;

    @Singleton
    /* compiled from: vungle */
    public static class a extends b<aea, com.vungle.publisher.aep.a> {
        @Inject
        agt b;
        @Inject
        com.vungle.publisher.aep.a c;

        public final /* synthetic */ ade a(JSONObject jSONObject) {
            return b(jSONObject);
        }

        protected final /* bridge */ /* synthetic */ com.vungle.publisher.aeo.a a() {
            return this.c;
        }

        protected final /* synthetic */ Object b() {
            return c();
        }

        public final /* synthetic */ Object c(JSONObject jSONObject) {
            return b(jSONObject);
        }

        public final /* synthetic */ aed d(JSONObject jSONObject) {
            return b(jSONObject);
        }

        @Inject
        a() {
        }

        public final aea b(JSONObject jSONObject) {
            aea com_vungle_publisher_aea = null;
            if (jSONObject != null) {
                Boolean a = se.a(jSONObject, "shouldStream");
                if (Boolean.TRUE.equals(a)) {
                    com_vungle_publisher_aea = (aea) super.d(jSONObject);
                } else {
                    com_vungle_publisher_aea = c();
                }
                com_vungle_publisher_aea.k = a;
            }
            return com_vungle_publisher_aea;
        }

        private aea c() {
            return new aea(this.b);
        }
    }

    protected aea(agt com_vungle_publisher_agt) {
        super(com_vungle_publisher_agt);
    }
}
