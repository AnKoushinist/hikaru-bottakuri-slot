package com.tapjoy.internal;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Region.Op;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;
import twitter4j.TwitterResponse;

public class an extends ao {
    private int a = 0;
    private final Matrix b = new Matrix();
    private final float[] c = new float[2];

    public an(Context context) {
        super(context);
    }

    public int getRotationCount() {
        return this.a;
    }

    public void setRotationCount(int i) {
        this.a = i & 3;
    }

    public void onMeasure(int i, int i2) {
        if (this.a % 2 == 0) {
            super.onMeasure(i, i2);
            return;
        }
        super.onMeasure(i2, i);
        setMeasuredDimension(getMeasuredHeight(), getMeasuredWidth());
    }

    protected void dispatchDraw(Canvas canvas) {
        if (this.a == 0) {
            super.dispatchDraw(canvas);
            return;
        }
        canvas.save();
        int width = getWidth();
        int height = getHeight();
        canvas.clipRect(0.0f, 0.0f, (float) width, (float) height, Op.REPLACE);
        try {
            View view = (ViewGroup) getParent();
            try {
                View view2 = (ViewGroup) view.getParent();
                if ((view2 instanceof ScrollView) || (view2 instanceof HorizontalScrollView)) {
                    view = view2;
                }
            } catch (Exception e) {
            }
            int left = getLeft() - view.getScrollX();
            int top = getTop() - view.getScrollY();
            canvas.clipRect((float) (0 - left), (float) (0 - top), (float) (view.getWidth() - left), (float) (view.getHeight() - top), Op.INTERSECT);
        } catch (Exception e2) {
        }
        canvas.rotate((float) (this.a * 90));
        switch (this.a) {
            case TwitterResponse.READ /*1*/:
                canvas.translate(0.0f, (float) (-width));
                break;
            case TwitterResponse.READ_WRITE /*2*/:
                canvas.translate((float) (-width), (float) (-height));
                break;
            case TwitterResponse.READ_WRITE_DIRECTMESSAGES /*3*/:
                canvas.translate((float) (-height), 0.0f);
                break;
            default:
                throw new IllegalStateException();
        }
        this.b.setRotate((float) (this.a * -90));
        switch (this.a) {
            case TwitterResponse.READ /*1*/:
                this.b.postTranslate(0.0f, (float) (width - 1));
                break;
            case TwitterResponse.READ_WRITE /*2*/:
                this.b.postTranslate((float) (width - 1), (float) (height - 1));
                break;
            case TwitterResponse.READ_WRITE_DIRECTMESSAGES /*3*/:
                this.b.postTranslate((float) (height - 1), 0.0f);
                break;
            default:
                throw new IllegalStateException();
        }
        super.dispatchDraw(canvas);
        canvas.restore();
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (this.a == 0) {
            return super.dispatchTouchEvent(motionEvent);
        }
        float[] fArr = this.c;
        fArr[0] = motionEvent.getX();
        fArr[1] = motionEvent.getY();
        this.b.mapPoints(fArr);
        motionEvent.setLocation(fArr[0], fArr[1]);
        return super.dispatchTouchEvent(motionEvent);
    }
}
