package com.vungle.publisher;

import com.tapjoy.TJAdUnitConstants.String;
import com.vungle.log.Logger;
import org.json.JSONObject;

/* compiled from: vungle */
public final class aes extends acb {
    protected Integer d;

    /* compiled from: vungle */
    public static abstract class a<P extends cp<?, P, E>, E extends cr<P>> extends com.vungle.publisher.acb.a<P, E, aes> {
        protected final /* synthetic */ acb a(cp cpVar) {
            return b(cpVar);
        }

        protected final /* synthetic */ Object b() {
            return new aes();
        }

        protected a() {
        }

        private aes b(P p) {
            aes com_vungle_publisher_aes = (aes) super.a((cp) p);
            if (com_vungle_publisher_aes != null) {
                try {
                    com_vungle_publisher_aes.d = p.a.p();
                } catch (NullPointerException e) {
                    Logger.w(Logger.PROTOCOL_TAG, "null play report parent");
                }
                com_vungle_publisher_aes.a = p.b;
            }
            return com_vungle_publisher_aes;
        }
    }

    public final /* synthetic */ Object b() {
        return a();
    }

    aes() {
    }

    public final JSONObject a() {
        JSONObject a = super.a();
        a.putOpt(String.VIDEO_LENGTH, this.d);
        a.putOpt("videoViewed", this.a);
        return a;
    }
}
