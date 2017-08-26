package jp.co.geniee.a.a.a;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;
import android.webkit.WebView;
import com.d.a.a.c;
import com.tapjoy.TJAdUnitConstants.String;
import org.cocos2dx.lib.BuildConfig;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: GNNativeAd */
public class a {
    private final jp.co.geniee.gnadsdk.a.a A;
    private b B = null;
    private Context C;
    public String a = BuildConfig.FLAVOR;
    public String b = BuildConfig.FLAVOR;
    public String c = BuildConfig.FLAVOR;
    public String d = BuildConfig.FLAVOR;
    public String e = BuildConfig.FLAVOR;
    public double f = 0.0d;
    public String g = BuildConfig.FLAVOR;
    public int h = 0;
    public int i = 0;
    public double j = 0.0d;
    public String k = BuildConfig.FLAVOR;
    public int l = 0;
    public int m = 0;
    public String n = BuildConfig.FLAVOR;
    public String o = BuildConfig.FLAVOR;
    public double p = 0.0d;
    public String q = BuildConfig.FLAVOR;
    public String r = BuildConfig.FLAVOR;
    public String s = BuildConfig.FLAVOR;
    public String t = BuildConfig.FLAVOR;
    public String u = BuildConfig.FLAVOR;
    private String v = BuildConfig.FLAVOR;
    private String w = BuildConfig.FLAVOR;
    private String x = BuildConfig.FLAVOR;
    private boolean y = false;
    private boolean z = false;

    public a(Context context, String str, JSONObject jSONObject, b bVar) {
        int i = 0;
        this.C = context;
        this.A = new jp.co.geniee.gnadsdk.a.a("GNAdSDK", Integer.MAX_VALUE);
        this.a = str;
        try {
            JSONObject jSONObject2;
            JSONArray jSONArray;
            this.b = a(jSONObject, "advertiser");
            if (jSONObject.has("app")) {
                jSONObject2 = jSONObject.getJSONObject("app");
                this.n = a(jSONObject2, "appName");
                this.o = a(jSONObject2, "appid");
                this.p = b(jSONObject2, "rating");
                this.q = a(jSONObject2, "storeURL");
                this.r = a(jSONObject2, "targetAge");
            }
            this.e = a(jSONObject, "cta");
            this.d = a(jSONObject, "description");
            if (jSONObject.has("icon")) {
                jSONObject2 = jSONObject.getJSONObject("icon");
                this.f = b(jSONObject2, "aspectRatio");
                this.g = a(jSONObject2, String.URL);
                this.h = c(jSONObject2, "height");
                this.i = c(jSONObject2, "width");
            }
            this.v = a(jSONObject, "landingURL");
            if (jSONObject.has("screenshots")) {
                jSONObject2 = jSONObject.getJSONObject("screenshots");
                this.j = b(jSONObject2, "aspectRatio");
                this.k = a(jSONObject2, String.URL);
                this.l = c(jSONObject2, "height");
                this.m = c(jSONObject2, "width");
            }
            this.c = a(jSONObject, String.TITLE);
            if (jSONObject.has("trackings")) {
                jSONArray = jSONObject.getJSONArray("trackings");
            } else {
                jSONArray = new JSONArray();
            }
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                this.w += jSONArray.getString(i2);
            }
            jSONArray = jSONObject.has("clicktrackers") ? jSONObject.getJSONArray("clicktrackers") : new JSONArray();
            while (i < jSONArray.length()) {
                this.x += jSONArray.getString(i);
                i++;
            }
            if (jSONObject.has("optout")) {
                jSONObject2 = jSONObject.getJSONObject("optout");
                this.s = a(jSONObject2, "text");
                this.t = a(jSONObject2, "image_url");
                this.u = a(jSONObject2, String.URL);
            }
            this.B = bVar;
            this.y = true;
        } catch (JSONException e) {
            Log.e("GNNativeAd", "parse json error : " + e.toString());
        }
    }

    public void a() {
        if (this.z) {
            this.A.b("GNNativeAd", "onTrackingImpression : impression ignored, reported already");
            return;
        }
        if (this.w.length() > 0) {
            final Handler handler = new Handler();
            new Thread(new Runnable(this) {
                final /* synthetic */ a b;

                public void run() {
                    handler.post(new Runnable(this) {
                        final /* synthetic */ AnonymousClass1 a;

                        {
                            this.a = r1;
                        }

                        @SuppressLint({"SetJavaScriptEnabled"})
                        public void run() {
                            WebView webView = new WebView(this.a.b.C.getApplicationContext());
                            webView.getSettings().setJavaScriptEnabled(true);
                            String str = "<html><head></head><body>" + this.a.b.w + "</body></html>";
                            webView.loadDataWithBaseURL("http://a-mobile.genieesspv.jp", str, "text/html", c.DEFAULT_CHARSET, null);
                            this.a.b.A.a("GNNativeAd", "onTrackingImpression tag: " + str);
                        }
                    });
                }
            }).start();
        }
        this.z = true;
    }

    public void b() {
        if (this.x.length() > 0) {
            final Handler handler = new Handler();
            new Thread(new Runnable(this) {
                final /* synthetic */ a b;

                public void run() {
                    handler.post(new Runnable(this) {
                        final /* synthetic */ AnonymousClass2 a;

                        {
                            this.a = r1;
                        }

                        @SuppressLint({"SetJavaScriptEnabled"})
                        public void run() {
                            WebView webView = new WebView(this.a.b.C.getApplicationContext());
                            webView.getSettings().setJavaScriptEnabled(true);
                            String str = "<html><head></head><body>" + this.a.b.x + "</body></html>";
                            webView.loadDataWithBaseURL("http://a-mobile.genieesspv.jp", str, "text/html", c.DEFAULT_CHARSET, null);
                            this.a.b.A.a("GNNativeAd", "onTrackingClick tag: " + str);
                        }
                    });
                }
            }).start();
        }
        if (this.v.length() > 0) {
            this.A.a("GNNativeAd", "onTrackingClick landingURL: " + this.v);
            boolean z = false;
            if (!(this.B == null || this.B.a() == null)) {
                z = this.B.a().onShouldStartInternalBrowserWithClick(this.v);
            }
            if (!z) {
                a(this.v);
                return;
            }
            return;
        }
        this.A.b("GNNativeAd", "onTrackingClick : landingURL is empty!");
    }

    protected boolean c() {
        return this.y;
    }

    protected void a(int i) {
        this.A.a(i);
    }

    private void a(String str) {
        this.A.a("GNNativeAd", "onShowWebPage : " + str);
        if (this.B != null) {
            this.B.b().startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
        }
    }

    private String a(JSONObject jSONObject, String str) {
        return (!jSONObject.has(str) || jSONObject.isNull(str)) ? BuildConfig.FLAVOR : jSONObject.getString(str);
    }

    private double b(JSONObject jSONObject, String str) {
        return (!jSONObject.has(str) || jSONObject.isNull(str)) ? 0.0d : jSONObject.getDouble(str);
    }

    private int c(JSONObject jSONObject, String str) {
        return (!jSONObject.has(str) || jSONObject.isNull(str)) ? 0 : jSONObject.getInt(str);
    }
}
