package com.vungle.publisher;

import android.webkit.JsPromptResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import com.tapjoy.TJAdUnitConstants.String;
import com.vungle.log.Logger;
import com.vungle.publisher.jl.a;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.json.JSONObject;

@Singleton
/* compiled from: vungle */
public final class mj extends WebChromeClient {
    @Inject
    ql a;

    public final boolean onJsPrompt(WebView webView, String str, String str2, String str3, JsPromptResult jsPromptResult) {
        Logger.v(Logger.AD_TAG, "js prompt: " + str2);
        boolean startsWith = str2.startsWith("vungle:");
        if (startsWith) {
            String str4 = null;
            try {
                str4 = str2.substring(7);
                JSONObject jSONObject = new JSONObject(str4);
                String string = jSONObject.getString(String.METHOD);
                jSONObject.getString("params");
                if (String.CLOSE.equalsIgnoreCase(string)) {
                    this.a.a(new ah());
                } else if ("download".equalsIgnoreCase(string)) {
                    this.a.a(new w(a.postroll_click));
                } else if ("replay".equalsIgnoreCase(string)) {
                    this.a.a(new ai());
                } else if ("privacy".equalsIgnoreCase(string)) {
                    this.a.a(new ak());
                } else {
                    Logger.w(Logger.AD_TAG, "unknown javascript method: " + string);
                }
            } catch (IndexOutOfBoundsException e) {
                Logger.w(Logger.AD_TAG, "no javascript method call");
            } catch (Throwable e2) {
                Logger.w(Logger.AD_TAG, "invalid json " + str4, e2);
            } catch (Throwable e22) {
                Logger.e(Logger.AD_TAG, e22);
            }
            jsPromptResult.cancel();
        }
        return startsWith;
    }
}
