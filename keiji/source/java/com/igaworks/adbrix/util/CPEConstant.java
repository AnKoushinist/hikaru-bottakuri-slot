package com.igaworks.adbrix.util;

import android.content.Context;
import android.util.TypedValue;
import android.widget.TextView;

public class CPEConstant {
    public static int convertPixelToDP(Context context, int i, boolean z) {
        float f = 1.5f;
        int i2 = context.getResources().getDisplayMetrics().widthPixels;
        int i3 = context.getResources().getDisplayMetrics().heightPixels;
        if (context.getResources().getConfiguration().orientation == 2) {
            i2 = context.getResources().getDisplayMetrics().heightPixels;
            i3 = context.getResources().getDisplayMetrics().widthPixels;
        }
        float f2 = ((float) i2) / 480.0f;
        float f3 = ((float) i3) / 800.0f;
        if (f2 != f3) {
            f3 = f2;
        }
        float f4 = (float) i;
        if (z) {
            f3 = ((float) i) * f2;
        } else {
            f3 *= (float) i;
        }
        if (f3 >= 1.5f) {
            f = f3;
        }
        return (int) TypedValue.applyDimension(0, f, context.getResources().getDisplayMetrics());
    }

    public static void setTextViewSize(Context context, TextView textView, int i) {
        textView.setTextSize(0, (float) calNormPixel(context, i, false));
    }

    public static int calNormPixel(Context context, int i, boolean z) {
        int i2 = context.getResources().getDisplayMetrics().widthPixels;
        int i3 = context.getResources().getDisplayMetrics().heightPixels;
        if (context.getResources().getConfiguration().orientation == 2) {
            i2 = context.getResources().getDisplayMetrics().heightPixels;
            i3 = context.getResources().getDisplayMetrics().widthPixels;
        }
        if (((float) i2) / 480.0f != ((float) i3) / 800.0f) {
            if (z) {
                return (int) (((float) (i3 * i)) / 800.0f);
            }
            return (int) (((float) (i * i2)) / 480.0f);
        } else if (z) {
            return (int) (((float) (i3 * i)) / 800.0f);
        } else {
            return (int) (((float) (i * i2)) / 480.0f);
        }
    }
}
