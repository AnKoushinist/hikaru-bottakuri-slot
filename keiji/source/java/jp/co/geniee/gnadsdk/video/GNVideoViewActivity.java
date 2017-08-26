package jp.co.geniee.gnadsdk.video;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;
import com.tapjoy.TapjoyConnectCore;
import java.util.List;
import jp.co.geniee.gnadsdk.a.a;
import org.cocos2dx.lib.BuildConfig;
import org.cocos2dx.lib.GameControllerDelegate;

public class GNVideoViewActivity extends Activity {
    private boolean A = false;
    private boolean B = false;
    private boolean C = false;
    private boolean D = false;
    private TextView E;
    private boolean F = true;
    String a = null;
    Handler b = new Handler();
    RelativeLayout c;
    LinearLayout d;
    LinearLayout e;
    TextView f;
    FrameLayout g;
    ProgressBar h;
    private a i;
    private final a j = new a("GNAdSDK", Integer.MAX_VALUE);
    private VideoView k = null;
    private Thread l;
    private int m = -1;
    private double n;
    private double o;
    private ProgressBar p;
    private MediaPlayer q;
    private c r;
    private boolean s = false;
    private Button t;
    private int u = 0;
    private int v = 0;
    private boolean w = false;
    private boolean x = false;
    private boolean y = false;
    private boolean z = false;

    public void a() {
        float f = 0.0f;
        this.p.setVisibility(8);
        MediaPlayer mediaPlayer = this.q;
        float f2 = this.s ? 0.0f : TapjoyConnectCore.DEFAULT_CURRENCY_MULTIPLIER;
        if (!this.s) {
            f = TapjoyConnectCore.DEFAULT_CURRENCY_MULTIPLIER;
        }
        mediaPlayer.setVolume(f2, f);
        this.m = 1;
        this.D = true;
        this.f.setText(a(this.v / GameControllerDelegate.THUMBSTICK_LEFT_X));
        b();
        this.j.a("GNVideoViewActivity", "Video Start");
    }

