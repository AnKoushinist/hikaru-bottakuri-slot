package com.vungle.publisher;

import javax.inject.Inject;
import javax.inject.Singleton;
import org.json.JSONObject;

/* compiled from: vungle */
public final class adj extends abe {
    public Boolean a;
    public Integer b;
    public Integer c;
    public b d;
    public Boolean e;
    public Long f;
    public Long g;

    @Singleton
    /* compiled from: vungle */
    public static class a extends abv<adj> {
        protected final /* bridge */ /* synthetic */ Object[] a(int i) {
            return new adj[i];
        }

        protected final /* synthetic */ Object b() {
            return new adj();
        }

        protected final /* synthetic */ Object c(JSONObject jSONObject) {
            Object obj = null;
            if (jSONObject == null) {
                return null;
            }
            adj com_vungle_publisher_adj = new adj();
            com_vungle_publisher_adj.a = se.a(jSONObject, "optIn");
            com_vungle_publisher_adj.b = se.c(jSONObject, "updateDelay");
            com_vungle_publisher_adj.c = se.c(jSONObject, "threshold");
            Class cls = b.class;
            String e = se.e(jSONObject, "connection");
            if (e != null) {
                obj = Enum.valueOf(cls, e);
            }
            com_vungle_publisher_adj.d = (b) obj;
            com_vungle_publisher_adj.e = se.a(jSONObject, "exceptionReportingEnabled");
            com_vungle_publisher_adj.f = se.d(jSONObject, "last_app_fingerprint_timestamp");
            com_vungle_publisher_adj.g = se.d(jSONObject, "app_fingerprint_frequency");
            return com_vungle_publisher_adj;
        }

        @Inject
        a() {
        }
    }

    /* compiled from: vungle */
    public enum b {
        all,
        wifi
    }
}
