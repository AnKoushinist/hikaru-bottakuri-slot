package com.tapjoy.internal;

import android.content.Context;
import com.tapjoy.TJPlacement;
import com.tapjoy.TJPlacementListener;

public final class en {
    private static final er a = new er() {
        protected final /* bridge */ /* synthetic */ String a(Object obj) {
            return "InsufficientCurrency";
        }

        protected final /* synthetic */ TJPlacement a(Context context, TJPlacementListener tJPlacementListener, Object obj) {
            TJPlacement tJPlacement = new TJPlacement(context, "InsufficientCurrency", tJPlacementListener);
            tJPlacement.initiatedBySdk = true;
            return tJPlacement;
        }
    };

    public static void a() {
        a.c(null);
    }
}
