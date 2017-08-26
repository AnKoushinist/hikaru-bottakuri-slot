package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class fy implements c<eo> {
    static final /* synthetic */ boolean a = (!fy.class.desiredAssertionStatus());
    private final MembersInjector<eo> b;

    public final /* synthetic */ Object get() {
        return (eo) d.a(this.b, new eo());
    }

    private fy(MembersInjector<eo> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<eo> a(MembersInjector<eo> membersInjector) {
        return new fy(membersInjector);
    }
}
