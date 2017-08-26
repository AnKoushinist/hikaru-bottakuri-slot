package com.applovin.impl.adview;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.WebView;
import android.webkit.WebViewDatabase;
import android.widget.RelativeLayout.LayoutParams;
import com.applovin.adview.AdViewController;
import com.applovin.adview.AppLovinAdView;
import com.applovin.adview.AppLovinInterstitialActivity;
import com.applovin.adview.ClickTrackingOverlayView;
import com.applovin.impl.sdk.AppLovinAdServiceImpl;
import com.applovin.impl.sdk.cf;
import com.applovin.impl.sdk.ch;
import com.applovin.impl.sdk.dm;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdService;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdVideoPlaybackListener;
import com.applovin.sdk.AppLovinLogger;
import com.applovin.sdk.AppLovinSdk;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.util.concurrent.atomic.AtomicReference;

public class AdViewControllerImpl implements AdViewController {
    private Activity a;
    private AppLovinSdk b;
    private AppLovinAdService c;
    private AppLovinLogger d;
    private AppLovinAdSize e;
    private String f;
    private r g;
    private l h;
    private o i;
    private AppLovinAd j;
    private Runnable k;
    private Runnable l;
    private Runnable m;
    private volatile AppLovinAd n = null;
    private ClickTrackingOverlayView o = null;
    private WeakReference p = null;
    private final AtomicReference q = new AtomicReference();
    private volatile boolean r = false;
    private volatile boolean s = true;
    private volatile boolean t = false;
    private volatile boolean u = false;
    private volatile AppLovinAdLoadListener v;
    private volatile AppLovinAdDisplayListener w;
    private volatile AppLovinAdVideoPlaybackListener x;
    private volatile AppLovinAdClickListener y;
    private volatile boolean z;

    private void a(ViewGroup viewGroup, AppLovinSdk appLovinSdk, AppLovinAdSize appLovinAdSize, Context context) {
        if (viewGroup == null) {
            throw new IllegalArgumentException("No parent view specified");
        } else if (appLovinSdk == null) {
            throw new IllegalArgumentException("No sdk specified");
        } else if (appLovinAdSize == null) {
            throw new IllegalArgumentException("No ad size specified");
        } else if (context instanceof Activity) {
            this.b = appLovinSdk;
            this.c = appLovinSdk.e();
            this.d = appLovinSdk.h();
            this.e = appLovinAdSize;
            this.a = (Activity) context;
            this.j = dm.a();
            this.g = new r(this, appLovinSdk);
            this.m = new f();
            this.k = new k();
            this.l = new i();
            this.h = new l(this, appLovinSdk);
            if (a(context)) {
                this.i = k();
                viewGroup.setBackgroundColor(0);
                viewGroup.addView(this.i);
                b(this.i, appLovinAdSize);
                a(this.m);
                a(new j());
                this.r = true;
                return;
            }
            this.d.e("AppLovinAdView", "Web view database is corrupt, AdView not loaded");
        } else {
            throw new IllegalArgumentException("Specified context is not an activity");
        }
    }

    private void a(AppLovinAd appLovinAd, AppLovinAdView appLovinAdView, Uri uri) {
        if (this.o == null) {
            this.d.a("AppLovinAdView", "Creating and rendering click overlay");
            this.o = new ClickTrackingOverlayView(appLovinAdView.getContext(), this.b);
            this.o.setLayoutParams(new LayoutParams(-1, -1));
            appLovinAdView.addView(this.o);
            appLovinAdView.bringChildToFront(this.o);
            ((AppLovinAdServiceImpl) this.c).b(appLovinAd, this.f, appLovinAdView, this, uri);
            return;
        }
        this.d.a("AppLovinAdView", "Skipping click overlay rendering because it already exists");
    }

    private void a(Runnable runnable) {
        this.a.runOnUiThread(runnable);
    }

    private static boolean a(Context context) {
        try {
            if (VERSION.SDK_INT >= 11) {
                return true;
            }
            WebViewDatabase instance = WebViewDatabase.getInstance(context);
            Method declaredMethod = WebViewDatabase.class.getDeclaredMethod("getCacheTotalSize", new Class[0]);
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(instance, new Object[0]);
            return true;
        } catch (Throwable e) {
            Log.e("AppLovinAdView", "Error invoking getCacheTotalSize()", e);
            return true;
        } catch (Throwable e2) {
            Log.e("AppLovinAdView", "Error invoking getCacheTotalSize()", e2);
            return true;
        } catch (Throwable e22) {
            Log.e("AppLovinAdView", "Error invoking getCacheTotalSize()", e22);
            return true;
        } catch (Throwable e3) {
            Log.e("AppLovinAdView", "getCacheTotalSize() reported exception", e3);
            return false;
        } catch (Throwable e32) {
            Log.e("AppLovinAdView", "Unexpected error while checking DB state", e32);
            return false;
        }
    }

