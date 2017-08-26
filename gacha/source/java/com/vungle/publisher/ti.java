package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class ti implements c<th> {
    static final /* synthetic */ boolean a = (!ti.class.desiredAssertionStatus());
    private final MembersInjector<th> b;

    public final /* synthetic */ Object get() {
        return (th) d.a(this.b, new th());
    }

    private ti(MembersInjector<th> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<th> a(MembersInjector<th> membersInjector) {
        return new ti(membersInjector);
    }
}
