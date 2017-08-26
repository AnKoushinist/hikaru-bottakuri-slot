package com.vungle.publisher;

import com.vungle.publisher.env.WrapperFramework;
import dagger.a.c;
import dagger.a.e;

/* compiled from: vungle */
public final class ru implements c<WrapperFramework> {
    static final /* synthetic */ boolean a = (!ru.class.desiredAssertionStatus());
    private final re b;

    public final /* synthetic */ Object get() {
        re reVar = this.b;
        return (WrapperFramework) e.a(reVar.e == null ? WrapperFramework.none : reVar.e, "Cannot return null from a non-@Nullable @Provides method");
    }

    private ru(re reVar) {
        if (a || reVar != null) {
            this.b = reVar;
            return;
        }
        throw new AssertionError();
    }

    public static c<WrapperFramework> a(re reVar) {
        return new ru(reVar);
    }
}
