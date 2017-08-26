package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class InitializationEventListener_Factory implements c<InitializationEventListener> {
    static final /* synthetic */ boolean a = (!InitializationEventListener_Factory.class.desiredAssertionStatus());
    private final MembersInjector<InitializationEventListener> b;

    public InitializationEventListener_Factory(MembersInjector<InitializationEventListener> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public final InitializationEventListener get() {
        return (InitializationEventListener) d.a(this.b, new InitializationEventListener());
    }

    public static c<InitializationEventListener> create(MembersInjector<InitializationEventListener> membersInjector) {
        return new InitializationEventListener_Factory(membersInjector);
    }
}
