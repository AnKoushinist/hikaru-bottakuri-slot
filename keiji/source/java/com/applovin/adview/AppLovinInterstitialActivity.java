package com.applovin.adview;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;
import com.applovin.impl.adview.AdViewControllerImpl;
import com.applovin.impl.adview.AppLovinVideoView;
import com.applovin.impl.adview.ah;
import com.applovin.impl.adview.ao;
import com.applovin.impl.adview.ap;
import com.applovin.impl.adview.aq;
import com.applovin.impl.adview.ar;
import com.applovin.impl.adview.s;
import com.applovin.impl.adview.u;
import com.applovin.impl.adview.w;
import com.applovin.impl.sdk.AppLovinAdImpl;
import com.applovin.impl.sdk.AppLovinAdImpl.AdTarget;
import com.applovin.impl.sdk.AppLovinAdServiceImpl;
import com.applovin.impl.sdk.AppLovinSdkImpl;
import com.applovin.impl.sdk.aw;
import com.applovin.impl.sdk.cf;
import com.applovin.impl.sdk.dm;
import com.applovin.impl.sdk.n;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdType;
import com.applovin.sdk.AppLovinAdVideoPlaybackListener;
import com.applovin.sdk.AppLovinLogger;
import com.applovin.sdk.AppLovinSdkUtils;
import com.tapjoy.TapjoyConnectCore;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

public class AppLovinInterstitialActivity extends Activity implements w {
    public static volatile ah a = null;
    private View A;
    private u B;
    private View C;
    private s D;
    private volatile UUID E;
    private ImageView F;
    private WeakReference G = new WeakReference(null);
    private aw H;
    private ap I;
    private ar J;
    private AppLovinAdView b;
    private ah c;
    private volatile boolean d = false;
    private AppLovinLogger e;
    private cf f;
    private AppLovinSdkImpl g;
    private volatile AppLovinAdImpl h = dm.a();
    private volatile boolean i = false;
    private volatile boolean j = false;
    private volatile boolean k = false;
    private volatile boolean l = false;
    private volatile boolean m = false;
    private volatile boolean n = false;
    private volatile boolean o = false;
    private volatile boolean p = false;
    private volatile boolean q = false;
    private boolean r = false;
    private volatile boolean s = false;
    private boolean t = false;
    private int u = 0;
    private AtomicBoolean v = new AtomicBoolean(false);
    private Handler w;
    private FrameLayout x;
    private AppLovinVideoView y;
    private u z;

    private boolean A() {
        return !J() && AppLovinAdType.b.equals(this.h.c()) && this.f.P() && this.H != null;
    }

    private void B() {
        if (!this.d) {
            if (this.b != null) {
                this.b.setAdDisplayListener(new k(this));
                this.b.setAdClickListener(new l(this));
                this.h = (AppLovinAdImpl) this.c.d();
                j();
                m();
                File a = this.g.o().a(this.h.k(), (Context) this, false);
                if (a != null) {
                    a(a);
                } else {
                    this.i = true;
                    this.g.h().d("AppLovinInterstitialActivity", "Video file was missing or null - please make sure your app has the WRITE_EXTERNAL_STORAGE permission!");
                    C();
                }
                this.z.bringToFront();
                if (q() && this.A != null) {
                    this.A.bringToFront();
                }
                if (this.B != null) {
                    this.B.bringToFront();
                }
                this.b.a(this.h, this.c.j());
                this.c.a(true);
                return;
            }
            a("AdView was null");
        }
    }

    private void C() {
        H();
        if (this.y != null) {
            this.y.stopPlayback();
        }
        View frameLayout = new FrameLayout(this);
        frameLayout.setLayoutParams(new LayoutParams(-1, -1));
        frameLayout.setBackgroundColor(-16777216);
        frameLayout.addView(this.b);
        if (!(this.x == null || this.B == null)) {
            this.x.removeView(this.B);
            if (this.C != null) {
                this.x.removeView(this.C);
            }
        }
        if (q() && this.A != null) {
            if (this.x != null) {
                this.x.removeView(this.A);
            }
            frameLayout.addView(this.A);
            this.A.bringToFront();
        }
        if (this.x != null) {
            this.x.removeView(this.z);
        }
        frameLayout.addView(this.z);
        setContentView(frameLayout);
        this.z.bringToFront();
        if (this.h.f() >= 0.0f) {
            a(dm.c(this.h.f()), this.z);
        } else if (this.h.f() == -2.0f) {
            this.z.setVisibility(0);
        } else {
            a(0, this.z);
        }
        this.q = true;
    }

