package com.tapjoy.internal;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import com.tapjoy.TJAdUnitConstants.String;
import java.sql.Timestamp;
import org.cocos2dx.lib.BuildConfig;

public abstract class r extends s {
    public final Context a;
    public final t b;
    private boolean c = false;

    public r(Context context, t tVar) {
        this.a = context.getApplicationContext();
        this.b = tVar;
    }

    private boolean b() {
        boolean e = this.b.e(this.a);
        if (e) {
            long f = this.b.f(this.a);
            if (f != 0 && System.currentTimeMillis() > f) {
                new Object[1][0] = new Timestamp(f);
                return false;
            }
        }
        return e;
    }

    public final boolean a() {
        String a = this.b.a(this.a);
        if (cr.c(a)) {
            return false;
        }
        a(a);
        return true;
    }

    public final void a(String str) {
        String b = this.b.b(this.a);
        if (b.length() == 0) {
            e(str);
            return;
        }
        if (str.equals(this.b.a(this.a))) {
            int d = this.b.d(this.a);
            int a = ae.a(this.a);
            if (d != Integer.MIN_VALUE && d != a) {
                Object[] objArr = new Object[]{Integer.valueOf(d), Integer.valueOf(a), b};
                e(str);
                return;
            } else if (this.b.c(this.a)) {
                new Object[1][0] = b;
                e(str);
                return;
            } else if (b()) {
                r1 = new Object[]{str, b};
                return;
            } else {
                r1 = new Object[]{str, b};
                a(this.a, b);
                return;
            }
        }
        Object[] objArr2 = new Object[]{this.b.a(this.a), str, b};
        e(str);
    }

    private void e(String str) {
        this.b.a(this.a, str);
        this.b.a(this.a, true);
        if ((!this.c && b(this.a, str)) || !c(this.a, str)) {
        }
    }

    private static boolean b(Context context, String str) {
        try {
            int b = ae.b(context.getPackageManager(), "com.google.android.gms");
            if (b >= 3159130) {
                Object[] objArr = new Object[]{Integer.valueOf(b), str};
                Intent intent = new Intent("com.google.android.c2dm.intent.REGISTER");
                intent.setPackage("com.google.android.gms");
                b(context, intent);
                intent.putExtra("sender", str);
                if (context.startService(intent) != null) {
                    return true;
                }
                return false;
            }
            new Object[1][0] = Integer.valueOf(b);
            return false;
        } catch (RuntimeException e) {
        }
    }

    private static boolean c(Context context, String str) {
        try {
            int b = ae.b(context.getPackageManager(), "com.google.android.gsf");
            Object[] objArr = new Object[]{Integer.valueOf(b), str};
            Intent intent = new Intent("com.google.android.c2dm.intent.REGISTER");
            intent.setPackage("com.google.android.gsf");
            b(context, intent);
            intent.putExtra("sender", str);
            if (context.startService(intent) != null) {
                return true;
            }
            return false;
        } catch (RuntimeException e) {
            return false;
        }
    }

    private static void b(Context context, Intent intent) {
        intent.putExtra("app", PendingIntent.getBroadcast(context, 0, new Intent(), 0));
    }

    public final void a(Context context) {
        this.b.b(context, 3000);
    }

    public final boolean a(Intent intent) {
        String action = intent.getAction();
        if ("com.google.android.c2dm.intent.REGISTRATION".equals(action)) {
            action = intent.getStringExtra("registration_id");
            String stringExtra = intent.getStringExtra("unregistered");
            String stringExtra2 = intent.getStringExtra(String.VIDEO_ERROR);
            if (action != null) {
                if (action.length() > 0) {
                    if (action.startsWith("|")) {
                        return false;
                    }
                    f(action);
                    return false;
                } else if (stringExtra == null && stringExtra2 == null && !this.c) {
                    this.c = true;
                    a();
                    return true;
                }
            }
            if (stringExtra != null) {
                action = this.b.b(this.a);
                a(this.a);
                this.b.b(this.a, false);
                this.b.b(this.a, BuildConfig.FLAVOR);
                this.b.a(this.a, true);
                b(action);
                return false;
            } else if (stringExtra2 == null) {
                return false;
            } else {
                boolean c;
                if ("SERVICE_NOT_AVAILABLE".equals(stringExtra2)) {
                    c = c(stringExtra2);
                } else if (this.c) {
                    c = d(stringExtra2);
                } else {
                    c(stringExtra2);
                    this.c = true;
                    a();
                    return true;
                }
                if (c) {
                    int g = this.b.g(this.a);
                    new Object[1][0] = Integer.valueOf(g);
                    Intent intent2 = new Intent("com.google.android.gcm.intent.RETRY");
                    intent2.setPackage(this.a.getPackageName());
                    ((AlarmManager) this.a.getSystemService("alarm")).set(3, SystemClock.elapsedRealtime() + ((long) g), PendingIntent.getBroadcast(this.a, 0, intent2, 0));
                    if (g < 3600000) {
                        this.b.b(this.a, g * 2);
                    }
                }
                return true;
            }
        } else if ("com.google.android.c2dm.intent.RECEIVE".equals(action)) {
            action = intent.getStringExtra("message_type");
            if (action == null) {
                return a(this.a, intent);
            }
            if ("deleted_messages".equals(action)) {
                action = intent.getStringExtra("total_deleted");
                if (action == null) {
                    return false;
                }
                try {
                    int parseInt = Integer.parseInt(action);
                    new Object[1][0] = Integer.valueOf(parseInt);
                    a(parseInt);
                    return false;
                } catch (NumberFormatException e) {
                    new Object[1][0] = action;
                    return false;
                }
            }
            new Object[1][0] = action;
            return false;
        } else if ("com.google.android.gcm.intent.RETRY".equals(action)) {
            action = intent.getPackage();
            if (action == null || !action.equals(this.a.getPackageName())) {
                new Object[1][0] = action;
                return false;
            }
            a();
            return true;
        } else {
            new Object[1][0] = action;
            return false;
        }
    }

    private synchronized void f(String str) {
        a(this.a);
        this.b.a(this.a, false);
        this.b.a(this.a, ae.a(this.a));
        if (!str.equals(this.b.b(this.a))) {
            new Object[1][0] = str;
            this.b.b(this.a, false);
            this.b.b(this.a, str);
            a(this.a, str);
        } else if (b()) {
            new Object[1][0] = str;
        } else {
            new Object[1][0] = str;
            a(this.a, str);
        }
    }
}
