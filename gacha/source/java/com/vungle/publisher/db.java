package com.vungle.publisher;

import android.content.ContentValues;
import android.database.Cursor;
import com.vungle.log.Logger;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: vungle */
public final class db extends dl<Integer> {
    em<?, ?> a;
    String b;
    Integer c;
    @Inject
    a d;

    @Singleton
    /* compiled from: vungle */
    public static class a extends com.vungle.publisher.dl.a<db, Integer> {
        @Inject
        Provider<db> a;

        protected final /* synthetic */ dl a(dl dlVar, Cursor cursor) {
            db dbVar = (db) dlVar;
            dbVar.t = cb.d(cursor, "id");
            dbVar.b = cb.f(cursor, "relative_path");
            dbVar.c = cb.d(cursor, "size");
            return dbVar;
        }

        protected final /* bridge */ /* synthetic */ Object[] b(int i) {
            return new Integer[i];
        }

        public final /* bridge */ /* synthetic */ List c(int i) {
            return super.c(i);
        }

        protected final /* synthetic */ dl c_() {
            return a();
        }

        public final /* bridge */ /* synthetic */ List d() {
            return super.d();
        }

        @Inject
        a() {
        }

        final int a(Integer num) {
            if (num == null) {
                throw new IllegalArgumentException("null viewable_id");
            }
            int delete = this.d.getWritableDatabase().delete("archive_entry", "viewable_id = ?", new String[]{String.valueOf(num)});
            Logger.d(Logger.DATABASE_TAG, "deleted " + delete + " archive_entry rows for viewable_id " + num);
            return delete;
        }

        final db[] a(em<?, ?> emVar) {
            Throwable th;
            if (emVar == null) {
                throw new IllegalArgumentException("null archive");
            }
            Integer D = emVar.D();
            if (D == null) {
                throw new IllegalArgumentException("null viewable_id");
            }
            Cursor query;
            try {
                Logger.d(Logger.DATABASE_TAG, "fetching archive_entry records by viewable_id " + D);
                query = this.d.getReadableDatabase().query("archive_entry", null, "viewable_id = ?", new String[]{String.valueOf(D)}, null, null, null);
                try {
                    db[] dbVarArr = new db[query.getCount()];
                    int i = 0;
                    while (query.moveToNext()) {
                        dl a = a();
                        b(a, query);
                        a.a = emVar;
                        dbVarArr[i] = a;
                        Logger.v(Logger.DATABASE_TAG, "fetched " + a);
                        i++;
                    }
                    if (query != null) {
                        query.close();
                    }
                    return dbVarArr;
                } catch (Throwable th2) {
                    th = th2;
                    if (query != null) {
                        query.close();
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                query = null;
                if (query != null) {
                    query.close();
                }
                throw th;
            }
        }

        protected final String c() {
            return "archive_entry";
        }

        final db a() {
            return (db) this.a.get();
        }
    }

    protected final /* bridge */ /* synthetic */ com.vungle.publisher.dl.a a_() {
        return this.d;
    }

    @Inject
    db() {
    }

    protected final String c() {
        return "archive_entry";
    }

    private Integer e() {
        return this.a == null ? null : this.a.D();
    }

    public final int b_() {
        if (this.t != null) {
            return super.b_();
        }
        Integer e = e();
        Logger.d(Logger.DATABASE_TAG, "updating archive_entry by viewable_id " + e + ", relative_path " + this.b);
        int updateWithOnConflict = this.u.getWritableDatabase().updateWithOnConflict("archive_entry", a(false), "viewable_id = ? AND relative_path = ?", new String[]{String.valueOf(e), r6}, 3);
        x();
        return updateWithOnConflict;
    }

    protected final ContentValues a(boolean z) {
        ContentValues contentValues = new ContentValues();
        if (this.t != null) {
            contentValues.put("id", (Integer) this.t);
        }
        contentValues.put("viewable_id", e());
        contentValues.put("relative_path", this.b);
        contentValues.put("size", this.c);
        return contentValues;
    }

    protected final StringBuilder m() {
        StringBuilder m = super.m();
        dl.a(m, "viewable_id", e(), false);
        dl.a(m, "relative_path", this.b, false);
        dl.a(m, "size", this.c, false);
        return m;
    }
}
