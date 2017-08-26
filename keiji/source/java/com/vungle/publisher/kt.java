package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class kt implements c<kn> {
    static final /* synthetic */ boolean a = (!kt.class.desiredAssertionStatus());
    private final MembersInjector<kn> b;

    public final /* synthetic */ Object get() {
        return (kn) d.a(this.b, new kn());
    }

    private kt(MembersInjector<kn> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<kn> a(MembersInjector<kn> membersInjector) {
        return new kt(membersInjector);
    }
}
