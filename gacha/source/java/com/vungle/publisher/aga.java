package com.vungle.publisher;

import com.vungle.publisher.cq.b;
import com.vungle.publisher.fj.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class aga implements MembersInjector<afy> {
    static final /* synthetic */ boolean a = (!aga.class.desiredAssertionStatus());
    private final Provider<ql> b;
    private final Provider<b> c;
    private final Provider<a> d;
    private final Provider<kn.a> e;
    private final Provider<hd.a> f;
    private final Provider<yc> g;
    private final Provider<py> h;
    private final Provider<if.a> i;
    private final Provider<gm.a> j;

    public final /* synthetic */ void injectMembers(Object obj) {
        afy com_vungle_publisher_afy = (afy) obj;
        if (com_vungle_publisher_afy == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        com_vungle_publisher_afy.a = (ql) this.b.get();
        com_vungle_publisher_afy.b = (b) this.c.get();
        com_vungle_publisher_afy.c = (a) this.d.get();
        com_vungle_publisher_afy.d = (kn.a) this.e.get();
        com_vungle_publisher_afy.e = (hd.a) this.f.get();
        com_vungle_publisher_afy.f = (yc) this.g.get();
        com_vungle_publisher_afy.g = (py) this.h.get();
        com_vungle_publisher_afy.h = (if.a) this.i.get();
        com_vungle_publisher_afy.i = (gm.a) this.j.get();
    }

    private aga(Provider<ql> provider, Provider<b> provider2, Provider<a> provider3, Provider<kn.a> provider4, Provider<hd.a> provider5, Provider<yc> provider6, Provider<py> provider7, Provider<if.a> provider8, Provider<gm.a> provider9) {
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
                                if (a || provider7 != null) {
                                    this.h = provider7;
                                    if (a || provider8 != null) {
                                        this.i = provider8;
                                        if (a || provider9 != null) {
                                            this.j = provider9;
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
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<afy> a(Provider<ql> provider, Provider<b> provider2, Provider<a> provider3, Provider<kn.a> provider4, Provider<hd.a> provider5, Provider<yc> provider6, Provider<py> provider7, Provider<if.a> provider8, Provider<gm.a> provider9) {
        return new aga(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9);
    }
}
