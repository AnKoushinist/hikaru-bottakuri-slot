package jp.gmotech.smaad.video.ad;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import jp.gmotech.smaad.video.ad.b.a;

class e implements OnCompletionListener {
    final /* synthetic */ SmaAdVideoActivity a;

    e(SmaAdVideoActivity smaAdVideoActivity) {
        this.a = smaAdVideoActivity;
    }

    public void onCompletion(MediaPlayer mediaPlayer) {
        String str = this.a.k + "&sdk_version=" + "1.1.2";
        a.a("SmaAdVideoActivity", "[onCompletion] url : " + str);
        this.a.c.loadUrl(str);
        this.a.i = o.ENDED;
        this.a.d();
        this.a.a(false);
        this.a.g();
    }
}
