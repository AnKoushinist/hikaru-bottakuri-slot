package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class fa implements c<ez> {
    static final /* synthetic */ boolean a = (!fa.class.desiredAssertionStatus());
    private final MembersInjector<ez> b;

    public final /* synthetic */ Object get() {
        return (ez) d.a(this.b, new ez());
    }

    private fa(MembersInjector<ez> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<ez> a(MembersInjector<ez> membersInjector) {
        return new fa(membersInjector);
    }
}
