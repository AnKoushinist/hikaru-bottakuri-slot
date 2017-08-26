package com.tapjoy.internal;

import android.content.Context;
import android.view.ViewGroup.LayoutParams;

public final class hp extends an {
    private final gu a;
    private final hq b;
    private af c = null;

    public hp(Context context, gu guVar, hq hqVar) {
        super(context);
        this.a = guVar;
        this.b = hqVar;
        addView(hqVar, new LayoutParams(-1, -1));
    }

    protected final void onMeasure(int i, int i2) {
        af afVar;
        af a = af.a(getContext());
        if (!this.a.a()) {
            afVar = af.LANDSCAPE;
            if (!a.a()) {
                setRotationCount(0);
            } else if (a.c() == 3) {
                setRotationCount(1);
            } else {
                setRotationCount(1);
            }
        } else if (this.a.b()) {
            if (a.a()) {
                afVar = af.PORTRAIT;
            } else if (a.b() || !af.b(getContext()).a()) {
                afVar = af.LANDSCAPE;
            } else {
                afVar = af.PORTRAIT;
            }
            setRotationCount(0);
        } else {
            afVar = af.PORTRAIT;
            if (!a.b()) {
                setRotationCount(0);
            } else if (a.c() == 3) {
                setRotationCount(1);
            } else {
                setRotationCount(3);
            }
        }
        if (this.c != afVar) {
            this.c = afVar;
            this.b.setLandscape(this.c.b());
        }
        super.onMeasure(i, i2);
    }
}
