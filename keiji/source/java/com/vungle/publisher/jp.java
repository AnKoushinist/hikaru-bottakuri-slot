package com.vungle.publisher;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import com.moat.analytics.mobile.tjy.MoatAdEvent;
import com.vungle.log.Logger;
import javax.inject.Inject;
import javax.inject.Singleton;
import twitter4j.TwitterResponse;

/* compiled from: vungle */
public abstract class jp<A extends cj> extends dl<Integer> implements el<A> {
    protected String o;
    protected j p;
    protected com.vungle.publisher.el.a q;
    protected com.vungle.publisher.el.b r;
    protected A v;

    /* compiled from: vungle */
    public static abstract class a<A extends cj, W extends jp<A>, R extends ade> extends com.vungle.publisher.dl.a<W, Integer> {
        @Inject
        com.vungle.publisher.j.a e;

        protected a() {
        }

        protected /* synthetic */ Object[] b(int i) {
            return d(i);
        }

        protected W a(A a, R r) {
            if (r == null) {
                return null;
            }
            jp jpVar = (jp) c_();
            jpVar.v = a;
            jpVar.o = r.f;
            jpVar.p = r.e;
            jpVar.q = com.vungle.publisher.el.a.aware;
            return jpVar;
        }

        protected final W a(String str, com.vungle.publisher.el.b bVar, boolean z) {
            jp jpVar = (jp) c_();
            jpVar.o = str;
            jpVar.r = bVar;
            return a(jpVar, z);
        }

        private W a(W w, boolean z) {
            Throwable th;
            W w2 = null;
            Integer num = (Integer) w.t;
            com.vungle.publisher.el.b bVar = w.r;
            Cursor query;
            try {
                String str;
                String str2 = w.o;
                String str3;
                if (num != null) {
                    str3 = "id: " + num;
                    Logger.d(Logger.DATABASE_TAG, "fetching " + bVar + " by " + str3);
                    query = this.d.getReadableDatabase().query("viewable", null, "id = ?", new String[]{String.valueOf(num)}, null, null, null);
                    str = str3;
                } else if (str2 == null) {
                    Logger.w(Logger.DATABASE_TAG, "unable to fetch " + bVar + ": no id or ad_id");
                    str = null;
                    query = null;
                } else {
                    str3 = "ad_id " + str2;
                    Logger.d(Logger.DATABASE_TAG, "fetching " + bVar + " by " + str3);
                    query = this.d.getReadableDatabase().query("viewable", null, "ad_id = ? AND type = ?", new String[]{str2, String.valueOf(bVar)}, null, null, null);
                    str = str3;
                }
                if (query != null) {
                    try {
                        int count = query.getCount();
                        switch (count) {
                            case TwitterResponse.NONE /*0*/:
                                Logger.v(Logger.DATABASE_TAG, "no " + bVar + " found for " + str);
                                break;
                            case TwitterResponse.READ /*1*/:
                                Logger.d(Logger.DATABASE_TAG, "have " + bVar + " for " + str);
                                query.moveToFirst();
                                w2 = a((jp) w, query, z);
                                break;
                            default:
                                throw new SQLException(count + " " + bVar + " records for " + str);
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        if (query != null) {
                            query.close();
                        }
                        throw th;
                    }
                }
                if (query != null) {
                    query.close();
                }
                Logger.v(Logger.DATABASE_TAG, "fetched " + w2);
                return w2;
            } catch (Throwable th3) {
                th = th3;
                query = null;
                if (query != null) {
                    query.close();
                }
                throw th;
            }
        }

        protected W a(W w, Cursor cursor, boolean z) {
            w.t = cb.d(cursor, "id");
            w.o = cb.f(cursor, "ad_id");
            w.q = (com.vungle.publisher.el.a) cb.a(cursor, "status", com.vungle.publisher.el.a.class);
            w.r = (com.vungle.publisher.el.b) cb.a(cursor, MoatAdEvent.EVENT_TYPE, com.vungle.publisher.el.b.class);
            w.p = this.e.a(cb.f(cursor, "ad_type"));
            return w;
        }

        protected final String c() {
            return "viewable";
        }

        protected Integer[] d(int i) {
            return new Integer[i];
        }
    }

    @Singleton
    /* compiled from: vungle */
    public static class b {
        @Inject
        cf a;
        @Inject
        a b;
        @Inject
        com.vungle.publisher.jt.a c;
        @Inject
        com.vungle.publisher.ep.a d;
        @Inject
        com.vungle.publisher.en.a e;

        @Inject
        b() {
        }
    }

    protected abstract com.vungle.publisher.cj.a<A, ?> r();

    public final /* bridge */ /* synthetic */ Object w() {
        return (Integer) this.t;
    }

    protected jp() {
    }

    protected final String c() {
        return "viewable";
    }

    public final Integer D() {
        return (Integer) this.t;
    }

    public final String l() {
        return this.o;
    }

    public final String d() {
        if (this.v == null) {
            this.v = (cj) r().a((Object) this.o);
        }
        return this.v.h();
    }

    public final com.vungle.publisher.el.a s() {
        return this.q;
    }

    public final void a(com.vungle.publisher.el.a aVar) {
        Logger.v(Logger.PREPARE_TAG, "setting " + this.r + " status from " + this.q + " to " + aVar + " for ad_id: " + this.o);
        this.q = aVar;
    }

    public final void b(com.vungle.publisher.el.a aVar) {
        Logger.v(Logger.PREPARE_TAG, "updating " + this.r + " status from " + this.q + " to " + aVar + " for ad_id: " + this.o);
        this.q = aVar;
        b_();
    }

    public final com.vungle.publisher.el.b t() {
        return this.r;
    }

    protected ContentValues a(boolean z) {
        ContentValues contentValues = new ContentValues();
        if (z) {
            contentValues.put("id", (Integer) this.t);
            contentValues.put("ad_id", this.o);
            contentValues.put(MoatAdEvent.EVENT_TYPE, this.r.toString());
            contentValues.put("ad_type", this.p.toString());
        }
        contentValues.put("status", this.q.toString());
        return contentValues;
    }

    protected StringBuilder m() {
        StringBuilder m = super.m();
        dl.a(m, "ad_id", this.o, false);
        dl.a(m, "status", this.q, false);
        dl.a(m, MoatAdEvent.EVENT_TYPE, this.r, false);
        return m;
    }

    protected final String A() {
        return String.valueOf(this.r);
    }
}
