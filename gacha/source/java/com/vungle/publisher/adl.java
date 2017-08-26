package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class adl implements c<adi> {
    static final /* synthetic */ boolean a = (!adl.class.desiredAssertionStatus());
    private final MembersInjector<adi> b;

    public final /* synthetic */ Object get() {
        return (adi) d.a(this.b, new adi());
    }

    private adl(MembersInjector<adi> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<adi> a(MembersInjector<adi> membersInjector) {
        return new adl(membersInjector);
    }
}
