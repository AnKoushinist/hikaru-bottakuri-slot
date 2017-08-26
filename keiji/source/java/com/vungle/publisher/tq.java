package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class tq implements c<tp> {
    static final /* synthetic */ boolean a = (!tq.class.desiredAssertionStatus());
    private final MembersInjector<tp> b;

    public final /* synthetic */ Object get() {
        return (tp) d.a(this.b, new tp());
    }

    private tq(MembersInjector<tp> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<tp> a(MembersInjector<tp> membersInjector) {
        return new tq(membersInjector);
    }
}
