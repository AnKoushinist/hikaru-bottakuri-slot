package jp.co.mediasdk.mscore.ui.pva;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import jp.co.mediasdk.android.ImageUtil;
import jp.co.mediasdk.android.ResourceContextSupport;
import jp.co.mediasdk.android.StringUtilEqualsSupport;
import jp.co.mediasdk.android.Util;
import jp.co.mediasdk.mscore.util.MSPngPackageRef;

public class MSPVAVideoTimeDisplay {
    private ImageView a = null;
    private Drawable b = null;
    private TextView c;
    private SimpleDateFormat d;
    private Context e;

    public MSPVAVideoTimeDisplay(Context context, FrameLayout frameLayout) {
        this.e = context;
        a(context, frameLayout);
        b(context, frameLayout);
    }

    private void a(Context context, FrameLayout frameLayout) {
        LayoutParams b = b(MSPVACloseButtonPosition.a());
        this.a = new ImageView(context);
        this.b = MSPngPackageRef.a("jp_co_mediasdk_time.png", ResourceContextSupport.i());
        ImageUtil.a(this.a, this.b);
        frameLayout.addView(this.a, b);
    }

    private void b(Context context, FrameLayout frameLayout) {
        this.d = new SimpleDateFormat("mm:ss");
        this.c = new TextView(context);
        this.c.setText("00:00");
        this.c.setTextSize(1, 12.0f);
        this.c.setTextColor(-1);
        frameLayout.addView(this.c, a(MSPVACloseButtonPosition.a()));
    }

    public void a(int i) {
        this.c.setText(this.d.format(Integer.valueOf(i)));
        this.c.invalidate();
    }

    public void a() {
        if (this.b != null) {
            this.b.setCallback(null);
            this.b = null;
        }
        if (this.a != null) {
            ImageUtil.a(this.a, null);
            this.a = null;
        }
        this.c = null;
        this.d = null;
        this.e = null;
    }

    public void b(int i) {
        c(i);
    }

    public void c(int i) {
        String a = MSPVACloseButtonPosition.a();
        if (i > 0) {
            a = MSPVACloseButtonPosition.b();
        }
        LayoutParams a2 = a(a);
        LayoutParams b = b(a);
        this.c.setLayoutParams(a2);
        this.a.setLayoutParams(b);
    }

    private FrameLayout.LayoutParams a(String str) {
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        if (StringUtilEqualsSupport.a("center_left", str)) {
            layoutParams.setMargins(Util.a(this.e, 91), 0, 0, Util.a(this.e, 7));
        } else if (!StringUtilEqualsSupport.a("lower_left", str)) {
            layoutParams.setMargins(Util.a(this.e, 51), 0, 0, Util.a(this.e, 7));
        } else if (MSPVAOrientation.b()) {
            layoutParams.setMargins(Util.a(this.e, 91), 0, 0, Util.a(this.e, 7));
        } else if (MSPVAType.b()) {
            layoutParams.setMargins(Util.a(this.e, 91), 0, 0, Util.a(this.e, 7));
        } else {
            layoutParams.setMargins(Util.a(this.e, 51), 0, 0, Util.a(this.e, 7));
        }
        layoutParams.gravity = 80;
        return layoutParams;
    }

    private FrameLayout.LayoutParams b(String str) {
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(Util.a(this.e, 85), Util.a(this.e, 21));
        layoutParams.gravity = 80;
        if (StringUtilEqualsSupport.a("center_left", str)) {
            layoutParams.setMargins(Util.a(this.e, 45), 0, 0, Util.a(this.e, 5));
        } else if (!StringUtilEqualsSupport.a("lower_left", str)) {
            layoutParams.setMargins(Util.a(this.e, 5), 0, 0, Util.a(this.e, 5));
        } else if (MSPVAOrientation.b()) {
            layoutParams.setMargins(Util.a(this.e, 45), 0, 0, Util.a(this.e, 5));
        } else if (MSPVAType.b()) {
            layoutParams.setMargins(Util.a(this.e, 45), 0, 0, Util.a(this.e, 5));
        } else {
            layoutParams.setMargins(Util.a(this.e, 5), 0, 0, Util.a(this.e, 5));
        }
        return layoutParams;
    }
}
