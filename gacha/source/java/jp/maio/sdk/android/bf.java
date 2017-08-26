package jp.maio.sdk.android;

import android.content.Context;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Build.VERSION;
import android.os.Environment;
import android.text.TextUtils;
import com.raizlabs.android.dbflow.sql.language.Condition.Operation;
import com.tapjoy.TJAdUnitConstants.String;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import jp.reifrontier.silentlogsdkandroid.util.RFLConstants;
import org.cocos2dx.lib.BuildConfig;

class bf {
    private static File a;

    static File a(Uri uri, String str) {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(uri.toString()).openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(Constants.IP_RETRY_TIME);
            httpURLConnection.setReadTimeout(Constants.IP_RETRY_TIME);
            if (VERSION.SDK_INT > 13) {
                httpURLConnection.setRequestProperty("Connection", String.CLOSE);
            }
            httpURLConnection.connect();
            long contentLength = (long) httpURLConnection.getContentLength();
            String str2 = BuildConfig.FLAVOR;
            if (uri.toString().contains("/video")) {
                str2 = ".mp4";
            }
            if (uri.toString().contains(".html")) {
                str2 = ".html";
            }
            File file = new File((str + Operation.DIVISION + b(uri) + str2).replace("/null", BuildConfig.FLAVOR));
            file.getParentFile().mkdirs();
            try {
                file.createNewFile();
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    byte[] bArr = new byte[1024];
                    InputStream inputStream = httpURLConnection.getInputStream();
                    while (true) {
                        int read = inputStream.read(bArr);
                        if (read <= 0) {
                            break;
                        }
                        fileOutputStream.write(bArr, 0, read);
                    }
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    inputStream.close();
                    httpURLConnection.disconnect();
                    long length = file.length();
                    long contentLength2 = (long) httpURLConnection.getContentLength();
                    ax.a("Downloading  " + uri.toString() + " size = " + (length / RFLConstants.UPDATE_INTERVAL_IN_MILLISECONDS_ON_STAY), BuildConfig.FLAVOR, "DATA", null);
                    if (str2 == ".html") {
                        return file;
                    }
                    if (httpURLConnection.getHeaderField("server") != null && httpURLConnection.getHeaderField("server").equals("AmazonS3")) {
                        str2 = a(file);
                        if (!(httpURLConnection.getHeaderField("Etag") == null || str2 == null || httpURLConnection.getHeaderField("Etag").replace("\"", BuildConfig.FLAVOR).equals(str2))) {
                            file.delete();
                            return null;
                        }
                    }
                    if (length == contentLength && length > 0) {
                        return file;
                    }
                    file.delete();
                    return null;
                } catch (IOException e) {
                    file.delete();
                    return null;
                }
            } catch (IOException e2) {
                if (file.exists()) {
                    file.delete();
                }
                return null;
            }
        } catch (IOException e3) {
            return null;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String a(java.io.File r6) {
        /*
        r0 = 0;
        r1 = "MD5";
        r1 = java.security.MessageDigest.getInstance(r1);	 Catch:{ NoSuchAlgorithmException -> 0x006a }
        r2 = new java.io.FileInputStream;	 Catch:{ FileNotFoundException -> 0x0068 }
        r2.<init>(r6);	 Catch:{ FileNotFoundException -> 0x0068 }
        r3 = 8192; // 0x2000 float:1.148E-41 double:4.0474E-320;
        r3 = new byte[r3];
    L_0x0010:
        r4 = r2.read(r3);	 Catch:{ IOException -> 0x001b, all -> 0x005a }
        if (r4 <= 0) goto L_0x0020;
    L_0x0016:
        r5 = 0;
        r1.update(r3, r5, r4);	 Catch:{ IOException -> 0x001b, all -> 0x005a }
        goto L_0x0010;
    L_0x001b:
        r1 = move-exception;
        r2.close();	 Catch:{ IOException -> 0x0051 }
    L_0x001f:
        return r0;
    L_0x0020:
        r1 = r1.digest();	 Catch:{ IOException -> 0x001b, all -> 0x005a }
        r3 = new java.math.BigInteger;	 Catch:{ IOException -> 0x001b, all -> 0x005a }
        r4 = 1;
        r3.<init>(r4, r1);	 Catch:{ IOException -> 0x001b, all -> 0x005a }
        r1 = 16;
        r1 = r3.toString(r1);	 Catch:{ IOException -> 0x001b, all -> 0x005a }
        r3 = "%32s";
        r4 = 1;
        r4 = new java.lang.Object[r4];	 Catch:{ IOException -> 0x001b, all -> 0x005a }
        r5 = 0;
        r4[r5] = r1;	 Catch:{ IOException -> 0x001b, all -> 0x005a }
        r1 = java.lang.String.format(r3, r4);	 Catch:{ IOException -> 0x001b, all -> 0x005a }
        r3 = 32;
        r4 = 48;
        r0 = r1.replace(r3, r4);	 Catch:{ IOException -> 0x001b, all -> 0x005a }
        r2.close();	 Catch:{ IOException -> 0x0048 }
        goto L_0x001f;
    L_0x0048:
        r1 = move-exception;
        r2 = "calculateMD5";
        r3 = "Exception on closing MD5 input stream";
        android.util.Log.e(r2, r3, r1);
        goto L_0x001f;
    L_0x0051:
        r1 = move-exception;
        r2 = "calculateMD5";
        r3 = "Exception on closing MD5 input stream";
        android.util.Log.e(r2, r3, r1);
        goto L_0x001f;
    L_0x005a:
        r0 = move-exception;
        r2.close();	 Catch:{ IOException -> 0x005f }
    L_0x005e:
        throw r0;
    L_0x005f:
        r1 = move-exception;
        r2 = "calculateMD5";
        r3 = "Exception on closing MD5 input stream";
        android.util.Log.e(r2, r3, r1);
        goto L_0x005e;
    L_0x0068:
        r1 = move-exception;
        goto L_0x001f;
    L_0x006a:
        r1 = move-exception;
        goto L_0x001f;
        */
        throw new UnsupportedOperationException("Method not decompiled: jp.maio.sdk.android.bf.a(java.io.File):java.lang.String");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static jp.maio.sdk.android.av a(java.lang.String r5, boolean r6) {
        /*
        r2 = 0;
        r0 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x0048, JSONException -> 0x005a, ParseException -> 0x007e }
        r0.<init>();	 Catch:{ IOException -> 0x0048, JSONException -> 0x005a, ParseException -> 0x007e }
        r1 = "https://media-api.maio.jp/api/media/";
        r0 = r0.append(r1);	 Catch:{ IOException -> 0x0048, JSONException -> 0x005a, ParseException -> 0x007e }
        r0 = r0.append(r5);	 Catch:{ IOException -> 0x0048, JSONException -> 0x005a, ParseException -> 0x007e }
        r1 = "?ad_deliver_test=";
        r0 = r0.append(r1);	 Catch:{ IOException -> 0x0048, JSONException -> 0x005a, ParseException -> 0x007e }
        r1 = java.lang.String.valueOf(r6);	 Catch:{ IOException -> 0x0048, JSONException -> 0x005a, ParseException -> 0x007e }
        r0 = r0.append(r1);	 Catch:{ IOException -> 0x0048, JSONException -> 0x005a, ParseException -> 0x007e }
        r0 = r0.toString();	 Catch:{ IOException -> 0x0048, JSONException -> 0x005a, ParseException -> 0x007e }
        r3 = a(r0);	 Catch:{ IOException -> 0x0048, JSONException -> 0x005a, ParseException -> 0x007e }
        r1 = new java.lang.String;	 Catch:{ IOException -> 0x0048, JSONException -> 0x005a, ParseException -> 0x007e }
        r0 = r3.c;	 Catch:{ IOException -> 0x0048, JSONException -> 0x005a, ParseException -> 0x007e }
        r0 = (java.io.File) r0;	 Catch:{ IOException -> 0x0048, JSONException -> 0x005a, ParseException -> 0x007e }
        r0 = jp.maio.sdk.android.ba.a(r0);	 Catch:{ IOException -> 0x0048, JSONException -> 0x005a, ParseException -> 0x007e }
        r3 = r3.a();	 Catch:{ IOException -> 0x0048, JSONException -> 0x005a, ParseException -> 0x007e }
        r1.<init>(r0, r3);	 Catch:{ IOException -> 0x0048, JSONException -> 0x005a, ParseException -> 0x007e }
        r0 = "Response Json data.";
        r2 = 0;
        jp.maio.sdk.android.ax.a(r0, r1, r2);	 Catch:{ IOException -> 0x0048, JSONException -> 0x00a4, ParseException -> 0x00a1 }
        r0 = new jp.maio.sdk.android.av;	 Catch:{ IOException -> 0x0048, JSONException -> 0x00a4, ParseException -> 0x00a1 }
        r2 = new org.json.JSONObject;	 Catch:{ IOException -> 0x0048, JSONException -> 0x00a4, ParseException -> 0x00a1 }
        r2.<init>(r1);	 Catch:{ IOException -> 0x0048, JSONException -> 0x00a4, ParseException -> 0x00a1 }
        r0.<init>(r2);	 Catch:{ IOException -> 0x0048, JSONException -> 0x00a4, ParseException -> 0x00a1 }
        return r0;
    L_0x0048:
        r0 = move-exception;
        r1 = "WebApiManager#downloadMedia response data error";
        r2 = "";
        r3 = "DATA";
        jp.maio.sdk.android.ax.a(r1, r2, r3, r0);
        r0 = new jp.maio.sdk.android.ay;
        r1 = jp.maio.sdk.android.FailNotificationReason.RESPONSE;
        r0.<init>(r1);
        throw r0;
    L_0x005a:
        r0 = move-exception;
        r1 = r2;
    L_0x005c:
        r2 = "WebApiManager#downloadMedia response data error";
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "jsonObject ParseException.";
        r3 = r3.append(r4);
        r1 = r3.append(r1);
        r1 = r1.toString();
        r3 = "DATA";
        jp.maio.sdk.android.ax.a(r2, r1, r3, r0);
        r0 = new jp.maio.sdk.android.ay;
        r1 = jp.maio.sdk.android.FailNotificationReason.RESPONSE;
        r0.<init>(r1);
        throw r0;
    L_0x007e:
        r0 = move-exception;
    L_0x007f:
        r1 = "WebApiManager#downloadMedia response data error";
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "jsonObject ParseException.";
        r3 = r3.append(r4);
        r2 = r3.append(r2);
        r2 = r2.toString();
        r3 = "DATA";
        jp.maio.sdk.android.ax.a(r1, r2, r3, r0);
        r0 = new jp.maio.sdk.android.ay;
        r1 = jp.maio.sdk.android.FailNotificationReason.RESPONSE;
        r0.<init>(r1);
        throw r0;
    L_0x00a1:
        r0 = move-exception;
        r2 = r1;
        goto L_0x007f;
    L_0x00a4:
        r0 = move-exception;
        goto L_0x005c;
        */
        throw new UnsupportedOperationException("Method not decompiled: jp.maio.sdk.android.bf.a(java.lang.String, boolean):jp.maio.sdk.android.av");
    }

    static bh a(Builder builder, String str) {
        Uri build = builder.build();
        return (bh) new bi().a(build.toString(), new bg(build, str));
    }

    static bh a(String str) {
        return a(Uri.parse(str).buildUpon(), "default");
    }

    static void a(Context context) {
        if (Environment.getExternalStorageState().equals("mounted")) {
            a = new File(t.b(), "WebApiManager");
            return;
        }
        throw new ay(FailNotificationReason.UNKNOWN);
    }

    private static String b(Uri uri) {
        return uri.getHost() + File.separator + TextUtils.join(File.separator, uri.getPathSegments()) + File.separator + uri.getQuery();
    }
}
