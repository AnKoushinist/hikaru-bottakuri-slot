package com.tapjoy.internal;

import android.content.Context;
import com.tapjoy.TJPlacement;
import com.tapjoy.TJPlacementListener;
import com.tapjoy.TJPlacementManager;
import com.tapjoy.TapjoyConnectCore;
import com.tapjoy.internal.fx.a;
import java.util.Observer;

public final class eq extends fx {
    private final er b = new er(this) {
        final /* synthetic */ eq a;

        {
            this.a = r1;
        }

        protected final /* synthetic */ TJPlacement a(Context context, TJPlacementListener tJPlacementListener, Object obj) {
            a aVar = (a) obj;
            TJPlacement tJPlacement = new TJPlacement(TapjoyConnectCore.getContext(), aVar.b, tJPlacementListener);
            tJPlacement.pushId = aVar.a;
            return tJPlacement;
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
        fx.a(new eq());
    }

    public static void a() {
    }

    private eq() {
    }

    protected final boolean b() {
        return this.b.b != null;
    }

    protected final void a(a aVar) {
        this.b.c(aVar);
    }
}
