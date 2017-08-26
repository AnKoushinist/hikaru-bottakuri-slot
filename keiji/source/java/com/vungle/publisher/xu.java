package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class xu implements c<xt> {
    static final /* synthetic */ boolean a = (!xu.class.desiredAssertionStatus());
    private final MembersInjector<xt> b;

    public final /* synthetic */ Object get() {
        return (xt) d.a(this.b, new xt());
    }

    private xu(MembersInjector<xt> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<xt> a(MembersInjector<xt> membersInjector) {
        return new xu(membersInjector);
    }
}
