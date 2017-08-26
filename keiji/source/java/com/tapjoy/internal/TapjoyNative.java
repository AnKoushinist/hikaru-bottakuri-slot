package com.tapjoy.internal;

import android.content.Context;
import com.tapjoy.TJPlacementListener;

@eu
public class TapjoyNative {
    @eu
    public static Object createPlacement(Context context, String str, TJPlacementListener tJPlacementListener) {
        return fb.a().a(context, str, tJPlacementListener);
    }
}
