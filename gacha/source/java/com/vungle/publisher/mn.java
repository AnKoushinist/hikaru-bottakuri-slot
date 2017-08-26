package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class mn implements c<mm> {
    static final /* synthetic */ boolean a = (!mn.class.desiredAssertionStatus());
    private final MembersInjector<mm> b;

    public final /* synthetic */ Object get() {
        return (mm) d.a(this.b, new mm());
    }

    private mn(MembersInjector<mm> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<mm> a(MembersInjector<mm> membersInjector) {
        return new mn(membersInjector);
    }
}
