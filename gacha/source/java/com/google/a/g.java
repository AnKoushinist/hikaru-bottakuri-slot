package com.google.a;

import com.google.a.b.d;
import com.google.a.c.a;
import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.cocos2dx.lib.BuildConfig;

/* compiled from: GsonBuilder */
public final class g {
    private d a = d.a;
    private u b = u.DEFAULT;
    private e c = d.IDENTITY;
    private final Map<Type, h<?>> d = new HashMap();
    private final List<x> e = new ArrayList();
    private final List<x> f = new ArrayList();
    private boolean g = false;
    private String h;
    private int i = 2;
    private int j = 2;
    private boolean k = false;
    private boolean l = false;
    private boolean m = true;
    private boolean n = false;
    private boolean o = false;
    private boolean p = false;

    public g a() {
        this.a = this.a.b();
        return this;
    }

    public f b() {
        List arrayList = new ArrayList();
        arrayList.addAll(this.e);
        Collections.reverse(arrayList);
        arrayList.addAll(this.f);
        a(this.h, this.i, this.j, arrayList);
        return new f(this.a, this.c, this.d, this.g, this.k, this.o, this.m, this.n, this.p, this.l, this.b, arrayList);
    }

    private void a(String str, int i, int i2, List<x> list) {
        Object aVar;
        if (str != null && !BuildConfig.FLAVOR.equals(str.trim())) {
            aVar = new a(str);
        } else if (i != 2 && i2 != 2) {
            aVar = new a(i, i2);
        } else {
            return;
        }
        list.add(v.a(a.get(Date.class), aVar));
        list.add(v.a(a.get(Timestamp.class), aVar));
        list.add(v.a(a.get(java.sql.Date.class), aVar));
    }
}
