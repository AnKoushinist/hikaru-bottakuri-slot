package com.jirbo.adcolony;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.PhoneStateListener;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.MediaController;
import android.widget.VideoView;
import com.immersion.hapticmediasdk.b;
import com.jirbo.adcolony.ADCDownload.Listener;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import org.cocos2dx.lib.BuildConfig;
import org.cocos2dx.lib.GameControllerDelegate;
import twitter4j.TwitterResponse;

public abstract class ADCVideo extends Activity implements OnPreparedListener, Listener {
    static int a;
    static int b;
    static int c;
    static boolean d;
    static boolean e;
    static boolean f;
    static boolean g;
    static boolean h;
    static boolean i;
    double A;
    String B = BuildConfig.FLAVOR;
    String C = BuildConfig.FLAVOR;
    boolean D = true;
    boolean E = true;
    boolean F;
    e G;
    ad H;
    AdColonyAd I;
    com.immersion.hapticmediasdk.a J;
    String K;
    boolean L;
    boolean M = true;
    String N = "Your purchase will begin shortly!";
    VideoView O;
    FrameLayout P;
    FrameLayout Q;
    FrameLayout R;
    Rect S = new Rect();
    ADCImage T;
    a U;
    FileInputStream V;
    PhoneStateListener W;
    boolean X = false;
    boolean Y = false;
    boolean j = true;
    boolean k;
    boolean l;
    boolean m;
    boolean n;
    boolean o;
    double p;
    double q;
    long r;
    long s;
    int t;
    int u;
    int v;
    int w;
    int x;
    int y;
    int z;

    class a extends View {
        Rect a = new Rect();
        final /* synthetic */ ADCVideo b;

        public a(ADCVideo aDCVideo, Activity activity) {
            this.b = aDCVideo;
            super(activity);
        }

        public void onDraw(Canvas canvas) {
            canvas.drawARGB(255, 0, 0, 0);
            getDrawingRect(this.a);
            this.b.T.a(canvas, (this.a.width() - this.b.T.f) / 2, (this.a.height() - this.b.T.g) / 2);
            invalidate();
        }
    }

    static void a() {
        a = 0;
        d = false;
        e = false;
        g = false;
    }

    public void onCreate(Bundle bundle) {
        int i = 1;
        a.ak = false;
        super.onCreate(bundle);
        this.I = a.T;
        if (this.I == null) {
            finish();
            return;
        }
        String j;
        boolean z;
        int i2;
        this.L = a.i("haptics_enabled");
        this.K = a.j("haptics_filepath");
        this.N = a.j("in_progress");
        if (this.I.x == null) {
            j = a.j("video_filepath");
        } else {
            j = this.I.x.f;
        }
        this.C = j;
        this.A = (double) a.h("video_duration");
        if (this.L) {
            try {
                this.J = b.a(0, this);
                this.J.a(this.K);
            } catch (Exception e) {
                e.printStackTrace();
                this.L = false;
            }
            if (this.J == null) {
                this.L = false;
            }
        }
        if (a.i("video_enabled")) {
            z = false;
        } else {
            z = true;
        }
        a.aa = z;
        if (a.i("end_card_enabled")) {
            z = false;
        } else {
            z = true;
        }
        a.Z = z;
        a.ab = a.i("load_timeout_enabled");
        a.ac = a.h("load_timeout");
        for (i2 = 0; i2 < a.aq.size(); i2++) {
            if (a.aq.get(i2) != null) {
                AdColonyNativeAdView adColonyNativeAdView = (AdColonyNativeAdView) a.aq.get(i2);
                if (adColonyNativeAdView.ag != null) {
                    adColonyNativeAdView.U.setVisibility(4);
                }
                if (adColonyNativeAdView.S != null) {
                    adColonyNativeAdView.S.setVisibility(4);
                }
            }
        }
        if (a.i("v4iap_enabled")) {
            this.I.z = AdColonyIAPEngagement.AUTOMATIC;
            this.I.v = true;
            this.I.n = a.j("product_id");
        }
        e = this.I.u;
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        if (a.m) {
            i2 = getResources().getConfiguration().orientation;
            int i3 = (i2 == 0 || i2 == 6 || i2 == 2) ? 6 : 7;
            a.F = i3;
            if (VERSION.SDK_INT < 10 || Build.MODEL.equals("Kindle Fire")) {
                if (Build.MODEL.equals("Kindle Fire")) {
                    getRequestedOrientation();
                    switch (((WindowManager) getSystemService("window")).getDefaultDisplay().getRotation()) {
                        case TwitterResponse.NONE /*0*/:
                            break;
                        case TwitterResponse.READ /*1*/:
                            i = 0;
                            break;
                        case TwitterResponse.READ_WRITE /*2*/:
                            i = 9;
                            break;
                        default:
                            i = 8;
                            break;
                    }
                }
                i = i2;
                a.F = i;
                setRequestedOrientation(i);
            } else {
                setRequestedOrientation(a.F);
            }
        } else if (VERSION.SDK_INT >= 10) {
            setRequestedOrientation(6);
        } else {
            setRequestedOrientation(0);
        }
        setVolumeControlStream(3);
        this.G = new e(this);
        this.G.a((OnPreparedListener) this);
        this.P = new FrameLayout(this);
        this.H = new ad(this);
        this.R = new FrameLayout(this);
        this.U = new a(this, this);
        this.T = new ADCImage(a.j("browser_icon"));
        AdColonyBrowser.A = false;
        a.U = this;
        a.V = this;
    }

