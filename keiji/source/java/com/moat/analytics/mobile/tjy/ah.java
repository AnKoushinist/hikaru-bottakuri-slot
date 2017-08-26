package com.moat.analytics.mobile.tjy;

import android.media.MediaPlayer;
import android.view.View;
import java.util.HashMap;
import java.util.Map;

class ah extends i implements NativeVideoTracker {
    public ah(String str, a aVar, ap apVar) {
        super(str, aVar, apVar);
    }

    protected Map a() {
        MediaPlayer mediaPlayer = (MediaPlayer) this.f.get();
        Map hashMap = new HashMap();
        hashMap.put("width", Integer.valueOf(mediaPlayer.getVideoWidth()));
        hashMap.put("height", Integer.valueOf(mediaPlayer.getVideoHeight()));
        hashMap.put("duration", Integer.valueOf(mediaPlayer.getDuration()));
        return hashMap;
    }

    public /* synthetic */ boolean a(Map map, Object obj, View view) {
        return trackVideoAd(map, (MediaPlayer) obj, view);
    }

    public void changeTargetView(View view) {
        super.changeTargetView(view);
    }

    protected Integer f() {
        return Integer.valueOf(((MediaPlayer) this.f.get()).getCurrentPosition());
    }

    protected boolean g() {
        return ((MediaPlayer) this.f.get()).isPlaying();
    }

    protected Integer h() {
        return Integer.valueOf(((MediaPlayer) this.f.get()).getDuration());
    }

    public boolean trackVideoAd(Map map, MediaPlayer mediaPlayer, View view) {
        if (mediaPlayer == null) {
            a("Null player instance. Not tracking.");
        }
        try {
            mediaPlayer.getCurrentPosition();
            return super.a(map, mediaPlayer, view);
        } catch (IllegalStateException e) {
            a("Playback has already completed. Not tracking.");
            return false;
        }
    }
}
