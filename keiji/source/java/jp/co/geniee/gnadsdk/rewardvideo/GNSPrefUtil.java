package jp.co.geniee.gnadsdk.rewardvideo;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import android.util.Base64;
import com.d.a.a.c;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.tapjoy.TapjoyConstants;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import jp.co.geniee.gnadsdk.a.f;
import org.cocos2dx.lib.BuildConfig;

class GNSPrefUtil {
    private GNSPrefUtil() {
    }

    public static int a(Context context, String str, int i) {
        return context == null ? i : context.getSharedPreferences("geniee_pref.dat", 0).getInt(str, i);
    }

    public static void b(Context context, String str, int i) {
        if (context != null) {
            Editor edit = context.getSharedPreferences("geniee_pref.dat", 0).edit();
            edit.putInt(str, i);
            edit.commit();
        }
    }

    public static long a(Context context, String str, long j) {
        return context == null ? j : context.getSharedPreferences("geniee_pref.dat", 0).getLong(str, j);
    }

    public static void b(Context context, String str, long j) {
        if (context != null) {
            Editor edit = context.getSharedPreferences("geniee_pref.dat", 0).edit();
            edit.putLong(str, j);
            edit.commit();
        }
    }

    public static boolean a(Context context) {
        return context.getSharedPreferences("geniee_pref.dat", 0).getBoolean("preload", true);
    }

    public static void a(Context context, boolean z) {
        Editor edit = context.getSharedPreferences("geniee_pref.dat", 0).edit();
        edit.putBoolean("preload", z);
        edit.commit();
    }

    public static void a(Context context, int i) {
        Editor edit = context.getSharedPreferences("geniee_pref.dat", 0).edit();
        edit.putInt("timeout", i);
        edit.commit();
    }

    public static void b(Context context, int i) {
        Editor edit = context.getSharedPreferences("geniee_pref.dat", 0).edit();
        edit.putInt(TapjoyConstants.TJC_RETRY, i);
        edit.commit();
    }

    public static int b(Context context) {
        return context.getSharedPreferences("geniee_pref.dat", 0).getInt("show_rate", 100);
    }

    public static void c(Context context, int i) {
        Editor edit = context.getSharedPreferences("geniee_pref.dat", 0).edit();
        edit.putInt("show_rate", i);
        edit.commit();
    }

    public static String c(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("geniee_pref.dat", 0);
        String string = sharedPreferences.getString("useragent", BuildConfig.FLAVOR);
        if (string == null || string.length() <= 0) {
            string = f.a(context);
            if (string == null) {
                string = BuildConfig.FLAVOR;
            }
            Editor edit = sharedPreferences.edit();
            edit.putString("useragent", string);
            edit.commit();
        }
        return string;
    }

    public static String d(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("geniee_pref.dat", 0);
        long j = sharedPreferences.getLong("idfa_time", -1);
        long time = new Date().getTime();
        String str = BuildConfig.FLAVOR;
        if (j == -1 || 60000 <= time - j) {
            try {
                if (Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient") == null) {
                    return BuildConfig.FLAVOR;
                }
                Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(context);
                if (advertisingIdInfo == null) {
                    return BuildConfig.FLAVOR;
                }
                str = advertisingIdInfo.getId();
                boolean isLimitAdTrackingEnabled = advertisingIdInfo.isLimitAdTrackingEnabled();
                if (TextUtils.isEmpty(str)) {
                    return str;
                }
                String str2 = new String(Base64.encode(str.getBytes(), 2));
                Editor edit = sharedPreferences.edit();
                edit.putString("idfa", str2);
                edit.putBoolean("idfa_limit", isLimitAdTrackingEnabled);
                edit.putLong("idfa_time", time);
                edit.commit();
                return str;
            } catch (Exception e) {
                return str;
            }
        } else if (sharedPreferences.getBoolean("idfa_limit", false)) {
            return BuildConfig.FLAVOR;
        } else {
            String string = sharedPreferences.getString("idfa", BuildConfig.FLAVOR);
            if (TextUtils.isEmpty(string)) {
                return string;
            }
            try {
                return new String(Base64.decode(string, 2));
            } catch (Exception e2) {
                return string;
            }
        }
    }

    public static Boolean e(Context context) {
        boolean z = false;
        SharedPreferences sharedPreferences = context.getSharedPreferences("geniee_pref.dat", 0);
        long j = sharedPreferences.getLong("idfa_time", -1);
        long time = new Date().getTime();
        String str = BuildConfig.FLAVOR;
        if (j == -1 || 60000 <= time - j) {
            try {
                if (Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient") == null) {
                    return Boolean.valueOf(false);
                }
                Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(context);
                if (advertisingIdInfo == null) {
                    return Boolean.valueOf(false);
                }
                String id = advertisingIdInfo.getId();
                z = advertisingIdInfo.isLimitAdTrackingEnabled();
                String str2 = new String(Base64.encode(id.getBytes(), 2));
                Editor edit = sharedPreferences.edit();
                edit.putString("idfa", str2);
                edit.putBoolean("idfa_limit", z);
                edit.putLong("idfa_time", time);
                edit.commit();
            } catch (Exception e) {
            }
        } else if (sharedPreferences.getBoolean("idfa_limit", false)) {
            return Boolean.valueOf(false);
        } else {
            z = sharedPreferences.getBoolean("idfa_limit", false);
        }
        return Boolean.valueOf(z);
    }

