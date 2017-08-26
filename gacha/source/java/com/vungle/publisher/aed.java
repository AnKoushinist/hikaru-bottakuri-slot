package com.vungle.publisher;

import com.tapjoy.TJAdUnitConstants.String;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.json.JSONObject;

/* compiled from: vungle */
public abstract class aed extends ade {
    protected Integer n;
    protected Integer o;
    protected String p;
    protected a q;
    protected String r;
    protected String s;
    protected Integer t;
    protected Integer u;
    protected Integer v;
    protected Integer w;
    protected String x;
    protected Integer y;

    /* compiled from: vungle */
    public static abstract class b<R extends aed, T extends com.vungle.publisher.aeo.a<?>> extends com.vungle.publisher.ade.a<R, T> {
        @Inject
        protected a d;

        protected abstract T a();

        public /* synthetic */ ade a(JSONObject jSONObject) {
            return d(jSONObject);
        }

        public /* synthetic */ Object c(JSONObject jSONObject) {
            return d(jSONObject);
        }

        protected b() {
        }

        public R d(JSONObject jSONObject) {
            aed com_vungle_publisher_aed = (aed) super.a(jSONObject);
            if (com_vungle_publisher_aed != null) {
                com_vungle_publisher_aed.o = se.c(jSONObject, "asyncThreshold");
                com_vungle_publisher_aed.n = se.c(jSONObject, "retryCount");
                com_vungle_publisher_aed.p = se.e(jSONObject, "callToActionDest");
                com_vungle_publisher_aed.q = a.a(jSONObject.optJSONObject("cta_overlay"));
                com_vungle_publisher_aed.r = se.e(jSONObject, "callToActionUrl");
                com_vungle_publisher_aed.s = se.e(jSONObject, "chk");
                com_vungle_publisher_aed.t = se.c(jSONObject, "showCloseIncentivized");
                com_vungle_publisher_aed.u = se.c(jSONObject, "showClose");
                com_vungle_publisher_aed.v = se.c(jSONObject, "countdown");
                com_vungle_publisher_aed.w = se.c(jSONObject, String.VIDEO_HEIGHT);
                abv.a(jSONObject, String.VIDEO_HEIGHT, com_vungle_publisher_aed.w);
                com_vungle_publisher_aed.x = se.e(jSONObject, String.URL);
                abv.a(jSONObject, String.URL, com_vungle_publisher_aed.x);
                com_vungle_publisher_aed.y = se.c(jSONObject, String.VIDEO_WIDTH);
                abv.a(jSONObject, String.VIDEO_WIDTH, com_vungle_publisher_aed.y);
                com_vungle_publisher_aed.d = a().b(jSONObject.optJSONObject("tpat"));
            }
            return com_vungle_publisher_aed;
        }
    }

    /* compiled from: vungle */
    public static class a extends abe {
        protected Float a;
        protected Integer b;
        protected Boolean c;
        protected Boolean d;
        protected Integer e;

        @Singleton
        /* compiled from: vungle */
        public static class a extends abv<a> {
            protected final /* synthetic */ Object b() {
                return new a();
            }

            protected final /* synthetic */ Object c(JSONObject jSONObject) {
                return a(jSONObject);
            }

            @Inject
            a() {
            }

            protected static a a(JSONObject jSONObject) {
                if (jSONObject == null) {
                    return null;
                }
                a aVar = new a();
                aVar.a = se.b(jSONObject, "click_area");
                aVar.c = se.a(jSONObject, String.ENABLED);
                aVar.d = se.a(jSONObject, "show_onclick");
                aVar.e = se.c(jSONObject, "time_show");
                aVar.b = se.c(jSONObject, "time_enabled");
                return aVar;
            }
        }

        public final /* synthetic */ Object b() {
            return a();
        }

        protected a() {
        }

        public final Float c() {
            return this.a;
        }

        public final Boolean e() {
            return this.c;
        }

        public final Boolean f() {
            return this.d;
        }

        public final Integer g() {
            return this.e;
        }

        public final Integer h() {
            return this.b;
        }

        public final JSONObject a() {
            JSONObject a = super.a();
            a.putOpt("click_area", this.a);
            a.putOpt(String.ENABLED, this.c);
            a.putOpt("show_onclick", this.d);
            a.putOpt("time_show", this.e);
            a.putOpt("time_enabled", this.b);
            return a;
        }
    }

    protected aed(agt com_vungle_publisher_agt) {
        super(com_vungle_publisher_agt);
    }

    public final String c() {
        return this.p;
    }

    public final a d() {
        return this.q;
    }

    public final String e() {
        return this.r;
    }

    public final Integer f() {
        return this.t;
    }

    public final Integer g() {
        return this.u;
    }

    public final Integer h() {
        return this.v;
    }

    public final Integer i() {
        return this.w;
    }

    public final String j() {
        return this.x;
    }

    public final Integer k() {
        return this.y;
    }
}
