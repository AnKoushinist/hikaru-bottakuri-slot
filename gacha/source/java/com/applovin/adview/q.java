package com.applovin.adview;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnErrorListener;

class q implements OnErrorListener {
    final /* synthetic */ AppLovinInterstitialActivity a;

    q(AppLovinInterstitialActivity appLovinInterstitialActivity) {
        this.a = appLovinInterstitialActivity;
    }

    public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        this.a.v.post(new r(this, i, i2));
        return true;
    }
}
