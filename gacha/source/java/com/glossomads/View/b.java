package com.glossomads.View;

import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.Point;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import com.glossomads.e;
import com.glossomads.m;
import twitter4j.TwitterResponse;

public abstract class b extends Activity {
    private FrameLayout contentView;
    private SugarAdView mAdView;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        makeViews((t) getIntent().getSerializableExtra("sugarHolder"));
    }

    public void makeViews(t tVar) {
        this.contentView = new FrameLayout(this);
        this.contentView.setLayoutParams(new LayoutParams(v.a, v.a));
        this.contentView.setBackgroundColor(-16777216);
        setContentView(this.contentView);
        if (m.b((Activity) this)) {
            tVar.c(80);
        } else {
            tVar.c(17);
        }
        Point a = m.a((Activity) this);
        int i = a.x;
        int i2 = a.y;
        tVar.a(i);
        tVar.b(i2);
        this.mAdView = new SugarAdView(tVar);
        e.a(this.mAdView);
        e.g(this.mAdView.getAdId());
        this.contentView.addView(this.mAdView);
        this.mAdView.setSugarAdViewListener(new c(this));
        this.mAdView.prepare();
    }

    public void onStart() {
        super.onStart();
    }

    public void onStop() {
        super.onStop();
    }

    public void onConfigurationChanged(Configuration configuration) {
        Point a = m.a((Activity) this);
        int i = a.x;
        int i2 = a.y;
        switch (configuration.orientation) {
            case TwitterResponse.READ /*1*/:
                rotate(i, i2);
                break;
            case TwitterResponse.READ_WRITE /*2*/:
                rotate(i, i2);
                break;
        }
        super.onConfigurationChanged(configuration);
    }

    public void rotate(int i, int i2) {
        if (this.mAdView != null) {
            this.mAdView.rotate(i, i2);
        }
    }

    public void onDestroy() {
        if (this.mAdView != null) {
            e.a(this.mAdView.getHolder());
            this.mAdView.onDestroy();
            this.contentView.removeView(this.mAdView);
            this.mAdView = null;
        }
        super.onDestroy();
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return i == 4 ? false : false;
    }
}
