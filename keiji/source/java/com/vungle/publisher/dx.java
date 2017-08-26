package com.vungle.publisher;

import android.database.Cursor;
import android.database.SQLException;
import com.moat.analytics.mobile.tjy.MoatAdEvent;
import com.vungle.log.Logger;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import twitter4j.TwitterResponse;

/* compiled from: vungle */
public abstract class dx<A extends cj, R extends ade, F extends com.vungle.publisher.cj.a<A, R>> {
    @Inject
    cf a;
    @Inject
    c b;
    @Inject
    agt c;
    com.vungle.publisher.cj.a<A, R> d;

    /* compiled from: vungle */
    public static abstract class a<A extends cj, R extends ade, F extends com.vungle.publisher.cj.a<A, R>, C extends dx<A, R, F>> {
        abstract C a();

        public final C a(F f) {
            C a = a();
            a.d = f;
            return a;
        }
    }

    @Singleton
    /* compiled from: vungle */
    public static class b {
        @Inject
        cf a;
        @Inject
        com.vungle.publisher.j.a b;
        @Inject
        com.vungle.publisher.cj.b c;
        @Inject
        com.vungle.publisher.gm.a d;
        @Inject
        com.vungle.publisher.jy.a e;
        @Inject
        com.vungle.publisher.eu.a f;
        @Inject
        c g;

        /* compiled from: vungle */
        public abstract class a {
            final /* synthetic */ b b;

            abstract int a(dx<?, ?, ?> dxVar);

            private a(b bVar) {
                this.b = bVar;
            }

            public final int a() {
                int i = 0;
                b bVar = this.b;
                dx[] dxVarArr = new dx[]{bVar.c.a(j.vungle_mraid).i_(), bVar.c.a(j.vungle_local).i_()};
                int i2 = 0;
                while (i < 2) {
                    i2 += a(dxVarArr[i]);
                    i++;
                }
                return i2;
            }
        }

        @Inject
        b() {
        }

        public final int a() {
            return new a(this) {
                final /* synthetic */ b a;

                {
                    this.a = r2;
                }

                final int a(dx<?, ?, ?> dxVar) {
                    return dxVar.c();
                }
            }.a();
        }

        public final dn<?> b() {
            return a(com.vungle.publisher.cj.c.ready);
        }

