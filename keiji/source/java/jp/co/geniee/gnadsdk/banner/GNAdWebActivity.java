package jp.co.geniee.gnadsdk.banner;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.tapjoy.TapjoyConnectCore;

public class GNAdWebActivity extends Activity {
    private WebView a;
    private b b;

    private class a extends WebViewClient {
        final /* synthetic */ GNAdWebActivity a;

        private a(GNAdWebActivity gNAdWebActivity) {
            this.a = gNAdWebActivity;
        }

        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            super.onPageStarted(webView, str, bitmap);
            this.a.b.a(webView.canGoBack(), webView.canGoForward());
        }

        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            this.a.b.a(webView.canGoBack(), webView.canGoForward());
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            super.shouldOverrideUrlLoading(webView, str);
            return false;
        }
    }

    class b extends LinearLayout {
        final /* synthetic */ GNAdWebActivity a;
        private Button b;
        private Button c;

        public b(final GNAdWebActivity gNAdWebActivity, Context context) {
            this.a = gNAdWebActivity;
            super(context);
            setOrientation(0);
            setGravity(16);
            a("Close", new OnClickListener(this) {
                final /* synthetic */ b b;

                public void onClick(View view) {
                    this.b.a.finish();
                }
            });
            this.b = a("Prev", new OnClickListener(this) {
                final /* synthetic */ b b;

                public void onClick(View view) {
                    this.b.a.a.goBack();
                }
            });
            this.c = a("Next", new OnClickListener(this) {
                final /* synthetic */ b b;

                public void onClick(View view) {
                    this.b.a.a.goForward();
                }
            });
            a("Reload", new OnClickListener(this) {
                final /* synthetic */ b b;

                public void onClick(View view) {
                    this.b.a.a.reload();
                }
            });
            a("Browser", new OnClickListener(this) {
                final /* synthetic */ b b;

                public void onClick(View view) {
                    this.b.a.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(this.b.a.a.getUrl())));
                }
            });
        }

        public void a(boolean z, boolean z2) {
            int i;
            int i2 = 0;
            Button button = this.b;
            if (z) {
                i = 0;
            } else {
                i = 4;
            }
            button.setVisibility(i);
            Button button2 = this.c;
            if (!z2) {
                i2 = 4;
            }
            button2.setVisibility(i2);
        }

        private Button a(String str, OnClickListener onClickListener) {
            View button = new Button(getContext());
            button.setGravity(17);
            button.setPadding(0, button.getPaddingTop(), 0, button.getPaddingBottom());
            button.setText(str);
            button.setTextColor(-1);
            button.setOnClickListener(onClickListener);
            addView(button, new LayoutParams(0, -2, TapjoyConnectCore.DEFAULT_CURRENCY_MULTIPLIER));
            return button;
        }
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        String stringExtra = getIntent().getStringExtra("URL");
        this.a = new WebView(this);
        this.a.getSettings().setJavaScriptEnabled(true);
        View linearLayout;
        if (VERSION.SDK_INT < 8) {
            this.a.setWebViewClient(new a());
            requestWindowFeature(1);
            this.b = new b(this, this);
            linearLayout = new LinearLayout(this);
            linearLayout.setOrientation(1);
            linearLayout.addView(this.a, new LayoutParams(-1, 0, TapjoyConnectCore.DEFAULT_CURRENCY_MULTIPLIER));
            linearLayout.addView(this.b, new LayoutParams(-1, -2));
            setContentView(linearLayout);
            this.a.loadUrl(stringExtra);
        } else {
            this.a.setWebViewClient(new a());
            requestWindowFeature(1);
            this.b = new b(this, this);
            linearLayout = new LinearLayout(this);
            linearLayout.setOrientation(1);
            linearLayout.addView(this.a, new LayoutParams(-1, 0, TapjoyConnectCore.DEFAULT_CURRENCY_MULTIPLIER));
            linearLayout.addView(this.b, new LayoutParams(-1, -2));
            setContentView(linearLayout);
            this.a.loadUrl(stringExtra);
        }
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (keyEvent.getAction() != 0 || keyEvent.getKeyCode() != 4 || !this.a.canGoBack()) {
            return super.dispatchKeyEvent(keyEvent);
        }
        this.a.goBack();
        return true;
    }
}
