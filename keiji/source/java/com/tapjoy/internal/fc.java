package com.tapjoy.internal;

import android.content.Context;
import com.tapjoy.TJPlacement;
import com.tapjoy.TJPlacementListener;

final class fc extends fb {
    fc() {
    }

    public final Object a(Context context, String str, TJPlacementListener tJPlacementListener) {
        return new TJPlacement(context, str, tJPlacementListener);
    }
}
