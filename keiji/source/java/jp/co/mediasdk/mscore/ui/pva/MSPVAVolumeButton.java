package jp.co.mediasdk.mscore.ui.pva;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView;
import jp.co.mediasdk.android.ImageUtil;
import jp.co.mediasdk.android.ResourceContextSupport;
import jp.co.mediasdk.android.StringUtilEqualsSupport;
import jp.co.mediasdk.android.Util;
import jp.co.mediasdk.mscore.ui.common.MSParameterSupport;
import jp.co.mediasdk.mscore.util.MSPngPackageRef;

public class MSPVAVolumeButton {
    private Drawable a;
    private ImageView b;
    private boolean c;
    private Context d;
    private OnVolumeButtonListener e;

    public interface OnVolumeButtonListener {
        void a(boolean z);
    }

    public MSPVAVolumeButton(Context context, FrameLayout frameLayout) {
        this.a = null;
        this.b = null;
        this.c = false;
        this.d = null;
        this.e = null;
        this.c = StringUtilEqualsSupport.a("1", MSParameterSupport.a("mute"));
        a(context, frameLayout);
    }

    public void a(OnVolumeButtonListener onVolumeButtonListener) {
        this.e = onVolumeButtonListener;
    }

    public boolean a() {
        return this.c;
    }

    public void a(boolean z) {
        this.c = z;
    }

    private void a(Context context, FrameLayout frameLayout) {
        this.d = context;
        LayoutParams b = b(MSPVACloseButtonPosition.a());
        this.b = new ImageView(context);
        this.b.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ MSPVAVolumeButton a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.c = !this.a.c;
                this.a.e.a(this.a.c);
            }
        });
        frameLayout.addView(this.b, b);
    }

    public void b() {
        if (this.c) {
            this.a = MSPngPackageRef.a("jp_co_mediasdk_volume_off.png", ResourceContextSupport.i());
        } else {
            this.a = MSPngPackageRef.a("jp_co_mediasdk_volume_on.png", ResourceContextSupport.i());
        }
        ImageUtil.a(this.b, this.a);
    }

    public void c() {
        if (this.a != null) {
            this.a.setCallback(null);
            this.a = null;
        }
        if (this.b != null) {
            ImageUtil.a(this.b, null);
            this.b.setOnClickListener(null);
            this.b = null;
        }
        this.e = null;
        this.d = null;
    }

    private FrameLayout.LayoutParams b(String str) {
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(Util.a(this.d, 31), Util.a(this.d, 31));
        layoutParams.gravity = 80;
        if (StringUtilEqualsSupport.a("center_left", str)) {
            layoutParams.setMargins(Util.a(this.d, 5), 0, 0, Util.a(this.d, 50));
        } else if (!StringUtilEqualsSupport.a("lower_left", str)) {
            layoutParams.setMargins(Util.a(this.d, 5), 0, 0, Util.a(this.d, 30));
        } else if (MSPVAOrientation.b()) {
            layoutParams.setMargins(Util.a(this.d, 5), 0, 0, Util.a(this.d, 50));
        } else if (MSPVAType.b()) {
            layoutParams.setMargins(Util.a(this.d, 5), 0, 0, Util.a(this.d, 50));
        } else {
            layoutParams.setMargins(Util.a(this.d, 5), 0, 0, Util.a(this.d, 30));
        }
        return layoutParams;
    }

    public void a(String str) {
        this.b.setLayoutParams(b(str));
    }
}
