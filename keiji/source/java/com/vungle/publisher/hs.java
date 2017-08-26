package com.vungle.publisher;

import android.text.TextUtils;
import java.lang.reflect.Array;
import org.cocos2dx.lib.BuildConfig;

/* compiled from: vungle */
public final class hs {
    public final String a;
    public final String[] b;
    public final String c;
    public final String[] d;
    public final String e;
    public final String f;
    public final String g;
    public final String h;

    /* compiled from: vungle */
    public static final class a {
        String a;
        String[] b;
        String c = BuildConfig.FLAVOR;
        String[] d = new String[0];
        String e;
        String f;
        String g;
        String h;

        public final a a(String str) {
            this.c = this.c.concat(str);
            return this;
        }

        public final a a(String[] strArr) {
            Object obj;
            String[][] strArr2 = new String[][]{this.d, strArr};
            Class cls = null;
            int i = 0;
            for (int i2 = 0; i2 < 2; i2++) {
                Object obj2 = strArr2[i2];
                if (obj2 != null) {
                    i += obj2.length;
                    cls = obj2.getClass();
                }
            }
            if (cls != null) {
                obj = (Object[]) Array.newInstance(cls.getComponentType(), i);
                i = 0;
                for (int i3 = 0; i3 < 2; i3++) {
                    Object obj3 = strArr2[i3];
                    if (obj3 != null) {
                        System.arraycopy(obj3, 0, obj, i, obj3.length);
                        i += obj3.length;
                    }
                }
            } else {
                obj = null;
            }
            this.d = (String[]) obj;
            return this;
        }

        public final hs a() {
            return new hs();
        }
    }

    private hs(a aVar) {
        this.a = aVar.a;
        this.b = aVar.b;
        this.c = aVar.c;
        this.d = aVar.d;
        this.e = aVar.e;
        this.f = aVar.f;
        this.g = aVar.g;
        this.h = aVar.h;
    }

    public final String a() {
        String str;
        Object a = ags.a(this.b);
        Object a2 = ags.a(this.d);
        StringBuilder append = new StringBuilder().append(TextUtils.isEmpty(this.a) ? BuildConfig.FLAVOR : "table: " + this.a + "; ").append(TextUtils.isEmpty(a) ? BuildConfig.FLAVOR : "columns: " + a + "; ").append(TextUtils.isEmpty(this.c) ? BuildConfig.FLAVOR : "selection: " + this.c + "; ").append(TextUtils.isEmpty(a2) ? BuildConfig.FLAVOR : "selectionArgs: " + a2 + "; ").append(TextUtils.isEmpty(this.e) ? BuildConfig.FLAVOR : "groupBy: " + this.e + "; ").append(TextUtils.isEmpty(this.f) ? BuildConfig.FLAVOR : "having: " + this.f + "; ").append(TextUtils.isEmpty(this.g) ? BuildConfig.FLAVOR : "orderBy: " + this.g + "; ");
        if (TextUtils.isEmpty(this.h)) {
            str = BuildConfig.FLAVOR;
        } else {
            str = "limit: " + this.h + "; ";
        }
        return append.append(str).toString();
    }
}
