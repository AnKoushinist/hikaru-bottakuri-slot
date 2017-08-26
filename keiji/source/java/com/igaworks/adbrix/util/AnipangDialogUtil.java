package com.igaworks.adbrix.util;

import android.content.Context;
import android.util.TypedValue;
import android.widget.TextView;

public class AnipangDialogUtil {
    public static int convertPixelToDP(Context context, int i, boolean z) {
        float f = 1.5f;
        float f2 = ((float) context.getResources().getDisplayMetrics().widthPixels) / 576.0f;
        float f3 = ((float) context.getResources().getDisplayMetrics().heightPixels) / 960.0f;
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
        if (((float) context.getResources().getDisplayMetrics().widthPixels) / 576.0f != ((float) context.getResources().getDisplayMetrics().heightPixels) / 960.0f) {
            if (z) {
                return (int) (((float) (context.getResources().getDisplayMetrics().heightPixels * i)) / 960.0f);
            }
            return (int) (((float) (context.getResources().getDisplayMetrics().widthPixels * i)) / 576.0f);
        } else if (z) {
            return (int) (((float) (context.getResources().getDisplayMetrics().heightPixels * i)) / 960.0f);
        } else {
            return (int) (((float) (context.getResources().getDisplayMetrics().widthPixels * i)) / 576.0f);
        }
    }
}
