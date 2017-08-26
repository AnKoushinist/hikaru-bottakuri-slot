package com.tapjoy.internal;

import android.content.Context;
import com.tapjoy.TJPlacement;
import com.tapjoy.TJPlacementListener;
import com.tapjoy.TJPlacementManager;
import com.tapjoy.TapjoyConnectCore;
import com.tapjoy.internal.gk.a;
import java.util.Observer;

public final class ew extends gk {
    private final ey b = new ey(this) {
        final /* synthetic */ ew a;

        {
            this.a = r1;
        }

        protected final /* synthetic */ TJPlacement a(Context context, TJPlacementListener tJPlacementListener, Object obj) {
            a aVar = (a) obj;
            TJPlacement createPlacement = TJPlacementManager.createPlacement(TapjoyConnectCore.getContext(), aVar.b, false, tJPlacementListener);
            createPlacement.pushId = aVar.a;
            return createPlacement;
        }

        protected final /* synthetic */ a b(Object obj) {
            a aVar = (a) obj;
            return new a(this, aVar, aVar.d);
        }

        protected final boolean a() {
            return true;
        }

        protected final boolean a(Observer observer) {
            if (TapjoyConnectCore.isViewOpen()) {
                TJPlacementManager.dismissContentShowing(true);
            }
            return super.a(observer);
        }
    };

    static {
        gk.a(new ew());
    }

    public static void a() {
    }

    private ew() {
    }

    protected final boolean b() {
        return this.b.b != null;
    }

    protected final void a(a aVar) {
        this.b.c(aVar);
    }
}
