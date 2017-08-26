package jp.co.mediasdk.mscore.ui.pva;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.FrameLayout;
import android.widget.ImageView;
import jp.co.mediasdk.android.ImageUtil;

public class MSPVAActivityImage {
    private ImageView a;
    private Drawable b;
    private Bitmap c;
    private FrameLayout d;

    class AnonymousClass1 implements Runnable {
        final /* synthetic */ Activity a;
        final /* synthetic */ MSPVAActivityImage b;

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
            r3 = this;
            r0 = r3.b;	 Catch:{ OutOfMemoryError -> 0x002d, all -> 0x0034 }
            r1 = r3.a;	 Catch:{ OutOfMemoryError -> 0x002d, all -> 0x0034 }
            r2 = "image_url";
            r2 = jp.co.mediasdk.mscore.ui.common.MSParameterSupport.a(r2);	 Catch:{ OutOfMemoryError -> 0x002d, all -> 0x0034 }
            r1 = jp.co.mediasdk.android.ImageUtil.a(r1, r2);	 Catch:{ OutOfMemoryError -> 0x002d, all -> 0x0034 }
            r0.b = r1;	 Catch:{ OutOfMemoryError -> 0x002d, all -> 0x0034 }
            r0 = r3.b;	 Catch:{ OutOfMemoryError -> 0x002d, all -> 0x0034 }
            r0 = r0.d;	 Catch:{ OutOfMemoryError -> 0x002d, all -> 0x0034 }
            if (r0 == 0) goto L_0x0027;
        L_0x0019:
            r0 = r3.b;	 Catch:{ OutOfMemoryError -> 0x002d, all -> 0x0034 }
            r0 = r0.d;	 Catch:{ OutOfMemoryError -> 0x002d, all -> 0x0034 }
            r1 = new jp.co.mediasdk.mscore.ui.pva.MSPVAActivityImage$1$1;	 Catch:{ OutOfMemoryError -> 0x002d, all -> 0x0034 }
            r1.<init>(r3);	 Catch:{ OutOfMemoryError -> 0x002d, all -> 0x0034 }
            r0.post(r1);	 Catch:{ OutOfMemoryError -> 0x002d, all -> 0x0034 }
        L_0x0027:
            r0 = "image_url";
            jp.co.mediasdk.mscore.ui.common.MSParameterSupport.c(r0);
        L_0x002c:
            return;
        L_0x002d:
            r0 = move-exception;
            r0 = "image_url";
            jp.co.mediasdk.mscore.ui.common.MSParameterSupport.c(r0);
            goto L_0x002c;
        L_0x0034:
            r0 = move-exception;
            r1 = "image_url";
            jp.co.mediasdk.mscore.ui.common.MSParameterSupport.c(r1);
            throw r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: jp.co.mediasdk.mscore.ui.pva.MSPVAActivityImage.1.run():void");
        }
    }

    public void a() {
        if (this.b != null) {
            this.b.setCallback(null);
            this.b = null;
        }
        if (this.a != null) {
            this.a.setImageDrawable(null);
            this.a = null;
        }
        if (this.c != null) {
            this.c.recycle();
            this.c = null;
        }
        ImageUtil.a(this.d, null);
        this.d = null;
    }
}
