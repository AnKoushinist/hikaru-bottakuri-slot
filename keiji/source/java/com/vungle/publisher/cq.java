package com.vungle.publisher;

import android.content.ContentValues;
import android.database.Cursor;
import com.vungle.log.Logger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.inject.Inject;
import javax.inject.Singleton;
import twitter4j.TwitterResponse;

/* compiled from: vungle */
public abstract class cq<T extends cq<T, P, E, A>, P extends cp<T, P, E>, E extends cr<P>, A extends cj> extends dl<Integer> {
    protected A a;
    protected String b;
    protected String c;
    protected Long d;
    protected boolean e;
    protected String f;
    protected c g;
    protected Long h;
    protected Integer i;
    protected Long j;
    protected Long k;
    ej l;
    protected List<P> m;
    protected List<ck> n;
    Long o;
    boolean p;
    @Inject
    com.vungle.publisher.cs.a q;
    @Inject
    agt r;

    /* compiled from: vungle */
    public static abstract class a<T extends cq<T, P, E, A>, P extends cp<T, P, E>, E extends cr<P>, A extends cj, R extends ade> extends com.vungle.publisher.dl.a<T, Integer> {
        @Inject
        com.vungle.publisher.cs.a a;
        @Inject
        com.vungle.publisher.ck.a b;

        protected abstract com.vungle.publisher.cj.a<A, R> a();

        protected abstract com.vungle.publisher.cp.a<T, P, E> e();

        public abstract j f();

        public final /* bridge */ /* synthetic */ List d() {
            return super.d();
        }

        protected a() {
        }

        public T a(A a) {
            cq cqVar = (cq) c_();
            cqVar.a = a;
            cqVar.g = c.open;
            a(cqVar, (cj) a, false);
            Logger.d(Logger.DATABASE_TAG, "creating new " + cqVar.z());
            return cqVar;
        }

        protected final List<T> g() {
            List<T> a = a("status = ? AND ad_id IN (SELECT id FROM ad WHERE type = ?)", new String[]{c.reportable.toString(), f().toString()}, "insert_timestamp_millis ASC");
            for (T a2 : a) {
                a((cq) a2, null, true);
            }
            return a;
        }

        public final T b(A a) {
            T d = d(a);
            if (d != null) {
                return d;
            }
            d = a((cj) a);
            d.u();
            return d;
        }

        public final T c(A a) {
            return d(a);
        }

        private T d(A a) {
            String[] strArr = new String[]{c.open.toString()};
            String str = "status = ?";
            if (a == null) {
                throw new IllegalArgumentException("null ad");
            }
            String str2 = (String) a.w();
            if (str2 == null) {
                throw new IllegalArgumentException("null ad_id");
            }
            Object[] objArr = new String[2];
            objArr[0] = str2;
            for (int i = 0; i <= 0; i++) {
                objArr[1] = strArr[0];
            }
            List a2 = a("ad_id = ? AND " + str, objArr, "insert_timestamp_millis DESC");
            str = "ad_id = ? AND " + str + ", with params: " + ags.a(objArr);
            int size = a2.size();
            switch (size) {
                case TwitterResponse.NONE /*0*/:
                    return null;
                case TwitterResponse.READ /*1*/:
                    T a3 = a((cq) a2.get(0), (cj) a, false);
                    Logger.d(Logger.DATABASE_TAG, "fetched " + a3.z());
                    return a3;
                default:
                    Logger.w(Logger.DATABASE_TAG, size + " ad_report records for " + str);
                    return null;
            }
        }

        protected T a(T t, Cursor cursor, boolean z) {
            t.t = cb.d(cursor, "id");
            t.a(cb.f(cursor, "ad_id"));
            t.c = cb.f(cursor, "incentivized_publisher_app_user_id");
            t.e = cb.a(cursor, "is_incentivized").booleanValue();
            t.d = cb.e(cursor, "insert_timestamp_millis");
            t.f = cb.f(cursor, "placement");
            t.g = (c) cb.a(cursor, "status", c.class);
            t.h = cb.e(cursor, "update_timestamp_millis");
            t.i = cb.d(cursor, "video_duration_millis");
            t.j = cb.e(cursor, "view_end_millis");
            t.k = cb.e(cursor, "view_start_millis");
            return t;
        }

        protected T a(T t, A a, boolean z) {
            if (a == null) {
                t.a = (cj) a().a((Object) t.g());
            } else {
                t.a = a;
            }
            if (z) {
                t.m = e().b((cq) t);
                t.l = this.a.b((Integer) t.t);
                t.n = this.b.a((Integer) t.t);
            }
            return t;
        }

