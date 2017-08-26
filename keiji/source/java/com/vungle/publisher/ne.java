package com.vungle.publisher;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import com.vungle.log.Logger;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: vungle */
public abstract class ne<A extends cj> {
    A a;
    FullScreenAdActivity b;
    public ms c;
    mr<?> d;
    afx<?> e;
    protected String f;
    @Inject
    ql g;
    @Inject
    public com.vungle.publisher.gm.a h;
    @Inject
    agp i;
    @Inject
    pn j;
    private n k;

    @Singleton
    /* compiled from: vungle */
    public static class a {
        @Inject
        Provider<oy> a;
        @Inject
        Provider<nq> b;

        @Inject
        a() {
        }
    }

    protected abstract mr<?> a();

    protected abstract afx<?> b();

    public void a(FullScreenAdActivity fullScreenAdActivity, A a, n nVar, Bundle bundle) {
        this.a = a;
        this.b = fullScreenAdActivity;
        this.k = nVar;
        this.d = a();
        this.e = b();
        this.d.registerOnce();
        this.e.registerOnce();
        Object obj = bundle != null ? 1 : null;
        if (obj == null) {
            this.g.a(new aq(a, nVar));
        }
        this.f = obj != null ? bundle.getString("currentFragment") : null;
    }

    public void a(FullScreenAdActivity fullScreenAdActivity) {
    }

    final void a(boolean z, boolean z2) {
        try {
            this.g.a(z ? new bq(this.a, z2) : new bp(this.a, z2));
        } catch (Throwable e) {
            this.h.a(Logger.AD_TAG, "error exiting ad", e);
        } finally {
            this.b.finish();
        }
    }

    final void a(Uri uri) {
        try {
            Intent a = agp.a("android.intent.action.VIEW", uri);
            a.addFlags(268435456);
            this.b.startActivity(a);
        } catch (Throwable e) {
            this.h.a(Logger.AD_TAG, "error loading URL: " + uri.toString(), e);
        }
    }

    protected final void a(ms msVar) {
        if (msVar != this.c) {
            FragmentTransaction beginTransaction = this.b.getFragmentManager().beginTransaction();
            if (this.k == null || this.k.isTransitionAnimationEnabled()) {
                beginTransaction.setTransition(4099);
            }
            this.c = msVar;
            beginTransaction.replace(16908290, msVar, msVar.b());
            beginTransaction.commit();
        }
    }

    protected final void a(int i) {
        try {
            this.b.setRequestedOrientation(i);
        } catch (Throwable e) {
            this.h.a(Logger.AD_TAG, "could not set orientation", e);
        }
    }
}
