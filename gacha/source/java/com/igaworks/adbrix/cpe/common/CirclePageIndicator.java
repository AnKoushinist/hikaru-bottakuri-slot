package com.igaworks.adbrix.cpe.common;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.f;
import android.support.v4.view.ao;
import android.support.v4.view.t;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.View.MeasureSpec;
import android.view.ViewConfiguration;
import com.igaworks.adbrix.util.CPEConstant;
import com.tapjoy.TapjoyConnectCore;
import twitter4j.TwitterResponse;

public class CirclePageIndicator extends View implements PageIndicator {
    private int mActivePointerId;
    private boolean mCentered;
    private int mCurrentPage;
    private boolean mIsDragging;
    private float mLastMotionX;
    private f mListener;
    private int mOrientation;
    private float mPageOffset;
    private final Paint mPaintFill;
    private final Paint mPaintPageFill;
    private final Paint mPaintStroke;
    private float mRadius;
    private int mScrollState;
    private boolean mSnap;
    private int mSnapPage;
    private int mTouchSlop;
    private ViewPager mViewPager;

    static class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR = new Creator<SavedState>() {
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };
        int currentPage;

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            this.currentPage = parcel.readInt();
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.currentPage);
        }
    }

    public CirclePageIndicator(Context context) {
        this(context, null);
    }

    public CirclePageIndicator(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CirclePageIndicator(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mPaintPageFill = new Paint(1);
        this.mPaintStroke = new Paint(1);
        this.mPaintFill = new Paint(1);
        this.mLastMotionX = -1.0f;
        this.mActivePointerId = -1;
        if (!isInEditMode()) {
            getResources();
            int parseColor = Color.parseColor("#eeeeee");
            int parseColor2 = Color.parseColor("#ff0000");
            int parseColor3 = Color.parseColor("#000000");
            float convertPixelToDP = (float) CPEConstant.convertPixelToDP(context, 1, true);
            float convertPixelToDP2 = (float) CPEConstant.convertPixelToDP(context, 6, true);
            this.mCentered = true;
            this.mOrientation = 0;
            this.mPaintPageFill.setStyle(Style.FILL);
            this.mPaintPageFill.setColor(parseColor);
            this.mPaintStroke.setStyle(Style.STROKE);
            this.mPaintStroke.setColor(parseColor3);
            this.mPaintStroke.setStrokeWidth(convertPixelToDP);
            this.mPaintFill.setStyle(Style.FILL);
            this.mPaintFill.setColor(parseColor2);
            this.mPaintFill.setStrokeWidth(convertPixelToDP);
            this.mRadius = convertPixelToDP2;
            this.mSnap = true;
            Drawable colorDrawable = new ColorDrawable(0);
            if (colorDrawable != null) {
                setBackgroundDrawable(colorDrawable);
            }
            this.mTouchSlop = ao.a(ViewConfiguration.get(context));
        }
    }

    public void setCentered(boolean z) {
        this.mCentered = z;
        invalidate();
    }

    public void setPageColor(int i) {
        this.mPaintPageFill.setColor(i);
        invalidate();
    }

    public int getPageColor() {
        return this.mPaintPageFill.getColor();
    }

    public void setFillColor(int i) {
        this.mPaintFill.setColor(i);
        invalidate();
    }

    public int getFillColor() {
        return this.mPaintFill.getColor();
    }

    public void setOrientation(int i) {
        switch (i) {
            case TwitterResponse.NONE /*0*/:
            case TwitterResponse.READ /*1*/:
                this.mOrientation = i;
                requestLayout();
                return;
            default:
                throw new IllegalArgumentException("Orientation must be either HORIZONTAL or VERTICAL.");
        }
    }

    public int getOrientation() {
        return this.mOrientation;
    }

    public void setStrokeColor(int i) {
        this.mPaintStroke.setColor(i);
        invalidate();
    }

    public int getStrokeColor() {
        return this.mPaintStroke.getColor();
    }

    public void setStrokeWidth(float f) {
        this.mPaintStroke.setStrokeWidth(f);
        invalidate();
    }

    public float getStrokeWidth() {
        return this.mPaintStroke.getStrokeWidth();
    }

    public void setRadius(float f) {
        this.mRadius = f;
        invalidate();
    }

    public float getRadius() {
        return this.mRadius;
    }

    public void setSnap(boolean z) {
        this.mSnap = z;
        invalidate();
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.mViewPager != null) {
            int count = this.mViewPager.getAdapter().getCount();
            if (count == 0) {
                return;
            }
            if (this.mCurrentPage >= count) {
                setCurrentItem(count - 1);
                return;
            }
            int width;
            int paddingLeft;
            int paddingRight;
            int paddingTop;
            float f;
            if (this.mOrientation == 0) {
                width = getWidth();
                paddingLeft = getPaddingLeft();
                paddingRight = getPaddingRight();
                paddingTop = getPaddingTop();
            } else {
                width = getHeight();
                paddingLeft = getPaddingTop();
                paddingRight = getPaddingBottom();
                paddingTop = getPaddingLeft();
            }
            float f2 = this.mRadius * 3.0f;
            float f3 = this.mRadius + ((float) paddingTop);
            float f4 = ((float) paddingLeft) + this.mRadius;
            if (this.mCentered) {
                f4 += (((float) ((width - paddingLeft) - paddingRight)) / 2.0f) - ((((float) count) * f2) / 2.0f);
            }
            float f5 = this.mRadius;
            if (this.mPaintStroke.getStrokeWidth() > 0.0f) {
                f5 -= this.mPaintStroke.getStrokeWidth() / 2.0f;
            }
            for (int i = 0; i < count; i++) {
                float f6;
                f = (((float) i) * f2) + f4;
                if (this.mOrientation == 0) {
                    f6 = f;
                    f = f3;
                } else {
                    f6 = f3;
                }
                if (this.mPaintPageFill.getAlpha() > 0) {
                    canvas.drawCircle(f6, f, f5, this.mPaintPageFill);
                }
                if (f5 != this.mRadius) {
                    canvas.drawCircle(f6, f, this.mRadius, this.mPaintStroke);
                }
            }
            f = ((float) (this.mSnap ? this.mSnapPage : this.mCurrentPage)) * f2;
            if (!this.mSnap) {
                f += this.mPageOffset * f2;
            }
            if (this.mOrientation == 0) {
                f4 += f;
            } else {
                float f7 = f4 + f;
                f4 = f3;
                f3 = f7;
            }
            canvas.drawCircle(f4, f3, f5, this.mPaintFill);
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int i = 0;
        if (super.onTouchEvent(motionEvent)) {
            return true;
        }
        if (this.mViewPager == null || this.mViewPager.getAdapter().getCount() == 0) {
            return false;
        }
        int action = motionEvent.getAction() & 255;
        switch (action) {
            case TwitterResponse.NONE /*0*/:
                this.mActivePointerId = t.b(motionEvent, 0);
                this.mLastMotionX = motionEvent.getX();
                return true;
            case TwitterResponse.READ /*1*/:
            case TwitterResponse.READ_WRITE_DIRECTMESSAGES /*3*/:
                if (!this.mIsDragging) {
                    int count = this.mViewPager.getAdapter().getCount();
                    int width = getWidth();
                    float f = ((float) width) / 2.0f;
                    float f2 = ((float) width) / 6.0f;
                    if (this.mCurrentPage <= 0 || motionEvent.getX() >= f - f2) {
                        if (this.mCurrentPage < count - 1 && motionEvent.getX() > f2 + f) {
                            if (action == 3) {
                                return true;
                            }
                            this.mViewPager.setCurrentItem(this.mCurrentPage + 1);
                            return true;
                        }
                    } else if (action == 3) {
                        return true;
                    } else {
                        this.mViewPager.setCurrentItem(this.mCurrentPage - 1);
                        return true;
                    }
                }
                this.mIsDragging = false;
                this.mActivePointerId = -1;
                if (!this.mViewPager.f()) {
                    return true;
                }
                this.mViewPager.e();
                return true;
            case TwitterResponse.READ_WRITE /*2*/:
                float c = t.c(motionEvent, t.a(motionEvent, this.mActivePointerId));
                float f3 = c - this.mLastMotionX;
                if (!this.mIsDragging && Math.abs(f3) > ((float) this.mTouchSlop)) {
                    this.mIsDragging = true;
                }
                if (!this.mIsDragging) {
                    return true;
                }
                this.mLastMotionX = c;
                if (!this.mViewPager.f() && !this.mViewPager.d()) {
                    return true;
                }
                this.mViewPager.b(f3);
                return true;
            case AdInfo.BANNER_KIND_BANNER5 /*5*/:
                i = t.b(motionEvent);
                this.mLastMotionX = t.c(motionEvent, i);
                this.mActivePointerId = t.b(motionEvent, i);
                return true;
            case AdInfo.BANNER_KIND_BANNER6 /*6*/:
                action = t.b(motionEvent);
                if (t.b(motionEvent, action) == this.mActivePointerId) {
                    if (action == 0) {
                        i = 1;
                    }
                    this.mActivePointerId = t.b(motionEvent, i);
                }
                this.mLastMotionX = t.c(motionEvent, t.a(motionEvent, this.mActivePointerId));
                return true;
            default:
                return true;
        }
    }

    public void setViewPager(ViewPager viewPager) {
        if (this.mViewPager != viewPager) {
            if (this.mViewPager != null) {
                this.mViewPager.setOnPageChangeListener(null);
            }
            if (viewPager.getAdapter() == null) {
                throw new IllegalStateException("ViewPager does not have adapter instance.");
            }
            this.mViewPager = viewPager;
            this.mViewPager.setOnPageChangeListener(this);
            invalidate();
        }
    }

    public void setCurrentItem(int i) {
        if (this.mViewPager == null) {
            throw new IllegalStateException("ViewPager has not been bound.");
        }
        this.mViewPager.setCurrentItem(i);
        this.mCurrentPage = i;
        invalidate();
    }

    public void onPageScrollStateChanged(int i) {
        this.mScrollState = i;
        if (this.mListener != null) {
            this.mListener.onPageScrollStateChanged(i);
        }
    }

    public void onPageScrolled(int i, float f, int i2) {
        this.mCurrentPage = i;
        this.mPageOffset = f;
        invalidate();
        if (this.mListener != null) {
            this.mListener.onPageScrolled(i, f, i2);
        }
    }

    public void onPageSelected(int i) {
        if (this.mSnap || this.mScrollState == 0) {
            this.mCurrentPage = i;
            this.mSnapPage = i;
            invalidate();
        }
        if (this.mListener != null) {
            this.mListener.onPageSelected(i);
        }
    }

    public void setOnPageChangeListener(f fVar) {
        this.mListener = fVar;
    }

    protected void onMeasure(int i, int i2) {
        if (this.mOrientation == 0) {
            setMeasuredDimension(measureLong(i), measureShort(i2));
        } else {
            setMeasuredDimension(measureShort(i), measureLong(i2));
        }
    }

    private int measureLong(int i) {
        int mode = MeasureSpec.getMode(i);
        int size = MeasureSpec.getSize(i);
        if (mode == 1073741824 || this.mViewPager == null) {
            return size;
        }
        int count = this.mViewPager.getAdapter().getCount();
        count = (int) (((((float) (count - 1)) * this.mRadius) + (((float) (getPaddingLeft() + getPaddingRight())) + (((float) (count * 2)) * this.mRadius))) + TapjoyConnectCore.DEFAULT_CURRENCY_MULTIPLIER);
        if (mode == Integer.MIN_VALUE) {
            return Math.min(count, size);
        }
        return count;
    }

    private int measureShort(int i) {
        int mode = MeasureSpec.getMode(i);
        int size = MeasureSpec.getSize(i);
        if (mode == 1073741824) {
            return size;
        }
        int paddingTop = (int) ((((2.0f * this.mRadius) + ((float) getPaddingTop())) + ((float) getPaddingBottom())) + TapjoyConnectCore.DEFAULT_CURRENCY_MULTIPLIER);
        return mode == Integer.MIN_VALUE ? Math.min(paddingTop, size) : paddingTop;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.mCurrentPage = savedState.currentPage;
        this.mSnapPage = savedState.currentPage;
        requestLayout();
    }

    public Parcelable onSaveInstanceState() {
        Parcelable savedState = new SavedState(super.onSaveInstanceState());
        savedState.currentPage = this.mCurrentPage;
        return savedState;
    }
}
