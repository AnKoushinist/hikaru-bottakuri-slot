package com.igaworks.adbrix.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.TypedValue;
import android.widget.TextView;

public class DialogUtil {
    private static Canvas canvas;
    private static Bitmap output;

    public static int convertPixelToDP(Context context, int i, boolean z) {
        float f = 1.5f;
        int i2 = context.getResources().getDisplayMetrics().widthPixels;
        int i3 = context.getResources().getDisplayMetrics().heightPixels;
        if (context.getResources().getConfiguration().orientation == 2) {
            i2 = context.getResources().getDisplayMetrics().heightPixels;
            i3 = context.getResources().getDisplayMetrics().widthPixels;
        }
        float f2 = ((float) i2) / 720.0f;
        float f3 = ((float) i3) / 1280.0f;
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
        if (((float) i2) / 720.0f != ((float) i3) / 1280.0f) {
            if (z) {
                return (int) (((float) (i3 * i)) / 1280.0f);
            }
            return (int) (((float) (i * i2)) / 720.0f);
        } else if (z) {
            return (int) (((float) (i3 * i)) / 1280.0f);
        } else {
            return (int) (((float) (i * i2)) / 720.0f);
        }
    }

    public static Bitmap getRoundedCornerBitmap(Context context, Bitmap bitmap, int i, int i2) {
        if (bitmap == null) {
            return null;
        }
        Bitmap bitmap2;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int i3 = (int) (((double) i) * 1.1d);
        if (height > i3) {
            bitmap2 = bitmap;
            while (height > i3) {
                bitmap2 = Bitmap.createScaledBitmap(bitmap2, (width * i3) / height, i3, true);
                width = bitmap2.getWidth();
                height = bitmap2.getHeight();
            }
        } else {
            bitmap2 = bitmap;
            while (height < i3) {
                bitmap2 = Bitmap.createScaledBitmap(bitmap2, (width * i3) / height, i3, true);
                width = bitmap2.getWidth();
                height = bitmap2.getHeight();
            }
        }
        output = Bitmap.createBitmap(width, height, Config.ARGB_8888);
        canvas = new Canvas(output);
        Paint paint = new Paint();
        Rect rect = new Rect(0, 0, width, height);
        RectF rectF = new RectF(rect);
        float convertPixelToDP = (float) convertPixelToDP(context, 14, true);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(-12434878);
        canvas.drawRoundRect(rectF, (float) ((int) (convertPixelToDP * 1.3f)), (float) ((int) (convertPixelToDP * 1.3f)), paint);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap2, rect, rect, paint);
        return output;
    }
}
