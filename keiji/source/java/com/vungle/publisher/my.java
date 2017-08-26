package com.vungle.publisher;

import android.content.Context;
import com.vungle.publisher.mt.b;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class my<W extends mt> implements MembersInjector<b<W>> {
    static final /* synthetic */ boolean a = (!my.class.desiredAssertionStatus());
    private final Provider<Context> b;
    private final Provider<pn> c;
    private final Provider<ql> d;

    public final /* synthetic */ void injectMembers(Object obj) {
        b bVar = (b) obj;
        if (bVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        bVar.a = (Context) this.b.get();
        bVar.b = (pn) this.c.get();
        bVar.c = (ql) this.d.get();
    }

    public static <W extends mt> void a(b<W> bVar, Provider<Context> provider) {
        bVar.a = (Context) provider.get();
    }

    public static <W extends mt> void b(b<W> bVar, Provider<pn> provider) {
        bVar.b = (pn) provider.get();
    }

    public static <W extends mt> void c(b<W> bVar, Provider<ql> provider) {
        bVar.c = (ql) provider.get();
    }
}
