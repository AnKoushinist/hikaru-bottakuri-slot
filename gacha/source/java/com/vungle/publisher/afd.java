package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class afd implements c<afc> {
    static final /* synthetic */ boolean a = (!afd.class.desiredAssertionStatus());
    private final MembersInjector<afc> b;

    public final /* synthetic */ Object get() {
        return (afc) d.a(this.b, new afc());
    }

    private afd(MembersInjector<afc> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<afc> a(MembersInjector<afc> membersInjector) {
        return new afd(membersInjector);
    }
}
