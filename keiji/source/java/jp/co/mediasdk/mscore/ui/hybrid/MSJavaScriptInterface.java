package jp.co.mediasdk.mscore.ui.hybrid;

import android.app.Activity;
import android.webkit.JavascriptInterface;

public class MSJavaScriptInterface {
    private Activity a;

    public MSJavaScriptInterface(Activity activity) {
        this.a = activity;
    }

    @JavascriptInterface
    public void closeWebView() {
        this.a.finish();
    }
}
