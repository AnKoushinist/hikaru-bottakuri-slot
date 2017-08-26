package com.igaworks.adbrix.cpe.common;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Matrix.ScaleToFit;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.Shape;

public class RoundedRepeatShapDrawable extends ShapeDrawable {
    private final Paint fillpaint;
    PaintFlagsDrawFilter mDF = new PaintFlagsDrawFilter(6, 3);
    Paint mPaint;
    BitmapShader mShader;
    Bitmap mTexture;
    private final int strokeWidth;
    private final Paint strokepaint;

    public RoundedRepeatShapDrawable(Shape shape, int i, int i2, int i3, Bitmap bitmap) {
        super(shape);
        this.strokeWidth = i3;
        this.mTexture = bitmap;
        this.mShader = new BitmapShader(this.mTexture, TileMode.REPEAT, TileMode.REPEAT);
        this.mPaint = new Paint(2);
        this.mPaint.setDither(false);
        this.fillpaint = this.mPaint;
        this.strokepaint = new Paint(this.fillpaint);
        this.strokepaint.setStyle(Style.STROKE);
        this.strokepaint.setStrokeWidth((float) i3);
        this.strokepaint.setColor(i2);
    }

    protected void onDraw(Shape shape, Canvas canvas, Paint paint) {
        canvas.setDrawFilter(this.mDF);
        this.mPaint.setShader(this.mShader);
        shape.resize((float) canvas.getClipBounds().right, (float) canvas.getClipBounds().bottom);
        shape.draw(canvas, this.fillpaint);
        Matrix matrix = new Matrix();
        matrix.setRectToRect(new RectF(0.0f, 0.0f, (float) canvas.getClipBounds().right, (float) canvas.getClipBounds().bottom), new RectF((float) (this.strokeWidth / 2), (float) (this.strokeWidth / 2), (float) (canvas.getClipBounds().right - (this.strokeWidth / 2)), (float) (canvas.getClipBounds().bottom - (this.strokeWidth / 2))), ScaleToFit.FILL);
        canvas.concat(matrix);
    }
}
