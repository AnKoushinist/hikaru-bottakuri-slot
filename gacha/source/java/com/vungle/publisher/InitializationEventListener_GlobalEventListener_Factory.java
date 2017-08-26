package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class InitializationEventListener_GlobalEventListener_Factory implements c<a> {
    static final /* synthetic */ boolean a = (!InitializationEventListener_GlobalEventListener_Factory.class.desiredAssertionStatus());
    private final MembersInjector<a> b;

    public InitializationEventListener_GlobalEventListener_Factory(MembersInjector<a> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public final a get() {
        return (a) d.a(this.b, new a());
    }

    public static c<a> create(MembersInjector<a> membersInjector) {
        return new InitializationEventListener_GlobalEventListener_Factory(membersInjector);
    }
}
