package jp.co.mediasdk.mscore.ui.pva;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.lang.ref.WeakReference;
import jp.co.mediasdk.android.ImageUtil;
import jp.co.mediasdk.android.Util;
import jp.co.mediasdk.android.WindowUtil;
import jp.co.mediasdk.mscore.util.MSPngPackageRef;

public class MSPVAResumeButton {
    private TextView a;
    private ImageView b;
    private LinearLayout c;
    private WeakReference<Activity> d = null;
    private MSPVAVideoView e;
    private OnClickResumeButtonListener f;
    private RoundedCornerLayout g;

    public interface OnClickResumeButtonListener {
        void b();
    }

    public MSPVAResumeButton(Activity activity, MSPVAVideoView mSPVAVideoView, OnClickResumeButtonListener onClickResumeButtonListener) {
        this.d = new WeakReference(activity);
        this.e = mSPVAVideoView;
        this.f = onClickResumeButtonListener;
        this.g = new RoundedCornerLayout((Context) this.d.get());
        this.g.setBackgroundColor(-7829368);
        this.g.setAlpha(0.8f);
        this.g.setClickable(true);
        this.g.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ MSPVAResumeButton a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.e.setVideoPlaying(true);
                this.a.f.b();
                this.a.e.a(0);
                this.a.e.a();
                WindowUtil.a((Activity) this.a.d.get());
            }
        });
        this.c = new LinearLayout((Context) this.d.get());
        this.c.setOrientation(0);
        this.a = new TextView((Context) this.d.get());
        this.b = new ImageView((Context) this.d.get());
        ImageUtil.a(this.b, MSPngPackageRef.a("jp_co_mediasdk_repeat.png", (Context) this.d.get()));
        LayoutParams layoutParams = new LinearLayout.LayoutParams(Util.a((Context) this.d.get(), 40), Util.a(activity, 40));
        layoutParams.gravity = 17;
        this.c.addView(this.b, layoutParams);
        this.a.setTextSize(1, 20.0f);
        this.a.setText("\u3082\u3046\u4e00\u5ea6\u898b\u308b");
        this.a.setTextColor(-1);
        layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 16;
        int a = Util.a((Context) this.d.get(), 10);
        layoutParams.setMargins(a, a, a, a);
        this.c.addView(this.a, layoutParams);
        this.g.addView(this.c, 0, new FrameLayout.LayoutParams(-2, -2));
    }

    public RoundedCornerLayout a() {
        return this.g;
    }

    public void b() {
        if (this.b != null) {
            ImageUtil.a(this.b, null);
            this.b = null;
        }
        this.a = null;
        if (this.c != null) {
            this.c.setOnClickListener(null);
        }
        this.c = null;
        this.e = null;
        this.d = null;
        this.f = null;
        if (this.g != null) {
            this.g.a();
            this.g.setOnClickListener(null);
        }
        this.g = null;
    }
}
