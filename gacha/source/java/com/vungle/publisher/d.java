package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;

/* compiled from: vungle */
public final class d implements c<a> {
    static final /* synthetic */ boolean a = (!d.class.desiredAssertionStatus());
    private final MembersInjector<a> b;

    public final /* synthetic */ Object get() {
        return (a) dagger.a.d.a(this.b, new a());
    }

    private d(MembersInjector<a> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<a> a(MembersInjector<a> membersInjector) {
        return new d(membersInjector);
    }
}