        protected final String c() {
            return "ad_report";
        }
    }

    @Singleton
    /* compiled from: vungle */
    public static class b {
        @Inject
        public cf a;
        @Inject
        com.vungle.publisher.fj.a b;
        @Inject
        com.vungle.publisher.if.a c;
        @Inject
        com.vungle.publisher.kn.a d;
        @Inject
        com.vungle.publisher.hd.a e;
        Map<j, a<?, ?, ?, ?, ?>> f;

        @Inject
        b() {
        }

        public final <A extends cj> ec a(dn<A> dnVar) {
            Object a = a(dnVar.h_());
            try {
                return (ec) a;
            } catch (Exception e) {
                throw new IllegalArgumentException("ad report type is not cacheable " + a);
            }
        }

        public final <A extends cj> ec b(dn<A> dnVar) {
            final cj h_ = dnVar.h_();
            try {
                return (ec) ((cq) new o<cq<?, ?, ?, A>>(this) {
                    final /* synthetic */ b b;

                    protected final /* synthetic */ Object a() {
                        return this.b.b.c(h_);
                    }

                    protected final /* synthetic */ Object b() {
                        return this.b.c.c(h_);
                    }

                    protected final /* bridge */ /* synthetic */ Object c() {
                        return this.b.d.c(h_);
                    }

                    protected final /* synthetic */ Object d() {
                        return this.b.e.c(h_);
                    }
                }.a(h_));
            } catch (Exception e) {
                throw new IllegalArgumentException("ad type is not cacheable " + h_);
            }
        }

        public final List<cq<?, ?, ?, ?>> a() {
            List<cq<?, ?, ?, ?>> arrayList = new ArrayList();
            Map map;
            if (this.f != null) {
                map = this.f;
            } else {
                this.f = new HashMap();
                this.f.put(j.vungle_local, this.b);
                this.f.put(j.vungle_streaming, this.c);
                this.f.put(j.vungle_mraid, this.d);
                this.f.put(j.third_party_mraid, this.e);
                map = this.f;
            }
            for (a g : r0.values()) {
                for (cq add : g.g()) {
                    arrayList.add(add);
                }
            }
            return arrayList;
        }

        public final <A extends cj> cq<?, ?, ?, A> a(final A a) {
            return (cq) new o<cq<?, ?, ?, A>>(this) {
                final /* synthetic */ b b;

                protected final /* synthetic */ Object a() {
                    return this.b.b.b(a);
                }

                protected final /* bridge */ /* synthetic */ Object b() {
                    return this.b.c.b(a);
                }

                protected final /* synthetic */ Object c() {
                    return this.b.d.b(a);
                }

                protected final /* synthetic */ Object d() {
                    return this.b.e.b(a);
                }
            }.a((cj) a);
        }
    }

    /* compiled from: vungle */
    public enum c {
        open,
        failed,
        playing,
        reportable
    }

    public abstract a<T, P, E, A, ?> b();

    protected abstract com.vungle.publisher.cp.a<T, P, E> e();

    public final /* synthetic */ Object v() {
        return u();
    }

    protected cq() {
    }

    protected final String c() {
        return "ad_report";
    }

    public final List<ck> f() {
        return this.n;
    }

    protected final String g() {
        return this.a == null ? this.b : (String) this.a.w();
    }

    protected final void a(String str) {
        this.b = str;
    }

    public final A h() {
        return this.a;
    }

    public final void a(Map<String, String> map) {
        ej ejVar;
        com.vungle.publisher.cs.a aVar = this.q;
        Integer num = (Integer) this.t;
        if (map != null) {
            ej ejVar2 = new ej();
            for (Entry entry : map.entrySet()) {
                String str = (String) entry.getKey();
                String str2 = (String) entry.getValue();
                cs a = aVar.a();
                a.a = num;
                a.b = str;
                a.c = str2;
                ejVar2.put(str, a);
            }
            ejVar = ejVar2;
        } else {
            ejVar = null;
        }
        this.l = ejVar;
        int size = ejVar == null ? 0 : ejVar.size();
        if (size <= 0) {
            Logger.d(Logger.DATABASE_TAG, "no new extras for " + z());
            return;
        }
        Logger.d(Logger.DATABASE_TAG, size + " new extras for " + z());
        this.p = true;
        C();
    }

    public final ej i() {
        ej ejVar = this.l;
        if (ejVar != null) {
            return ejVar;
        }
        ejVar = this.q.b((Integer) this.t);
        this.l = ejVar;
        return ejVar;
    }

