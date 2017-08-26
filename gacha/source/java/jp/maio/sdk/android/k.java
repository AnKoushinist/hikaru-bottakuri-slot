package jp.maio.sdk.android;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.VideoView;
import java.util.concurrent.CountDownLatch;
import org.cocos2dx.lib.GameControllerDelegate;

class k extends VideoView implements OnCompletionListener, OnErrorListener, OnPreparedListener {
    public MediaPlayer a;
    public boolean b = false;
    private bj c;
    private v d;
    private MaioAdsListenerInterface e;
    private final CountDownLatch f = new CountDownLatch(1);
    private final CountDownLatch g = new CountDownLatch(1);
    private boolean h = false;
    private Activity i;

    public k(Context context) {
        super(context);
        setLayoutParams(new LayoutParams(-1, -1));
        setKeepScreenOn(true);
    }

    private void a(int i) {
        double log;
        if (100 - i > 0) {
            try {
                log = Math.log((double) (100 - i));
            } catch (Exception e) {
                this.e.onFailed(FailNotificationReason.VIDEO, this.c.b);
                this.i.finish();
                return;
            }
        }
        log = 0.0d;
        float log2 = (float) (1.0d - (log / Math.log(100.0d)));
        this.a.setVolume(log2, log2);
    }

    public void a() {
        this.f.await();
    }

    public void a(bj bjVar, v vVar, MaioAdsListenerInterface maioAdsListenerInterface, Activity activity) {
        this.f.countDown();
        this.c = bjVar;
        this.e = maioAdsListenerInterface;
        this.i = activity;
        this.d = vVar;
        setOnPreparedListener(this);
        setOnCompletionListener(this);
        setOnErrorListener(this);
        setVideoPath(vVar.a(vVar.c).getPath());
    }

    public void b() {
        try {
            a(0);
        } catch (Exception e) {
            this.e.onFailed(FailNotificationReason.VIDEO, this.c.b);
            this.i.finish();
        }
    }

    public void c() {
        try {
            a(100);
        } catch (Exception e) {
            this.e.onFailed(FailNotificationReason.VIDEO, this.c.b);
            this.i.finish();
        }
    }

    public int d() {
        return getDuration();
    }

    public void e() {
        this.a = null;
    }

    public void onCompletion(MediaPlayer mediaPlayer) {
        this.b = false;
        if (this.h) {
            this.e.onFinishedAd(mediaPlayer.getCurrentPosition() / GameControllerDelegate.THUMBSTICK_LEFT_X, false, mediaPlayer.getDuration() / GameControllerDelegate.THUMBSTICK_LEFT_X, this.c.b);
        }
        this.h = false;
    }

    public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        ax.a("VideoView#onError", "what=" + i + ", extra=" + i2, null);
        if (isPlaying()) {
            stopPlayback();
        }
        e();
        this.e.onFailed(FailNotificationReason.VIDEO, this.c.b);
        bk.b(FailNotificationReason.VIDEO, String.valueOf(this.d.b));
        this.i.finish();
        return true;
    }

    public void onPrepared(MediaPlayer mediaPlayer) {
        this.a = mediaPlayer;
    }

    public void start() {
        super.start();
        this.h = true;
        this.b = true;
    }
}
