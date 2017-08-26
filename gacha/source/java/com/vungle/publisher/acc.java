package com.vungle.publisher;

import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.json.JSONObject;

/* compiled from: vungle */
public final class acc extends abe {
    public Float a;
    public List<String> b;

    @Singleton
    /* compiled from: vungle */
    public static class a extends abv<acc> {
        protected final /* bridge */ /* synthetic */ Object[] a(int i) {
            return new acc[i];
        }

        protected final /* synthetic */ Object b() {
            return new acc();
        }

        protected final /* synthetic */ Object c(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            acc com_vungle_publisher_acc = new acc();
            com_vungle_publisher_acc.a = se.b(jSONObject, "checkpoint");
            abv.a(jSONObject, "checkpoint", com_vungle_publisher_acc.a);
            com_vungle_publisher_acc.b = se.f(jSONObject, "urls");
            abv.a(jSONObject, "urls", com_vungle_publisher_acc.b);
            return com_vungle_publisher_acc;
        }

        @Inject
        protected a() {
        }
    }

    public final /* synthetic */ Object b() {
        return a();
    }

    protected acc() {
    }

    public final JSONObject a() {
        JSONObject a = super.a();
        a.putOpt("checkpoint", this.a);
        a.putOpt("urls", this.b);
        return a;
    }
}
