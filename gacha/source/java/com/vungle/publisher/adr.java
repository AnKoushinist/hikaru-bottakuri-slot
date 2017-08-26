package com.vungle.publisher;

import com.vungle.publisher.aed.b;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.json.JSONObject;

/* compiled from: vungle */
public final class adr extends aed {
    public String k;
    public Integer l;
    public String m;

    @Singleton
    /* compiled from: vungle */
    public static class a extends b<adr, com.vungle.publisher.aep.a> {
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
            return new adr(this.b);
        }

        public final /* synthetic */ Object c(JSONObject jSONObject) {
            return b(jSONObject);
        }

        public final /* synthetic */ aed d(JSONObject jSONObject) {
            return b(jSONObject);
        }

        public final adr b(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            adr com_vungle_publisher_adr = (adr) super.d(jSONObject);
            com_vungle_publisher_adr.k = se.e(jSONObject, "postBundle");
            com_vungle_publisher_adr.l = se.c(jSONObject, "size");
            com_vungle_publisher_adr.m = jSONObject.optString("md5");
            abv.a(jSONObject, "md5", com_vungle_publisher_adr.m);
            return com_vungle_publisher_adr;
        }
    }

    protected adr(agt com_vungle_publisher_agt) {
        super(com_vungle_publisher_agt);
    }
}
