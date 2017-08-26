package com.tapjoy.internal;

import com.applovin.sdk.AppLovinEventParameters;
import com.tapjoy.TJAdUnitConstants;
import com.tapjoy.TJAdUnitConstants.String;
import com.tapjoy.TapjoyConstants;
import com.tapjoy.internal.gd.a;
import com.tapjoy.internal.gd.c;
import com.tapjoy.internal.gd.d;
import com.tapjoy.internal.gd.f;
import com.tapjoy.internal.gd.i;
import com.tapjoy.internal.gd.j;
import com.tapjoy.internal.gd.l;
import com.tapjoy.internal.gd.p;
import com.tapjoy.internal.gd.r;
import com.tapjoy.internal.gd.t;
import com.tapjoy.internal.gd.z;
import com.unity3d.ads.metadata.MediationMetaData;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import twitter4j.TwitterResponse;

public final class gc {

    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[i.values().length];

        static {
            try {
                a[i.APP.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[i.CAMPAIGN.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[i.CUSTOM.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    public static String a(l lVar) {
        bl b = new bl().c().a("sdk").b(lVar.C()).a("os_name").b(lVar.n()).a("os_ver").b(lVar.p()).a("device_id").b(lVar.h()).a("device_maker").b(lVar.j()).a("device_model").b(lVar.l()).a(TapjoyConstants.TJC_PACKAGE_ID).b(lVar.y()).a(TapjoyConstants.TJC_PACKAGE_SIGN).b(lVar.A()).a(TapjoyConstants.TJC_DEVICE_DISPLAY_DENSITY).a((long) lVar.c).a(TapjoyConstants.TJC_DEVICE_DISPLAY_WIDTH).a((long) lVar.d).a(TapjoyConstants.TJC_DEVICE_DISPLAY_HEIGHT).a((long) lVar.e).a("locale").b(lVar.u()).a(TapjoyConstants.TJC_DEVICE_TIMEZONE).b(lVar.w());
        if (lVar.e()) {
            b.a("mac").b(lVar.f());
        }
        if (lVar.D()) {
            b.a(TapjoyConstants.TJC_DEVICE_COUNTRY_SIM).b(lVar.E());
        }
        if (lVar.F()) {
            b.a("country_net").b(lVar.G());
        }
        if (lVar.H()) {
            b.a("imei").b(lVar.I());
        }
        return b.d().toString();
    }

    public static String a(a aVar) {
        bl c = new bl().c();
        if (aVar.e()) {
            c.a(TapjoyConstants.TJC_PACKAGE_VERSION).b(aVar.f());
        }
        if (aVar.g()) {
            c.a(TapjoyConstants.TJC_PACKAGE_REVISION).a((long) aVar.c);
        }
        if (aVar.h()) {
            c.a("data_ver").b(aVar.i());
        }
        if (aVar.j()) {
            c.a(TapjoyConstants.TJC_INSTALLER).b(aVar.k());
        }
        if (aVar.l()) {
            c.a(TapjoyConstants.TJC_STORE).b(aVar.m());
        }
        return c.d().toString();
    }

    public static String a(z zVar) {
        return a(zVar, null);
    }

    private static String a(z zVar, c cVar) {
        bl c = new bl().c();
        if (zVar.e()) {
            c.a(TapjoyConstants.TJC_INSTALLED).a(zVar.c);
        }
        if (zVar.f()) {
            c.a(TapjoyConstants.TJC_REFERRER).b(zVar.g());
        }
        if (zVar.u()) {
            c.a(Constants.PREFKEY_GAID).b(zVar.v());
            if (zVar.w() && zVar.o) {
                c.a("idfa_optout").a(1);
            }
        } else if (cVar != null && cVar.j() && fp.a.equals(cVar.k())) {
            String b = ga.b();
            if (b != null) {
                c.a(Constants.PREFKEY_GAID).b(b);
                if (ga.c()) {
                    c.a("idfa_optout").a(1);
                }
            }
        }
        if (zVar.h()) {
            c.a(TapjoyConstants.TJC_USER_WEEKLY_FREQUENCY).a((long) Math.max(zVar.d, 1));
        }
        if (zVar.i()) {
            c.a(TapjoyConstants.TJC_USER_MONTHLY_FREQUENCY).a((long) Math.max(zVar.e, 1));
        }
        if (zVar.j() > 0) {
            ArrayList arrayList = new ArrayList(zVar.j());
            for (t tVar : zVar.f) {
                if (tVar.h()) {
                    arrayList.add(tVar.f());
                }
            }
            if (!arrayList.isEmpty()) {
                c.a("push").a();
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    c.b((String) it.next());
                }
                c.b();
            }
        }
        c.a("session").c();
        if (zVar.k()) {
            c.a("total_count").a((long) zVar.g);
        }
        if (zVar.l()) {
            c.a("total_length").a(zVar.h);
        }
        if (zVar.m()) {
            c.a("last_at").a(zVar.i);
        }
        if (zVar.n()) {
            c.a("last_length").a(zVar.j);
        }
        c.d();
        c.a("purchase").c();
        if (zVar.o()) {
            c.a(AppLovinEventParameters.REVENUE_CURRENCY).b(zVar.p());
        }
        if (zVar.q()) {
            c.a("total_count").a((long) zVar.k);
        }
        if (zVar.r()) {
            c.a("total_price").a(zVar.l);
        }
        if (zVar.s()) {
            c.a("last_at").a(zVar.m);
        }
        if (zVar.t()) {
            c.a("last_price").a(zVar.n);
        }
        c.d();
        if (zVar.x()) {
            c.a("user_id").b(zVar.y());
        }
        if (zVar.z()) {
            c.a(TapjoyConstants.TJC_USER_LEVEL).a((long) zVar.p);
        }
        if (zVar.A()) {
            c.a(TapjoyConstants.TJC_USER_FRIEND_COUNT).a((long) zVar.q);
        }
        if (zVar.B()) {
            c.a(TapjoyConstants.TJC_USER_VARIABLE_1).b(zVar.C());
        }
        if (zVar.D()) {
            c.a(TapjoyConstants.TJC_USER_VARIABLE_2).b(zVar.E());
        }
        if (zVar.F()) {
            c.a(TapjoyConstants.TJC_USER_VARIABLE_3).b(zVar.G());
        }
        if (zVar.H()) {
            c.a(TapjoyConstants.TJC_USER_VARIABLE_4).b(zVar.I());
        }
        if (zVar.J()) {
            c.a(TapjoyConstants.TJC_USER_VARIABLE_5).b(zVar.K());
        }
        if (zVar.L() > 0) {
            c.a("tags").a(zVar.r);
        }
        if (zVar.s) {
            c.a("push_optout").a(1);
        }
        return c.d().toString();
    }

    private static String a(c cVar, boolean z, boolean z2, boolean z3) {
        bl b;
        bl b2 = new bl().c().a("type").b(a(cVar.c)).a(MediationMetaData.KEY_NAME).b(cVar.g());
        b2.a("time");
        if (cVar.i()) {
            b2.a(cVar.d);
            b2.a("systime").a(cVar.e);
        } else if (y.c() && cVar.j() && cVar.l() && fp.a.equals(cVar.k())) {
            b2.a(y.a(cVar.f));
            b2.a("systime").a(cVar.d);
        } else {
            b2.a(cVar.d);
        }
        if (cVar.m()) {
            b2.a("duration").a(cVar.g);
        }
        if (!z && cVar.n()) {
            b2.a(String.VIDEO_INFO).a(new bq(a(cVar.h)));
        }
        if (!z2 && cVar.o()) {
            b2.a("app").a(new bq(a(cVar.i)));
        }
        if (!z3 && cVar.p()) {
            b2.a("user").a(new bq(a(cVar.j, cVar)));
        }
        if (cVar.r()) {
            b2.a("event_seq").a((long) cVar.l);
        }
        if (cVar.s()) {
            bl a = b2.a("event_prev");
            f fVar = cVar.m;
            b = new bl().c().a("type").b(a(fVar.c)).a(MediationMetaData.KEY_NAME).b(fVar.g());
            if (fVar.h()) {
                b.a("category").b(fVar.i());
            }
            a.a(new bq(b.d().toString()));
        }
        if (cVar.t()) {
            a = b2.a("purchase");
            r rVar = cVar.n;
            b = new bl().c().a("product_id").b(rVar.f());
            if (rVar.g()) {
                b.a("product_quantity").a((long) rVar.c);
            }
            if (rVar.h()) {
                b.a("product_price").a(rVar.d);
            }
            if (rVar.i()) {
                b.a("product_price_currency").b(rVar.j());
            }
            if (rVar.w()) {
                b.a("currency_price").b(rVar.x());
            }
            if (rVar.k()) {
                b.a("product_type").b(rVar.l());
            }
            if (rVar.m()) {
                b.a("product_title").b(rVar.n());
            }
            if (rVar.o()) {
                b.a("product_description").b(rVar.p());
            }
            if (rVar.q()) {
                b.a(AppLovinEventParameters.CHECKOUT_TRANSACTION_IDENTIFIER).b(rVar.r());
            }
            if (rVar.s()) {
                b.a("transaction_state").a((long) rVar.e);
            }
            if (rVar.t()) {
                b.a("transaction_date").a(rVar.f);
            }
            if (rVar.u()) {
                b.a("campaign_id").b(rVar.v());
            }
            if (rVar.y()) {
                b.a("receipt").b(rVar.z());
            }
            if (rVar.A()) {
                b.a("signature").b(rVar.B());
            }
            a.a(new bq(b.d().toString()));
        }
        if (cVar.u()) {
            b2.a("exception").b(cVar.v());
        }
        try {
            if (cVar.y()) {
                Map linkedHashMap = new LinkedHashMap();
                if (cVar.w()) {
                    br.b(cVar.x()).a(linkedHashMap);
                }
                p pVar = cVar.o;
                if (pVar.e()) {
                    linkedHashMap.put("fq7_change", pVar.f());
                }
                if (pVar.g()) {
                    linkedHashMap.put("fq30_change", pVar.h());
                }
                if (pVar.i()) {
                    linkedHashMap.put(TJAdUnitConstants.PARAM_PUSH_ID, pVar.j());
                }
                b2.a("meta").a(linkedHashMap);
            } else if (cVar.w()) {
                b2.a("meta").a(new bq(cVar.x()));
            }
        } catch (IOException e) {
        }
        if (cVar.z()) {
            b2.a("category").b(cVar.A());
        }
        if (cVar.B()) {
            b2.a("p1").b(cVar.C());
        }
        if (cVar.D()) {
            b2.a("p2").b(cVar.E());
        }
        if (cVar.F() > 0) {
            b2.a("values").c();
            for (j jVar : cVar.p) {
                b2.a(jVar.f()).a(jVar.c);
            }
            b2.d();
        }
        return b2.d().toString();
    }

    public static String a(d dVar) {
        z zVar = null;
        bl a = new bl().a();
        a aVar = null;
        l lVar = null;
        for (c cVar : dVar.c) {
            l lVar2;
            boolean z;
            a aVar2;
            boolean z2;
            z zVar2;
            boolean z3;
            if (lVar == null || (cVar.n() && !lVar.equals(cVar.h))) {
                lVar2 = cVar.h;
                z = false;
            } else {
                z = true;
                lVar2 = lVar;
            }
            if (aVar == null || (cVar.o() && !aVar.equals(cVar.i))) {
                aVar2 = cVar.i;
                z2 = false;
            } else {
                z2 = true;
                aVar2 = aVar;
            }
            if (zVar == null || (cVar.p() && !zVar.equals(cVar.j))) {
                zVar2 = cVar.j;
                z3 = false;
            } else {
                zVar2 = zVar;
                z3 = true;
            }
            a.a(new bq(a(cVar, z, z2, z3)));
            zVar = zVar2;
            aVar = aVar2;
            lVar = lVar2;
        }
        return a.b().toString();
    }

    private static String a(i iVar) {
        switch (AnonymousClass1.a[iVar.ordinal()]) {
            case TwitterResponse.READ /*1*/:
                return "app";
            case TwitterResponse.READ_WRITE /*2*/:
                return "campaign";
            case TwitterResponse.READ_WRITE_DIRECTMESSAGES /*3*/:
                return "custom";
            default:
                throw new RuntimeException();
        }
    }
}
