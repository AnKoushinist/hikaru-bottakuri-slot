package jp.co.mediasdk.mscore.ui.pva;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Handler;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import java.util.Timer;
import java.util.TimerTask;
import jp.co.mediasdk.android.StringUtilEqualsSupport;
import jp.co.mediasdk.mscore.ui.common.MSParameterSupport;
import jp.co.mediasdk.mscore.ui.pva.MSPVAVolumeButton.OnVolumeButtonListener;

public class MSPVAVideoView extends FrameLayout implements OnVolumeButtonListener {
    private FrameLayout a = this;
    private MSPVATextureVideoView b = null;
    private String c;
    private OnCompletionListener d = null;
    private OnPreparedListener e = null;
    private OnErrorListener f = null;
    private boolean g = false;
    private final Handler h = new Handler();
    private int i;
    private boolean j = false;
    private Timer k = null;
    private MSPVAVideoTracking l = null;
    private OnCurrentVideoTimeListener m = null;
    private int n = 0;
    private MediaPlayer o = null;
    private MSPVAVolumeButton p;
    private UpdateTimeDisplayTask q = null;

    public interface OnCurrentVideoTimeListener {
        void a(int i);
    }

    private class UpdateTimeDisplayTask extends TimerTask {
        final /* synthetic */ MSPVAVideoView a;

        private UpdateTimeDisplayTask(MSPVAVideoView mSPVAVideoView) {
            this.a = mSPVAVideoView;
        }

        public void run() {
            this.a.h.post(new Runnable(this) {
                final /* synthetic */ UpdateTimeDisplayTask a;

                {
                    this.a = r1;
                }

                public void run() {
                    if (this.a.a.b != null) {
                        int currentPosition = this.a.a.b.getCurrentPosition();
                        int m = this.a.a.i - currentPosition;
                        if (this.a.a.n > 0 && currentPosition > 0 && this.a.a.l.c()) {
                            this.a.a.l.b(this.a.a.n);
                            this.a.a.l.a(false);
                        }
                        if (this.a.a.m != null) {
                            this.a.a.m.a(m);
                        }
                        if (this.a.a.l.a(currentPosition)) {
                            this.a.a.l.c(this.a.a.n);
                        }
                    }
                }
            });
        }
    }

    public void setPlayCount(int i) {
        this.n = i;
    }

    public int getPlayCount() {
        return this.n;
    }

    public boolean getError() {
        return this.j;
    }

    public MSPVAVideoView(Context context, FrameLayout frameLayout) {
        super(context);
        this.a = frameLayout;
        setBackgroundColor(-16777216);
        a(context, 0);
        this.n = 0;
    }

    public FrameLayout getFrameLayout() {
        return this.a;
    }

    public void setCurrentVideoTimeListener(OnCurrentVideoTimeListener onCurrentVideoTimeListener) {
        this.m = onCurrentVideoTimeListener;
    }

