package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class zh implements c<zg> {
    static final /* synthetic */ boolean a = (!zh.class.desiredAssertionStatus());
    private final MembersInjector<zg> b;

    public final /* synthetic */ Object get() {
        return (zg) d.a(this.b, new zg());
    }

    private zh(MembersInjector<zg> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<zg> a(MembersInjector<zg> membersInjector) {
        return new zh(membersInjector);
    }
}
