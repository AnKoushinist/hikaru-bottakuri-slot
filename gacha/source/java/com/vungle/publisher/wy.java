package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class wy implements c<wx> {
    static final /* synthetic */ boolean a = (!wy.class.desiredAssertionStatus());
    private final MembersInjector<wx> b;

    public final /* synthetic */ Object get() {
        return (wx) d.a(this.b, new wx());
    }

    private wy(MembersInjector<wx> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<wx> a(MembersInjector<wx> membersInjector) {
        return new wy(membersInjector);
    }
}
