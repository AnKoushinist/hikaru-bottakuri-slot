package jp.co.mediasdk.mscore.ui.pva;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.TextView;
import jp.co.mediasdk.android.StringUtilEqualsSupport;

public class MSPVAActivityGoToDetailButton {
    private TextView a;
    private MSPVAActivityGoToVideoButton b;
    private MSPVALandscapeComponent c;

    public MSPVAActivityGoToDetailButton(Activity activity, FrameLayout frameLayout, MSPVALandscapeComponent mSPVALandscapeComponent, MSPVAActivityGoToVideoButton mSPVAActivityGoToVideoButton) {
        this.c = mSPVALandscapeComponent;
        this.b = mSPVAActivityGoToVideoButton;
        a(activity, frameLayout);
    }

    private void a(Activity activity, FrameLayout frameLayout) {
        this.a = new TextView(activity);
        this.a.setClickable(true);
        this.a.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ MSPVAActivityGoToDetailButton a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.c != null) {
                    this.a.c.a(0);
                    this.a.b.a(0);
                }
            }
        });
        this.a.setTextSize(1, 18.0f);
        this.a.setText("\u8a73\u7d30\u3092\u898b\u308b");
        this.a.setTextColor(-1);
        this.a.setAlpha(0.8f);
        this.a.setBackgroundColor(-7829368);
        LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        String a = MSPVACloseButtonPosition.a();
        if (StringUtilEqualsSupport.a("center_right", a) || StringUtilEqualsSupport.a("lower_right", a)) {
            layoutParams.gravity = 53;
            layoutParams.setMargins(0, 20, 20, 0);
        } else {
            layoutParams.gravity = 85;
            layoutParams.setMargins(0, 0, 20, 20);
        }
        frameLayout.addView(this.a, 1, layoutParams);
    }

    public void a() {
        if (this.a != null) {
            this.a.setOnClickListener(null);
        }
        if (this.b != null) {
            this.b.a();
            this.b = null;
        }
        this.a = null;
        this.c = null;
    }

    public void a(int i) {
        if (this.a != null) {
            this.a.setVisibility(i);
        }
    }

    public void b() {
        LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        String b = MSPVACloseButtonPosition.b();
        if (StringUtilEqualsSupport.a("center_right", b) || StringUtilEqualsSupport.a("lower_right", b)) {
            layoutParams.gravity = 53;
            layoutParams.setMargins(0, 20, 20, 0);
        } else {
            layoutParams.gravity = 85;
            layoutParams.setMargins(0, 0, 20, 20);
        }
        this.a.setLayoutParams(layoutParams);
    }
}
