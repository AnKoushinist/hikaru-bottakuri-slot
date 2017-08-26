package com.google.firebase.messaging;

import android.app.PendingIntent;
import android.app.PendingIntent.CanceledException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.google.firebase.iid.b;
import com.google.firebase.iid.g;
import com.tapjoy.TJAdUnitConstants.String;
import java.util.Iterator;
import twitter4j.TwitterResponse;

public class FirebaseMessagingService extends b {
    static void a(Bundle bundle) {
        Iterator it = bundle.keySet().iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            if (str != null && str.startsWith("google.c.")) {
                it.remove();
            }
        }
    }

    static boolean b(Bundle bundle) {
        return bundle == null ? false : "1".equals(bundle.getString("google.c.a.e"));
    }

    private void d(Intent intent) {
        PendingIntent pendingIntent = (PendingIntent) intent.getParcelableExtra("pending_intent");
        if (pendingIntent != null) {
            try {
                pendingIntent.send();
            } catch (CanceledException e) {
                Log.e("FirebaseMessaging", "Notification pending intent canceled");
            }
        }
        if (b(intent.getExtras())) {
            c.b(this, intent);
        }
    }

    private void e(Intent intent) {
        String stringExtra = intent.getStringExtra("message_type");
        if (stringExtra == null) {
            stringExtra = "gcm";
        }
        Object obj = -1;
        switch (stringExtra.hashCode()) {
            case -2062414158:
                if (stringExtra.equals("deleted_messages")) {
                    obj = 1;
                    break;
                }
                break;
            case 102161:
                if (stringExtra.equals("gcm")) {
                    obj = null;
                    break;
                }
                break;
            case 814694033:
                if (stringExtra.equals("send_error")) {
                    obj = 3;
                    break;
                }
                break;
            case 814800675:
                if (stringExtra.equals("send_event")) {
                    obj = 2;
                    break;
                }
                break;
        }
        switch (obj) {
            case TwitterResponse.NONE /*0*/:
                if (b(intent.getExtras())) {
                    c.a(this, intent);
                }
                f(intent);
                return;
            case TwitterResponse.READ /*1*/:
                a();
                return;
            case TwitterResponse.READ_WRITE /*2*/:
                a(intent.getStringExtra("google.message_id"));
                return;
            case TwitterResponse.READ_WRITE_DIRECTMESSAGES /*3*/:
                a(g(intent), new a(intent.getStringExtra(String.VIDEO_ERROR)));
                return;
            default:
                String str = "FirebaseMessaging";
                String str2 = "Received message with unknown type: ";
                stringExtra = String.valueOf(stringExtra);
                Log.w(str, stringExtra.length() != 0 ? str2.concat(stringExtra) : new String(str2));
                return;
        }
    }

    private void f(Intent intent) {
        Bundle extras = intent.getExtras();
        extras.remove("android.support.content.wakelockid");
        if (b.a(extras)) {
            if (!b.b((Context) this)) {
                b.a((Context) this).b(extras);
                return;
            } else if (b(extras)) {
                c.d(this, intent);
            }
        }
        a(new RemoteMessage(extras));
    }

    private String g(Intent intent) {
        String stringExtra = intent.getStringExtra("google.message_id");
        return stringExtra == null ? intent.getStringExtra("message_id") : stringExtra;
    }

    public void a() {
    }

    public void a(RemoteMessage remoteMessage) {
    }

    public void a(String str) {
    }

    public void a(String str, Exception exception) {
    }

    public boolean a(Intent intent) {
        if (!"com.google.firebase.messaging.NOTIFICATION_OPEN".equals(intent.getAction())) {
            return false;
        }
        d(intent);
        return true;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void b(android.content.Intent r5) {
        /*
        r4 = this;
        r0 = r5.getAction();
        if (r0 != 0) goto L_0x0008;
    L_0x0006:
        r0 = "";
    L_0x0008:
        r1 = -1;
        r2 = r0.hashCode();
        switch(r2) {
            case 75300319: goto L_0x0038;
            case 366519424: goto L_0x002e;
            default: goto L_0x0010;
        };
    L_0x0010:
        r0 = r1;
    L_0x0011:
        switch(r0) {
            case 0: goto L_0x0042;
            case 1: goto L_0x0046;
            default: goto L_0x0014;
        };
    L_0x0014:
        r1 = "FirebaseMessaging";
        r2 = "Unknown intent action: ";
        r0 = r5.getAction();
        r0 = java.lang.String.valueOf(r0);
        r3 = r0.length();
        if (r3 == 0) goto L_0x0054;
    L_0x0026:
        r0 = r2.concat(r0);
    L_0x002a:
        android.util.Log.d(r1, r0);
    L_0x002d:
        return;
    L_0x002e:
        r2 = "com.google.android.c2dm.intent.RECEIVE";
        r0 = r0.equals(r2);
        if (r0 == 0) goto L_0x0010;
    L_0x0036:
        r0 = 0;
        goto L_0x0011;
    L_0x0038:
        r2 = "com.google.firebase.messaging.NOTIFICATION_DISMISS";
        r0 = r0.equals(r2);
        if (r0 == 0) goto L_0x0010;
    L_0x0040:
        r0 = 1;
        goto L_0x0011;
    L_0x0042:
        r4.e(r5);
        goto L_0x002d;
    L_0x0046:
        r0 = r5.getExtras();
        r0 = b(r0);
        if (r0 == 0) goto L_0x002d;
    L_0x0050:
        com.google.firebase.messaging.c.c(r4, r5);
        goto L_0x002d;
    L_0x0054:
        r0 = new java.lang.String;
        r0.<init>(r2);
        goto L_0x002a;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.messaging.FirebaseMessagingService.b(android.content.Intent):void");
    }

    protected Intent c(Intent intent) {
        return g.a().c();
    }
}