    private void a(Context context, int i) {
        this.o = null;
        this.l = new MSPVAVideoTracking();
        this.l.a();
        this.b = new MSPVATextureVideoView(context);
        LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        layoutParams.gravity = 17;
        this.b.setLayoutParams(layoutParams);
        if (this.a == null) {
            this.p = new MSPVAVolumeButton(context, this);
        } else {
            this.p = new MSPVAVolumeButton(context, this.a);
        }
        this.p.a((OnVolumeButtonListener) this);
        if (StringUtilEqualsSupport.a("1", MSParameterSupport.a("mute"))) {
            this.p.a(true);
        } else {
            this.p.a(false);
        }
        this.b.setOnPreparedListener(new OnPreparedListener(this) {
            final /* synthetic */ MSPVAVideoView a;

            {
                this.a = r1;
            }

            public void onPrepared(MediaPlayer mediaPlayer) {
                this.a.o = mediaPlayer;
                if (this.a.p.a()) {
                    mediaPlayer.setVolume(0.0f, 0.0f);
                }
                this.a.p.b();
                if (this.a.g) {
                    this.a.b.b();
                    this.a.i = this.a.b.getDuration();
                    this.a.b();
                    if (this.a.q == null) {
                        this.a.q = new UpdateTimeDisplayTask();
                    }
                    this.a.k = new Timer("timer", false);
                    this.a.k.schedule(this.a.q, 0, 1000);
                    if (this.a.e != null) {
                        this.a.e.onPrepared(mediaPlayer);
                    }
                    this.a.l.b();
                    this.a.l.b(this.a.n);
                    return;
                }
                this.a.b.a(0);
            }
        });
        this.b.setOnCompletionListener(new OnCompletionListener(this) {
            final /* synthetic */ MSPVAVideoView a;

            {
                this.a = r1;
            }

            public void onCompletion(MediaPlayer mediaPlayer) {
                this.a.g = false;
                this.a.l.a();
                if (!(this.a.j || this.a.d == null)) {
                    this.a.d.onCompletion(mediaPlayer);
                }
                if (!this.a.j) {
                    this.a.l.d(this.a.n);
                }
                this.a.b();
                this.a.n = this.a.n + 1;
                this.a.e();
            }
        });
        this.b.setOnErrorListener(new OnErrorListener(this) {
            final /* synthetic */ MSPVAVideoView a;

            {
                this.a = r1;
            }

            public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
                this.a.j = true;
                if (this.a.f != null) {
                    this.a.f.onError(mediaPlayer, i, i2);
                }
                return false;
            }
        });
        this.g = true;
        addView(this.b, i, layoutParams);
        this.l.d();
        this.j = false;
    }

    public void setVideoSize(FrameLayout.LayoutParams layoutParams) {
        this.b.setLayoutParams(layoutParams);
    }

    public void setVideoUrlString(String str) {
        this.c = str;
        Uri parse = Uri.parse(str);
        this.j = false;
        this.b.setVideoURI(parse);
        MSPVATrackingCheck.a();
    }

    public void setOnPreparedListener(OnPreparedListener onPreparedListener) {
        this.e = onPreparedListener;
    }

    public void setOnCompletionListener(OnCompletionListener onCompletionListener) {
        this.d = onCompletionListener;
    }

    public void setOnErrorListener(OnErrorListener onErrorListener) {
        this.f = onErrorListener;
    }

    public String getVideoUrlString() {
        return this.c;
    }

    public boolean getVideoPlaying() {
        return this.g;
    }

    public void setVideoPlaying(boolean z) {
        this.g = z;
    }

    public int getCurrentPosition() {
        return this.b.getCurrentPosition();
    }

    public void a(int i) {
        this.b.a(i);
    }

    public void a() {
        this.b.b();
        b();
        this.k = new Timer("timer", false);
        if (this.q == null) {
            this.q = new UpdateTimeDisplayTask();
        }
        this.k.schedule(this.q, 0, 1000);
        this.l.a(true);
        this.l.b();
    }

    public void b() {
        if (this.k != null) {
            this.k.cancel();
            this.k.purge();
            this.k = null;
            this.q = null;
        }
    }

    public void c() {
        this.b.a();
        this.g = false;
    }

    public void d() {
        b();
        if (this.p != null) {
            this.p.c();
            this.p = null;
        }
        if (this.b != null) {
            removeView(this.b);
            this.b.setVideoURI(null);
            this.b.setOnCompletionListener(null);
            this.b.setOnErrorListener(null);
            this.b.setOnPreparedListener(null);
        }
        this.b = null;
        this.a = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.m = null;
        this.l = null;
        this.q = null;
        this.o = null;
    }

    public void a(boolean z) {
        try {
            if (this.o != null) {
                if (z) {
                    this.o.setVolume(0.0f, 0.0f);
                } else {
                    this.o.setVolume(0.5f, 0.5f);
                }
            }
            this.p.b();
        } catch (IllegalStateException e) {
        }
    }

    public void e() {
        String a = MSPVACloseButtonPosition.a();
        if (this.n > 0) {
            a = MSPVACloseButtonPosition.b();
        }
        if (this.p != null) {
            this.p.a(a);
        }
    }
}
