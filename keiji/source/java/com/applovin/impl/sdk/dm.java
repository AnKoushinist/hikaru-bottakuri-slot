package com.applovin.impl.sdk;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import com.applovin.impl.adview.v;
import com.applovin.impl.sdk.AppLovinAdImpl.AdTarget;
import com.applovin.impl.sdk.AppLovinAdImpl.Builder;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdType;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkUtils;
import com.d.a.a.c;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.Map;
import java.util.Map.Entry;
import org.cocos2dx.lib.BuildConfig;

public class dm extends AppLovinSdkUtils {
    private static final char[] a = "0123456789abcdef".toCharArray();
    private static final char[] b = "-'".toCharArray();

    public static float a(float f) {
        return 1000.0f * f;
    }

    public static Bitmap a(File file, int i) {
        FileInputStream fileInputStream;
        FileInputStream fileInputStream2;
        FileInputStream fileInputStream3;
        Throwable th;
        int i2 = 1;
        FileInputStream fileInputStream4 = null;
        try {
            Options options = new Options();
            options.inJustDecodeBounds = true;
            fileInputStream = new FileInputStream(file);
            try {
                BitmapFactory.decodeStream(fileInputStream, null, options);
                fileInputStream.close();
                if (options.outHeight > i || options.outWidth > i) {
                    i2 = (int) Math.pow(2.0d, (double) ((int) Math.ceil(Math.log(((double) i) / ((double) Math.max(options.outHeight, options.outWidth))) / Math.log(0.5d))));
                }
                Options options2 = new Options();
                options2.inSampleSize = i2;
                InputStream fileInputStream5 = new FileInputStream(file);
                try {
                    Bitmap decodeStream = BitmapFactory.decodeStream(fileInputStream5, null, options2);
                    fileInputStream5.close();
                    try {
                        fileInputStream.close();
                        fileInputStream5.close();
                        return decodeStream;
                    } catch (Exception e) {
                        return decodeStream;
                    }
                } catch (Exception e2) {
                    InputStream inputStream = fileInputStream5;
                    fileInputStream2 = fileInputStream;
                    try {
                        fileInputStream2.close();
                        fileInputStream3.close();
                    } catch (Exception e3) {
                    }
                    return null;
                } catch (Throwable th2) {
                    th = th2;
                    InputStream inputStream2 = fileInputStream5;
                    try {
                        fileInputStream.close();
                        fileInputStream4.close();
                    } catch (Exception e4) {
                    }
                    throw th;
                }
            } catch (Exception e5) {
                fileInputStream3 = null;
                fileInputStream2 = fileInputStream;
                fileInputStream2.close();
                fileInputStream3.close();
                return null;
            } catch (Throwable th3) {
                th = th3;
                fileInputStream.close();
                fileInputStream4.close();
                throw th;
            }
        } catch (Exception e6) {
            fileInputStream3 = null;
            fileInputStream2 = null;
            fileInputStream2.close();
            fileInputStream3.close();
            return null;
        } catch (Throwable th4) {
            th = th4;
            fileInputStream = null;
            fileInputStream.close();
            fileInputStream4.close();
            throw th;
        }
    }

    public static AppLovinAdImpl a() {
        return new Builder().a(BuildConfig.FLAVOR).a(AppLovinAdSize.a).a(AppLovinAdType.a).b(BuildConfig.FLAVOR).a(AdTarget.DEFAULT).a(v.WhiteXOnOpaqueBlack).a(0.0f).b(0.0f).a(0).a(-1).c(BuildConfig.FLAVOR).a();
    }

    public static String a(String str) {
        return (str == null || str.length() <= 4) ? "NOKEY" : str.substring(str.length() - 4);
    }

    public static String a(String str, AppLovinSdkImpl appLovinSdkImpl) {
        return a(str, (Integer) appLovinSdkImpl.a(cb.n), (String) appLovinSdkImpl.a(cb.m));
    }

    private static String a(String str, Integer num, String str2) {
        if (str2 == null) {
            throw new IllegalArgumentException("No algorithm specified");
        } else if (str == null || str.length() < 1) {
            return BuildConfig.FLAVOR;
        } else {
            if (str2.length() < 1 || "none".equals(str2)) {
                return str;
            }
            try {
                MessageDigest instance = MessageDigest.getInstance(str2);
                instance.update(str.getBytes(c.DEFAULT_CHARSET));
                str = a(instance.digest());
                return (str == null || num.intValue() <= 0) ? str : str.substring(0, Math.min(num.intValue(), str.length()));
            } catch (Throwable e) {
                throw new RuntimeException("Unknown algorithm \"" + str2 + "\"", e);
            } catch (Throwable e2) {
                throw new RuntimeException("Programming error: UTF-8 is not know encoding", e2);
            }
        }
    }

    public static String a(String str, String str2) {
        if (str == null) {
            str = BuildConfig.FLAVOR;
        }
        return str2.replace("{PLACEMENT}", c(str));
    }

    static String a(Map map) {
        if (map == null || map.isEmpty()) {
            return BuildConfig.FLAVOR;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (Entry entry : map.entrySet()) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append("&");
            }
            stringBuilder.append(entry.getKey()).append('=').append(entry.getValue());
        }
        return stringBuilder.toString();
    }

    public static String a(byte[] bArr) {
        if (bArr == null) {
            throw new IllegalArgumentException("No data specified");
        }
        char[] cArr = new char[(bArr.length * 2)];
        for (int i = 0; i < bArr.length; i++) {
            cArr[i * 2] = a[(bArr[i] & 240) >>> 4];
            cArr[(i * 2) + 1] = a[bArr[i] & 15];
        }
        return new String(cArr);
    }

    public static boolean a(AppLovinSdk appLovinSdk, String str) {
        for (String startsWith : ((String) ((AppLovinSdkImpl) appLovinSdk).a(cb.C)).split(",")) {
            if (str.startsWith(startsWith)) {
                return true;
            }
        }
        return false;
    }

    public static long b(float f) {
        return (long) Math.round(f);
    }

    public static String b(String str) {
        return a(str, Integer.valueOf(-1), "SHA-1");
    }

    public static long c(float f) {
        return b(a(f));
    }

    static String c(String str) {
        if (!AppLovinSdkUtils.d(str)) {
            return BuildConfig.FLAVOR;
        }
        try {
            return URLEncoder.encode(str, c.DEFAULT_CHARSET);
        } catch (Throwable e) {
            throw new UnsupportedOperationException(e);
        }
    }
}
