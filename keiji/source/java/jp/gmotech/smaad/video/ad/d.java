package jp.gmotech.smaad.video.ad;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnSeekCompleteListener;

class d implements OnSeekCompleteListener {
    final /* synthetic */ c a;

    d(c cVar) {
        this.a = cVar;
    }

    public void onSeekComplete(MediaPlayer mediaPlayer) {
        this.a.a.f.start();
        if (this.a.a.m < 1) {
            this.a.a.c();
        }
    }
}
