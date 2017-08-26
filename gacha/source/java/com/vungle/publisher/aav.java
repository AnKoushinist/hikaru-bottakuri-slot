package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class aav implements c<aau> {
    static final /* synthetic */ boolean a = (!aav.class.desiredAssertionStatus());
    private final MembersInjector<aau> b;

    public final /* synthetic */ Object get() {
        return (aau) d.a(this.b, new aau());
    }

    private aav(MembersInjector<aau> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<aau> a(MembersInjector<aau> membersInjector) {
        return new aav(membersInjector);
    }
}
