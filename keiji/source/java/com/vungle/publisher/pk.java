package com.vungle.publisher;

import com.vungle.publisher.env.AndroidDevice;
import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class pk implements c<AndroidDevice> {
    static final /* synthetic */ boolean a = (!pk.class.desiredAssertionStatus());
    private final MembersInjector<AndroidDevice> b;

    public final /* synthetic */ Object get() {
        return (AndroidDevice) d.a(this.b, new AndroidDevice());
    }

    private pk(MembersInjector<AndroidDevice> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<AndroidDevice> a(MembersInjector<AndroidDevice> membersInjector) {
        return new pk(membersInjector);
    }
}
