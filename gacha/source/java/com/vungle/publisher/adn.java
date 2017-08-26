package com.vungle.publisher;

import com.tapjoy.TJAdUnitConstants.String;
import com.tapjoy.TapjoyConstants;
import com.vungle.log.Logger;
import dagger.Lazy;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.json.JSONObject;

/* compiled from: vungle */
public final class adn extends add<adn> {
    List<ee> g;
    b[] h;

    @Singleton
    /* compiled from: vungle */
    public static class a extends com.vungle.publisher.add.a<adn> {
        @Inject
        com.vungle.publisher.ee.a f;
        @Inject
        a g;
        @Inject
        Lazy<py> h;

        public final /* synthetic */ abg a() {
            return d();
        }

        protected final /* bridge */ /* synthetic */ Object[] a(int i) {
            return new adn[i];
        }

        protected final /* synthetic */ Object b() {
            return new adn();
        }

        public final /* synthetic */ add c() {
            return d();
        }

        @Inject
        a() {
        }

        public final adn d() {
            b[] bVarArr = null;
            adn com_vungle_publisher_adn = (adn) super.c();
            List<ee> d = this.f.d();
            com_vungle_publisher_adn.g = d;
            int size = d == null ? 0 : d.size();
            if (size > 0) {
                Logger.d(Logger.REPORT_TAG, "sending " + size + " event_tracking_http_log records");
                b[] bVarArr2 = new b[size];
                size = 0;
                for (ee eeVar : d) {
                    b bVar;
                    Logger.v(Logger.REPORT_TAG, "sending " + eeVar.z());
                    int i = size + 1;
                    if (eeVar != null) {
                        b bVar2 = new b();
                        bVar2.a = eeVar.a;
                        bVar2.b = eeVar.b;
                        bVar2.c = Long.valueOf(eeVar.d);
                        bVar2.d = String.valueOf(eeVar.c);
                        bVar2.e = eeVar.e;
                        bVar2.f = eeVar.f;
                        bVar2.g = eeVar.g;
                        bVar = bVar2;
                    } else {
                        bVar = null;
                    }
                    bVarArr2[size] = bVar;
                    size = i;
                }
                bVarArr = bVarArr2;
            }
            com_vungle_publisher_adn.h = bVarArr;
            return com_vungle_publisher_adn;
        }
    }

    /* compiled from: vungle */
    static class b extends abe {
        String a;
        String b;
        Long c;
        String d;
        Integer e;
        Long f;
        String g;

        @Singleton
        /* compiled from: vungle */
        static class a extends abw<b> {
            protected final /* bridge */ /* synthetic */ Object[] a(int i) {
                return new b[i];
            }

            protected final /* synthetic */ Object b() {
                return new b();
            }

            @Inject
            a() {
            }
        }

        b() {
        }

        public final /* synthetic */ Object b() {
            return a();
        }

        public final JSONObject a() {
            JSONObject a = super.a();
            a.putOpt("campaignId", this.a);
            a.putOpt("deliveryId", this.b);
            a.putOpt("deviceMillis", this.c);
            a.putOpt(TapjoyConstants.TJC_SDK_TYPE_DEFAULT, this.d);
            a.putOpt("responseCode", this.e);
            a.putOpt("responseMillis", this.f);
            a.putOpt(String.URL, this.g);
            return a;
        }
    }

    public final /* synthetic */ Object b() {
        return a();
    }

    adn() {
    }

    public final JSONObject a() {
        JSONObject a = super.a();
        a.putOpt("httpLog", se.a(this.h));
        return a;
    }
}
