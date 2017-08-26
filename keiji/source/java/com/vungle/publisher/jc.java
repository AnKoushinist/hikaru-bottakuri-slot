package com.vungle.publisher;

import android.content.ContentValues;
import android.database.Cursor;
import com.vungle.log.Logger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import org.json.JSONObject;

/* compiled from: vungle */
public final class jc extends dl<Integer> {
    @Inject
    a a;
    String b;
    String c;
    String d;

    @Singleton
    /* compiled from: vungle */
    public static class a extends com.vungle.publisher.dl.a<jc, Integer> {
        @Inject
        com.vungle.publisher.gm.a a;
        @Inject
        Provider<jc> b;

        protected final /* synthetic */ dl a(dl dlVar, Cursor cursor) {
            jc jcVar = (jc) dlVar;
            jcVar.d = cb.f(cursor, "ad_id");
            jcVar.b = cb.f(cursor, "key");
            jcVar.c = cb.f(cursor, "value");
            return jcVar;
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

        public final jc a(String str, String str2, String str3) {
            jc a = a();
            a.d = str;
            a.b = str2;
            a.c = str3;
            return a;
        }

        public final List<jc> a(String str, JSONObject jSONObject) {
            List<jc> arrayList = new ArrayList();
            try {
                Iterator keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String str2 = (String) keys.next();
                    arrayList.add(a(str, str2, jSONObject.getString(str2)));
                }
            } catch (Throwable e) {
                this.a.a(Logger.DATABASE_TAG, "could not create template replacement list", e);
            }
            return arrayList;
        }

        public final b a(String str) {
            return new b(super.a("ad_id = ?", new String[]{str}));
        }

        protected final String c() {
            return "template_replacements";
        }

        private jc a() {
            return (jc) this.b.get();
        }
    }

    /* compiled from: vungle */
    public static class b extends abe {
        private final JSONObject a = new JSONObject();

        public final /* bridge */ /* synthetic */ Object b() {
            return this.a;
        }

        public b(List<jc> list) {
            try {
                for (jc jcVar : list) {
                    this.a.put(jcVar.b, jcVar.c);
                }
            } catch (Throwable e) {
                Logger.e(Logger.PROTOCOL_TAG, "could not parse json", e);
            }
        }

        public final JSONObject a() {
            return this.a;
        }
    }

    protected final /* bridge */ /* synthetic */ com.vungle.publisher.dl.a a_() {
        return this.a;
    }

    @Inject
    jc() {
    }

    protected final String c() {
        return "template_replacements";
    }

    protected final ContentValues a(boolean z) {
        ContentValues contentValues = new ContentValues();
        if (z) {
            contentValues.put("id", (Integer) this.t);
            contentValues.put("ad_id", this.d);
        }
        contentValues.put("key", this.b);
        contentValues.put("value", this.c);
        return contentValues;
    }
}
