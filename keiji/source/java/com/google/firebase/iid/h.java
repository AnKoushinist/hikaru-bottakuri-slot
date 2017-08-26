package com.google.firebase.iid;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import com.google.android.gms.common.util.r;
import com.tapjoy.TapjoyConstants;
import java.io.File;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;

class h {
    SharedPreferences a;
    Context b;

    static class a {
        private static final long d = TimeUnit.DAYS.toMillis(7);
        final String a;
        final String b;
        final long c;

        private a(String str, String str2, long j) {
            this.a = str;
            this.b = str2;
            this.c = j;
        }

        static a a(String str) {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            if (!str.startsWith("{")) {
                return new a(str, null, 0);
            }
            try {
                JSONObject jSONObject = new JSONObject(str);
                return new a(jSONObject.getString("token"), jSONObject.getString("appVersion"), jSONObject.getLong(TapjoyConstants.TJC_TIMESTAMP));
            } catch (JSONException e) {
                String valueOf = String.valueOf(e);
                Log.w("InstanceID/Store", new StringBuilder(String.valueOf(valueOf).length() + 23).append("Failed to parse token: ").append(valueOf).toString());
                return null;
            }
        }

        static String a(String str, String str2, long j) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("token", str);
                jSONObject.put("appVersion", str2);
                jSONObject.put(TapjoyConstants.TJC_TIMESTAMP, j);
                return jSONObject.toString();
            } catch (JSONException e) {
                String valueOf = String.valueOf(e);
                Log.w("InstanceID/Store", new StringBuilder(String.valueOf(valueOf).length() + 24).append("Failed to encode token: ").append(valueOf).toString());
                return null;
            }
        }

        boolean b(String str) {
            return System.currentTimeMillis() > this.c + d || !str.equals(this.b);
        }
    }

    public h(Context context) {
        this(context, "com.google.android.gms.appid");
    }

    public h(Context context, String str) {
        this.b = context;
        this.a = context.getSharedPreferences(str, 4);
        String valueOf = String.valueOf(str);
        String valueOf2 = String.valueOf("-no-backup");
        e(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf));
    }

    private String a(String str, String str2) {
        String valueOf = String.valueOf("|S|");
        return new StringBuilder(((String.valueOf(str).length() + 0) + String.valueOf(valueOf).length()) + String.valueOf(str2).length()).append(str).append(valueOf).append(str2).toString();
    }

    private String c(String str, String str2, String str3) {
        String valueOf = String.valueOf("|T|");
        return new StringBuilder((((String.valueOf(str).length() + 1) + String.valueOf(valueOf).length()) + String.valueOf(str2).length()) + String.valueOf(str3).length()).append(str).append(valueOf).append(str2).append("|").append(str3).toString();
    }

    private void e(String str) {
        File file = new File(r.a(this.b), str);
        if (!file.exists()) {
            try {
                if (file.createNewFile() && !b()) {
                    Log.i("InstanceID/Store", "App restored, clearing state");
                    FirebaseInstanceId.a(this.b, this);
                }
            } catch (IOException e) {
                if (Log.isLoggable("InstanceID/Store", 3)) {
                    String str2 = "InstanceID/Store";
                    String str3 = "Error creating file in no backup dir: ";
                    String valueOf = String.valueOf(e.getMessage());
                    Log.d(str2, valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
                }
            }
        }
    }

    private void f(String str) {
        Editor edit = this.a.edit();
        for (String str2 : this.a.getAll().keySet()) {
            if (str2.startsWith(str)) {
                edit.remove(str2);
            }
        }
        edit.commit();
    }

    public SharedPreferences a() {
        return this.a;
    }

    public synchronized a a(String str, String str2, String str3) {
        return a.a(this.a.getString(c(str, str2, str3), null));
    }

    synchronized KeyPair a(String str) {
        KeyPair a;
        a = a.a();
        long currentTimeMillis = System.currentTimeMillis();
        Editor edit = this.a.edit();
        edit.putString(a(str, "|P|"), FirebaseInstanceId.a(a.getPublic().getEncoded()));
        edit.putString(a(str, "|K|"), FirebaseInstanceId.a(a.getPrivate().getEncoded()));
        edit.putString(a(str, "cre"), Long.toString(currentTimeMillis));
        edit.commit();
        return a;
    }

    public synchronized void a(String str, String str2, String str3, String str4, String str5) {
        String a = a.a(str4, str5, System.currentTimeMillis());
        if (a != null) {
            Editor edit = this.a.edit();
            edit.putString(c(str, str2, str3), a);
            edit.commit();
        }
    }

    synchronized void b(String str) {
        f(String.valueOf(str).concat("|"));
    }

    public synchronized void b(String str, String str2, String str3) {
        String c = c(str, str2, str3);
        Editor edit = this.a.edit();
        edit.remove(c);
        edit.commit();
    }

    public synchronized boolean b() {
        return this.a.getAll().isEmpty();
    }

    public synchronized void c() {
        this.a.edit().clear().commit();
    }

    public synchronized void c(String str) {
        f(String.valueOf(str).concat("|T|"));
    }

    public synchronized KeyPair d(String str) {
        KeyPair keyPair;
        Object e;
        String string = this.a.getString(a(str, "|P|"), null);
        String string2 = this.a.getString(a(str, "|K|"), null);
        if (string == null || string2 == null) {
            keyPair = null;
        } else {
            try {
                byte[] decode = Base64.decode(string, 8);
                byte[] decode2 = Base64.decode(string2, 8);
                KeyFactory instance = KeyFactory.getInstance("RSA");
                keyPair = new KeyPair(instance.generatePublic(new X509EncodedKeySpec(decode)), instance.generatePrivate(new PKCS8EncodedKeySpec(decode2)));
            } catch (InvalidKeySpecException e2) {
                e = e2;
                string = String.valueOf(e);
                Log.w("InstanceID/Store", new StringBuilder(String.valueOf(string).length() + 19).append("Invalid key stored ").append(string).toString());
                FirebaseInstanceId.a(this.b, this);
                keyPair = null;
                return keyPair;
            } catch (NoSuchAlgorithmException e3) {
                e = e3;
                string = String.valueOf(e);
                Log.w("InstanceID/Store", new StringBuilder(String.valueOf(string).length() + 19).append("Invalid key stored ").append(string).toString());
                FirebaseInstanceId.a(this.b, this);
                keyPair = null;
                return keyPair;
            }
        }
        return keyPair;
    }
}