    public final boolean j() {
        return this.e;
    }

    public final void b(boolean z) {
        this.e = z;
    }

    public final String k() {
        return this.c;
    }

    public final void b(String str) {
        this.c = str;
    }

    public final void c(String str) {
        this.f = str;
    }

    public final String o() {
        return this.f;
    }

    public final void a(c cVar) {
        Logger.d(Logger.REPORT_TAG, "setting ad_report status " + cVar + " for " + z());
        this.g = cVar;
    }

    public final Integer p() {
        return this.i;
    }

    public final void a(Integer num) {
        Logger.d(Logger.REPORT_TAG, "setting video duration millis " + num + " for " + z());
        this.i = num;
        b_();
    }

    public final int q() {
        if (this.k == null) {
            Logger.w(Logger.DATABASE_TAG, "unable to calculate ad duration because view start millis was null");
            return -1;
        } else if (this.j != null) {
            return (int) (this.j.longValue() - this.k.longValue());
        } else {
            Logger.w(Logger.DATABASE_TAG, "unable to calculate ad duration because view end millis was null");
            return -1;
        }
    }

    public final void a(Long l) {
        Logger.d(Logger.REPORT_TAG, "setting ad end millis " + l + " for " + z());
        this.j = l;
    }

    public final void b(Long l) {
        a(l);
        b_();
    }

    public final Long r() {
        return this.k;
    }

    public final void c(Long l) {
        Logger.d(Logger.REPORT_TAG, "setting ad start millis " + l + " for " + z());
        this.k = l;
    }

    public final P s() {
        List B = B();
        P a = e().a(this);
        a.v();
        B.add(a);
        return a;
    }

    public final P[] t() {
        List B = B();
        return (cp[]) B.toArray(e().a(B.size()));
    }

    private List<P> B() {
        List<P> list = this.m;
        if (list != null) {
            return list;
        }
        list = e().b(this);
        this.m = list;
        return list;
    }

    public final Integer u() {
        Integer num = (Integer) super.v();
        C();
        return num;
    }

    private void C() {
        if (this.p) {
            ej ejVar = this.l;
            if (this.t == null) {
                Logger.d(Logger.DATABASE_TAG, "delaying inserting extras for uninserted " + z());
                return;
            }
            Logger.d(Logger.DATABASE_TAG, "replacing extras for " + z());
            this.q.a((Integer) this.t);
            if (!(ejVar == null || ejVar.isEmpty())) {
                com.vungle.publisher.dl.a.a((dl[]) (cs[]) ejVar.values().toArray(new cs[ejVar.size()]));
            }
            this.p = false;
            return;
        }
        Logger.v(Logger.DATABASE_TAG, "no new extras to insert for " + z());
    }

    protected ContentValues a(boolean z) {
        long currentTimeMillis = System.currentTimeMillis();
        ContentValues contentValues = new ContentValues();
        if (z) {
            contentValues.put("ad_id", g());
            Long valueOf = Long.valueOf(currentTimeMillis);
            this.d = valueOf;
            contentValues.put("insert_timestamp_millis", valueOf);
        }
        contentValues.put("incentivized_publisher_app_user_id", this.c);
        contentValues.put("is_incentivized", Boolean.valueOf(this.e));
        contentValues.put("placement", this.f);
        contentValues.put("status", ags.a(this.g));
        Long valueOf2 = Long.valueOf(currentTimeMillis);
        this.h = valueOf2;
        contentValues.put("update_timestamp_millis", valueOf2);
        contentValues.put("video_duration_millis", this.i);
        contentValues.put("view_end_millis", this.j);
        contentValues.put("view_start_millis", this.k);
        return contentValues;
    }

    public StringBuilder m() {
        StringBuilder m = super.m();
        dl.a(m, "ad_id", g(), false);
        dl.a(m, "insert_timestamp_millis", this.d, false);
        dl.a(m, "incentivized_publisher_app_user_id", this.c, false);
        dl.a(m, "is_incentivized", Boolean.valueOf(this.e), false);
        dl.a(m, "placement", this.f, false);
        dl.a(m, "status", this.g, false);
        dl.a(m, "update_timestamp_millis", this.h, false);
        dl.a(m, "video_duration_millis", this.i, false);
        dl.a(m, "view_end_millis", this.j, false);
        dl.a(m, "view_start_millis", this.k, false);
        dl.a(m, "plays", this.m == null ? "not fetched" : Integer.valueOf(this.m.size()), false);
        return m;
    }
}
