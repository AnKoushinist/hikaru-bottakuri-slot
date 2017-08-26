package com.tapjoy.internal;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources.NotFoundException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.Html;
import com.tapjoy.TJAdUnitConstants.String;
import com.tapjoy.Tapjoy;
import com.tapjoy.TapjoyConstants;
import com.tapjoy.TapjoyReceiver;
import com.tapjoy.internal.a.c;
import com.tapjoy.internal.a.d;
import com.tapjoy.internal.a.l;
import com.tapjoy.internal.dy.a;
import jp.co.geniee.gnsrewardadapter.GNSAdapterTapjoyRewardVideoAd;
import org.cocos2dx.lib.BuildConfig;

public final class gb extends r {
    private static gb c;

    public static synchronized gb b(Context context) {
        gb gbVar;
        synchronized (gb.class) {
            if (c == null) {
                c = new gb(context);
            }
            gbVar = c;
        }
        return gbVar;
    }

    private gb(Context context) {
        super(context, new t() {
            public final String a(Context context) {
                return gj.a(context).b.getString("gcm.senderIds", BuildConfig.FLAVOR);
            }

            public final void a(Context context, String str) {
                p.a(gj.a(context).b, "gcm.senderIds", str);
            }

            public final String b(Context context) {
                return gj.a(context).b.getString("gcm.regId", BuildConfig.FLAVOR);
            }

            public final void b(Context context, String str) {
                p.a(gj.a(context).b, "gcm.regId", str);
            }

            public final boolean c(Context context) {
                return gj.a(context).b.getBoolean("gcm.stale", true);
            }

            public final void a(Context context, boolean z) {
                p.a(gj.a(context).b, "gcm.stale", z);
            }

            public final int d(Context context) {
                return gj.a(context).b.getInt("gcm.appVersion", Integer.MIN_VALUE);
            }

            public final void a(Context context, int i) {
                p.a(gj.a(context).b, "gcm.appVersion", i);
            }

            public final boolean e(Context context) {
                return gj.a(context).b.getBoolean("gcm.onServer", false);
            }

            public final void b(Context context, boolean z) {
                gj.a(context).a(z);
            }

            public final long f(Context context) {
                return gj.a(context).b.getLong("gcm.onServerExpirationTime", 0);
            }

            public final void a(Context context, long j) {
                Editor edit = gj.a(context).b.edit();
                edit.putLong("gcm.onServerExpirationTime", j);
                edit.commit();
            }

            public final int g(Context context) {
                return gj.a(context).b.getInt("gcm.backoff", 0);
            }

            public final void b(Context context, int i) {
                p.a(gj.a(context).b, "gcm.backoff", i);
            }
        });
    }

    final void e(String str) {
        if (str != null && str.length() > 0) {
            String[] strArr = new String[]{str};
            super.a(this.a);
            super.a(strArr[0]);
        }
    }

    protected final void a(Context context, String str) {
        new Object[1][0] = str;
        fz.a(context).a(str);
    }

    protected final void b(String str) {
        new Object[1][0] = str;
        a();
    }

    protected final boolean a(Context context, Intent intent) {
        Object[] objArr = new Object[]{intent, intent.getExtras()};
        String stringExtra = intent.getStringExtra("fiverocks");
        if (stringExtra == null) {
            return false;
        }
        if (gc.a(context).f()) {
            fy fyVar = fz.a(context).g;
            a a = fyVar.a(eb.APP, "push_ignore");
            a.s = new ef(null, null, stringExtra);
            fyVar.a(a);
            return true;
        }
        String stringExtra2 = intent.getStringExtra(String.TITLE);
        String stringExtra3 = intent.getStringExtra(String.MESSAGE);
        if (stringExtra3 != null) {
            boolean z;
            Bundle extras = intent.getExtras();
            Object obj = extras.get("rich");
            Object obj2 = extras.get("sound");
            Object obj3 = extras.get("important");
            String string = extras.getString("payload");
            Object obj4 = extras.get("always");
            obj4 = (TapjoyConstants.TJC_TRUE.equals(obj4) || Boolean.TRUE.equals(obj4)) ? 1 : null;
            Object obj5 = extras.get("repeatable");
            if (TapjoyConstants.TJC_TRUE.equals(obj5) || Boolean.TRUE.equals(obj5)) {
                z = true;
            } else {
                z = false;
            }
            String string2 = extras.getString("placement");
            int b = b(extras.get("nid"));
            if (!(obj4 == null && fz.a(context).d())) {
                String a2 = ct.a(stringExtra2);
                boolean a3 = a(obj);
                boolean a4 = a(obj2);
                a(obj3);
                Notification a5 = a(context, stringExtra, a2, stringExtra3, a3, a4, string, string2, b);
                fz a6 = fz.a(context);
                long currentTimeMillis = System.currentTimeMillis();
                a6.b(context);
                if (a6.f.a(stringExtra, currentTimeMillis, z)) {
                    fyVar = a6.g;
                    a a7 = fyVar.a(eb.APP, "push_show");
                    a7.s = new ef(null, null, stringExtra);
                    fyVar.a(a7);
                    obj4 = 1;
                } else {
                    obj4 = null;
                }
                if (obj4 != null) {
                    ((NotificationManager) context.getSystemService("notification")).notify(b, a5);
                }
            }
        }
        return true;
    }

