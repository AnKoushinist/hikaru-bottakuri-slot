package jp.maio.sdk.android;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build.VERSION;
import android.view.ViewGroup.LayoutParams;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import org.cocos2dx.lib.BuildConfig;
import org.cocos2dx.lib.GameControllerDelegate;

final class l extends WebView implements MaioAdsListenerInterface {
    public boolean a = false;
    private bj b;
    private v c;
    private av d;
    private MaioAdsListenerInterface e;
    private ag f;
    private final CountDownLatch g = new CountDownLatch(1);
    private final CountDownLatch h = new CountDownLatch(1);
    private k i;
    private boolean j = false;
    private String k;
    private float l;
    private boolean m;
    private ar n;
    private int o = 0;
    private final WebChromeClient p = new m(this);
    private final WebViewClient q = new n(this);

    @SuppressLint({"SetJavaScriptEnabled"})
    public l(Context context) {
        super(context);
        setLayoutParams(new LayoutParams(-1, -1));
        setVerticalScrollbarOverlay(true);
        setBackgroundColor(0);
        if (VERSION.SDK_INT >= 11) {
            setLayerType(1, null);
        }
        super.setWebChromeClient(this.p);
        super.setWebViewClient(this.q);
        getSettings().setJavaScriptEnabled(true);
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

    @TargetApi(19)
    private void b(String str) {
        super.evaluateJavascript("javascript:" + str + "();", null);
    }

    private String c() {
        if (this.k != null) {
            return this.k;
        }
        try {
            this.k = URLEncoder.encode(UUID.randomUUID().toString(), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return this.k;
    }

    @TargetApi(19)
    private void d() {
        evaluateJavascript(String.format("javascript:setTimeout(native_onPreparedAd, 0);", new Object[0]), null);
    }

    public String a(String str) {
        try {
            return bd.a(MessageDigest.getInstance("MD5").digest(str.getBytes()));
        } catch (Throwable e) {
            ax.a("MD5 string error", BuildConfig.FLAVOR, BuildConfig.FLAVOR, e);
            return null;
        }
    }

    public void a() {
        this.g.await();
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
        this.m = bool.booleanValue();
        onFinishedAd(this.i.a.getCurrentPosition() / GameControllerDelegate.THUMBSTICK_LEFT_X, bool.booleanValue(), this.i.a.getDuration() / GameControllerDelegate.THUMBSTICK_LEFT_X, this.b.b);
    }

    public void a(bj bjVar, v vVar, MaioAdsListenerInterface maioAdsListenerInterface, ag agVar, av avVar, k kVar, ar arVar) {
        if (this.b == null) {
            this.g.countDown();
            this.b = bjVar;
            this.c = vVar;
            this.e = maioAdsListenerInterface;
            this.f = agVar;
            this.d = avVar;
            this.i = kVar;
            this.n = arVar;
            super.loadUrl("file://" + this.c.a(this.c.e).getPath());
        }
    }

    public void b() {
        this.h.countDown();
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
            this.o++;
            i = i2;
        }
        if (VERSION.SDK_INT >= 19) {
            a(i, z, i2, this.o);
        } else {
            super.loadUrl(String.format("javascript:setTimeout(native_onFinishedAd('%d', %d, %s, %d), 0);", new Object[]{Integer.valueOf(i), Integer.valueOf(i2), Boolean.valueOf(z), Integer.valueOf(this.o)}));
        }
        this.l = (float) i;
        this.m = z;
    }

    public void onInitialized() {
        ax.a("MD5 string error", BuildConfig.FLAVOR, BuildConfig.FLAVOR, null);
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
