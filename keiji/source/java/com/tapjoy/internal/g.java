package com.tapjoy.internal;

import com.moat.analytics.mobile.tjy.MoatAdEvent;
import com.tapjoy.TJAdUnitConstants.String;

public final class g {
    public String a;
    public String b;
    public String c;
    public String d;
    public String e;
    public String f;
    public long g;

    public g(String str) {
        bs b = bs.b(str);
        b.h();
        while (b.j()) {
            String l = b.l();
            if ("productId".equals(l)) {
                this.a = b.m();
            } else if (MoatAdEvent.EVENT_TYPE.equals(l)) {
                this.b = b.m();
            } else if ("price".equals(l)) {
                this.c = b.m();
            } else if (String.TITLE.equals(l)) {
                this.d = b.m();
            } else if ("description".equals(l)) {
                this.e = b.m();
            } else if ("price_currency_code".equals(l)) {
                this.f = b.m();
            } else if ("price_amount_micros".equals(l)) {
                this.g = b.q();
            } else {
                b.s();
            }
        }
        b.i();
    }
}
