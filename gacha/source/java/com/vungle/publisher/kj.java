package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class kj implements c<ki> {
    static final /* synthetic */ boolean a = (!kj.class.desiredAssertionStatus());
    private final MembersInjector<ki> b;

    public final /* synthetic */ Object get() {
        return (ki) d.a(this.b, new ki());
    }

    private kj(MembersInjector<ki> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<ki> a(MembersInjector<ki> membersInjector) {
        return new kj(membersInjector);
    }
}
