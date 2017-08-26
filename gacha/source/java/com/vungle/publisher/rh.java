package com.vungle.publisher;

import android.content.Context;
import android.media.AudioManager;
import com.vungle.log.Logger;
import dagger.a.c;
import dagger.a.e;
import javax.inject.Provider;

/* compiled from: vungle */
public final class rh implements c<AudioManager> {
    static final /* synthetic */ boolean a = (!rh.class.desiredAssertionStatus());
    private final re b;
    private final Provider<Context> c;

    public final /* synthetic */ Object get() {
        AudioManager audioManager = (AudioManager) ((Context) this.c.get()).getSystemService("audio");
        if (audioManager == null) {
            Logger.d(Logger.DEVICE_TAG, "AudioManager not avaialble");
        }
        return (AudioManager) e.a(audioManager, "Cannot return null from a non-@Nullable @Provides method");
    }

    private rh(re reVar, Provider<Context> provider) {
        if (a || reVar != null) {
            this.b = reVar;
            if (a || provider != null) {
                this.c = provider;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static c<AudioManager> a(re reVar, Provider<Context> provider) {
        return new rh(reVar, provider);
    }
}
