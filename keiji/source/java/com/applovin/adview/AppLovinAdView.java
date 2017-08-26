package com.applovin.adview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.applovin.impl.adview.AdViewControllerImpl;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdVideoPlaybackListener;
import com.applovin.sdk.AppLovinSdk;

public class AppLovinAdView extends RelativeLayout {
    private AdViewController a;

    public AppLovinAdView(AppLovinSdk appLovinSdk, AppLovinAdSize appLovinAdSize, Activity activity) {
        super(activity);
        a(appLovinAdSize, appLovinSdk, activity, null);
    }

    private void a(AttributeSet attributeSet, Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int i = displayMetrics.widthPixels;
        int applyDimension = (int) TypedValue.applyDimension(1, 50.0f, displayMetrics);
        View textView = new TextView(context);
        textView.setBackgroundColor(Color.rgb(220, 220, 220));
        textView.setTextColor(-16777216);
        textView.setText("AppLovin Ad");
        textView.setGravity(17);
        addView(textView, i, applyDimension);
    }

    private void a(AppLovinAdSize appLovinAdSize, AppLovinSdk appLovinSdk, Context context, AttributeSet attributeSet) {
        if (isInEditMode()) {
            a(attributeSet, context);
            return;
        }
        AdViewController adViewControllerImpl = new AdViewControllerImpl();
        adViewControllerImpl.a(this, context, appLovinAdSize, appLovinSdk, attributeSet);
        this.a = adViewControllerImpl;
    }

    public void a() {
        if (this.a != null) {
            this.a.a();
        } else {
            Log.i("AppLovinSdk", "Unable to load next ad: AppLovinAdView is not initialized.");
        }
    }

    public void a(AppLovinAd appLovinAd, String str) {
        if (this.a != null) {
            this.a.a(appLovinAd, str);
        }
    }

    public void b() {
        if (this.a != null) {
            this.a.b();
        }
    }

    public AdViewController getAdViewController() {
        return this.a;
    }

    public AppLovinAdSize getSize() {
        return this.a != null ? this.a.c() : null;
    }

    protected void onDetachedFromWindow() {
        if (this.a != null) {
            this.a.d();
        }
        super.onDetachedFromWindow();
    }

    protected void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        if (this.a != null) {
            this.a.a(i);
        }
    }

    public void setAdClickListener(AppLovinAdClickListener appLovinAdClickListener) {
        if (this.a != null) {
            this.a.a(appLovinAdClickListener);
        }
    }

    public void setAdDisplayListener(AppLovinAdDisplayListener appLovinAdDisplayListener) {
        if (this.a != null) {
            this.a.a(appLovinAdDisplayListener);
        }
    }

    public void setAdLoadListener(AppLovinAdLoadListener appLovinAdLoadListener) {
        if (this.a != null) {
            this.a.a(appLovinAdLoadListener);
        }
    }

    public void setAdVideoPlaybackListener(AppLovinAdVideoPlaybackListener appLovinAdVideoPlaybackListener) {
        if (this.a != null) {
            this.a.a(appLovinAdVideoPlaybackListener);
        }
    }

    public void setAutoDestroy(boolean z) {
        if (this.a != null) {
            this.a.a(z);
        }
    }
}
