package com.tapjoy;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.tapjoy.TapjoyErrorMessage.ErrorType;
import java.util.concurrent.CountDownLatch;

@SuppressLint({"SetJavaScriptEnabled"})
public class TJEventOptimizer extends WebView {
    private static String a = "TJEventOptimizer";
    private static TJEventOptimizer b;
    private static CountDownLatch c;
    private Context d;
    private TJAdUnitJSBridge e;

    class a extends WebChromeClient {
        final /* synthetic */ TJEventOptimizer a;

        private a(TJEventOptimizer tJEventOptimizer) {
            this.a = tJEventOptimizer;
        }

        @TargetApi(8)
        public final boolean onConsoleMessage(ConsoleMessage consoleMessage) {
            TapjoyLog.d(TJEventOptimizer.a, "JS CONSOLE: " + consoleMessage.message() + " -- From line " + consoleMessage.lineNumber() + " of " + consoleMessage.sourceId());
            return true;
        }
    }

    class b extends WebViewClient {
        final /* synthetic */ TJEventOptimizer a;

        private b(TJEventOptimizer tJEventOptimizer) {
            this.a = tJEventOptimizer;
        }

        public final void onReceivedError(WebView webView, int i, String str, String str2) {
            super.onReceivedError(webView, i, str, str2);
            TapjoyLog.e(TJEventOptimizer.a, new TapjoyErrorMessage(ErrorType.SDK_ERROR, "Error encountered when instantiating a WebViewClient"));
        }

        public final void onPageFinished(WebView webView, String str) {
            TapjoyLog.d(TJEventOptimizer.a, "boostrap html loaded successfully");
        }
    }

    private TJEventOptimizer(Context context) {
        super(context);
        this.d = context;
        this.e = new TJAdUnitJSBridge(this.d, (WebView) this);
        getSettings().setJavaScriptEnabled(true);
        setWebViewClient(new b());
        setWebChromeClient(new a());
        loadUrl(TapjoyConnectCore.getHostURL() + TJAdUnitConstants.EVENTS_PROXY_PATH + TapjoyUtil.convertURLParams(TapjoyConnectCore.getGenericURLParams(), true));
    }

    public static void init(final Context context) {
        TapjoyLog.d(a, "Initializing event optimizer");
        c = new CountDownLatch(1);
        TapjoyUtil.runOnMainThread(new Runnable() {
            public final void run() {
                try {
                    TJEventOptimizer.b = new TJEventOptimizer(context);
                } catch (Exception e) {
                    TapjoyLog.w(TJEventOptimizer.a, e.getMessage());
                }
                TJEventOptimizer.c.countDown();
            }
        });
        c.await();
        if (b == null) {
            throw new RuntimeException("Failed to init TJEventOptimizer");
        }
    }

    public static TJEventOptimizer getInstance() {
        return b;
    }
}
