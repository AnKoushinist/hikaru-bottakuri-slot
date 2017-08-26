package com.tapjoy;

import android.annotation.TargetApi;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import com.tapjoy.TJAdUnitConstants.String;
import com.tapjoy.TapjoyErrorMessage.ErrorType;
import java.util.ArrayList;
import java.util.Map;
import java.util.PriorityQueue;
import org.cocos2dx.lib.BuildConfig;
import org.json.JSONArray;
import org.json.JSONObject;

public class TJWebViewJSInterface {
    WebView a;
    TJWebViewJSInterfaceListener b;
    PriorityQueue c = new PriorityQueue();

    @TargetApi(19)
    class a extends AsyncTask {
        WebView a;
        final /* synthetic */ TJWebViewJSInterface b;

        protected final /* bridge */ /* synthetic */ Object doInBackground(Object[] objArr) {
            return ((String[]) objArr)[0];
        }

        protected final /* synthetic */ void onPostExecute(Object obj) {
            String str = (String) obj;
            if (this.a == null) {
                return;
            }
            if (!str.startsWith("javascript:") || VERSION.SDK_INT < 19) {
                try {
                    this.a.loadUrl(str);
                    return;
                } catch (Exception e) {
                    TapjoyLog.e("TJWebViewJSInterface", new TapjoyErrorMessage(ErrorType.INTERNAL_ERROR, "Exception in loadUrl. Device not supported. " + e.toString()));
                    return;
                }
            }
            try {
                this.a.evaluateJavascript(str.replaceFirst("javascript:", BuildConfig.FLAVOR), null);
            } catch (Exception e2) {
                TapjoyLog.e("TJWebViewJSInterface", new TapjoyErrorMessage(ErrorType.INTERNAL_ERROR, "Exception in evaluateJavascript. Device not supported. " + e2.toString()));
            }
        }

        public a(TJWebViewJSInterface tJWebViewJSInterface, WebView webView) {
            this.b = tJWebViewJSInterface;
            this.a = webView;
        }
    }

    public TJWebViewJSInterface(WebView webView, TJWebViewJSInterfaceListener tJWebViewJSInterfaceListener) {
        this.a = webView;
        this.b = tJWebViewJSInterfaceListener;
    }

    @JavascriptInterface
    public void dispatchMethod(String str) {
        TapjoyLog.d("TJWebViewJSInterface", "dispatchMethod params: " + str);
        try {
            JSONObject jSONObject = new JSONObject(str);
            String string = jSONObject.getJSONObject(String.DATA).getString(String.METHOD);
            TapjoyLog.d("TJWebViewJSInterface", "method: " + string);
            if (this.b != null) {
                this.b.onDispatchMethod(string, jSONObject);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void callback(ArrayList arrayList, String str, String str2) {
        try {
            callbackToJavaScript(new JSONArray(arrayList), str, str2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void callback(Map map, String str, String str2) {
        try {
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(new JSONObject(map));
            callbackToJavaScript(jSONArray, str, str2);
        } catch (Exception e) {
            TapjoyLog.e("TJWebViewJSInterface", "Exception in callback to JS: " + e.toString());
            e.printStackTrace();
        }
    }

    public void callbackToJavaScript(Object obj, String str, String str2) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(String.ARGUMENTS, obj);
            if (str != null && str.length() > 0) {
                jSONObject.put(String.METHOD, str);
            }
            JSONObject jSONObject2 = new JSONObject();
            if (str2 != null && str2.length() > 0) {
                jSONObject2.put(String.CALLBACK_ID, str2);
            }
            jSONObject2.put(String.DATA, jSONObject);
            String str3 = "javascript:if(window.AndroidWebViewJavascriptBridge) AndroidWebViewJavascriptBridge._handleMessageFromAndroid('" + jSONObject2 + "');";
            if (this.c != null) {
                this.c.add(str3);
                return;
            }
            new a(this, this.a).execute(new String[]{str3});
        } catch (Exception e) {
            TapjoyLog.e("TJWebViewJSInterface", "Exception in callback to JS: " + e.toString());
            e.printStackTrace();
        }
    }

    public void flushMessageQueue() {
        if (this.c != null) {
            while (this.c.size() != 0) {
                String str = (String) this.c.poll();
                new a(this, this.a).execute(new String[]{str});
            }
            this.c = null;
        }
    }
}
