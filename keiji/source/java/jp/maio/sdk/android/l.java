package jp.maio.sdk.android;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build.VERSION;
import android.view.ViewGroup.LayoutParams;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import org.cocos2dx.lib.BuildConfig;
import org.cocos2dx.lib.GameControllerDelegate;
import twitter4j.HttpResponseCode;

final class l extends WebView implements MaioAdsListenerInterface {
    public boolean a = false;
    private bo b;
    private x c;
    private u d;
    private ba e;
    private MaioAdsListenerInterface f;
    private aj g;
    private final CountDownLatch h = new CountDownLatch(1);
    private final CountDownLatch i = new CountDownLatch(1);
    private k j;
    private boolean k = false;
    private String l;
    private float m;
    private boolean n;
    private av o;
    private int p = 0;
    private Thread q;
    private final WebChromeClient r = new n(this);
    private final WebViewClient s = new o(this);

    @SuppressLint({"SetJavaScriptEnabled"})
    public l(Context context) {
        super(context);
        setLayoutParams(new LayoutParams(-1, -1));
        setVerticalScrollbarOverlay(true);
        setBackgroundColor(0);
        if (VERSION.SDK_INT >= 11) {
            setLayerType(1, null);
        }
        super.setWebChromeClient(this.r);
        super.setWebViewClient(this.s);
        getSettings().setJavaScriptEnabled(true);
        setOnTouchListener(new m(this));
    }

    @TargetApi(19)
    private void a(float f) {
        evaluateJavascript(String.format("javascript:native_onPreparedVideo('%f');", new Object[]{Float.valueOf(f)}), null);
    }

    @TargetApi(19)
    private void a(int i, boolean z, int i2, int i3) {
        evaluateJavascript(String.format("javascript:setTimeout(native_onFinishedAd('%d', %d, %s, %d), 0);", new Object[]{Integer.valueOf(i), Integer.valueOf(i2), Boolean.valueOf(z), Integer.valueOf(i3)}), null);
    }

    @TargetApi(19)
    private void a(String str, String str2, String str3) {
        super.evaluateJavascript("javascript:" + str + "( " + str2 + ", " + str3 + ");", null);
    }

    @TargetApi(19)
    private void b(int i, int i2) {
        evaluateJavascript(String.format("javascript:setTimeout(native_onUpdateTime('%f', %f), 0);", new Object[]{Float.valueOf(((float) i) / 1000.0f), Float.valueOf(((float) i2) / 1000.0f)}), null);
    }

