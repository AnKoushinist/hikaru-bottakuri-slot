package com.vungle.publisher;

import com.vungle.publisher.nh.b;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class np implements MembersInjector<b> {
    static final /* synthetic */ boolean a = (!np.class.desiredAssertionStatus());
    private final Provider<ql> b;

    public final /* synthetic */ void injectMembers(Object obj) {
        b bVar = (b) obj;
        if (bVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        bVar.eventBus = (ql) this.b.get();
    }

    private np(Provider<ql> provider) {
        if (a || provider != null) {
            this.b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<b> a(Provider<ql> provider) {
        return new np(provider);
    }
}
