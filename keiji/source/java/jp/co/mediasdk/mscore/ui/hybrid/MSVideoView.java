package jp.co.mediasdk.mscore.ui.hybrid;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;
import java.text.SimpleDateFormat;
import java.util.Timer;
import java.util.TimerTask;
import jp.co.mediasdk.android.ImageUtil;
import jp.co.mediasdk.android.PackageManagerUtil;
import jp.co.mediasdk.android.Util;
import jp.co.mediasdk.mscore.util.MSPngPackageRef;

public class MSVideoView extends FrameLayout {
    private VideoView a = null;
    private String b;
    private OnCompletionListener c = null;
    private OnPreparedListener d = null;
    private OnErrorListener e = null;
    private OnCloseListener f = null;
    private boolean g = false;
    private final Handler h = new Handler();
    private TextView i;
    private SimpleDateFormat j;
    private int k;
    private ImageView l;
    private boolean m;

    public interface OnCloseListener {
        void a();
    }

    private class UpdateTimeDisplayTask extends TimerTask {
        final /* synthetic */ MSVideoView a;

        private UpdateTimeDisplayTask(MSVideoView mSVideoView) {
            this.a = mSVideoView;
        }

        public void run() {
            this.a.h.post(new Runnable(this) {
                final /* synthetic */ UpdateTimeDisplayTask a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.a.i.setText(this.a.a.j.format(Integer.valueOf(this.a.a.k - this.a.a.a.getCurrentPosition())));
                    this.a.a.i.invalidate();
                }
            });
        }
    }

    public MSVideoView(Context context) {
        super(context);
        setBackgroundColor(-16777216);
        a(context, 0);
        b(context, 1);
        c(context, 2);
        d(context, 3);
    }

    private void a(Context context, int i) {
        this.a = new VideoView(context);
        LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        layoutParams.gravity = 17;
        this.a.setLayoutParams(layoutParams);
        this.a.setOnPreparedListener(new OnPreparedListener(this) {
            final /* synthetic */ MSVideoView a;

            {
                this.a = r1;
            }

            public void onPrepared(MediaPlayer mediaPlayer) {
                this.a.g = true;
                this.a.a.start();
                this.a.k = this.a.a.getDuration();
                new Timer("timer", false).schedule(new UpdateTimeDisplayTask(), 0, 1000);
                if (this.a.d != null) {
                    this.a.d.onPrepared(mediaPlayer);
                }
            }
        });
        this.a.setOnCompletionListener(new OnCompletionListener(this) {
            final /* synthetic */ MSVideoView a;

            {
                this.a = r1;
            }

            public void onCompletion(MediaPlayer mediaPlayer) {
                this.a.g = false;
                this.a.setVisibility(8);
                if (!this.a.m && this.a.c != null) {
                    this.a.c.onCompletion(mediaPlayer);
                }
            }
        });
        this.a.setOnErrorListener(new OnErrorListener(this) {
            final /* synthetic */ MSVideoView a;

            {
                this.a = r1;
            }

            public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
                this.a.m = true;
                if (this.a.e != null) {
                    this.a.e.onError(mediaPlayer, i, i2);
                }
                return false;
            }
        });
        addView(this.a, i, layoutParams);
    }

    private void b(Context context, int i) {
        LayoutParams layoutParams = new FrameLayout.LayoutParams(Util.a(context, 85), Util.a(context, 21));
        layoutParams.gravity = 80;
        layoutParams.setMargins(Util.a(context, 5), 0, 0, Util.a(context, 5));
        View imageView = new ImageView(context);
        ImageUtil.a(imageView, MSPngPackageRef.a("jp_co_mediasdk_time.png", getContext()));
        addView(imageView, i, layoutParams);
    }

    private void c(Context context, int i) {
        this.j = new SimpleDateFormat("mm:ss");
        this.i = new TextView(context);
        this.i.setText("00:00");
        this.i.setTextSize(1, 12.0f);
        this.i.setTextColor(-1);
        LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.setMargins(Util.a(context, 51), 0, 0, Util.a(context, 7));
        layoutParams.gravity = 80;
        addView(this.i, i, layoutParams);
    }

    private void d(Context context, int i) {
        LayoutParams layoutParams = new FrameLayout.LayoutParams(Util.a(context, 35), Util.a(context, 35));
        layoutParams.gravity = 53;
        layoutParams.setMargins(0, Util.a(context, 10), Util.a(context, 10), 0);
        this.l = new ImageView(context);
        ImageUtil.a(this.l, MSPngPackageRef.a("jp_co_mediasdk_close.png", context));
        this.l.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ MSVideoView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.a.stopPlayback();
                this.a.g = false;
                this.a.removeView(this.a.a);
                this.a.setVisibility(8);
                this.a.f.a();
            }
        });
        addView(this.l, i, layoutParams);
    }

    public void setVideoUrlString(String str) {
        this.b = str;
        String str2 = "https://";
        if (PackageManagerUtil.c() < 12) {
            str2 = "http://";
        }
        Uri parse = Uri.parse(str2 + str);
        this.m = false;
        this.a.setVideoURI(parse);
    }

    public void setOnPreparedListener(OnPreparedListener onPreparedListener) {
        this.d = onPreparedListener;
    }

    public void setOnCompletionListener(OnCompletionListener onCompletionListener) {
        this.c = onCompletionListener;
    }

    public void setOnCloseListener(OnCloseListener onCloseListener) {
        this.f = onCloseListener;
    }

    public void setOnErrorListener(OnErrorListener onErrorListener) {
        this.e = onErrorListener;
    }

    public String getVideoUrlString() {
        return this.b;
    }

    public boolean getVideoPlaying() {
        return this.g;
    }

    public int getCurrentPosition() {
        return this.a.getCurrentPosition();
    }
}
