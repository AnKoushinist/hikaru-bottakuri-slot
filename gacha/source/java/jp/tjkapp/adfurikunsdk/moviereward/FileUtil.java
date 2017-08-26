package jp.tjkapp.adfurikunsdk.moviereward;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import android.util.Base64;
import android.webkit.WebView;
import com.d.a.a.c;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.raizlabs.android.dbflow.sql.language.Condition.Operation;
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
import java.util.Iterator;
import jp.tjkapp.adfurikunsdk.moviereward.ApiAccessUtil.WebAPIResult;
import org.cocos2dx.lib.BuildConfig;
import org.json.JSONException;
import org.json.JSONObject;
import twitter4j.HttpResponseCode;

class FileUtil {

    public static class IPUA {
        public String carrier = BuildConfig.FLAVOR;
        public String ip = BuildConfig.FLAVOR;
        public String loc = BuildConfig.FLAVOR;
    }

    public static class IntersAdPref {
        public int frequencyCount;
        public int maxCount;
    }

    private FileUtil() {
    }

    public static int getPrefIntValue(Context context, String str, int i) {
        if (context == null) {
            return i;
        }
        return context.getSharedPreferences(Constants.PREF_FILE, 0).getInt(str, i);
    }

    public static void setPrefIntValue(Context context, String str, int i) {
        if (context != null) {
            Editor edit = context.getSharedPreferences(Constants.PREF_FILE, 0).edit();
            edit.putInt(str, i);
            edit.commit();
        }
    }

    public static int getTestMode(Context context) {
        return context.getSharedPreferences(Constants.PREF_FILE, 0).getInt(Constants.PREFKEY_TESTMODE, -1);
    }

    public static void setTestMode(Context context, int i) {
        Editor edit = context.getSharedPreferences(Constants.PREF_FILE, 0).edit();
        edit.putInt(Constants.PREFKEY_TESTMODE, i);
        edit.commit();
    }

