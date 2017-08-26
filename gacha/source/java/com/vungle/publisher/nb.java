package com.vungle.publisher;

import android.content.Context;
import android.util.TypedValue;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public final class nb {
    @Inject
    public Context a;

    @Inject
    nb() {
    }

    public final float a(int i) {
        return TypedValue.applyDimension(1, (float) i, this.a.getResources().getDisplayMetrics());
    }
}
