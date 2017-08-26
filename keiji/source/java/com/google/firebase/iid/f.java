package com.google.firebase.iid;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.ConditionVariable;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcelable;
import android.os.Process;
import android.os.RemoteException;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import com.d.a.a.c;
import com.google.android.gms.iid.MessengerCompat;
import com.tapjoy.TJAdUnitConstants.String;
import java.io.IOException;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import org.cocos2dx.lib.GameControllerDelegate;

public class f {
    static String a = null;
    static int b = 0;
    static int c = 0;
    static int d = 0;
    Context e;
    Map<String, Object> f = new HashMap();
    Messenger g;
    Messenger h;
    MessengerCompat i;
    PendingIntent j;
    long k;
    long l;
    int m;
    int n;
    long o;

    public f(Context context) {
        this.e = context;
    }

    public static String a(Context context) {
        if (a != null) {
            return a;
        }
        b = Process.myUid();
        PackageManager packageManager = context.getPackageManager();
        for (ResolveInfo resolveInfo : packageManager.queryIntentServices(new Intent("com.google.android.c2dm.intent.REGISTER"), 0)) {
            if (packageManager.checkPermission("com.google.android.c2dm.permission.RECEIVE", resolveInfo.serviceInfo.packageName) == 0) {
                try {
                    ApplicationInfo applicationInfo = packageManager.getApplicationInfo(resolveInfo.serviceInfo.packageName, 0);
                    Log.w("InstanceID/Rpc", "Found " + applicationInfo.uid);
                    c = applicationInfo.uid;
                    a = resolveInfo.serviceInfo.packageName;
                    return a;
                } catch (NameNotFoundException e) {
                }
            } else {
                String valueOf = String.valueOf(resolveInfo.serviceInfo.packageName);
                String valueOf2 = String.valueOf("com.google.android.c2dm.intent.REGISTER");
                Log.w("InstanceID/Rpc", new StringBuilder((String.valueOf(valueOf).length() + 56) + String.valueOf(valueOf2).length()).append("Possible malicious package ").append(valueOf).append(" declares ").append(valueOf2).append(" without permission").toString());
            }
        }
        Log.w("InstanceID/Rpc", "Failed to resolve REGISTER intent, falling back");
        ApplicationInfo applicationInfo2;
        try {
            applicationInfo2 = packageManager.getApplicationInfo("com.google.android.gms", 0);
            a = applicationInfo2.packageName;
            c = applicationInfo2.uid;
            return a;
        } catch (NameNotFoundException e2) {
            try {
                applicationInfo2 = packageManager.getApplicationInfo("com.google.android.gsf", 0);
                a = applicationInfo2.packageName;
                c = applicationInfo2.uid;
                return a;
            } catch (NameNotFoundException e3) {
                Log.w("InstanceID/Rpc", "Both Google Play Services and legacy GSF package are missing");
                return null;
            }
        }
    }

    static String a(KeyPair keyPair, String... strArr) {
        String str = null;
        try {
            byte[] bytes = TextUtils.join("\n", strArr).getBytes(c.DEFAULT_CHARSET);
            try {
                PrivateKey privateKey = keyPair.getPrivate();
                Signature instance = Signature.getInstance(privateKey instanceof RSAPrivateKey ? "SHA256withRSA" : "SHA256withECDSA");
                instance.initSign(privateKey);
                instance.update(bytes);
                str = FirebaseInstanceId.a(instance.sign());
            } catch (Throwable e) {
                Log.e("InstanceID/Rpc", "Unable to sign registration request", e);
            }
        } catch (Throwable e2) {
            Log.e("InstanceID/Rpc", "Unable to encode string", e2);
        }
        return str;
    }

    private void a(Object obj) {
        synchronized (getClass()) {
            for (String str : this.f.keySet()) {
                Object obj2 = this.f.get(str);
                this.f.put(str, obj);
                a(obj2, obj);
            }
        }
    }

