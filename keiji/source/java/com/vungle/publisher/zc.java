package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class zc implements c<zb> {
    static final /* synthetic */ boolean a = (!zc.class.desiredAssertionStatus());
    private final MembersInjector<zb> b;

    public final /* synthetic */ Object get() {
        return (zb) d.a(this.b, new zb());
    }

    private zc(MembersInjector<zb> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<zb> a(MembersInjector<zb> membersInjector) {
        return new zc(membersInjector);
    }
}
