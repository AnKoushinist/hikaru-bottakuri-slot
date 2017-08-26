package jp.co.geniee.gnadsdk.rewardvideo;

import android.content.Context;
import android.graphics.Point;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Build;
import com.tapjoy.TJAdUnitConstants.String;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import jp.co.geniee.gnadsdk.a.d;
import jp.co.geniee.gnadsdk.a.e;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.cocos2dx.lib.BuildConfig;
import org.json.JSONArray;
import twitter4j.HttpResponseCode;

public class GNSAladdinApiUtil {

    public static class WebAPIResult {
        public String a = BuildConfig.FLAVOR;
        public JSONArray b = null;
        public int c = -2;
    }

    private static String a() {
        String str = "https";
        if (!GNSEnv.a().b() || GNSEnv.a().c() == null) {
            return str;
        }
        return Uri.parse(GNSEnv.a().c()).getScheme();
    }

    private static String b() {
        String str = "a-mobile.genieesspv.jp";
        if (!GNSEnv.a().b() || GNSEnv.a().c() == null) {
            return str;
        }
        return Uri.parse(GNSEnv.a().c()).getAuthority();
    }

    private static String c() {
        String str = "/yie/ld/rwd";
        if (!GNSEnv.a().b() || GNSEnv.a().c() == null) {
            return str;
        }
        return Uri.parse(GNSEnv.a().c()).getPath();
    }

    public static WebAPIResult a(Context context, String str, GNSLogger gNSLogger, String str2, d dVar, boolean z) {
        Locale locale = Locale.getDefault();
        Builder builder = new Builder();
        builder.scheme(a());
        builder.authority(b());
        builder.path(c());
        String c = e.c(context);
        Point d = e.d(context);
        builder.appendQueryParameter("ver", "2.2.3");
        builder.appendQueryParameter("zoneid", str);
        builder.appendQueryParameter("tkf", "1");
        if (c.length() > 0) {
            builder.appendQueryParameter("carr", c);
        }
        builder.appendQueryParameter("idfa", GNSPrefUtil.d(context));
        builder.appendQueryParameter("adtk", GNSPrefUtil.e(context).booleanValue() ? "0" : "1");
        builder.appendQueryParameter("ua", str2);
        builder.appendQueryParameter("dvmd", Build.MODEL);
        builder.appendQueryParameter("apid", e.a(context));
        builder.appendQueryParameter("lang", locale.getLanguage());
        builder.appendQueryParameter("apnm", e.b(context));
        builder.appendQueryParameter("ran", String.valueOf(GNSVideoTerm.b()));
        if (d.x > 0 && d.y > 0) {
            builder.appendQueryParameter("scw", String.valueOf(d.x));
            builder.appendQueryParameter("sch", String.valueOf(d.y));
        }
        if (dVar.a.length() > 0 && dVar.b.length() > 0) {
            builder.appendQueryParameter("lati", dVar.a);
            builder.appendQueryParameter(String.LONG, dVar.b);
        }
        gNSLogger.d("Api", "apiZoneId=" + str);
        gNSLogger.d("Api", "apiurl=" + builder.build().toString());
        return a(builder.build().toString(), gNSLogger, str2, true);
    }

    public static void a(Context context, String str, String str2, GNSLogger gNSLogger, String str3, String str4, String str5, String str6, String str7) {
        int i = 1;
        WebAPIResult a = a(str7, gNSLogger, str3, true);
        gNSLogger.d("Api", "send impurl=" + str7);
        if (a.c != HttpResponseCode.OK) {
            while (i <= 3) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    a = a(str7, gNSLogger, str3, true);
                    gNSLogger.d("Api", "send retry impurl=" + str7);
                    if (a.c != HttpResponseCode.OK) {
                        i++;
                    } else {
                        return;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return;
                }
            }
        }
    }

    public static void a(String str, String str2, GNSLogger gNSLogger, String str3, String str4, String str5) {
    }

    public static WebAPIResult a(String str, GNSLogger gNSLogger, String str2, boolean z) {
        WebAPIResult webAPIResult = new WebAPIResult();
        DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
        try {
            HttpUriRequest httpGet = new HttpGet(str);
            httpGet.setHeader("Content-Type", "application/x-www-form-urlencoded");
            HttpParams params = defaultHttpClient.getParams();
            if (z) {
                HttpConnectionParams.setConnectionTimeout(params, 5000);
                HttpConnectionParams.setSoTimeout(params, 5000);
            } else {
                HttpConnectionParams.setConnectionTimeout(params, 2000);
                HttpConnectionParams.setSoTimeout(params, 2000);
            }
            if (str2 != null && str2.length() > 0) {
                params.setParameter("http.useragent", str2);
            }
            HttpResponse execute = defaultHttpClient.execute(httpGet);
            webAPIResult.c = execute.getStatusLine().getStatusCode();
            if (webAPIResult.c == HttpResponseCode.OK) {
                OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                execute.getEntity().writeTo(byteArrayOutputStream);
                webAPIResult.a = byteArrayOutputStream.toString();
                gNSLogger.d("Api", "apiResult.response=" + webAPIResult.a);
            } else {
                gNSLogger.f("Api", "status code " + webAPIResult.c);
            }
        } catch (Exception e) {
            webAPIResult.c = -2;
            gNSLogger.f("Api", "ClientProtocolException");
            gNSLogger.a("Api", e);
        } catch (Exception e2) {
            webAPIResult.c = -2;
            gNSLogger.f("Api", "IllegalArgumentException");
            gNSLogger.a("Api", e2);
        } catch (Exception e22) {
            webAPIResult.c = -2;
            gNSLogger.f("Api", "IOException");
            gNSLogger.a("Api", e22);
        }
        return webAPIResult;
    }
}