    private static void b(View view, AppLovinAdSize appLovinAdSize) {
        DisplayMetrics displayMetrics = view.getResources().getDisplayMetrics();
        int applyDimension = appLovinAdSize.c().equals(AppLovinAdSize.c.c()) ? -1 : appLovinAdSize.a() == -1 ? displayMetrics.widthPixels : (int) TypedValue.applyDimension(1, (float) appLovinAdSize.a(), displayMetrics);
        int applyDimension2 = appLovinAdSize.c().equals(AppLovinAdSize.c.c()) ? -1 : appLovinAdSize.b() == -1 ? displayMetrics.heightPixels : (int) TypedValue.applyDimension(1, (float) appLovinAdSize.b(), displayMetrics);
        ViewGroup.LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new LayoutParams(-2, -2);
        }
        layoutParams.width = applyDimension;
        layoutParams.height = applyDimension2;
        if (layoutParams instanceof LayoutParams) {
            layoutParams.addRule(10);
            layoutParams.addRule(9);
        }
        view.setLayoutParams(layoutParams);
    }

    private o k() {
        o oVar = new o(this.g, this.b, this.a);
        oVar.setBackgroundColor(0);
        oVar.setWillNotCacheDrawing(false);
        if (new cf(this.b).F() && VERSION.SDK_INT >= 19) {
            oVar.setLayerType(2, null);
        }
        return oVar;
    }

    public void a() {
        if (this.b == null || this.h == null || this.a == null || !this.r) {
            Log.i("AppLovinSdk", "Unable to load next ad: AppLovinAdView is not initialized.");
        } else {
            this.c.a(this.e, this.h);
        }
    }

    public void a(int i) {
        if (!this.r || !this.s) {
            return;
        }
        if (i == 8 || i == 4) {
            e();
        } else if (i == 0) {
            f();
        }
    }

    public void a(ViewGroup viewGroup, Context context, AppLovinAdSize appLovinAdSize, AppLovinSdk appLovinSdk, AttributeSet attributeSet) {
        if (viewGroup == null) {
            throw new IllegalArgumentException("No parent view specified");
        } else if (context == null) {
            Log.e("AppLovinSdk", "Unable to build AppLovinAdView: no context provided. Please use a different constructor for this view.");
        } else {
            if (appLovinAdSize == null) {
                appLovinAdSize = m.a(attributeSet);
                if (appLovinAdSize == null) {
                    appLovinAdSize = AppLovinAdSize.a;
                }
            }
            if (appLovinSdk == null) {
                appLovinSdk = AppLovinSdk.c(context);
            }
            if (appLovinSdk != null && !appLovinSdk.d()) {
                a(viewGroup, appLovinSdk, appLovinAdSize, context);
                if (m.b(attributeSet)) {
                    a();
                }
            }
        }
    }

    public void a(WebView webView) {
        if (this.n != null) {
            webView.setVisibility(0);
            try {
                if (this.w != null) {
                    this.w.adDisplayed(this.n);
                }
            } catch (Throwable th) {
                this.d.c("AppLovinAdView", "Exception while notifying ad display listener", th);
            }
        }
    }

    public void a(AppLovinAd appLovinAd) {
        a(appLovinAd, null);
    }

    void a(AppLovinAd appLovinAd, AppLovinAdView appLovinAdView, AdViewControllerImpl adViewControllerImpl, Uri uri) {
        AppLovinAdServiceImpl appLovinAdServiceImpl = (AppLovinAdServiceImpl) this.c;
        if (!new cf(this.b).J() || uri == null) {
            appLovinAdServiceImpl.a(appLovinAd, this.f, appLovinAdView, this, uri);
        } else {
            a(appLovinAd, appLovinAdView, uri);
        }
        a(new g(this, appLovinAd));
    }

    public void a(AppLovinAd appLovinAd, String str) {
        if (appLovinAd == null) {
            throw new IllegalArgumentException("No ad specified");
        } else if (!this.r) {
            Log.i("AppLovinSdk", "Unable to render ad: AppLovinAdView is not initialized.");
        } else if (appLovinAd != this.n) {
            this.d.a("AppLovinAdView", "Rendering ad #" + appLovinAd.a() + " (" + appLovinAd.b() + ") over placement: " + str);
            a(new h(this, this.n));
            this.q.set(null);
            this.n = appLovinAd;
            this.f = str;
            if (appLovinAd.b() == this.e) {
                a(this.k);
            } else if (appLovinAd.b() == AppLovinAdSize.c) {
                a(this.m);
                a(this.l);
            }
            new ch(this.b).a();
        } else {
            this.d.c("AppLovinAdView", "Ad #" + appLovinAd.a() + " is already showing, ignoring");
        }
    }

    public void a(AppLovinAdClickListener appLovinAdClickListener) {
        this.y = appLovinAdClickListener;
    }

    public void a(AppLovinAdDisplayListener appLovinAdDisplayListener) {
        this.w = appLovinAdDisplayListener;
    }

    public void a(AppLovinAdLoadListener appLovinAdLoadListener) {
        this.v = appLovinAdLoadListener;
    }

    public void a(AppLovinAdVideoPlaybackListener appLovinAdVideoPlaybackListener) {
        this.x = appLovinAdVideoPlaybackListener;
    }

    public void a(WeakReference weakReference) {
        this.p = weakReference;
    }

    public void a(boolean z) {
        this.s = z;
    }

    public void b() {
        if (this.c != null) {
            this.c.a(this.h, c());
        }
        if (this.i != null) {
            try {
                ViewParent parent = this.i.getParent();
                if (parent != null && (parent instanceof ViewGroup)) {
                    ((ViewGroup) parent).removeView(this.i);
                }
                this.i.removeAllViews();
                this.i.destroy();
                this.i = null;
            } catch (Throwable th) {
                this.d.a("AppLovinAdView", "Unable to destroy ad view", th);
            }
        }
        this.t = true;
    }

    void b(int i) {
        if (!this.t) {
            this.c.b(this.h, this.e);
            a(this.m);
        }
        a(new b(this, i));
    }

    void b(AppLovinAd appLovinAd) {
        if (appLovinAd != null) {
            this.u = true;
            if (this.t) {
                this.q.set(appLovinAd);
                this.d.a("AppLovinAdView", "Ad view has paused when an ad was recieved, ad saved for later");
            } else {
                this.c.b(this.h, this.e);
                a(appLovinAd);
            }
            a(new a(this, appLovinAd));
            return;
        }
        this.d.d("AppLovinAdView", "No provided when to the view controller");
        b(-1);
    }

    public void b(boolean z) {
        this.z = z;
    }

    public AppLovinAdSize c() {
        return this.e;
    }

    public void d() {
        if (this.r) {
            a(new h(this, this.n));
            if (this.s) {
                b();
            }
        }
    }

    public void e() {
        if (this.r) {
            this.c.a(this.h, c());
            AppLovinAd appLovinAd = this.n;
            a(this.j);
            if (appLovinAd != null) {
                this.q.set(appLovinAd);
            }
            this.t = true;
        }
    }

    public void f() {
        if (this.r) {
            if (this.u) {
                this.c.b(this.h, this.e);
            }
            AppLovinAd appLovinAd = (AppLovinAd) this.q.getAndSet(null);
            if (appLovinAd != null) {
                a(appLovinAd);
            }
            this.t = false;
        }
    }

    void g() {
        this.d.a("AppLovinAdView", "Ad: " + this.n + " with placement = \"" + this.f + "\" closed.");
        a(this.m);
        a(new h(this, this.n));
        this.n = null;
        this.f = null;
    }

    public void h() {
        if (this.o != null) {
            ViewParent parent = this.o.getParent();
            if (parent != null && (parent instanceof ViewGroup)) {
                ((ViewGroup) parent).removeView(this.o);
                this.o = null;
                return;
            }
            return;
        }
        this.d.a("AppLovinAdView", "Asked to remove an overlay when none existed. Skipping...");
    }

    public boolean i() {
        return this.z;
    }

    public void j() {
        if (!new cf(this.b).N()) {
            return;
        }
        if (this.a != null && (this.a instanceof AppLovinInterstitialActivity)) {
            ((AppLovinInterstitialActivity) this.a).dismiss();
        } else if (this.p != null) {
            x xVar = (x) this.p.get();
            if (xVar != null) {
                xVar.dismiss();
            }
        }
    }
}
