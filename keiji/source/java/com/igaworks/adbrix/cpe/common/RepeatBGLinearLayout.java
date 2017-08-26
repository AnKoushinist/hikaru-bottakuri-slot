package com.igaworks.adbrix.cpe.common;

import android.content.Context;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.widget.LinearLayout;

public class RepeatBGLinearLayout extends LinearLayout {
    PaintFlagsDrawFilter mDF;
    Paint mPaint;
    BitmapShader mShader;

    public RepeatBGLinearLayout(Context context) {
        super(context);
    }

    public void onDraw(Canvas canvas) {
        if (this.mPaint == null) {
            this.mPaint = new Paint(2);
            this.mPaint.setDither(false);
        }
        canvas.setDrawFilter(this.mDF);
        this.mPaint.setShader(this.mShader);
        canvas.drawPaint(this.mPaint);
    }
}
