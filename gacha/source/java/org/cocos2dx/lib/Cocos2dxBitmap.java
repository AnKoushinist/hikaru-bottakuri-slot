package org.cocos2dx.lib;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.text.Layout.Alignment;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.util.Log;
import com.tapjoy.TapjoyConnectCore;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public final class Cocos2dxBitmap {
    private static final int HORIZONTAL_ALIGN_CENTER = 3;
    private static final int HORIZONTAL_ALIGN_LEFT = 1;
    private static final int HORIZONTAL_ALIGN_RIGHT = 2;
    private static final int VERTICAL_ALIGN_BOTTOM = 2;
    private static final int VERTICAL_ALIGN_CENTER = 3;
    private static final int VERTICAL_ALIGN_TOP = 1;
    private static Context sContext;

    private static native void nativeInitBitmapDC(int i, int i2, byte[] bArr);

    public static void setContext(Context context) {
        sContext = context;
    }

    public static boolean createTextBitmapShadowStroke(byte[] bArr, String str, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, boolean z, float f, float f2, float f3, float f4, boolean z2, int i9, int i10, int i11, int i12, float f5) {
        if (bArr == null || bArr.length == 0) {
            return false;
        }
        int ceil;
        CharSequence str2 = new String(bArr);
        Alignment alignment = Alignment.ALIGN_NORMAL;
        int i13 = i6 & 15;
        switch (i13) {
            case VERTICAL_ALIGN_BOTTOM /*2*/:
                alignment = Alignment.ALIGN_OPPOSITE;
                break;
            case VERTICAL_ALIGN_CENTER /*3*/:
                alignment = Alignment.ALIGN_CENTER;
                break;
        }
        TextPaint newPaint = newPaint(str, i);
        if (z2) {
            newPaint.setStyle(Style.STROKE);
            newPaint.setStrokeWidth(f5);
        }
        if (i7 <= 0) {
            ceil = (int) Math.ceil((double) StaticLayout.getDesiredWidth(str2, newPaint));
        } else {
            ceil = i7;
        }
        StaticLayout staticLayout = new StaticLayout(str2, newPaint, ceil, alignment, TapjoyConnectCore.DEFAULT_CURRENCY_MULTIPLIER, 0.0f, false);
        ceil = staticLayout.getWidth();
        int lineTop = staticLayout.getLineTop(staticLayout.getLineCount());
        int max = Math.max(ceil, i7);
        if (i8 <= 0) {
            i8 = lineTop;
        }
        if (max == 0 || i8 == 0) {
            return false;
        }
        if (i13 == VERTICAL_ALIGN_CENTER) {
            ceil = (max - ceil) / VERTICAL_ALIGN_BOTTOM;
        } else if (i13 == VERTICAL_ALIGN_BOTTOM) {
            ceil = max - ceil;
        } else {
            ceil = 0;
        }
        int i14 = 0;
        switch ((i6 >> 4) & 15) {
            case VERTICAL_ALIGN_BOTTOM /*2*/:
                i14 = i8 - lineTop;
                break;
            case VERTICAL_ALIGN_CENTER /*3*/:
                i14 = (i8 - lineTop) / VERTICAL_ALIGN_BOTTOM;
                break;
        }
        Bitmap createBitmap = Bitmap.createBitmap(max, i8, Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        canvas.translate((float) ceil, (float) i14);
        if (z2) {
            newPaint.setARGB(i12, i9, i10, i11);
            staticLayout.draw(canvas);
        }
        newPaint.setStyle(Style.FILL);
        newPaint.setARGB(i5, i2, i3, i4);
        staticLayout.draw(canvas);
        initNativeObject(createBitmap);
        return true;
    }

    private static TextPaint newPaint(String str, int i) {
        TextPaint textPaint = new TextPaint();
        textPaint.setTextSize((float) i);
        textPaint.setAntiAlias(true);
        if (str.endsWith(".ttf")) {
            try {
                textPaint.setTypeface(Cocos2dxTypefaces.get(sContext, str));
            } catch (Exception e) {
                Log.e("Cocos2dxBitmap", "error to create ttf type face: " + str);
                textPaint.setTypeface(Typeface.create(str, 0));
            }
        } else {
            textPaint.setTypeface(Typeface.create(str, 0));
        }
        return textPaint;
    }

    private static void initNativeObject(Bitmap bitmap) {
        byte[] pixels = getPixels(bitmap);
        if (pixels != null) {
            nativeInitBitmapDC(bitmap.getWidth(), bitmap.getHeight(), pixels);
        }
    }

    private static byte[] getPixels(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        byte[] bArr = new byte[((bitmap.getWidth() * bitmap.getHeight()) * 4)];
        Buffer wrap = ByteBuffer.wrap(bArr);
        wrap.order(ByteOrder.nativeOrder());
        bitmap.copyPixelsToBuffer(wrap);
        return bArr;
    }

    public static int getFontSizeAccordingHeight(int i) {
        TextPaint textPaint = new TextPaint();
        Rect rect = new Rect();
        textPaint.setTypeface(Typeface.DEFAULT);
        int i2 = 0;
        int i3 = VERTICAL_ALIGN_TOP;
        while (i2 == 0) {
            textPaint.setTextSize((float) i3);
            String str = "SghMNy";
            textPaint.getTextBounds(str, 0, str.length(), rect);
            i3 += VERTICAL_ALIGN_TOP;
            if (i - rect.height() <= VERTICAL_ALIGN_BOTTOM) {
                i2 = VERTICAL_ALIGN_TOP;
            }
        }
        return i3;
    }

    private static String getStringWithEllipsis(String str, float f, float f2) {
        if (TextUtils.isEmpty(str)) {
            return BuildConfig.FLAVOR;
        }
        TextPaint textPaint = new TextPaint();
        textPaint.setTypeface(Typeface.DEFAULT);
        textPaint.setTextSize(f2);
        return TextUtils.ellipsize(str, textPaint, f, TruncateAt.END).toString();
    }
}
