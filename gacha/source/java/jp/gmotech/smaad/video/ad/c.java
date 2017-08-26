package jp.gmotech.smaad.video.ad;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import jp.gmotech.smaad.video.ad.b.a;

class c implements OnPreparedListener {
    final /* synthetic */ SmaAdVideoActivity a;

    c(SmaAdVideoActivity smaAdVideoActivity) {
        this.a = smaAdVideoActivity;
    }

    public void onPrepared(MediaPlayer mediaPlayer) {
        a.a("SmaAdVideoActivity", "[onPrepared] mPlayStatus : " + this.a.i);
        mediaPlayer.setOnSeekCompleteListener(new d(this));
        if (this.a.i == o.START) {
            this.a.a();
        }
    }
}
