package com.vungle.publisher;

import com.vungle.publisher.inject.EndpointModule;
import dagger.a.c;
import dagger.a.e;

/* compiled from: vungle */
public final class rx implements c<String> {
    static final /* synthetic */ boolean a = (!rx.class.desiredAssertionStatus());
    private final EndpointModule b;

    public final /* synthetic */ Object get() {
        return (String) e.a(this.b.b, "Cannot return null from a non-@Nullable @Provides method");
    }

    private rx(EndpointModule endpointModule) {
        if (a || endpointModule != null) {
            this.b = endpointModule;
            return;
        }
        throw new AssertionError();
    }

    public static c<String> a(EndpointModule endpointModule) {
        return new rx(endpointModule);
    }
}
