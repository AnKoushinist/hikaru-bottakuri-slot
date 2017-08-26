package com.vungle.publisher;

import android.webkit.WebView;
import com.vungle.log.Logger;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public final class mm extends mu {
    @Inject
    ql a;
    @Inject
    mp b;

    public final void onReceivedError(WebView webView, int i, String str, String str2) {
        Logger.e(Logger.AD_TAG, "failed to load web view: code " + i + ", " + str);
        this.a.a(new bd());
    }

    public final void onPageFinished(WebView webView, String str) {
        Logger.v(Logger.AD_TAG, "webview finished loading. appending config string");
        if (!str.toLowerCase().startsWith("javascript:")) {
            StringBuilder stringBuilder = new StringBuilder("javascript:function actionClicked(m,p){ var q = prompt('vungle:'+JSON.stringify({method:m,params:(p?p:null)}));if(q&&typeof q === 'string'){return JSON.parse(q).result;}};function noTapHighlight(){var l=document.getElementsByTagName('*');for(var i=0; i<l.length; i++){l[i].style.webkitTapHighlightColor='rgba(0,0,0,0)';}};noTapHighlight();");
            try {
                stringBuilder.append("if (typeof vungleInit == 'function') {vungleInit($webviewConfig$);};".replace("$webviewConfig$", this.b.d()));
            } catch (Throwable e) {
                Logger.e(Logger.AD_TAG, "webview failed to load config object", e);
            }
            String stringBuilder2 = stringBuilder.toString();
            Logger.v(Logger.AD_TAG, "webview client injecting javascript: " + stringBuilder2);
            webView.loadUrl(stringBuilder2);
            super.onPageFinished(webView, str);
        }
    }
}
