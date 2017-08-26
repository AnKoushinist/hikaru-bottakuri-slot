package com.vungle.publisher;

import javax.inject.Inject;
import javax.inject.Singleton;
import org.json.JSONObject;

/* compiled from: vungle */
public final class adx extends add<adx> {
    abt g;
    String h;
    String i;

    @Singleton
    /* compiled from: vungle */
    public static class a extends com.vungle.publisher.add.a<adx> {
        @Inject
        protected a f;

        protected final /* bridge */ /* synthetic */ Object[] a(int i) {
            return new adx[i];
        }

        protected final /* synthetic */ Object b() {
            return new adx();
        }

        @Inject
        a() {
        }

        public final adx a(String str, p pVar) {
            adx com_vungle_publisher_adx = (adx) super.c();
            String placement = pVar.getPlacement();
            if (ags.a(placement)) {
                com_vungle_publisher_adx.i = placement;
            }
            com_vungle_publisher_adx.g = a.a(pVar.getExtras());
            com_vungle_publisher_adx.h = str;
            return com_vungle_publisher_adx;
        }
    }

    public final /* synthetic */ Object b() {
        return a();
    }

    public final JSONObject a() {
        JSONObject a = super.a();
        a.putOpt("campaignId", this.h);
        a.putOpt("extraInfo", se.a(this.g));
        a.putOpt("placement", this.i);
        return a;
    }
}
