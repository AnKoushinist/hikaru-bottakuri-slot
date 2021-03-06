package com.jirbo.adcolony;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnVideoSizeChangedListener;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.MediaController;
import android.widget.MediaController.MediaPlayerControl;
import com.tapjoy.TJAdUnitConstants.String;
import java.io.FileDescriptor;
import twitter4j.HttpResponseCode;

class e extends SurfaceView implements MediaPlayerControl {
    static final int e = -1;
    static final int f = 0;
    static final int g = 1;
    static final int h = 2;
    static final int i = 3;
    static final int j = 4;
    static final int k = 5;
    static final int l = 6;
    static final int m = 7;
    static final int n = 8;
    OnErrorListener A;
    int B;
    boolean C;
    boolean D;
    boolean E;
    boolean F;
    int G;
    OnVideoSizeChangedListener H;
    OnPreparedListener I;
    Callback J;
    private OnCompletionListener K;
    private OnErrorListener L;
    private OnBufferingUpdateListener M;
    String a;
    Uri b;
    FileDescriptor c;
    int d;
    int o;
    int p;
    SurfaceHolder q;
    MediaPlayer r;
    int s;
    int t;
    int u;
    int v;
    MediaController w;
    OnCompletionListener x;
    OnPreparedListener y;
    int z;