    private void D() {
        boolean z = !E();
        a(z);
        String m = z ? this.h.m() : this.h.n();
        int c = c(this.f.C());
        File a = this.g.o().a(m, (Context) this, true);
        if (a != null) {
            AppLovinSdkUtils.a(this.F, Uri.fromFile(a), c);
        } else {
            this.g.h().c("AppLovinInterstitialActivity", "Attempt to toggle mute but no cached mute image file found.");
        }
    }

    private boolean E() {
        return this.s;
    }

    private void F() {
        Editor edit = K().edit();
        edit.putInt("com.applovin.interstitial.last_video_position", this.y.getCurrentPosition());
        edit.putBoolean("com.applovin.interstitial.should_resume_video", true);
        edit.commit();
        this.y.pause();
    }

    private void G() {
        SharedPreferences K = K();
        if (this.y != null) {
            int duration = this.y.getDuration();
            int i = K.getInt("com.applovin.interstitial.last_video_position", duration);
            duration -= i;
            v();
            this.y.seekTo(i);
            this.y.start();
            a(duration);
        }
    }

    private void H() {
        if (!this.m) {
            double I = I();
            String a = this.h.a((int) I, this.c != null ? this.c.j() : null);
            if (AppLovinSdkUtils.d(a)) {
                this.g.r().a(a, null);
            } else {
                this.e.d("AppLovinInterstitialActivity", "Received invalid placement aware parameterized video completion url. No postback dispatched.");
            }
            a(this.h, I, J());
        }
    }

    private double I() {
        if (this.n) {
            return 100.0d;
        }
        if (this.y != null) {
            return 100.0d * (((double) this.y.getCurrentPosition()) / ((double) this.y.getDuration()));
        }
        this.e.d("AppLovinInterstitialActivity", "No video view detected on video end");
        return 0.0d;
    }

    private boolean J() {
        return I() >= 95.0d;
    }

    private SharedPreferences K() {
        return getSharedPreferences("com.applovin.interstitial.sharedpreferences", 0);
    }

    private static int a(Display display) {
        return display.getWidth() == display.getHeight() ? 3 : display.getWidth() < display.getHeight() ? 1 : 2;
    }

    private void a(int i) {
        b((int) (((float) i) - dm.a((float) TapjoyConnectCore.DEFAULT_CURRENCY_MULTIPLIER)));
    }

    private void a(int i, UUID uuid) {
        if (this.D != null && uuid.equals(this.E)) {
            if (i <= 0) {
                this.D.setVisibility(8);
                this.t = true;
            } else if (!this.t) {
                int i2 = i - 1;
                this.D.a(i2);
                this.w.postDelayed(new g(this, i2, uuid), 1000);
            }
        }
    }

    private void a(long j, u uVar) {
        this.w.postDelayed(new f(this, uVar), j);
    }

    private void a(View view, boolean z, long j) {
        float f = TapjoyConnectCore.DEFAULT_CURRENCY_MULTIPLIER;
        float f2 = z ? 0.0f : TapjoyConnectCore.DEFAULT_CURRENCY_MULTIPLIER;
        if (!z) {
            f = 0.0f;
        }
        Animation alphaAnimation = new AlphaAnimation(f2, f);
        alphaAnimation.setDuration(j);
        alphaAnimation.setAnimationListener(new b(this, view, z));
        view.startAnimation(alphaAnimation);
    }

    private void a(AppLovinAd appLovinAd) {
        AppLovinAdDisplayListener f = this.c.f();
        if (f != null) {
            f.adDisplayed(appLovinAd);
        }
        this.j = true;
    }

