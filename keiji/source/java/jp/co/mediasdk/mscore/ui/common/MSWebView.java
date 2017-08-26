package jp.co.mediasdk.mscore.ui.common;

import android.content.Context;
import android.os.Build.VERSION;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class MSWebView extends WebView {
    public MSWebView(Context context) {
        super(context);
    }

    public void a(String str) {
        setWebChromeClient(new WebChromeClient());
        getSettings().setBuiltInZoomControls(true);
        getSettings().setSupportZoom(true);
        getSettings().setDomStorageEnabled(true);
        getSettings().setSaveFormData(true);
        getSettings().setDatabaseEnabled(true);
        if (VERSION.SDK_INT <= 18) {
            getSettings().setDatabasePath(getContext().getDir("databases", 0).getPath());
        }
        WebSettings settings = getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setCacheMode(2);
        loadUrl(str);
    }
}
