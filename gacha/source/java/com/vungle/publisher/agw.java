package com.vungle.publisher;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.util.Base64;
import android.widget.ImageView;
import com.vungle.publisher.gm.a;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public final class agw {
    @Inject
    rb a;
    @Inject
    a b;

    public final void a(ImageView imageView, rb.a aVar) {
        Bitmap a = a(aVar);
        if (a != null) {
            imageView.setImageBitmap(a);
        }
    }

    public final Bitmap a(rb.a aVar) {
        rb rbVar = this.a;
        byte[] decode = Base64.decode(aVar.g, 0);
        int length = decode.length;
        Context context = rbVar.a;
        Options options = new Options();
        options.inDensity = 330;
        options.inTargetDensity = (int) (context.getResources().getDisplayMetrics().density * ((float) options.inDensity));
        return BitmapFactory.decodeByteArray(decode, 0, length, options);
    }
}
