package com.vungle.publisher;

import com.vungle.publisher.adf.a;
import javax.inject.Inject;

/* compiled from: vungle */
public final class xt implements aii<String, ahp<adf>> {
    @Inject
    a a;

    @Inject
    xt() {
    }

    private ahp<adf> a(String str) {
        try {
            return akc.a(this.a.a(str));
        } catch (Throwable e) {
            throw ahx.a(e);
        }
    }
}
