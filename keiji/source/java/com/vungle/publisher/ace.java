package com.vungle.publisher;

import com.unity3d.ads.metadata.MediationMetaData;
import com.vungle.publisher.abg.c;
import com.vungle.publisher.acb.b;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import jp.co.geniee.gnsrewardadapter.GNSAdapterVungleRewardVideoAd;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: vungle */
public abstract class ace extends abg {
    protected abt g;
    protected Integer h;
    protected Long i;
    protected String j;
    protected String k;
    protected Integer l;
    protected String m;
    protected Boolean n;
    protected String o;
    protected acb[] p;
    protected List<ck> q;
    protected j r;
    protected String s;

    /* compiled from: vungle */
    public static abstract class a<P extends cp<T, P, ?>, O extends ace, T extends cq<T, P, ?, A>, A extends cj> extends c<O> {
        @Inject
        protected a f;

        protected abstract com.vungle.publisher.acb.a<P, ?, ?> c();

        protected a() {
        }

        public O a(T t) {
            ace com_vungle_publisher_ace = (ace) super.a();
            if (t != null) {
                cj h = t.h();
                com_vungle_publisher_ace.i = t.r();
                com_vungle_publisher_ace.j = h.e();
                com_vungle_publisher_ace.k = (String) h.w();
                com_vungle_publisher_ace.l = Integer.valueOf(t.q());
                com_vungle_publisher_ace.m = t.k();
                com_vungle_publisher_ace.n = Boolean.valueOf(t.j());
                com_vungle_publisher_ace.o = t.o();
                com_vungle_publisher_ace.p = c().a(t.t());
                com_vungle_publisher_ace.q = t.f();
                com_vungle_publisher_ace.r = h.f();
                com_vungle_publisher_ace.g = a.b(t.i());
            }
            return com_vungle_publisher_ace;
        }
    }

    public /* synthetic */ Object b() {
        return a();
    }

    public JSONObject a() {
        Object valueOf;
        JSONObject a = super.a();
        a.putOpt(FullScreenAdActivity.AD_TYPE_EXTRA_KEY, this.r);
        a.putOpt("ttDownload", this.h);
        a.putOpt("adStartTime", this.i);
        a.putOpt(GNSAdapterVungleRewardVideoAd.APP_ID_COLUMN_NAME, this.j);
        a.putOpt("campaign", this.k);
        a.putOpt("adDuration", this.l);
        if (Boolean.TRUE.equals(this.n)) {
            a.putOpt(MediationMetaData.KEY_NAME, this.m);
        }
        String str = "incentivized";
        Boolean bool = this.n;
        if (bool != null) {
            valueOf = Integer.valueOf(bool.booleanValue() ? 1 : 0);
        } else {
            valueOf = null;
        }
        a.putOpt(str, valueOf);
        a.putOpt("placement", this.o);
        a.putOpt("plays", se.a(this.p));
        a.putOpt("clickedThrough", new JSONArray(c()));
        a.putOpt("id", this.s);
        a.putOpt("errors", se.a(this.q));
        a.putOpt("extraInfo", se.a(this.g));
        return a;
    }

    private List<String> c() {
        List<String> arrayList = new ArrayList();
        if (this.p != null && this.p.length > 0) {
            String aVar = com.vungle.publisher.jo.a.volume.toString();
            for (acb com_vungle_publisher_acb : this.p) {
                b[] bVarArr = com_vungle_publisher_acb.c;
                if (bVarArr != null) {
                    for (b bVar : bVarArr) {
                        if (!aVar.equals(bVar.a)) {
                            arrayList.add(bVar.a);
                        }
                    }
                }
            }
        }
        return arrayList;
    }
}
