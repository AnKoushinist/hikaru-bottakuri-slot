package com.b.a;

import a.a.a.a.i;
import a.a.a.a.j;
import com.b.a.b.c;
import com.b.a.c.f;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/* compiled from: Crashlytics */
public class a extends i<Void> implements j {
    public final com.b.a.a.a a;
    public final c b;
    public final f c;
    public final Collection<? extends i> d;

    protected /* synthetic */ Object e() {
        return d();
    }

    public a() {
        this(new com.b.a.a.a(), new c(), new f());
    }

    a(com.b.a.a.a aVar, c cVar, f fVar) {
        this.a = aVar;
        this.b = cVar;
        this.c = fVar;
        this.d = Collections.unmodifiableCollection(Arrays.asList(new i[]{aVar, cVar, fVar}));
    }

    public String a() {
        return "2.5.5.97";
    }

    public String b() {
        return "com.crashlytics.sdk.android:crashlytics";
    }

    public Collection<? extends i> c() {
        return this.d;
    }

    protected Void d() {
        return null;
    }
}
