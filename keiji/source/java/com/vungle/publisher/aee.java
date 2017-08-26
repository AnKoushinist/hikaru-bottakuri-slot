package com.vungle.publisher;

import com.vungle.publisher.aed.a.a;
import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class aee implements c<a> {
    static final /* synthetic */ boolean a = (!aee.class.desiredAssertionStatus());
    private final MembersInjector<a> b;

    public final /* synthetic */ Object get() {
        return (a) d.a(this.b, new a());
    }

    private aee(MembersInjector<a> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<a> a(MembersInjector<a> membersInjector) {
        return new aee(membersInjector);
    }
}
