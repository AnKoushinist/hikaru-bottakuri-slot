package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class mc implements c<ly> {
    static final /* synthetic */ boolean a = (!mc.class.desiredAssertionStatus());
    private final MembersInjector<ly> b;

    public final /* synthetic */ Object get() {
        return (ly) d.a(this.b, new ly());
    }

    private mc(MembersInjector<ly> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<ly> a(MembersInjector<ly> membersInjector) {
        return new mc(membersInjector);
    }
}
