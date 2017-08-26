package com.applovin.adview;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

class m implements OnPreparedListener {
    final /* synthetic */ AppLovinInterstitialActivity a;

    m(AppLovinInterstitialActivity appLovinInterstitialActivity) {
        this.a = appLovinInterstitialActivity;
    }

    public void onPrepared(MediaPlayer mediaPlayer) {
        this.a.G = new WeakReference(mediaPlayer);
        int i = this.a.i() ? 0 : 1;
        mediaPlayer.setVolume((float) i, (float) i);
        i = mediaPlayer.getVideoWidth();
        int videoHeight = mediaPlayer.getVideoHeight();
        this.a.u = (int) TimeUnit.MILLISECONDS.toSeconds((long) mediaPlayer.getDuration());
        this.a.y.a(i, videoHeight);
        mediaPlayer.setDisplay(this.a.y.getHolder());
        mediaPlayer.setOnErrorListener(new n(this));
        this.a.t();
        this.a.n();
        this.a.x();
        this.a.v();
        this.a.l();
    }
}
