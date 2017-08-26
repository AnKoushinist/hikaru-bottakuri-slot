package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class afz implements c<afy> {
    static final /* synthetic */ boolean a = (!afz.class.desiredAssertionStatus());
    private final MembersInjector<afy> b;

    public final /* synthetic */ Object get() {
        return (afy) d.a(this.b, new afy());
    }

    private afz(MembersInjector<afy> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<afy> a(MembersInjector<afy> membersInjector) {
        return new afz(membersInjector);
    }
}
