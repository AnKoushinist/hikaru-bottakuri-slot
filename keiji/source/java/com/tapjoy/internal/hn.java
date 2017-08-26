package com.tapjoy.internal;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;

public final class hn extends View {
    public boolean a = false;
    private Bitmap b = null;
    private Rect c = null;
    private Rect d = null;
    private Rect e = null;
    private Rect f = new Rect();

    public hn(Context context) {
        super(context);
    }

    public final void setImageBitmap(Bitmap bitmap) {
        this.b = bitmap;
        int width = this.b.getWidth();
        int height = this.b.getHeight();
        this.d = new Rect(0, 0, width / 2, height);
        this.c = new Rect(width / 2, 0, width, height);
        a();
    }

    final void a() {
        if (this.a) {
            this.e = this.c;
        } else {
            this.e = this.d;
        }
    }

    public final void onDraw(Canvas canvas) {
        if (this.e != null && this.b != null) {
            getDrawingRect(this.f);
            canvas.drawBitmap(this.b, this.e, this.f, null);
        }
    }
}
