package com.tapjoy.internal;

import com.moat.analytics.mobile.tjy.MoatAdEvent;
import com.tapjoy.TJAdUnitConstants;
import com.tapjoy.TJAdUnitConstants.String;
import com.tapjoy.TapjoyConstants;
import com.unity3d.ads.metadata.MediationMetaData;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import jp.co.vaz.creator.hikaru2.R;
import twitter4j.TwitterResponse;

public final class gp {

    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[eb.values().length];

        static {
            try {
                a[eb.APP.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[eb.CAMPAIGN.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[eb.CUSTOM.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[eb.USAGES.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    public static String a(ed edVar) {
        bm b = new bm().c().a("sdk").b(edVar.t).a("os_name").b(edVar.k).a("os_ver").b(edVar.l).a("device_id").b(edVar.h).a("device_maker").b(edVar.i).a("device_model").b(edVar.j).a(TapjoyConstants.TJC_PACKAGE_ID).b(edVar.r).a(TapjoyConstants.TJC_PACKAGE_SIGN).b(edVar.s).a("locale").b(edVar.p).a(TapjoyConstants.TJC_DEVICE_TIMEZONE).b(edVar.q);
        if (edVar.m != null) {
            b.a(TapjoyConstants.TJC_DEVICE_DISPLAY_DENSITY).a(edVar.m);
        }
        if (edVar.n != null) {
            b.a(TapjoyConstants.TJC_DEVICE_DISPLAY_WIDTH).a(edVar.n);
        }
        if (edVar.o != null) {
            b.a(TapjoyConstants.TJC_DEVICE_DISPLAY_HEIGHT).a(edVar.o);
        }
        if (edVar.g != null) {
            b.a("mac").b(edVar.g);
        }
        if (edVar.u != null) {
            b.a(TapjoyConstants.TJC_DEVICE_COUNTRY_SIM).b(edVar.u);
        }
        if (edVar.v != null) {
            b.a("country_net").b(edVar.v);
        }
        if (edVar.w != null) {
            b.a("imei").b(edVar.w);
        }
        return b.d().toString();
    }

    public static String a(dx dxVar) {
        bm c = new bm().c();
        if (dxVar.e != null) {
            c.a(TapjoyConstants.TJC_PACKAGE_VERSION).b(dxVar.e);
        }
        if (dxVar.f != null) {
            c.a(TapjoyConstants.TJC_PACKAGE_REVISION).a(dxVar.f);
        }
        if (dxVar.g != null) {
            c.a("data_ver").b(dxVar.g);
        }
        if (dxVar.h != null) {
            c.a(TapjoyConstants.TJC_INSTALLER).b(dxVar.h);
        }
        if (dxVar.i != null) {
            c.a(TapjoyConstants.TJC_STORE).b(dxVar.i);
        }
        return c.d().toString();
    }

    public static String a(ek ekVar) {
        return a(ekVar, null);
    }

    private static String a(ek ekVar, dy dyVar) {
        bm c = new bm().c();
        if (ekVar.s != null) {
            c.a(TapjoyConstants.TJC_INSTALLED).a(ekVar.s);
        }
        if (ekVar.t != null) {
            c.a(TapjoyConstants.TJC_REFERRER).b(ekVar.t);
        }
        if (ekVar.G != null) {
            c.a("idfa").b(ekVar.G);
            if (ekVar.H != null && ekVar.H.booleanValue()) {
                c.a("idfa_optout").a(1);
            }
        } else if (!(dyVar == null || dyVar.r == null || !gc.a.equals(dyVar.r))) {
            String b = gn.b();
            if (b != null) {
                c.a("idfa").b(b);
                if (gn.c()) {
                    c.a("idfa_optout").a(1);
                }
            }
        }
        if (ekVar.u != null) {
            c.a(TapjoyConstants.TJC_USER_WEEKLY_FREQUENCY).a((long) Math.max(ekVar.u.intValue(), 1));
        }
        if (ekVar.v != null) {
            c.a(TapjoyConstants.TJC_USER_MONTHLY_FREQUENCY).a((long) Math.max(ekVar.v.intValue(), 1));
        }
        if (ekVar.w.size() > 0) {
            ArrayList arrayList = new ArrayList(ekVar.w.size());
            for (eh ehVar : ekVar.w) {
                if (ehVar.h != null) {
                    arrayList.add(ehVar.f);
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
        if (ekVar.x != null) {
            c.a("total_count").a(ekVar.x);
        }
        if (ekVar.y != null) {
            c.a("total_length").a(ekVar.y);
        }
        if (ekVar.z != null) {
            c.a("last_at").a(ekVar.z);
        }
        if (ekVar.A != null) {
            c.a("last_length").a(ekVar.A);
        }
        c.d();
        c.a("purchase").c();
        if (ekVar.B != null) {
            c.a("currency").b(ekVar.B);
        }
        if (ekVar.C != null) {
            c.a("total_count").a(ekVar.C);
        }
        if (ekVar.D != null) {
            c.a("total_price").a(ekVar.D);
        }
        if (ekVar.E != null) {
            c.a("last_at").a(ekVar.E);
        }
        if (ekVar.F != null) {
            c.a("last_price").a(ekVar.F);
        }
        c.d();
        if (ekVar.I != null) {
            c.a("user_id").b(ekVar.I);
        }
        if (ekVar.J != null) {
            c.a(TapjoyConstants.TJC_USER_LEVEL).a(ekVar.J);
        }
        if (ekVar.K != null) {
            c.a(TapjoyConstants.TJC_USER_FRIEND_COUNT).a(ekVar.K);
        }
        if (ekVar.L != null) {
            c.a(TapjoyConstants.TJC_USER_VARIABLE_1).b(ekVar.L);
        }
        if (ekVar.M != null) {
            c.a(TapjoyConstants.TJC_USER_VARIABLE_2).b(ekVar.M);
        }
        if (ekVar.N != null) {
            c.a(TapjoyConstants.TJC_USER_VARIABLE_3).b(ekVar.N);
        }
        if (ekVar.O != null) {
            c.a(TapjoyConstants.TJC_USER_VARIABLE_4).b(ekVar.O);
        }
        if (ekVar.P != null) {
            c.a(TapjoyConstants.TJC_USER_VARIABLE_5).b(ekVar.P);
        }
        if (ekVar.Q.size() > 0) {
            c.a("tags").a(ekVar.Q);
        }
        if (Boolean.TRUE.equals(ekVar.R)) {
            c.a("push_optout").a(1);
        }
        return c.d().toString();
    }

    private static String a(dy dyVar, boolean z, boolean z2, boolean z3) {
        bm b;
        bm b2 = new bm().c().a(MoatAdEvent.EVENT_TYPE).b(a(dyVar.n)).a(MediationMetaData.KEY_NAME).b(dyVar.o);
        b2.a("time");
        if (dyVar.q != null) {
            b2.a(dyVar.p);
            b2.a("systime").a(dyVar.q);
        } else if (!y.c() || dyVar.r == null || dyVar.s == null || !gc.a.equals(dyVar.r)) {
            b2.a(dyVar.p);
        } else {
            b2.a(y.a(dyVar.s.longValue()));
            b2.a("systime").a(dyVar.p);
        }
        if (dyVar.t != null) {
            b2.a("duration").a(dyVar.t);
        }
        if (!(z || dyVar.u == null)) {
            b2.a(String.VIDEO_INFO).a(new br(a(dyVar.u)));
        }
        if (!(z2 || dyVar.v == null)) {
            b2.a("app").a(new br(a(dyVar.v)));
        }
        if (!(z3 || dyVar.w == null)) {
            b2.a("user").a(new br(a(dyVar.w, dyVar)));
        }
        if (dyVar.y != null) {
            b2.a("event_seq").a(dyVar.y);
        }
        if (dyVar.z != null) {
            bm a = b2.a("event_prev");
            ea eaVar = dyVar.z;
            b = new bm().c().a(MoatAdEvent.EVENT_TYPE).b(a(eaVar.e)).a(MediationMetaData.KEY_NAME).b(eaVar.f);
            if (eaVar.g != null) {
                b.a("category").b(eaVar.g);
            }
            a.a(new br(b.d().toString()));
        }
        if (dyVar.A != null) {
            a = b2.a("purchase");
            eg egVar = dyVar.A;
            b = new bm().c().a("product_id").b(egVar.h);
            if (egVar.i != null) {
                b.a("product_quantity").a(egVar.i);
            }
            if (egVar.j != null) {
                b.a("product_price").a(egVar.j);
            }
            if (egVar.k != null) {
                b.a("product_price_currency").b(egVar.k);
            }
            if (egVar.s != null) {
                b.a("currency_price").b(egVar.s);
            }
            if (egVar.l != null) {
                b.a("product_type").b(egVar.l);
            }
            if (egVar.m != null) {
                b.a("product_title").b(egVar.m);
            }
            if (egVar.n != null) {
                b.a("product_description").b(egVar.n);
            }
            if (egVar.o != null) {
                b.a("transaction_id").b(egVar.o);
            }
            if (egVar.p != null) {
                b.a("transaction_state").a(egVar.p);
            }
            if (egVar.q != null) {
                b.a("transaction_date").a(egVar.q);
            }
            if (egVar.r != null) {
                b.a("campaign_id").b(egVar.r);
            }
            if (egVar.t != null) {
                b.a("receipt").b(egVar.t);
            }
            if (egVar.u != null) {
                b.a("signature").b(egVar.u);
            }
            a.a(new br(b.d().toString()));
        }
        if (dyVar.B != null) {
            b2.a("exception").b(dyVar.B);
        }
        try {
            if (dyVar.D != null) {
                Map linkedHashMap = new LinkedHashMap();
                if (dyVar.C != null) {
                    bs.b(dyVar.C).a(linkedHashMap);
                }
                ef efVar = dyVar.D;
                if (efVar.d != null) {
                    linkedHashMap.put("fq7_change", efVar.d);
                }
                if (efVar.e != null) {
                    linkedHashMap.put("fq30_change", efVar.e);
                }
                if (efVar.f != null) {
                    linkedHashMap.put(TJAdUnitConstants.PARAM_PUSH_ID, efVar.f);
                }
                b2.a("meta").a(linkedHashMap);
            } else if (dyVar.C != null) {
                b2.a("meta").a(new br(dyVar.C));
            }
        } catch (IOException e) {
        }
        if (dyVar.I != null) {
            b2.a("dimensions").a(new br(dyVar.I));
        }
        if (dyVar.J != null) {
            b2.a("count").a(dyVar.J);
        }
        if (dyVar.K != null) {
            b2.a("first_time").a(dyVar.K);
        }
        if (dyVar.L != null) {
            b2.a("last_time").a(dyVar.L);
        }
        if (dyVar.E != null) {
            b2.a("category").b(dyVar.E);
        }
        if (dyVar.F != null) {
            b2.a("p1").b(dyVar.F);
        }
        if (dyVar.G != null) {
            b2.a("p2").b(dyVar.G);
        }
        if (dyVar.H.size() > 0) {
            b2.a("values").c();
            for (ec ecVar : dyVar.H) {
                b2.a(ecVar.e).a(ecVar.f);
            }
            b2.d();
        }
        return b2.d().toString();
    }

    public static String a(dz dzVar) {
        ek ekVar = null;
        bm a = new bm().a();
        dx dxVar = null;
        ed edVar = null;
        for (dy dyVar : dzVar.d) {
            ed edVar2;
            boolean z;
            dx dxVar2;
            boolean z2;
            ek ekVar2;
            boolean z3;
            if (edVar == null || !edVar.equals(dyVar.u)) {
                edVar2 = dyVar.u;
                z = false;
            } else {
                z = true;
                edVar2 = edVar;
            }
            if (dxVar == null || !dxVar.equals(dyVar.v)) {
                dxVar2 = dyVar.v;
                z2 = false;
            } else {
                z2 = true;
                dxVar2 = dxVar;
            }
            if (ekVar == null || !ekVar.equals(dyVar.w)) {
                ekVar2 = dyVar.w;
                z3 = false;
            } else {
                ekVar2 = ekVar;
                z3 = true;
            }
            a.a(new br(a(dyVar, z, z2, z3)));
            ekVar = ekVar2;
            dxVar = dxVar2;
            edVar = edVar2;
        }
        return a.b().toString();
    }

    private static String a(eb ebVar) {
        switch (AnonymousClass1.a[ebVar.ordinal()]) {
            case TwitterResponse.READ /*1*/:
                return "app";
            case TwitterResponse.READ_WRITE /*2*/:
                return "campaign";
            case TwitterResponse.READ_WRITE_DIRECTMESSAGES /*3*/:
                return "custom";
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsTextAppearance /*4*/:
                return "usages";
            default:
                throw new RuntimeException();
        }
    }
}
