package com.vungle.publisher;

import com.tapjoy.TJAdUnitConstants.String;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.json.JSONObject;

/* compiled from: vungle */
public final class ael extends abe {
    String a;
    String b;
    String c;
    String d;
    Long e;

    @Singleton
    /* compiled from: vungle */
    public static class a {
        @Inject
        pn a;
        @Inject
        pu b;

        @Inject
        a() {
        }

        public final ael a(long j) {
            ael com_vungle_publisher_ael = new ael();
            com_vungle_publisher_ael.a = this.a.a();
            com_vungle_publisher_ael.b = this.a.c();
            com_vungle_publisher_ael.c = this.a.j();
            com_vungle_publisher_ael.d = this.b.b();
            com_vungle_publisher_ael.e = Long.valueOf(j);
            return com_vungle_publisher_ael;
        }
    }

    public final /* synthetic */ Object b() {
        return a();
    }

    ael() {
    }

    public final JSONObject a() {
        JSONObject a = super.a();
        a.putOpt("ifa", this.a);
        a.putOpt("isu", this.b);
        a.putOpt("mac", this.c);
        abf.a("pubAppId", this.d);
        a.put("pubAppId", this.d);
        abf.a(String.VIDEO_START, this.e);
        a.put(String.VIDEO_START, this.e);
        return a;
    }
}
