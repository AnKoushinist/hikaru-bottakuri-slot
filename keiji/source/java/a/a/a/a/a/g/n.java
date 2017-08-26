package a.a.a.a.a.g;

import a.a.a.a.a.b.i;
import a.a.a.a.c;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;

/* compiled from: IconRequest */
public class n {
    public final String a;
    public final int b;
    public final int c;
    public final int d;

    public n(String str, int i, int i2, int i3) {
        this.a = str;
        this.b = i;
        this.c = i2;
        this.d = i3;
    }

    public static n a(Context context, String str) {
        if (str != null) {
            try {
                int l = i.l(context);
                c.h().a("Fabric", "App icon resource ID is " + l);
                Options options = new Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeResource(context.getResources(), l, options);
                return new n(str, l, options.outWidth, options.outHeight);
            } catch (Throwable e) {
                c.h().e("Fabric", "Failed to load icon", e);
            }
        }
        return null;
    }
}
