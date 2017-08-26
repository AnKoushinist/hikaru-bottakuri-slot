package jp.co.geniee.gnadsdk.interstitial;

import android.os.Handler;
import android.webkit.JavascriptInterface;

/* compiled from: InterstitialJsObj */
public class c {
    private GNInterstitialActivity a = null;
    private Handler b = new Handler();

    @JavascriptInterface
    public void buttonclick(final int i) {
        if (this.a.a == null) {
            return;
        }
        if (i == 0) {
            this.b.post(new Runnable(this) {
                final /* synthetic */ c a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.a.a.a(false);
                    this.a.a.finish();
                    if (this.a.a.a.e() != null) {
                        this.a.a.a.e().a();
                    }
                }
            });
        } else if (i > 0) {
            this.b.post(new Runnable(this) {
                final /* synthetic */ c a;

                public void run() {
                    this.a.a.a.a(false);
                    this.a.a.finish();
                    if (this.a.a.a.e() != null) {
                        this.a.a.a.e().a(i);
                    }
                }
            });
        }
    }

    public void a(GNInterstitialActivity gNInterstitialActivity) {
        this.a = gNInterstitialActivity;
    }
}
