package jp.co.mediasdk.mscore.ui.pva;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import jp.co.mediasdk.android.ImageUtil;
import jp.co.mediasdk.android.StringUtilEmptySupport;
import jp.co.mediasdk.mscore.util.MSPngPackageRef;

public class MSPVACloseButton {
    private Drawable a = null;
    private ImageView b = null;
    private Context c = null;
    private OnCloseListener d = null;

    public interface OnCloseListener {
        void a();
    }

    public MSPVACloseButton(Context context, FrameLayout frameLayout, String str, LayoutParams layoutParams) {
        this.c = context;
        a(context, frameLayout, str, layoutParams);
    }

    private void a(Context context, FrameLayout frameLayout, String str, LayoutParams layoutParams) {
        this.b = new ImageView(context);
        this.b.setVisibility(4);
        if (this.a == null) {
            String j = MSPVAVast.a().j("CloseButtonImage");
            if (StringUtilEmptySupport.c(j)) {
                c();
            } else {
                c();
                a(j);
            }
        }
        this.b.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ MSPVACloseButton a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.d.a();
            }
        });
        frameLayout.addView(this.b, layoutParams);
    }

    private void a(final String str) {
        new Thread(new Runnable(this) {
            final /* synthetic */ MSPVACloseButton b;

            public void run() {
                try {
                    this.b.a = ImageUtil.a(this.b.c, str);
                    if (this.b.b != null) {
                        this.b.b.post(new Runnable(this) {
                            final /* synthetic */ AnonymousClass2 a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                if (this.a.b.a != null) {
                                    ImageUtil.a(this.a.b.b, this.a.b.a);
                                } else {
                                    this.a.b.c();
                                }
                            }
                        });
                    }
                } catch (OutOfMemoryError e) {
                    this.b.c();
                }
            }
        }).start();
    }

    private void c() {
        this.a = MSPngPackageRef.a("jp_co_mediasdk_close.png", this.c);
        ImageUtil.a(this.b, this.a);
    }

    public void a(OnCloseListener onCloseListener) {
        this.d = onCloseListener;
    }

    public void a() {
        if (this.a != null) {
            this.a.setCallback(null);
            this.a = null;
        }
        if (this.b != null) {
            ImageUtil.a(this.b, null);
            this.b.setOnClickListener(null);
            this.b = null;
        }
        this.d = null;
    }

    public void b() {
        this.b.invalidate();
    }

    public void a(int i) {
        this.b.setVisibility(i);
    }
}
