package jp.co.geniee.gnadsdk.interstitial;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build.VERSION;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebChromeClient.CustomViewCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import java.util.Timer;
import java.util.TimerTask;

/* compiled from: GNWebView */
class b extends FrameLayout {
    private final jp.co.geniee.gnadsdk.a.a a;
    private boolean b = false;
    private boolean c = false;
    private boolean d = false;
    private boolean e = false;
    private d f;
    private c g;
    private WebView h;
    private String i;
    private Timer j = null;
    private View k;
    private CustomViewCallback l;
    private a m;
    private Context n;
    private FrameLayout o;
    private c p = null;
    private f q = new f();
    private final Handler r = new Handler();

    /* compiled from: GNWebView */
    protected interface d {
        void a(int i, String str);
    }

    /* compiled from: GNWebView */
    protected interface c {
        void a(int i, String str);

        void f();
    }

    /* compiled from: GNWebView */
    private class a extends WebChromeClient {
        final /* synthetic */ b a;
        private View b;

        private a(b bVar) {
            this.a = bVar;
        }

        public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
            this.a.a.b("GNWebView", "onConsoleMessage : " + consoleMessage.message() + "[W007]");
            return super.onConsoleMessage(consoleMessage);
        }

        public boolean onJsAlert(WebView webView, String str, String str2, JsResult jsResult) {
            this.a.a.b("GNWebView", "onJSAlert : " + str2 + "[W008]");
            return super.onJsAlert(webView, str, str2, jsResult);
        }

        public boolean onJsTimeout() {
            this.a.a.b("GNWebView", "onJSTimeout[W009]");
            return false;
        }

        public void onProgressChanged(WebView webView, int i) {
            this.a.a.a("GNWebView", "WebChromeClient : onProgressChanged : " + i);
            if (i == 100) {
                if (this.a.h.getParent() instanceof b) {
                    this.a.a.a("GNWebView", "onProgressChanged : webview already added.");
                } else {
                    this.a.a.a("GNWebView", "onProgressChanged : webview add.");
                    try {
                        this.a.addView(this.a.h);
                    } catch (Throwable e) {
                        this.a.a.a("GNWebView", "onProgressChanged Exception", e);
                    }
                }
                if (!this.a.e && this.a.g != null) {
                    this.a.e = true;
                    this.a.g.f();
                }
            }
        }

        public void onShowCustomView(View view, CustomViewCallback customViewCallback) {
            this.a.a.a("GNWebView", "WebChromeClient : onShowCustomView");
            this.a.h.setVisibility(8);
            if (this.a.k != null) {
                customViewCallback.onCustomViewHidden();
                return;
            }
            this.a.o.addView(view);
            this.a.k = view;
            this.a.l = customViewCallback;
            this.a.o.setVisibility(0);
        }

        public void onHideCustomView() {
            this.a.a.a("GNWebView", "WebChromeClient : onHideCustomView");
            if (this.a.k != null) {
                try {
                    WebView.class.getMethod("onPause", (Class[]) null).invoke(this.a.k, (Object[]) null);
                } catch (Exception e) {
                }
                this.a.k.setVisibility(8);
                this.a.o.removeView(this.a.k);
                this.a.k = null;
                this.a.o.setVisibility(8);
                this.a.l.onCustomViewHidden();
                this.a.h.setVisibility(0);
            }
        }

