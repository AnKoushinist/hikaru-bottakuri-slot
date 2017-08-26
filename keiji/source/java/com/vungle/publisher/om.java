package com.vungle.publisher;

import android.content.Context;
import com.vungle.publisher.mt.b;
import javax.inject.Inject;
import javax.inject.Singleton;

/* compiled from: vungle */
public final class om extends mt {

    @Singleton
    /* compiled from: vungle */
    static class a extends b<om> {
        @Inject
        mm d;
        @Inject
        mj e;

        protected final /* synthetic */ mt a(Context context) {
            return new om(context);
        }

        protected final /* synthetic */ void a(mt mtVar) {
            ((om) mtVar).setWebChromeClient(this.e);
        }

        protected final /* synthetic */ void a(String str, mt mtVar, n nVar) {
            ((om) mtVar).setWebViewClient(this.d);
        }

        @Inject
        a() {
        }
    }

    private om(Context context) {
        super(context);
    }
}
