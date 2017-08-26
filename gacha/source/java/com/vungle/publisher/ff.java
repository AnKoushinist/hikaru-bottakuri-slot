package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class ff implements c<fe> {
    static final /* synthetic */ boolean a = (!ff.class.desiredAssertionStatus());
    private final MembersInjector<fe> b;

    public final /* synthetic */ Object get() {
        return (fe) d.a(this.b, new fe());
    }

    private ff(MembersInjector<fe> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<fe> a(MembersInjector<fe> membersInjector) {
        return new ff(membersInjector);
    }
}
