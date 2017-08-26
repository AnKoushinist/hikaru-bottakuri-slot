package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class qg implements c<qf> {
    static final /* synthetic */ boolean a = (!qg.class.desiredAssertionStatus());
    private final MembersInjector<qf> b;

    public final /* synthetic */ Object get() {
        return (qf) d.a(this.b, new qf());
    }

    private qg(MembersInjector<qf> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<qf> a(MembersInjector<qf> membersInjector) {
        return new qg(membersInjector);
    }
}