    private void a(AppLovinAd appLovinAd, double d, boolean z) {
        this.m = true;
        AppLovinAdVideoPlaybackListener e = this.c.e();
        if (e != null) {
            e.videoPlaybackEnded(appLovinAd, d, z);
        }
    }

    private void a(File file) {
        Uri fromFile = Uri.fromFile(file);
        this.y = new AppLovinVideoView(this);
        this.y.setOnPreparedListener(new m(this));
        this.y.setOnCompletionListener(new p(this));
        this.y.setOnErrorListener(new q(this));
        this.y.setVideoURI(fromFile);
        this.y.setLayoutParams(new LayoutParams(-1, -1, 17));
        this.y.setOnTouchListener(new AppLovinTouchToClickListener(this, new s(this)));
        this.x.addView(this.y);
        setContentView(this.x);
        s();
    }

    private void a(boolean z) {
        this.s = z;
        MediaPlayer mediaPlayer = (MediaPlayer) this.G.get();
        if (mediaPlayer != null) {
            int i = z ? 0 : 1;
            mediaPlayer.setVolume((float) i, (float) i);
        }
    }

    private void b(int i) {
        a(i, this.E);
    }

    private void b(AppLovinAd appLovinAd) {
        c(appLovinAd);
        dismiss();
    }

    private int c(int i) {
        return AppLovinSdkUtils.a(this, i);
    }

    private void c(AppLovinAd appLovinAd) {
        if (!this.k) {
            this.k = true;
            if (this.c != null) {
                AppLovinAdDisplayListener f = this.c.f();
                if (f != null) {
                    f.adHidden(appLovinAd);
                }
            }
        }
    }

    private boolean c() {
        return (this.c == null || this.f == null || this.f.a()) ? true : (this.f.c() && this.o) ? true : this.f.b() && this.q;
    }

    private void d() {
        if (this.h.p() && AppLovinSdkUtils.d(this.h.q())) {
            this.g.h().a("AppLovinInterstitialActivity", "Clicking through video...");
            e();
            return;
        }
        f();
        g();
    }

    private void d(AppLovinAd appLovinAd) {
        if (!this.l) {
            this.l = true;
            AppLovinAdVideoPlaybackListener e = this.c.e();
            if (e != null) {
                e.videoPlaybackBegan(appLovinAd);
            }
        }
    }

    private void e() {
        try {
            ((AppLovinAdServiceImpl) this.g.e()).a(this.h, this.c.j(), this.b, Uri.parse(this.h.q()));
            AppLovinAdClickListener g = this.c.g();
            if (g != null) {
                g.adClicked(this.h);
            }
        } catch (Throwable th) {
            this.g.h().b("AppLovinInterstitialActivity", "Encountered error while clicking through video.", th);
        }
    }

    private void f() {
        if (this.f.z() && this.D != null && this.D.getVisibility() != 8) {
            a(this.D, this.D.getVisibility() == 4, 750);
        }
    }

    private void g() {
        ao s = this.h.s();
        if (s != null && s.e() && !this.q && this.I != null) {
            a(this.I, this.I.getVisibility() == 4, (long) s.f());
        }
    }

    private void h() {
        Editor edit = K().edit();
        edit.putBoolean("com.applovin.interstitial.should_resume_video", false);
        edit.putInt("com.applovin.interstitial.last_video_position", 0);
        edit.commit();
    }

    private boolean i() {
        return K().getInt("com.applovin.interstitial.last_video_position", 0) > 0 ? this.s : this.f.I() ? this.g.b().g() : this.f.G();
    }

    private void j() {
        ViewGroup.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        this.x = new FrameLayout(this);
        this.x.setLayoutParams(layoutParams);
        this.x.setBackgroundColor(-16777216);
        this.w = new Handler();
    }

