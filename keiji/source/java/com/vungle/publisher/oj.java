package com.vungle.publisher;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.ShapeDrawable;
import android.view.View.MeasureSpec;
import javax.inject.Inject;
import javax.inject.Singleton;

/* compiled from: vungle */
public final class oj extends oa {
    private ShapeDrawable a;
    private int b;
    private int c;
    private int d;
    private int e;

    @Singleton
    /* compiled from: vungle */
    public static class a {
        @Inject
        Context a;
        @Inject
        nb b;

        @Inject
        a() {
        }
    }

    private oj(Context context) {
        super(context);
        this.a = new ShapeDrawable();
        this.b = -1;
        this.a.getPaint().setColor(-13659954);
    }

    protected final void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.a.draw(canvas);
    }

    protected final void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.b = MeasureSpec.getSize(i);
    }

    private void setProgressBarWidth(float f) {
        this.a.setBounds(0, 0, (int) (((float) this.b) * f), this.d);
    }

    public final void setMaxTimeMillis(int i) {
        this.c = i;
    }

    public final void setCurrentTimeMillis(int i) {
        setProgressBarWidth(((float) i) / ((float) this.c));
        invalidate();
    }

    public final int getProgressBarHeight() {
        return this.d;
    }

    public final int getId() {
        return this.e;
    }
}
