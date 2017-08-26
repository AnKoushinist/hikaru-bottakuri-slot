package com.jirbo.adcolony;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Handler;
import android.view.Display;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.TextureView;
import android.view.TextureView.SurfaceTextureListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.tapjoy.TJAdUnitConstants.String;
import com.tapjoy.TapjoyConnectCore;
import com.tapjoy.TapjoyConstants;
import com.tapjoy.mraid.controller.Abstract;
import java.io.FileInputStream;
import org.cocos2dx.lib.BuildConfig;

public class AdColonyNativeAdView extends FrameLayout implements OnCompletionListener, OnErrorListener, OnPreparedListener {
    boolean A;
    boolean B = true;
    boolean C;
    boolean D = true;
    boolean E;
    boolean F;
    boolean G;
    boolean H;
    boolean I;
    boolean J;
    boolean K;
    String L;
    AdColonyInterstitialAd M;
    AdColonyNativeAdListener N;
    AdColonyNativeAdMutedListener O;
    ADCImage P;
    ADCImage Q;
    ADCImage R;
    ImageView S;
    b T;
    View U;
    Bitmap V;
    ADCImage W;
    TextView a;
    ad aA;
    a aB;
    float aC = 0.25f;
    float aD = 0.25f;
    float aE;
    boolean aF;
    boolean aG;
    boolean aH;
    LayoutParams aI;
    LayoutParams aJ;
    FileInputStream aK;
    ImageView aa;
    boolean ab = false;
    Button ac;
    String ad = BuildConfig.FLAVOR;
    String ae = BuildConfig.FLAVOR;
    String af = BuildConfig.FLAVOR;
    MediaPlayer ag;
    Surface ah;
    String ai;
    String aj;
    String ak;
    String al;
    String am;
    String an;
    String ao;
    String ap = BuildConfig.FLAVOR;
    String aq = BuildConfig.FLAVOR;
    AdColonyIAPEngagement ar = AdColonyIAPEngagement.NONE;
    int as;
    int at;
    int au;
    int av = -1;
    int aw;
    int ax = -3355444;
    int ay = -16777216;
    int az;
    TextView b;
    TextView c;
    Activity d;
    String e;
    String f;
    ViewGroup g;
    SurfaceTexture h;
    int i;
    int j;
    int k;
    int l;
    boolean m;
    boolean n;
    boolean o;
    boolean p;
    boolean q;
    boolean r;
    boolean s;
    boolean t;
    boolean u;
    boolean v;
    boolean w;
    boolean x;
    boolean y;
    boolean z;

    class a extends TextureView implements SurfaceTextureListener {
        boolean a;
        boolean b;
        final /* synthetic */ AdColonyNativeAdView c;

        a(AdColonyNativeAdView adColonyNativeAdView, Context context) {
            this(adColonyNativeAdView, context, false);
        }

        a(AdColonyNativeAdView adColonyNativeAdView, Context context, boolean z) {
            this.c = adColonyNativeAdView;
            super(context);
            this.a = false;
            this.b = false;
            setSurfaceTextureListener(this);
            setWillNotDraw(false);
            setBackgroundColor(-16777216);
            this.a = z;
        }

