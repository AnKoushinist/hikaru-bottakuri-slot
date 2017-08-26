package com.igaworks.adbrix.cpe.common;

import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Matrix.ScaleToFit;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.Shape;

public class CustomShapeDrawable extends ShapeDrawable {
    private final Paint fillpaint;
    private final int strokeWidth;
    private final Paint strokepaint;

    public CustomShapeDrawable(Shape shape, int i, int i2, int i3) {
        this(shape, i, i2, i3, false);
    }

    public CustomShapeDrawable(Shape shape, int i, int i2, int i3, boolean z) {
        super(shape);
        this.strokeWidth = i3;
        this.fillpaint = new Paint(getPaint());
        this.fillpaint.setStyle(Style.FILL);
        this.fillpaint.setColor(i);
        this.strokepaint = new Paint(getPaint());
        this.strokepaint.setStyle(Style.STROKE);
        this.strokepaint.setStrokeWidth((float) i3);
        this.strokepaint.setColor(i2);
        if (z) {
            ColorMatrix colorMatrix = new ColorMatrix();
            colorMatrix.setSaturation(0.0f);
            this.strokepaint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        }
    }

    protected void onDraw(Shape shape, Canvas canvas, Paint paint) {
        shape.draw(canvas, this.fillpaint);
        Matrix matrix = new Matrix();
        matrix.setRectToRect(new RectF(0.0f, 0.0f, (float) canvas.getClipBounds().right, (float) canvas.getClipBounds().bottom), new RectF(0.0f, 0.0f, (float) canvas.getClipBounds().right, (float) canvas.getClipBounds().bottom), ScaleToFit.FILL);
        canvas.concat(matrix);
    }
}
