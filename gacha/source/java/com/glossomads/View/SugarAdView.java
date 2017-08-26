package com.glossomads.View;

import android.content.Context;
import android.os.Handler;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import com.glossomads.b.b;
import com.glossomads.e;
import com.glossomads.j;
import com.glossomads.s;

public class SugarAdView extends FrameLayout implements u {
    private final Runnable delayFunc = new e(this);
    private boolean isShow;
    t mHolder;
    private a mListener;
    l mPlayer;
    p thumbnailView;

    public interface a {
        void a();
    }

    public SugarAdView(Context context) {
        super(context);
    }

    public SugarAdView(t tVar) {
        super(s.a().d());
        this.mHolder = tVar;
        this.isShow = false;
        this.thumbnailView = new p(this.mHolder.b().b().toString());
        this.thumbnailView.setSugarThumbnailViewListener(new d(this));
        this.mPlayer = new l(this, this.mHolder);
    }

    public void prepare() {
        try {
            j.a().a(this.mHolder.b().b().toString(), this.mHolder.a());
            if (this.thumbnailView.a()) {
                makeViews();
            } else {
                this.thumbnailView.b();
            }
        } catch (b e) {
            onVideoError(e);
        }
    }

    public void makeViews() {
        if (v.a(this.mHolder.b())) {
            setLayoutParams(new LayoutParams(this.mHolder.d(), this.mHolder.e(), 17));
            setBackgroundColor(-16777216);
            this.thumbnailView.setBackgroundColor(-16777216);
            addView(this.mPlayer);
            addView(this.thumbnailView);
            onPrepared();
            return;
        }
        onVideoError(new b(b.e));
    }

    public void show() {
        if (!this.isShow && this.thumbnailView.d()) {
            this.isShow = true;
            this.thumbnailView.setVisibility(0);
            this.thumbnailView.bringToFront();
            rotate();
            if (this.mHolder.h()) {
                if (e.a().e(this.mHolder.b().l())) {
                    this.thumbnailView.setVisibility(4);
                } else {
                    com.glossomads.Logger.a.c(com.glossomads.Logger.a.a(this.mHolder.b().m()), this.mHolder.a(), this.mHolder.b().l());
                }
                this.mPlayer.d();
            } else if (e.c()) {
                com.glossomads.Logger.a.c(com.glossomads.Logger.a.a(this.mHolder.b().m()), this.mHolder.a(), this.mHolder.b().l());
                e.g(getAdId());
                this.mPlayer.d();
            } else {
                com.glossomads.Logger.a.a(this.mHolder.a(), this.mHolder.b().l(), this.mHolder.b().m());
            }
        }
    }

    private void onPrepared() {
        if (this.mListener != null) {
            this.mListener.a();
        }
    }

    public void onPause() {
        this.mPlayer.h();
    }

    public void onResume() {
        this.mPlayer.i();
    }

    public void rotate() {
        boolean z = true;
        if (this.mHolder.d() > this.mHolder.e()) {
            z = false;
        }
        setLayoutParams(new LayoutParams(this.mHolder.d(), this.mHolder.e(), 17));
        this.thumbnailView.a(z);
        this.mPlayer.a(this.mHolder.d(), this.mHolder.e());
    }

    public void rotate(int i, int i2) {
        this.mHolder.a(i);
        this.mHolder.b(i2);
        rotate();
    }

    public void onVideoStart() {
        new Handler().postDelayed(this.delayFunc, 1000);
        com.glossomads.d.a.a(this.mHolder, com.glossomads.d.a.b.AD_START);
    }

    public void onVideoPause() {
        com.glossomads.d.a.a(this.mHolder, com.glossomads.d.a.b.AD_PAUSE);
    }

    public void onVideoResume() {
        com.glossomads.d.a.a(this.mHolder, com.glossomads.d.a.b.AD_RESUME);
    }

    public void onVideoFinish() {
        com.glossomads.d.a.a(this.mHolder, com.glossomads.d.a.b.AD_FINISH);
    }

    public void onVideoClose(boolean z) {
        this.mHolder.a(z);
        e.h(getAdId());
        if (!this.mHolder.h()) {
            this.thumbnailView.bringToFront();
            this.thumbnailView.setVisibility(0);
        }
        com.glossomads.d.a.a(this.mHolder, com.glossomads.d.a.b.AD_CLOSE);
    }

    public void onVideoError(b bVar) {
        e.h(getAdId());
        if (!this.mHolder.h()) {
            this.thumbnailView.bringToFront();
            this.thumbnailView.setVisibility(0);
        }
        com.glossomads.d.a.a(this.mHolder, com.glossomads.d.a.b.AD_ERROR);
    }

    public void networkNotification(boolean z) {
        this.mPlayer.b(z);
    }

    public void audioChangeNotification(com.glossomads.d.b.b bVar) {
        this.mPlayer.a(bVar);
    }

    public String getAdId() {
        return this.mHolder.b().l();
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    public void onDestroy() {
        v.a(getAdId());
        e.b(this);
        removeView(this.thumbnailView);
        removeView(this.mPlayer);
        this.thumbnailView.c();
        this.mPlayer.j();
        this.mPlayer = null;
        this.mHolder = null;
        System.gc();
    }

    public t getHolder() {
        return this.mHolder;
    }

    public void setSugarAdViewListener(a aVar) {
        this.mListener = aVar;
    }
}
