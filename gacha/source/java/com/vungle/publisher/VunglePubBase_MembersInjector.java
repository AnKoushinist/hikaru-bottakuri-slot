package com.vungle.publisher;

import android.content.Context;
import com.vungle.publisher.mt.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class VunglePubBase_MembersInjector implements MembersInjector<VunglePubBase> {
    static final /* synthetic */ boolean a = (!VunglePubBase_MembersInjector.class.desiredAssertionStatus());
    private final Provider<a> b;
    private final Provider<InitializationEventListener> c;
    private final Provider<qu> d;
    private final Provider<cf> e;
    private final Provider<Demographic> f;
    private final Provider<pn> g;
    private final Provider<ql> h;
    private final Provider<AdConfig> i;
    private final Provider<q> j;
    private final Provider<pv> k;
    private final Provider<py> l;
    private final Provider<pp> m;
    private final Provider<Context> n;
    private final Provider<a> o;

    public VunglePubBase_MembersInjector(Provider<a> provider, Provider<InitializationEventListener> provider2, Provider<qu> provider3, Provider<cf> provider4, Provider<Demographic> provider5, Provider<pn> provider6, Provider<ql> provider7, Provider<AdConfig> provider8, Provider<q> provider9, Provider<pv> provider10, Provider<py> provider11, Provider<pp> provider12, Provider<Context> provider13, Provider<a> provider14) {
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
                                                    if (a || provider12 != null) {
                                                        this.m = provider12;
                                                        if (a || provider13 != null) {
                                                            this.n = provider13;
                                                            if (a || provider14 != null) {
                                                                this.o = provider14;
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
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<VunglePubBase> create(Provider<a> provider, Provider<InitializationEventListener> provider2, Provider<qu> provider3, Provider<cf> provider4, Provider<Demographic> provider5, Provider<pn> provider6, Provider<ql> provider7, Provider<AdConfig> provider8, Provider<q> provider9, Provider<pv> provider10, Provider<py> provider11, Provider<pp> provider12, Provider<Context> provider13, Provider<a> provider14) {
        return new VunglePubBase_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12, provider13, provider14);
    }

    public final void injectMembers(VunglePubBase vunglePubBase) {
        if (vunglePubBase == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        vunglePubBase.a = (a) this.b.get();
        vunglePubBase.b = (InitializationEventListener) this.c.get();
        vunglePubBase.c = (qu) this.d.get();
        vunglePubBase.d = (cf) this.e.get();
        vunglePubBase.e = (Demographic) this.f.get();
        vunglePubBase.f = (pn) this.g.get();
        vunglePubBase.g = (ql) this.h.get();
        vunglePubBase.h = (AdConfig) this.i.get();
        vunglePubBase.i = (q) this.j.get();
        vunglePubBase.j = (pv) this.k.get();
        vunglePubBase.k = (py) this.l.get();
        vunglePubBase.l = (pp) this.m.get();
        vunglePubBase.m = (Context) this.n.get();
        vunglePubBase.n = (a) this.o.get();
    }

    public static void injectAdManager(VunglePubBase vunglePubBase, Provider<a> provider) {
        vunglePubBase.a = (a) provider.get();
    }

    public static void injectInitializationEventListener(VunglePubBase vunglePubBase, Provider<InitializationEventListener> provider) {
        vunglePubBase.b = (InitializationEventListener) provider.get();
    }

    public static void injectCacheManager(VunglePubBase vunglePubBase, Provider<qu> provider) {
        vunglePubBase.c = (qu) provider.get();
    }

    public static void injectDatabaseHelper(VunglePubBase vunglePubBase, Provider<cf> provider) {
        vunglePubBase.d = (cf) provider.get();
    }

    public static void injectDemographic(VunglePubBase vunglePubBase, Provider<Demographic> provider) {
        vunglePubBase.e = (Demographic) provider.get();
    }

    public static void injectDevice(VunglePubBase vunglePubBase, Provider<pn> provider) {
        vunglePubBase.f = (pn) provider.get();
    }

    public static void injectEventBus(VunglePubBase vunglePubBase, Provider<ql> provider) {
        vunglePubBase.g = (ql) provider.get();
    }

    public static void injectGlobalAdConfig(VunglePubBase vunglePubBase, Provider<AdConfig> provider) {
        vunglePubBase.h = (AdConfig) provider.get();
    }

    public static void injectSafeBundleAdConfigFactory(VunglePubBase vunglePubBase, Provider<q> provider) {
        vunglePubBase.i = (q) provider.get();
    }

    public static void injectSdkConfig(VunglePubBase vunglePubBase, Provider<pv> provider) {
        vunglePubBase.j = (pv) provider.get();
    }

    public static void injectSdkState(VunglePubBase vunglePubBase, Provider<py> provider) {
        vunglePubBase.k = (py) provider.get();
    }

    public static void injectInterstitialAdState(VunglePubBase vunglePubBase, Provider<pp> provider) {
        vunglePubBase.l = (pp) provider.get();
    }

    public static void injectContext(VunglePubBase vunglePubBase, Provider<Context> provider) {
        vunglePubBase.m = (Context) provider.get();
    }

    public static void injectDummyWebViewFactory(VunglePubBase vunglePubBase, Provider<a> provider) {
        vunglePubBase.n = (a) provider.get();
    }
}
