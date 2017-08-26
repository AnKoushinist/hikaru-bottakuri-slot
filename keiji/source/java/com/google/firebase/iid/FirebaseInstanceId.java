package com.google.firebase.iid;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.support.annotation.Keep;
import android.support.v4.f.a;
import android.util.Base64;
import android.util.Log;
import java.io.IOException;
import java.security.KeyPair;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import jp.co.vaz.creator.hikaru2.R;
import org.cocos2dx.lib.BuildConfig;

public class FirebaseInstanceId {
    private static Map<String, FirebaseInstanceId> a = new a();
    private static e b;
    private final com.google.firebase.a c;
    private final d d;
    private final String e = b();

    private FirebaseInstanceId(com.google.firebase.a aVar, d dVar) {
        this.c = aVar;
        this.d = dVar;
        if (this.e == null) {
            throw new IllegalStateException("IID failing to initialize, FirebaseApp is missing project ID");
        }
        FirebaseInstanceIdService.a(this.c.a(), this);
    }

    static int a(Context context, String str) {
        int i = 0;
        try {
            return context.getPackageManager().getPackageInfo(str, 0).versionCode;
        } catch (NameNotFoundException e) {
            String valueOf = String.valueOf(e);
            Log.w("FirebaseInstanceId", new StringBuilder(String.valueOf(valueOf).length() + 23).append("Failed to find package ").append(valueOf).toString());
            return i;
        }
    }

    public static FirebaseInstanceId a() {
        return getInstance(com.google.firebase.a.d());
    }

    static String a(Context context) {
        return a().c.c().b();
    }

    static String a(KeyPair keyPair) {
        try {
            byte[] digest = MessageDigest.getInstance("SHA1").digest(keyPair.getPublic().getEncoded());
            digest[0] = (byte) (((digest[0] & 15) + R.styleable.AppCompatTheme_spinnerStyle) & 255);
            return Base64.encodeToString(digest, 0, 8, 11);
        } catch (NoSuchAlgorithmException e) {
            Log.w("FirebaseInstanceId", "Unexpected error, device missing required alghorithms");
            return null;
        }
    }

    static String a(byte[] bArr) {
        return Base64.encodeToString(bArr, 11);
    }

    static void a(Context context, h hVar) {
        hVar.c();
        Intent intent = new Intent();
        intent.putExtra("CMD", "RST");
        g.a().a(context, intent);
    }

    static int b(Context context) {
        return a(context, context.getPackageName());
    }

    static String c(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (NameNotFoundException e) {
            String valueOf = String.valueOf(e);
            Log.w("FirebaseInstanceId", new StringBuilder(String.valueOf(valueOf).length() + 38).append("Never happens: can't find own package ").append(valueOf).toString());
            return null;
        }
    }

    static void d(Context context) {
        Intent intent = new Intent();
        intent.putExtra("CMD", "SYNC");
        g.a().a(context, intent);
    }

    @Keep
    public static synchronized FirebaseInstanceId getInstance(com.google.firebase.a aVar) {
        FirebaseInstanceId firebaseInstanceId;
        synchronized (FirebaseInstanceId.class) {
            firebaseInstanceId = (FirebaseInstanceId) a.get(aVar.c().b());
            if (firebaseInstanceId == null) {
                d a = d.a(aVar.a(), null);
                if (b == null) {
                    b = new e(a.c());
                }
                firebaseInstanceId = new FirebaseInstanceId(aVar, a);
                a.put(aVar.c().b(), firebaseInstanceId);
            }
        }
        return firebaseInstanceId;
    }

    public String a(String str, String str2) {
        return this.d.b(str, str2, null);
    }

    void a(String str) {
        a d = d();
        if (d == null || d.b(d.e)) {
            throw new IOException("token not available");
        }
        Bundle bundle = new Bundle();
        String str2 = "gcm.topic";
        String valueOf = String.valueOf("/topics/");
        String valueOf2 = String.valueOf(str);
        bundle.putString(str2, valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf));
        d dVar = this.d;
        String str3 = d.a;
        valueOf = String.valueOf("/topics/");
        valueOf2 = String.valueOf(str);
        dVar.b(str3, valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf), bundle);
    }

    String b() {
        String c = this.c.c().c();
        if (c != null) {
            return c;
        }
        c = this.c.c().b();
        if (!c.startsWith("1:")) {
            return c;
        }
        String[] split = c.split(":");
        if (split.length < 2) {
            return null;
        }
        c = split[1];
        return c.isEmpty() ? null : c;
    }

    void b(String str) {
        a d = d();
        if (d == null || d.b(d.e)) {
            throw new IOException("token not available");
        }
        Bundle bundle = new Bundle();
        String str2 = "gcm.topic";
        String valueOf = String.valueOf("/topics/");
        String valueOf2 = String.valueOf(str);
        bundle.putString(str2, valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf));
        d dVar = this.d;
        String str3 = d.a;
        valueOf = String.valueOf("/topics/");
        valueOf2 = String.valueOf(str);
        dVar.a(str3, valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf), bundle);
    }

    public String c() {
        return a(this.d.a());
    }

    a d() {
        return this.d.c().a(BuildConfig.FLAVOR, this.e, "*");
    }

    String e() {
        return a(this.e, "*");
    }

    e f() {
        return b;
    }
}