    private void a(Object obj, Object obj2) {
        if (obj instanceof ConditionVariable) {
            ((ConditionVariable) obj).open();
        }
        if (obj instanceof Messenger) {
            Messenger messenger = (Messenger) obj;
            Message obtain = Message.obtain();
            obtain.obj = obj2;
            try {
                messenger.send(obtain);
            } catch (RemoteException e) {
                String valueOf = String.valueOf(e);
                Log.w("InstanceID/Rpc", new StringBuilder(String.valueOf(valueOf).length() + 24).append("Failed to send response ").append(valueOf).toString());
            }
        }
    }

    private void a(String str) {
        if ("com.google.android.gsf".equals(a)) {
            this.m++;
            if (this.m >= 3) {
                if (this.m == 3) {
                    this.n = new Random().nextInt(GameControllerDelegate.THUMBSTICK_LEFT_X) + GameControllerDelegate.THUMBSTICK_LEFT_X;
                }
                this.n *= 2;
                this.o = SystemClock.elapsedRealtime() + ((long) this.n);
                Log.w("InstanceID/Rpc", new StringBuilder(String.valueOf(str).length() + 31).append("Backoff due to ").append(str).append(" for ").append(this.n).toString());
            }
        }
    }

    private void a(String str, Object obj) {
        synchronized (getClass()) {
            Object obj2 = this.f.get(str);
            this.f.put(str, obj);
            a(obj2, obj);
        }
    }

    private Intent b(Bundle bundle, KeyPair keyPair) {
        Intent intent;
        ConditionVariable conditionVariable = new ConditionVariable();
        String b = b();
        synchronized (getClass()) {
            this.f.put(b, conditionVariable);
        }
        a(bundle, keyPair, b);
        conditionVariable.block(30000);
        synchronized (getClass()) {
            Object remove = this.f.remove(b);
            if (remove instanceof Intent) {
                intent = (Intent) remove;
            } else if (remove instanceof String) {
                throw new IOException((String) remove);
            } else {
                String valueOf = String.valueOf(remove);
                Log.w("InstanceID/Rpc", new StringBuilder(String.valueOf(valueOf).length() + 12).append("No response ").append(valueOf).toString());
                throw new IOException("TIMEOUT");
            }
        }
        return intent;
    }

    public static synchronized String b() {
        String num;
        synchronized (f.class) {
            int i = d;
            d = i + 1;
            num = Integer.toString(i);
        }
        return num;
    }

    Intent a(Bundle bundle, KeyPair keyPair) {
        Intent b = b(bundle, keyPair);
        if (b == null || !b.hasExtra("google.messenger")) {
            return b;
        }
        b = b(bundle, keyPair);
        return (b == null || !b.hasExtra("google.messenger")) ? b : null;
    }

    void a() {
        if (this.g == null) {
            a(this.e);
            this.g = new Messenger(new Handler(this, Looper.getMainLooper()) {
                final /* synthetic */ f a;

                public void handleMessage(Message message) {
                    this.a.a(message);
                }
            });
        }
    }

    synchronized void a(Intent intent) {
        if (this.j == null) {
            Intent intent2 = new Intent();
            intent2.setPackage("com.google.example.invalidpackage");
            this.j = PendingIntent.getBroadcast(this.e, 0, intent2, 0);
        }
        intent.putExtra("app", this.j);
    }

    protected void a(Intent intent, String str) {
        this.k = SystemClock.elapsedRealtime();
        intent.putExtra("kid", new StringBuilder(String.valueOf(str).length() + 5).append("|ID|").append(str).append("|").toString());
        intent.putExtra("X-kid", new StringBuilder(String.valueOf(str).length() + 5).append("|ID|").append(str).append("|").toString());
        boolean equals = "com.google.android.gsf".equals(a);
        if (Log.isLoggable("InstanceID/Rpc", 3)) {
            String valueOf = String.valueOf(intent.getExtras());
            Log.d("InstanceID/Rpc", new StringBuilder(String.valueOf(valueOf).length() + 8).append("Sending ").append(valueOf).toString());
        }
        if (equals) {
            this.e.startService(intent);
            return;
        }
        intent.putExtra("google.messenger", this.g);
        if (!(this.h == null && this.i == null)) {
            Message obtain = Message.obtain();
            obtain.obj = intent;
            try {
                if (this.h != null) {
                    this.h.send(obtain);
                    return;
                } else {
                    this.i.b(obtain);
                    return;
                }
            } catch (RemoteException e) {
                if (Log.isLoggable("InstanceID/Rpc", 3)) {
                    Log.d("InstanceID/Rpc", "Messenger failed, fallback to startService");
                }
            }
        }
        this.e.startService(intent);
    }

