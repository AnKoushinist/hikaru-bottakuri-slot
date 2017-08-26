package com.vungle.publisher;

import android.os.Bundle;
import com.vungle.log.Logger;
import com.vungle.publisher.inject.Injector;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: vungle */
public final class ob extends mv<om> {
    @Inject
    a m;

    @Singleton
    /* compiled from: vungle */
    public static class a extends com.vungle.publisher.mv.a<ob> {
        @Inject
        Provider<ob> a;

        protected final /* synthetic */ mv a() {
            return (ob) this.a.get();
        }

        @Inject
        a() {
        }

        protected final String b() {
            return "postRollFragment";
        }
    }

    protected final /* bridge */ /* synthetic */ mt a(String str, n nVar) {
        return (om) this.m.a(str, nVar);
    }

    public final void a() {
        try {
            this.j.a(new ah());
        } catch (Throwable e) {
            Logger.w(Logger.AD_TAG, "exception in onBackPressed", e);
        }
    }

    public final String b() {
        return "postRollFragment";
    }

    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Injector.b().a(this);
    }
}
