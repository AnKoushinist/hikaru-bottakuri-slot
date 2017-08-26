package com.vungle.publisher;

import android.content.ContentValues;
import android.database.Cursor;
import com.vungle.log.Logger;
import java.util.Arrays;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

/* compiled from: vungle */
public abstract class cj extends dl<String> {
    protected static final String a = ("(SELECT DISTINCT ad_id FROM ad_report WHERE status IN ('" + com.vungle.publisher.cq.c.reportable + "', '" + com.vungle.publisher.cq.c.playing + "'))");
    protected static final String b = ("id NOT IN " + a);
    protected static final String c = ("id IN " + a);
    protected j d;
    protected c e;
    protected long f;
    protected long g;
    protected long h;
    protected String i;
    protected String j;
    int k;
    Long l;
    String m;
    int n;
    long o;
    String p;

    /* compiled from: vungle */
    public static abstract class a<A extends cj, R extends ade> extends com.vungle.publisher.dl.a<A, String> {
        @Inject
        ql a;
        @Inject
        agt b;

        protected abstract j a();

        protected /* synthetic */ Object[] b(int i) {
            return a_(i);
        }

        public final /* bridge */ /* synthetic */ List c(int i) {
            return super.c(i);
        }

        public final /* bridge */ /* synthetic */ List d() {
            return super.d();
        }

        public A a(R r) {
            cj cjVar = (cj) c_();
            cjVar.t = r.f;
            cjVar.d = a();
            cjVar.s = String.class;
            cjVar.l = r.c;
            a(cjVar, (ade) r);
            return cjVar;
        }

        protected A a(A a, R r) {
            a.j = r.h;
            a.i = r.a();
            return a;
        }

        public int b(A a, R r) {
            a.l = r.c;
            return a.b_();
        }

        protected final int b() {
            Logger.d(Logger.DATABASE_TAG, "deleting " + a() + " records without pending reports in status " + c.deleting);
            return this.d.getWritableDatabase().delete("ad", cj.b + " AND status = ?", new String[]{r0.toString()});
        }

        public final boolean a(cj cjVar) {
            if (!b("id = ? AND " + cj.b + " AND ((expiration_timestamp_seconds IS NULL OR expiration_timestamp_seconds <= ?) OR status != ?)", new String[]{(String) cjVar.w(), Long.toString(System.currentTimeMillis() / 1000), c.ready.toString()})) {
                return false;
            }
            Logger.d(Logger.DATABASE_TAG, "deleting ad after successful report");
            if (cjVar.n() > 0) {
                return true;
            }
            return false;
        }

        protected final int a(List<? extends cj> list, c cVar) {
            int size = list.size();
            Object[] objArr = new String[size];
            int i = 0;
            int i2 = 0;
            for (cj cjVar : list) {
                int i3 = i + 1;
                objArr[i] = (String) cjVar.w();
                c g = cjVar.g();
                int i4 = (cVar == c.ready || g != c.ready) ? (cVar != c.ready || g == c.ready) ? 0 : 1 : -1;
                i4 += i2;
                cjVar.a(cVar);
                i = i3;
                i2 = i4;
            }
            ContentValues contentValues = new ContentValues();
            contentValues.put("status", cVar.toString());
            String str = "id IN (" + cb.a(size) + ")";
            Logger.d(Logger.DATABASE_TAG, "updating status of ads " + ags.a(objArr) + " to " + cVar);
            int updateWithOnConflict = this.d.getWritableDatabase().updateWithOnConflict(c(), contentValues, str, objArr, 3);
            if (updateWithOnConflict > 0) {
                if (i2 > 0) {
                    Logger.d(Logger.DATABASE_TAG, "ad availability increased by " + i2);
                    this.a.a(new ag());
                } else if (i2 < 0) {
                    Logger.d(Logger.DATABASE_TAG, "ad availability decreased by " + i2);
                    this.a.a(new ab());
                }
            }
            return updateWithOnConflict;
        }

        protected A a(A a, Cursor cursor, boolean z) {
            a.j = cb.f(cursor, "advertising_app_vungle_id");
            a.i = cb.f(cursor, "delivery_id");
            a.t = cb.f(cursor, "id");
            a.f = cb.e(cursor, "insert_timestamp_millis").longValue();
            a.e = (c) cb.a(cursor, "status", c.class);
            a.d = (j) cb.a(cursor, "type", j.class);
            a.g = cb.e(cursor, "update_timestamp_millis").longValue();
            a.h = cb.e(cursor, "failed_timestamp_millis").longValue();
            a.k = cb.c(cursor, "delete_local_content_attempts");
            a.l = cb.e(cursor, "expiration_timestamp_seconds");
            a.n = cb.c(cursor, "prepare_retry_count");
            a.o = System.currentTimeMillis();
            return a;
        }

        protected String c() {
            return "ad";
        }

        protected String[] a_(int i) {
            return new String[i];
        }
    }

    @Singleton
    /* compiled from: vungle */
    public static class b {
        @Inject
        com.vungle.publisher.eo.a a;
        @Inject
        com.vungle.publisher.hu.a b;
        @Inject
        com.vungle.publisher.js.a c;
        @Inject
        com.vungle.publisher.gs.a d;

        /* compiled from: vungle */
        public class AnonymousClass2 extends o<cj> {
            final /* synthetic */ String a;
            final /* synthetic */ b b;

