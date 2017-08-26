package com.adcolony.sdk;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.TextureView;
import android.view.TextureView.SurfaceTextureListener;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import com.tapjoy.TJAdUnitConstants.String;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import jp.co.vaz.creator.hikaru2.R;
import org.cocos2dx.lib.BuildConfig;
import org.cocos2dx.lib.GameControllerDelegate;
import org.json.JSONObject;
import twitter4j.TwitterResponse;

class ac extends TextureView implements OnCompletionListener, OnErrorListener, OnPreparedListener, SurfaceTextureListener {
    private boolean A;
    private boolean B;
    private boolean C;
    private boolean D;
    private boolean E;
    private String F;
    private String G;
    private FileInputStream H;
    private o I;
    private al J;
    private Surface K;
    private SurfaceTexture L;
    private RectF M = new RectF();
    private a N;
    private ProgressBar O;
    private MediaPlayer P;
    private JSONObject Q = bb.a();
    private ExecutorService R = Executors.newSingleThreadExecutor();
    private float a;
    private float b;
    private float c;
    private float d;
    private float e;
    private float f;
    private int g;
    private boolean h = true;
    private Paint i = new Paint();
    private Paint j = new Paint(1);
    private int k;
    private int l;
    private int m;
    private int n;
    private int o;
    private int p;
    private int q;
    private int r;
    private double s;
    private double t;
    private long u;
    private boolean v;
    private boolean w;
    private boolean x;
    private boolean y;
    private boolean z;

    private class a extends View {
        final /* synthetic */ ac a;

        a(ac acVar, Context context) {
            this.a = acVar;
            super(context);
            setWillNotDraw(false);
            try {
                getClass().getMethod("setLayerType", new Class[]{Integer.TYPE, Paint.class}).invoke(this, new Object[]{Integer.valueOf(1), null});
            } catch (Exception e) {
            }
        }

