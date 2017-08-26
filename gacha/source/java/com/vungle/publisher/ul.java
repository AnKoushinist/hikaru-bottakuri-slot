package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class ul implements c<uk> {
    static final /* synthetic */ boolean a = (!ul.class.desiredAssertionStatus());
    private final MembersInjector<uk> b;

    public final /* synthetic */ Object get() {
        return (uk) d.a(this.b, new uk());
    }

    private ul(MembersInjector<uk> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<uk> a(MembersInjector<uk> membersInjector) {
        return new ul(membersInjector);
    }
}
