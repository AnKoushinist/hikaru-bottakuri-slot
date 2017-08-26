package jp.maio.sdk.android.view.util;

import android.os.Build.VERSION;
import android.view.View.OnSystemUiVisibilityChangeListener;

class f implements OnSystemUiVisibilityChangeListener {
    final /* synthetic */ e a;

    f(e eVar) {
        this.a = eVar;
    }

    public void onSystemUiVisibilityChange(int i) {
        if ((this.a.g & i) != 0) {
            if (VERSION.SDK_INT < 16) {
                this.a.a.getActionBar().hide();
                this.a.a.getWindow().setFlags(1024, 1024);
            }
            this.a.d.a(false);
            this.a.h = false;
            return;
        }
        this.a.b.setSystemUiVisibility(this.a.e);
        if (VERSION.SDK_INT < 16) {
            this.a.a.getActionBar().show();
            this.a.a.getWindow().setFlags(0, 1024);
        }
        this.a.d.a(true);
        this.a.h = true;
    }
}