    private void k() {
        if (this.v.compareAndSet(false, true)) {
            if (this.f.k()) {
                this.e.d("AppLovinInterstitialActivity", "Handling media player error - Finishing activity...");
                finish();
            } else {
                this.e.d("AppLovinInterstitialActivity", "Handling media player error - Showing poststitial...");
                C();
            }
            this.e.d("AppLovinInterstitialActivity", "Finished handling media player error.");
            return;
        }
        this.e.d("AppLovinInterstitialActivity", "Already handled media player error. Doing nothing...");
    }

    private void l() {
        d(this.h);
        this.y.start();
        b(u());
    }

    private void m() {
        int i = 3;
        this.z = u.a(this.g, this, this.h.i());
        this.z.setVisibility(8);
        this.z.setOnClickListener(new t(this));
        int c = c(this.f.m());
        ViewGroup.LayoutParams layoutParams = new LayoutParams(c, c, (this.f.x() ? 3 : 5) | 48);
        this.z.a(c);
        int c2 = c(this.f.o());
        int c3 = c(this.f.q());
        layoutParams.setMargins(c3, c2, c3, c2);
        this.x.addView(this.z, layoutParams);
        this.B = u.a(this.g, this, this.h.i());
        this.B.setVisibility(8);
        this.B.setOnClickListener(new u(this));
        layoutParams = new LayoutParams(c, c, (this.f.w() ? 3 : 5) | 48);
        layoutParams.setMargins(c3, c2, c3, c2);
        this.B.a(c);
        this.x.addView(this.B, layoutParams);
        this.B.bringToFront();
        if (q()) {
            int c4 = c(new cf(this.g).r());
            this.A = new View(this);
            this.A.setBackgroundColor(0);
            this.A.setVisibility(8);
            this.C = new View(this);
            this.C.setBackgroundColor(0);
            this.C.setVisibility(8);
            c += c4;
            int c5 = c2 - c(5);
            ViewGroup.LayoutParams layoutParams2 = new LayoutParams(c, c, (this.f.x() ? 3 : 5) | 48);
            layoutParams2.setMargins(c5, c5, c5, c5);
            if (!this.f.w()) {
                i = 5;
            }
            ViewGroup.LayoutParams layoutParams3 = new LayoutParams(c, c, i | 48);
            layoutParams3.setMargins(c5, c5, c5, c5);
            this.A.setOnClickListener(new v(this));
            this.C.setOnClickListener(new w(this));
            this.x.addView(this.A, layoutParams2);
            this.A.bringToFront();
            this.x.addView(this.C, layoutParams3);
            this.C.bringToFront();
        }
    }

    private void n() {
        if (this.F == null) {
            try {
                this.F = new ImageView(this);
                if (o()) {
                    this.g.h().a("AppLovinInterstitialActivity", "Mute button should be hidden");
                    return;
                }
                int c = c(this.f.C());
                ViewGroup.LayoutParams layoutParams = new LayoutParams(c, c, this.f.D());
                this.F.setScaleType(ScaleType.FIT_CENTER);
                int c2 = c(this.f.E());
                layoutParams.setMargins(c2, c2, c2, c2);
                this.s = i();
                String m = i() ? this.h.m() : this.h.n();
                File a = this.g.o().a(m, (Context) this, true);
                if (a != null) {
                    this.g.h().a("AppLovinInterstitialActivity", "Added mute button with params: " + layoutParams);
                    AppLovinSdkUtils.a(this.F, Uri.fromFile(a), c);
                    this.F.setClickable(true);
                    this.F.setOnClickListener(new c(this));
                    this.x.addView(this.F, layoutParams);
                    this.F.bringToFront();
                    return;
                }
                this.g.h().d("AppLovinInterstitialActivity", "Attempting to add mute button but could not find cached file with intialFilename = " + m);
            } catch (Throwable e) {
                this.g.h().a("AppLovinInterstitialActivity", "Failed to attach mute button", e);
            }
        }
    }

    private boolean o() {
        return !this.f.A() ? true : this.f.B() ? i() ? false : !this.f.H() : false;
    }

    private void p() {
        runOnUiThread(new d(this));
    }

    private boolean q() {
        return this.f.r() > 0;
    }

