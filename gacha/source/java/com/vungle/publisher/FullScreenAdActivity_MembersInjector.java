package com.vungle.publisher;

import com.vungle.publisher.cj.b;
import com.vungle.publisher.gm.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class FullScreenAdActivity_MembersInjector implements MembersInjector<FullScreenAdActivity> {
    static final /* synthetic */ boolean a = (!FullScreenAdActivity_MembersInjector.class.desiredAssertionStatus());
    private final Provider<ql> b;
    private final Provider<bw> c;
    private final Provider<py> d;
    private final Provider<b> e;
    private final Provider<lr> f;
    private final Provider<a> g;
    private final Provider<ne.a> h;
    private final Provider<q> i;

    public FullScreenAdActivity_MembersInjector(Provider<ql> provider, Provider<bw> provider2, Provider<py> provider3, Provider<b> provider4, Provider<lr> provider5, Provider<a> provider6, Provider<ne.a> provider7, Provider<q> provider8) {
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

    public static MembersInjector<FullScreenAdActivity> create(Provider<ql> provider, Provider<bw> provider2, Provider<py> provider3, Provider<b> provider4, Provider<lr> provider5, Provider<a> provider6, Provider<ne.a> provider7, Provider<q> provider8) {
        return new FullScreenAdActivity_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8);
    }

    public final void injectMembers(FullScreenAdActivity fullScreenAdActivity) {
        if (fullScreenAdActivity == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        fullScreenAdActivity.b = (ql) this.b.get();
        fullScreenAdActivity.c = (bw) this.c.get();
        fullScreenAdActivity.d = (py) this.d.get();
        fullScreenAdActivity.e = (b) this.e.get();
        fullScreenAdActivity.f = (lr) this.f.get();
        fullScreenAdActivity.g = (a) this.g.get();
        fullScreenAdActivity.h = (ne.a) this.h.get();
        fullScreenAdActivity.i = (q) this.i.get();
    }

    public static void injectEventBus(FullScreenAdActivity fullScreenAdActivity, Provider<ql> provider) {
        fullScreenAdActivity.b = (ql) provider.get();
    }

    public static void injectUiExecutor(FullScreenAdActivity fullScreenAdActivity, Provider<bw> provider) {
        fullScreenAdActivity.c = (bw) provider.get();
    }

    public static void injectSdkState(FullScreenAdActivity fullScreenAdActivity, Provider<py> provider) {
        fullScreenAdActivity.d = (py) provider.get();
    }

    public static void injectAdMediator(FullScreenAdActivity fullScreenAdActivity, Provider<b> provider) {
        fullScreenAdActivity.e = (b) provider.get();
    }

    public static void injectAudioHelper(FullScreenAdActivity fullScreenAdActivity, Provider<lr> provider) {
        fullScreenAdActivity.f = (lr) provider.get();
    }

    public static void injectLoggedExceptionFactory(FullScreenAdActivity fullScreenAdActivity, Provider<a> provider) {
        fullScreenAdActivity.g = (a) provider.get();
    }

    public static void injectAdPresenterMediator(FullScreenAdActivity fullScreenAdActivity, Provider<ne.a> provider) {
        fullScreenAdActivity.h = (ne.a) provider.get();
    }

    public static void injectAdConfigFactory(FullScreenAdActivity fullScreenAdActivity, Provider<q> provider) {
        fullScreenAdActivity.i = (q) provider.get();
    }
}