    public void onPrepared(MediaPlayer mediaPlayer) {
        if (this.j) {
            int duration = this.G.getDuration() / GameControllerDelegate.THUMBSTICK_LEFT_X;
            l.a.a("duration, actual_duration = ").a(this.A).a(", ").b(duration);
            boolean z = this.A / ((double) duration) > 0.9d && this.A / ((double) duration) < 1.1d;
            if (z) {
                a.l.a(this.I);
                this.j = false;
                return;
            }
            finish();
        }
    }

    public void onResume() {
        h = true;
        super.onResume();
        AdColony.resume(this);
        if (a.a()) {
            finish();
        }
        b();
        if (this.D) {
            this.D = false;
            if (!d) {
                if (this.H.Q) {
                    this.Q.addView(this.H.a);
                }
                if (this.H.Q) {
                    this.Q.setVisibility(4);
                }
                if (Build.MODEL.equals("Kindle Fire")) {
                    this.H.m = 20;
                }
                if (Build.MODEL.equals("SCH-I800")) {
                    this.H.m = 25;
                }
                this.P.addView(this.G, new LayoutParams(this.x, this.y, 17));
                if (this.H.Q) {
                    this.P.addView(this.Q, new LayoutParams(this.t, this.u - this.H.m, 17));
                }
                this.P.addView(this.H, new LayoutParams(this.t, this.u, 17));
            }
        }
        if (g) {
            this.R.removeView(this.U);
            this.R.addView(this.U);
            setContentView(this.R);
        } else {
            setContentView(this.P);
            if (d) {
                this.r = System.currentTimeMillis();
            }
        }
        this.G.a(this.H);
        this.G.a(this.H);
        try {
            this.V = new FileInputStream(this.C);
            this.G.a(this.V.getFD());
            if (!i) {
                onWindowFocusChanged(true);
            }
            if (a.aa) {
                this.H.a();
                this.H.d();
            }
        } catch (IOException e) {
            a.e("Unable to play video: " + a.j("video_filepath"));
            this.H.c(true);
        }
    }

    public void onDestroy() {
        super.onDestroy();
        if (a.ak) {
            v.H = false;
            v.I = null;
        } else {
            v.H = false;
            v.I = null;
        }
        if (this.I != null && this.I.y != null && !this.I.w) {
            this.I.f = 1;
            this.I.a();
        }
    }

    boolean b() {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        this.t = displayMetrics.widthPixels;
        this.u = displayMetrics.heightPixels;
        this.t = displayMetrics.widthPixels;
        this.u = displayMetrics.heightPixels;
        this.z = -16777216;
        getWindow().setBackgroundDrawable(new ColorDrawable(this.z));
        int i = this.t;
        int i2 = this.u;
        this.x = i;
        this.y = i2;
        if (!a.m && this.x < this.y) {
            this.t = i2;
            this.u = i;
            this.x = i2;
            this.y = i;
        }
        if (!a.K) {
            return false;
        }
        a.K = false;
        return true;
    }