    private void r() {
        runOnUiThread(new e(this));
    }

    private void s() {
        if (this.h.e() >= 0.0f) {
            u uVar = (!this.r || this.B == null) ? this.z : this.B;
            a(dm.c(this.h.e()), uVar);
        }
    }

    private void t() {
        if (this.D == null) {
            this.D = new s(this);
            int w = w();
            this.D.c(w);
            this.D.b((float) this.f.h());
            this.D.d(w);
            this.D.a((float) this.f.g());
            this.D.b(u());
            ViewGroup.LayoutParams layoutParams = new LayoutParams(c(this.f.f()), c(this.f.f()), this.f.v());
            int c = c(this.f.u());
            layoutParams.setMargins(c, c, c, c);
            this.x.addView(this.D, layoutParams);
            this.D.bringToFront();
            s sVar = this.D;
            w = (!this.f.i() || u() <= 0) ? 4 : 0;
            sVar.setVisibility(w);
        }
    }

    private int u() {
        int g = this.h.g();
        return (g <= 0 && this.f.t()) ? this.u + 1 : g;
    }

    private void v() {
        this.E = UUID.randomUUID();
    }

    private int w() {
        return Color.parseColor(this.f.d());
    }

    private void x() {
        ao s = this.h.s();
        if (AppLovinSdkUtils.d(this.h.r()) && s != null && this.I == null) {
            this.e.b("AppLovinInterstitialActivity", "Attaching video button...");
            this.I = y();
            double b = ((double) s.b()) / 100.0d;
            ViewGroup.LayoutParams layoutParams = new LayoutParams((int) ((((double) s.a()) / 100.0d) * ((double) this.y.getWidth())), (int) (((double) this.y.getHeight()) * b), s.d());
            int c = c(s.c());
            layoutParams.setMargins(c, c, c, c);
            this.x.addView(this.I, layoutParams);
            this.I.bringToFront();
            if (s.i() > 0.0f) {
                this.I.setVisibility(4);
                this.w.postDelayed(new h(this, s), dm.c(s.i()));
            }
            if (s.j() > 0.0f) {
                this.w.postDelayed(new i(this, s), dm.c(s.j()));
            }
        }
    }

    private ap y() {
        this.e.a("AppLovinInterstitialActivity", "Create video button with HTML = " + this.h.r());
        aq aqVar = new aq(this.g);
        this.J = new j(this);
        aqVar.a(new WeakReference(this.J));
        ap apVar = new ap(aqVar, getApplicationContext());
        apVar.a(this.h.r());
        return apVar;
    }

    private void z() {
        if (A()) {
            F();
            this.H.b();
            return;
        }
        a();
    }

    public void a() {
        if (this.h.o()) {
            dismiss();
        } else {
            C();
        }
    }

    public void a(String str) {
        try {
            Log.e("AppLovinInterstitialActivity", "Failed to properly render an Interstitial Activity, due to error: " + str, new Throwable("Initialized = " + ah.a + "; CleanedUp = " + ah.b));
            c(dm.a());
        } catch (Throwable e) {
            Log.e("AppLovinInterstitialActivity", "Failed to show a video ad due to error:", e);
        }
        finish();
    }

    public void b() {
        G();
    }

    public void dismiss() {
        ((AdViewControllerImpl) this.b.getAdViewController()).b(true);
        h();
        H();
        if (this.c != null) {
            if (this.h != null) {
                c(this.h);
            }
            this.c.a(false);
            this.c.k();
        }
        finish();
    }