    public static String getUserAgent(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.PREF_FILE, 0);
        String string = sharedPreferences.getString(Constants.PREFKEY_USERAGENT, BuildConfig.FLAVOR);
        if (string == null || string.length() <= 0) {
            string = new WebView(context.getApplicationContext()).getSettings().getUserAgentString();
            if (string == null) {
                string = BuildConfig.FLAVOR;
            }
            Editor edit = sharedPreferences.edit();
            edit.putString(Constants.PREFKEY_USERAGENT, string);
            edit.commit();
        }
        return string;
    }

    public static IPUA getIPUA(Context context, LogUtil logUtil, String str) {
        IPUA ipua = new IPUA();
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.PREF_FILE, 0);
        String string = sharedPreferences.getString(Constants.PREFKEY_IP, BuildConfig.FLAVOR);
        String string2 = sharedPreferences.getString(Constants.PREFKEY_CARR, BuildConfig.FLAVOR);
        String string3 = sharedPreferences.getString(Constants.PREFKEY_LOC, BuildConfig.FLAVOR);
        long j = sharedPreferences.getLong(Constants.PREFKEY_IP_TIME, -1);
        long time = new Date().getTime();
        if (string == null || string.length() <= 0 || j == -1 || 60000 <= time - j) {
            WebAPIResult ipua2 = ApiAccessUtil.getIPUA(logUtil, str);
            if (ipua2.returnCode == HttpResponseCode.OK) {
                try {
                    JSONObject jSONObject = new JSONObject(ipua2.message);
                    Iterator keys = jSONObject.keys();
                    while (keys.hasNext()) {
                        string = (String) keys.next();
                        if (Constants.PREFKEY_IP.equals(string)) {
                            ipua.ip = jSONObject.getString(string);
                        } else if (Constants.PREFKEY_CARR.equals(string)) {
                            ipua.carrier = jSONObject.getString(string);
                        } else if (Constants.PREFKEY_LOC.equals(string)) {
                            ipua.loc = jSONObject.getString(string);
                        }
                    }
                    Editor edit = sharedPreferences.edit();
                    edit.putString(Constants.PREFKEY_IP, ipua.ip);
                    edit.putString(Constants.PREFKEY_CARR, ipua.carrier);
                    edit.putString(Constants.PREFKEY_LOC, ipua.loc);
                    edit.putLong(Constants.PREFKEY_IP_TIME, time);
                    edit.commit();
                } catch (JSONException e) {
                }
            }
            return ipua;
        }
        ipua.ip = string;
        ipua.carrier = string2;
        ipua.loc = string3;
        return ipua;
    }

    public static String getGaid(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.PREF_FILE, 0);
        long j = sharedPreferences.getLong(Constants.PREFKEY_GAID_UPDATE_TIME, -1);
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
                edit.putString(Constants.PREFKEY_GAID, str2);
                edit.putBoolean(Constants.PREFKEY_GAID_LIMIT_TRACKING, isLimitAdTrackingEnabled);
                edit.putLong(Constants.PREFKEY_GAID_UPDATE_TIME, time);
                edit.commit();
                return str;
            } catch (Exception e) {
                return str;
            }
        } else if (sharedPreferences.getBoolean(Constants.PREFKEY_GAID_LIMIT_TRACKING, false)) {
            return BuildConfig.FLAVOR;
        } else {
            String string = sharedPreferences.getString(Constants.PREFKEY_GAID, BuildConfig.FLAVOR);
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

    public static int getGetInfoState(Context context, String str) {
        return context.getSharedPreferences(Constants.PREF_FILE, 0).getInt(Constants.PREFKEY_GETINFO_STATE + str, 0);
    }

    public static void setGetInfoState(Context context, String str, int i) {
        Editor edit = context.getSharedPreferences(Constants.PREF_FILE, 0).edit();
        edit.putInt(Constants.PREFKEY_GETINFO_STATE + str, i);
        edit.commit();
    }

    public static boolean isGetInfoCache(Context context, String str) {
        if (new File(getGetInfoFilePath(context, str)).exists()) {
            return true;
        }
        return false;
    }

    public static boolean isFillerCache(Context context, String str) {
        if (new File(getFillerFilePath(context, str)).exists()) {
            return true;
        }
        return false;
    }

    public static long getGetInfoUpdateTime(Context context, String str) {
        return context.getSharedPreferences(Constants.PREF_FILE, 0).getLong(Constants.PREFKEY_AD_LAST_TIME + str, 0);
    }

    public static void saveGetInfoUpdateTime(Context context, String str, long j) {
        Editor edit = context.getSharedPreferences(Constants.PREF_FILE, 0).edit();
        edit.putLong(Constants.PREFKEY_AD_LAST_TIME + str, j);
        edit.commit();
    }

    public static IntersAdPref getIntersAdPref(Context context, String str) {
        IntersAdPref intersAdPref = new IntersAdPref();
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.PREF_FILE, 0);
        intersAdPref.frequencyCount = sharedPreferences.getInt(str + Constants.PREFKEY_INTERS_AD_FREQUENCY_CT, 0);
        intersAdPref.maxCount = sharedPreferences.getInt(str + Constants.PREFKEY_INTERS_AD_MAX_CT, 0);
        return intersAdPref;
    }

    public static void setIntersAdPref(Context context, String str, IntersAdPref intersAdPref) {
        Editor edit = context.getSharedPreferences(Constants.PREF_FILE, 0).edit();
        edit.putInt(str + Constants.PREFKEY_INTERS_AD_MAX_CT, intersAdPref.maxCount);
        edit.putInt(str + Constants.PREFKEY_INTERS_AD_FREQUENCY_CT, intersAdPref.frequencyCount);
        edit.commit();
    }

    public static String getGetInfoFilePath(Context context, String str) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            stringBuilder.append(context.getCacheDir().getPath());
            stringBuilder.append(Constants.ADFURIKUN_FOLDER);
            stringBuilder.append(str);
            stringBuilder.append(Operation.DIVISION);
        } catch (Exception e) {
        }
        stringBuilder.append(Constants.GETINFO_FILE);
        return stringBuilder.toString();
    }

    public static String getAdInfoDetailFilePath(Context context, String str, AdInfoDetail adInfoDetail) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            stringBuilder.append(context.getCacheDir().getPath());
            stringBuilder.append(Constants.ADFURIKUN_FOLDER);
            stringBuilder.append(str);
            stringBuilder.append(Operation.DIVISION);
        } catch (Exception e) {
        }
        stringBuilder.append(adInfoDetail.adnetworkKey);
        stringBuilder.append("_");
        stringBuilder.append(adInfoDetail.userAdId);
        stringBuilder.append(".html");
        return stringBuilder.toString();
    }

    public static String getFillerFilePath(Context context, String str) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            stringBuilder.append(context.getCacheDir().getPath());
            stringBuilder.append(Constants.ADFURIKUN_FOLDER);
            stringBuilder.append(str);
            stringBuilder.append(Operation.DIVISION);
        } catch (Exception e) {
        }
        stringBuilder.append(Constants.FILLER_FILE);
        return stringBuilder.toString();
    }

    public static void saveStringFile(String str, String str2) {
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

    public static String loadStringFile(String str) {
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

    public static boolean deleteFile(String str) {
        File file = new File(str);
        if (file.isDirectory()) {
            String[] list = file.list();
            for (String file2 : list) {
                if (!deleteFile(new File(file, file2).getPath())) {
                    return false;
                }
            }
        }
        return file.delete();
    }
}