    private void d() {
        this.k.setOnTouchListener(new OnTouchListener(this) {
            final /* synthetic */ GNVideoViewActivity a;

            {
                this.a = r1;
            }

            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (!this.a.D) {
                    return true;
                }
                this.a.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(this.a.r.c())));
                List d = this.a.r.d();
                this.a.j.a("GNVideoViewActivity", "Triggering " + d.size() + " click tracking requests");
                if (d != null && d.size() > 0) {
                    String[] strArr = new String[d.size()];
                    b bVar = new b();
                    bVar.a(this.a.j.a());
                    bVar.execute(d.toArray(strArr));
                }
                return false;
            }
        });
        this.k.setOnCompletionListener(new OnCompletionListener(this) {
            final /* synthetic */ GNVideoViewActivity a;

            {
                this.a = r1;
            }

            public void onCompletion(MediaPlayer mediaPlayer) {
                if (this.a.n <= 0.75d) {
                    this.a.j.a("GNVideoViewActivity", "onCompletion but no 100% played, skipping event_complete.");
                } else if (!this.a.B) {
                    this.a.B = true;
                    List a = this.a.r.a(6);
                    this.a.j.a("GNVideoViewActivity", "Triggering " + a.size() + " event_complete tracking requests");
                    if (a != null && a.size() > 0) {
                        String[] strArr = new String[a.size()];
                        b bVar = new b();
                        bVar.a(this.a.j.a());
                        bVar.execute(a.toArray(strArr));
                    }
                }
                try {
                    ((AudioManager) this.a.getSystemService("audio")).abandonAudioFocus(null);
                } catch (Exception e) {
                    this.a.j.a("GNVideoViewActivity", "onCompletion Error");
                }
                this.a.m = 3;
                this.a.u = this.a.v;
                try {
                    this.a.l.join(100);
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                }
                if (this.a.F) {
                    this.a.finish();
                    this.a.c();
                    return;
                }
                this.a.a(this.a.a(this.a.u / GameControllerDelegate.THUMBSTICK_LEFT_X));
            }
        });
        this.k.setOnPreparedListener(new OnPreparedListener(this) {
            final /* synthetic */ GNVideoViewActivity a;

            {
                this.a = r1;
            }

            public void onPrepared(MediaPlayer mediaPlayer) {
                List a;
                this.a.q = mediaPlayer;
                this.a.o = (double) this.a.v = this.a.q.getDuration();
                this.a.h.setMax(this.a.v);
                this.a.a();
                if (!this.a.w) {
                    a = this.a.r.a();
                    this.a.j.a("GNVideoViewActivity", "Triggering " + a.size() + " impression tracking requests");
                    if (a != null && a.size() > 0) {
                        String[] strArr = new String[a.size()];
                        b bVar = new b();
                        bVar.a(this.a.j.a());
                        bVar.execute(a.toArray(strArr));
                    }
                    this.a.w = true;
                }
                if (!this.a.x) {
                    a = this.a.r.a(2);
                    this.a.j.a("GNVideoViewActivity", "Triggering " + a.size() + " start tracking requests");
                    if (a != null && a.size() > 0 && a != null && a.size() > 0) {
                        strArr = new String[a.size()];
                        bVar = new b();
                        bVar.a(this.a.j.a());
                        bVar.execute(a.toArray(strArr));
                    }
                    this.a.x = true;
                }
                this.a.j.a("GNVideoViewActivity", "Ready State OK");
            }
        });
        this.k.setOnErrorListener(new OnErrorListener(this) {
            final /* synthetic */ GNVideoViewActivity a;

            {
                this.a = r1;
            }

            public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
                if (i == 100) {
                    this.a.k.stopPlayback();
                }
                return true;
            }
        });
        this.k.setVideoURI(Uri.parse(this.r.b()));
        this.k.start();
    }

    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        if (intent != null) {
            this.j.a(intent.getIntExtra("log_priority", Integer.MAX_VALUE));
            this.F = intent.getBooleanExtra("autoCloseMode", true);
        }
        this.j.a("GNVideoViewActivity", "onCreate call");
        if (this.i == null) {
            this.i = a.d();
        }
        if (this.i != null) {
            this.r = this.i.f();
            requestWindowFeature(1);
            if (this.m == -1) {
                a(bundle);
                return;
            }
            return;
        }
        finish();
        c();
    }

    private void a(Bundle bundle) {
        this.j.a("GNVideoViewActivity", "onCreateView call");
        int i = (int) (0.5f + (10.0f * getResources().getDisplayMetrics().density));
        LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 17;
        LayoutParams layoutParams2 = new FrameLayout.LayoutParams(-1, -2);
        layoutParams2.gravity = 17;
        LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-2, -2);
        layoutParams3.gravity = 17;
        layoutParams3.setMargins(i, 0, i, 0);
        this.g = new FrameLayout(this);
        this.g.setBackgroundColor(Color.argb(128, 0, 0, 0));
        setContentView(this.g);
        this.k = new VideoView(this);
        this.g.addView(this.k, layoutParams2);
        this.k.setVisibility(0);
        this.p = new ProgressBar(this);
        this.g.addView(this.p, layoutParams);
        this.p.setVisibility(0);
        this.c = new RelativeLayout(this);
        this.c.setBackgroundColor(Color.argb(128, 0, 0, 0));
        this.g.addView(this.c, new FrameLayout.LayoutParams(-1, -2));
        this.e = new LinearLayout(this);
        this.e.setId(1);
        this.e.setBackgroundColor(0);
        LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams4.addRule(11);
        layoutParams4.addRule(15);
        this.c.addView(this.e, layoutParams4);
        this.e.setVisibility(0);
        this.f = new TextView(this);
        this.f.setTextColor(-1);
        this.f.setText("0:00");
        this.e.addView(this.f, layoutParams3);
        this.f.setVisibility(0);
        this.d = new LinearLayout(this);
        this.d.setBackgroundColor(0);
        layoutParams4 = new RelativeLayout.LayoutParams(-1, -2);
        this.d.setId(2);
        layoutParams4.addRule(9);
        layoutParams4.addRule(0, 1);
        this.c.addView(this.d, layoutParams4);
        this.d.setVisibility(0);
        this.t = new Button(this);
        this.t.setBackgroundColor(0);
        this.t.setText("Skip");
        this.t.setTextColor(-1);
        this.t.setTextSize(18.0f);
        this.d.addView(this.t, layoutParams3);
        this.t.setVisibility(0);
        this.E = new TextView(this);
        this.E.setTextColor(-1);
        this.E.setText("0:00");
        this.d.addView(this.E, layoutParams3);
        this.E.setVisibility(0);
        this.h = new ProgressBar(this, null, 16842872);
        this.h.setIndeterminate(false);
        this.h.setMinimumHeight(1);
        LayoutParams layoutParams5 = new LinearLayout.LayoutParams(-1, 5);
        layoutParams5.gravity = 17;
        this.d.addView(this.h, layoutParams5);
        this.h.setVisibility(0);
        d();
        this.c.setVisibility(0);
        a(a(this.u / GameControllerDelegate.THUMBSTICK_LEFT_X));
        this.f.setText(a(this.v / GameControllerDelegate.THUMBSTICK_LEFT_X));
        this.t.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ GNVideoViewActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                try {
                    ((AudioManager) this.a.getSystemService("audio")).abandonAudioFocus(null);
                } catch (Exception e) {
                    this.a.j.a("GNVideoViewActivity", "onClick CloseButton Error");
                }
                this.a.k.pause();
                this.a.m = 2;
                if (this.a.l != null && this.a.l.isAlive()) {
                    try {
                        this.a.l.join(100);
                    } catch (InterruptedException e2) {
                        this.a.j.a("GNVideoViewActivity", "onClick CloseButton InterruptedException Error");
                    }
                }
                this.a.finish();
                this.a.c();
            }
        });
    }

    void b() {
        this.j.a("GNVideoViewActivity", "createTrackThread call");
        if (this.m != 1) {
            return;
        }
        if (this.l == null || !this.l.isAlive()) {
            this.j.a("GNVideoViewActivity", "create video thread");
            this.l = new Thread(new Runnable(this) {
                final /* synthetic */ GNVideoViewActivity a;

                {
                    this.a = r1;
                }

                private void a() {
                    List a;
                    if (this.a.n >= 25.0d && !this.a.y) {
                        a = this.a.r.a(3);
                        this.a.y = true;
                        this.a.j.a("GNVideoViewActivity", "Triggering " + a.size() + " event_firstq tracking requests");
                        if (a != null && a.size() > 0 && a != null && a.size() > 0) {
                            String[] strArr = new String[a.size()];
                            b bVar = new b();
                            bVar.a(this.a.j.a());
                            bVar.execute(a.toArray(strArr));
                        }
                    }
                    if (this.a.n >= 50.0d && !this.a.z) {
                        a = this.a.r.a(4);
                        this.a.z = true;
                        this.a.j.a("GNVideoViewActivity", "Triggering " + a.size() + " event_mid tracking requests");
                        if (a != null && a.size() > 0 && a != null && a.size() > 0) {
                            strArr = new String[a.size()];
                            bVar = new b();
                            bVar.a(this.a.j.a());
                            bVar.execute(a.toArray(strArr));
                        }
                    }
                    if (this.a.n >= 75.0d && !this.a.A) {
                        a = this.a.r.a(5);
                        this.a.A = true;
                        this.a.j.a("GNVideoViewActivity", "Triggering " + a.size() + " event_thirdq tracking requests");
                        if (a != null && a.size() > 0 && a != null && a.size() > 0) {
                            strArr = new String[a.size()];
                            bVar = new b();
                            bVar.a(this.a.j.a());
                            bVar.execute(a.toArray(strArr));
                        }
                    }
                }

                public void run() {
                    while (this.a.m == 1) {
                        if (this.a.k.isPlaying()) {
                            this.a.u = this.a.k.getCurrentPosition();
                            this.a.h.setProgress(this.a.u);
                            this.a.a(this.a.a(this.a.u / GameControllerDelegate.THUMBSTICK_LEFT_X));
                            this.a.n = (((double) this.a.u) / this.a.o) * 100.0d;
                            a();
                            this.a.j.a("GNVideoViewActivity", Double.toString(this.a.n));
                        }
                        try {
                            Thread.sleep(250);
                        } catch (InterruptedException e) {
                            this.a.j.a("GNVideoViewActivity", "thread error");
                        }
                    }
                    this.a.j.a("GNVideoViewActivity", "video thread exit");
                }
            }, "GenieeThread");
            this.l.start();
            this.j.a("GNVideoViewActivity", "video thread  Start");
        }
    }

    protected void onPause() {
        super.onPause();
        this.j.a("GNVideoViewActivity", "onPause call");
        if (this.i != null) {
            this.i.a(false);
        }
        if (this.k != null && this.k.isPlaying()) {
            try {
                this.u = this.k.getCurrentPosition();
                this.j.a("GNVideoViewActivity", "onPause call");
            } catch (IllegalStateException e) {
                this.j.a("GNVideoViewActivity", "onPause IllegalStateException Error");
            }
        }
        if (this.m != 3) {
            this.m = 2;
        }
        this.k.pause();
        if (this.l != null && this.l.isAlive()) {
            try {
                this.l.join(100);
            } catch (InterruptedException e2) {
                this.j.a("GNVideoViewActivity", "onStop InterruptedException Error");
            }
        }
        this.q = null;
        this.j.a("GNVideoViewActivity", "Suspending video activity.");
    }

    protected void onStop() {
        super.onStop();
        this.j.a("GNVideoViewActivity", "onStop call");
    }

    protected void onDestroy() {
        this.j.a("GNVideoViewActivity", "onDestroy call");
        super.onDestroy();
    }

    protected void onStart() {
        super.onStart();
        this.j.a("GNVideoViewActivity", "onStart call");
    }

    protected void onResume() {
        super.onResume();
        this.j.a("GNVideoViewActivity", "onResume call");
        if (this.i != null) {
            this.i.a(true);
        }
        if (this.m == 3) {
            this.u = this.u + -1000 > 0 ? this.u - 1000 : 0;
        }
        if ((this.m == 2 || this.m == 3) && this.k != null) {
            this.p.setVisibility(0);
            this.k.seekTo(this.u);
            this.k.start();
            this.j.a("GNVideoViewActivity", "videoView resumed");
        }
    }

    protected void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
    }

    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
    }

    public void onBackPressed() {
        this.k.pause();
        this.m = 2;
        if (this.l != null && this.l.isAlive()) {
            try {
                this.l.join(100);
            } catch (InterruptedException e) {
                this.j.a("GNVideoViewActivity", "onBackPressed InterruptedException Error");
            }
        }
        super.onBackPressed();
        c();
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.j.a("GNVideoViewActivity", "onConfigurationChanged");
        if (this.q != null && this.q.isPlaying()) {
            try {
                this.q.pause();
            } catch (IllegalStateException e) {
            }
            if (!this.C && configuration.orientation == 2) {
                List a = this.r.a(11);
                this.j.a("GNVideoViewActivity", "Triggering " + a.size() + " event_fullscreen tracking requests");
                if (a != null && a.size() > 0) {
                    String[] strArr = new String[a.size()];
                    b bVar = new b();
                    bVar.a(this.j.a());
                    bVar.execute(a.toArray(strArr));
                }
                this.C = true;
            }
            try {
                this.q.start();
                this.j.a("GNVideoViewActivity", "onConfigurationChanged: start");
            } catch (IllegalStateException e2) {
                Log.w("GNVideoViewActivity", "Problem resuming media player.");
            }
        }
    }

    public void c() {
        if (this.i != null && this.i.e() != null) {
            this.i.e().c();
            this.i.a(false);
        }
    }

    private void a(final String str) {
        if (this.b != null) {
            this.b.post(new Runnable(this) {
                final /* synthetic */ GNVideoViewActivity b;

                public void run() {
                    this.b.E.setText(str);
                }
            });
        }
    }

    private String a(int i) {
        String str = BuildConfig.FLAVOR;
        int i2 = i - ((i / 60) * 60);
        return String.format("%d:%02d", new Object[]{Integer.valueOf(i / 60), Integer.valueOf(i2)});
    }
}
