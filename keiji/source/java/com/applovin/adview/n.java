package com.applovin.adview;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnErrorListener;

class n implements OnErrorListener {
    final /* synthetic */ m a;

    n(m mVar) {
        this.a = mVar;
    }

    public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        this.a.a.w.post(new o(this, i, i2));
        return true;
    }
}
