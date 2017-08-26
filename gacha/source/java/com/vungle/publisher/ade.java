package com.vungle.publisher;

import com.tapjoy.TapjoyConstants;
import javax.inject.Inject;
import org.json.JSONObject;

/* compiled from: vungle */
public abstract class ade {
    String a;
    Integer b;
    public Long c;
    public aeo d;
    public j e;
    public String f;
    public Integer g;
    public String h;
    protected String i;
    agt j;

    /* compiled from: vungle */
    public static abstract class a<R extends ade, T extends com.vungle.publisher.aeo.a<?>> extends abv<R> {
        @Inject
        protected com.vungle.publisher.j.a a;

        public /* synthetic */ Object c(JSONObject jSONObject) {
            return a(jSONObject);
        }

        public R a(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            ade com_vungle_publisher_ade = (ade) b();
            com_vungle_publisher_ade.h = se.e(jSONObject, TapjoyConstants.TJC_APP_ID);
            com_vungle_publisher_ade.g = se.c(jSONObject, "delay");
            com_vungle_publisher_ade.i = se.e(jSONObject, "id");
            com_vungle_publisher_ade.e = this.a.a(se.e(jSONObject, FullScreenAdActivity.AD_TYPE_EXTRA_KEY));
            com_vungle_publisher_ade.f = se.e(jSONObject, "campaign");
            abv.a(jSONObject, "campaign", com_vungle_publisher_ade.f);
            Long d = se.d(jSONObject, "expiry");
            com_vungle_publisher_ade.c = d;
            abv.a(jSONObject, "expiry", d);
            com_vungle_publisher_ade.b = se.c(jSONObject, "sleep");
            com_vungle_publisher_ade.a = se.e(jSONObject, "sleepCode");
            return com_vungle_publisher_ade;
        }
    }

    public ade(agt com_vungle_publisher_agt) {
        this.j = com_vungle_publisher_agt;
    }

    public final String a() {
        return this.i;
    }

    public final Long b() {
        long j = Long.MAX_VALUE;
        if (this.b == null) {
            return null;
        }
        long intValue = (long) this.b.intValue();
        if (intValue < 0 || 1000 < 0 || Long.MAX_VALUE < 0) {
            throw new IllegalArgumentException("inputs must be positive");
        }
        if (intValue == 0 || 1000 == 0) {
            j = 0;
        } else if (1000 < Long.MAX_VALUE / intValue) {
            j = intValue * 1000;
        }
        return Long.valueOf(j);
    }
}
