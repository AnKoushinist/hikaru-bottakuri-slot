package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class afm implements c<afl> {
    static final /* synthetic */ boolean a = (!afm.class.desiredAssertionStatus());
    private final MembersInjector<afl> b;

    public final /* synthetic */ Object get() {
        return (afl) d.a(this.b, new afl());
    }

    private afm(MembersInjector<afl> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<afl> a(MembersInjector<afl> membersInjector) {
        return new afm(membersInjector);
    }
}
