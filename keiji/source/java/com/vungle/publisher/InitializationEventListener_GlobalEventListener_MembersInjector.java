package com.vungle.publisher;

import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class InitializationEventListener_GlobalEventListener_MembersInjector implements MembersInjector<a> {
    static final /* synthetic */ boolean a = (!InitializationEventListener_GlobalEventListener_MembersInjector.class.desiredAssertionStatus());
    private final Provider<ql> b;
    private final Provider<lz> c;

    public InitializationEventListener_GlobalEventListener_MembersInjector(Provider<ql> provider, Provider<lz> provider2) {
        if (a || provider != null) {
            this.b = provider;
            if (a || provider2 != null) {
                this.c = provider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<a> create(Provider<ql> provider, Provider<lz> provider2) {
        return new InitializationEventListener_GlobalEventListener_MembersInjector(provider, provider2);
    }

    public final void injectMembers(a aVar) {
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.eventBus = (ql) this.b.get();
        aVar.a = (lz) this.c.get();
    }

    public static void injectAppFingerprintManager(a aVar, Provider<lz> provider) {
        aVar.a = (lz) provider.get();
    }
}
