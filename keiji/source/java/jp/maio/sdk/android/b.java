package jp.maio.sdk.android;

import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;

class b implements Runnable {
    final /* synthetic */ a a;

    b(a aVar) {
        this.a = aVar;
    }

    public void run() {
        this.a.a.getWindowManager().getDefaultDisplay().getWidth();
        this.a.a.getWindowManager().getDefaultDisplay().getHeight();
        this.a.a.h.getHolder().setFixedSize(this.a.a.getWindowManager().getDefaultDisplay().getWidth(), this.a.a.getWindowManager().getDefaultDisplay().getHeight());
        LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        layoutParams.gravity = 17;
        this.a.a.h.setLayoutParams(layoutParams);
        this.a.a.h.forceLayout();
    }
}
