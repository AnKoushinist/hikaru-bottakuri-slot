package com.adcolony.sdk;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import com.tapjoy.TapjoyConnectCore;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import org.cocos2dx.lib.BuildConfig;
import org.json.JSONObject;

class ae extends FrameLayout {
    float a = TapjoyConnectCore.DEFAULT_CURRENCY_MULTIPLIER;
    int b = 2;
    private al c;
    private al d;
    private an e;
    private String f;
    private String g;
    private boolean h;
    private boolean i;
    private boolean j;
    private boolean k;
    private ImageView l;
    private String m = BuildConfig.FLAVOR;
    private String n = BuildConfig.FLAVOR;
    private String o = BuildConfig.FLAVOR;
    private String p = BuildConfig.FLAVOR;
    private ExecutorService q = Executors.newSingleThreadExecutor();

    ae(Context context, o oVar, an anVar) {
        super(context);
        this.e = anVar;
        this.p = anVar.a;
        this.f = bb.a(oVar.b(), "id");
        bd.b.a("Retrieving container tied to ad session id: ").b(this.f);
        this.c = (al) n.a().h().b().get(this.f);
        setLayoutParams(new LayoutParams(this.c.o(), this.c.n()));
        addView(this.c);
        d();
    }

    boolean a() {
        am h = n.a().h();
        h.a(this.c);
        if (this.d != null) {
            h.a(this.d);
        }
        ao aoVar = (ao) h.e().remove(this.f);
        if (aoVar != null) {
            for (MediaPlayer mediaPlayer : aoVar.c().c().values()) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                }
                mediaPlayer.release();
            }
            aoVar.d().a().autoPause();
            aoVar.d().a().release();
        }
        h.d().remove(this.f);
        this.c = null;
        this.e = null;
        removeAllViews();
        this.q.shutdown();
        return true;
    }

    public String getZoneID() {
        if (!this.i) {
            return this.p;
        }
        bd.e.b((Object) "Ignoring call to getZoneID() as view has been destroyed");
        return BuildConfig.FLAVOR;
    }

    boolean b() {
        if (this.g.equals(BuildConfig.FLAVOR) || !n.d()) {
            return false;
        }
        this.l = new ImageView(n.c());
        this.l.setImageBitmap(BitmapFactory.decodeFile(this.g));
        return true;
    }

    private void d() {
        try {
            this.q.submit(new Runnable(this) {
                final /* synthetic */ ae a;

                {
                    this.a = r1;
                }

                public void run() {
                    JSONObject a = bb.a();
                    bb.a(a, "id", this.a.f);
                    while (!this.a.i) {
                        boolean z;
                        Rect rect = new Rect();
                        Rect rect2 = new Rect();
                        this.a.getLocalVisibleRect(rect);
                        this.a.getGlobalVisibleRect(rect2);
                        ViewParent parent = this.a.getParent();
                        if (parent != null) {
                            parent.getChildVisibleRect(this.a, rect2, null);
                        }
                        boolean z2 = rect.bottom - rect.top > this.a.c.n() / 2;
                        boolean z3;
                        if ((rect2.bottom - rect2.top < this.a.c.n() / 2 || rect2.bottom - rect2.top >= this.a.c.n()) && this.a.k) {
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                        if (rect.bottom > this.a.c.n() || rect.bottom < 0 || rect.top < 0 || rect2.top <= 0) {
                            z = true;
                        } else {
                            z = false;
                        }
                        if (!z && !r3 && z2 && !this.a.h) {
                            this.a.k = true;
                            this.a.h = true;
                            new o(this.a.j ? "AdSession.on_native_ad_view_visible" : "AdSession.on_ad_view_visible", this.a.c.b(), a).a();
                        } else if ((!z2 || (z2 && z)) && this.a.h) {
                            this.a.h = false;
                            new o(this.a.j ? "AdSession.on_native_ad_view_hidden" : "AdSession.on_ad_view_hidden", this.a.c.b(), a).a();
                            bd.d.b((Object) "AdColonyAdView has been hidden.");
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                        }
                    }
                }
            });
        } catch (RejectedExecutionException e) {
            JSONObject a = bb.a();
            bb.a(a, "id", this.f);
            new o("AdSession.on_error", this.c.b(), a).a();
        }
    }

    void setNative(boolean z) {
        this.j = z;
    }

    void setAdvertiserName(String str) {
        this.m = str;
    }

    void setTitle(String str) {
        this.n = str;
    }

    void setDescription(String str) {
        this.o = str;
    }

    void setImageFilepath(String str) {
        this.g = str;
    }

    void setExpandedContainer(al alVar) {
        this.d = alVar;
    }

    boolean c() {
        return this.i;
    }

    String getAdSessionId() {
        return this.f;
    }

    al getContainer() {
        return this.c;
    }

    al getExpandedContainer() {
        return this.d;
    }

    String getAdvertiserName() {
        return this.m;
    }

    ImageView getIcon() {
        return this.l;
    }

    String getTitle() {
        return this.n;
    }

    String getDescription() {
        return this.o;
    }

    an getListener() {
        return this.e;
    }
}
