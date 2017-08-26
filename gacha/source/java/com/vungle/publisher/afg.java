package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class afg implements c<aff> {
    static final /* synthetic */ boolean a = (!afg.class.desiredAssertionStatus());
    private final MembersInjector<aff> b;

    public final /* synthetic */ Object get() {
        return (aff) d.a(this.b, new aff());
    }

    private afg(MembersInjector<aff> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<aff> a(MembersInjector<aff> membersInjector) {
        return new afg(membersInjector);
    }
}
