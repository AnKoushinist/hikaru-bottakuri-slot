package com.vungle.publisher;

import com.vungle.log.Logger;
import com.vungle.publisher.aea.a;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.json.JSONObject;

@Singleton
/* compiled from: vungle */
public final class zw extends wv {
    @Inject
    ql a;
    @Inject
    a b;
    @Inject
    adu.a i;
    @Inject
    adf.a j;

    @Inject
    zw() {
    }

    protected final void a(we weVar, vy vyVar) {
        adf com_vungle_publisher_adf = (adf) this.j.a(a(vyVar.a));
        j jVar = com_vungle_publisher_adf.e;
        final JSONObject jSONObject = com_vungle_publisher_adf.l;
        if (jVar == null) {
            Logger.w(Logger.NETWORK_TAG, "received ad with null ad type");
            d(weVar, vyVar);
            return;
        }
        final xk xkVar = weVar.c;
        final we weVar2 = weVar;
        final vy vyVar2 = vyVar;
        new o<Void>(this) {
            final /* synthetic */ zw e;

            protected final /* synthetic */ Object a() {
                this.e.c.b(Logger.NETWORK_TAG, "received local ad, expected streaming ad from server, ignoring", new RuntimeException());
                this.e.d(weVar2, vyVar2);
                return null;
            }

            protected final /* synthetic */ Object b() {
                return e();
            }

            protected final /* synthetic */ Object c() {
                this.e.c.b(Logger.NETWORK_TAG, "received vungle mraid ad, expected streaming ad from server, ignoring", new RuntimeException());
                this.e.d(weVar2, vyVar2);
                return null;
            }

            protected final /* synthetic */ Object d() {
                return f();
            }

            private Void e() {
                try {
                    this.e.a.a(new ap(this.e.b.b(jSONObject), xkVar));
                } catch (Throwable e) {
                    this.e.c.b(Logger.NETWORK_TAG, "error parsing json response from RequestStreamingAd for vungle streaming ad", e);
                }
                return null;
            }

            private Void f() {
                try {
                    this.e.a.a(new ao(this.e.i.b(jSONObject), xkVar));
                } catch (Throwable e) {
                    this.e.c.b(Logger.NETWORK_TAG, "error parsing json response from RequestStreamingAd for third party mraid ad", e);
                }
                return null;
            }
        }.a(jVar);
    }

    protected final void d(we weVar, vy vyVar) {
        this.a.a(new af());
    }
}
