package jp.co.mediasdk.android;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.cocos2dx.lib.BuildConfig;

public class Hash {
    public static byte[] a(String str, String str2) {
        Key secretKeySpec = new SecretKeySpec(str2.getBytes(), "HmacSHA512");
        try {
            Mac instance = Mac.getInstance("HmacSHA512");
            try {
                instance.init(secretKeySpec);
                return instance.doFinal(str.getBytes());
            } catch (InvalidKeyException e) {
                e.printStackTrace();
                LoggerBase.a(String.format("[%s::%s] failed to mac.init().", new Object[]{Hash.class, "HmacSHA1"}));
                return null;
            }
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
            LoggerBase.a(String.format("[%s::%s] failed to Mac.getInstance().", new Object[]{Hash.class, "HmacSHA512"}));
            return null;
        }
    }

    public static byte[] a(String str) {
        byte[] bArr = null;
        if (str == null || str.length() == 0) {
            LoggerBase.a(Hash.class, "md5", "input stream is null or zero length.", new Object[0]);
            return bArr;
        }
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(str.getBytes());
            return instance.digest();
        } catch (NoSuchAlgorithmException e) {
            LoggerBase.a(Hash.class, "md5", "no such algorithm 'MD5'.", new Object[0]);
            return bArr;
        }
    }

    public static byte[] b(String str) {
        byte[] bArr = null;
        if (str == null || str.length() == 0) {
            LoggerBase.a(Hash.class, "sha1", "input stream is null or zero length.", new Object[0]);
            return bArr;
        }
        try {
            MessageDigest instance = MessageDigest.getInstance("SHA-1");
            instance.update(str.getBytes());
            return instance.digest();
        } catch (NoSuchAlgorithmException e) {
            LoggerBase.a(Hash.class, "sha1", "no such algorithm 'sha1'.", new Object[0]);
            return bArr;
        }
    }

    public static byte[] c(String str) {
        byte[] bArr = null;
        if (str == null || str.length() == 0) {
            LoggerBase.a(Hash.class, "sha512", "input stream is null or zero length.", new Object[0]);
            return bArr;
        }
        try {
            MessageDigest instance = MessageDigest.getInstance("SHA-512");
            instance.update(str.getBytes());
            return instance.digest();
        } catch (NoSuchAlgorithmException e) {
            LoggerBase.a(Hash.class, "sha512", "no such algorithm 'sha512'.", new Object[0]);
            return bArr;
        }
    }

    public static String a(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < bArr.length; i++) {
            if ((bArr[i] & 255) < 16) {
                stringBuffer.append("0" + Integer.toHexString(bArr[i] & 255));
            } else {
                stringBuffer.append(Integer.toHexString(bArr[i] & 255));
            }
        }
        return stringBuffer.toString();
    }

    public static String b(String str, String str2) {
        if (str == null || str2 == null || str.isEmpty() || str2.isEmpty()) {
            return BuildConfig.FLAVOR;
        }
        return a(a(str, str2));
    }
}
