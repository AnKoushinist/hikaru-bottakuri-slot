package com.tapjoy.internal;

import android.content.Context;
import android.content.SharedPreferences;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public final class fv {
    final q a = new q(this.c, "noMoreToday.date");
    public final q b = new q(this.c, "noMoreToday.actionIds");
    private final SharedPreferences c;

    public fv(Context context) {
        this.c = context.getApplicationContext().getSharedPreferences("fiverocks", 0);
        b();
    }

    static String a() {
        return new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
    }

    public final void b() {
        String a = this.a.a();
        if (a != null && !a().equals(a)) {
            this.a.a(null);
            this.b.a(null);
        }
    }
}
