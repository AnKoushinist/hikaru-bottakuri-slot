package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class zl implements c<zk> {
    static final /* synthetic */ boolean a = (!zl.class.desiredAssertionStatus());
    private final MembersInjector<zk> b;

    public final /* synthetic */ Object get() {
        return (zk) d.a(this.b, new zk());
    }

    private zl(MembersInjector<zk> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<zk> a(MembersInjector<zk> membersInjector) {
        return new zl(membersInjector);
    }
}
