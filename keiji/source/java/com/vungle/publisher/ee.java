package com.vungle.publisher;

import android.content.ContentValues;
import android.database.Cursor;
import com.tapjoy.TJAdUnitConstants.String;
import com.tapjoy.TapjoyConstants;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: vungle */
public final class ee extends dl<Integer> {
    public String a;
    public String b;
    public ji c;
    public long d;
    public Integer e;
    public Long f;
    public String g;
    @Inject
    a h;

    @Singleton
    /* compiled from: vungle */
    public static class a extends com.vungle.publisher.dl.a<ee, Integer> {
        @Inject
        Provider<ee> a;

        protected final /* synthetic */ dl a(dl dlVar, Cursor cursor) {
            ee eeVar = (ee) dlVar;
            eeVar.a = cb.f(cursor, "ad_id");
            eeVar.b = cb.f(cursor, "delivery_id");
            eeVar.c = (ji) cb.a(cursor, TapjoyConstants.TJC_SDK_TYPE_DEFAULT, com.vungle.publisher.jl.a.class);
            eeVar.t = cb.d(cursor, "id");
            eeVar.d = cb.e(cursor, "insert_timestamp_millis").longValue();
            eeVar.e = cb.d(cursor, "response_code");
            eeVar.f = cb.e(cursor, "response_timestamp_millis");
            eeVar.g = cb.f(cursor, String.URL);
            return eeVar;
        }

        protected final /* bridge */ /* synthetic */ Object[] b(int i) {
            return new Integer[i];
        }

        public final /* bridge */ /* synthetic */ List c(int i) {
            return super.c(i);
        }

        protected final /* synthetic */ dl c_() {
            return (ee) this.a.get();
        }

        public final /* bridge */ /* synthetic */ List d() {
            return super.d();
        }

        @Inject
        a() {
        }

        protected final String c() {
            return "event_tracking_http_log";
        }
    }

    protected final /* bridge */ /* synthetic */ com.vungle.publisher.dl.a a_() {
        return this.h;
    }

    @Inject
    ee() {
    }

    protected final String c() {
        return "event_tracking_http_log";
    }

    protected final ContentValues a(boolean z) {
        ContentValues contentValues = new ContentValues();
        if (z) {
            contentValues.put("id", (Integer) w());
            long currentTimeMillis = System.currentTimeMillis();
            this.d = currentTimeMillis;
            contentValues.put("insert_timestamp_millis", Long.valueOf(currentTimeMillis));
        }
        contentValues.put("ad_id", this.a);
        contentValues.put("delivery_id", this.b);
        contentValues.put(TapjoyConstants.TJC_SDK_TYPE_DEFAULT, this.c.toString());
        contentValues.put("response_code", this.e);
        contentValues.put("response_timestamp_millis", this.f);
        contentValues.put(String.URL, this.g);
        return contentValues;
    }

    protected final StringBuilder m() {
        StringBuilder m = super.m();
        dl.a(m, "ad_id", this.a, false);
        dl.a(m, "delivery_id", this.b, false);
        dl.a(m, TapjoyConstants.TJC_SDK_TYPE_DEFAULT, this.c, false);
        dl.a(m, "response_code", this.e, false);
        dl.a(m, "response_timestamp_millis", this.f, false);
        dl.a(m, String.URL, this.g, false);
        dl.a(m, "insert_timestamp_millis", Long.valueOf(this.d), false);
        return m;
    }
}
