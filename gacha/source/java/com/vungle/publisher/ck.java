package com.vungle.publisher;

import android.content.ContentValues;
import android.database.Cursor;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: vungle */
public final class ck extends dl<Integer> {
    public String a;
    public Integer b;
    @Inject
    a c;

    @Singleton
    /* compiled from: vungle */
    public static class a extends com.vungle.publisher.dl.a<ck, Integer> {
        @Inject
        Provider<ck> a;

        protected final /* synthetic */ dl a(dl dlVar, Cursor cursor) {
            ck ckVar = (ck) dlVar;
            ckVar.a = cb.f(cursor, "code");
            ckVar.b = cb.d(cursor, "report_id");
            return ckVar;
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

        protected final String c() {
            return "errors";
        }

        public final List<ck> a(Integer num) {
            return super.a("report_id = ?", new String[]{num.toString()});
        }

        public final ck a() {
            return (ck) this.a.get();
        }
    }

    protected final /* bridge */ /* synthetic */ com.vungle.publisher.dl.a a_() {
        return this.c;
    }

    @Inject
    ck() {
    }

    protected final String c() {
        return "errors";
    }

    protected final ContentValues a(boolean z) {
        ContentValues contentValues = new ContentValues();
        if (z) {
            contentValues.put("id", (Integer) this.t);
            contentValues.put("report_id", this.b);
        }
        contentValues.put("code", this.a);
        return contentValues;
    }

    public final String toString() {
        return this.a;
    }
}
