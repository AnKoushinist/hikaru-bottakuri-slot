package jp.maio.sdk.android;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.ViewGroup;
import java.util.Timer;
import java.util.TimerTask;
import org.cocos2dx.lib.BuildConfig;
import org.json.JSONException;
import org.json.JSONObject;

public class AdFullscreenActivity extends aa {
    private bj d;
    private s e;
    private v f;
    private l g;
    private k h;
    private av i;
    private ar j;
    private Timer k;
    private TimerTask l;
    private int m;
    private MaioAdsListenerInterface n;
    private MaioAdsListenerInterface o;
    private int p = 0;
    private final ag q = new f(this);
    private final MaioAdsListenerInterface r = new i(this);

    private void b() {
        this.l = new d(this);
    }

    private void c() {
        if (this.k != null) {
            this.k.cancel();
            this.k = null;
        }
    }

    public void a() {
        this.k = new Timer();
        b();
        this.k.schedule(this.l, 0, (long) ((int) (this.i.b.h * 1000.0d)));
    }

    public void onBackPressed() {
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        getWindow().setFlags(1024, 1024);
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            this.p = bundle.getInt("lastPosition");
        }
        if (bk.a == null || bk.b == null) {
            finish();
            return;
        }
        this.n = bk.a;
        this.o = bk.b;
        try {
            this.d = (bj) getIntent().getSerializableExtra("zone");
            if (this.d == null) {
                finish();
                return;
            }
            t.a(this);
            this.e = this.d.c();
            if (this.e == null) {
                finish();
                return;
            }
            this.f = this.e.c();
            try {
                this.f.h = new JSONObject(this.f.i);
            } catch (JSONException e) {
            }
            try {
                this.i = (av) getIntent().getSerializableExtra("media");
                if (this.i == null) {
                    finish();
                    return;
                }
                this.j = ar.a(this.i.b.c, this.i.b.e);
                ax.a("Loading web view. media id:", BuildConfig.FLAVOR, this.i.c.toString(), null);
                this.g = new l(this);
                ((ViewGroup) findViewById(2)).addView(this.g);
                this.h = new k(this);
                be.a(findViewById(3), this.h);
                this.h.a(this.d, this.f, this.r, this);
                this.g.a(this.d, this.f, this.r, this.q, this.i, this.h, this.j);
                this.r.onOpenAd(this.d.b);
                bb.b.execute(new a(this));
            } catch (Exception e2) {
                finish();
            }
        } catch (Exception e3) {
            finish();
        }
    }

    protected void onDestroy() {
        if (this.r != null) {
            if (this.d == null) {
                this.r.onClosedAd(BuildConfig.FLAVOR);
            } else {
                this.r.onClosedAd(this.d.b);
            }
        }
        super.onDestroy();
        this.d = null;
        this.g = null;
        if (this.h != null) {
            this.h.e();
        }
        this.h = null;
        c();
        finish();
    }

    protected void onPause() {
        super.onPause();
        c();
        if (this.h != null) {
            this.p = this.h.getCurrentPosition();
            this.h.pause();
        }
    }

    protected void onResume() {
        super.onResume();
        if (!(this.h == null || this.h.a == null || !this.h.b)) {
            this.h.seekTo(this.p);
            this.h.start();
            a();
        }
        bk.b = null;
        bk.a = null;
        if (bk.a == null) {
            bk.a = this.n;
        }
        if (bk.b == null) {
            bk.b = this.o;
        }
    }
}
