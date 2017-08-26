package com.vungle.publisher;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import com.vungle.log.Logger;
import com.vungle.publisher.lk.b;
import javax.inject.Inject;

/* compiled from: vungle */
public abstract class mv<W extends mt> extends ms {
    protected lk e;
    protected W f;
    protected String g;
    protected n h;
    @Inject
    pn i;
    @Inject
    ql j;
    @Inject
    b k;
    @Inject
    Context l;

    /* compiled from: vungle */
    public static abstract class a<A extends mv<?>> {
        protected abstract A a();

        protected abstract String b();

        a() {
        }

        public final A a(Activity activity, String str, lk lkVar, n nVar) {
            A a = (mv) activity.getFragmentManager().findFragmentByTag(b());
            if (a == null) {
                a = a();
            }
            a.e = lkVar;
            a.g = str;
            a.h = nVar;
            return a;
        }
    }

    protected abstract W a(String str, n nVar);

    public void onCreate(Bundle bundle) {
        try {
            super.onCreate(bundle);
            if (bundle != null) {
                b bVar = this.k;
                lk a = bundle.containsKey("webViewRootContentIndexFile") ? bVar.a.a(bundle.getString("webViewRootContentIndexFile")) : bundle.containsKey("webViewRootContentString") ? bVar.b.a(bundle.getString("webViewRootContentString")) : null;
                this.e = a;
                this.g = bundle.getString(FullScreenAdActivity.AD_ID_EXTRA_KEY);
            }
        } catch (Throwable e) {
            Logger.w(Logger.AD_TAG, "exception in onCreate", e);
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.f.onConfigurationChanged(configuration);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View view;
        Throwable th;
        try {
            WebView a = a(this.g, this.h);
            this.f = a;
            lk lkVar = this.e;
            if (lkVar.a()) {
                Logger.v(Logger.AD_TAG, "loading webview with url: " + lkVar.b());
                a.loadUrl(lkVar.b());
            } else if (lkVar.c()) {
                Logger.v(Logger.AD_TAG, "loading webview with content: " + lkVar.d());
                a.loadDataWithBaseURL("http://lol.vungle.com/", lkVar.d(), "text/html", "utf-8", null);
            }
            this.i.a(a);
            a.setOnTouchListener(new OnTouchListener() {
                public final boolean onTouch(View view, MotionEvent motionEvent) {
                    return true;
                }
            });
            View relativeLayout = new RelativeLayout(this.l);
            try {
                relativeLayout.addView(a);
                LayoutParams layoutParams = a.getLayoutParams();
                layoutParams.width = -1;
                layoutParams.height = -1;
                return relativeLayout;
            } catch (Throwable e) {
                Throwable th2 = e;
                view = relativeLayout;
                th = th2;
                Logger.w(Logger.AD_TAG, "exception in onCreateView", th);
                return view;
            }
        } catch (Throwable e2) {
            th = e2;
            view = null;
            Logger.w(Logger.AD_TAG, "exception in onCreateView", th);
            return view;
        }
    }

    public void onDestroy() {
        Logger.d(Logger.AD_TAG, "webview fragment onDestroy()");
        super.onDestroy();
        this.f.destroy();
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        this.e.a(bundle);
        bundle.putString(FullScreenAdActivity.AD_ID_EXTRA_KEY, this.g);
    }
}
