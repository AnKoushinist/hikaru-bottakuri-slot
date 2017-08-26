package com.vungle.publisher;

import javax.inject.Inject;
import javax.inject.Singleton;
import org.json.JSONObject;

/* compiled from: vungle */
public final class ada extends ace {
    String t;

    @Singleton
    /* compiled from: vungle */
    public static class a extends com.vungle.publisher.ace.a<ki, ada, kn, js> {
        @Inject
        com.vungle.publisher.aew.a g;

        public final /* synthetic */ ace a(cq cqVar) {
            kn knVar = (kn) cqVar;
            ada com_vungle_publisher_ada = (ada) super.a(knVar);
            if (com_vungle_publisher_ada != null) {
                com_vungle_publisher_ada.h = Integer.valueOf(knVar.v.a());
                com_vungle_publisher_ada.t = knVar.w;
            }
            return com_vungle_publisher_ada;
        }

        protected final /* synthetic */ Object b() {
            return new ada();
        }

        @Inject
        a() {
        }

        protected final com.vungle.publisher.acb.a<ki, ?, ?> c() {
            return this.g;
        }
    }

    public final /* synthetic */ Object b() {
        return a();
    }

    public final JSONObject a() {
        JSONObject a = super.a();
        a.putOpt("templateId", this.t);
        return a;
    }
}
