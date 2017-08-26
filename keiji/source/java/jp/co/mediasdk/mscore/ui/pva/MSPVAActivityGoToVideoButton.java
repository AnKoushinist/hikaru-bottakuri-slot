package jp.co.mediasdk.mscore.ui.pva;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.TextView;
import jp.co.mediasdk.android.LayoutUtil;
import jp.co.mediasdk.android.LayoutUtilWidthHeightSupport;

public class MSPVAActivityGoToVideoButton {
    private TextView a;
    private FrameLayout b = null;
    private MSPVALandscapeComponent c;

    public MSPVAActivityGoToVideoButton(Activity activity, FrameLayout frameLayout, MSPVALandscapeComponent mSPVALandscapeComponent) {
        this.c = mSPVALandscapeComponent;
        a(activity, frameLayout);
        a(4);
    }

    private void a(Activity activity, FrameLayout frameLayout) {
        this.b = new FrameLayout(activity);
        this.b.setBackgroundColor(-16777216);
        LayoutUtil.a(this.b, LayoutUtil.b());
        LayoutUtilWidthHeightSupport.d(this.b, 80);
        LayoutUtil.a((ViewGroup) frameLayout, this.b);
        this.a = new TextView(activity);
        this.a.setClickable(true);
        this.a.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ MSPVAActivityGoToVideoButton a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.c != null) {
                    this.a.c.a(4);
                }
                this.a.a(4);
            }
        });
        this.b.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ MSPVAActivityGoToVideoButton a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.c != null) {
                    this.a.c.a(4);
                }
                this.a.a(4);
            }
        });
        this.a.setTextSize(1, 18.0f);
        this.a.setText("\u52d5\u753b\u3092\u898b\u308b");
        this.a.setTextColor(-1);
        LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 21;
        layoutParams.setMargins(0, 0, 30, 0);
        this.b.addView(this.a, layoutParams);
    }

    public void a(int i) {
        this.b.setVisibility(i);
    }

    public void a() {
        if (this.a != null) {
            this.a.setOnClickListener(null);
        }
        this.a = null;
        this.b = null;
        this.c = null;
    }
}