        public View getVideoLoadingProgressView() {
            if (this.b == null) {
                this.b = new ProgressBar(this.a.n);
            }
            return this.b;
        }
    }

    /* compiled from: GNWebView */
    private class b extends WebViewClient {
        final /* synthetic */ b a;

        private b(b bVar) {
            this.a = bVar;
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            this.a.a.a("GNWebView", "shouldOverrideUrlLoading : " + str);
            if (this.a.f != null) {
                this.a.b = true;
                this.a.f.a(2, str);
            }
            return true;
        }

        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            this.a.a.a("GNWebView", "onPageFinished : " + str);
            if (!this.a.d) {
                this.a.a(1500);
            }
            if (this.a.h.getParent() instanceof b) {
                this.a.a.a("GNWebView", "onPageFinished : webview already added.");
            } else {
                this.a.a.a("GNWebView", "onPageFinished : webview add.");
                try {
                    this.a.addView(this.a.h);
                } catch (Throwable e) {
                    this.a.a.a("GNWebView", "onPageFinished Exception", e);
                }
            }
            if (!this.a.e && this.a.g != null) {
                this.a.e = true;
                this.a.g.f();
            }
        }

        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            super.onPageStarted(webView, str, bitmap);
            this.a.a.a("GNWebView", "onPageStarted : " + str);
            if (this.a.d && this.a.f != null) {
                this.a.b = true;
                webView.stopLoading();
                this.a.a.a("GNWebView", "onPageStarted : Start showing external browser.");
                this.a.f.a(2, str);
            }
        }

        public void onReceivedError(WebView webView, int i, String str, String str2) {
            super.onReceivedError(webView, i, str, str2);
            this.a.a.b("GNWebView", "onReceivedError : " + i + " : " + str + " : " + str2 + "[W005]");
            if (!this.a.e && this.a.g != null) {
                this.a.e = true;
                this.a.g.a(i, str);
            }
        }

        public void onLoadResource(WebView webView, String str) {
            this.a.a.a("GNWebView", "onLoadResource : " + str);
            if (!this.a.b && this.a.c && this.a.d) {
                if (webView.getHitTestResult() != null) {
                    int type = webView.getHitTestResult().getType();
                    this.a.a.a("GNWebView", "onLoadResource() HitTestResult Type:" + Integer.toString(type));
                    if (type == 8 || type == 0) {
                        if (this.a.f != null) {
                            this.a.b = true;
                            webView.stopLoading();
                            this.a.a.a("GNWebView", "onLoadResource : Start showing external browser.");
                            this.a.f.a(2, str);
                        }
                        this.a.b = false;
                    }
                }
                this.a.a(false);
            }
        }
    }

    /* compiled from: GNWebView */
    private class e extends TimerTask {
        final /* synthetic */ b a;

        private e(b bVar) {
            this.a = bVar;
        }

        public void run() {
            this.a.r.post(this.a.q);
        }
    }

    /* compiled from: GNWebView */
    private class f implements Runnable {
        final /* synthetic */ b a;

        private f(b bVar) {
            this.a = bVar;
        }

        public void run() {
            this.a.b(true);
        }
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    public b(Context context, jp.co.geniee.gnadsdk.a.a aVar, String str, d dVar) {
        super(context);
        this.n = context;
        this.a = aVar;
        this.i = str;
        this.h = new WebView(context.getApplicationContext());
        this.h.setWebViewClient(new b());
        this.h.setVerticalScrollBarEnabled(false);
        this.f = dVar;
        WebSettings settings = this.h.getSettings();
        settings.setLoadWithOverviewMode(true);
        settings.setBuiltInZoomControls(false);
        settings.setSupportZoom(false);
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        this.h.setBackgroundColor(0);
        if (VERSION.SDK_INT >= 11) {
            this.h.setLayerType(1, null);
        }
        this.m = new a();
        this.h.setWebChromeClient(this.m);
        this.h.getSettings().setJavaScriptEnabled(true);
        this.o = new FrameLayout(this.n);
        this.o.setVisibility(8);
        this.o.setBackgroundColor(-16777216);
        addView(this.o);
        this.p = new c();
        a(this.p);
        addView(this.h);
    }

    private void a(c cVar) {
        this.h.addJavascriptInterface(cVar, "gnsdkinters");
    }

    public void a(boolean z) {
        this.c = z;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        this.a.a("GNWebView", "onInterceptTouchEvent : " + motionEvent.toString());
        if (!this.d) {
            return true;
        }
        if (a()) {
            this.k.dispatchTouchEvent(motionEvent);
            return true;
        }
        a(true);
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        MotionEvent obtain = MotionEvent.obtain(5, 0, 0, x, y, 0);
        this.h.dispatchTouchEvent(obtain);
        obtain.recycle();
        obtain = MotionEvent.obtain(5, 0, 1, x, y, 0);
        this.h.dispatchTouchEvent(obtain);
        obtain.recycle();
        return true;
    }

    public boolean a() {
        return this.k != null;
    }

    public void b() {
        this.m.onHideCustomView();
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4 || !a()) {
            return super.onKeyDown(i, keyEvent);
        }
        b();
        return true;
    }

    protected void a(int i) {
        if (this.j != null) {
            this.j.cancel();
            this.j.purge();
        }
        this.j = new Timer(false);
        this.j.schedule(new e(), (long) i);
    }

    protected void b(boolean z) {
        this.a.a("GNWebView", "set webview load finished flag : " + z);
        this.d = z;
    }

    protected void c() {
        this.h.onPause();
        b();
    }

    protected void d() {
        this.h.onResume();
    }

    private void f() {
        if (this.j != null) {
            this.j.cancel();
            this.j.purge();
        }
    }

    public void e() {
        f();
        this.h.stopLoading();
        this.h.setWebChromeClient(null);
        this.h.setWebViewClient(null);
        this.h.destroy();
        this.h = null;
    }

    public void a(GNInterstitialActivity gNInterstitialActivity) {
        this.p.a(gNInterstitialActivity);
    }

    public void a(d dVar) {
        this.f = dVar;
    }
}
