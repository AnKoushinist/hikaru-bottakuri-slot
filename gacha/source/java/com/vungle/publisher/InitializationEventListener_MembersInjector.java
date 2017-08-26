package com.vungle.publisher;

import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class InitializationEventListener_MembersInjector implements MembersInjector<InitializationEventListener> {
    static final /* synthetic */ boolean a = (!InitializationEventListener_MembersInjector.class.desiredAssertionStatus());
    private final Provider<ql> b;
    private final Provider<a> c;
    private final Provider<bt> d;
    private final Provider<yc> e;
    private final Provider<afy> f;
    private final Provider<b> g;
    private final Provider<a> h;
    private final Provider<py> i;
    private final Provider<pn> j;

    public InitializationEventListener_MembersInjector(Provider<ql> provider, Provider<a> provider2, Provider<bt> provider3, Provider<yc> provider4, Provider<afy> provider5, Provider<b> provider6, Provider<a> provider7, Provider<py> provider8, Provider<pn> provider9) {
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

    public static MembersInjector<InitializationEventListener> create(Provider<ql> provider, Provider<a> provider2, Provider<bt> provider3, Provider<yc> provider4, Provider<afy> provider5, Provider<b> provider6, Provider<a> provider7, Provider<py> provider8, Provider<pn> provider9) {
        return new InitializationEventListener_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9);
    }

    public final void injectMembers(InitializationEventListener initializationEventListener) {
        if (initializationEventListener == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        initializationEventListener.eventBus = (ql) this.b.get();
        initializationEventListener.a = (a) this.c.get();
        initializationEventListener.b = (bt) this.d.get();
        initializationEventListener.c = (yc) this.e.get();
        initializationEventListener.d = (afy) this.f.get();
        initializationEventListener.e = (b) this.g.get();
        initializationEventListener.f = (a) this.h.get();
        initializationEventListener.g = (py) this.i.get();
        initializationEventListener.h = (pn) this.j.get();
    }

    public static void injectAdManager(InitializationEventListener initializationEventListener, Provider<a> provider) {
        initializationEventListener.a = (a) provider.get();
    }

    public static void injectExecutor(InitializationEventListener initializationEventListener, Provider<bt> provider) {
        initializationEventListener.b = (bt) provider.get();
    }

    public static void injectProtocolHttpGateway(InitializationEventListener initializationEventListener, Provider<yc> provider) {
        initializationEventListener.c = (yc) provider.get();
    }

    public static void injectReportManager(InitializationEventListener initializationEventListener, Provider<afy> provider) {
        initializationEventListener.d = (afy) provider.get();
    }

    public static void injectInitialConfigUpdatedEventListener(InitializationEventListener initializationEventListener, Provider<b> provider) {
        initializationEventListener.e = (b) provider.get();
    }

    public static void injectGlobalEventListener(InitializationEventListener initializationEventListener, Provider<a> provider) {
        initializationEventListener.f = (a) provider.get();
    }

    public static void injectSdkState(InitializationEventListener initializationEventListener, Provider<py> provider) {
        initializationEventListener.g = (py) provider.get();
    }

    public static void injectDevice(InitializationEventListener initializationEventListener, Provider<pn> provider) {
        initializationEventListener.h = (pn) provider.get();
    }
}