    e(Context context) {
        super(context);
        this.a = "ADCCustomVideoView";
        this.o = f;
        this.p = f;
        this.q = null;
        this.r = null;
        this.H = new OnVideoSizeChangedListener(this) {
            final /* synthetic */ e a;

            {
                this.a = r1;
            }

            public void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i2) {
                this.a.s = mediaPlayer.getVideoWidth();
                this.a.t = mediaPlayer.getVideoHeight();
                if (this.a.s != 0 && this.a.t != 0) {
                    this.a.getHolder().setFixedSize(this.a.s, this.a.t);
                }
            }
        };
        this.I = new OnPreparedListener(this) {
            final /* synthetic */ e a;

            {
                this.a = r1;
            }

            public void onPrepared(MediaPlayer mediaPlayer) {
                this.a.o = e.h;
                e eVar = this.a;
                e eVar2 = this.a;
                this.a.E = true;
                eVar2.D = true;
                eVar.C = true;
                if (this.a.y != null) {
                    this.a.y.onPrepared(this.a.r);
                }
                if (this.a.w != null) {
                    this.a.w.setEnabled(true);
                }
                this.a.s = mediaPlayer.getVideoWidth();
                this.a.t = mediaPlayer.getVideoHeight();
                int i = this.a.B;
                if (i != 0) {
                    this.a.seekTo(i);
                }
                if (this.a.s != 0 && this.a.t != 0) {
                    this.a.getHolder().setFixedSize(this.a.s, this.a.t);
                    if (this.a.u != this.a.s || this.a.v != this.a.t) {
                        return;
                    }
                    if (this.a.p == e.i) {
                        this.a.start();
                        if (this.a.w != null) {
                            this.a.w.show();
                        }
                    } else if (!this.a.isPlaying()) {
                        if ((i != 0 || this.a.getCurrentPosition() > 0) && this.a.w != null) {
                            this.a.w.show(e.f);
                        }
                    }
                } else if (this.a.p == e.i) {
                    this.a.start();
                }
            }
        };
        this.K = new OnCompletionListener(this) {
            final /* synthetic */ e a;

            {
                this.a = r1;
            }

            public void onCompletion(MediaPlayer mediaPlayer) {
                this.a.o = e.k;
                this.a.p = e.k;
                if (this.a.w != null) {
                    this.a.w.hide();
                }
                if (this.a.x != null) {
                    this.a.x.onCompletion(this.a.r);
                }
            }
        };
        this.L = new OnErrorListener(this) {
            final /* synthetic */ e a;

            {
                this.a = r1;
            }

            public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
                Log.d(this.a.a, "Error: " + i + "," + i2);
                this.a.o = e.e;
                this.a.p = e.e;
                if (this.a.w != null) {
                    this.a.w.hide();
                }
                if ((this.a.A == null || !this.a.A.onError(this.a.r, i, i2)) && this.a.getWindowToken() != null) {
                    CharSequence charSequence;
                    this.a.b().getResources();
                    if (i == HttpResponseCode.OK) {
                        charSequence = "Invalid progressive playback";
                    } else {
                        charSequence = "Unknown error";
                    }
                    new Builder(this.a.b()).setTitle("ERROR").setMessage(charSequence).setPositiveButton("OKAY", new OnClickListener(this) {
                        final /* synthetic */ AnonymousClass4 a;

                        {
                            this.a = r1;
                        }

                        public void onClick(DialogInterface dialogInterface, int i) {
                            if (this.a.a.x != null) {
                                this.a.a.x.onCompletion(this.a.a.r);
                            }
                        }
                    }).setCancelable(false).show();
                }
                return true;
            }
        };
        this.M = new OnBufferingUpdateListener(this) {
            final /* synthetic */ e a;

            {
                this.a = r1;
            }

            public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
                this.a.z = i;
            }
        };
        this.J = new Callback(this) {
            final /* synthetic */ e a;

            {
                this.a = r1;
            }

            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
                Object obj = e.g;
                this.a.u = i2;
                this.a.v = i3;
                Object obj2 = this.a.p == e.i ? e.g : e.f;
                if (!(this.a.s == i2 && this.a.t == i3)) {
                    obj = e.f;
                }
                if (this.a.r != null && obj2 != null && r1 != null) {
                    if (this.a.B != 0) {
                        this.a.seekTo(this.a.B);
                    }
                    this.a.start();
                    if (this.a.w != null) {
                        this.a.w.show();
                    }
                }
            }

            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                this.a.q = surfaceHolder;
                if (this.a.r != null && this.a.o == e.l && this.a.p == e.m) {
                    this.a.r.setDisplay(this.a.q);
                    this.a.d();
                    return;
                }
                this.a.f();
            }

            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                this.a.q = null;
                if (this.a.w != null) {
                    this.a.w.hide();
                }
                if (this.a.o != e.l) {
                    this.a.a(true);
                }
            }
        };
        e();
    }

    e(Context context, boolean z) {
        super(context);
        this.a = "ADCCustomVideoView";
        this.o = f;
        this.p = f;
        this.q = null;
        this.r = null;
        this.H = /* anonymous class already generated */;
        this.I = /* anonymous class already generated */;
        this.K = /* anonymous class already generated */;
        this.L = /* anonymous class already generated */;
        this.M = /* anonymous class already generated */;
        this.J = /* anonymous class already generated */;
        this.F = z;
        e();
    }

    public e(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, f);
        e();
    }

    public e(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = "ADCCustomVideoView";
        this.o = f;
        this.p = f;
        this.q = null;
        this.r = null;
        this.H = /* anonymous class already generated */;
        this.I = /* anonymous class already generated */;
        this.K = /* anonymous class already generated */;
        this.L = /* anonymous class already generated */;
        this.M = /* anonymous class already generated */;
        this.J = /* anonymous class already generated */;
        e();
    }

    protected void onMeasure(int i, int i2) {
        int defaultSize = getDefaultSize(this.s, i);
        int defaultSize2 = getDefaultSize(this.t, i2);
        if (this.s > 0 && this.t > 0) {
            if (this.s * defaultSize2 > this.t * defaultSize) {
                defaultSize2 = (this.t * defaultSize) / this.s;
            } else if (this.s * defaultSize2 < this.t * defaultSize) {
                defaultSize = (this.s * defaultSize2) / this.t;
            }
        }
        setMeasuredDimension(defaultSize, defaultSize2);
    }

    public int a(int i, int i2) {
        int mode = MeasureSpec.getMode(i2);
        int size = MeasureSpec.getSize(i2);
        switch (mode) {
            case Integer.MIN_VALUE:
                return Math.min(i, size);
            case 1073741824:
                return size;
            default:
                return i;
        }
    }

    private void e() {
        this.s = f;
        this.t = f;
        getHolder().addCallback(this.J);
        getHolder().setType(i);
        setFocusable(true);
        setFocusableInTouchMode(true);
        if (this.F) {
            requestFocus();
        }
        this.o = f;
        this.p = f;
    }

    public int getAudioSessionId() {
        return f;
    }

    public void a(String str) {
        a(Uri.parse(str));
    }

    public void a(FileDescriptor fileDescriptor) {
        this.c = fileDescriptor;
        this.B = f;
        f();
        requestLayout();
        invalidate();
    }

    public void a(Uri uri) {
        this.b = uri;
        this.B = f;
        f();
        requestLayout();
        invalidate();
    }

    public void a() {
        if (this.r != null) {
            this.r.stop();
            this.r.release();
            this.r = null;
            this.o = f;
            this.p = f;
        }
    }

    Activity b() {
        return AdColony.activity();
    }

    private void f() {
        if ((this.b != null || this.c != null) && this.q != null) {
            Intent intent = new Intent("com.android.music.musicservicecommand");
            intent.putExtra(String.COMMAND, "pause");
            b().sendBroadcast(intent);
            a(false);
            try {
                this.r = new MediaPlayer();
                this.r.setOnPreparedListener(this.I);
                this.r.setOnVideoSizeChangedListener(this.H);
                this.d = e;
                this.r.setOnCompletionListener(this.K);
                this.r.setOnErrorListener(this.L);
                this.r.setOnBufferingUpdateListener(this.M);
                this.z = f;
                if (this.b != null) {
                    this.r.setDataSource(b(), this.b);
                } else {
                    this.r.setDataSource(this.c);
                }
                this.r.setDisplay(this.q);
                this.r.setAudioStreamType(i);
                this.r.setScreenOnWhilePlaying(true);
                this.r.prepare();
                this.o = g;
                g();
            } catch (Throwable e) {
                if (this.b != null) {
                    Log.w(this.a, "Unable to open content: " + this.b, e);
                } else {
                    Log.w(this.a, "Unable to open content");
                }
                this.o = e;
                this.p = e;
                this.L.onError(this.r, g, f);
                e.printStackTrace();
            } catch (Throwable e2) {
                if (this.b != null) {
                    Log.w(this.a, "Unable to open content: " + this.b, e2);
                } else {
                    Log.w(this.a, "Unable to open content");
                }
                this.o = e;
                this.p = e;
                this.L.onError(this.r, g, f);
                e2.printStackTrace();
            }
        }
    }

    public void a(MediaController mediaController) {
        if (this.w != null) {
            this.w.hide();
        }
        this.w = mediaController;
        g();
    }

    private void g() {
        if (this.r != null && this.w != null) {
            View view;
            this.w.setMediaPlayer(this);
            if (getParent() instanceof View) {
                view = (View) getParent();
            } else {
                view = this;
            }
            this.w.setAnchorView(view);
            this.w.setEnabled(i());
        }
    }

    public void a(OnPreparedListener onPreparedListener) {
        this.y = onPreparedListener;
    }

    public void a(OnCompletionListener onCompletionListener) {
        this.x = onCompletionListener;
    }

    public void a(OnErrorListener onErrorListener) {
        this.A = onErrorListener;
    }

    private void a(boolean z) {
        if (this.r != null) {
            this.r.reset();
            this.r.release();
            this.r = null;
            this.o = f;
            if (z) {
                this.p = f;
            }
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (i() && this.w != null) {
            h();
        }
        return false;
    }

    public boolean onTrackballEvent(MotionEvent motionEvent) {
        if (i() && this.w != null) {
            h();
        }
        return false;
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        boolean z = (i == j || i == 24 || i == 25 || i == 82 || i == k || i == l) ? false : true;
        if (i() && z && this.w != null) {
            if (i == 79 || i == 85) {
                if (this.r.isPlaying()) {
                    pause();
                    this.w.show();
                    return true;
                }
                start();
                this.w.hide();
                return true;
            } else if (i == 86 && this.r.isPlaying()) {
                pause();
                this.w.show();
            } else {
                h();
            }
        }
        return super.onKeyDown(i, keyEvent);
    }

    private void h() {
        if (this.w.isShowing()) {
            this.w.hide();
        } else {
            this.w.show();
        }
    }

    public void start() {
        if (i()) {
            this.r.start();
            this.o = i;
        }
        this.p = i;
    }

    public void pause() {
        if (i() && this.r.isPlaying()) {
            this.r.pause();
            this.o = j;
        }
        this.p = j;
    }

    public void c() {
        if (i()) {
            this.r.stop();
            this.G = this.o;
            this.o = l;
            this.p = l;
        }
    }

    public void d() {
        if (this.q == null && this.o == l) {
            this.p = m;
        } else if (this.r != null && this.o == l) {
            this.r.start();
            this.o = this.G;
            this.p = this.G;
        } else if (this.o == n) {
            f();
        }
    }

    public int getDuration() {
        if (!i()) {
            this.d = e;
            return this.d;
        } else if (this.d > 0) {
            return this.d;
        } else {
            this.d = this.r.getDuration();
            return this.d;
        }
    }

    public int getCurrentPosition() {
        if (i()) {
            return this.r.getCurrentPosition();
        }
        return f;
    }

    public void seekTo(int i) {
        if (i()) {
            this.r.seekTo(i);
            this.B = f;
            return;
        }
        this.B = i;
    }

    public boolean isPlaying() {
        return i() && this.r.isPlaying();
    }

    public int getBufferPercentage() {
        if (this.r != null) {
            return this.z;
        }
        return f;
    }

    private boolean i() {
        return (this.r == null || this.o == e || this.o == 0 || this.o == g) ? false : true;
    }

    public boolean canPause() {
        return this.C;
    }

    public boolean canSeekBackward() {
        return this.D;
    }

    public boolean canSeekForward() {
        return this.E;
    }
}
