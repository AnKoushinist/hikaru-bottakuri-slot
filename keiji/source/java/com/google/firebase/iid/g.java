package com.google.firebase.iid;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.support.v4.a.h;
import android.support.v4.f.i;
import android.util.Log;
import java.util.LinkedList;
import java.util.Queue;
import twitter4j.HttpResponseCode;
import twitter4j.TwitterResponse;

public class g {
    private static g c;
    final Queue<Intent> a = new LinkedList();
    final Queue<Intent> b = new LinkedList();
    private final i<String, String> d = new i();
    private Boolean e = null;

    private g() {
    }

    public static PendingIntent a(Context context, int i, Intent intent, int i2) {
        return a(context, i, "com.google.firebase.INSTANCE_ID_EVENT", intent, i2);
    }

    private static PendingIntent a(Context context, int i, String str, Intent intent, int i2) {
        Intent intent2 = new Intent(context, FirebaseInstanceIdInternalReceiver.class);
        intent2.setAction(str);
        intent2.putExtra("wrapped_intent", intent);
        return PendingIntent.getBroadcast(context, i, intent2, i2);
    }

    public static synchronized g a() {
        g gVar;
        synchronized (g.class) {
            if (c == null) {
                c = new g();
            }
            gVar = c;
        }
        return gVar;
    }

    private boolean a(Context context) {
        if (this.e == null) {
            this.e = Boolean.valueOf(context.checkCallingOrSelfPermission("android.permission.WAKE_LOCK") == 0);
        }
        return this.e.booleanValue();
    }

    private int b(Context context, Intent intent) {
        c(context, intent);
        try {
            ComponentName startWakefulService;
            if (a(context)) {
                startWakefulService = h.startWakefulService(context, intent);
            } else {
                startWakefulService = context.startService(intent);
                Log.d("FirebaseInstanceId", "Missing wake lock permission, service start may be delayed");
            }
            if (startWakefulService != null) {
                return -1;
            }
            Log.e("FirebaseInstanceId", "Error while delivering the message: ServiceIntent not found.");
            return HttpResponseCode.NOT_FOUND;
        } catch (Throwable e) {
            Log.e("FirebaseInstanceId", "Error while delivering the message to the serviceIntent", e);
            return HttpResponseCode.UNAUTHORIZED;
        }
    }

    public static PendingIntent b(Context context, int i, Intent intent, int i2) {
        return a(context, i, "com.google.firebase.MESSAGING_EVENT", intent, i2);
    }

    private void c(Context context, Intent intent) {
        String str;
        synchronized (this.d) {
            str = (String) this.d.get(intent.getAction());
        }
        if (str == null) {
            ResolveInfo resolveService = context.getPackageManager().resolveService(intent, 0);
            if (resolveService == null || resolveService.serviceInfo == null) {
                Log.e("FirebaseInstanceId", "Failed to resolve target intent service, skipping classname enforcement");
                return;
            }
            ServiceInfo serviceInfo = resolveService.serviceInfo;
            if (!context.getPackageName().equals(serviceInfo.packageName) || serviceInfo.name == null) {
                String valueOf = String.valueOf(serviceInfo.packageName);
                str = String.valueOf(serviceInfo.name);
                Log.e("FirebaseInstanceId", new StringBuilder((String.valueOf(valueOf).length() + 94) + String.valueOf(str).length()).append("Error resolving target intent service, skipping classname enforcement. Resolved service was: ").append(valueOf).append("/").append(str).toString());
                return;
            }
            str = serviceInfo.name;
            if (str.startsWith(".")) {
                String valueOf2 = String.valueOf(context.getPackageName());
                str = String.valueOf(str);
                str = str.length() != 0 ? valueOf2.concat(str) : new String(valueOf2);
            }
            synchronized (this.d) {
                this.d.put(intent.getAction(), str);
            }
        }
        if (Log.isLoggable("FirebaseInstanceId", 3)) {
            valueOf = "FirebaseInstanceId";
            String str2 = "Restricting intent to a specific service: ";
            valueOf2 = String.valueOf(str);
            Log.d(valueOf, valueOf2.length() != 0 ? str2.concat(valueOf2) : new String(str2));
        }
        intent.setClassName(context.getPackageName(), str);
    }

    public int a(Context context, String str, Intent intent) {
        Object obj = -1;
        switch (str.hashCode()) {
            case -842411455:
                if (str.equals("com.google.firebase.INSTANCE_ID_EVENT")) {
                    obj = null;
                    break;
                }
                break;
            case 41532704:
                if (str.equals("com.google.firebase.MESSAGING_EVENT")) {
                    obj = 1;
                    break;
                }
                break;
        }
        switch (obj) {
            case TwitterResponse.NONE /*0*/:
                this.a.offer(intent);
                break;
            case TwitterResponse.READ /*1*/:
                this.b.offer(intent);
                break;
            default:
                String str2 = "FirebaseInstanceId";
                String str3 = "Unknown service action: ";
                String valueOf = String.valueOf(str);
                Log.w(str2, valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
                return HttpResponseCode.INTERNAL_SERVER_ERROR;
        }
        Intent intent2 = new Intent(str);
        intent2.setPackage(context.getPackageName());
        return b(context, intent2);
    }

    public void a(Context context, Intent intent) {
        a(context, "com.google.firebase.INSTANCE_ID_EVENT", intent);
    }

    public Intent b() {
        return (Intent) this.a.poll();
    }

    public Intent c() {
        return (Intent) this.b.poll();
    }
}
