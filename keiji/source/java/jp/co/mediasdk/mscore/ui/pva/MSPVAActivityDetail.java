package jp.co.mediasdk.mscore.ui.pva;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.TextView;
import java.lang.ref.WeakReference;
import jp.co.mediasdk.android.ImageUtil;
import jp.co.mediasdk.android.LayoutUtil;
import jp.co.mediasdk.android.LayoutUtilMarginSupport;
import jp.co.mediasdk.android.Util;

public class MSPVAActivityDetail implements MSPVALandscapeComponent {
    private WeakReference<Activity> a = null;
    private FrameLayout b = null;
    private MSPVAInstallButton c = null;
    private RoundedCornerLayout d;
    private Drawable e;
    private TextView f = null;
    private TextView g = null;

    public MSPVAActivityDetail(Activity activity, FrameLayout frameLayout) {
        this.a = new WeakReference(activity);
        this.b = new FrameLayout(activity);
        this.b.setBackgroundColor(-1);
        if (MSPVAOrientation.b()) {
            a(4);
        }
        if (!LayoutUtil.c(frameLayout, this.b)) {
            LayoutUtil.a(this.b, LayoutUtil.a());
            LayoutUtil.a((ViewGroup) frameLayout, this.b);
            LayoutUtilMarginSupport.c(this.b, b());
        }
        this.c = new MSPVAInstallButton((Activity) this.a.get());
        LayoutParams layoutParams = new FrameLayout.LayoutParams(Util.a((Context) this.a.get(), 120), Util.a((Context) this.a.get(), 45));
        this.c.a(12);
        layoutParams.setMargins(0, Util.a((Context) this.a.get(), 90) + 40, 20, 0);
        layoutParams.gravity = 53;
        LayoutUtil.a(this.c.a(), layoutParams);
        LayoutUtil.a(this.b, this.c.a());
        this.d = new RoundedCornerLayout((Context) this.a.get());
        layoutParams = new FrameLayout.LayoutParams(Util.a((Context) this.a.get(), 90), Util.a((Context) this.a.get(), 90));
        layoutParams.setMargins(20, 20, 0, 0);
        layoutParams.gravity = 51;
        LayoutUtil.a(this.d, layoutParams);
        LayoutUtil.a(this.b, this.d);
        new Thread(new Runnable(this) {
            final /* synthetic */ MSPVAActivityDetail a;

            {
                this.a = r1;
            }

            public void run() {
                try {
                    this.a.e = ImageUtil.a((Context) this.a.a.get(), MSPVAVast.a().j("Icon"));
                    this.a.d.post(new Runnable(this) {
                        final /* synthetic */ AnonymousClass1 a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            if (this.a.a.e != null) {
                                ImageUtil.a(this.a.a.d, this.a.a.e);
                            }
                        }
                    });
                } catch (OutOfMemoryError e) {
                }
            }
        }).start();
        this.f = new TextView((Context) this.a.get());
        this.f.setText(MSPVAVast.a().j("AdTitle"));
        this.f.setTextSize(14.0f);
        this.f.setTypeface(Typeface.create(Typeface.SANS_SERIF, 1));
        this.f.setTextColor(-16777216);
        layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.setMargins(Util.a((Context) this.a.get(), 90) + 40, 20, 0, 0);
        layoutParams.gravity = 51;
        LayoutUtil.a(this.f, layoutParams);
        LayoutUtil.a(this.b, this.f);
        this.g = new TextView((Context) this.a.get());
        this.g.setText(MSPVAVast.a().j("Description"));
        this.g.setTextSize(14.0f);
        this.g.setTextColor(-16777216);
        layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.setMargins(20, Util.a((Context) this.a.get(), 135) + 60, 20, 0);
        layoutParams.gravity = 51;
        LayoutUtil.a(this.g, layoutParams);
        LayoutUtil.a(this.b, this.g);
    }

    private int b() {
        Point a = Util.a((Context) this.a.get());
        if (MSPVAOrientation.b()) {
            return a.y;
        }
        return Util.b(a.x);
    }

    public void a(int i) {
        if (i == 0) {
            LayoutUtilMarginSupport.c(this.b, 80);
        } else {
            LayoutUtilMarginSupport.c(this.b, b());
        }
    }

    public void a() {
        if (this.e != null) {
            this.e.setCallback(null);
            this.e = null;
        }
        if (this.d != null) {
            ImageUtil.a(this.d, null);
            this.d.a();
        }
        this.d = null;
        if (this.c != null) {
            this.c.b();
            this.c = null;
        }
        this.b = null;
    }
}
