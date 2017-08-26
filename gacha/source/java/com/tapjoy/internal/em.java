package com.tapjoy.internal;

import android.content.Context;
import com.tapjoy.TJPlacement;
import com.tapjoy.TJPlacementListener;
import com.tapjoy.internal.eo.a;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

public final class em extends fg implements Observer {
    private final Map b = new HashMap();
    private final eg c = new eg();
    private boolean d;
    private final er e = new er(this) {
        final /* synthetic */ em a;

        {
            this.a = r1;
        }

        protected final boolean a() {
            return super.a() && !fx.c();
        }

        protected final /* bridge */ /* synthetic */ String a(Object obj) {
            return "AppLaunch";
        }

        protected final /* synthetic */ TJPlacement a(Context context, TJPlacementListener tJPlacementListener, Object obj) {
            TJPlacement tJPlacement = new TJPlacement(context, "AppLaunch", tJPlacementListener);
            tJPlacement.initiatedBySdk = true;
            return tJPlacement;
        }
    };

    static {
        fg.a = new em();
    }

    public static void a() {
    }

    private em() {
    }

    public final void update(Observable observable, Object obj) {
        a aVar = eo.d;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected final void a(android.app.Activity r7) {
        /*
        r6 = this;
        r2 = 1;
        r1 = 0;
        if (r7 == 0) goto L_0x000e;
    L_0x0004:
        r3 = r7.getTaskId();
        r0 = -1;
        if (r3 != r0) goto L_0x0023;
    L_0x000b:
        r0 = r1;
    L_0x000c:
        if (r0 != 0) goto L_0x001a;
    L_0x000e:
        r0 = r6.d;
        if (r0 != 0) goto L_0x0020;
    L_0x0012:
        r0 = r6.c;
        r0 = r0.a();
        if (r0 == 0) goto L_0x0020;
    L_0x001a:
        r0 = r6.e;
        r1 = 0;
        r0.c(r1);
    L_0x0020:
        r6.d = r2;
        return;
    L_0x0023:
        r4 = r7.getIntent();
        r0 = r4.getCategories();
        if (r0 == 0) goto L_0x0046;
    L_0x002d:
        r5 = "android.intent.category.LAUNCHER";
        r0 = r0.contains(r5);
        if (r0 == 0) goto L_0x0046;
    L_0x0035:
        r0 = "android.intent.action.MAIN";
        r5 = r4.getAction();
        r0 = r0.equals(r5);
        if (r0 == 0) goto L_0x0046;
    L_0x0041:
        r0 = r2;
    L_0x0042:
        if (r0 != 0) goto L_0x0048;
    L_0x0044:
        r0 = r1;
        goto L_0x000c;
    L_0x0046:
        r0 = r1;
        goto L_0x0042;
    L_0x0048:
        r0 = r4.getComponent();
        r0 = r0.getClassName();
        r4 = r6.b;
        r5 = java.lang.Integer.valueOf(r3);
        r0 = r4.put(r0, r5);
        r0 = (java.lang.Integer) r0;
        if (r0 == 0) goto L_0x0066;
    L_0x005e:
        r0 = r0.intValue();
        if (r0 != r3) goto L_0x0066;
    L_0x0064:
        r0 = r1;
        goto L_0x000c;
    L_0x0066:
        r0 = r2;
        goto L_0x000c;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tapjoy.internal.em.a(android.app.Activity):void");
    }
}
