package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class aam implements c<aal> {
    static final /* synthetic */ boolean a = (!aam.class.desiredAssertionStatus());
    private final MembersInjector<aal> b;

    public final /* synthetic */ Object get() {
        return (aal) d.a(this.b, new aal());
    }

    private aam(MembersInjector<aal> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<aal> a(MembersInjector<aal> membersInjector) {
        return new aam(membersInjector);
    }
}
