package com.vungle.publisher;

import android.content.Context;
import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;
import javax.inject.Provider;

/* compiled from: vungle */
public final class cg implements c<cf> {
    static final /* synthetic */ boolean a = (!cg.class.desiredAssertionStatus());
    private final MembersInjector<cf> b;
    private final Provider<Context> c;

    public final /* synthetic */ Object get() {
        return (cf) d.a(this.b, new cf((Context) this.c.get()));
    }

    private cg(MembersInjector<cf> membersInjector, Provider<Context> provider) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            if (a || provider != null) {
                this.c = provider;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static c<cf> a(MembersInjector<cf> membersInjector, Provider<Context> provider) {
        return new cg(membersInjector, provider);
    }
}
