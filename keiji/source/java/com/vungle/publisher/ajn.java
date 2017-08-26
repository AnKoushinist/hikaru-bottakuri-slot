package com.vungle.publisher;

import com.vungle.publisher.ahs.a;
import java.util.concurrent.ThreadFactory;

/* compiled from: vungle */
public final class ajn extends ahs {
    private final ThreadFactory b;

    public ajn(ThreadFactory threadFactory) {
        this.b = threadFactory;
    }

    public final a a() {
        return new ajo(this.b);
    }
}