    private static boolean a(Object obj) {
        return Boolean.TRUE.equals(obj) || TapjoyConstants.TJC_TRUE.equals(obj);
    }

    private static int b(Object obj) {
        if (obj instanceof Number) {
            return ((Number) obj).intValue();
        }
        if (obj instanceof String) {
            try {
                return Integer.parseInt((String) obj);
            } catch (NumberFormatException e) {
            }
        }
        return 0;
    }

    protected final void a(int i) {
        new Object[1][0] = Integer.valueOf(i);
    }

    protected final boolean c(String str) {
        new Object[1][0] = str;
        return true;
    }

    protected final boolean d(String str) {
        new Object[1][0] = str;
        return false;
    }

    private static Notification a(Context context, String str, String str2, String str3, boolean z, boolean z2, String str4, String str5, int i) {
        Bitmap bitmap = null;
        Intent intent = new Intent(context.getApplicationContext(), TapjoyReceiver.class);
        intent.setAction("com.tapjoy.PUSH_CLICK");
        intent.putExtra("com.tapjoy.PUSH_ID", str);
        if (str4 != null) {
            intent.putExtra(Tapjoy.INTENT_EXTRA_PUSH_PAYLOAD, str4);
        }
        if (str5 != null) {
            intent.putExtra("com.tapjoy.PUSH_PLACEMENT", str5);
        }
        int i2 = 134217728;
        if (VERSION.SDK_INT == 19) {
            i2 = 268435456;
        }
        PendingIntent broadcast = PendingIntent.getBroadcast(context.getApplicationContext(), i, intent, i2);
        if (broadcast == null) {
            return null;
        }
        PackageManager packageManager = context.getPackageManager();
        try {
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 128);
            if (str2.length() == 0) {
                str2 = packageManager.getApplicationLabel(applicationInfo);
            } else if (z) {
                str2 = Html.fromHtml(str2);
            }
            if (z) {
                str3 = Html.fromHtml(str3);
            }
            i2 = a(applicationInfo.metaData, "com.tapjoy.notification.icon", context);
            if (i2 == 0) {
                i2 = applicationInfo.icon != 0 ? applicationInfo.icon : 17301651;
            }
            int a = a(applicationInfo.metaData, "com.tapjoy.notification.icon.large", context);
            if (a != 0) {
                bitmap = BitmapFactory.decodeResource(context.getResources(), a);
            }
            d dVar = new d(context);
            dVar.r.icon = i2;
            dVar.r.tickerText = str2;
            dVar.b = str2;
            dVar.c = str3;
            dVar.d = broadcast;
            Notification notification = dVar.r;
            notification.flags |= 16;
            l cVar = new c();
            cVar.e = str2;
            cVar.a = str3;
            d a2 = dVar.a(cVar);
            if (z2) {
                a2.r.defaults = 1;
            }
            if (bitmap != null) {
                a2.g = bitmap;
            }
            return a.a.a(a2);
        } catch (NameNotFoundException e) {
            return null;
        }
    }

    private static int a(Bundle bundle, String str, Context context) {
        if (bundle != null) {
            Object obj = bundle.get(str);
            if (obj instanceof Integer) {
                int intValue = ((Integer) obj).intValue();
                try {
                    if ("drawable".equals(context.getResources().getResourceTypeName(intValue))) {
                        return intValue;
                    }
                } catch (NotFoundException e) {
                }
            }
            if (obj != null) {
                String str2 = "meta-data of {} invalid";
                Object[] objArr = new Object[]{str};
                if (fw.a) {
                    ac.a(4, GNSAdapterTapjoyRewardVideoAd.TAG, str2, objArr);
                }
            }
        }
        return 0;
    }
}
