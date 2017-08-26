package jp.co.mediasdk.mscore.ui.adformat;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.FrameLayout;
import jp.co.mediasdk.android.HandlerManager;
import jp.co.mediasdk.android.LayoutUtil;
import jp.co.mediasdk.android.LayoutUtilMarginSupport;
import jp.co.mediasdk.android.LayoutUtilWidthHeightSupport;
import jp.co.mediasdk.android.MathUtilParseSupport;
import jp.co.mediasdk.android.StringUtilEmptySupport;
import jp.co.mediasdk.android.Util;
import jp.co.mediasdk.android.WindowUtil;
import jp.co.mediasdk.mscore.ui.common.MSParameterSupport;
import jp.co.mediasdk.mscore.ui.common.MSWebView;
import org.cocos2dx.lib.BuildConfig;

public class MSAdFormatActivity extends Activity implements OnClickListener {
    private FrameLayout a;
    private MSWebView b = null;
    private MSAdVideoWebClient c;
    private MSWebView d = null;
    private MSWebView e = null;
    private ProgressDialog f;
    private String g = BuildConfig.FLAVOR;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        this.a = new FrameLayout(this);
        setContentView(this.a);
        String stringExtra = getIntent().getStringExtra("ad_url");
        this.b = new MSWebView(this);
        if (VERSION.SDK_INT > 16) {
            this.b.getSettings().setMediaPlaybackRequiresUserGesture(false);
        }
        this.c = new MSAdVideoWebClient(this, this.b);
        this.b.setWebViewClient(this.c);
        this.b.a(stringExtra);
        LayoutUtil.a(this.b, LayoutUtil.a());
        LayoutUtil.a(this.a, this.b);
        WindowUtil.a(this);
    }

    public void a() {
        MSAdListenerManager.a("type=close&status=ok");
        MSAdListenerManager.a();
    }

    public void onClick(View view) {
        finish();
    }

    public void onPause() {
        if (this.c != null) {
            this.c.a();
        }
        super.onPause();
    }

    public void onRestart() {
        super.onRestart();
    }

    public void onDestroy() {
        c();
        HandlerManager.b(MyRunnable.class);
        if (this.c != null) {
            this.c.b();
        }
        a(this.b);
        a(this.d);
        a(this.e);
        a();
        super.onDestroy();
    }

    public void a(WebView webView) {
        if (webView != null) {
            webView.setWebChromeClient(null);
            webView.setWebViewClient(null);
            if (webView.getParent() != null) {
                ((ViewGroup) webView.getParent()).removeView(webView);
            }
            webView.removeAllViews();
            webView.destroy();
        }
    }

    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            c();
        }
        return super.onKeyDown(i, keyEvent);
    }

    public void a(String str) {
        this.g = str;
        float a = (float) Util.a(this, (int) MathUtilParseSupport.b(str));
        if (this.d != null) {
            LayoutUtilMarginSupport.a(this.d, a);
        }
        WindowUtil.a(this);
    }

    public void b(String str) {
        if (this.d == null) {
            this.d = new MSWebView(this);
        }
        if (LayoutUtil.c(this.a, this.d)) {
            this.d.loadUrl(str);
        } else {
            this.d.setWebViewClient(new MSAdFormatWebClient(this));
            this.d.a(str);
            LayoutUtil.a(this.d, LayoutUtil.a());
            LayoutUtil.a(this.a, this.d);
        }
        if (!StringUtilEmptySupport.c(this.g)) {
            a(this.g);
        }
    }

    public void c(String str) {
        if (this.e == null) {
            this.e = new MSWebView(this);
        }
        if (LayoutUtil.c(this.a, this.e)) {
            this.e.loadUrl(str);
            return;
        }
        this.e.setWebViewClient(new MSAdClickWebClient());
        this.e.setVisibility(4);
        this.e.a(str);
        LayoutUtilMarginSupport.c(this.e, 0);
        LayoutUtilMarginSupport.b(this.e, 0);
        LayoutUtil.a(this.e, 1);
        LayoutUtilWidthHeightSupport.d(this.e, 1);
        LayoutUtil.a(this.a, this.e);
    }

    public void d(String str) {
        MSAdListenerManager.a(str);
    }

    public void b() {
        c();
        this.f = new ProgressDialog(this);
        this.f.setProgressStyle(0);
        CharSequence charSequence = "\u8aad\u307f\u8fbc\u307f\u4e2d";
        if (MSParameterSupport.b(MSAdParameterSupport.n)) {
            charSequence = MSParameterSupport.a(MSAdParameterSupport.n);
        }
        this.f.setMessage(charSequence);
        this.f.show();
    }

    public void c() {
        if (this.f != null) {
            try {
                this.f.dismiss();
            } catch (Exception e) {
            }
            this.f = null;
        }
    }
}
