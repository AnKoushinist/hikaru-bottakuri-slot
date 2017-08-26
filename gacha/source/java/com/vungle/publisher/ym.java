package com.vungle.publisher;

import com.vungle.publisher.we.a;
import com.vungle.publisher.yg.b;
import com.vungle.publisher.yg.b.AnonymousClass1;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public final class ym extends a {
    @Inject
    b a;
    @Inject
    Provider<yj> b;

    @Inject
    ym() {
    }

    public final we a(cq<?, ?, ?, ?> cqVar) {
        b bVar = this.a;
        return super.a((yg) new AnonymousClass1(bVar, cqVar).a(cqVar.b().f()), (vz) this.b.get());
    }
}
