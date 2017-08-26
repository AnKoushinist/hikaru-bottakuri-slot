package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class ea implements c<dx.c> {
    static final /* synthetic */ boolean a = (!ea.class.desiredAssertionStatus());
    private final MembersInjector<dx.c> b;

    public final /* synthetic */ Object get() {
        return (dx.c) d.a(this.b, new dx.c());
    }

    private ea(MembersInjector<dx.c> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<dx.c> a(MembersInjector<dx.c> membersInjector) {
        return new ea(membersInjector);
    }
}
