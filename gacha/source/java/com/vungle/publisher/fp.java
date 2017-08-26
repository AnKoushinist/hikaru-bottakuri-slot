package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class fp implements c<fj> {
    static final /* synthetic */ boolean a = (!fp.class.desiredAssertionStatus());
    private final MembersInjector<fj> b;

    public final /* synthetic */ Object get() {
        return (fj) d.a(this.b, new fj());
    }

    private fp(MembersInjector<fj> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<fj> a(MembersInjector<fj> membersInjector) {
        return new fp(membersInjector);
    }
}
