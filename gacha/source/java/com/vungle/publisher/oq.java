package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class oq implements c<op> {
    static final /* synthetic */ boolean a = (!oq.class.desiredAssertionStatus());
    private final MembersInjector<op> b;

    public final /* synthetic */ Object get() {
        return (op) d.a(this.b, new op());
    }

    private oq(MembersInjector<op> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<op> a(MembersInjector<op> membersInjector) {
        return new oq(membersInjector);
    }
}