    public static long a(Context context, String str) {
        return context.getSharedPreferences("geniee_pref.dat", 0).getLong("ad_last_time_" + str, 0);
    }

    public static void c(Context context, String str, long j) {
        Editor edit = context.getSharedPreferences("geniee_pref.dat", 0).edit();
        edit.putLong("ad_last_time_" + str, j);
        edit.commit();
    }

    public static String b(Context context, String str) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            stringBuilder.append(context.getCacheDir().getPath());
            stringBuilder.append("/geniee/");
            stringBuilder.append(str);
            stringBuilder.append("/");
        } catch (Exception e) {
        }
        stringBuilder.append("geniee_zoneinfo.dat");
        return stringBuilder.toString();
    }

    public static void a(String str, String str2) {
        Throwable th;
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            File file = new File(str);
            File parentFile = file.getParentFile();
            if (!parentFile.exists()) {
                parentFile.mkdirs();
            }
            PrintWriter printWriter = null;
            PrintWriter printWriter2;
            try {
                printWriter2 = new PrintWriter(new OutputStreamWriter(new FileOutputStream(file), c.DEFAULT_CHARSET));
                try {
                    printWriter2.write(str2);
                    if (printWriter2 != null) {
                        printWriter2.close();
                    }
                } catch (FileNotFoundException e) {
                    if (printWriter2 != null) {
                        printWriter2.close();
                    }
                } catch (UnsupportedEncodingException e2) {
                    printWriter = printWriter2;
                    if (printWriter != null) {
                        printWriter.close();
                    }
                } catch (Throwable th2) {
                    Throwable th3 = th2;
                    printWriter = printWriter2;
                    th = th3;
                    if (printWriter != null) {
                        printWriter.close();
                    }
                    throw th;
                }
            } catch (FileNotFoundException e3) {
                printWriter2 = null;
                if (printWriter2 != null) {
                    printWriter2.close();
                }
            } catch (UnsupportedEncodingException e4) {
                if (printWriter != null) {
                    printWriter.close();
                }
            } catch (Throwable th4) {
                th = th4;
                if (printWriter != null) {
                    printWriter.close();
                }
                throw th;
            }
        }
    }

    public static String a(String str) {
        BufferedReader bufferedReader;
        Throwable th;
        File file = new File(str);
        if (!file.exists()) {
            return BuildConfig.FLAVOR;
        }
        String str2 = BuildConfig.FLAVOR;
        BufferedReader bufferedReader2 = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), c.DEFAULT_CHARSET));
            try {
                StringBuilder stringBuilder = new StringBuilder();
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    }
                    stringBuilder.append(readLine);
                }
                str2 = stringBuilder.toString();
                if (bufferedReader == null) {
                    return str2;
                }
                try {
                    bufferedReader.close();
                    return str2;
                } catch (IOException e) {
                    return str2;
                }
            } catch (FileNotFoundException e2) {
                if (bufferedReader != null) {
                    return str2;
                }
                try {
                    bufferedReader.close();
                    return str2;
                } catch (IOException e3) {
                    return str2;
                }
            } catch (UnsupportedEncodingException e4) {
                bufferedReader2 = bufferedReader;
                if (bufferedReader2 != null) {
                    return str2;
                }
                try {
                    bufferedReader2.close();
                    return str2;
                } catch (IOException e5) {
                    return str2;
                }
            } catch (IOException e6) {
                bufferedReader2 = bufferedReader;
                if (bufferedReader2 != null) {
                    return str2;
                }
                try {
                    bufferedReader2.close();
                    return str2;
                } catch (IOException e7) {
                    return str2;
                }
            } catch (Throwable th2) {
                th = th2;
                bufferedReader2 = bufferedReader;
                if (bufferedReader2 != null) {
                    try {
                        bufferedReader2.close();
                    } catch (IOException e8) {
                    }
                }
                throw th;
            }
        } catch (FileNotFoundException e9) {
            bufferedReader = null;
            if (bufferedReader != null) {
                return str2;
            }
            bufferedReader.close();
            return str2;
        } catch (UnsupportedEncodingException e10) {
            if (bufferedReader2 != null) {
                return str2;
            }
            bufferedReader2.close();
            return str2;
        } catch (IOException e11) {
            if (bufferedReader2 != null) {
                return str2;
            }
            bufferedReader2.close();
            return str2;
        } catch (Throwable th3) {
            th = th3;
            if (bufferedReader2 != null) {
                bufferedReader2.close();
            }
            throw th;
        }
    }

    public static boolean b(String str) {
        File file = new File(str);
        if (file.isDirectory()) {
            String[] list = file.list();
            for (String file2 : list) {
                if (!b(new File(file, file2).getPath())) {
                    return false;
                }
            }
        }
        return file.delete();
    }
}
