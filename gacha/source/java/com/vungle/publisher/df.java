package com.vungle.publisher;

import com.vungle.publisher.db.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class df implements MembersInjector<db> {
    static final /* synthetic */ boolean a = (!df.class.desiredAssertionStatus());
    private final Provider<cf> b;
    private final Provider<a> c;

    public final /* synthetic */ void injectMembers(Object obj) {
        db dbVar = (db) obj;
        if (dbVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        dbVar.u = (cf) this.b.get();
        dbVar.d = (a) this.c.get();
    }

    private df(Provider<cf> provider, Provider<a> provider2) {
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

    public static MembersInjector<db> a(Provider<cf> provider, Provider<a> provider2) {
        return new df(provider, provider2);
    }
}
