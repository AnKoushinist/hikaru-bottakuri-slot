package com.vungle.publisher;

import android.animation.ObjectAnimator;
import android.app.AlertDialog;
import android.content.Context;
import android.database.ContentObserver;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.provider.Settings.System;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.TouchDelegate;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.VideoView;
import com.tapjoy.TJAdUnitConstants.String;
import com.tapjoy.TapjoyConnectCore;
import com.vungle.log.Logger;
import com.vungle.publisher.inject.Injector;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import org.cocos2dx.lib.BuildConfig;
import org.cocos2dx.lib.GameControllerDelegate;

/* compiled from: vungle */
public final class op extends ms implements OnCompletionListener, OnErrorListener, OnPreparedListener {
    private VideoView A;
    private ViewGroup B;
    private Bitmap C;
    private Bitmap D;
    private final Handler E = new Handler();
    private final Runnable F = new d(this);
    private c G;
    private String H;
    private int I;
    private boolean J;
    private int K;
    private boolean L;
    private AtomicBoolean M = new AtomicBoolean();
    private int N;
    jj<?> e;
    ImageView f;
    ImageView g;
    TouchDelegate h;
    AtomicBoolean i = new AtomicBoolean();
    int j;
    AtomicBoolean k = new AtomicBoolean();
    int l;
    @Inject
    nb m;
    @Inject
    ql n;
    @Inject
    a o;
    @Inject
    com.vungle.publisher.og.a p;
    @Inject
    com.vungle.publisher.oj.a q;
    @Inject
    com.vungle.publisher.nx.a r;
    @Inject
    pn s;
    @Inject
    agw t;
    @Inject
    by u;
    @Inject
    a v;
    @Inject
    lr w;
    private oj x;
    private nx y;
    private RelativeLayout z;

    @Singleton
    /* compiled from: vungle */
    public static class a {
        @Inject
        Provider<op> a;

        @Inject
        a() {
        }
    }

    /* compiled from: vungle */
    class b implements OnClickListener {
        final /* synthetic */ op a;

        b(op opVar) {
            this.a = opVar;
        }

        public final void onClick(View view) {
            Logger.v(Logger.AD_TAG, "close clicked");
            this.a.f(false);
        }
    }

    @Singleton
    /* compiled from: vungle */
    static class c extends qe {
        op a;

        @Singleton
        /* compiled from: vungle */
        public static class a {
            @Inject
            c a;

            @Inject
            a() {
            }
        }

        @Inject
        c() {
        }

        public final void onEvent(ba baVar) {
            nx j = this.a.y;
            boolean z = baVar.b != 0;
            if (z != j.a) {
                j.setAndCacheSoundEnabled(z);
                Logger.d(Logger.AD_TAG, "volume change " + (z ? "un" : BuildConfig.FLAVOR) + "mute");
                j.b();
                j.a(z);
            }
        }
    }

    /* compiled from: vungle */
    class d implements Runnable {
        final /* synthetic */ op a;

        d(op opVar) {
            this.a = opVar;
        }

        public final void run() {
            try {
                this.a.c(false);
                this.a.c();
                if (!this.a.k.get()) {
                    op opVar = this.a;
                    if (opVar.l > (opVar.j * GameControllerDelegate.THUMBSTICK_LEFT_X) - 750 && opVar.k.compareAndSet(false, true)) {
                        ObjectAnimator.ofFloat(opVar.g, "alpha", new float[]{TapjoyConnectCore.DEFAULT_CURRENCY_MULTIPLIER}).setDuration(750).start();
                    }
                }
                this.a.x.setCurrentTimeMillis(this.a.A.getCurrentPosition());
                this.a.n.a(new al(this.a.l));
            } catch (Throwable e) {
                Logger.w(Logger.AD_TAG, e);
            } finally {
                this.a.E.postDelayed(this, 50);
            }
        }
    }

