package jp.co.mediasdk.mscore.ui.pva;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.FrameLayout;

public class RoundedCornerLayout extends FrameLayout {
    private Bitmap a;
    private Paint b;
    private Paint c;
    private float d;

    public RoundedCornerLayout(Context context) {
        super(context);
        a(context, null, 0);
    }

    private void a(Context context, AttributeSet attributeSet, int i) {
        this.d = TypedValue.applyDimension(1, 7.0f, context.getResources().getDisplayMetrics());
        this.b = new Paint(1);
        this.c = new Paint(3);
        this.c.setXfermode(new PorterDuffXfermode(Mode.CLEAR));
        setWillNotDraw(false);
    }

    public void draw(Canvas canvas) {
        Bitmap createBitmap = Bitmap.createBitmap(canvas.getWidth(), canvas.getHeight(), Config.ARGB_8888);
        Canvas canvas2 = new Canvas(createBitmap);
        super.draw(canvas2);
        if (this.a == null) {
            this.a = a(canvas.getWidth(), canvas.getHeight());
        }
        canvas2.drawBitmap(this.a, 0.0f, 0.0f, this.c);
        canvas.drawBitmap(createBitmap, 0.0f, 0.0f, this.b);
    }

    private Bitmap a(int i, int i2) {
        Bitmap createBitmap = Bitmap.createBitmap(i, i2, Config.ALPHA_8);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint(1);
        paint.setColor(-1);
        canvas.drawRect(0.0f, 0.0f, (float) i, (float) i2, paint);
        paint.setXfermode(new PorterDuffXfermode(Mode.CLEAR));
        canvas.drawRoundRect(new RectF(0.0f, 0.0f, (float) i, (float) i2), this.d, this.d, paint);
        return createBitmap;
    }

    public void a() {
        if (this.a != null) {
            this.a.recycle();
            this.a = null;
        }
    }
}
