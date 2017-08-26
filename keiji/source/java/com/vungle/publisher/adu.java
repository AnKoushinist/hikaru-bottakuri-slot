package com.vungle.publisher;

import javax.inject.Inject;
import javax.inject.Singleton;
import org.json.JSONObject;

/* compiled from: vungle */
public final class adu extends ade {
    public String k;

    @Singleton
    /* compiled from: vungle */
    public static class a extends com.vungle.publisher.ade.a<adu, com.vungle.publisher.abp.a> {
        @Inject
        agt b;
        @Inject
        com.vungle.publisher.gm.a c;

        public final /* synthetic */ ade a(JSONObject jSONObject) {
            return b(jSONObject);
        }

        protected final /* synthetic */ Object b() {
            return new adu(this.b);
        }

        public final /* synthetic */ Object c(JSONObject jSONObject) {
            return b(jSONObject);
        }

        @Inject
        a() {
        }

        public final adu b(JSONObject jSONObject) {
            adu com_vungle_publisher_adu = (adu) super.a(jSONObject);
            if (com_vungle_publisher_adu != null) {
                com_vungle_publisher_adu.k = se.e(jSONObject, "mraidContent");
            }
            return com_vungle_publisher_adu;
        }
    }

    protected adu(agt com_vungle_publisher_agt) {
        super(com_vungle_publisher_agt);
    }
}
