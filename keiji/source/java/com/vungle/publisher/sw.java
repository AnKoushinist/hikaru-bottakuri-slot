package com.vungle.publisher;

import android.content.Context;
import android.webkit.WebChromeClient;
import com.vungle.publisher.mt.b;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: vungle */
public final class sw extends mt {
    public sx b;
    public th c;

    @Singleton
    /* compiled from: vungle */
    public static class a extends b<sw> {
        @Inject
        com.vungle.publisher.sx.a d;
        @Inject
        Provider<su> e;
        @Inject
        th f;

        protected final /* synthetic */ mt a(Context context) {
            mt swVar = new sw(context);
            swVar.c = this.f;
            return swVar;
        }

        protected final /* synthetic */ void a(mt mtVar) {
            ((sw) mtVar).setWebChromeClient((WebChromeClient) this.e.get());
        }

        protected final /* synthetic */ void a(String str, mt mtVar, n nVar) {
            sw swVar = (sw) mtVar;
            com.vungle.publisher.sx.a aVar = this.d;
            sx sxVar = (sx) aVar.a.get();
            sxVar.a = aVar.b.a(str);
            sxVar.j = nVar;
            swVar.b = sxVar;
            swVar.setWebViewClient(sxVar);
        }

        @Inject
        a() {
        }
    }

    sw(Context context) {
        super(context);
    }

    public final int getHistoryIndex() {
        return copyBackForwardList().getCurrentIndex();
    }
}
