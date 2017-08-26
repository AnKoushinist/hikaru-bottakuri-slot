package com.vungle.publisher;

import android.content.ContentValues;
import android.database.Cursor;
import com.vungle.log.Logger;
import java.util.Arrays;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import org.json.JSONArray;

/* compiled from: vungle */
public final class gm extends dl<Integer> {
    public long a;
    public String[] b;
    public int c;
    public String d;
    public String e;
    public String f;
    public String g;
    public String h;
    public String i;
    @Inject
    a j;

    @Singleton
    /* compiled from: vungle */
    public static class a extends com.vungle.publisher.dl.a<gm, Integer> {
        @Inject
        pn a;
        @Inject
        pv b;
        @Inject
        Provider<gm> c;

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

        public final void a(String str, String str2, Throwable th) {
            a(2, str, str2, th);
        }

        public final void b(String str, String str2, Throwable th) {
            a(1, str, str2, th);
        }

        private void a(int i, String str, String str2, Throwable th) {
            Logger.e(str, str2, th);
            if (!this.b.b()) {
                return;
            }
            if (h() < 100) {
                gm a = a();
                a.d = str;
                a.e = str2;
                a.f = th.getClass().toString();
                a.c = i;
                a.g = this.a.g();
                a.h = VunglePub.VERSION;
                a.i = this.a.p();
                String[] strArr = null;
                if (th != null) {
                    StackTraceElement[] stackTrace = th.getStackTrace();
                    if (stackTrace != null) {
                        String[] strArr2 = new String[stackTrace.length];
                        for (int i2 = 0; i2 < stackTrace.length; i2++) {
                            strArr2[i2] = stackTrace[i2].toString();
                        }
                        strArr = strArr2;
                    }
                }
                a.b = strArr;
                a.v();
                return;
            }
            Logger.w(str, "could not insert logged exception... too many already!");
        }

        protected final String c() {
            return "logged_exceptions";
        }

        private static gm a(gm gmVar, Cursor cursor) {
            gmVar.a = cb.e(cursor, "insert_timestamp_millis").longValue();
            gmVar.c = cb.c(cursor, "type");
            gmVar.d = cb.f(cursor, "tag");
            gmVar.e = cb.f(cursor, "log_message");
            gmVar.f = cb.f(cursor, "class");
            gmVar.g = cb.f(cursor, "android_version");
            gmVar.h = cb.f(cursor, "sdk_version");
            gmVar.i = cb.f(cursor, "play_services_version");
            gmVar.t = cb.d(cursor, "id");
            try {
                String[] strArr;
                String f = cb.f(cursor, "stack_trace");
                if (f == null) {
                    strArr = null;
                } else {
                    JSONArray jSONArray = new JSONArray(f);
                    strArr = new String[jSONArray.length()];
                    for (int i = 0; i < jSONArray.length(); i++) {
                        strArr[i] = jSONArray.getString(i);
                    }
                }
                gmVar.b = strArr;
            } catch (Throwable e) {
                Logger.e(Logger.DATABASE_TAG, "could not parse stack trace string", e);
            }
            return gmVar;
        }

        private gm a() {
            return (gm) this.c.get();
        }
    }

    protected final /* bridge */ /* synthetic */ com.vungle.publisher.dl.a a_() {
        return this.j;
    }

    @Inject
    gm() {
    }

    protected final String c() {
        return "logged_exceptions";
    }

    protected final ContentValues a(boolean z) {
        ContentValues contentValues = new ContentValues();
        if (z) {
            contentValues.put("id", (Integer) w());
            long currentTimeMillis = System.currentTimeMillis();
            this.a = currentTimeMillis;
            contentValues.put("insert_timestamp_millis", Long.valueOf(currentTimeMillis));
        }
        contentValues.put("type", Integer.valueOf(this.c));
        contentValues.put("tag", this.d);
        contentValues.put("log_message", this.e);
        contentValues.put("class", this.f);
        contentValues.put("android_version", this.g);
        contentValues.put("sdk_version", this.h);
        contentValues.put("play_services_version", this.i);
        try {
            String str = "stack_trace";
            String[] strArr = this.b;
            String str2 = null;
            if (strArr != null) {
                str2 = new JSONArray(Arrays.asList(strArr)).toString();
            }
            contentValues.put(str, str2);
        } catch (Throwable e) {
            Logger.e(Logger.DATABASE_TAG, "could not parse stack trace array", e);
        }
        return contentValues;
    }
}