    private String c() {
        if (this.l != null) {
            return this.l;
        }
        try {
            this.l = URLEncoder.encode(UUID.randomUUID().toString(), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return this.l;
    }

    @TargetApi(19)
    private void c(String str) {
        super.evaluateJavascript("javascript:" + str + "();", null);
    }

    @TargetApi(19)
    private void d() {
        evaluateJavascript(String.format("javascript:setTimeout(native_onPreparedAd, 0);", new Object[0]), null);
    }

    public void a() {
        this.h.await();
    }

    public void a(int i) {
        float f = (float) (i / GameControllerDelegate.THUMBSTICK_LEFT_X);
        if (VERSION.SDK_INT >= 19) {
            a((float) i);
            return;
        }
        super.loadUrl(String.format("javascript:native_onPreparedVideo('%f');", new Object[]{Float.valueOf(f)}));
    }

    public void a(int i, int i2) {
        if (VERSION.SDK_INT >= 19) {
            b(i, i2);
            return;
        }
        super.loadUrl(String.format("javascript:setTimeout(native_onUpdateTime('%f', %f), 0);", new Object[]{Float.valueOf(((float) i) / 1000.0f), Float.valueOf(((float) i2) / 1000.0f)}));
    }

    public void a(Boolean bool) {
        this.n = bool.booleanValue();
        this.f.onFinishedAd(this.j.a.getCurrentPosition() / GameControllerDelegate.THUMBSTICK_LEFT_X, bool.booleanValue(), this.j.a.getDuration() / GameControllerDelegate.THUMBSTICK_LEFT_X, this.b.b);
    }

    public void a(String str) {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
        httpURLConnection.setInstanceFollowRedirects(false);
        httpURLConnection.connect();
        int responseCode = httpURLConnection.getResponseCode();
        httpURLConnection.disconnect();
        if (responseCode == 301 || responseCode == HttpResponseCode.FOUND) {
            String headerField = httpURLConnection.getHeaderField("Location");
            if (headerField.startsWith("market://") || headerField.startsWith("http://play.google.com") || headerField.startsWith("https://play.google.com")) {
                getContext().startActivity(new Intent("android.intent.action.VIEW", Uri.parse(headerField)));
                return;
            } else {
                a(headerField);
                return;
            }
        }
        getContext().startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + this.d.k)));
    }

    public void a(bo boVar, x xVar, u uVar, MaioAdsListenerInterface maioAdsListenerInterface, aj ajVar, ba baVar, k kVar, av avVar) {
        if (this.b == null) {
            this.h.countDown();
            this.b = boVar;
            this.c = xVar;
            this.f = maioAdsListenerInterface;
            this.g = ajVar;
            this.e = baVar;
            this.d = uVar;
            this.j = kVar;
            this.o = avVar;
            super.loadUrl("file://" + this.c.a(this.c.e).getPath());
        }
    }

    public String b(String str) {
        try {
            return bi.a(MessageDigest.getInstance("MD5").digest(str.getBytes()));
        } catch (Throwable e) {
            bc.a("MD5 string error", BuildConfig.FLAVOR, BuildConfig.FLAVOR, e);
            return null;
        }
    }

    public void b() {
        this.i.countDown();
        if (VERSION.SDK_INT >= 19) {
            d();
        } else {
            super.loadUrl(String.format("javascript:setTimeout(native_onPreparedAd, 0);", new Object[0]));
        }
    }

    @Deprecated
    public boolean canGoBack() {
        return false;
    }

    @Deprecated
    public boolean canGoBackOrForward(int i) {
        return false;
    }

    @Deprecated
    public boolean canGoForward() {
        return false;
    }

    @Deprecated
    public void clearView() {
    }

    @Deprecated
    public Bitmap getFavicon() {
        return null;
    }

    @Deprecated
    public String getTitle() {
        return null;
    }

    @Deprecated
    public String getUrl() {
        return null;
    }

    @Deprecated
    public void goBack() {
    }

    @Deprecated
    public void goBackOrForward(int i) {
    }

    @Deprecated
    public void goForward() {
    }

    @Deprecated
    public void loadData(String str, String str2, String str3) {
    }

    @Deprecated
    public void loadDataWithBaseURL(String str, String str2, String str3, String str4, String str5) {
    }

    @Deprecated
    public void loadUrl(String str) {
    }

    public void onChangedCanShow(String str, boolean z) {
    }

    public void onClickedAd(String str) {
    }

    public void onClosedAd(String str) {
    }

    public void onFailed(FailNotificationReason failNotificationReason, String str) {
    }

    public void onFinishedAd(int i, boolean z, int i2, String str) {
        if (!z) {
            this.p++;
            i = i2;
        }
        if (VERSION.SDK_INT >= 19) {
            a(i, z, i2, this.p);
        } else {
            super.loadUrl(String.format("javascript:setTimeout(native_onFinishedAd('%d', %d, %s, %d), 0);", new Object[]{Integer.valueOf(i), Integer.valueOf(i2), Boolean.valueOf(z), Integer.valueOf(this.p)}));
        }
        this.m = (float) i;
        this.n = z;
    }

    public void onInitialized() {
        bc.a("MD5 string error", BuildConfig.FLAVOR, BuildConfig.FLAVOR, null);
    }

    public void onOpenAd(String str) {
    }

    public void onStartedAd(String str) {
    }

    @Deprecated
    public boolean pageDown(boolean z) {
        return false;
    }

    @Deprecated
    public boolean pageUp(boolean z) {
        return false;
    }

    @Deprecated
    public void reload() {
    }

    @Deprecated
    public void setDownloadListener(DownloadListener downloadListener) {
    }

    @Deprecated
    public void setWebChromeClient(WebChromeClient webChromeClient) {
    }

    @Deprecated
    public void setWebViewClient(WebViewClient webViewClient) {
    }

    @Deprecated
    public void stopLoading() {
    }
}