            public AnonymousClass2(b bVar, String str) {
                this.b = bVar;
                this.a = str;
            }

            protected final /* bridge */ /* synthetic */ Object a() {
                return (cj) this.b.a.a(this.a);
            }

            protected final /* synthetic */ Object b() {
                return (cj) this.b.b.a(this.a);
            }

            protected final /* synthetic */ Object c() {
                return (cj) this.b.c.a(this.a);
            }

            protected final /* synthetic */ Object d() {
                return (cj) this.b.d.a(this.a);
            }
        }

        @Inject
        b() {
        }

        public final <A extends cj, R extends ade, F extends dw<A, R>> F a(final j jVar) {
            return (dw) new o<F>(this) {
                final /* synthetic */ b b;

                protected final /* bridge */ /* synthetic */ Object a() {
                    return this.b.a;
                }

                protected final /* synthetic */ Object b() {
                    throw new IllegalArgumentException("cannot get cacheable factory for ad type: " + jVar);
                }

                protected final /* bridge */ /* synthetic */ Object c() {
                    return this.b.c;
                }

                protected final /* bridge */ /* synthetic */ Object d() {
                    return this.b.d;
                }
            }.a(jVar);
        }
    }

    /* compiled from: vungle */
    public enum c {
        aware,
        failed,
        invalid,
        preparing,
        ready,
        viewed,
        deleting
    }

    protected abstract a<?, ?> a();

    protected abstract boolean b();

    protected final String c() {
        return "ad";
    }

    protected final boolean d_() {
        return false;
    }

    public final String e() {
        return this.j;
    }

    public final j f() {
        return this.d;
    }

    public final c g() {
        return this.e;
    }

    public final void a(String str) {
        this.m = str;
        this.p = null;
    }

    public final String h() {
        if (this.p == null) {
            this.p = qx.a(this.m, qx.c((String) this.t));
        }
        return this.p;
    }

    public final int i() {
        return this.n;
    }

    public final void j() {
        this.n = 0;
    }

    public final long k() {
        return this.h;
    }

    public void a(c cVar) {
        Logger.v(Logger.PREPARE_TAG, "setting status from " + this.e + " to " + cVar + " for " + z());
        this.e = cVar;
        if (cVar == c.failed) {
            this.h = System.currentTimeMillis();
        }
    }

    public final void b(c cVar) {
        a().a(Arrays.asList(new cj[]{this}), cVar);
    }

    protected ContentValues a(boolean z) {
        long currentTimeMillis = System.currentTimeMillis();
        this.g = currentTimeMillis;
        ContentValues contentValues = new ContentValues();
        if (z) {
            contentValues.put("id", (String) w());
            this.f = currentTimeMillis;
            contentValues.put("insert_timestamp_millis", Long.valueOf(currentTimeMillis));
            contentValues.put("type", this.d.toString());
        }
        contentValues.put("advertising_app_vungle_id", this.j);
        contentValues.put("delivery_id", this.i);
        contentValues.put("status", this.e.toString());
        contentValues.put("update_timestamp_millis", Long.valueOf(currentTimeMillis));
        contentValues.put("failed_timestamp_millis", Long.valueOf(this.h));
        contentValues.put("delete_local_content_attempts", Integer.valueOf(this.k));
        contentValues.put("expiration_timestamp_seconds", this.l);
        contentValues.put("parent_path", this.m);
        contentValues.put("prepare_retry_count", Integer.valueOf(this.n));
        contentValues.put("received_timestamp_millis", Long.valueOf(this.o));
        return contentValues;
    }

    protected final StringBuilder e_() {
        StringBuilder e_ = super.e_();
        dl.a(e_, "type", this.d, false);
        return e_;
    }

    protected StringBuilder m() {
        StringBuilder m = super.m();
        dl.a(m, "advertising_app_vungle_id", this.j, false);
        dl.a(m, "delivery_id", this.i, false);
        dl.a(m, "insert_timestamp_millis", Long.valueOf(this.f), false);
        dl.a(m, "status", this.e, false);
        dl.a(m, "update_timestamp_millis", Long.valueOf(this.g), false);
        dl.a(m, "failed_timestamp_millis", Long.valueOf(this.h), false);
        dl.a(m, "delete_local_content_attempts", Integer.valueOf(this.k), false);
        dl.a(m, "expiration_timestamp_seconds", this.l, false);
        dl.a(m, "parent_path", this.m, false);
        dl.a(m, "prepare_retry_count", Integer.valueOf(this.n), false);
        dl.a(m, "received_timestamp_millis", Long.valueOf(this.o), false);
        return m;
    }

    public final int n() {
        int i = this.k;
        this.k = i + 1;
        if (b()) {
            return super.n();
        }
        Logger.w(Logger.DATABASE_TAG, "unable to delete files for " + z() + " attempt " + i);
        b_();
        return 0;
    }

    public boolean equals(Object obj) {
        return (obj instanceof cj) && a((cj) obj);
    }

    public final boolean a(cj cjVar) {
        return (cjVar == null || cjVar.t == null || !((String) cjVar.t).equals(this.t)) ? false : true;
    }

    public int hashCode() {
        return this.t == null ? super.hashCode() : ((String) this.t).hashCode();
    }

    public final boolean o() {
        return a().a(this);
    }
}
