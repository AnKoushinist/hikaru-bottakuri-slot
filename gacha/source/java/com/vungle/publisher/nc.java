package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class nc implements c<nb> {
    static final /* synthetic */ boolean a = (!nc.class.desiredAssertionStatus());
    private final MembersInjector<nb> b;

    public final /* synthetic */ Object get() {
        return (nb) d.a(this.b, new nb());
    }

    private nc(MembersInjector<nb> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<nb> a(MembersInjector<nb> membersInjector) {
        return new nc(membersInjector);
    }
}