        public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
            if (surfaceTexture == null) {
                this.c.u = true;
                this.c.S.setVisibility(8);
                return;
            }
            this.c.T.setVisibility(0);
            this.c.h = surfaceTexture;
            if (!this.c.u && !this.a) {
                this.c.ah = new Surface(surfaceTexture);
                if (this.c.ag != null) {
                    this.c.ag.release();
                }
                this.c.i = i;
                this.c.j = i2;
                this.c.ag = new MediaPlayer();
                try {
                    this.c.aK = new FileInputStream(this.c.f);
                    this.c.ag.setDataSource(this.c.aK.getFD());
                    this.c.ag.setSurface(this.c.ah);
                    this.c.ag.setOnCompletionListener(this.c);
                    this.c.ag.setOnPreparedListener(this.c);
                    this.c.ag.setOnErrorListener(this.c);
                    this.c.ag.prepareAsync();
                    l.c.b((Object) "[ADC] Native Ad Prepare called.");
                    this.b = true;
                    Handler handler = new Handler();
                    Runnable anonymousClass1 = new Runnable(this) {
                        final /* synthetic */ a a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            if (!this.a.c.z && !this.a.c.A) {
                                this.a.b = false;
                                this.a.c.u = true;
                                this.a.c.S.setVisibility(8);
                            }
                        }
                    };
                    if (!this.b) {
                        handler.postDelayed(anonymousClass1, 1800);
                    }
                } catch (Exception e) {
                    this.c.u = true;
                    this.c.S.setVisibility(8);
                }
            }
        }

        public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
            l.c.b((Object) "[ADC] onSurfaceTextureSizeChanged");
        }

        public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
            l.c.b((Object) "[ADC] Native surface destroyed");
            this.c.z = false;
            this.c.S.setVisibility(4);
            this.c.T.setVisibility(0);
            return true;
        }

        public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
        }

        public boolean onTouchEvent(MotionEvent motionEvent) {
            int action = motionEvent.getAction();
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            if (action == 1 && a.E && q.c() && (x <= ((float) ((this.c.au - this.c.Q.f) + 8)) || y >= ((float) (this.c.Q.g + 8)) || this.c.u || this.c.ag == null || !this.c.ag.isPlaying())) {
                a.T = this.c.M;
                a.l.a.a(this.c.e, this.c.M.j);
                ADCVideo.a();
                if (this.c.K) {
                    try {
                        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(this.c.L));
                        if (a.P != null) {
                            a.b().startActivity(intent);
                        }
                    } catch (Exception e) {
                        if (a.P != null) {
                            Toast.makeText(a.b(), "Unable to open store.", 0).show();
                        }
                    }
                } else {
                    this.c.M.k = TapjoyConstants.TJC_PLUGIN_NATIVE;
                    this.c.M.l = Abstract.FULL_SCREEN;
                    this.c.M.t = true;
                    this.c.M.u = this.c.C;
                    if ((this.c.z || this.c.u) && q.c()) {
                        if (this.c.N != null) {
                            this.c.N.onAdColonyNativeAdStarted(true, this.c);
                        }
                        if (this.c.ag == null || !this.c.ag.isPlaying()) {
                            this.c.M.q = 0.0d;
                            ADCVideo.c = 0;
                        } else {
                            ADCVideo.c = this.c.ag.getCurrentPosition();
                            this.c.M.q = this.c.M.p;
                            this.c.ag.pause();
                        }
                        a.E = false;
                        a.l.d.b("video_expanded", this.c.M);
                        if (a.m) {
                            l.a.b((Object) "Launching AdColonyOverlay");
                            a.b().startActivity(new Intent(a.b(), AdColonyOverlay.class));
                        } else {
                            l.a.b((Object) "Launching AdColonyFullscreen");
                            a.b().startActivity(new Intent(a.b(), AdColonyFullscreen.class));
                        }
                        if (this.c.u) {
                            af afVar = this.c.M.i.r;
                            afVar.d++;
                        }
                        this.c.u = true;
                        this.c.C = true;
                    }
                }
            }
            return true;
        }
    }

    class b extends View {
        boolean a;
        final /* synthetic */ AdColonyNativeAdView b;

        public b(AdColonyNativeAdView adColonyNativeAdView, Context context) {
            this.b = adColonyNativeAdView;
            super(context);
            setBackgroundColor(-16777216);
        }

        public void onDraw(Canvas canvas) {
            this.b.g = (ViewGroup) getParent().getParent();
            Rect rect = new Rect();
            if (!(this.b.ag == null || this.b.ag.isPlaying() || !this.b.n)) {
                this.a = false;
            }
            if (getLocalVisibleRect(rect) && VERSION.SDK_INT >= 14 && this.b.z) {
                if ((!this.b.n || (this.b.n && (rect.top == 0 || rect.bottom - rect.top > this.b.getNativeAdHeight()))) && rect.bottom - rect.top > this.b.getNativeAdHeight() / 2 && rect.right - rect.left > this.b.getNativeAdWidth() / 2) {
                    if (this.a || this.b.u || this.b.ag == null || this.b.ag.isPlaying() || this.b.A || this.b.M.a(true) || !this.b.t) {
                    }
                    if (!this.b.t) {
                        l.c.b((Object) "[ADC] Native Ad Starting");
                        this.b.b();
                        this.b.t = true;
                        this.b.M.k = TapjoyConstants.TJC_PLUGIN_NATIVE;
                        this.b.M.l = TapjoyConstants.TJC_PLUGIN_NATIVE;
                    } else if (!this.b.v && this.b.ag != null && q.c() && !this.b.ag.isPlaying() && !a.B) {
                        l.c.b((Object) "[ADC] Native Ad Resuming");
                        a.l.d.b("video_resumed", this.b.M);
                        if (!this.b.r) {
                            this.b.c(true);
                        }
                        this.b.setVolume(this.b.aD);
                        this.b.ag.seekTo(this.b.M.r);
                        this.b.ag.start();
                    } else if (!(this.b.u || this.b.t || (a.l.a(this.b.M.h, true, false) && this.b.aH))) {
                        this.b.u = true;
                        setVisibility(0);
                        this.b.S.setVisibility(8);
                    }
                }
                this.a = true;
            } else {
                this.a = false;
            }
            if (!(this.b.u || q.c() || this.b.ag == null || this.b.ag.isPlaying())) {
                setVisibility(0);
                this.b.S.setVisibility(8);
                this.b.u = true;
            }
            if (!this.b.u && this.b.ag != null && this.b.ag.isPlaying()) {
                setVisibility(4);
                this.b.S.setVisibility(0);
            } else if (this.b.u || this.b.v) {
                canvas.drawARGB(255, 0, 0, 0);
                this.b.S.setVisibility(8);
                this.b.P.a(canvas, (this.b.au - this.b.P.f) / 2, (this.b.av - this.b.P.g) / 2);
            }
            if (!this.b.A && !this.b.u) {
                invalidate();
            }
        }
    }

    public AdColonyNativeAdView(Activity activity, String str, int i) {
        super(activity);
        a(activity, str, i);
        a();
    }

    public AdColonyNativeAdView(Activity activity, String str, int i, int i2) {
        super(activity);
        a(activity, str, i, i2);
        a(false);
    }

    AdColonyNativeAdView(Activity activity, String str, int i, boolean z) {
        super(activity);
        this.G = z;
        a(activity, str, i);
        a();
    }

    void a(Activity activity, String str, int i) {
        a(activity, str, i, 0);
    }

    void a(Activity activity, String str, int i, int i2) {
        int i3;
        int i4;
        a.e();
        a.am = 0;
        this.d = activity;
        this.e = str;
        this.au = i;
        this.k = i;
        if (i2 != 0) {
            this.l = i2;
            this.av = i2;
            this.o = true;
        }
        this.r = true;
        this.aE = a.b().getResources().getDisplayMetrics().density;
        Display defaultDisplay = a.b().getWindowManager().getDefaultDisplay();
        if (VERSION.SDK_INT >= 14) {
            Point point = new Point();
            defaultDisplay.getSize(point);
            i3 = point.x;
            i4 = point.y;
        } else {
            i3 = defaultDisplay.getWidth();
            i4 = defaultDisplay.getHeight();
        }
        if (i3 >= i4) {
            i3 = i4;
        }
        this.az = i3;
        this.M = new AdColonyInterstitialAd(str);
        this.M.k = TapjoyConstants.TJC_PLUGIN_NATIVE;
        this.M.l = TapjoyConstants.TJC_PLUGIN_NATIVE;
        setBackgroundColor(-16777216);
        if (!this.B) {
            return;
        }
        if (!this.M.b(true) || this.M.j == null || this.M.j.A == null) {
            if (!this.G) {
                l.d.b((Object) "AdColonyNativeAdView created while no ads are available, returning blank view.");
                this.M.g = 5;
                a.l.d.a(str, this.M);
            }
            this.aH = true;
            return;
        }
        this.aA = this.M.i;
        if (!this.G) {
            if (!this.G) {
                a.aq.add(this);
            }
            this.M.i.c();
            if (!this.M.a(true)) {
                this.aH = true;
            }
            this.M.j.A.i = true;
            a.l.d.a(str, this.M);
        }
    }

    public boolean isEngagementEnabled() {
        return this.ab;
    }

    public String getEngagementLabel() {
        if (this.ad == null) {
            return BuildConfig.FLAVOR;
        }
        return this.ad;
    }

    public String getEngagementCommand() {
        if (this.ae == null) {
            return BuildConfig.FLAVOR;
        }
        return this.ae;
    }

    public String getEngagementType() {
        if (this.af == null) {
            return BuildConfig.FLAVOR;
        }
        return this.af;
    }

    void a() {
        a(true);
    }

    void a(boolean z) {
        this.z = false;
        this.q = false;
        setWillNotDraw(false);
        this.M.x = this;
        if (this.B) {
            if (a.l == null || a.l.a == null || this.M == null || this.M.h == null || (!a.l.a(this.M.h, true, false) && this.aH)) {
                this.u = true;
            } else {
                a.l.a.b(this.e);
            }
            this.f = a.j("video_filepath");
            this.ai = a.j("advertiser_name");
            this.aj = a.j("description");
            this.ak = a.j(String.TITLE);
            this.al = a.j("poster_image");
            this.am = a.j("unmute");
            this.an = a.j("mute");
            this.ao = a.j("thumb_image");
            this.ab = a.i("native_engagement_enabled");
            this.ad = a.j("native_engagement_label");
            this.ae = a.j("native_engagement_command");
            this.af = a.j("native_engagement_type");
            this.J = a.i("v4iap_enabled");
            this.K = a.i("click_to_install");
            this.L = a.j("store_url");
            this.aq = a.ad;
            if (this.J) {
                this.ar = AdColonyIAPEngagement.AUTOMATIC;
            }
            this.ap = a.j("product_id");
            if (this.M.j == null || this.M.j.A == null) {
                this.y = true;
            } else {
                this.y = this.M.j.A.b;
            }
            if (this.aA != null) {
                this.aA.m();
            }
            if (this.M.j == null || this.M.j.A == null || !this.M.j.A.a || this.M.i == null) {
                a.am = 13;
                return;
            }
            this.s = true;
            if (this.G) {
                return;
            }
        } else if (VERSION.SDK_INT < 14) {
            return;
        }
        if (this.B) {
            float f;
            this.as = this.M.j.z.b;
            this.at = this.M.j.z.c;
            a.h();
            if (this.av == -1) {
                this.av = (int) (((double) this.at) * (((double) this.au) / ((double) this.as)));
                this.l = this.av;
            }
            if (!z && this.ab) {
                this.av -= this.av / 6;
            }
            float f2 = ((float) this.as) / ((float) this.at);
            if (((float) this.au) / ((float) this.as) > ((float) this.av) / ((float) this.at)) {
                this.aG = true;
                this.au = (int) (((float) this.av) * f2);
            } else {
                this.aF = true;
                this.av = (int) (((float) this.au) / f2);
            }
            this.aJ = new LayoutParams(this.au, this.av, 48);
            this.aI = new LayoutParams(this.k, this.l, 48);
            if (this.ab && !z && this.aF) {
                this.aJ.setMargins(0, ((this.l - this.av) / 2) - (this.av / 10), 0, 0);
                this.aI.setMargins(0, ((this.l - this.av) / 2) - (this.av / 10), 0, (((this.l - this.av) / 2) - (this.av / 10)) * -1);
            } else if (!z && this.aF) {
                this.aJ.setMargins(0, (this.l - this.av) / 2, 0, 0);
                this.aI.setMargins(0, (this.l - this.av) / 2, 0, ((this.l - this.av) / 2) * -1);
            } else if (this.ab && !z && this.aG) {
                this.aJ.setMargins((this.k - this.au) / 2, 0, 0, 0);
                this.aI.setMargins((this.k - this.au) / 2, 0, ((this.k - this.au) / 2) * -1, 0);
            } else if (!z && this.aG) {
                this.aJ.setMargins((this.k - this.au) / 2, 0, 0, 0);
                this.aI.setMargins((this.k - this.au) / 2, 0, ((this.k - this.au) / 2) * -1, 0);
            }
            this.P = new ADCImage(this.al, true, false);
            if (TapjoyConnectCore.DEFAULT_CURRENCY_MULTIPLIER / (((float) this.P.f) / ((float) this.au)) > TapjoyConnectCore.DEFAULT_CURRENCY_MULTIPLIER / (((float) this.P.g) / ((float) this.av))) {
                f = TapjoyConnectCore.DEFAULT_CURRENCY_MULTIPLIER / (((float) this.P.g) / ((float) this.av));
            } else {
                f = TapjoyConnectCore.DEFAULT_CURRENCY_MULTIPLIER / (((float) this.P.f) / ((float) this.au));
            }
            this.P.a((double) f, true);
            this.B = false;
        }
        if (this.ab) {
            this.ac = new Button(a.b());
            this.ac.setText(this.ad);
            this.ac.setGravity(17);
            this.ac.setTextSize((float) ((int) (18.0d * (((double) this.au) / ((double) this.az)))));
            this.ac.setPadding(0, 0, 0, 0);
            this.ac.setBackgroundColor(this.ax);
            this.ac.setTextColor(this.ay);
            this.ac.setOnTouchListener(new OnTouchListener(this) {
                final /* synthetic */ AdColonyNativeAdView a;

                {
                    this.a = r1;
                }

                public boolean onTouch(View view, MotionEvent motionEvent) {
                    int action = motionEvent.getAction();
                    if (action == 0) {
                        float[] fArr = new float[3];
                        Color.colorToHSV(this.a.ax, fArr);
                        fArr[2] = fArr[2] * 0.8f;
                        this.a.ac.setBackgroundColor(Color.HSVToColor(fArr));
                    } else if (action == 3) {
                        this.a.ac.setBackgroundColor(this.a.ax);
                    } else if (action == 1) {
                        if (this.a.J) {
                            this.a.ar = AdColonyIAPEngagement.OVERLAY;
                            this.a.u = true;
                        } else {
                            if (this.a.af.equals("install") || this.a.af.equals(String.URL)) {
                                a.l.d.b("native_overlay_click", this.a.M);
                                try {
                                    a.b().startActivity(new Intent("android.intent.action.VIEW", Uri.parse(this.a.ae)));
                                } catch (Exception e) {
                                    Toast.makeText(a.b(), "Unable to open store.", 0).show();
                                }
                            }
                            this.a.ac.setBackgroundColor(this.a.ax);
                        }
                    }
                    return true;
                }
            });
        }
        this.R = new ADCImage(this.am, true, false);
        this.Q = new ADCImage(this.an, true, false);
        this.W = new ADCImage(this.ao, true, false);
        this.W.a((double) (TapjoyConnectCore.DEFAULT_CURRENCY_MULTIPLIER / ((float) (((double) (((float) this.W.f) / ((float) this.au))) / ((((double) this.au) / 5.5d) / ((double) ((float) this.au)))))), true);
        this.Q.a((double) (this.aE / 2.0f), true);
        this.R.a((double) (this.aE / 2.0f), true);
        this.T = new b(this, a.b());
        this.aa = new ImageView(a.b());
        this.S = new ImageView(a.b());
        this.aa.setImageBitmap(this.W.a);
        if (this.r) {
            this.S.setImageBitmap(this.Q.a);
        } else {
            this.S.setImageBitmap(this.R.a);
        }
        ViewGroup.LayoutParams layoutParams = new LayoutParams(this.Q.f, this.Q.g, 48);
        layoutParams.setMargins(this.k - this.Q.f, 0, 0, 0);
        this.S.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ AdColonyNativeAdView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.r) {
                    if (this.a.O != null) {
                        this.a.O.onAdColonyNativeAdMuted(this.a, true);
                    }
                    this.a.a(true, true);
                    this.a.x = true;
                } else if (this.a.V == this.a.R.a) {
                    if (this.a.O != null) {
                        this.a.O.onAdColonyNativeAdMuted(this.a, false);
                    }
                    this.a.x = false;
                    this.a.a(false, true);
                }
            }
        });
        this.V = this.Q.a;
        if (this.u) {
            this.S.setVisibility(8);
        }
        if (this.v) {
            this.S.setVisibility(4);
        }
        if (VERSION.SDK_INT >= 14) {
            this.U = new a(this, a.b(), this.u);
        }
        if (VERSION.SDK_INT >= 14) {
            addView(this.U, this.aJ);
        }
        if (VERSION.SDK_INT < 14) {
            this.u = true;
        }
        addView(this.T, this.aI);
        if (this.y && VERSION.SDK_INT >= 14 && this.D) {
            addView(this.S, layoutParams);
        }
        if (this.ab) {
            if (z) {
                layoutParams = new LayoutParams(this.k, this.l / 5, 80);
            } else {
                layoutParams = new LayoutParams(this.k, this.av / 5, 80);
            }
            addView(this.ac, layoutParams);
        }
    }

    public boolean isReady() {
        if ((this.M.a(true) || !this.aH) && this.s && !this.F) {
            return true;
        }
        return false;
    }

    boolean b(boolean z) {
        if ((this.M.a(true) || !this.aH) && AdColony.isZoneNative(this.e)) {
            return true;
        }
        return false;
    }

    public int getNativeAdWidth() {
        return this.k;
    }

    public int getNativeAdHeight() {
        return (this.o || !this.ab) ? this.l : this.l + (this.l / 5);
    }

    public void setOverlayButtonColor(int i) {
        if (this.ab) {
            this.ac.setBackgroundColor(i);
        }
        this.ax = i;
    }

    public void setOverlayButtonTextColor(int i) {
        if (this.ab) {
            this.ac.setTextColor(i);
        }
        this.ay = i;
    }

    public void setOverlayButtonTypeface(Typeface typeface, int i) {
        if (this.ab) {
            this.ac.setTypeface(typeface, i);
        }
    }

    void a(boolean z, boolean z2) {
        if (z) {
            this.S.setImageBitmap(this.R.a);
            this.r = false;
            a(0.0f, z2);
            this.V = this.R.a;
        } else if (!this.x && this.V == this.R.a) {
            this.S.setImageBitmap(this.Q.a);
            this.r = true;
            if (this.ag != null) {
                if (((double) this.aD) != 0.0d) {
                    a(this.aD, z2);
                } else {
                    a(0.25f, z2);
                }
            }
            this.V = this.Q.a;
        }
    }

    public void setMuted(boolean z) {
        a(z, false);
    }

    public void destroy() {
        l.c.b((Object) "[ADC] Native Ad Destroy called.");
        if (this.ah != null) {
            this.ah.release();
        }
        if (this.ag != null) {
            this.ag.release();
        }
        this.ag = null;
        this.M.j.A.i = false;
        a.aq.remove(this);
    }

    public ImageView getAdvertiserImage() {
        if (this.W == null) {
            this.W = new ADCImage(this.ao, true, false);
            this.W.a((double) (this.aE / 2.0f), true);
        }
        if (this.aa == null) {
            this.aa = new ImageView(a.b());
            this.aa.setImageBitmap(this.W.a);
        }
        return this.aa;
    }

    public String getTitle() {
        return this.ak;
    }

    public String getAdvertiserName() {
        return this.ai;
    }

    public String getDescription() {
        return this.aj;
    }

    public boolean canceled() {
        return this.I;
    }

    public boolean iapEnabled() {
        return this.J;
    }

    public String iapProductID() {
        return this.ap;
    }

    public AdColonyIAPEngagement iapEngagementType() {
        if (this.M == null || this.M.z != AdColonyIAPEngagement.END_CARD) {
            return this.ar;
        }
        return AdColonyIAPEngagement.END_CARD;
    }

    public AdColonyNativeAdView withListener(AdColonyNativeAdListener adColonyNativeAdListener) {
        this.N = adColonyNativeAdListener;
        this.M.C = adColonyNativeAdListener;
        return this;
    }

    public AdColonyNativeAdView withMutedListener(AdColonyNativeAdMutedListener adColonyNativeAdMutedListener) {
        this.O = adColonyNativeAdMutedListener;
        return this;
    }

    public void pause() {
        l.c.b((Object) "[ADC] Native Ad Pause called.");
        if (this.ag != null && !this.u && this.ag.isPlaying() && VERSION.SDK_INT >= 14) {
            a.l.d.b("video_paused", this.M);
            this.v = true;
            this.ag.pause();
            this.T.setVisibility(0);
            this.S.setVisibility(4);
        }
    }

    public void resume() {
        l.c.b((Object) "[ADC] Native Ad Resume called.");
        if (this.ag != null && this.v && !this.u && VERSION.SDK_INT >= 14) {
            a.l.d.b("video_resumed", this.M);
            this.v = false;
            this.ag.seekTo(this.M.r);
            this.ag.start();
            this.T.setVisibility(4);
            this.S.setVisibility(0);
        }
    }

    void c(boolean z) {
        if (this.ag != null && this.S != null) {
            if (z) {
                this.ag.setVolume(0.0f, 0.0f);
                this.S.setImageBitmap(this.R.a);
                this.V = this.R.a;
                return;
            }
            this.ag.setVolume(this.aD, this.aD);
            this.S.setImageBitmap(this.Q.a);
            this.V = this.Q.a;
        }
    }

    void a(float f, boolean z) {
        if (VERSION.SDK_INT >= 14) {
            this.aD = f;
            if (this.ag != null && ((double) f) >= 0.0d && ((double) f) <= 1.0d) {
                if (!this.x) {
                    this.ag.setVolume(f, f);
                }
                if (!this.z) {
                    return;
                }
                g gVar;
                if (this.V == this.R.a && ((double) f) > 0.0d && !this.x) {
                    gVar = new g();
                    gVar.b("user_action", z);
                    this.S.setImageBitmap(this.Q.a);
                    this.V = this.Q.a;
                    a.l.d.a("sound_unmute", gVar, this.M);
                    this.r = true;
                } else if (this.V == this.Q.a && ((double) f) == 0.0d) {
                    gVar = new g();
                    gVar.b("user_action", z);
                    this.S.setImageBitmap(this.R.a);
                    this.V = this.R.a;
                    a.l.d.a("sound_mute", gVar, this.M);
                    this.r = false;
                }
            } else if (((double) f) >= 0.0d && ((double) f) <= 1.0d) {
                this.aC = f;
            }
        }
    }

    public void setVolume(float f) {
        a(f, false);
    }

    synchronized void b() {
        if ((this.u || this.ag == null || !this.ag.isPlaying()) && this.ag != null) {
            setVolume(this.aD);
            this.ag.start();
            a.l.a(this.M);
            this.M.s = true;
            if (this.N != null) {
                this.N.onAdColonyNativeAdStarted(false, this);
            }
        }
    }

    void c() {
        if (!this.u && this.ag != null && this.ag.isPlaying() && !this.v) {
            a.l.d.b("video_paused", this.M);
            this.ag.pause();
        }
    }

    public void onPrepared(MediaPlayer mediaPlayer) {
        l.c.b((Object) "[ADC] Native Ad onPrepared called.");
        this.z = true;
        if (this.V == null || this.Q.a == null) {
            this.T.setVisibility(0);
            this.S.setVisibility(8);
            this.u = true;
            this.ag = null;
            this.M.r = 0;
        } else if (this.r || !this.V.equals(this.Q.a)) {
            setVolume(this.aD);
        } else {
            c(true);
        }
    }

    public void onCompletion(MediaPlayer mediaPlayer) {
        try {
            this.aK.close();
        } catch (Exception e) {
        }
        this.T.setVisibility(0);
        this.S.setVisibility(8);
        this.M.k = TapjoyConstants.TJC_PLUGIN_NATIVE;
        this.M.l = TapjoyConstants.TJC_PLUGIN_NATIVE;
        this.M.s = true;
        this.u = true;
        if (this.ag != null) {
            this.ag.release();
        }
        this.ag = null;
        this.M.r = 0;
        g gVar = new g();
        gVar.b("ad_slot", a.l.e.j);
        gVar.b("replay", false);
        a.l.d.a("native_complete", gVar, this.M);
        this.M.j.r = false;
        if (this.N != null) {
            this.N.onAdColonyNativeAdFinished(false, this);
        }
        this.C = true;
    }

    public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        this.T.setVisibility(0);
        this.S.setVisibility(8);
        this.u = true;
        this.z = true;
        this.ag = null;
        this.M.r = 0;
        return true;
    }

    public void onDraw(Canvas canvas) {
        if (this.g != null) {
            Rect rect = new Rect();
            if (!this.g.hasFocus()) {
                this.g.requestFocus();
            }
            if (!(this.u || this.ag == null)) {
                this.aw = this.ag.getCurrentPosition();
            }
            if (this.aw != 0) {
                this.M.r = this.aw;
            }
            getLocalVisibleRect(rect);
            boolean z = rect.bottom - rect.top > getNativeAdHeight() / 2 && rect.right - rect.left > getNativeAdWidth() / 2;
            if ((z || this.n) && (!this.n || (z && (rect.bottom - rect.top >= getNativeAdHeight() || rect.top == 0)))) {
                if (this.u || this.ag == null || !this.ag.isPlaying()) {
                    if (!this.T.a) {
                        canvas.drawARGB(255, 0, 0, 0);
                    }
                } else if (this.z) {
                    this.M.k = TapjoyConstants.TJC_PLUGIN_NATIVE;
                    this.M.l = TapjoyConstants.TJC_PLUGIN_NATIVE;
                    a.l.a(((double) this.ag.getCurrentPosition()) / ((double) this.ag.getDuration()), this.M);
                    if (!this.H) {
                        this.H = true;
                        a.l.a("native_start", "{\"ad_slot\":" + (a.l.e.j + 1) + ", \"replay\":false}", this.M);
                        this.M.j.r = true;
                        this.M.j.q = true;
                        a.h();
                        this.M.i.j.a(this.M.j.a);
                    }
                } else {
                    canvas.drawARGB(255, 0, 0, 0);
                }
            } else if (!(this.u || this.ag == null || !this.ag.isPlaying() || this.v)) {
                l.c.b((Object) "[ADC] Scroll Pause");
                a.l.d.b("video_paused", this.M);
                this.ag.pause();
                this.T.setVisibility(0);
            }
            if (!this.A && !this.u) {
                invalidate();
            }
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (VERSION.SDK_INT >= 14) {
            return false;
        }
        if (motionEvent.getAction() == 1 && a.E && q.c()) {
            if (this.K) {
                try {
                    Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(this.L));
                    if (a.P != null) {
                        a.b().startActivity(intent);
                    }
                } catch (Exception e) {
                    if (a.P != null) {
                        Toast.makeText(a.b(), "Unable to open store.", 0).show();
                    }
                }
            } else {
                a.T = this.M;
                a.l.a.a(this.e, this.M.j);
                ADCVideo.a();
                this.M.u = this.C;
                this.M.t = true;
                this.M.k = TapjoyConstants.TJC_PLUGIN_NATIVE;
                this.M.l = Abstract.FULL_SCREEN;
                a.E = false;
                a.l.d.b("video_expanded", this.M);
                if (this.N != null) {
                    this.N.onAdColonyNativeAdStarted(true, this);
                }
                if (a.m) {
                    l.a.b((Object) "Launching AdColonyOverlay");
                    a.b().startActivity(new Intent(a.b(), AdColonyOverlay.class));
                } else {
                    l.a.b((Object) "Launching AdColonyFullscreen");
                    a.b().startActivity(new Intent(a.b(), AdColonyFullscreen.class));
                }
                if (this.u) {
                    this.M.f = -1;
                    af afVar = this.M.i.r;
                    afVar.d++;
                    this.M.j.r = true;
                }
                this.u = true;
                this.C = true;
            }
        }
        return true;
    }

    public void notifyAddedToListView() {
        if (this.m) {
            ((a) this.U).onSurfaceTextureAvailable(this.h, this.i, this.j);
        } else {
            this.m = true;
        }
    }

    public void prepareForListView() {
        this.n = true;
    }
}
