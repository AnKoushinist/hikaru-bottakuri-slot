package jp.gmotech.smaad.video.ad;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnErrorListener;
import jp.gmotech.smaad.video.ad.b.a;

class f implements OnErrorListener {
    final /* synthetic */ SmaAdVideoActivity a;

    f(SmaAdVideoActivity smaAdVideoActivity) {
        this.a = smaAdVideoActivity;
    }

    public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        a.a("SmaAdVideoActivity", "[onError] what: " + i + ", extra: " + i2);
        if (this.a.h != null) {
            this.a.h.onSmaAdVideoError(4);
        }
        this.a.finish();
        return true;
    }
}
