package com.adcolony.sdk;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build.VERSION;
import android.view.ViewGroup.LayoutParams;
import android.webkit.ConsoleMessage;
import android.webkit.ConsoleMessage.MessageLevel;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import com.d.a.a.c;
import com.tapjoy.TJAdUnitConstants.String;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import org.cocos2dx.lib.BuildConfig;
import org.json.JSONArray;
import org.json.JSONObject;

class ad extends WebView implements r {
    private String a;
    private String b;
    private String c = BuildConfig.FLAVOR;
    private String d = BuildConfig.FLAVOR;
    private String e;
    private String f = BuildConfig.FLAVOR;
    private String g = BuildConfig.FLAVOR;
    private int h;
    private int i;
    private int j;
    private int k;
    private int l;
    private int m;
    private boolean n;
    private boolean o;
    private boolean p;
    private boolean q;
    private boolean r;
    private boolean s;
    private JSONArray t = bb.b();
    private JSONObject u = bb.a();
    private al v;
    private o w;

    private class a extends WebViewClient {
        final /* synthetic */ ad b;

        private a(ad adVar) {
            this.b = adVar;
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            return false;
        }

        public void onLoadResource(WebView webView, String str) {
            if (str.equals(this.b.a)) {
                this.b.a("if (typeof(CN) != 'undefined' && CN.div) {\n  if (typeof(cn_dispatch_on_touch_begin) != 'undefined') CN.div.removeEventListener('mousedown',  cn_dispatch_on_touch_begin, true);\n  if (typeof(cn_dispatch_on_touch_end) != 'undefined')   CN.div.removeEventListener('mouseup',  cn_dispatch_on_touch_end, true);\n  if (typeof(cn_dispatch_on_touch_move) != 'undefined')  CN.div.removeEventListener('mousemove',  cn_dispatch_on_touch_move, true);\n}\n");
            }
        }

        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            this.b.q = false;
            this.b.r = false;
            bd.d.b("onPageStarted with URL = " + str);
        }

        public void onPageFinished(WebView webView, String str) {
            JSONObject a = bb.a();
            bb.b(a, "id", this.b.h);
            bb.a(a, String.URL, str);
            bd.b.a("onPageFinished called with URL = ").b((Object) str);
            if (this.b.v == null) {
                new o("WebView.on_load", this.b.m, a).a();
            } else {
                bb.a(a, "ad_session_id", this.b.e);
                bb.b(a, "container_id", this.b.v.c());
                new o("WebView.on_load", this.b.v.b(), a).a();
            }
            if ((this.b.n || this.b.o) && !this.b.q && (str.startsWith(String.DATA) || str.startsWith("file") || str.equals(this.b.d) || this.b.r)) {
                bd.b.b((Object) "WebView data loaded - executing ADC3_init");
                bd.b.b((Object) "===============================================================================");
                bd.b.b("ADC3_init(" + this.b.m + "," + this.b.u.toString() + ");");
                bd.b.b((Object) "===============================================================================");
                this.b.a("ADC3_init(" + this.b.m + "," + this.b.u.toString() + ");");
                this.b.q = true;
            }
            if (this.b.o) {
                a = bb.a();
                bb.a(a, "success", true);
                bb.b(a, "id", this.b.m);
                this.b.w.a(a).a();
            }
        }

