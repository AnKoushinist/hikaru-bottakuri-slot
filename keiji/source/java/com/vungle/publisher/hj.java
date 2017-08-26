package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class hj implements c<hd> {
    static final /* synthetic */ boolean a = (!hj.class.desiredAssertionStatus());
    private final MembersInjector<hd> b;

    public final /* synthetic */ Object get() {
        return (hd) d.a(this.b, new hd());
    }

    private hj(MembersInjector<hd> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<hd> a(MembersInjector<hd> membersInjector) {
        return new hj(membersInjector);
    }
}
