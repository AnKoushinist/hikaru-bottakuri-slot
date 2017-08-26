package jp.maio.sdk.android.view.util;

import android.app.Activity;
import android.view.View;

public class d extends a {
    private boolean e = true;

    protected d(Activity activity, View view, int i) {
        super(activity, view, i);
    }

    public void a() {
        if ((this.c & 1) == 0) {
            this.a.getWindow().setFlags(768, 768);
        }
    }

    public void b() {
        if ((this.c & 2) != 0) {
            this.a.getWindow().setFlags(1024, 1024);
        }
        this.d.a(false);
        this.e = false;
    }

    public void c() {
        if ((this.c & 2) != 0) {
            this.a.getWindow().setFlags(0, 1024);
        }
        this.d.a(true);
        this.e = true;
    }
}
