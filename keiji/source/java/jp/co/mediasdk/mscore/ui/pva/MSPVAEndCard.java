package jp.co.mediasdk.mscore.ui.pva;

import android.app.Activity;
import android.content.Context;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import java.lang.ref.WeakReference;
import jp.co.mediasdk.android.StringUtilEqualsSupport;
import jp.co.mediasdk.mscore.ui.pva.MSPVAResumeButton.OnClickResumeButtonListener;

public class MSPVAEndCard implements OnClickResumeButtonListener {
    private MSPVAResumeButton a;
    private MSPVAInstallButton b;
    private LinearLayout c;
    private WeakReference<Activity> d = null;
    private MSPVAVideoView e;

    public MSPVAEndCard(Activity activity, MSPVAVideoView mSPVAVideoView) {
        LayoutParams layoutParams;
        this.d = new WeakReference(activity);
        this.e = mSPVAVideoView;
        this.c = new LinearLayout((Context) this.d.get());
        this.c.setOrientation(1);
        if (c()) {
            this.a = new MSPVAResumeButton((Activity) this.d.get(), this.e, this);
            layoutParams = new LinearLayout.LayoutParams(-2, -2);
            layoutParams.gravity = 49;
            this.c.addView(this.a.a(), layoutParams);
        }
        this.b = new MSPVAInstallButton((Activity) this.d.get());
        layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 81;
        layoutParams.setMargins(0, 40, 0, 0);
        this.c.addView(this.b.a(), layoutParams);
        layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 17;
        this.e.addView(this.c, 1, layoutParams);
    }

    public void a(int i) {
        this.c.setVisibility(i);
        this.c.invalidate();
    }

    public void b(int i) {
        if (this.b != null) {
            this.b.a(i);
        }
    }

    public void c(int i) {
        if (this.b != null) {
            this.b.b(i);
        }
    }

    public void a() {
        if (this.a != null) {
            this.a.b();
            this.a = null;
        }
        if (this.b != null) {
            this.b.b();
            this.b = null;
        }
        this.c = null;
        this.e = null;
        this.d = null;
    }

    public void b() {
        a(8);
    }

    private static boolean c() {
        if (StringUtilEqualsSupport.a("1", MSPVAVast.a().j("ShowRepeat"))) {
            return true;
        }
        return false;
    }
}
