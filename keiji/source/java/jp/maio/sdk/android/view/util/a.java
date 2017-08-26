package jp.maio.sdk.android.view.util;

import android.app.Activity;
import android.os.Build.VERSION;
import android.view.View;

public abstract class a {
    private static c e = new b();
    protected Activity a;
    protected View b;
    protected int c;
    protected c d = e;

    protected a(Activity activity, View view, int i) {
        this.a = activity;
        this.b = view;
        this.c = i;
    }

    public static a a(Activity activity, View view, int i) {
        return VERSION.SDK_INT >= 11 ? new e(activity, view, i) : new d(activity, view, i);
    }

    public abstract void a();

    public void a(c cVar) {
        if (cVar == null) {
            cVar = e;
        }
        this.d = cVar;
    }

    public abstract void b();

    public abstract void c();
}
