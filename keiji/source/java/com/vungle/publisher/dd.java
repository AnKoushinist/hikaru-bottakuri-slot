package com.vungle.publisher;

import com.vungle.publisher.db.a;
import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class dd implements c<a> {
    static final /* synthetic */ boolean a = (!dd.class.desiredAssertionStatus());
    private final MembersInjector<a> b;

    public final /* synthetic */ Object get() {
        return (a) d.a(this.b, new a());
    }

    private dd(MembersInjector<a> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<a> a(MembersInjector<a> membersInjector) {
        return new dd(membersInjector);
    }
}
