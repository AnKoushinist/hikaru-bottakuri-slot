package com.vungle.publisher;

import com.vungle.publisher.env.AndroidDevice.DeviceIdStrategy;
import dagger.a.c;
import dagger.a.e;
import javax.inject.Provider;

/* compiled from: vungle */
public final class sa implements c<DeviceIdStrategy> {
    static final /* synthetic */ boolean a = (!sa.class.desiredAssertionStatus());
    private final rz b;
    private final Provider<pf> c;

    public final /* synthetic */ Object get() {
        return (DeviceIdStrategy) e.a((pf) this.c.get(), "Cannot return null from a non-@Nullable @Provides method");
    }

    private sa(rz rzVar, Provider<pf> provider) {
        if (a || rzVar != null) {
            this.b = rzVar;
            if (a || provider != null) {
                this.c = provider;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static c<DeviceIdStrategy> a(rz rzVar, Provider<pf> provider) {
        return new sa(rzVar, provider);
    }
}
