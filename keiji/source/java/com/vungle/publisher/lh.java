package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class lh implements c<lg> {
    static final /* synthetic */ boolean a = (!lh.class.desiredAssertionStatus());
    private final MembersInjector<lg> b;

    public final /* synthetic */ Object get() {
        return (lg) d.a(this.b, new lg());
    }

    private lh(MembersInjector<lg> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<lg> a(MembersInjector<lg> membersInjector) {
        return new lh(membersInjector);
    }
}