    public void onBackPressed() {
        if (c()) {
            this.e.a("AppLovinInterstitialActivity", "Back button was pressed; forwarding to Android for handling...");
            super.onBackPressed();
            return;
        }
        try {
            if (this.r && this.B != null && this.B.getVisibility() == 0 && this.B.getAlpha() > 0.0f && !this.o) {
                this.e.a("AppLovinInterstitialActivity", "Back button was pressed; forwarding as a click to skip button.");
                this.B.performClick();
            } else if (this.z == null || this.z.getVisibility() != 0 || this.z.getAlpha() <= 0.0f) {
                this.e.a("AppLovinInterstitialActivity", "Back button was pressed, but was not eligible for dismissal.");
            } else {
                this.e.a("AppLovinInterstitialActivity", "Back button was pressed; forwarding as a click to close button.");
                this.z.performClick();
            }
        } catch (Exception e) {
            super.onBackPressed();
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        String stringExtra = getIntent().getStringExtra("com.applovin.interstitial.wrapper_id");
        if (stringExtra == null || stringExtra.isEmpty()) {
            a("Wrapper ID is null");
        } else {
            this.c = ah.b(stringExtra);
            if (this.c == null && a != null) {
                this.c = a;
            }
            if (this.c != null) {
                AppLovinAd d = this.c.d();
                this.g = (AppLovinSdkImpl) this.c.c();
                this.e = this.c.c().h();
                this.f = new cf(this.c.c());
                if (d != null) {
                    Display defaultDisplay = ((WindowManager) getSystemService("window")).getDefaultDisplay();
                    int a = a(defaultDisplay);
                    int rotation = defaultDisplay.getRotation();
                    boolean z = (a == 2 && rotation == 0) || ((a == 2 && rotation == 2) || ((a == 1 && rotation == 1) || (a == 1 && rotation == 3)));
                    if (this.c.i() == AdTarget.ACTIVITY_PORTRAIT) {
                        if (z) {
                            if (!(rotation == 1 || rotation == 3)) {
                                this.d = true;
                                setRequestedOrientation(1);
                            }
                        } else if (!(rotation == 0 || rotation == 2)) {
                            this.d = true;
                            setRequestedOrientation(1);
                        }
                    } else if (z) {
                        if (!(rotation == 0 || rotation == 2)) {
                            this.d = true;
                            setRequestedOrientation(0);
                        }
                    } else if (!(rotation == 1 || rotation == 3)) {
                        this.d = true;
                        setRequestedOrientation(0);
                    }
                    this.b = new AppLovinAdView(this.g, AppLovinAdSize.c, this);
                    this.b.setAutoDestroy(false);
                    this.c.a((w) this);
                    this.r = this.f.s();
                    this.H = new aw(this.g, this);
                } else {
                    a("No current ad found.");
                }
            } else {
                a("Wrapper is null; initialized state: " + Boolean.toString(ah.a));
            }
        }
        Editor edit = K().edit();
        edit.putBoolean("com.applovin.interstitial.should_resume_video", false);
        edit.commit();
        h();
        B();
    }

    protected void onDestroy() {
        try {
            if (this.b != null) {
                ViewParent parent = this.b.getParent();
                if (parent != null && (parent instanceof ViewGroup)) {
                    ((ViewGroup) parent).removeView(this.b);
                }
                this.b.b();
                this.b = null;
            }
            if (this.y != null) {
                this.y.pause();
                this.y.stopPlayback();
            }
        } catch (Throwable th) {
            this.e.a("AppLovinInterstitialActivity", "Unable to destroy video view", th);
        }
        super.onDestroy();
    }

    protected void onPause() {
        if (!(this.d || this.i)) {
            F();
        }
        this.c.a(false);
        this.H.a();
        super.onPause();
    }

    protected void onResume() {
        super.onResume();
        this.c.a(true);
        if (K().getBoolean("com.applovin.interstitial.should_resume_video", false) && !this.H.c()) {
            G();
            if (this.z == null || !this.f.j()) {
                dismiss();
                return;
            }
            this.e.a("AppLovinInterstitialActivity", "Fading in close button due to app resume.");
            u uVar = (!this.r || this.B == null) ? this.z : this.B;
            a(0, uVar);
        }
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (z) {
            try {
                if (n.c() && this.f.O()) {
                    getWindow().getDecorView().setSystemUiVisibility(5894);
                } else {
                    getWindow().setFlags(1024, 1024);
                }
            } catch (Throwable th) {
                this.e.b("AppLovinInterstitialActivity", "Setting window flags failed.", th);
            }
        }
    }
}
