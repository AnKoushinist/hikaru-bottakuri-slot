package jp.co.mediasdk.mscore.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import jp.co.mediasdk.android.ImageUtil;

public class MSPngPackageRef {
    public static Bitmap a(String str) {
        return ImageUtil.b(str);
    }

    public static Drawable a(String str, Context context) {
        return ImageUtil.a(a(str), context);
    }
}
