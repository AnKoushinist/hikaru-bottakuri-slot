package com.tapjoy.internal;

import android.content.Context;
import com.tapjoy.TJPlacementListener;

@ep
public class TapjoyNative {
    @ep
    public static Object createPlacement(Context context, String str, TJPlacementListener tJPlacementListener) {
        return et.a().a(context, str, tJPlacementListener);
    }
}
