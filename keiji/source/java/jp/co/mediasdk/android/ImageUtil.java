package jp.co.mediasdk.android;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.view.View;
import java.io.InputStream;
import java.net.URL;

public class ImageUtil {
    public static Drawable a(Context context, String str) {
        try {
            return Drawable.createFromStream((InputStream) a(str), "src");
        } catch (Exception e) {
            return null;
        }
    }

    public static Object a(String str) {
        return new URL(str).getContent();
    }

    public static Bitmap b(String str) {
        InputStream inputStream = null;
        try {
            inputStream = ResourceUtil.a(str);
            Bitmap decodeStream = BitmapFactory.decodeStream(inputStream);
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception e) {
                }
            }
            return decodeStream;
        } catch (Throwable th) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception e2) {
                }
            }
        }
    }

    public static Drawable a(Bitmap bitmap, Context context) {
        return new BitmapDrawable(context.getResources(), bitmap);
    }

    @SuppressLint({"NewApi"})
    public static void a(View view, Drawable drawable) {
        if (VERSION.SDK_INT > 16) {
            view.setBackground(drawable);
        } else {
            view.setBackgroundDrawable(drawable);
        }
        if (drawable != null) {
            drawable.setCallback(null);
        }
    }
}
