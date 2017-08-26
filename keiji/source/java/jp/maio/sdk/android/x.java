package jp.maio.sdk.android;

import android.net.Uri;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.tapjoy.TJAdUnitConstants.String;
import com.tapjoy.TapjoyConstants;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import org.cocos2dx.lib.BuildConfig;
import org.json.JSONArray;
import org.json.JSONObject;

final class x implements Serializable {
    public static String a = "video";
    public final int b;
    public final String c;
    public final String d;
    public final String e;
    public final int f;
    public ArrayList g;
    public transient JSONObject h;
    public String i;
    public String j;
    public final int k;
    public final Calendar l;
    public String m = BuildConfig.FLAVOR;
    public String n = BuildConfig.FLAVOR;
    private final HashMap o = new HashMap(3);
    private y p = y.None;

    public x(JSONObject jSONObject, int i) {
        this.b = jSONObject.getInt("creative_id");
        this.f = i;
        this.c = jSONObject.getJSONObject("template_params").getString(TapjoyConstants.TJC_VIDEO_URL);
        this.d = jSONObject.getString("destination_url");
        this.j = jSONObject.getString("destination_url");
        this.e = jSONObject.getString("template_url");
        this.i = jSONObject.getString("template_params");
        this.k = jSONObject.getInt("template_type");
        String optString = jSONObject.optString("deliver_end_time");
        Calendar a = (optString == null || TextUtils.isEmpty(optString) || optString.equals("null")) ? null : ak.a(optString);
        this.l = a;
        this.g = new ArrayList();
        JSONArray jSONArray = jSONObject.getJSONObject("template_params").getJSONArray("download_urls");
        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
            this.g.add(jSONArray.getString(i2));
        }
        this.g.add(this.e);
    }

    private static String a(Uri uri) {
        return (uri.getHost() + File.separator + TextUtils.join(File.separator, uri.getPathSegments()) + File.separator + uri.getQuery()).replace("/null", BuildConfig.FLAVOR);
    }

    private boolean c() {
        return this.m.equals(this.n);
    }

    private boolean d() {
        return this.l == null || Calendar.getInstance().compareTo(this.l) < 0;
    }

    public File a(String str) {
        if (str != null) {
            return (File) this.o.get(str);
        }
        throw new IllegalArgumentException();
    }

    public void a(y yVar) {
        this.p = yVar;
    }

    public boolean a() {
        return this.p == y.Completed && d() && c();
    }

    public void b() {
        this.p = y.Loading;
        try {
            String str = v.b() + "/WebApiManager/videos/" + String.valueOf(this.b);
            Iterator it = this.g.iterator();
            while (it.hasNext()) {
                String str2 = (String) it.next();
                Uri parse = Uri.parse(str2);
                String str3 = BuildConfig.FLAVOR;
                if (parse.toString().contains("/video")) {
                    str3 = ".mp4";
                }
                String str4 = parse.toString().contains(".html") ? ".html" : str3;
                File file = new File(str + "/" + a(parse) + str4);
                z a;
                if (!file.exists() || file.length() <= 0) {
                    a = bk.a(parse, str);
                    if (a == null) {
                        throw new IOException();
                    }
                    this.o.put(str2, a.a);
                    if (a.d) {
                        this.n = a.c;
                        this.m = a.b;
                    }
                } else {
                    Date date = new Date(file.lastModified());
                    HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str2).openConnection();
                    if (VERSION.SDK_INT > 13) {
                        httpURLConnection.setRequestProperty("Connection", String.CLOSE);
                    }
                    httpURLConnection.setRequestMethod("HEAD");
                    long lastModified = httpURLConnection.getLastModified();
                    if (str4 == ".mp4") {
                        this.n = bk.a(file);
                        if (httpURLConnection.getHeaderField("Etag") != null) {
                            this.m = httpURLConnection.getHeaderField("Etag").replace("\"", BuildConfig.FLAVOR);
                        }
                    }
                    httpURLConnection.disconnect();
                    if (lastModified == 0 || new Date(lastModified).after(date) || !c()) {
                        a = bk.a(parse, str);
                        if (a == null) {
                            throw new IOException();
                        }
                        this.o.put(str2, a.a);
                        if (a.d) {
                            this.n = a.c;
                            this.m = a.b;
                        }
                    } else {
                        this.o.put(str2, file);
                    }
                }
            }
            this.p = y.Completed;
        } catch (IOException e) {
            this.p = y.Error;
        }
    }
}
