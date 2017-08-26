package jp.reifrontier.silentlogsdkandroid.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.util.Locale;

public class RFLUtils {
    public static boolean isJaLocale() {
        if (Locale.getDefault().getLanguage().equals(Constants.LOCALE_JA)) {
            return true;
        }
        return false;
    }

    public static String getLang() {
        return Locale.getDefault().getLanguage();
    }

    public static int getByteLength(String str, Charset charset) {
        return str.getBytes(charset).length;
    }

    public static boolean isOnline(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static String getMD5(String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(str.getBytes());
            byte[] digest = instance.digest();
            StringBuilder stringBuilder = new StringBuilder();
            for (byte b : digest) {
                int i = b & 255;
                if (i < 16) {
                    stringBuilder.append("0");
                }
                stringBuilder.append(Integer.toHexString(i));
            }
            return stringBuilder.toString();
        } catch (Exception e) {
            return null;
        }
    }
}