        public void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            canvas.drawArc(this.a.M, 270.0f, this.a.d, false, this.a.i);
            canvas.drawText(BuildConfig.FLAVOR + this.a.g, this.a.M.centerX(), (float) (((double) this.a.M.centerY()) + (((double) this.a.j.getFontMetrics().bottom) * 1.35d)), this.a.j);
            invalidate();
        }
    }

    ac(Context context, o oVar, int i, al alVar) {
        super(context);
        this.J = alVar;
        this.I = oVar;
        this.o = i;
        setSurfaceTextureListener(this);
    }

    void a() {
        if (this.L != null) {
            this.A = true;
        }
        this.R.shutdown();
    }

    void b() {
        JSONObject b = this.I.b();
        this.G = bb.a(b, "ad_session_id");
        this.k = bb.b(b, "x");
        this.l = bb.b(b, "y");
        this.m = bb.b(b, "width");
        this.n = bb.b(b, "height");
        this.C = bb.c(b, "enable_timer");
        this.E = bb.c(b, "enable_progress");
        this.F = bb.a(b, "filepath");
        this.p = bb.b(b, "video_width");
        this.q = bb.b(b, "video_height");
        this.f = n.a().a.j();
        bd.b.b("Original video dimensions = " + this.p + "x" + this.q);
        setVisibility(4);
        LayoutParams layoutParams = new FrameLayout.LayoutParams(this.m, this.n);
        layoutParams.setMargins(this.k, this.l, 0, 0);
        layoutParams.gravity = 0;
        this.J.addView(this, layoutParams);
        if (this.E && n.d()) {
            this.O = new ProgressBar(n.c());
            this.J.addView(this.O, new FrameLayout.LayoutParams((int) (this.f * 100.0f), (int) (this.f * 100.0f), 17));
        }
        this.P = new MediaPlayer();
        this.z = false;
        try {
            if (this.F.startsWith("http")) {
                this.B = true;
                this.P.setDataSource(this.F);
            } else {
                this.H = new FileInputStream(this.F);
                this.P.setDataSource(this.H.getFD());
            }
            this.P.setOnErrorListener(this);
            this.P.setOnPreparedListener(this);
            this.P.setOnCompletionListener(this);
            this.P.prepareAsync();
        } catch (IOException e) {
            bd.g.a("Failed to create/prepare MediaPlayer: ").b(e.toString());
            k();
        }
        this.J.l().add(n.a("VideoView.play", new q(this) {
            final /* synthetic */ ac a;

            {
                this.a = r1;
            }

            public void a(o oVar) {
                if (this.a.a(oVar)) {
                    this.a.e();
                }
            }
        }, true));
        this.J.l().add(n.a("VideoView.set_bounds", new q(this) {
            final /* synthetic */ ac a;

            {
                this.a = r1;
            }

            public void a(o oVar) {
                if (this.a.a(oVar)) {
                    this.a.b(oVar);
                }
            }
        }, true));
        this.J.l().add(n.a("VideoView.set_visible", new q(this) {
            final /* synthetic */ ac a;

            {
                this.a = r1;
            }

            public void a(o oVar) {
                if (this.a.a(oVar)) {
                    this.a.c(oVar);
                }
            }
        }, true));
        this.J.l().add(n.a("VideoView.pause", new q(this) {
            final /* synthetic */ ac a;

            {
                this.a = r1;
            }

            public void a(o oVar) {
                if (this.a.a(oVar)) {
                    this.a.f();
                }
            }
        }, true));
        this.J.l().add(n.a("VideoView.seek_to_time", new q(this) {
            final /* synthetic */ ac a;

            {
                this.a = r1;
            }

            public void a(o oVar) {
                if (this.a.a(oVar)) {
                    this.a.e(oVar);
                }
            }
        }, true));
        this.J.l().add(n.a("VideoView.set_volume", new q(this) {
            final /* synthetic */ ac a;

            {
                this.a = r1;
            }

            public void a(o oVar) {
                if (this.a.a(oVar)) {
                    this.a.d(oVar);
                }
            }
        }, true));
        this.J.m().add("VideoView.play");
        this.J.m().add("VideoView.set_bounds");
        this.J.m().add("VideoView.set_visible");
        this.J.m().add("VideoView.pause");
        this.J.m().add("VideoView.seek_to_time");
        this.J.m().add("VideoView.set_volume");
    }

    private boolean a(o oVar) {
        JSONObject b = oVar.b();
        return bb.b(b, "id") == this.o && bb.b(b, "container_id") == this.J.c() && bb.a(b, "ad_session_id").equals(this.J.a());
    }

    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        if (surfaceTexture == null || this.A) {
            bd.h.b((Object) "Null texture provided by system's onSurfaceTextureAvailable or MediaPlayer has been destroyed.");
            return;
        }
        this.K = new Surface(surfaceTexture);
        try {
            this.P.setSurface(this.K);
        } catch (IllegalStateException e) {
            bd.g.b((Object) "IllegalStateException thrown when calling MediaPlayer.setSurface()");
            k();
        }
        this.L = surfaceTexture;
    }

    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
        this.L = surfaceTexture;
    }

    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        this.L = surfaceTexture;
        if (!this.A) {
            return false;
        }
        surfaceTexture.release();
        return true;
    }

    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
        this.L = surfaceTexture;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        aq a = n.a();
        am h = a.h();
        int action = motionEvent.getAction() & 255;
        if (action != 0 && action != 1 && action != 3 && action != 2 && action != 5 && action != 6) {
            return false;
        }
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        JSONObject a2 = bb.a();
        bb.b(a2, "view_id", this.o);
        bb.a(a2, "ad_session_id", this.G);
        bb.b(a2, "container_x", this.k + x);
        bb.b(a2, "container_y", this.l + y);
        bb.b(a2, "view_x", x);
        bb.b(a2, "view_y", y);
        bb.b(a2, "id", this.J.c());
        switch (action) {
            case TwitterResponse.NONE /*0*/:
                new o("AdContainer.on_touch_began", this.J.b(), a2).a();
                break;
            case TwitterResponse.READ /*1*/:
                if (!this.J.p()) {
                    a.a((ae) h.d().get(this.G));
                }
                new o("AdContainer.on_touch_ended", this.J.b(), a2).a();
                break;
            case TwitterResponse.READ_WRITE /*2*/:
                new o("AdContainer.on_touch_moved", this.J.b(), a2).a();
                break;
            case TwitterResponse.READ_WRITE_DIRECTMESSAGES /*3*/:
                new o("AdContainer.on_touch_cancelled", this.J.b(), a2).a();
                break;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsHeaderTextAppearance /*5*/:
                int action2 = (motionEvent.getAction() & 65280) >> 8;
                bb.b(a2, "container_x", ((int) motionEvent.getX(action2)) + this.k);
                bb.b(a2, "container_y", ((int) motionEvent.getY(action2)) + this.l);
                bb.b(a2, "view_x", (int) motionEvent.getX(action2));
                bb.b(a2, "view_y", (int) motionEvent.getY(action2));
                new o("AdContainer.on_touch_began", this.J.b(), a2).a();
                break;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                action = (motionEvent.getAction() & 65280) >> 8;
                bb.b(a2, "container_x", ((int) motionEvent.getX(action)) + this.k);
                bb.b(a2, "container_y", ((int) motionEvent.getY(action)) + this.l);
                bb.b(a2, "view_x", (int) motionEvent.getX(action));
                bb.b(a2, "view_y", (int) motionEvent.getY(action));
                if (!this.J.p()) {
                    a.a((ae) h.d().get(this.G));
                }
                new o("AdContainer.on_touch_ended", this.J.b(), a2).a();
                break;
        }
        return true;
    }

    public void onMeasure(int i, int i2) {
        double d = ((double) this.m) / ((double) this.p);
        double d2 = ((double) this.n) / ((double) this.q);
        if (d <= d2) {
            d2 = d;
        }
        int i3 = (int) (((double) this.p) * d2);
        int i4 = (int) (d2 * ((double) this.q));
        bd.d.a("setMeasuredDimension to ").a(i3).a(" by ").b(i4);
        setMeasuredDimension(i3, i4);
        if (this.B) {
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) getLayoutParams();
            layoutParams.width = i3;
            layoutParams.height = i4;
            layoutParams.gravity = 17;
            layoutParams.setMargins(0, 0, 0, 0);
            setLayoutParams(layoutParams);
        }
    }

    private void k() {
        JSONObject a = bb.a();
        bb.a(a, "id", this.G);
        new o("AdSession.on_error", this.J.b(), a).a();
        this.v = true;
    }

    public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        k();
        bd.g.a("MediaPlayer error: ").a(i).a(",").b(i2);
        return true;
    }

    public void onPrepared(MediaPlayer mediaPlayer) {
        this.z = true;
        if (this.E) {
            this.J.removeView(this.O);
        }
        if (this.B) {
            this.p = mediaPlayer.getVideoWidth();
            this.q = mediaPlayer.getVideoHeight();
            onMeasure(this.p, this.q);
            bd.d.a("MediaPlayer getVideoWidth = ").b(mediaPlayer.getVideoWidth());
            bd.d.a("MediaPlayer getVideoHeight = ").b(mediaPlayer.getVideoHeight());
        }
        JSONObject a = bb.a();
        bb.b(a, "id", this.o);
        bb.b(a, "container_id", this.J.c());
        bb.a(a, "ad_session_id", this.G);
        bd.b.b((Object) "ADCVideoView is prepared");
        new o("VideoView.on_ready", this.J.b(), a).a();
    }

    public void onCompletion(MediaPlayer mediaPlayer) {
        this.v = true;
        this.s = this.t;
        bb.b(this.Q, "id", this.o);
        bb.b(this.Q, "container_id", this.J.c());
        bb.a(this.Q, "ad_session_id", this.G);
        bb.a(this.Q, "elapsed", this.s);
        bb.a(this.Q, "duration", this.t);
        new o("VideoView.on_progress", this.J.b(), this.Q).a();
    }

    private void l() {
        try {
            this.R.submit(new Runnable(this) {
                final /* synthetic */ ac a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.u = 0;
                    while (!this.a.v && !this.a.y && n.d()) {
                        if (!this.a.v && !this.a.A) {
                            if (this.a.P.isPlaying()) {
                                if (this.a.u == 0 && n.b) {
                                    this.a.u = System.currentTimeMillis();
                                }
                                this.a.x = true;
                                this.a.s = ((double) this.a.P.getCurrentPosition()) / 1000.0d;
                                this.a.t = ((double) this.a.P.getDuration()) / 1000.0d;
                                if (System.currentTimeMillis() - this.a.u > 1000 && !this.a.D && n.b) {
                                    if (this.a.s == 0.0d) {
                                        bd.h.b((Object) "getCurrentPosition() not working, firing AdSession.on_error");
                                        this.a.k();
                                    } else {
                                        this.a.D = true;
                                    }
                                }
                                if (this.a.C) {
                                    this.a.c();
                                }
                            }
                            if (!(!this.a.x || this.a.v || this.a.y)) {
                                bb.b(this.a.Q, "id", this.a.o);
                                bb.b(this.a.Q, "container_id", this.a.J.c());
                                bb.a(this.a.Q, "ad_session_id", this.a.G);
                                bb.a(this.a.Q, "elapsed", this.a.s);
                                bb.a(this.a.Q, "duration", this.a.t);
                                new o("VideoView.on_progress", this.a.J.b(), this.a.Q).a();
                            }
                            if (this.a.w || n.c().isFinishing()) {
                                this.a.w = false;
                                this.a.d();
                                return;
                            }
                            try {
                                Thread.sleep(50);
                            } catch (InterruptedException e) {
                                this.a.k();
                                bd.g.b((Object) "InterruptedException in ADCVideoView's update thread.");
                            }
                        } else {
                            return;
                        }
                    }
                    if (this.a.w) {
                        this.a.d();
                    }
                }
            });
        } catch (RejectedExecutionException e) {
            k();
        }
    }

    void c() {
        float f = 6.0f;
        float f2 = 4.0f;
        if (this.h) {
            this.e = (float) (360.0d / this.t);
            this.j.setColor(-3355444);
            this.j.setShadowLayer((float) ((int) (this.f * 2.0f)), 0.0f, 0.0f, -16777216);
            this.j.setTextAlign(Align.CENTER);
            this.j.setLinearText(true);
            this.j.setTextSize(12.0f * this.f);
            this.i.setStyle(Style.STROKE);
            if (this.f * 2.0f <= 6.0f) {
                f = this.f * 2.0f;
            }
            if (f >= 4.0f) {
                f2 = f;
            }
            this.i.setStrokeWidth(f2);
            this.i.setShadowLayer((float) ((int) (this.f * 3.0f)), 0.0f, 0.0f, -16777216);
            this.i.setColor(-3355444);
            Rect rect = new Rect();
            this.j.getTextBounds("0123456789", 0, 9, rect);
            this.c = (float) rect.height();
            if (n.d()) {
                ab.a(new Runnable(this) {
                    final /* synthetic */ ac a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.N = new a(this.a, n.c());
                        LayoutParams layoutParams = new FrameLayout.LayoutParams((int) (this.a.c * 4.0f), (int) (this.a.c * 4.0f));
                        layoutParams.setMargins(0, this.a.J.n() - ((int) (this.a.c * 4.0f)), 0, 0);
                        layoutParams.gravity = 0;
                        this.a.J.addView(this.a.N, layoutParams);
                    }
                });
            }
            this.h = false;
        }
        this.g = (int) (this.t - this.s);
        this.a = (float) ((int) this.c);
        this.b = (float) ((int) (this.c * 3.0f));
        this.M.set(this.a - (this.c / 2.0f), this.b - (this.c * 2.0f), this.a + (this.c * 2.0f), this.b + (this.c / 2.0f));
        this.d = (float) (((double) this.e) * (this.t - this.s));
    }

    void d() {
        bd.d.b((Object) "MediaPlayer stopped and released.");
        if (!this.v && this.z) {
            this.P.stop();
        }
        if (this.O != null) {
            this.J.removeView(this.O);
        }
        this.v = true;
        this.z = false;
        this.P.release();
    }

    private void b(o oVar) {
        JSONObject b = oVar.b();
        this.k = bb.b(b, "x");
        this.l = bb.b(b, "y");
        this.m = bb.b(b, "width");
        this.n = bb.b(b, "height");
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) getLayoutParams();
        layoutParams.setMargins(this.k, this.l, 0, 0);
        layoutParams.width = this.m;
        layoutParams.height = this.n;
        setLayoutParams(layoutParams);
        if (this.C && this.N != null) {
            LayoutParams layoutParams2 = new FrameLayout.LayoutParams((int) (this.c * 4.0f), (int) (this.c * 4.0f));
            layoutParams2.setMargins(0, this.J.n() - ((int) (this.c * 4.0f)), 0, 0);
            layoutParams2.gravity = 0;
            this.N.setLayoutParams(layoutParams2);
        }
    }

    private void c(o oVar) {
        if (bb.c(oVar.b(), String.VISIBLE)) {
            setVisibility(0);
            if (this.C && this.N != null) {
                this.N.setVisibility(0);
                return;
            }
            return;
        }
        setVisibility(4);
        if (this.C && this.N != null) {
            this.N.setVisibility(4);
        }
    }

    private boolean d(o oVar) {
        if (!this.z) {
            return false;
        }
        float d = (float) bb.d(oVar.b(), "volume");
        this.P.setVolume(d, d);
        return true;
    }

    boolean e() {
        if (!this.z) {
            return false;
        }
        if (!this.y && n.b) {
            this.P.start();
            l();
            bd.b.b((Object) "MediaPlayer is prepared - ADCVideoView play() called.");
        } else if (!this.v && n.b) {
            this.P.start();
            this.y = false;
            if (!this.R.isShutdown()) {
                l();
            }
            if (this.N != null) {
                this.N.invalidate();
            }
        }
        setWillNotDraw(false);
        return true;
    }

    boolean f() {
        if (!this.z) {
            bd.f.b((Object) "ADCVideoView pause() called while MediaPlayer is not prepared.");
            return false;
        } else if (this.x) {
            this.r = this.P.getCurrentPosition();
            this.t = (double) this.P.getDuration();
            this.P.pause();
            this.y = true;
            bd.b.b((Object) "Video view paused");
            return true;
        } else {
            bd.d.b((Object) "Ignoring ADCVideoView pause due to invalid MediaPlayer state.");
            return false;
        }
    }

    private boolean e(o oVar) {
        if (!this.z) {
            return false;
        }
        if (this.v) {
            this.v = false;
        }
        this.P.seekTo(bb.b(oVar.b(), "time") * GameControllerDelegate.THUMBSTICK_LEFT_X);
        JSONObject a = bb.a();
        bb.b(a, "id", this.o);
        bb.a(a, "ad_session_id", this.G);
        bb.a(a, "success", true);
        oVar.a(a).a();
        return true;
    }

    void g() {
        this.w = true;
    }

    boolean h() {
        return this.P != null;
    }

    MediaPlayer i() {
        return this.P;
    }

    boolean j() {
        return this.v;
    }
}
