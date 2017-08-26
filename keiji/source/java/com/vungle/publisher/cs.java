package com.vungle.publisher;

import android.content.ContentValues;
import android.database.Cursor;
import com.unity3d.ads.metadata.MediationMetaData;
import com.vungle.log.Logger;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: vungle */
public final class cs extends dl<Integer> {
    Integer a;
    public String b;
    public String c;
    @Inject
    a d;

    @Singleton
    /* compiled from: vungle */
    public static class a extends com.vungle.publisher.dl.a<cs, Integer> {
        @Inject
        Provider<cs> a;
        @Inject
        com.vungle.publisher.ej.a b;

        protected final /* synthetic */ dl a(dl dlVar, Cursor cursor) {
            cs csVar = (cs) dlVar;
            csVar.t = cb.d(cursor, "id");
            csVar.a = cb.d(cursor, "ad_report_id");
            csVar.b = cb.f(cursor, MediationMetaData.KEY_NAME);
            csVar.c = cb.f(cursor, "value");
            return csVar;
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

        public final int a(Integer num) {
            int delete = this.d.getWritableDatabase().delete("ad_report_extra", "ad_report_id = ?", new String[]{String.valueOf(num)});
            Logger.v(Logger.DATABASE_TAG, "deleted " + delete + " ad_report_extra records for adReportId: " + num);
            return delete;
        }

        final ej b(Integer num) {
            Throwable th;
            Cursor cursor = null;
            if (num == null) {
                Logger.w(Logger.DATABASE_TAG, "failed to fetch ad_report_extra records by ad_report_id " + num);
                return null;
            }
            try {
                Logger.d(Logger.DATABASE_TAG, "fetching ad_report_extra records by ad_report_id " + num);
                Cursor query = this.d.getReadableDatabase().query("ad_report_extra", null, "ad_report_id = ?", new String[]{String.valueOf(num)}, null, null, null);
                try {
                    ej ejVar;
                    int count = query.getCount();
                    Logger.v(Logger.DATABASE_TAG, count + " ad_report_extra for ad_report_id " + num);
                    if (count > 0) {
                        ejVar = new ej();
                        while (query.moveToNext()) {
                            dl a = a();
                            b(a, query);
                            ejVar.put(a.b, a);
                        }
                    } else {
                        ejVar = null;
                    }
                    if (query == null) {
                        return ejVar;
                    }
                    query.close();
                    return ejVar;
                } catch (Throwable th2) {
                    th = th2;
                    cursor = query;
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                if (cursor != null) {
                    cursor.close();
                }
                throw th;
            }
        }

        protected final String c() {
            return "ad_report_extra";
        }

        final cs a() {
            return (cs) this.a.get();
        }
    }

    protected final /* bridge */ /* synthetic */ com.vungle.publisher.dl.a a_() {
        return this.d;
    }

    @Inject
    cs() {
    }

    protected final String c() {
        return "ad_report_extra";
    }

    protected final ContentValues a(boolean z) {
        ContentValues contentValues = new ContentValues();
        if (z) {
            contentValues.put("ad_report_id", this.a);
        }
        contentValues.put(MediationMetaData.KEY_NAME, this.b);
        contentValues.put("value", this.c);
        return contentValues;
    }

    protected final StringBuilder m() {
        StringBuilder m = super.m();
        dl.a(m, "ad_report_id", this.a, false);
        dl.a(m, MediationMetaData.KEY_NAME, this.b, false);
        dl.a(m, "value", this.c, false);
        return m;
    }
}
