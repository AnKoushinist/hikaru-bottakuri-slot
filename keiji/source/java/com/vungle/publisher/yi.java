package com.vungle.publisher;

import com.vungle.publisher.yg.b;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class yi implements MembersInjector<b> {
    static final /* synthetic */ boolean a = (!yi.class.desiredAssertionStatus());
    private final Provider<a> b;
    private final Provider<a> c;
    private final Provider<a> d;
    private final Provider<a> e;

    public final /* synthetic */ void injectMembers(Object obj) {
        b bVar = (b) obj;
        if (bVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        bVar.a = (a) this.b.get();
        bVar.b = (a) this.c.get();
        bVar.c = (a) this.d.get();
        bVar.d = (a) this.e.get();
    }

    private yi(Provider<a> provider, Provider<a> provider2, Provider<a> provider3, Provider<a> provider4) {
        if (a || provider != null) {
            this.b = provider;
            if (a || provider2 != null) {
                this.c = provider2;
                if (a || provider3 != null) {
                    this.d = provider3;
                    if (a || provider4 != null) {
                        this.e = provider4;
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

    public static MembersInjector<b> a(Provider<a> provider, Provider<a> provider2, Provider<a> provider3, Provider<a> provider4) {
        return new yi(provider, provider2, provider3, provider4);
    }
}
