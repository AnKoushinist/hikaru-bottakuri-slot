package com.tapjoy.internal;

import android.view.animation.Animation;
import android.view.animation.AnimationSet;

public final class aj extends ai {
    private final AnimationSet b = ((AnimationSet) this.a);

    public aj() {
        super(new AnimationSet(true));
    }

    public final aj a(Animation animation) {
        this.b.addAnimation(animation);
        return this;
    }

    public final /* bridge */ /* synthetic */ Animation a() {
        return this.b;
    }
}
