package com.vungle.publisher;

import javax.inject.Inject;
import javax.inject.Singleton;
import org.json.JSONObject;

/* compiled from: vungle */
public final class aef extends ade {
    public JSONObject k;
    public aeu l;
    public String m;
    public String n;

    @Singleton
    /* compiled from: vungle */
    public static class a extends com.vungle.publisher.ade.a<aef, com.vungle.publisher.abp.a> {
        @Inject
        agt b;
        @Inject
        com.vungle.publisher.afa.a c;
        @Inject
        com.vungle.publisher.aeu.a d;
        @Inject
        com.vungle.publisher.abp.a e;

        public final /* synthetic */ ade a(JSONObject jSONObject) {
            return b(jSONObject);
        }

        protected final /* synthetic */ Object b() {
            return new aef(this.b);
        }

        public final /* synthetic */ Object c(JSONObject jSONObject) {
            return b(jSONObject);
        }

        @Inject
        a() {
        }

        public final aef b(JSONObject jSONObject) {
            aef com_vungle_publisher_aef = (aef) super.a(jSONObject);
            if (com_vungle_publisher_aef != null) {
                JSONObject optJSONObject = jSONObject.optJSONObject("templateSettings");
                if (optJSONObject != null) {
                    com_vungle_publisher_aef.k = optJSONObject.optJSONObject("normal_replacements");
                    com_vungle_publisher_aef.l = com.vungle.publisher.aeu.a.a(optJSONObject.optJSONObject("cacheable_replacements"));
                }
                com_vungle_publisher_aef.n = se.e(jSONObject, "templateId");
                com_vungle_publisher_aef.m = se.e(jSONObject, "templateURL");
                com_vungle_publisher_aef.d = this.e.a(jSONObject.optJSONObject("tpat"));
            }
            return com_vungle_publisher_aef;
        }
    }

    protected aef(agt com_vungle_publisher_agt) {
        super(com_vungle_publisher_agt);
    }
}
