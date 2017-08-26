package com.vungle.publisher;

import com.vungle.publisher.gm.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class jx implements MembersInjector<jt> {
    static final /* synthetic */ boolean a = (!jx.class.desiredAssertionStatus());
    private final Provider<cf> b;
    private final Provider<a> c;
    private final Provider<db.a> d;
    private final Provider<gh> e;
    private final Provider<js.a> f;
    private final Provider<jt.a> g;

    public final /* synthetic */ void injectMembers(Object obj) {
        jt jtVar = (jt) obj;
        if (jtVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        jtVar.u = (cf) this.b.get();
        jtVar.e = (a) this.c.get();
        jtVar.f = (db.a) this.d.get();
        jtVar.g = (gh) this.e.get();
        jtVar.h = (js.a) this.f.get();
        jtVar.i = (jt.a) this.g.get();
    }

    private jx(Provider<cf> provider, Provider<a> provider2, Provider<db.a> provider3, Provider<gh> provider4, Provider<js.a> provider5, Provider<jt.a> provider6) {
        if (a || provider != null) {
            this.b = provider;
            if (a || provider2 != null) {
                this.c = provider2;
                if (a || provider3 != null) {
                    this.d = provider3;
                    if (a || provider4 != null) {
                        this.e = provider4;
                        if (a || provider5 != null) {
                            this.f = provider5;
                            if (a || provider6 != null) {
                                this.g = provider6;
                                return;
                            }
                            throw new AssertionError();
                        }
                        throw new AssertionError();
                    }
                    throw new AssertionError();
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<jt> a(Provider<cf> provider, Provider<a> provider2, Provider<db.a> provider3, Provider<gh> provider4, Provider<js.a> provider5, Provider<jt.a> provider6) {
        return new jx(provider, provider2, provider3, provider4, provider5, provider6);
    }
}
