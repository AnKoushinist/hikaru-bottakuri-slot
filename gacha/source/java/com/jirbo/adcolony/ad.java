package com.jirbo.adcolony;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.webkit.ConsoleMessage;
import android.webkit.GeolocationPermissions.Callback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import com.raizlabs.android.dbflow.sql.language.Condition.Operation;
import com.tapjoy.TJAdUnitConstants.String;
import com.tapjoy.TapjoyConstants;
import jp.tjkapp.adfurikunsdk.moviereward.ApiAccessUtil;
import org.cocos2dx.lib.BuildConfig;
import org.cocos2dx.lib.GameControllerDelegate;
import twitter4j.HttpResponseCode;
import twitter4j.TwitterResponse;

class ad extends View implements OnCompletionListener, OnErrorListener {
    static float[] aB = new float[80];
    boolean A = true;
    boolean B = true;
    boolean C = true;
    boolean D = true;
    boolean E;
    boolean F;
    boolean G;
    boolean H;
    boolean I;
    boolean J;
    boolean K;
    boolean L;
    boolean M;
    boolean N;
    boolean O;
    boolean P;
    boolean Q;
    boolean R;
    boolean S;
    boolean T;
    boolean U;
    boolean V;
    Canvas W;
    WebView a;
    String[] aA = new String[4];
    float aC;
    float aD;
    float aE;
    float aF;
    float aG;
    float aH;
    float aI;
    Paint aJ = new Paint(1);
    RectF aK = new RectF();
    b aL = new b(this);
    Handler aM = new Handler(this) {
        final /* synthetic */ ad a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            if (!this.a.d.isFinishing() && this.a.d.G != null) {
                this.a.a(message.what);
            }
        }
    };
    String aa = a.l.a.b;
    String ab;
    String ac;
    String ad;
    String ae;
    String af;
    b ag;
    Paint ah = new Paint();
    Paint ai = new Paint(1);
    Paint aj = new Paint(1);
    Paint ak = new Paint(1);
    Rect al = new Rect();
    ADCImage am;
    ADCImage an;
    ADCImage ao;
    ADCImage ap;
    ADCImage aq;
    ADCImage ar;
    ADCImage as;
    ADCImage at;
    ADCImage au;
    ADCImage av;
    ADCImage aw;
    ADCImage[] ax = new ADCImage[4];
    ADCImage[] ay = new ADCImage[4];
    m az;
    WebView b;
    View c;
    ADCVideo d;
    double e = 1.0d;
    double f = 1.0d;
    int g = 99;
    int h = 0;
    int i;
    int j;
    int k;
    int l;
    int m;
    int n;
    int o;
    int p;
    int q;
    int r;
    int s;
    int t;
    int u;
    long v;
    long w;
    float x;
    boolean y = true;
    boolean z = true;

    class a extends View {
        Rect a = new Rect();
        final /* synthetic */ ad b;

        public a(ad adVar, Activity activity) {
            this.b = adVar;
            super(activity);
        }

        public void onDraw(Canvas canvas) {
            canvas.drawARGB(255, 0, 0, 0);
            getDrawingRect(this.a);
            this.b.av.a(canvas, (this.a.width() - this.b.av.f) / 2, (this.a.height() - this.b.av.g) / 2);
            invalidate();
        }
    }

    class b extends Handler {
        final /* synthetic */ ad a;

        b(ad adVar) {
            this.a = adVar;
            a();
        }

        void a() {
            sendMessageDelayed(obtainMessage(), 500);
        }

        public void handleMessage(Message message) {
            a();
            if (!this.a.d.isFinishing() && this.a.d.G != null) {
                synchronized (this) {
                    if (!(this.a.ag == null || !this.a.ag.a() || this.a.d.G.isPlaying())) {
                        this.a.ag = null;
                        this.a.t = 0;
                        if (this.a.d.G != null) {
                            this.a.d.G.a();
                        }
                        this.a.d.n = true;
                        this.a.g();
                    }
                }
            }
        }
    }

    ad(ADCVideo aDCVideo) {
        super(aDCVideo);
        this.d = aDCVideo;
        this.M = a.l.a.t;
        if (a.T != null) {
            this.M |= a.T.j.z.l.a;
            a.T.p = a.T.q;
        }
        this.x = aDCVideo.getResources().getDisplayMetrics().density;
        this.Q = a.Y;
        if (!(a.ad == null || a.e == null)) {
            int indexOf = a.ad.indexOf("#");
            a.ad = a.e + (indexOf >= 0 ? a.ad.substring(indexOf) : BuildConfig.FLAVOR);
        }
        aDCVideo.I.o = a.ad;
        l.a.a("DEC URL = ").b(aDCVideo.I.o);
        if (a.T != null && a.T.j.y.d) {
            boolean z;
            if (this.Q) {
                z = false;
            } else {
                z = true;
            }
            this.N = z;
        }
        if (this.N) {
            this.am = new ADCImage(a.j("end_card_filepath"));
            this.n = this.am.f;
            this.o = this.am.g;
            if (this.n == 0) {
                this.n = 480;
            }
            if (this.o == 0) {
                this.o = 320;
            }
            this.ax[0] = new ADCImage(a.j("info_image_normal"));
            this.ax[1] = new ADCImage(a.j("download_image_normal"));
            this.ax[2] = new ADCImage(a.j("replay_image_normal"));
            this.ax[3] = new ADCImage(a.j("continue_image_normal"));
            this.ay[0] = new ADCImage(a.j("info_image_down"), true);
            this.ay[1] = new ADCImage(a.j("download_image_down"), true);
            this.ay[2] = new ADCImage(a.j("replay_image_down"), true);
            this.ay[3] = new ADCImage(a.j("continue_image_down"), true);
            this.aA[0] = "Info";
            this.aA[1] = "Download";
            this.aA[2] = "Replay";
            this.aA[3] = "Continue";
        } else if (this.Q) {
            this.ar = new ADCImage(a.j("reload_image_normal"));
            this.ap = new ADCImage(a.j("close_image_normal"));
            this.aq = new ADCImage(a.j("close_image_down"));
            this.as = new ADCImage(a.j("reload_image_down"));
            this.av = new ADCImage(a.j("browser_icon"));
            this.c = new a(this, aDCVideo);
            b();
        }
        if (this.M) {
            this.an = new ADCImage(a.j("skip_video_image_normal"));
            this.ao = new ADCImage(a.j("skip_video_image_down"));
            this.p = a.h("skip_delay") * GameControllerDelegate.THUMBSTICK_LEFT_X;
        }
        this.aJ.setStyle(Style.STROKE);
        float f = 2.0f * aDCVideo.getResources().getDisplayMetrics().density;
        if (f > 6.0f) {
            f = 6.0f;
        }
        if (f < 4.0f) {
            this.aJ.setStrokeWidth(2.0f * aDCVideo.getResources().getDisplayMetrics().density);
            this.aJ.setColor(-3355444);
            this.S = false;
            this.L = false;
            this.T = false;
        } else {
            this.aJ.setStrokeWidth(2.0f * aDCVideo.getResources().getDisplayMetrics().density);
            this.aJ.setColor(-3355444);
            this.S = false;
            this.L = false;
            this.T = false;
        }
        if (a.T != null) {
            this.L = a.T.j.z.m.a;
            this.T = a.i("image_overlay_enabled");
        }
        if (this.L) {
            this.at = new ADCImage(a.j("engagement_image_normal"));
            this.au = new ADCImage(a.j("engagement_image_down"));
            this.ad = a.T.j.z.m.j;
            this.ab = a.T.j.z.m.l;
            this.ac = a.T.j.z.m.o;
            this.r = a.T.j.z.m.c;
            this.q = a.h("engagement_delay") * GameControllerDelegate.THUMBSTICK_LEFT_X;
            if (this.ab.equals(BuildConfig.FLAVOR)) {
                this.ab = "Learn More";
            }
            if (!this.ac.equals(BuildConfig.FLAVOR)) {
                this.G = true;
            }
            if (this.G) {
                this.b = new WebView(aDCVideo);
                this.b.setBackgroundColor(0);
            }
            if (this.at == null || this.au == null) {
                this.L = false;
            }
        }
        if (this.T) {
            double d;
            this.aw = new ADCImage(a.j("image_overlay_filepath"));
            if (AdColony.isTablet()) {
                d = (((double) this.r) * (((double) this.x) / 1.0d)) / ((double) this.aw.g);
            } else {
                d = (((double) this.r) * (((double) this.x) / 0.75d)) / ((double) this.aw.g);
            }
            this.aw.a(d);
        }
        if (ADCVideo.d) {
            f();
        }
        this.ah.setColor(-1);
        this.aj.setTextSize(24.0f);
        this.aj.setColor(-16777216);
        this.ai.setColor(-3355444);
        this.ai.setTextSize(20.0f);
        this.ai.setTextAlign(Align.CENTER);
        this.ak.setTextSize(20.0f);
        this.ak.setColor(-1);
        try {
            getClass().getMethod("setLayerType", new Class[]{Integer.TYPE, Paint.class}).invoke(this, new Object[]{Integer.valueOf(1), null});
        } catch (Exception e) {
        }
    }

    public void onDraw(Canvas canvas) {
        if (!this.F) {
            a();
            this.W = canvas;
            if (!this.O && this.M) {
                this.O = this.d.G.getCurrentPosition() > this.p;
            }
            if (!this.P && this.L) {
                this.P = this.d.G.getCurrentPosition() > this.q;
            }
            ADCVideo aDCVideo = this.d;
            int c;
            int i;
            if (ADCVideo.d && this.N) {
                canvas.drawARGB((this.d.z >> 24) & 255, 0, 0, 0);
                this.am.a(canvas, (this.d.t - this.am.f) / 2, (this.d.u - this.am.g) / 2);
                c = this.am.c() + ((int) (186.0d * this.e));
                int d = this.am.d() + ((int) (470.0d * this.e));
                i = 0;
                while (i < this.ax.length) {
                    if (this.t == i + 1 || !(this.u != i + 1 || this.A || this.u == 0)) {
                        this.ay[i].a(this.e);
                        this.ay[i].a(canvas, c, d);
                        c = (int) (((double) c) + (((double) 1125974016) * this.e));
                    } else if (this.A || i + 1 != this.u) {
                        this.ax[i].a(this.e);
                        this.ax[i].a(canvas, c, d);
                        c = (int) (((double) c) + (((double) 1125974016) * this.e));
                    }
                    this.ai.setColor(-1);
                    this.ai.clearShadowLayer();
                    canvas.drawText(this.aA[i], ((float) this.ax[i].c()) + ((float) (this.ax[i].f / 2)), (float) (this.ax[i].d() + this.ax[i].g), this.ai);
                    i++;
                }
                return;
            }
            aDCVideo = this.d;
            if (ADCVideo.d && this.Q) {
                this.ap.a(this.f);
                this.aq.a(this.f);
                this.ar.a(this.f);
                this.as.a(this.f);
                i = (a.m || this.i == 0) ? this.d.t - this.ap.f : this.i;
                this.i = i;
                this.j = 0;
                this.k = 0;
                this.l = 0;
                if (this.H) {
                    this.aq.a(canvas, this.i, this.j);
                } else {
                    this.ap.a(canvas, this.i, this.j);
                }
                if (this.I) {
                    this.as.a(canvas, this.k, this.l);
                } else {
                    this.ar.a(canvas, this.k, this.l);
                }
                j();
                return;
            }
            if (this.d.G != null) {
                int i2;
                a.l.a(((double) this.d.G.getCurrentPosition()) / ((double) this.d.G.getDuration()), this.d.I);
                if (this.d.L) {
                    this.d.J.a((long) this.d.G.getCurrentPosition());
                }
                c = this.d.G.getCurrentPosition();
                i = ((this.s - c) + 999) / GameControllerDelegate.THUMBSTICK_LEFT_X;
                if (this.S && i == 1) {
                    i2 = 0;
                } else {
                    i2 = i;
                }
                if (i2 == 0) {
                    this.S = true;
                }
                if (c >= HttpResponseCode.INTERNAL_SERVER_ERROR) {
                    if (this.B) {
                        this.aD = (float) (360.0d / (((double) this.s) / 1000.0d));
                        this.B = false;
                        Rect rect = new Rect();
                        this.ai.getTextBounds("0123456789", 0, 9, rect);
                        this.aG = (float) rect.height();
                    }
                    this.aE = (float) getWidth();
                    this.aF = (float) getHeight();
                    this.aH = this.aG;
                    this.aI = (((float) this.d.u) - this.aG) - ((float) this.m);
                    this.aK.set(this.aH - (this.aG / 2.0f), this.aI - (2.0f * this.aG), this.aH + (2.0f * this.aG), this.aI + (this.aG / 2.0f));
                    this.aJ.setShadowLayer((float) ((int) (4.0d * this.e)), 0.0f, 0.0f, -16777216);
                    this.aC = (float) (((((double) this.s) / 1000.0d) - (((double) c) / 1000.0d)) * ((double) this.aD));
                    canvas.drawArc(this.aK, 270.0f, this.aC, false, this.aJ);
                    aDCVideo = this.d;
                    if (!ADCVideo.d) {
                        this.ai.setColor(-3355444);
                        this.ai.setShadowLayer((float) ((int) (2.0d * this.e)), 0.0f, 0.0f, -16777216);
                        this.ai.setTextAlign(Align.CENTER);
                        this.ai.setLinearText(true);
                        canvas.drawText(BuildConfig.FLAVOR + i2, this.aK.centerX(), (float) (((double) this.aK.centerY()) + (((double) this.ai.getFontMetrics().bottom) * 1.35d)), this.ai);
                    }
                    if (this.M) {
                        aDCVideo = this.d;
                        if (!ADCVideo.d && this.O) {
                            if (this.t == 10) {
                                this.ao.a(canvas, this.d.t - this.ao.f, (int) (this.e * 4.0d));
                            } else {
                                this.an.a(canvas, this.d.t - this.an.f, (int) (this.e * 4.0d));
                            }
                        }
                    }
                    if (this.L && this.P) {
                        if (!this.G && !this.T) {
                            if (this.J) {
                                this.au.c((int) (((float) (this.d.t - this.au.f)) - (this.aG / 2.0f)), ((this.d.u - this.au.g) - this.m) - ((int) (this.aG / 2.0f)));
                                this.au.a(canvas);
                            } else {
                                this.at.c((int) (((float) (this.d.t - this.at.f)) - (this.aG / 2.0f)), ((this.d.u - this.at.g) - this.m) - ((int) (this.aG / 2.0f)));
                                this.at.a(canvas);
                            }
                            this.aj.setTextAlign(Align.CENTER);
                            canvas.drawText(this.ab, (float) this.at.e.centerX(), (float) (((double) this.at.e.centerY()) + (((double) this.aj.getFontMetrics().bottom) * 1.35d)), this.aj);
                        } else if (!this.G && this.T) {
                            this.aw.c((int) (((float) (this.d.t - this.aw.f)) - (this.aG / 2.0f)), ((this.d.u - this.aw.g) - this.m) - ((int) (this.aG / 2.0f)));
                            this.aw.a(canvas);
                        }
                    }
                }
                if (v.I != null) {
                    v.I.onDraw(canvas);
                }
            }
            aDCVideo = this.d;
            if (ADCVideo.h) {
                invalidate();
            }
        }
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        this.m = this.d.u - i2;
        if (Build.MODEL.equals("Kindle Fire")) {
            this.m = 20;
        }
        if (Build.MODEL.equals("SCH-I800")) {
            this.m = 25;
        }
        if (Build.MODEL.equals("SHW-M380K") || Build.MODEL.equals("SHW-M380S") || Build.MODEL.equals("SHW-M380W")) {
            this.m = 40;
        }
    }

    void a(int i) {
        try {
            if (this.C || i == 10) {
                this.C = false;
                Object j;
                switch (i) {
                    case TwitterResponse.READ /*1*/:
                        this.t = 0;
                        a.a(String.VIDEO_INFO, "{\"ad_slot\":" + a.T.i.r.c + "}", this.d.I);
                        j = a.j("info_url");
                        l.b.a("INFO ").b(j);
                        if (!j.startsWith("market:") && !j.startsWith("amzn:")) {
                            AdColonyBrowser.url = j;
                            this.d.startActivity(new Intent(this.d, AdColonyBrowser.class));
                            break;
                        }
                        this.d.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(j)));
                        break;
                    case TwitterResponse.READ_WRITE /*2*/:
                        this.t = 0;
                        a.a("download", "{\"ad_slot\":" + a.T.i.r.c + "}", this.d.I);
                        j = a.j("download_url");
                        l.b.a("DOWNLOAD ").b(j);
                        if (!j.startsWith("market:") && !j.startsWith("amzn:")) {
                            AdColonyBrowser.url = j;
                            this.d.startActivity(new Intent(this.d, AdColonyBrowser.class));
                            break;
                        }
                        this.d.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(j)));
                        break;
                    case TwitterResponse.READ_WRITE_DIRECTMESSAGES /*3*/:
                        this.t = 0;
                        i();
                        invalidate();
                        break;
                    case ApiAccessUtil.SERVER_TYPE_AMAZON /*4*/:
                        this.t = 0;
                        this.d.G.a();
                        g();
                        break;
                    case AdInfo.BANNER_KIND_WALL1 /*10*/:
                        this.t = 0;
                        h();
                        break;
                    default:
                        this.t = 0;
                        break;
                }
                new Handler().postDelayed(new Runnable(this) {
                    final /* synthetic */ ad a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.C = true;
                    }
                }, 1500);
            }
        } catch (RuntimeException e) {
            this.C = true;
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (v.I != null) {
            v.I.onTouchEvent(motionEvent);
            return true;
        }
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        ADCVideo aDCVideo;
        if (action == 0) {
            aDCVideo = this.d;
            if (!ADCVideo.d || !this.Q) {
                aDCVideo = this.d;
                if (ADCVideo.d && this.N) {
                    x = (int) (((double) (motionEvent.getX() - ((float) this.am.c()))) / (this.e * 2.0d));
                    y = (int) (((double) (motionEvent.getY() - ((float) this.am.d()))) / (this.e * 2.0d));
                    if (this.t == 0 && y >= 235 && y < 305) {
                        action = a(x, y);
                        this.t = action;
                        this.u = action;
                        this.A = false;
                        invalidate();
                    }
                }
                if (this.M && this.O && this.d.G != null && a(this.an, r1, r0)) {
                    this.t = 10;
                    this.u = this.t;
                    this.A = false;
                    invalidate();
                    return true;
                } else if (this.L && this.P && (a(this.at, r1, r0) || a(this.aw, r1, r0))) {
                    this.J = true;
                    invalidate();
                    return true;
                }
            } else if (a(this.ap, x, y)) {
                this.H = true;
                invalidate();
                return true;
            } else if (!a(this.ar, x, y)) {
                return false;
            } else {
                this.I = true;
                invalidate();
                return true;
            }
        } else if (action == 1) {
            aDCVideo = this.d;
            if (ADCVideo.d && this.Q) {
                if (a(this.ap, x, y) && this.H) {
                    this.t = 4;
                    if (this.a != null) {
                        this.a.clearCache(true);
                    }
                    this.aM.sendMessageDelayed(this.aM.obtainMessage(this.t), 250);
                    return true;
                } else if (a(this.ar, x, y) && this.I) {
                    this.t = 3;
                    if (this.a != null) {
                        this.a.clearCache(true);
                    }
                    this.aM.sendMessageDelayed(this.aM.obtainMessage(this.t), 250);
                    return true;
                }
            }
            aDCVideo = this.d;
            if (ADCVideo.d && this.N) {
                x = (int) (((double) (motionEvent.getX() - ((float) this.am.c()))) / (this.e * 2.0d));
                y = (int) (((double) (motionEvent.getY() - ((float) this.am.d()))) / (this.e * 2.0d));
                if (!this.A && y >= 235 && y < 305) {
                    action = a(x, y);
                    if (action > 0 && action == this.u) {
                        this.aM.sendMessageDelayed(this.aM.obtainMessage(action), 250);
                    }
                }
            }
            if (this.M && this.O && this.d.G != null && a(this.an, r1, r0)) {
                this.t = 10;
                this.A = true;
                this.u = this.t;
                this.aM.sendMessageDelayed(this.aM.obtainMessage(this.t), 250);
                return true;
            } else if (this.L && this.P && (a(this.at, r1, r0) || a(this.aw, r1, r0))) {
                this.J = false;
                if (this.ad.startsWith("market:") || this.ad.startsWith("amzn:")) {
                    this.d.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(this.ad)));
                } else if (this.d.I.j.B == null || !this.d.I.j.B.c) {
                    AdColonyBrowser.url = this.ad;
                    this.d.startActivity(new Intent(this.d, AdColonyBrowser.class));
                } else {
                    this.ae = this.d.I.n;
                    this.d.I.z = AdColonyIAPEngagement.OVERLAY;
                    this.L = false;
                    this.U = true;
                    this.T = false;
                    h();
                }
                a.a("in_video_engagement", "{\"ad_slot\":" + a.T.i.r.c + "}", this.d.I);
                return true;
            } else {
                this.H = false;
                this.I = false;
                this.J = false;
                this.A = true;
                this.t = 0;
                invalidate();
                return true;
            }
        } else if (action == 3) {
            this.H = false;
            this.I = false;
            this.J = false;
            this.A = true;
            this.t = 0;
            invalidate();
            return true;
        }
        return true;
    }

    int a(int i, int i2) {
        if (i >= this.g && i < this.g + 62) {
            return 1;
        }
        if (i >= this.g + 78 && i < (this.g + 78) + 62) {
            return 2;
        }
        if (i >= (this.g + 78) + 78 && i < ((this.g + 78) + 78) + 62) {
            return 3;
        }
        if (i >= ((this.g + 78) + 78) + 78 && i < (((this.g + 78) + 78) + 78) + 62) {
            return 4;
        }
        if (this.d.G == null || !this.M || i < this.d.G.getWidth() - this.an.f || i2 > this.an.g) {
            return 0;
        }
        return 10;
    }

    public boolean a(ADCImage aDCImage, int i, int i2) {
        if (aDCImage != null && i < (aDCImage.c() + aDCImage.f) + 8 && i > aDCImage.c() - 8 && i2 < (aDCImage.d() + aDCImage.g) + 8 && i2 > aDCImage.d() - 8) {
            return true;
        }
        return false;
    }

    public void a() {
        boolean b = this.d.b();
        this.y |= b;
        if (this.d.G != null) {
            if (this.s <= 0) {
                this.s = this.d.G.getDuration();
            }
            if (b) {
                setLayoutParams(new LayoutParams(this.d.t, this.d.u, 17));
                this.d.G.setLayoutParams(new LayoutParams(this.d.x, this.d.y, 17));
                this.y = true;
            }
        }
        if (this.y) {
            double sqrt;
            this.y = false;
            if (this.z) {
                DisplayMetrics displayMetrics = AdColony.activity().getResources().getDisplayMetrics();
                float f = ((float) displayMetrics.widthPixels) / displayMetrics.xdpi;
                float f2 = ((float) displayMetrics.heightPixels) / displayMetrics.ydpi;
                sqrt = Math.sqrt((double) ((displayMetrics.heightPixels * displayMetrics.heightPixels) + (displayMetrics.widthPixels * displayMetrics.widthPixels))) / Math.sqrt((double) ((f * f) + (f2 * f2)));
                this.f = sqrt / 280.0d < 0.7d ? 0.7d : sqrt / 280.0d;
                if (!AdColony.isTablet() && this.f == 0.7d) {
                    this.f = 1.0d;
                }
                float f3 = this.f * 20.0d < 18.0d ? 18.0f : (float) (this.f * 20.0d);
                if (this.f * 20.0d < 18.0d) {
                    f = 18.0f;
                } else {
                    f = (float) (this.f * 20.0d);
                }
                this.ai.setTextSize(f3);
                this.ak.setTextSize(f3);
                this.aj.setTextSize(f);
                if (!(!this.L || this.at == null || this.au == null)) {
                    this.at.a(b(this.ab + (this.at.f * 2)), this.at.g);
                    this.au.a(b(this.ab + (this.au.f * 2)), this.au.g);
                }
                int i;
                if (this.d.t > this.d.u) {
                    i = this.d.u;
                } else {
                    i = this.d.t;
                }
                this.z = false;
            }
            if (this.Q) {
                if (b && this.a != null) {
                    this.a.setLayoutParams(new LayoutParams(this.d.t, this.d.u - this.m, 17));
                }
                this.e = ((double) this.d.y) / 640.0d < 0.9d ? 0.9d : ((double) this.d.y) / 640.0d;
                if (!AdColony.isTablet() && this.e == 0.9d) {
                    this.e = 1.2d;
                }
            }
            if (this.N) {
                double d = (double) (this.n / this.o);
                sqrt = ((double) this.d.t) / d > ((double) this.d.u) / 1.0d ? ((double) this.d.u) / 1.0d : ((double) this.d.t) / d;
                this.d.x = (int) (d * sqrt);
                this.d.y = (int) (sqrt * 1.0d);
                this.e = this.d.t > this.d.u ? ((double) this.d.y) / 640.0d : ((double) this.d.y) / 960.0d;
                if (((double) this.d.t) / ((double) this.n) > ((double) this.d.u) / ((double) this.o)) {
                    sqrt = ((double) this.d.u) / ((double) this.o);
                } else {
                    sqrt = ((double) this.d.t) / ((double) this.n);
                }
                this.am.a(sqrt);
                this.am.d(this.d.t, this.d.u);
            }
            if (!(!this.L || this.at == null || this.au == null)) {
                if (this.at == null || this.au == null || this.at.b == null || this.au.b == null) {
                    this.L = false;
                } else {
                    int height = (int) (((double) this.au.b.getHeight()) * this.f);
                    this.at.b(this.at.f, (int) (((double) this.at.b.getHeight()) * this.f));
                    this.au.b(this.au.f, height);
                }
            }
            if (this.M) {
                this.an.a(this.f);
                this.ao.a(this.f);
            }
        }
    }

    void b() {
        this.a = new WebView(this.d);
        this.a.setFocusable(true);
        this.a.setHorizontalScrollBarEnabled(false);
        this.a.setVerticalScrollBarEnabled(false);
        WebSettings settings = this.a.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setPluginState(PluginState.ON_DEMAND);
        settings.setBuiltInZoomControls(true);
        settings.setGeolocationEnabled(true);
        this.a.setWebChromeClient(new WebChromeClient(this) {
            final /* synthetic */ ad a;

            {
                this.a = r1;
            }

            public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                String sourceId = consoleMessage.sourceId();
                if (sourceId == null) {
                    sourceId = "Internal";
                } else {
                    int lastIndexOf = sourceId.lastIndexOf(47);
                    if (lastIndexOf != -1) {
                        sourceId = sourceId.substring(lastIndexOf + 1);
                    }
                }
                l.b.a(consoleMessage.message()).a(" [").a(sourceId).a(" line ").a(consoleMessage.lineNumber()).b((Object) "]");
                return true;
            }

            public void onGeolocationPermissionsShowPrompt(String str, Callback callback) {
                callback.invoke(str, true, false);
            }
        });
        this.d.Q = new FrameLayout(this.d);
        if (a.i("hardware_acceleration_disabled")) {
            try {
                this.d.Q.getClass().getMethod("setLayerType", new Class[]{Integer.TYPE, Paint.class}).invoke(this.a, new Object[]{Integer.valueOf(1), null});
            } catch (Exception e) {
            }
        }
        this.az = new m(this.d, this.a, this.d);
        this.a.setWebViewClient(new WebViewClient(this) {
            String a = a.ad;
            final /* synthetic */ ad b;

            {
                this.b = r2;
            }

            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                l.a.a("DEC request: ").b((Object) str);
                if (str.contains("mraid:")) {
                    this.b.az.a(str);
                    return true;
                } else if (str.contains("youtube")) {
                    Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("vnd.youtube:" + str));
                    intent.putExtra("VIDEO_ID", str);
                    this.b.d.startActivity(intent);
                    return true;
                } else if (str.contains("mraid.js")) {
                    return true;
                } else {
                    return false;
                }
            }

            public void onLoadResource(WebView webView, String str) {
                l.a.a("DEC onLoad: ").b((Object) str);
                if (str.equals(this.a)) {
                    l.a.b((Object) "DEC disabling mouse events");
                    this.b.a("if (typeof(CN) != 'undefined' && CN.div) {\n  if (typeof(cn_dispatch_on_touch_begin) != 'undefined') CN.div.removeEventListener('mousedown',  cn_dispatch_on_touch_begin, true);\n  if (typeof(cn_dispatch_on_touch_end) != 'undefined')   CN.div.removeEventListener('mouseup',  cn_dispatch_on_touch_end, true);\n  if (typeof(cn_dispatch_on_touch_move) != 'undefined')  CN.div.removeEventListener('mousemove',  cn_dispatch_on_touch_move, true);\n}\n");
                }
            }

            public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
                if (str.equals(this.a)) {
                    this.b.d.k = true;
                    this.b.v = System.currentTimeMillis();
                }
            }

            public void onPageFinished(WebView webView, String str) {
                l.a.a("onPageFinished - ").b((Object) str);
                l.a.a("END CARD URL = ").b(this.a);
                l.a.a("equals? ").b(str.equals(this.a));
                if (str.equals(this.a) || a.ad.startsWith(Operation.LESS_THAN)) {
                    l.a.b((Object) "DEC FINISHED LOADING");
                    this.b.D = false;
                    this.b.d.l = true;
                    this.b.w = System.currentTimeMillis();
                    this.b.d.p = ((double) (this.b.w - this.b.v)) / 1000.0d;
                }
                this.b.d.P.removeView(this.b.c);
            }
        });
    }

    public void c() {
        if (this.a != null) {
            if (this.d.I.o.startsWith(Operation.LESS_THAN)) {
                this.a.loadData(this.d.I.o, "text/html", null);
            } else {
                this.a.loadDataWithBaseURL(this.d.I.o, this.af, "text/html", null, null);
            }
            new f("htmltest").a(this.af);
            a("var is_tablet=" + (a.m ? TapjoyConstants.TJC_TRUE : TapjoyConstants.TJC_FALSE) + ";");
            String str = a.m ? "tablet" : "phone";
            a("adc_bridge.adc_version='" + a.ag + "'");
            a("adc_bridge.os_version='" + a.af + "'");
            a("adc_bridge.os_name='android'");
            a("adc_bridge.device_type='" + str + "'");
            a("adc_bridge.fireChangeEvent({state:'default'});");
            a("adc_bridge.fireReadyEvent()");
        }
    }

    public void onCompletion(MediaPlayer mediaPlayer) {
        d();
    }

    public void d() {
        d dVar = a.l;
        ADCVideo aDCVideo = this.d;
        dVar.a(ADCVideo.e, this.d.I);
        if (this.Q && this.D && a.ab) {
            this.d.P.addView(this.c);
            new Handler().postDelayed(new Runnable(this) {
                final /* synthetic */ ad a;

                {
                    this.a = r1;
                }

                public void run() {
                    if (this.a.D && this.a.d != null && this.a.Q && this.a.a != null) {
                        this.a.d.m = true;
                        this.a.g();
                    }
                }
            }, (long) (a.ac * GameControllerDelegate.THUMBSTICK_LEFT_X));
        }
        if (a.Z) {
            g();
        }
        a.a("card_shown", this.d.I);
        synchronized (this.aL) {
            this.ag = null;
            if (a.T.j.y.e) {
                this.ag = new b(a.T.j.y.g);
            }
        }
        if (this.Q) {
            Handler handler = new Handler();
            final View view = new View(this.d);
            Runnable anonymousClass6 = new Runnable(this) {
                final /* synthetic */ ad b;

                public void run() {
                    this.b.d.P.removeView(view);
                    this.b.a(true);
                    this.b.d.r = System.currentTimeMillis();
                }
            };
            view.setBackgroundColor(-16777216);
            this.d.P.addView(view);
            handler.postDelayed(anonymousClass6, 500);
            this.d.Q.setVisibility(0);
        }
        this.d.r = System.currentTimeMillis();
        f();
    }

    void e() {
        this.a.loadUrl(a.ad);
        l.a.a("Loading - end card url = ").b(a.ad);
    }

    void a(String str) {
        if (!this.N && this.a != null) {
            if (VERSION.SDK_INT >= 19) {
                this.a.evaluateJavascript(str, null);
            } else {
                this.a.loadUrl("javascript:" + str);
            }
        }
    }

    void a(boolean z) {
        if (!this.N) {
            if (z) {
                a("adc_bridge.fireChangeEvent({viewable:true});");
            } else {
                a("adc_bridge.fireChangeEvent({viewable:false});");
            }
        }
    }

    void b(boolean z) {
        if (!this.N) {
        }
    }

    public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        c(true);
        return true;
    }

    void f() {
        new Handler().postDelayed(new Runnable(this) {
            final /* synthetic */ ad a;

            {
                this.a = r1;
            }

            public void run() {
                if (this.a.d.G != null) {
                    this.a.d.G.setVisibility(8);
                }
            }
        }, 300);
        if (this.d.L) {
            this.d.J.h();
        }
        ADCVideo aDCVideo = this.d;
        ADCVideo.d = true;
        if (this.d.G != null) {
            this.d.G.a();
        }
        v.I = null;
        invalidate();
        this.I = false;
        invalidate();
    }

    void g() {
        if (this.d == null) {
            return;
        }
        if (!this.Q || (this.a != null && this.d.Q != null && this.d.P != null)) {
            a.M = true;
            this.d.s = System.currentTimeMillis();
            ADCVideo aDCVideo = this.d;
            aDCVideo.q += ((double) (this.d.s - this.d.r)) / 1000.0d;
            a.ak = true;
            for (int i = 0; i < a.aq.size(); i++) {
                if (a.aq.get(i) != null) {
                    ((AdColonyNativeAdView) a.aq.get(i)).a();
                }
            }
            try {
                this.d.V.close();
            } catch (Exception e) {
            }
            this.d.finish();
            this.ag = null;
            if (this.Q) {
                this.d.P.removeView(this.d.Q);
                this.a.destroy();
                this.a = null;
            }
            a.a(this.d.I);
            AdColonyBrowser.A = true;
            a.E = true;
        }
    }

    void h() {
        c(false);
    }

    void c(boolean z) {
        a.M = true;
        if (!a.T.b() || z) {
            for (int i = 0; i < a.aq.size(); i++) {
                if (a.aq.get(i) != null) {
                    ((AdColonyNativeAdView) a.aq.get(i)).a();
                }
            }
            this.d.finish();
            a.W.b(this.d.I);
            a.ak = true;
            a.E = true;
            AdColonyBrowser.A = true;
            return;
        }
        ADCVideo aDCVideo = this.d;
        ADCVideo.a = this.d.G.getCurrentPosition();
        v.I = new v(this.d, (AdColonyV4VCAd) a.T);
    }

    void i() {
        a.a("replay", this.d.I);
        ADCVideo aDCVideo = this.d;
        ADCVideo.e = true;
        aDCVideo = this.d;
        ADCVideo.d = false;
        aDCVideo = this.d;
        ADCVideo.a = 0;
        this.d.I.u = true;
        this.d.I.p = 0.0d;
        this.S = false;
        final View view = new View(this.d);
        view.setBackgroundColor(-16777216);
        this.d.P.addView(view, new LayoutParams(this.d.t, this.d.u, 17));
        new Handler().postDelayed(new Runnable(this) {
            final /* synthetic */ ad b;

            public void run() {
                if (this.b.Q) {
                    this.b.d.Q.setVisibility(4);
                }
                this.b.d.P.removeView(view);
            }
        }, 900);
        this.d.G.start();
        if (this.d.L) {
            try {
                this.d.J = com.immersion.hapticmediasdk.b.a(0, this.d);
                this.d.J.a(this.d.K);
            } catch (Exception e) {
                this.d.L = false;
            }
            if (this.d.J == null) {
                this.d.L = false;
            }
            if (this.d.L) {
                this.d.J.d();
            }
        }
        a.l.a(this.d.I);
        this.d.G.requestFocus();
        this.d.G.setBackgroundColor(0);
        this.d.G.setVisibility(0);
        a(false);
    }

    int b(String str) {
        this.aj.getTextWidths(str, aB);
        float f = 0.0f;
        for (int i = 0; i < str.length(); i++) {
            f += aB[i];
        }
        return (int) f;
    }

    void j() {
        getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener(this) {
            final /* synthetic */ ad b;

            public void onGlobalLayout() {
                Rect rect = new Rect();
                this.getWindowVisibleDisplayFrame(rect);
                if (this.b.a != null) {
                    this.b.b((this.getRootView().getHeight() - (rect.bottom - rect.top)) - ((this.b.d.u - this.b.a.getHeight()) / 2));
                }
                this.b.k();
            }
        });
    }

    void k() {
        if (this.h >= 70 && !this.E) {
            this.E = true;
            b(true);
        } else if (this.E && this.h == 0) {
            this.E = false;
            b(false);
        }
    }

    void b(int i) {
        this.h = i;
        if (i < 0) {
            this.h = 0;
        }
    }
}
