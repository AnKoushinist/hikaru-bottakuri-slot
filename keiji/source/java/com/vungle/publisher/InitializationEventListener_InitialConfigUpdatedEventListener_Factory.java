package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class InitializationEventListener_InitialConfigUpdatedEventListener_Factory implements c<b> {
    static final /* synthetic */ boolean a = (!InitializationEventListener_InitialConfigUpdatedEventListener_Factory.class.desiredAssertionStatus());
    private final MembersInjector<b> b;

    public InitializationEventListener_InitialConfigUpdatedEventListener_Factory(MembersInjector<b> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public final b get() {
        return (b) d.a(this.b, new b());
    }

    public static c<b> create(MembersInjector<b> membersInjector) {
        return new InitializationEventListener_InitialConfigUpdatedEventListener_Factory(membersInjector);
    }
}
