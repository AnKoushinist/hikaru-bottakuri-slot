package com.igaworks.adbrix.util;

import android.content.Context;
import android.graphics.Paint.FontMetrics;
import android.graphics.Rect;
import android.view.View.MeasureSpec;
import android.widget.TextView;
import com.tapjoy.TapjoyConnectCore;

public class AutoResizeTextView extends TextView {
    private float maxTextSize;
    private float minTextSize;

    public AutoResizeTextView(Context context) {
        super(context);
        init();
    }

    private void init() {
        this.maxTextSize = getTextSize();
        if (this.maxTextSize < 50.0f) {
            this.maxTextSize = 50.0f;
        }
        this.minTextSize = 10.0f;
    }

    private void refitText(String str, int i, int i2) {
        if (i > 0) {
            String[] split = str.split("\n");
            if (split == null || split.length <= 0) {
                int paddingLeft = (i - getPaddingLeft()) - getPaddingRight();
                float f = this.maxTextSize;
                setTextSize(0, f);
                while (f > this.minTextSize && getPaint().measureText(str) > ((float) paddingLeft)) {
                    f -= TapjoyConnectCore.DEFAULT_CURRENCY_MULTIPLIER;
                    if (f <= this.minTextSize) {
                        f = this.minTextSize;
                        break;
                    }
                    setTextSize(0, f);
                }
                setTextSize(0, f);
                return;
            }
            String str2 = split[0];
            int length = split.length;
            int i3 = 0;
            while (i3 < length) {
                String str3 = split[i3];
                if (str2.length() >= str3.length()) {
                    str3 = str2;
                }
                i3++;
                str2 = str3;
            }
            length = (i - getPaddingLeft()) - getPaddingRight();
            int paddingBottom = (i2 - getPaddingBottom()) - getPaddingTop();
            float f2 = this.maxTextSize;
            setTextSize(0, f2);
            getPaint().getTextBounds(str, 0, str.length(), new Rect());
            FontMetrics fontMetrics = getPaint().getFontMetrics();
            int abs = (int) Math.abs((fontMetrics.top - fontMetrics.bottom) * ((float) split.length));
            while (f2 > this.minTextSize && (getPaint().measureText(str2) > ((float) length) || r0 > paddingBottom)) {
                f2 -= TapjoyConnectCore.DEFAULT_CURRENCY_MULTIPLIER;
                if (f2 <= this.minTextSize) {
                    f2 = this.minTextSize;
                    break;
                }
                setTextSize(0, f2);
                fontMetrics = getPaint().getFontMetrics();
                abs = (int) Math.abs((fontMetrics.top - fontMetrics.bottom) * ((float) split.length));
            }
            setTextSize(0, f2);
        }
    }

    protected void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        refitText(charSequence.toString(), getWidth(), getHeight());
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        if (i != i3) {
            refitText(getText().toString(), i, i2);
        }
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        refitText(getText().toString(), MeasureSpec.getSize(i), MeasureSpec.getSize(i2));
    }

    public float getMinTextSize() {
        return this.minTextSize;
    }

    public void setMinTextSize(int i) {
        this.minTextSize = (float) i;
    }

    public float getMaxTextSize() {
        return this.maxTextSize;
    }

    public void setMaxTextSize(int i) {
        this.maxTextSize = (float) i;
    }
}
