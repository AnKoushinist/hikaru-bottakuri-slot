package com.vungle.publisher;

import com.vungle.publisher.gm.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class tj implements MembersInjector<th> {
    static final /* synthetic */ boolean a = (!tj.class.desiredAssertionStatus());
    private final Provider<nb> b;
    private final Provider<a> c;
    private final Provider<ss> d;
    private final Provider<tp.a> e;

    public final /* synthetic */ void injectMembers(Object obj) {
        th thVar = (th) obj;
        if (thVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        thVar.a = (nb) this.b.get();
        thVar.b = (a) this.c.get();
        thVar.c = (ss) this.d.get();
        thVar.d = (tp.a) this.e.get();
    }

    private tj(Provider<nb> provider, Provider<a> provider2, Provider<ss> provider3, Provider<tp.a> provider4) {
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

    public static MembersInjector<th> a(Provider<nb> provider, Provider<a> provider2, Provider<ss> provider3, Provider<tp.a> provider4) {
        return new tj(provider, provider2, provider3, provider4);
    }
}
