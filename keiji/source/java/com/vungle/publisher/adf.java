package com.vungle.publisher;

import javax.inject.Inject;
import javax.inject.Singleton;
import org.json.JSONObject;

/* compiled from: vungle */
public final class adf extends ade {
    public boolean k;
    public JSONObject l;

    @Singleton
    /* compiled from: vungle */
    public static class a extends com.vungle.publisher.ade.a<adf, com.vungle.publisher.aeo.a<?>> {
        @Inject
        agt b;
        @Inject
        com.vungle.publisher.j.a c;

        public final /* synthetic */ ade a(JSONObject jSONObject) {
            return b(jSONObject);
        }

        protected final /* synthetic */ Object b() {
            return new adf(this.b);
        }

        public final /* synthetic */ Object c(JSONObject jSONObject) {
            return b(jSONObject);
        }

        @Inject
        a() {
        }

        private adf b(JSONObject jSONObject) {
            adf com_vungle_publisher_adf = (adf) super.a(jSONObject);
            com_vungle_publisher_adf.l = jSONObject;
            com_vungle_publisher_adf.k = com_vungle_publisher_adf.b() == null;
            return com_vungle_publisher_adf;
        }
    }

    protected adf(agt com_vungle_publisher_agt) {
        super(com_vungle_publisher_agt);
    }
}
