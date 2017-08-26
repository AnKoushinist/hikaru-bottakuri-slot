package com.jirbo.adcolony;

import android.content.res.Configuration;
import android.graphics.Rect;
import android.os.Handler;
import android.view.Display;
import android.view.View;
import android.widget.FrameLayout.LayoutParams;

public class AdColonyOverlay extends ADCVideo {
    Rect Z = new Rect();
    int aa = 0;

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        Display defaultDisplay = getWindowManager().getDefaultDisplay();
        this.t = defaultDisplay.getWidth();
        this.u = defaultDisplay.getHeight();
        a.K = true;
        final View view = new View(this);
        view.setBackgroundColor(-16777216);
        if (d && this.H.Q) {
            this.Q.setLayoutParams(new LayoutParams(this.t, this.u - this.H.m, 17));
            this.P.addView(view, new LayoutParams(this.t, this.u, 17));
            new Handler().postDelayed(new Runnable(this) {
                final /* synthetic */ AdColonyOverlay b;

                public void run() {
                    this.b.P.removeView(view);
                }
            }, 1500);
        }
        this.H.a();
    }
}
