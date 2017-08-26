package com.vungle.publisher;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.webkit.WebSettings;
import android.webkit.WebView;
import javax.inject.Inject;
import javax.inject.Singleton;

/* compiled from: vungle */
public abstract class mt extends WebView {
    protected ql a;

    @Singleton
    /* compiled from: vungle */
    public static class a {
        @Inject
        ql a;
        @Inject
        pn b;
        @Inject
        public bw c;

        /* compiled from: vungle */
        public class AnonymousClass1 implements Runnable {
            final /* synthetic */ Context a;
            final /* synthetic */ a b;

            public AnonymousClass1(a aVar, Context context) {
                this.b = aVar;
                this.a = context;
            }

            public final void run() {
                this.b.b.a(new WebView(this.a));
                this.b.a.a(new qc());
            }
        }

        @Inject
        a() {
        }
    }

    /* compiled from: vungle */
    public static abstract class b<W extends mt> {
        @Inject
        Context a;
        @Inject
        pn b;
        @Inject
        ql c;

        public abstract W a(Context context);

        public abstract void a(W w);

        public abstract void a(String str, W w, n nVar);

        public final W a(String str, n nVar) {
            mt a = a(this.a);
            a.a = this.c;
            WebSettings settings = a.getSettings();
            settings.setBuiltInZoomControls(false);
            settings.setJavaScriptEnabled(true);
            settings.setLoadWithOverviewMode(true);
            settings.setSaveFormData(true);
            settings.setUseWideViewPort(false);
            if (agl.a(pj.JELLY_BEAN_MR1)) {
                settings.setMediaPlaybackRequiresUserGesture(false);
            }
            a.setBackgroundColor(Color.argb(1, 0, 0, 0));
            a.setBackgroundResource(0);
            a(str, a, nVar);
            a(a);
            if (agl.a(pj.KITKAT)) {
                WebView.setWebContentsDebuggingEnabled(true);
            }
            return a;
        }
    }

    public mt(Context context) {
        super(context);
    }

    public void onConfigurationChanged(Configuration configuration) {
    }
}