    public void a(Bundle bundle, KeyPair keyPair, String str) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (this.o == 0 || elapsedRealtime > this.o) {
            a();
            if (a == null) {
                throw new IOException("MISSING_INSTANCEID_SERVICE");
            }
            this.k = SystemClock.elapsedRealtime();
            Intent intent = new Intent("com.google.android.c2dm.intent.REGISTER");
            intent.setPackage(a);
            bundle.putString("gmsv", Integer.toString(FirebaseInstanceId.a(this.e, a(this.e))));
            bundle.putString("osv", Integer.toString(VERSION.SDK_INT));
            bundle.putString("app_ver", Integer.toString(FirebaseInstanceId.b(this.e)));
            bundle.putString("app_ver_name", FirebaseInstanceId.c(this.e));
            bundle.putString("cliv", "fiid-10084000");
            bundle.putString("appid", FirebaseInstanceId.a(keyPair));
            String a = FirebaseInstanceId.a(this.e);
            if (a != null) {
                bundle.putString("gmp_app_id", a);
            }
            bundle.putString("pub2", FirebaseInstanceId.a(keyPair.getPublic().getEncoded()));
            bundle.putString("sig", a(keyPair, this.e.getPackageName(), a));
            intent.putExtras(bundle);
            a(intent);
            a(intent, str);
            return;
        }
        elapsedRealtime = this.o - elapsedRealtime;
        Log.w("InstanceID/Rpc", "Backoff mode, next request attempt: " + elapsedRealtime + " interval: " + this.n);
        throw new IOException("RETRY_LATER");
    }

    public void a(Message message) {
        if (message != null) {
            if (message.obj instanceof Intent) {
                Intent intent = (Intent) message.obj;
                intent.setExtrasClassLoader(MessengerCompat.class.getClassLoader());
                if (intent.hasExtra("google.messenger")) {
                    Parcelable parcelableExtra = intent.getParcelableExtra("google.messenger");
                    if (parcelableExtra instanceof MessengerCompat) {
                        this.i = (MessengerCompat) parcelableExtra;
                    }
                    if (parcelableExtra instanceof Messenger) {
                        this.h = (Messenger) parcelableExtra;
                    }
                }
                d((Intent) message.obj);
                return;
            }
            Log.w("InstanceID/Rpc", "Dropping invalid message");
        }
    }

    String b(Intent intent) {
        if (intent == null) {
            throw new IOException("SERVICE_NOT_AVAILABLE");
        }
        String stringExtra = intent.getStringExtra("registration_id");
        if (stringExtra == null) {
            stringExtra = intent.getStringExtra("unregistered");
        }
        if (stringExtra != null) {
            return stringExtra;
        }
        stringExtra = intent.getStringExtra(String.VIDEO_ERROR);
        if (stringExtra != null) {
            throw new IOException(stringExtra);
        }
        String valueOf = String.valueOf(intent.getExtras());
        Log.w("InstanceID/Rpc", new StringBuilder(String.valueOf(valueOf).length() + 29).append("Unexpected response from GCM ").append(valueOf).toString(), new Throwable());
        throw new IOException("SERVICE_NOT_AVAILABLE");
    }

    void c(Intent intent) {
        String stringExtra = intent.getStringExtra(String.VIDEO_ERROR);
        if (stringExtra == null) {
            String valueOf = String.valueOf(intent.getExtras());
            Log.w("InstanceID/Rpc", new StringBuilder(String.valueOf(valueOf).length() + 49).append("Unexpected response, no error or registration id ").append(valueOf).toString());
            return;
        }
        if (Log.isLoggable("InstanceID/Rpc", 3)) {
            valueOf = "InstanceID/Rpc";
            String str = "Received InstanceID error ";
            String valueOf2 = String.valueOf(stringExtra);
            Log.d(valueOf, valueOf2.length() != 0 ? str.concat(valueOf2) : new String(str));
        }
        if (stringExtra.startsWith("|")) {
            String[] split = stringExtra.split("\\|");
            if (!"ID".equals(split[1])) {
                String str2 = "InstanceID/Rpc";
                String str3 = "Unexpected structured response ";
                valueOf2 = String.valueOf(stringExtra);
                Log.w(str2, valueOf2.length() != 0 ? str3.concat(valueOf2) : new String(str3));
            }
            if (split.length > 2) {
                valueOf2 = split[2];
                valueOf = split[3];
                if (valueOf.startsWith(":")) {
                    valueOf = valueOf.substring(1);
                }
            } else {
                valueOf = "UNKNOWN";
                valueOf2 = null;
            }
            intent.putExtra(String.VIDEO_ERROR, valueOf);
        } else {
            valueOf2 = null;
            valueOf = stringExtra;
        }
        if (valueOf2 == null) {
            a((Object) valueOf);
        } else {
            a(valueOf2, (Object) valueOf);
        }
        long longExtra = intent.getLongExtra("Retry-After", 0);
        if (longExtra > 0) {
            this.l = SystemClock.elapsedRealtime();
            this.n = ((int) longExtra) * GameControllerDelegate.THUMBSTICK_LEFT_X;
            this.o = SystemClock.elapsedRealtime() + ((long) this.n);
            Log.w("InstanceID/Rpc", "Explicit request from server to backoff: " + this.n);
        } else if ("SERVICE_NOT_AVAILABLE".equals(valueOf) || "AUTHENTICATION_FAILED".equals(valueOf)) {
            a(valueOf);
        }
    }

    void d(Intent intent) {
        if (intent != null) {
            String stringExtra;
            String str;
            if ("com.google.android.c2dm.intent.REGISTRATION".equals(intent.getAction())) {
                stringExtra = intent.getStringExtra("registration_id");
                if (stringExtra == null) {
                    stringExtra = intent.getStringExtra("unregistered");
                }
                if (stringExtra == null) {
                    c(intent);
                    return;
                }
                this.k = SystemClock.elapsedRealtime();
                this.o = 0;
                this.m = 0;
                this.n = 0;
                if (stringExtra.startsWith("|")) {
                    String[] split = stringExtra.split("\\|");
                    if (!"ID".equals(split[1])) {
                        str = "InstanceID/Rpc";
                        String str2 = "Unexpected structured response ";
                        stringExtra = String.valueOf(stringExtra);
                        Log.w(str, stringExtra.length() != 0 ? str2.concat(stringExtra) : new String(str2));
                    }
                    str = split[2];
                    if (split.length > 4) {
                        if ("SYNC".equals(split[3])) {
                            FirebaseInstanceId.d(this.e);
                        } else if ("RST".equals(split[3])) {
                            FirebaseInstanceId.a(this.e, d.a(this.e, null).c());
                            intent.removeExtra("registration_id");
                            a(str, (Object) intent);
                            return;
                        }
                    }
                    stringExtra = split[split.length - 1];
                    if (stringExtra.startsWith(":")) {
                        stringExtra = stringExtra.substring(1);
                    }
                    intent.putExtra("registration_id", stringExtra);
                    stringExtra = str;
                } else {
                    stringExtra = null;
                }
                if (stringExtra != null) {
                    a(stringExtra, (Object) intent);
                } else if (Log.isLoggable("InstanceID/Rpc", 3)) {
                    Log.d("InstanceID/Rpc", "Ignoring response without a request ID");
                }
            } else if (Log.isLoggable("InstanceID/Rpc", 3)) {
                str = "InstanceID/Rpc";
                String str3 = "Unexpected response ";
                stringExtra = String.valueOf(intent.getAction());
                Log.d(str, stringExtra.length() != 0 ? str3.concat(stringExtra) : new String(str3));
            }
        } else if (Log.isLoggable("InstanceID/Rpc", 3)) {
            Log.d("InstanceID/Rpc", "Unexpected response: null");
        }
    }
}
