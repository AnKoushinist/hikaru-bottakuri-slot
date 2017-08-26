package com.vungle.sdk;

import com.vungle.publisher.FullScreenAdActivity_MembersInjector;
import com.vungle.publisher.bw;
import com.vungle.publisher.cj.b;
import com.vungle.publisher.gm.a;
import com.vungle.publisher.lr;
import com.vungle.publisher.ne;
import com.vungle.publisher.py;
import com.vungle.publisher.q;
import com.vungle.publisher.ql;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class VungleAdvert_MembersInjector implements MembersInjector<VungleAdvert> {
    static final /* synthetic */ boolean a = (!VungleAdvert_MembersInjector.class.desiredAssertionStatus());
    private final Provider<ql> b;
    private final Provider<bw> c;
    private final Provider<py> d;
    private final Provider<b> e;
    private final Provider<lr> f;
    private final Provider<a> g;
    private final Provider<ne.a> h;
    private final Provider<q> i;

    public VungleAdvert_MembersInjector(Provider<ql> provider, Provider<bw> provider2, Provider<py> provider3, Provider<b> provider4, Provider<lr> provider5, Provider<a> provider6, Provider<ne.a> provider7, Provider<q> provider8) {
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

    public static MembersInjector<VungleAdvert> create(Provider<ql> provider, Provider<bw> provider2, Provider<py> provider3, Provider<b> provider4, Provider<lr> provider5, Provider<a> provider6, Provider<ne.a> provider7, Provider<q> provider8) {
        return new VungleAdvert_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8);
    }

    public final void injectMembers(VungleAdvert vungleAdvert) {
        if (vungleAdvert == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        FullScreenAdActivity_MembersInjector.injectEventBus(vungleAdvert, this.b);
        FullScreenAdActivity_MembersInjector.injectUiExecutor(vungleAdvert, this.c);
        FullScreenAdActivity_MembersInjector.injectSdkState(vungleAdvert, this.d);
        FullScreenAdActivity_MembersInjector.injectAdMediator(vungleAdvert, this.e);
        FullScreenAdActivity_MembersInjector.injectAudioHelper(vungleAdvert, this.f);
        FullScreenAdActivity_MembersInjector.injectLoggedExceptionFactory(vungleAdvert, this.g);
        FullScreenAdActivity_MembersInjector.injectAdPresenterMediator(vungleAdvert, this.h);
        FullScreenAdActivity_MembersInjector.injectAdConfigFactory(vungleAdvert, this.i);
    }
}
