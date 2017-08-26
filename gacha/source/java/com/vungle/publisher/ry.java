package com.vungle.publisher;

import com.vungle.publisher.inject.EndpointModule;
import dagger.a.c;
import dagger.a.e;

/* compiled from: vungle */
public final class ry implements c<String> {
    static final /* synthetic */ boolean a = (!ry.class.desiredAssertionStatus());
    private final EndpointModule b;

    public final /* synthetic */ Object get() {
        return (String) e.a(this.b.a, "Cannot return null from a non-@Nullable @Provides method");
    }

    private ry(EndpointModule endpointModule) {
        if (a || endpointModule != null) {
            this.b = endpointModule;
            return;
        }
        throw new AssertionError();
    }

    public static c<String> a(EndpointModule endpointModule) {
        return new ry(endpointModule);
    }
}
