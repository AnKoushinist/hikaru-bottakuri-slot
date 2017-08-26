package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class fu implements c<ft> {
    static final /* synthetic */ boolean a = (!fu.class.desiredAssertionStatus());
    private final MembersInjector<ft> b;

    public final /* synthetic */ Object get() {
        return (ft) d.a(this.b, new ft());
    }

    private fu(MembersInjector<ft> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<ft> a(MembersInjector<ft> membersInjector) {
        return new fu(membersInjector);
    }
}
