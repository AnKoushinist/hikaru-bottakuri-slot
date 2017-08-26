package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class hf implements c<he> {
    static final /* synthetic */ boolean a = (!hf.class.desiredAssertionStatus());
    private final MembersInjector<he> b;

    public final /* synthetic */ Object get() {
        return (he) d.a(this.b, new he());
    }

    private hf(MembersInjector<he> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<he> a(MembersInjector<he> membersInjector) {
        return new hf(membersInjector);
    }
}