    public void onWindowFocusChanged(boolean z) {
        if (z) {
            i = false;
            if (d || !h) {
                if (g) {
                    if (this.O != null) {
                        this.O.seekTo(b);
                        this.O.start();
                        return;
                    }
                    if (this.R != null) {
                        this.R.removeAllViews();
                    }
                    setContentView(this.P);
                    return;
                } else if (d) {
                    this.H.invalidate();
                    return;
                } else {
                    return;
                }
            } else if (this.G != null) {
                if (c != 0) {
                    a = c;
                }
                c = 0;
                this.G.seekTo(a);
                if (a.m) {
                    Handler handler = new Handler();
                    Runnable anonymousClass1 = new Runnable(this) {
                        final /* synthetic */ ADCVideo a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            this.a.G.setBackgroundColor(0);
                        }
                    };
                    this.G.setBackgroundColor(-16777216);
                    handler.postDelayed(anonymousClass1, 900);
                } else {
                    this.G.setBackgroundColor(0);
                }
                if (!(v.H || this.Y)) {
                    this.H.S = false;
                    this.G.start();
                    this.F = true;
                    if (this.M) {
                        if (this.L) {
                            this.J.d();
                        }
                        new ADCDownload(a.l, this.I.x == null ? this.I.o : this.I.x.aq, this).b();
                        this.M = false;
                    } else if (this.L) {
                        this.J.f();
                    }
                }
                this.H.requestFocus();
                this.H.invalidate();
                return;
            } else {
                return;
            }
        }
        if (h && !this.Y) {
            if (this.L) {
                this.J.g();
            }
            a = this.G.getCurrentPosition();
            this.G.pause();
            this.F = false;
        }
        i = true;
    }

    public void on_download_finished(ADCDownload aDCDownload) {
        try {
            if (this.H.Q) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("<script type=\"text/javascript\">");
                stringBuilder.append(aa.a(a.ae, BuildConfig.FLAVOR));
                stringBuilder.append("</script>");
                if (aDCDownload.n == null) {
                    this.H.af = BuildConfig.FLAVOR;
                    return;
                }
                String replaceAll = aDCDownload.n.replaceAll("<script (type=\"text/javascript\")?((\\s)*src=\"mraid.js\"){1}></script>", stringBuilder.toString());
                if (this.H != null) {
                    this.H.af = replaceAll;
                    runOnUiThread(new Runnable(this) {
                        final /* synthetic */ ADCVideo a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            this.a.H.c();
                        }
                    });
                }
            }
        } catch (OutOfMemoryError e) {
            l.d.b((Object) "OutOfMemoryError - disabling AdColony.");
            this.H.c(true);
            AdColony.disable();
        }
    }

    public void onPause() {
        h = false;
        if (!g) {
            b = 0;
        } else if (this.O != null) {
            b = this.O.getCurrentPosition();
            this.O.stopPlayback();
        }
        if (d) {
            View view = new View(this);
            view.setBackgroundColor(-16777216);
            setContentView(view);
            this.s = System.currentTimeMillis();
            if (!isFinishing()) {
                this.q += ((double) (this.s - this.r)) / 1000.0d;
            }
        }
        if (this.G == null || this.Y) {
            a = 0;
        } else {
            if (this.G.getCurrentPosition() != 0) {
                a = this.G.getCurrentPosition();
            }
            this.G.a();
            this.F = false;
            this.G.setBackgroundColor(-16777216);
            if (this.L) {
                this.J.g();
            }
        }
        this.H.A = true;
        this.H.I = false;
        this.H.H = false;
        this.H.J = false;
        this.H.u = 0;
        this.H.t = 0;
        this.H.invalidate();
        super.onPause();
        AdColony.pause();
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (v.I != null && v.I.onKeyDown(i, keyEvent)) {
            return true;
        }
        if (i == 4) {
            if (d) {
                if (g) {
                    this.O.stopPlayback();
                    g = false;
                    this.R.removeAllViews();
                    setContentView(this.P);
                } else if (this.H != null && this.H.t == 0) {
                    a.ak = true;
                    this.H.g();
                }
            } else if (this.H != null && v.I != null) {
                Iterator it = v.I.o.iterator();
                while (it.hasNext()) {
                    ((ADCImage) it.next()).a();
                }
                v.I = null;
                v.H = false;
                this.G.start();
                this.F = true;
            } else if (this.H != null && this.H.M && this.H.O) {
                a.ak = true;
                this.H.h();
            }
            return true;
        } else if (i == 82) {
            return true;
        } else {
            return super.onKeyUp(i, keyEvent);
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    void a(String str) {
        this.B = str;
        g = true;
        this.O = new VideoView(this);
        this.O.setVideoURI(Uri.parse(str));
        new MediaController(this).setMediaPlayer(this.O);
        this.O.setLayoutParams(new LayoutParams(this.t, this.u, 17));
        this.R.addView(this.O);
        this.R.addView(this.U);
        setContentView(this.R);
        this.O.setOnCompletionListener(new OnCompletionListener(this) {
            final /* synthetic */ ADCVideo a;

            {
                this.a = r1;
            }

            public void onCompletion(MediaPlayer mediaPlayer) {
                this.a.setContentView(this.a.P);
                this.a.R.removeAllViews();
                ADCVideo.g = false;
            }
        });
        this.O.setOnPreparedListener(new OnPreparedListener(this) {
            final /* synthetic */ ADCVideo a;

            {
                this.a = r1;
            }

            public void onPrepared(MediaPlayer mediaPlayer) {
                this.a.R.removeViewAt(1);
            }
        });
        this.O.start();
    }
}
