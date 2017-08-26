package jp.co.geniee.gnadsdk.a;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.webkit.WebView;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.cocos2dx.lib.GameControllerDelegate;

/* compiled from: GNUtil */
public final class f {
    private static String a = null;

    /* compiled from: GNUtil */
    private static class a implements Runnable {
        private final String a;
        private final String b;
        private final int c;
        private String d;

        public a(String str, int i, String str2) {
            this.a = str;
            this.c = i;
            this.b = str2;
        }

        public void run() {
            DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
            HttpParams params = defaultHttpClient.getParams();
            try {
                HttpUriRequest httpGet = new HttpGet(this.a);
                HttpConnectionParams.setConnectionTimeout(params, GameControllerDelegate.THUMBSTICK_LEFT_X);
                HttpConnectionParams.setSoTimeout(params, this.c * GameControllerDelegate.THUMBSTICK_LEFT_X);
                if (this.b != null) {
                    params.setParameter("http.useragent", this.b);
                }
                this.d = EntityUtils.toString(defaultHttpClient.execute(httpGet).getEntity());
            } catch (Throwable e) {
                Log.e("GNUtil", "ClientProtocolException", e);
            } catch (Throwable e2) {
                Log.e("GNUtil", "IOException", e2);
            } catch (Throwable e22) {
                Log.e("GNUtil", "ParseException", e22);
            } catch (Throwable e222) {
                Log.e("GNUtil", "Exception", e222);
            } finally {
                defaultHttpClient.getConnectionManager().shutdown();
            }
        }

        public String a() {
            return this.d;
        }
    }

    public static String a(Context context) {
        if (context == null) {
            return a;
        }
        if (a == null) {
            WebView webView = new WebView(context);
            a = webView.getSettings().getUserAgentString();
            webView.setWebChromeClient(null);
            webView.setWebViewClient(null);
            webView.destroy();
        }
        return a;
    }

    public static String a(String str, int i, String str2) {
        try {
            Object aVar = new a(str, i, str2);
            Thread thread = new Thread(aVar);
            thread.start();
            thread.join((long) (i * GameControllerDelegate.THUMBSTICK_LEFT_X));
            return aVar.a();
        } catch (InterruptedException e) {
            return null;
        }
    }

    public static final boolean b(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null) {
            return false;
        }
        return activeNetworkInfo.isConnected();
    }
}