        public WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
            if (VERSION.SDK_INT >= 21 || !str.endsWith("mraid.js")) {
                return null;
            }
            try {
                InputStream byteArrayInputStream = new ByteArrayInputStream(this.b.f.getBytes(c.DEFAULT_CHARSET));
                this.b.r = true;
                return new WebResourceResponse("text/javascript", c.DEFAULT_CHARSET, byteArrayInputStream);
            } catch (UnsupportedEncodingException e) {
                bd.h.b((Object) "UTF-8 not supported.");
                return null;
            }
        }

        public void onReceivedError(WebView webView, int i, String str, String str2) {
            if (VERSION.SDK_INT < 23) {
                JSONObject a = bb.a();
                bb.b(a, "id", this.b.h);
                bb.a(a, "ad_session_id", this.b.e);
                bb.b(a, "container_id", this.b.v.c());
                bb.b(a, "code", i);
                bb.a(a, String.VIDEO_ERROR, str);
                bb.a(a, String.URL, str2);
                new o("WebView.on_error", this.b.v.b(), a).a();
            }
        }
    }

    ad(Context context, o oVar, int i, int i2, al alVar) {
        super(context);
        this.w = oVar;
        a(oVar, i, i2, alVar);
        d();
    }

    ad(Context context, int i, boolean z) {
        super(context);
        this.m = i;
        this.p = z;
    }

    void a(String str) {
        if (this.s) {
            bd.b.b((Object) "Ignoring call to execute_js as WebView has been destroyed.");
        } else if (VERSION.SDK_INT >= 19) {
            evaluateJavascript(str, null);
        } else {
            loadUrl("javascript:" + str);
        }
    }

    public int a() {
        return this.m;
    }

    boolean a(o oVar) {
        JSONObject b = oVar.b();
        if (bb.b(b, "id") == this.h && bb.b(b, "container_id") == this.v.c() && bb.a(b, "ad_session_id").equals(this.v.a())) {
            return true;
        }
        return false;
    }

    void d() {
        a(false, null);
    }

    void a(boolean z, o oVar) {
        WebViewClient anonymousClass2;
        if (this.w == null) {
            this.w = oVar;
        }
        final JSONObject b = this.w.b();
        this.o = z;
        this.p = bb.c(b, "is_display_module");
        if (z) {
            String a = bb.a(b, "filepath");
            this.b = a;
            this.a = "file://" + a;
            this.u = bb.e(b, String.VIDEO_INFO);
            this.n = true;
        }
        setFocusable(true);
        setHorizontalScrollBarEnabled(false);
        setVerticalScrollBarEnabled(false);
        if (VERSION.SDK_INT >= 19) {
            setWebContentsDebuggingEnabled(true);
        }
        setWebChromeClient(new WebChromeClient(this) {
            final /* synthetic */ ad b;

            public boolean onJsAlert(WebView webView, String str, String str2, JsResult jsResult) {
                bd.d.a("JS Alert: ").b((Object) str2);
                return true;
            }

            public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                if (consoleMessage.messageLevel() == MessageLevel.WARNING) {
                    bd.f.b("onConsoleMessage: " + consoleMessage.message());
                } else if (consoleMessage.messageLevel() == MessageLevel.ERROR) {
                    if ((consoleMessage.message().contains("ADC3_update is not defined") || consoleMessage.message().contains("NativeLayer.dispatch_messages is not a function")) && n.d() && (n.c() instanceof ah)) {
                        o oVar = new o("AdSession.finish_fullscreen_ad", 0);
                        bb.b(b, "status", 1);
                        bd.g.b((Object) "Unable to communicate with ad, closing. Please ensure that you have added an exception for our Javascript interface in your ProGuard configuration and that you do not have a faulty proxy enabled on your device.");
                        ((ah) n.c()).a(oVar);
                    }
                    bd.h.b("onConsoleMessage: " + consoleMessage.message());
                }
                return true;
            }
        });
        WebSettings settings = getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setBuiltInZoomControls(true);
        settings.setGeolocationEnabled(true);
        settings.setUseWideViewPort(true);
        if (VERSION.SDK_INT >= 17) {
            settings.setMediaPlaybackRequiresUserGesture(false);
        }
        if (VERSION.SDK_INT >= 16) {
            settings.setAllowFileAccessFromFileURLs(true);
            settings.setAllowUniversalAccessFromFileURLs(true);
        }
        if (VERSION.SDK_INT >= 23) {
            anonymousClass2 = new a(this) {
                final /* synthetic */ ad a;

                {
                    this.a = r2;
                }

                public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
                    if (this.a.v != null) {
                        JSONObject a = bb.a();
                        bb.b(a, "id", this.a.h);
                        bb.a(a, "ad_session_id", this.a.e);
                        bb.b(a, "container_id", this.a.v.c());
                        bb.b(a, "code", webResourceError.getErrorCode());
                        bb.a(a, String.VIDEO_ERROR, webResourceError.getDescription().toString());
                        bb.a(a, String.URL, this.a.a);
                        new o("WebView.on_error", this.a.v.b(), a).a();
                    }
                    bd.h.b("onReceivedError: " + webResourceError.getDescription().toString());
                }

                public WebResourceResponse shouldInterceptRequest(WebView webView, WebResourceRequest webResourceRequest) {
                    if (!webResourceRequest.getUrl().toString().endsWith("mraid.js")) {
                        return null;
                    }
                    try {
                        InputStream byteArrayInputStream = new ByteArrayInputStream(this.a.f.getBytes(c.DEFAULT_CHARSET));
                        this.a.r = true;
                        return new WebResourceResponse("text/javascript", c.DEFAULT_CHARSET, byteArrayInputStream);
                    } catch (UnsupportedEncodingException e) {
                        bd.h.b((Object) "UTF-8 not supported.");
                        return null;
                    }
                }
            };
        } else if (VERSION.SDK_INT >= 21) {
            anonymousClass2 = new a(this) {
                final /* synthetic */ ad a;

                {
                    this.a = r2;
                }

                public WebResourceResponse shouldInterceptRequest(WebView webView, WebResourceRequest webResourceRequest) {
                    if (!webResourceRequest.getUrl().toString().endsWith("mraid.js")) {
                        return null;
                    }
                    try {
                        InputStream byteArrayInputStream = new ByteArrayInputStream(this.a.f.getBytes(c.DEFAULT_CHARSET));
                        this.a.r = true;
                        return new WebResourceResponse("text/javascript", c.DEFAULT_CHARSET, byteArrayInputStream);
                    } catch (UnsupportedEncodingException e) {
                        bd.h.b((Object) "UTF-8 not supported.");
                        return null;
                    }
                }
            };
        } else {
            anonymousClass2 = new a();
        }
        addJavascriptInterface(new Object(this) {
            final /* synthetic */ ad a;

            {
                this.a = r1;
            }

            @JavascriptInterface
            public void dispatch_messages(String str) {
                JSONArray b = bb.b(str);
                if (b == null) {
                    bd.g.b((Object) "[INTERNAL] ADCJSON parse error in dispatch_messages javascript interface function");
                    return;
                }
                for (int i = 0; i < b.length(); i++) {
                    n.a().k().a(bb.c(b, i));
                }
            }
        }, "NativeLayer");
        setWebViewClient(anonymousClass2);
        if (this.p) {
            String str = BuildConfig.FLAVOR;
            try {
                InputStream fileInputStream = new FileInputStream(this.b);
                StringBuilder stringBuilder = new StringBuilder(fileInputStream.available());
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = fileInputStream.read(bArr, 0, 1024);
                    if (read < 0) {
                        break;
                    }
                    stringBuilder.append(new String(bArr, 0, read, "ISO-8859-1"));
                }
                str = stringBuilder.toString().replaceFirst("var\\s*ADC_DEVICE_INFO\\s*=\\s*null\\s*;", "var ADC_DEVICE_INFO = " + bb.a(bb.e(b, String.VIDEO_INFO), "metadata") + ";\n");
            } catch (IOException e) {
                bd.g.a("Failed to find or open display module at URL: ").a(this.a).a(" with error: ").b(e.toString());
            }
            loadDataWithBaseURL(this.a, str, "text/html", null, null);
        } else if (this.a.startsWith("http") || this.a.startsWith("file")) {
            loadUrl(this.a);
        } else {
            loadDataWithBaseURL(this.d.equals(BuildConfig.FLAVOR) ? String.DATA : this.d, z ? bb.a(b, String.DATA) : this.a, "text/html", null, null);
        }
        if (!z) {
            e();
            f();
        }
        if (z || this.n) {
            n.a().k().a((r) this);
        }
        if (!this.c.equals(BuildConfig.FLAVOR)) {
            a(this.c);
        }
    }

    void e() {
        this.v.l().add(n.a("WebView.set_visible", new q(this) {
            final /* synthetic */ ad a;

            {
                this.a = r1;
            }

            public void a(o oVar) {
                if (this.a.a(oVar)) {
                    this.a.c(oVar);
                }
            }
        }, true));
        this.v.l().add(n.a("WebView.set_bounds", new q(this) {
            final /* synthetic */ ad a;

            {
                this.a = r1;
            }

            public void a(o oVar) {
                if (this.a.a(oVar)) {
                    this.a.b(oVar);
                }
            }
        }, true));
        this.v.l().add(n.a("WebView.execute_js", new q(this) {
            final /* synthetic */ ad a;

            {
                this.a = r1;
            }

            public void a(o oVar) {
                if (this.a.a(oVar)) {
                    this.a.a(bb.a(oVar.b(), "custom_js"));
                }
            }
        }, true));
        this.v.m().add("WebView.set_visible");
        this.v.m().add("WebView.set_bounds");
        this.v.m().add("WebView.execute_js");
    }

    void f() {
        setVisibility(4);
        LayoutParams layoutParams = new FrameLayout.LayoutParams(this.k, this.l);
        layoutParams.setMargins(this.i, this.j, 0, 0);
        layoutParams.gravity = 0;
        this.v.addView(this, layoutParams);
    }

    void a(o oVar, int i, al alVar) {
        a(oVar, i, -1, alVar);
        f();
    }

    void a(o oVar, int i, int i2, al alVar) {
        boolean z = false;
        JSONObject b = oVar.b();
        this.a = bb.a(b, String.URL);
        if (this.a.equals(BuildConfig.FLAVOR)) {
            this.a = bb.a(b, String.DATA);
        }
        this.d = bb.a(b, "base_url");
        this.c = bb.a(b, "custom_js");
        this.e = bb.a(b, "ad_session_id");
        this.u = bb.e(b, String.VIDEO_INFO);
        this.g = bb.a(b, "mraid_filepath");
        if (!this.p) {
            try {
                this.f = n.a().f().a(this.g, false).toString();
                String str = "bridge.os_name = \"\";\nvar ADC_DEVICE_INFO = " + this.u.toString() + ";\n";
                this.f = this.f.replaceFirst("bridge.os_name\\s*=\\s*\"\"\\s*;", str);
            } catch (IOException e) {
                bd.g.a("Could not load MRAID from filepath: ").b(this.g);
                JSONObject a = bb.a();
                bb.a(a, "id", this.e);
                new o("AdSession.on_error", alVar.b(), a).a();
            }
        }
        this.h = i;
        this.v = alVar;
        if (i2 >= 0) {
            this.m = i2;
        } else {
            e();
        }
        this.k = bb.b(b, "width");
        this.l = bb.b(b, "height");
        this.i = bb.b(b, "x");
        this.j = bb.b(b, "y");
        if (bb.c(b, "enable_messages") || this.o) {
            z = true;
        }
        this.n = z;
    }

    void b(o oVar) {
        JSONObject b = oVar.b();
        this.i = bb.b(b, "x");
        this.j = bb.b(b, "y");
        this.k = bb.b(b, "width");
        this.l = bb.b(b, "height");
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) getLayoutParams();
        layoutParams.setMargins(this.i, this.j, 0, 0);
        layoutParams.width = this.k;
        layoutParams.height = this.l;
        setLayoutParams(layoutParams);
        if (this.o) {
            b = bb.a();
            bb.a(b, "success", true);
            bb.b(b, "id", this.m);
            oVar.a(b).a();
        }
    }

    void c(o oVar) {
        if (bb.c(oVar.b(), String.VISIBLE)) {
            setVisibility(0);
        } else {
            setVisibility(4);
        }
        if (this.o) {
            JSONObject a = bb.a();
            bb.a(a, "success", true);
            bb.b(a, "id", this.m);
            oVar.a(a).a();
        }
    }

    public void a(JSONObject jSONObject) {
        synchronized (this.t) {
            this.t.put(jSONObject);
        }
    }

    public void b() {
    }

    public void c() {
        if (n.d() && this.q) {
            ab.a(new Runnable(this) {
                final /* synthetic */ ad a;

                {
                    this.a = r1;
                }

                public void run() {
                    String str = BuildConfig.FLAVOR;
                    synchronized (this.a.t) {
                        if (this.a.t.length() > 0) {
                            if (this.a.n) {
                                str = this.a.t.toString();
                            }
                            this.a.t = bb.b();
                        }
                    }
                    if (this.a.n) {
                        this.a.a("NativeLayer.dispatch_messages(ADC3_update(" + str + "));");
                    }
                }
            });
        }
    }

    boolean g() {
        return this.p;
    }

    void a(boolean z) {
        this.s = z;
    }
}
