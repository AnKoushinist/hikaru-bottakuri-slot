package com.vungle.publisher;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.vungle.log.Logger;
import com.vungle.publisher.inject.Injector;
import java.util.concurrent.atomic.AtomicInteger;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: vungle */
public final class nh extends mv<sw> {
    final Handler m = new Handler();
    AtomicInteger n = new AtomicInteger(0);
    AtomicInteger o = new AtomicInteger(0);
    te p;
    og q;
    @Inject
    com.vungle.publisher.sw.a r;
    @Inject
    a s;
    @Inject
    th t;
    @Inject
    nb u;
    @Inject
    com.vungle.publisher.te.a v;
    @Inject
    com.vungle.publisher.og.a w;

    @Singleton
    /* compiled from: vungle */
    public static class a extends com.vungle.publisher.mv.a<nh> {
        @Inject
        Provider<nh> a;

        protected final /* synthetic */ mv a() {
            return (nh) this.a.get();
        }

        @Inject
        a() {
        }

        protected final String b() {
            return "fullScreenMraidFragment";
        }
    }

    @Singleton
    /* compiled from: vungle */
    public static class b extends qe {
        nh a;

        @Singleton
        /* compiled from: vungle */
        public static class a {
            @Inject
            b a;

            @Inject
            a() {
            }

            public final b a(nh nhVar) {
                this.a.a = nhVar;
                return this.a;
            }
        }

        @Inject
        b() {
        }

        public final void onEvent(ui uiVar) {
            com.vungle.publisher.te.b bVar = uiVar.a;
            Logger.v(Logger.EVENT_TAG, "set close region: " + bVar);
            this.a.a(bVar, 2, 0);
        }

        public final void onEvent(uj ujVar) {
            boolean z = ujVar.a;
            Logger.v(Logger.EVENT_TAG, "use custom privacy icon? " + z);
            this.a.a(!z, 2, 0);
        }

        public final void onEvent(uh uhVar) {
            Logger.v(Logger.EVENT_TAG, "throw incentivized dialog");
            nh.a(this.a);
        }

        public final void onEvent(uf ufVar) {
            this.a.a(com.vungle.publisher.te.b.visible, 1, 500);
            this.a.a(true, 1, 500);
        }
    }

    /* compiled from: vungle */
    abstract class d<V> implements Runnable {
        final V b;
        final int c;
        final /* synthetic */ nh d;

        abstract AtomicInteger a();

        abstract void a(V v);

        public d(nh nhVar, V v, int i) {
            this.d = nhVar;
            this.c = i;
            this.b = v;
        }

        public void run() {
            try {
                AtomicInteger a = a();
                int i;
                do {
                    i = a.get();
                    if (this.c < i) {
                        return;
                    }
                } while (!a.compareAndSet(i, this.c));
                a(this.b);
            } catch (Throwable e) {
                Logger.w(Logger.AD_TAG, e);
            }
        }
    }

    /* compiled from: vungle */
    class c extends d<com.vungle.publisher.te.b> {
        final /* synthetic */ nh a;

        final /* synthetic */ void a(Object obj) {
            this.a.p.setCloseVisibility((com.vungle.publisher.te.b) obj);
        }

        public c(nh nhVar, com.vungle.publisher.te.b bVar, int i) {
            this.a = nhVar;
            super(nhVar, bVar, i);
        }

        final AtomicInteger a() {
            return this.a.n;
        }
    }

    /* compiled from: vungle */
    class e extends d<Integer> {
        final /* synthetic */ nh a;

        final /* synthetic */ void a(Object obj) {
            this.a.q.setVisibility(((Integer) obj).intValue());
        }

        public e(nh nhVar, Integer num, int i) {
            this.a = nhVar;
            super(nhVar, num, i);
        }

        final AtomicInteger a() {
            return this.a.o;
        }
    }

    static /* synthetic */ void a(nh nhVar) {
        nhVar.a = nhVar.a != null ? nhVar.a : nhVar.d.a(nhVar.getActivity(), nhVar.h, new com.vungle.publisher.mz.a(nhVar) {
            final /* synthetic */ nh a;

            {
                this.a = r1;
            }

            public final void a() {
                th.a(this.a.f, true);
            }

            public final void b() {
                Logger.d(Logger.AD_TAG, "cancel video");
                th.a(this.a.f, false);
            }

            public final void c() {
                th.a(this.a.f, true);
            }
        });
        nhVar.a.show();
    }

