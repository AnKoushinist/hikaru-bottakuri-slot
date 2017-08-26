package com.vungle.publisher;

import android.content.ContentValues;
import android.database.Cursor;
import com.tapjoy.TapjoyConstants;
import java.util.List;

/* compiled from: vungle */
public abstract class cr<P extends cp<?, P, ?>> extends dl<Integer> {
    P a;
    public ji b;
    public long c;
    public String d;
    private String e;

    /* compiled from: vungle */
    public static abstract class a<P extends cp<?, P, E>, E extends cr<P>> extends com.vungle.publisher.dl.a<E, Integer> {
        protected abstract ji a(Cursor cursor);

        protected final /* synthetic */ dl a(dl dlVar, Cursor cursor) {
            cr crVar = (cr) dlVar;
            crVar.b = a(cursor);
            crVar.t = cb.d(cursor, "id");
            crVar.c = cb.e(cursor, "insert_timestamp_millis").longValue();
            crVar.d = cb.f(cursor, "value");
            return crVar;
        }

        protected final /* bridge */ /* synthetic */ Object[] b(int i) {
            return new Integer[i];
        }

        protected a() {
        }

        protected final E a(P p, ji jiVar, Object obj) {
            if (p == null) {
                throw new IllegalArgumentException("null ad_play");
            } else if (jiVar == null) {
                throw new IllegalArgumentException("null event");
            } else {
                cr crVar = (cr) c_();
                crVar.a = p;
                crVar.b = jiVar;
                crVar.d = obj == null ? null : obj.toString();
                return crVar;
            }
        }

        protected final List<E> a(P p) {
            if (p == null) {
                throw new IllegalArgumentException("null ad_play");
            }
            if (((Integer) p.w()) == null) {
                throw new IllegalArgumentException("null play_id");
            }
            List<E> a = a("play_id = ?", new String[]{((Integer) p.w()).toString()}, "insert_timestamp_millis ASC");
            for (E e : a) {
                e.a = p;
            }
            return a;
        }

        protected final String c() {
            return "ad_report_event";
        }
    }

    protected cr() {
    }

    private Integer e() {
        return this.a == null ? null : (Integer) this.a.w();
    }

    protected final String c() {
        return "ad_report_event";
    }

    protected final ContentValues a(boolean z) {
        ContentValues contentValues = new ContentValues();
        if (z) {
            long currentTimeMillis = System.currentTimeMillis();
            this.c = currentTimeMillis;
            contentValues.put("insert_timestamp_millis", Long.valueOf(currentTimeMillis));
            contentValues.put("play_id", e());
            contentValues.put(TapjoyConstants.TJC_SDK_TYPE_DEFAULT, this.b.toString());
            contentValues.put("value", this.d);
        }
        return contentValues;
    }

    public String toString() {
        String str = this.e;
        if (str != null) {
            return str;
        }
        str = super.toString();
        this.e = str;
        return str;
    }

    protected final StringBuilder m() {
        StringBuilder m = super.m();
        dl.a(m, "play_id", e(), false);
        dl.a(m, TapjoyConstants.TJC_SDK_TYPE_DEFAULT, this.b, false);
        dl.a(m, "insert_timestamp_millis", Long.valueOf(this.c), false);
        dl.a(m, "value", this.d, false);
        return m;
    }
}
