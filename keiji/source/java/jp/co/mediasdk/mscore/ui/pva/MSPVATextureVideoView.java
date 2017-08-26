package jp.co.mediasdk.mscore.ui.pva;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnInfoListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnVideoSizeChangedListener;
import android.net.Uri;
import android.util.Log;
import android.view.Surface;
import android.view.TextureView;
import android.view.TextureView.SurfaceTextureListener;
import android.view.View.MeasureSpec;
import android.widget.VideoView;
import java.util.Map;
import twitter4j.HttpResponseCode;

public class MSPVATextureVideoView extends TextureView {
    private final OnInfoListener A = new OnInfoListener(this) {
        final /* synthetic */ MSPVATextureVideoView a;

        {
            this.a = r1;
        }

        public boolean onInfo(MediaPlayer mediaPlayer, int i, int i2) {
            if (3 == i) {
            }
            if (701 == i) {
            }
            if (702 == i) {
            }
            return false;
        }
    };
    OnVideoSizeChangedListener a = new OnVideoSizeChangedListener(this) {
        final /* synthetic */ MSPVATextureVideoView a;

        {
            this.a = r1;
        }

        public void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i2) {
            this.a.k = mediaPlayer.getVideoWidth();
            this.a.l = mediaPlayer.getVideoHeight();
            if (this.a.k != 0 && this.a.l != 0) {
                this.a.requestLayout();
            }
        }
    };
    OnPreparedListener b = new OnPreparedListener(this) {
        final /* synthetic */ MSPVATextureVideoView a;

        {
            this.a = r1;
        }

        public void onPrepared(MediaPlayer mediaPlayer) {
            this.a.f = 2;
            this.a.s = this.a.t = this.a.u = true;
            if (this.a.n != null) {
                this.a.n.onPrepared(this.a.i);
            }
            this.a.k = mediaPlayer.getVideoWidth();
            this.a.l = mediaPlayer.getVideoHeight();
            int e = this.a.r;
            if (e != 0) {
                this.a.a(e);
            }
            if (this.a.g == 3) {
                this.a.b();
            }
        }
    };
    private String c = "MSPVATextureVideoView";
    private Uri d;
    private Map<String, String> e;
    private int f = 0;
    private int g = 0;
    private SurfaceTexture h = null;
    private MediaPlayer i = null;
    private int j;
    private int k;
    private int l;
    private OnCompletionListener m;
    private OnPreparedListener n;
    private int o;
    private OnErrorListener p;
    private OnInfoListener q;
    private int r;
    private boolean s;
    private boolean t;
    private boolean u;
    private OnCompletionListener v = new OnCompletionListener(this) {
        final /* synthetic */ MSPVATextureVideoView a;

        {
            this.a = r1;
        }

        public void onCompletion(MediaPlayer mediaPlayer) {
            this.a.f = 5;
            this.a.g = 5;
            if (this.a.m != null) {
                this.a.m.onCompletion(this.a.i);
            }
        }
    };
    private OnInfoListener w = new OnInfoListener(this) {
        final /* synthetic */ MSPVATextureVideoView a;

        {
            this.a = r1;
        }

        public boolean onInfo(MediaPlayer mediaPlayer, int i, int i2) {
            if (this.a.q != null) {
                this.a.q.onInfo(mediaPlayer, i, i2);
            }
            return true;
        }
    };
    private OnErrorListener x = new OnErrorListener(this) {
        final /* synthetic */ MSPVATextureVideoView a;

        {
            this.a = r1;
        }

        public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
            Log.d(this.a.c, "Error: " + i + "," + i2);
            this.a.f = -1;
            this.a.g = -1;
            if ((this.a.p == null || !this.a.p.onError(this.a.i, i, i2)) && this.a.getWindowToken() != null) {
                CharSequence charSequence;
                this.a.getContext().getResources();
                if (i == HttpResponseCode.OK) {
                    charSequence = "\u3053\u306e\u52d5\u753b\u306f\u3053\u306e\u7aef\u672b\u306b\u30b9\u30c8\u30ea\u30fc\u30df\u30f3\u30b0\u3067\u304d\u307e\u305b\u3093\u3002";
                } else {
                    charSequence = "\u3053\u306e\u52d5\u753b\u3092\u518d\u751f\u3067\u304d\u307e\u305b\u3093\u3002";
                }
                new Builder(this.a.getContext()).setMessage(charSequence).setPositiveButton("OK", new OnClickListener(this) {
                    final /* synthetic */ AnonymousClass5 a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (this.a.a.m != null) {
                            this.a.a.m.onCompletion(this.a.a.i);
                        }
                    }
                }).setCancelable(false).show();
            }
            return true;
        }
    };
    private OnBufferingUpdateListener y = new OnBufferingUpdateListener(this) {
        final /* synthetic */ MSPVATextureVideoView a;

        {
            this.a = r1;
        }

        public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
            this.a.o = i;
        }
    };
    private SurfaceTextureListener z = new SurfaceTextureListener(this) {
        final /* synthetic */ MSPVATextureVideoView a;

        {
            this.a = r1;
        }

        public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
            this.a.h = surfaceTexture;
            this.a.d();
        }

        public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
            Object obj = 1;
            Object obj2 = this.a.g == 3 ? 1 : null;
            if (!(this.a.k == i && this.a.l == i2)) {
                obj = null;
            }
            if (this.a.i != null && obj2 != null && r1 != null) {
                if (this.a.r != 0) {
                    this.a.a(this.a.r);
                }
                this.a.b();
            }
        }

        public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
            this.a.h = null;
            this.a.a(true);
            return false;
        }

        public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
            this.a.h = surfaceTexture;
        }
    };

    public MSPVATextureVideoView(Context context) {
        super(context);
        c();
    }

    protected void onMeasure(int i, int i2) {
        int defaultSize = getDefaultSize(this.k, i);
        int defaultSize2 = getDefaultSize(this.l, i2);
        if (this.k > 0 && this.l > 0) {
            int mode = MeasureSpec.getMode(i);
            int size = MeasureSpec.getSize(i);
            int mode2 = MeasureSpec.getMode(i2);
            defaultSize2 = MeasureSpec.getSize(i2);
            if (mode == 1073741824 && mode2 == 1073741824) {
                if (this.k * defaultSize2 < this.l * size) {
                    defaultSize = (this.k * defaultSize2) / this.l;
                } else if (this.k * defaultSize2 > this.l * size) {
                    defaultSize2 = (this.l * size) / this.k;
                    defaultSize = size;
                } else {
                    defaultSize = size;
                }
            } else if (mode == 1073741824) {
                defaultSize = (this.l * size) / this.k;
                if (mode2 != Integer.MIN_VALUE || defaultSize <= defaultSize2) {
                    defaultSize2 = defaultSize;
                    defaultSize = size;
                } else {
                    defaultSize = size;
                }
            } else if (mode2 == 1073741824) {
                defaultSize = (this.k * defaultSize2) / this.l;
                if (mode == Integer.MIN_VALUE && defaultSize > size) {
                    defaultSize = size;
                }
            } else {
                int i3 = this.k;
                defaultSize = this.l;
                if (mode2 != Integer.MIN_VALUE || defaultSize <= defaultSize2) {
                    defaultSize2 = defaultSize;
                    defaultSize = i3;
                } else {
                    defaultSize = (this.k * defaultSize2) / this.l;
                }
                if (mode == Integer.MIN_VALUE && r1 > size) {
                    defaultSize2 = (this.l * size) / this.k;
                    defaultSize = size;
                }
            }
        }
        setMeasuredDimension(defaultSize, defaultSize2);
    }

    public CharSequence getAccessibilityClassName() {
        return VideoView.class.getName();
    }

    private void c() {
        this.k = 0;
        this.l = 0;
        setSurfaceTextureListener(this.z);
        setFocusable(true);
        setFocusableInTouchMode(true);
        requestFocus();
        this.f = 0;
        this.g = 0;
        setOnInfoListener(this.A);
    }

    public void setVideoPath(String str) {
        setVideoURI(Uri.parse(str));
    }

    public void setVideoURI(Uri uri) {
        a(uri, null);
    }

    public void a(Uri uri, Map<String, String> map) {
        this.d = uri;
        this.e = map;
        this.r = 0;
        d();
        requestLayout();
        invalidate();
    }

    public void a() {
        if (this.i != null) {
            this.i.stop();
            this.i.release();
            this.i = null;
            this.f = 0;
            this.g = 0;
        }
    }

    private void d() {
        if (this.d != null && this.h != null) {
            a(false);
            try {
                this.i = new MediaPlayer();
                getContext();
                if (this.j != 0) {
                    this.i.setAudioSessionId(this.j);
                } else {
                    this.j = this.i.getAudioSessionId();
                }
                this.i.setOnPreparedListener(this.b);
                this.i.setOnVideoSizeChangedListener(this.a);
                this.i.setOnCompletionListener(this.v);
                this.i.setOnErrorListener(this.x);
                this.i.setOnInfoListener(this.w);
                this.i.setOnBufferingUpdateListener(this.y);
                this.o = 0;
                this.i.setDataSource(getContext(), this.d, this.e);
                this.i.setSurface(new Surface(this.h));
                this.i.setAudioStreamType(3);
                this.i.setScreenOnWhilePlaying(true);
                this.i.prepareAsync();
                this.f = 1;
            } catch (Throwable e) {
                Log.w(this.c, "Unable to open content: " + this.d, e);
                this.f = -1;
                this.g = -1;
                this.x.onError(this.i, 1, 0);
            } catch (Throwable e2) {
                Log.w(this.c, "Unable to open content: " + this.d, e2);
                this.f = -1;
                this.g = -1;
                this.x.onError(this.i, 1, 0);
            }
        }
    }

    public void setOnPreparedListener(OnPreparedListener onPreparedListener) {
        this.n = onPreparedListener;
    }

    public void setOnCompletionListener(OnCompletionListener onCompletionListener) {
        this.m = onCompletionListener;
    }

    public void setOnErrorListener(OnErrorListener onErrorListener) {
        this.p = onErrorListener;
    }

    public void setOnInfoListener(OnInfoListener onInfoListener) {
        this.q = onInfoListener;
    }

    private void a(boolean z) {
        if (this.i != null) {
            this.i.reset();
            this.i.release();
            this.i = null;
            this.f = 0;
            if (z) {
                this.g = 0;
            }
        }
    }

    public void b() {
        if (e()) {
            this.i.start();
            this.f = 3;
        }
        this.g = 3;
    }

    public int getDuration() {
        if (e()) {
            return this.i.getDuration();
        }
        return -1;
    }

    public int getCurrentPosition() {
        if (e()) {
            return this.i.getCurrentPosition();
        }
        return 0;
    }

    public void a(int i) {
        if (e()) {
            this.i.seekTo(i);
            this.r = 0;
            return;
        }
        this.r = i;
    }

    public int getBufferPercentage() {
        if (this.i != null) {
            return this.o;
        }
        return 0;
    }

    private boolean e() {
        return (this.i == null || this.f == -1 || this.f == 0 || this.f == 1) ? false : true;
    }

    public int getAudioSessionId() {
        if (this.j == 0) {
            MediaPlayer mediaPlayer = new MediaPlayer();
            this.j = mediaPlayer.getAudioSessionId();
            mediaPlayer.release();
        }
        return this.j;
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
    }
}
