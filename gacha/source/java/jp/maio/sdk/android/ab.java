package jp.maio.sdk.android;

import android.annotation.TargetApi;
import android.os.Build.VERSION;
import android.view.View;
import jp.maio.sdk.android.view.util.c;

class ab implements c {
    int a;
    int b;
    final /* synthetic */ View c;
    final /* synthetic */ aa d;

    ab(aa aaVar, View view) {
        this.d = aaVar;
        this.c = view;
    }

    @TargetApi(13)
    public void a(boolean z) {
        if (VERSION.SDK_INT >= 13) {
            if (this.a == 0) {
                this.a = this.c.getHeight();
            }
            if (this.b == 0) {
                this.b = this.d.getResources().getInteger(17694720);
            }
            this.c.animate().translationY(z ? 0.0f : (float) this.a).setDuration((long) this.b);
        } else {
            this.c.setVisibility(z ? 0 : 8);
        }
        if (!z) {
        }
    }
}
