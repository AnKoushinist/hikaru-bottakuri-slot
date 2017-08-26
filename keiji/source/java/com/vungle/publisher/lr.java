package com.vungle.publisher;

import android.media.AudioManager;
import android.media.AudioManager.OnAudioFocusChangeListener;
import com.vungle.log.Logger;
import javax.inject.Inject;
import javax.inject.Singleton;
import jp.co.geniee.gnsrewardadapter.BuildConfig;
import twitter4j.TwitterResponse;

@Singleton
/* compiled from: vungle */
public final class lr implements OnAudioFocusChangeListener {
    @Inject
    public AudioManager a;
    private boolean b = false;

    @Inject
    lr() {
    }

    public final int a() {
        return this.a.getStreamMaxVolume(3);
    }

    public final int b() {
        return this.a.getStreamVolume(3);
    }

    public final float c() {
        return a((float) b());
    }

    public final float a(float f) {
        int a = a();
        return a == 0 ? -1.0f : f / ((float) a);
    }

    public final void a(boolean z) {
        this.a.setStreamMute(3, !z);
    }

    public final void onAudioFocusChange(int i) {
        switch (i) {
            case -3:
            case -2:
            case BuildConfig.VERSION_CODE /*-1*/:
                this.b = false;
                break;
            case TwitterResponse.READ /*1*/:
                this.b = true;
                break;
        }
        Logger.v(Logger.DEVICE_TAG, "audio focus changed to " + this.b + ", with focusChange code " + i);
    }
}