        public final Long c() {
            Cursor a;
            Throwable th;
            Long l = null;
            try {
                String[] strArr = new String[]{com.vungle.publisher.cj.c.viewed.toString(), com.vungle.publisher.cj.c.deleting.toString()};
                com.vungle.publisher.hs.a aVar = new com.vungle.publisher.hs.a();
                aVar.a = "ad";
                a = this.a.a(aVar.a("status NOT IN (" + cb.a(2) + ")").a(" ORDER BY expiration_timestamp_seconds ASC").a(" LIMIT ?").a(strArr).a(new String[]{"1"}).a());
                try {
                    if (a.moveToFirst()) {
                        Long e = cb.e(a, "expiration_timestamp_seconds");
                        if (e == null) {
                            Logger.w(Logger.PREPARE_TAG, "next ad expiration time seconds is null");
                        } else {
                            l = Long.valueOf(e.longValue() * 1000);
                            Logger.d(Logger.PREPARE_TAG, "next ad expiration time millis " + l);
                        }
                    }
                    if (a != null) {
                        a.close();
                    }
                    return l;
                } catch (Throwable th2) {
                    th = th2;
                    if (a != null) {
                        a.close();
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                a = null;
                if (a != null) {
                    a.close();
                }
                throw th;
            }
        }

        public final <A extends cj, R extends ade> dn<?> a(com.vungle.publisher.cj.c... cVarArr) {
            Cursor a;
            Throwable e;
            Cursor cursor = null;
            String[] a2 = agm.a(cVarArr);
            try {
                String[] a3 = agm.a(new j[]{j.vungle_local, j.vungle_mraid, j.third_party_mraid});
                String[] strArr = new String[]{Long.toString(System.currentTimeMillis() / 1000), "1"};
                com.vungle.publisher.hs.a aVar = new com.vungle.publisher.hs.a();
                aVar.a = "ad";
                hs a4 = aVar.a("status IN (" + cb.a(a2.length) + ")").a(" AND type IN (" + cb.a(a3.length) + ")").a(" AND expiration_timestamp_seconds > ?").a(" ORDER BY received_timestamp_millis ASC").a(" LIMIT ?").a(a2).a(a3).a(strArr).a();
                Logger.v(Logger.PREPARE_TAG, "built query: " + a4.a());
                a = this.a.a(a4);
                try {
                    dn<?> dnVar;
                    int count = a.getCount();
                    switch (count) {
                        case TwitterResponse.NONE /*0*/:
                            Logger.d(Logger.PREPARE_TAG, "no record found for " + a4.a());
                            dnVar = null;
                            break;
                        case TwitterResponse.READ /*1*/:
                            if (!a.moveToFirst()) {
                                dnVar = null;
                                break;
                            }
                            com.vungle.publisher.cj.a j_ = this.c.a(this.b.a(a, MoatAdEvent.EVENT_TYPE)).j_();
                            dnVar = (dn) j_.a((cj) j_.c_(), a, true);
                            break;
                        default:
                            throw new SQLException("fetched " + count);
                    }
                    if (a == null) {
                        return dnVar;
                    }
                    a.close();
                    return dnVar;
                } catch (Exception e2) {
                    e = e2;
                    try {
                        this.d.a(Logger.PREPARE_TAG, "could not get next ad by status", e);
                        if (a != null) {
                            return null;
                        }
                        a.close();
                        return null;
                    } catch (Throwable th) {
                        e = th;
                        cursor = a;
                        if (cursor != null) {
                            cursor.close();
                        }
                        throw e;
                    }
                }
            } catch (Exception e3) {
                e = e3;
                a = null;
                this.d.a(Logger.PREPARE_TAG, "could not get next ad by status", e);
                if (a != null) {
                    return null;
                }
                a.close();
                return null;
            } catch (Throwable th2) {
                e = th2;
                if (cursor != null) {
                    cursor.close();
                }
                throw e;
            }
        }
    }

    @Singleton
    /* compiled from: vungle */
    public static class c {
        @Inject
        cf a;
        @Inject
        com.vungle.publisher.j.a b;
        @Inject
        com.vungle.publisher.gm.a c;
        @Inject
        agt d;

        @Inject
        c() {
        }
    }

    public final int a(List<A> list) {
        if (list == null || list.size() <= 0) {
            return 0;
        }
        if (Logger.isLoggable(3)) {
            StringBuilder stringBuilder = new StringBuilder("deleting ");
            int i = 1;
            for (A a : list) {
                if (i != 0) {
                    i = 0;
                } else {
                    stringBuilder.append(", ");
                }
                stringBuilder.append(a.z());
            }
            Logger.d(Logger.PREPARE_TAG, stringBuilder.toString());
        }
        this.d.a(b(list), com.vungle.publisher.cj.c.deleting);
        return this.d.b();
    }

    private static List<A> b(List<A> list) {
        List<A> arrayList = new ArrayList();
        for (A a : list) {
            int i = a.k;
            a.k = i + 1;
            if (a.b() || i >= 3) {
                arrayList.add(a);
            } else {
                Logger.w(Logger.PREPARE_TAG, "unable to delete files for " + a.z() + " attempt " + i);
                a.b_();
            }
        }
        return arrayList;
    }

    protected final int a() {
        Logger.d(Logger.PREPARE_TAG, "deleting expired " + this.d.a() + " ad records without pending reports");
        return a(this.d.a("type = ? AND expiration_timestamp_seconds <= ?", new String[]{r0.toString(), String.valueOf(System.currentTimeMillis() / 1000)}, null));
    }

    protected final int b() {
        List a = this.d.a("type = ? AND " + cj.b + " ORDER BY insert_timestamp_millis DESC LIMIT ? OFFSET ?", new String[]{this.d.a().toString(), Integer.toString(Integer.MAX_VALUE), Integer.toString(4)}, null);
        Logger.d(Logger.PREPARE_TAG, "deleting " + a.size() + " extra " + this.d.a() + " ad records to reach target size 4");
        return a(a);
    }

    protected final int c() {
        List a = this.d.a("type = ?", new String[]{this.d.a().toString()}, null);
        Logger.d(Logger.PREPARE_TAG, "deleting " + a.size() + " " + this.d.a() + " ad records");
        return a(a);
    }
}
