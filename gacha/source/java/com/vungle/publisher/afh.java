package com.vungle.publisher;

import com.vungle.publisher.aft.b;
import com.vungle.publisher.zq.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class afh implements MembersInjector<aff> {
    static final /* synthetic */ boolean a = (!afh.class.desiredAssertionStatus());
    private final Provider<a> b;
    private final Provider<xz> c;
    private final Provider<afi> d;
    private final Provider<xw> e;
    private final Provider<xt> f;
    private final Provider<afq.a> g;
    private final Provider<b> h;
    private final Provider<aft.a> i;
    private final Provider<afc> j;
    private final Provider<py> k;
    private final Provider<afo> l;

    public final /* synthetic */ void injectMembers(Object obj) {
        aff com_vungle_publisher_aff = (aff) obj;
        if (com_vungle_publisher_aff == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        com_vungle_publisher_aff.b = (a) this.b.get();
        com_vungle_publisher_aff.c = (xz) this.c.get();
        com_vungle_publisher_aff.d = (afi) this.d.get();
        com_vungle_publisher_aff.e = (xw) this.e.get();
        com_vungle_publisher_aff.f = (xt) this.f.get();
        com_vungle_publisher_aff.g = (afq.a) this.g.get();
        com_vungle_publisher_aff.h = (b) this.h.get();
        com_vungle_publisher_aff.i = (aft.a) this.i.get();
        com_vungle_publisher_aff.j = (afc) this.j.get();
        com_vungle_publisher_aff.k = (py) this.k.get();
        com_vungle_publisher_aff.l = (afo) this.l.get();
    }

    private afh(Provider<a> provider, Provider<xz> provider2, Provider<afi> provider3, Provider<xw> provider4, Provider<xt> provider5, Provider<afq.a> provider6, Provider<b> provider7, Provider<aft.a> provider8, Provider<afc> provider9, Provider<py> provider10, Provider<afo> provider11) {
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
                                            if (a || provider10 != null) {
                                                this.k = provider10;
                                                if (a || provider11 != null) {
                                                    this.l = provider11;
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
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<aff> a(Provider<a> provider, Provider<xz> provider2, Provider<afi> provider3, Provider<xw> provider4, Provider<xt> provider5, Provider<afq.a> provider6, Provider<b> provider7, Provider<aft.a> provider8, Provider<afc> provider9, Provider<py> provider10, Provider<afo> provider11) {
        return new afh(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11);
    }
}
