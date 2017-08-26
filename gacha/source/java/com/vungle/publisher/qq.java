package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class qq implements c<qp> {
    static final /* synthetic */ boolean a = (!qq.class.desiredAssertionStatus());
    private final MembersInjector<qp> b;

    public final /* synthetic */ Object get() {
        return (qp) d.a(this.b, new qp());
    }

    private qq(MembersInjector<qp> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<qp> a(MembersInjector<qp> membersInjector) {
        return new qq(membersInjector);
    }
}
