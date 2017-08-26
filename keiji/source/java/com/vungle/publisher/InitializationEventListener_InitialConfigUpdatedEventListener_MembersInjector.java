package com.vungle.publisher;

import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class InitializationEventListener_InitialConfigUpdatedEventListener_MembersInjector implements MembersInjector<b> {
    static final /* synthetic */ boolean a = (!InitializationEventListener_InitialConfigUpdatedEventListener_MembersInjector.class.desiredAssertionStatus());
    private final Provider<ql> b;
    private final Provider<qp> c;

    public InitializationEventListener_InitialConfigUpdatedEventListener_MembersInjector(Provider<ql> provider, Provider<qp> provider2) {
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

    public static MembersInjector<b> create(Provider<ql> provider, Provider<qp> provider2) {
        return new InitializationEventListener_InitialConfigUpdatedEventListener_MembersInjector(provider, provider2);
    }

    public final void injectMembers(b bVar) {
        if (bVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        bVar.eventBus = (ql) this.b.get();
        bVar.a = (qp) this.c.get();
    }

    public static void injectExceptionManager(b bVar, Provider<qp> provider) {
        bVar.a = (qp) provider.get();
    }
}
