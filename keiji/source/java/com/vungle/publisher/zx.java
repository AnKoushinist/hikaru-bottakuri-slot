package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class zx implements c<zw> {
    static final /* synthetic */ boolean a = (!zx.class.desiredAssertionStatus());
    private final MembersInjector<zw> b;

    public final /* synthetic */ Object get() {
        return (zw) d.a(this.b, new zw());
    }

    private zx(MembersInjector<zw> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<zw> a(MembersInjector<zw> membersInjector) {
        return new zx(membersInjector);
    }
}
