package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class ev implements c<eu> {
    static final /* synthetic */ boolean a = (!ev.class.desiredAssertionStatus());
    private final MembersInjector<eu> b;

    public final /* synthetic */ Object get() {
        return (eu) d.a(this.b, new eu());
    }

    private ev(MembersInjector<eu> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<eu> a(MembersInjector<eu> membersInjector) {
        return new ev(membersInjector);
    }
}
