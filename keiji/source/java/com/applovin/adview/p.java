package com.applovin.adview;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;

class p implements OnCompletionListener {
    final /* synthetic */ AppLovinInterstitialActivity a;

    p(AppLovinInterstitialActivity appLovinInterstitialActivity) {
        this.a = appLovinInterstitialActivity;
    }

    public void onCompletion(MediaPlayer mediaPlayer) {
        this.a.n = true;
        this.a.C();
    }
}
