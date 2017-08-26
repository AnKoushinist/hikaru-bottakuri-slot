package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class aaa implements c<zz> {
    static final /* synthetic */ boolean a = (!aaa.class.desiredAssertionStatus());
    private final MembersInjector<zz> b;

    public final /* synthetic */ Object get() {
        return (zz) d.a(this.b, new zz());
    }

    private aaa(MembersInjector<zz> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<zz> a(MembersInjector<zz> membersInjector) {
        return new aaa(membersInjector);
    }
}
