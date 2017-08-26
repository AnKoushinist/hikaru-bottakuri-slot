package com.vungle.publisher;

import android.media.AudioManager;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class lt implements MembersInjector<lr> {
    static final /* synthetic */ boolean a = (!lt.class.desiredAssertionStatus());
    private final Provider<AudioManager> b;

    public final /* synthetic */ void injectMembers(Object obj) {
        lr lrVar = (lr) obj;
        if (lrVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        lrVar.a = (AudioManager) this.b.get();
    }

    private lt(Provider<AudioManager> provider) {
        if (a || provider != null) {
            this.b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<lr> a(Provider<AudioManager> provider) {
        return new lt(provider);
    }
}