    protected final /* bridge */ /* synthetic */ mt a(String str, n nVar) {
        return (sw) this.r.a(str, nVar);
    }

    public final void onCreate(Bundle bundle) {
        try {
            super.onCreate(bundle);
            Injector.b().a(this);
            this.s.a(this).registerOnce();
        } catch (Throwable e) {
            Logger.e(Logger.AD_TAG, "exception while creating Mraid ad fragment", e);
        }
    }

    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        th thVar = this.t;
        WebView webView = this.f;
        tp a = thVar.d.a();
        a.c();
        try {
            th.a(webView, a.a(), false);
        } catch (Throwable e) {
            thVar.b.b(Logger.AD_TAG, "could not update mraid dimensions", e);
        }
    }

    public final View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        RelativeLayout relativeLayout = (RelativeLayout) super.onCreateView(layoutInflater, viewGroup, bundle);
        try {
            com.vungle.publisher.te.a aVar = this.v;
            te teVar = new te(aVar.a);
            View imageView = new ImageView(aVar.a);
            teVar.a = imageView;
            aVar.c.a(imageView, com.vungle.publisher.rb.a.close);
            teVar.addView(imageView);
            LayoutParams layoutParams = (LayoutParams) imageView.getLayoutParams();
            layoutParams.addRule(11);
            layoutParams.addRule(10);
            teVar.setCloseVisibility(com.vungle.publisher.te.b.gone);
            this.p = teVar;
            relativeLayout.addView(this.p);
            layoutParams = (LayoutParams) this.p.getLayoutParams();
            layoutParams.addRule(11);
            layoutParams.addRule(10);
            try {
                layoutParams = (LayoutParams) this.p.getLayoutParams();
                int a = (int) this.v.b.a(50);
                layoutParams.width = a;
                layoutParams.height = a;
            } catch (Exception e) {
                Logger.e(Logger.AD_TAG, "could not set close region dimensions. did you add it to a view yet?");
            }
            a(com.vungle.publisher.te.b.visible, 0, 3000);
            this.p.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ nh a;

                {
                    this.a = r1;
                }

                public final void onClick(View view) {
                    this.a.j.a(new ty());
                }
            });
            this.q = this.w.a(this.l, true);
            relativeLayout.addView(this.q);
            layoutParams = (LayoutParams) this.q.getLayoutParams();
            layoutParams.addRule(9);
            layoutParams.addRule(10);
            a(true, 0, 3000);
            relativeLayout.setBackgroundColor(0);
        } catch (Throwable e2) {
            this.c.a(Logger.AD_TAG, "error creating MraidAdFragment", e2);
        }
        return relativeLayout;
    }

    final void a(com.vungle.publisher.te.b bVar, int i, long j) {
        this.m.postDelayed(new c(this, bVar, i), j);
    }

    final void a(boolean z, int i, long j) {
        this.m.postDelayed(new e(this, Integer.valueOf(z ? 0 : 8), i), j);
    }

    public final boolean c() {
        return ((sw) this.f).b.b;
    }

    public final void onDestroy() {
        super.onDestroy();
        this.s.a(this).unregister();
    }

    public final void onResume() {
        try {
            super.onResume();
            this.t.a(true, this.f);
        } catch (Throwable e) {
            this.c.a(Logger.AD_TAG, "error resuming mraid ad", e);
        }
    }

    public final void onPause() {
        try {
            super.onPause();
            this.t.a(false, this.f);
        } catch (Throwable e) {
            this.c.a(Logger.AD_TAG, "error pausing mraid ad", e);
        }
    }

    public final void a(boolean z) {
        super.a(z);
        if (z) {
            onResume();
        } else {
            onPause();
        }
    }

    public final void a() {
        try {
            sw swVar = (sw) this.f;
            if (swVar.b.b) {
                ss.a("window.vungle.mraidBridgeExt", swVar, "requestMRAIDClose", new String[0]);
            } else if (!swVar.b.c) {
                int historyIndex = swVar.getHistoryIndex();
                Logger.v(Logger.AD_TAG, "back pressed at index: " + historyIndex);
                if (historyIndex > 0) {
                    swVar.goBack();
                }
            }
        } catch (Throwable e) {
            Logger.w(Logger.AD_TAG, "exception in onBackPressed", e);
        }
    }

    public final String b() {
        return "fullScreenMraidFragment";
    }
}
