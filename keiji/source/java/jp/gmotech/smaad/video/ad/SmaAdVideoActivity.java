package jp.gmotech.smaad.video.ad;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.VideoView;
import jp.gmotech.smaad.video.ad.a.d;
import jp.gmotech.smaad.video.ad.b.a;
import jp.gmotech.smaad.video.ad.b.b;

public class SmaAdVideoActivity extends Activity implements OnClickListener {
    private RelativeLayout a = null;
    private RelativeLayout b = null;
    private WebView c = null;
    private ImageView d = null;
    private ImageView e = null;
    private VideoView f = null;
    private p g = null;
    private b h = null;
    private o i = o.START;
    private Uri j = null;
    private String k = null;
    private String l = null;
    private int m = 0;
    private boolean n = false;
    private boolean o = true;
    private boolean p = false;
    private boolean q = true;
    private boolean r = false;
    private boolean s = true;
    private boolean t = false;
    private boolean u = false;
    private boolean v = false;
    private AudioManager w = null;
    private String x = null;

    private void a() {
        a.a("SmaAdVideoActivity", "[playVideo]");
        if (this.w != null) {
            a.a("SmaAdVideoActivity", "[playVideo] audioFocusResult : " + this.w.requestAudioFocus(null, 3, 1));
        }
        this.i = o.START;
        this.h = this.g.h();
        if (this.h != null) {
            f();
        }
        this.f.seekTo(this.m);
    }

    private void a(String str) {
        a.a("SmaAdVideoActivity", "[requestClickThroughUrl] mIsSendingClickThroughUrl : " + this.t);
        if (!this.t) {
            this.t = true;
            if (!this.s) {
                a.a("SmaAdVideoActivity", "[requestClickThroughUrl] already request ClickThroughUrl");
            } else if (str == null) {
                this.t = false;
            } else {
                d.a().a(str, new l(this));
            }
        }
    }

    private void a(boolean z) {
        if (z) {
            this.b.setVisibility(0);
            this.a.setVisibility(8);
            this.e.setVisibility(8);
            if (this.n) {
                this.d.setVisibility(8);
                return;
            } else {
                this.d.setVisibility(0);
                return;
            }
        }
        this.b.setVisibility(8);
        this.a.setVisibility(0);
        this.e.setVisibility(0);
        this.d.setVisibility(0);
    }

    private String b() {
        if (this.g == null) {
            return null;
        }
        String f = this.g.f();
        return f != null ? f : null;
    }

    private void c() {
        a.a("SmaAdVideoActivity", "[requestStartUrl] mIsSendingStartUrl : " + this.p);
        if (!this.p) {
            this.p = true;
            if (!this.o) {
                a.a("SmaAdVideoActivity", "[requestStartUrl] already request startUrl");
            } else if (this.g == null || this.g.c() == null) {
                this.p = false;
            } else {
                d.a().a(this.g.c(), new m(this));
            }
        }
    }

    private void d() {
        a.a("SmaAdVideoActivity", "[requestCompleteUrl] mIsSendingCompleteUrl : " + this.r);
        if (!this.r) {
            this.r = true;
            if (!this.q) {
                a.a("SmaAdVideoActivity", "[requestCompleteUrl] already request complete");
            } else if (this.g == null || this.g.d() == null) {
                this.r = false;
            } else {
                d.a().a(this.g.d(), new n(this));
            }
        }
    }

    private void e() {
        a.a("SmaAdVideoActivity", "[closeActivity]");
        if (this.b.getVisibility() == 0) {
            this.v = false;
        } else {
            this.v = true;
        }
        if (this.w != null) {
            a.a("SmaAdVideoActivity", "[closeActivity] audioFocusResult : " + this.w.abandonAudioFocus(null));
        }
    }

    private void f() {
        if (!this.u && this.h != null) {
            this.h.b();
        }
    }

    private void g() {
        if (!this.u && this.h != null) {
            this.h.a(this.g.g());
        }
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return keyEvent.getKeyCode() == 4 ? true : super.dispatchKeyEvent(keyEvent);
    }

    public void finish() {
        a.a(this.x).a(this.v);
        super.finish();
    }

    public void onBackPressed() {
        e();
        finish();
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == this.d.getId()) {
            a.a("SmaAdVideoActivity", "[onClick] Close!!!");
            e();
            finish();
        } else if (id == this.e.getId()) {
            a.a("SmaAdVideoActivity", "[onClick] Replay!!!");
            this.u = true;
            this.m = 0;
            a();
            a(true);
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        Intent intent = getIntent();
        this.l = intent.getStringExtra("id");
        this.x = intent.getStringExtra("zoneId");
        this.n = intent.getBooleanExtra("disableStopBtn", false);
        if (a.a(this.x).d() == v.PORT) {
            setRequestedOrientation(7);
        } else {
            setRequestedOrientation(6);
        }
        this.g = a.a(this.x).a(this.l);
        this.j = Uri.parse(this.g.b());
        this.k = this.g.a();
        this.w = (AudioManager) getSystemService("audio");
        setContentView(b.a(this, "smaad_video", "layout"));
        this.f = (VideoView) findViewById(b.a(this, "smaad_video_view", "id"));
        this.f.setVideoURI(this.j);
        this.f.requestFocus();
        this.f.setOnPreparedListener(new c(this));
        this.f.setOnCompletionListener(new e(this));
        this.f.setOnErrorListener(new f(this));
        this.b = (RelativeLayout) findViewById(b.a(this, "smaad_video_view_layout", "id"));
        this.a = (RelativeLayout) findViewById(b.a(this, "smaad_video_endcard_layout", "id"));
        this.c = (WebView) findViewById(b.a(this, "smaad_video_endcard", "id"));
        this.d = (ImageView) findViewById(b.a(this, "smaad_video_close", "id"));
        this.d.setOnClickListener(this);
        if (this.n) {
            this.d.setVisibility(8);
        } else {
            this.d.setVisibility(0);
        }
        this.e = (ImageView) findViewById(b.a(this, "smaad_video_replay", "id"));
        this.e.setOnClickListener(this);
        this.e.setVisibility(8);
        this.c.setWebViewClient(new g(this));
        this.c.setWebChromeClient(new j(this));
        this.c.setHorizontalScrollBarEnabled(false);
        this.c.setVerticalScrollBarEnabled(false);
    }

    protected void onPause() {
        a.a("SmaAdVideoActivity", "[onPause]");
        super.onPause();
        a.a(this.x).g();
        if (this.i == o.START) {
            this.m = this.f.getCurrentPosition();
            this.f.pause();
        }
    }

    protected void onResume() {
        a.a("SmaAdVideoActivity", "[onResume]");
        super.onResume();
        a.a(this.x).f();
        if (this.f != null && !this.f.isPlaying() && this.m > 0 && this.i != o.ENDED) {
            a();
        }
    }
}
