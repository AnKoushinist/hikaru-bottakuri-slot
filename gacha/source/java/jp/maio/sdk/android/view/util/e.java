package jp.maio.sdk.android.view.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.view.View;
import android.view.View.OnSystemUiVisibilityChangeListener;

@TargetApi(11)
public class e extends d {
    private int e = 0;
    private int f = 1;
    private int g = 1;
    private boolean h = true;
    private OnSystemUiVisibilityChangeListener i = new f(this);

    protected e(Activity activity, View view, int i) {
        super(activity, view, i);
        if ((this.c & 2) != 0) {
            this.e |= 1024;
            this.f |= 1028;
        }
        if ((this.c & 6) != 0) {
            this.e |= 512;
            this.f |= 514;
            this.g |= 2;
        }
    }

    public void a() {
        this.b.setOnSystemUiVisibilityChangeListener(this.i);
    }

    public void b() {
        this.b.setSystemUiVisibility(this.f);
    }

    public void c() {
        this.b.setSystemUiVisibility(this.e);
    }
}
