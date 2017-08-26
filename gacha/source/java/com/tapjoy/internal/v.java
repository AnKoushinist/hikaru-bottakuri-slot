package com.tapjoy.internal;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import java.io.InputStream;
import java.io.OutputStream;

public final class v implements bh {
    public static final v a = new v();

    public final /* synthetic */ void a(OutputStream outputStream, Object obj) {
        if (!((Bitmap) obj).compress(CompressFormat.PNG, 100, outputStream)) {
            throw new RuntimeException();
        }
    }

    public final /* synthetic */ Object b(InputStream inputStream) {
        return a(inputStream);
    }

    private v() {
    }

    public final Bitmap a(final InputStream inputStream) {
        try {
            return (Bitmap) ad.a(new bf(this) {
                final /* synthetic */ v b;

                public final /* synthetic */ Object call() {
                    if (inputStream instanceof bg) {
                        return BitmapFactory.decodeStream(inputStream);
                    }
                    return BitmapFactory.decodeStream(new bg(inputStream));
                }
            });
        } catch (OutOfMemoryError e) {
            return null;
        }
    }
}