    public final View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.B = new RelativeLayout(getActivity());
        this.B.setBackgroundColor(-16777216);
        return this.B;
    }

    public final void onActivityCreated(Bundle bundle) {
        try {
            Integer num;
            super.onActivityCreated(bundle);
            try {
                Injector.b().a(this);
            } catch (Throwable th) {
                Logger.i(Logger.AD_TAG, "Unexpected error in fragment injection", th);
            }
            if (bundle != null) {
                Logger.d(Logger.AD_TAG, "Restoring saved state");
                this.b = (n) bundle.getParcelable("adConfig");
                this.L = bundle.getBoolean("adStarted");
                this.N = bundle.getInt("currentVideoPosition");
            }
            if (this.L) {
                this.n.a(new ax());
            }
            Context activity = getActivity();
            a aVar = this.v;
            aVar.a.a = this;
            this.G = aVar.a;
            View videoView = new VideoView(activity);
            this.A = videoView;
            View oaVar = new oa(activity);
            this.g = oaVar;
            com.vungle.publisher.nx.a aVar2 = this.r;
            boolean isSoundEnabled = this.b.isSoundEnabled();
            nx nxVar = new nx(aVar2.a);
            nxVar.d = aVar2.b.a(com.vungle.publisher.rb.a.muteOn);
            nxVar.e = aVar2.b.a(com.vungle.publisher.rb.a.muteOff);
            nxVar.b = aVar2.c;
            nxVar.a = isSoundEnabled;
            nxVar.c = aVar2.d;
            nxVar.setOnClickListener(new com.vungle.publisher.nx.a.AnonymousClass1(aVar2, nxVar));
            this.y = nxVar;
            View a = this.p.a(activity, false);
            com.vungle.publisher.oj.a aVar3 = this.q;
            View ojVar = new oj(aVar3.a);
            ojVar.e = 1;
            ojVar.d = (int) aVar3.b.a(2);
            this.x = ojVar;
            this.B.addView(videoView);
            LayoutParams layoutParams = (LayoutParams) videoView.getLayoutParams();
            layoutParams.height = -1;
            layoutParams.width = -1;
            layoutParams.addRule(13);
            View relativeLayout = new RelativeLayout(activity);
            this.B.addView(relativeLayout);
            layoutParams = (LayoutParams) relativeLayout.getLayoutParams();
            layoutParams.width = -1;
            layoutParams.height = -2;
            layoutParams.addRule(10);
            relativeLayout.addView(oaVar);
            this.t.a(oaVar, com.vungle.publisher.rb.a.close);
            layoutParams = (LayoutParams) oaVar.getLayoutParams();
            layoutParams.addRule(9);
            layoutParams.addRule(15);
            relativeLayout.addView(a);
            a.setVisibility(0);
            layoutParams = (LayoutParams) a.getLayoutParams();
            layoutParams.addRule(11);
            layoutParams.addRule(15);
            ViewGroup.LayoutParams layoutParams2 = new LayoutParams(-1, ojVar.getProgressBarHeight());
            this.B.addView(ojVar, layoutParams2);
            layoutParams2.addRule(12);
            a = new RelativeLayout(activity);
            this.z = a;
            this.B.addView(a);
            layoutParams = (LayoutParams) a.getLayoutParams();
            layoutParams.width = -1;
            layoutParams.height = -2;
            layoutParams.addRule(2, 1);
            a.addView(nxVar);
            layoutParams = (LayoutParams) nxVar.getLayoutParams();
            layoutParams.addRule(11);
            layoutParams.addRule(15);
            int round = Math.round(this.m.a(2));
            int round2 = Math.round(this.m.a(1));
            relativeLayout.setPadding(round, round2, round, round2);
            a.setPadding(round, round2, round, round2);
            this.g.setScaleType(ScaleType.FIT_CENTER);
            this.y.setScaleType(ScaleType.FIT_CENTER);
            Logger.i(Logger.AD_TAG, "video play URI = " + this.e.q());
            videoView.setVideoURI(this.e.q());
            if (Boolean.TRUE.equals(this.e.h)) {
                this.C = this.t.a(com.vungle.publisher.rb.a.cta);
                this.D = this.t.a(com.vungle.publisher.rb.a.ctaDisabled);
                Integer num2 = this.e.f;
                num = this.e.j;
                Integer num3;
                if (num2 == null) {
                    if (num != null) {
                        Logger.v(Logger.AD_TAG, "overriding cta enabled from null to " + num);
                        num2 = num;
                    }
                    num3 = num;
                    num = num2;
                    num2 = num3;
                } else if (num == null) {
                    Logger.v(Logger.AD_TAG, "overriding cta shown from null to " + num2);
                    num = num2;
                } else {
                    if (num.intValue() > num2.intValue()) {
                        Logger.v(Logger.AD_TAG, "overriding cta shown from " + num + " to " + num2);
                        num = num2;
                    }
                    num3 = num;
                    num = num2;
                    num2 = num3;
                }
                Logger.d(Logger.AD_TAG, "cta shown at " + num2 + " seconds; enabled at " + num + " seconds");
                this.I = num == null ? 0 : num.intValue();
                this.K = num2 == null ? 0 : num2.intValue();
                final View oaVar2 = new oa(getActivity());
                this.f = oaVar2;
                this.z.addView(oaVar2);
                layoutParams = (LayoutParams) oaVar2.getLayoutParams();
                layoutParams.addRule(9);
                layoutParams.addRule(15);
                oaVar2.setScaleType(ScaleType.FIT_CENTER);
                final Float f = this.e.e;
                if (f == null || f.floatValue() <= TapjoyConnectCore.DEFAULT_CURRENCY_MULTIPLIER) {
                    Logger.v(Logger.AD_TAG, "cta clickable area not scaled");
                } else {
                    oaVar2.post(new Runnable(this) {
                        final /* synthetic */ op c;

                        public final void run() {
                            float sqrt = (float) Math.sqrt((double) f.floatValue());
                            int height = oaVar2.getHeight();
                            int width = oaVar2.getWidth();
                            int round = Math.round(((float) height) * sqrt);
                            int round2 = Math.round(sqrt * ((float) width));
                            Logger.v(Logger.AD_TAG, "scaling cta clickable area " + f + "x - width: " + width + " --> " + round2 + ", height: " + height + " --> " + round);
                            Rect rect = new Rect();
                            oaVar2.getHitRect(rect);
                            rect.bottom = rect.top + round;
                            rect.left = rect.right - round2;
                            this.c.h = new TouchDelegate(rect, oaVar2);
                        }
                    });
                }
                if (Boolean.TRUE.equals(this.e.i)) {
                    oaVar2.setAlpha(0.0f);
                    oaVar2.setImageBitmap(this.C);
                } else {
                    d(this.K >= this.I);
                }
                oaVar2.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ op b;

                    public final void onClick(View view) {
                        if (this.b.J) {
                            Logger.d(Logger.AD_TAG, "cta overlay onClick");
                            oaVar2.setOnClickListener(null);
                            this.b.b(false);
                            this.b.n.a(new w(com.vungle.publisher.jl.a.video_click));
                            return;
                        }
                        Logger.v(Logger.AD_TAG, "cta overlay onClick, but not enabled");
                    }
                });
            }
            num = this.b.isIncentivized() ? this.e.k : this.e.l;
            if (num == null) {
                this.j = 0;
                this.k.set(true);
            } else {
                this.j = num.intValue();
                oaVar.setAlpha(0.0f);
                this.k.set(false);
            }
            oaVar.setOnClickListener(new b(this));
            videoView.setOnTouchListener(new OnTouchListener(this) {
                final /* synthetic */ op a;

                {
                    this.a = r1;
                }

                public final boolean onTouch(View view, MotionEvent motionEvent) {
                    Logger.v(Logger.AD_TAG, "video onTouch");
                    if (this.a.h != null) {
                        this.a.h.onTouchEvent(motionEvent);
                    }
                    op opVar = this.a;
                    if (motionEvent.getAction() != 0) {
                        return false;
                    }
                    if (opVar.f == null || !opVar.i.compareAndSet(false, true)) {
                        return true;
                    }
                    ObjectAnimator.ofFloat(opVar.f, "alpha", new float[]{TapjoyConnectCore.DEFAULT_CURRENCY_MULTIPLIER}).setDuration(750).start();
                    return true;
                }
            });
            videoView.setOnCompletionListener(this);
            videoView.setOnErrorListener(this);
            videoView.setOnPreparedListener(this);
        } catch (Throwable th2) {
            Logger.e(Logger.AD_TAG, "exception in onActivityCreated", th2);
        }
    }

    public final void onSaveInstanceState(Bundle bundle) {
        try {
            super.onSaveInstanceState(bundle);
            bundle.putParcelable("adConfig", (Parcelable) this.b);
            bundle.putBoolean("adStarted", this.L);
            bundle.putInt("currentVideoPosition", this.N);
        } catch (Throwable e) {
            this.c.a(Logger.AD_TAG, "exception in onSaveInstanceState", e);
        }
    }

    public final void onPrepared(MediaPlayer mediaPlayer) {
        int duration = mediaPlayer.getDuration();
        Logger.d(Logger.AD_TAG, "video ready: duration " + duration + " ms");
        this.x.setMaxTimeMillis(duration);
        this.n.a(new ac(duration));
        if (!this.s.a(getActivity())) {
            e();
        }
    }

    public final void onResume() {
        boolean z = true;
        try {
            super.onResume();
            Logger.d(Logger.AD_TAG, "video onResume");
            nx nxVar = this.y;
            String str = Logger.AD_TAG;
            StringBuilder stringBuilder = new StringBuilder("refresh mute state. is muted? ");
            if (nxVar.a) {
                z = false;
            }
            Logger.d(str, stringBuilder.append(z).toString());
            nxVar.setSoundEnabled(nxVar.a);
            nxVar.c.a(new az(nxVar.b.c()));
            ContentObserver contentObserver = this.u;
            if (!contentObserver.b) {
                contentObserver.a = contentObserver.c.b();
                contentObserver.b = true;
            }
            contentObserver.f.getContentResolver().registerContentObserver(System.CONTENT_URI, true, contentObserver);
            this.G.register();
            e();
        } catch (Throwable e) {
            this.c.a(Logger.AD_TAG, "error resuming VideoFragment", e);
            d();
        }
    }

    public final void onPause() {
        Logger.d(Logger.AD_TAG, "video onPause");
        try {
            super.onPause();
            if (this.A.isPlaying()) {
                Logger.d(Logger.AD_TAG, "Pausing video");
                this.N = this.A.getCurrentPosition();
                this.A.pause();
                d();
            }
            ContentObserver contentObserver = this.u;
            contentObserver.f.getContentResolver().unregisterContentObserver(contentObserver);
            this.G.unregister();
            this.w.a(true);
            if (this.L) {
                this.n.a(new al(this.A.getCurrentPosition()));
            }
        } catch (Throwable e) {
            this.c.a(Logger.AD_TAG, "error in VideoFragment.onPause()", e);
            d();
        }
    }

    public final void a(boolean z) {
        super.a(z);
        if (z) {
            onResume();
        } else {
            onPause();
        }
    }

    private void d() {
        this.E.removeCallbacks(this.F);
    }

    private void e() {
        boolean z = false;
        if (!this.A.isPlaying()) {
            boolean z2 = this.a != null && this.a.isShowing();
            if (z2) {
                Logger.v(Logger.AD_TAG, "Confirm dialog showing. Starting video briefly.");
                this.A.seekTo(this.N);
                this.A.start();
                this.A.pause();
                this.l = this.N;
                return;
            }
            Logger.d(Logger.AD_TAG, "Starting video");
            if (!this.L) {
                z = true;
            }
            this.L = true;
            this.A.requestFocus();
            this.A.seekTo(this.N);
            this.A.start();
            this.l = this.N;
            c();
            this.E.post(this.F);
            if (z) {
                this.n.a(new ar());
            }
        }
    }

    final void b(boolean z) {
        d();
        c(z);
        this.n.a(z ? new aa(this.l) : new ad(this.l));
        this.L = false;
        this.A.seekTo(0);
        this.l = 0;
        this.N = 0;
        this.M.set(false);
    }

    final void c(boolean z) {
        int duration = z ? this.A.getDuration() : this.A.getCurrentPosition();
        if (duration > this.l) {
            this.l = duration;
        }
    }

    public final void a() {
        Logger.v(Logger.AD_TAG, "back button pressed");
        f(true);
    }

    final void c() {
        boolean z = true;
        if (!Boolean.TRUE.equals(this.e.h)) {
            return;
        }
        if (Boolean.TRUE.equals(this.e.i)) {
            if (this.f.getAlpha() < TapjoyConnectCore.DEFAULT_CURRENCY_MULTIPLIER) {
                z = false;
            }
            e(z);
            return;
        }
        if (this.l > (this.K * GameControllerDelegate.THUMBSTICK_LEFT_X) - 750 && this.i.compareAndSet(false, true)) {
            ObjectAnimator.ofFloat(this.f, "alpha", new float[]{TapjoyConnectCore.DEFAULT_CURRENCY_MULTIPLIER}).setDuration(750).start();
        }
        if (this.l < this.I * GameControllerDelegate.THUMBSTICK_LEFT_X) {
            z = false;
        }
        e(z);
    }

    private void d(boolean z) {
        boolean z2 = z && this.H != null;
        Logger.v(Logger.AD_TAG, "cta button " + (z2 ? String.ENABLED : "disabled"));
        this.J = z2;
        this.f.setImageBitmap(z2 ? this.C : this.D);
    }

    private void e(boolean z) {
        if (z != this.J) {
            d(z);
        }
    }

    public final void onCompletion(MediaPlayer mediaPlayer) {
        Logger.d(Logger.AD_TAG, "video.onCompletion");
        b(true);
        this.n.a(new aw());
    }

    public final boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        Logger.e(Logger.AD_TAG, "video.onError: " + i + ", " + i2);
        b(false);
        this.n.a(new aw());
        return true;
    }

    private void f() {
        b(false);
        this.A.stopPlayback();
        this.n.a(new av());
    }

    private void f(boolean z) {
        if (z) {
            boolean z2 = this.k.get() || this.b.isBackButtonImmediatelyEnabled();
            if (!z2) {
                return;
            }
        } else if (!this.k.get()) {
            return;
        }
        if (this.M.compareAndSet(false, true)) {
            Logger.d(Logger.AD_TAG, "exiting video");
            if (this.b.isIncentivized()) {
                onPause();
                AlertDialog a = this.a != null ? this.a : this.d.a(getActivity(), this.b, new com.vungle.publisher.mz.a(this) {
                    final /* synthetic */ op a;

                    {
                        this.a = r1;
                    }

                    public final void a() {
                        d();
                    }

                    public final void b() {
                        Logger.d(Logger.AD_TAG, "cancel video");
                        this.a.f();
                    }

                    public final void c() {
                        d();
                    }

                    private void d() {
                        this.a.onResume();
                        this.a.M.set(false);
                    }
                });
                this.a = a;
                a.show();
                return;
            }
            this.g.setOnClickListener(null);
            f();
        }
    }

    public final boolean a(int i) {
        if (i == 24 && this.w.b() == 0) {
            Logger.d(Logger.AD_TAG, "volume up - unmuting");
            this.w.a(true);
        }
        return false;
    }

    public final String b() {
        return "videoFragment";
    }
}
