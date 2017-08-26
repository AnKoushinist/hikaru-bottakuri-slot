package jp.gmotech.smaad.video.ad.b;

import android.content.Context;
import android.os.Environment;
import com.d.a.a.c;
import java.io.File;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import org.json.JSONObject;

public class b {
    public static int a(Context context, String str, String str2) {
        return context.getResources().getIdentifier(str, str2, context.getPackageName());
    }

    public static String a(Context context) {
        String externalStorageState = Environment.getExternalStorageState();
        String str = ("mounted".equals(externalStorageState) ? context.getExternalFilesDir(null).getAbsolutePath().toString() : context.getFilesDir().getAbsolutePath().toString()) + File.separator + "SmaAdVideo";
        e(str);
        str = str + File.separator;
        a.a("SmaAdVideoUtil", "[getSmaAdVideoFileDir] state : " + externalStorageState + ", path : " + str);
        return str;
    }

    public static String a(String str) {
        String str2 = null;
        try {
            MessageDigest.getInstance("SHA-256").update(str.getBytes());
            str2 = String.format("%064x", new Object[]{new BigInteger(1, r1.digest())});
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return str2;
    }

    public static String a(JSONObject jSONObject) {
        StringBuilder stringBuilder = null;
        if (jSONObject == null) {
            return stringBuilder == null ? stringBuilder : stringBuilder.toString();
        } else {
            Iterator keys = jSONObject.keys();
            StringBuilder stringBuilder2 = stringBuilder;
            while (keys.hasNext()) {
                StringBuilder stringBuilder3;
                if (stringBuilder2 == null) {
                    stringBuilder3 = new StringBuilder();
                } else {
                    stringBuilder2.append('&');
                    stringBuilder3 = stringBuilder2;
                }
                String str = (String) keys.next();
                try {
                    stringBuilder3.append(URLEncoder.encode(str, c.DEFAULT_CHARSET));
                    stringBuilder3.append('=');
                    if (jSONObject.getString(str) != null) {
                        stringBuilder3.append(URLEncoder.encode(jSONObject.getString(str), c.DEFAULT_CHARSET));
                    }
                    stringBuilder2 = stringBuilder3;
                } catch (Exception e) {
                    e.printStackTrace();
                    stringBuilder2 = stringBuilder3;
                }
            }
            if (stringBuilder2 != null) {
                stringBuilder = stringBuilder2.toString();
            }
            return stringBuilder;
        }
    }

    public static int b(Context context) {
        return "phone".equalsIgnoreCase(context.getResources().getString(a(context, "smaad_screen_type", "string"))) ? 0 : 1;
    }

    public static boolean b(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        File file = new File(str);
        a.a("SmaAdVideoUtil", "[FileLength] " + file.length());
        return file.exists() && file.length() > 0;
    }

    public static void c(String str) {
        if (str != null && !str.isEmpty()) {
            File file = new File(str);
            if (file.exists()) {
                file.delete();
            }
        }
    }

    public static boolean d(String str) {
        return str == null || str.isEmpty();
    }

    private static void e(String str) {
        File file = new File(str);
        if (!file.exists() || !file.isDirectory()) {
            file.mkdirs();
        }
    }
}
