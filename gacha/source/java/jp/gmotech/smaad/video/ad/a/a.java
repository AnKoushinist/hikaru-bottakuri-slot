package jp.gmotech.smaad.video.ad.a;

import com.d.a.a.c;
import com.raizlabs.android.dbflow.sql.language.Condition.Operation;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.security.SecureRandom;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import jp.gmotech.smaad.video.ad.b.b;
import org.cocos2dx.lib.BuildConfig;
import org.json.JSONObject;

public class a {
    protected static int a = 20000;
    protected static String b = c.DEFAULT_CHARSET;
    static final HostnameVerifier h = new c();
    private static int i = 1;
    protected JSONObject c;
    protected int d = 600;
    protected String e = null;
    final String f;
    protected long g = -1;

    public a(String str, JSONObject jSONObject) {
        this.c = jSONObject;
        this.f = str;
    }

    private HttpsURLConnection a(URL url) {
        if (url == null) {
            return null;
        }
        HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
        httpsURLConnection.setHostnameVerifier(h);
        httpsURLConnection.setConnectTimeout(a);
        httpsURLConnection.setReadTimeout(a);
        httpsURLConnection.setRequestProperty("Charset", b);
        httpsURLConnection.setDoInput(true);
        httpsURLConnection.setUseCaches(false);
        return httpsURLConnection;
    }

    private void f() {
        TrustManager[] trustManagerArr = new TrustManager[]{new b(this)};
        try {
            SSLContext instance = SSLContext.getInstance("TLS");
            instance.init(null, trustManagerArr, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(instance.getSocketFactory());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public long a() {
        return this.g;
    }

    public int b() {
        return this.d;
    }

    public String c() {
        return this.e == null ? BuildConfig.FLAVOR : this.e;
    }

    protected String d() {
        return b.a(this.c);
    }

    public void e() {
        long currentTimeMillis = System.currentTimeMillis();
        HttpsURLConnection httpsURLConnection = null;
        f();
        try {
            StringBuilder stringBuilder = new StringBuilder(this.f);
            if (this.c != null) {
                String d = d();
                if (d != null) {
                    stringBuilder.append(Operation.EMPTY_PARAM).append(d);
                }
            }
            URL url = new URL(stringBuilder.toString());
            jp.gmotech.smaad.video.ad.b.a.a("BaseRequest", "[exec] url : " + stringBuilder);
            httpsURLConnection = a(url);
            if (httpsURLConnection == null) {
                throw new Exception();
            }
            httpsURLConnection.setConnectTimeout(a);
            httpsURLConnection.setReadTimeout(a);
            httpsURLConnection.setRequestMethod("GET");
            stringBuilder = new StringBuilder();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpsURLConnection.getInputStream(), b));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                stringBuilder.append(readLine);
            }
            bufferedReader.close();
            this.g = System.currentTimeMillis() - currentTimeMillis;
            this.d = httpsURLConnection.getResponseCode();
            this.e = stringBuilder.toString();
            if (httpsURLConnection != null) {
                try {
                    this.d = httpsURLConnection.getResponseCode();
                } catch (IOException e) {
                }
                httpsURLConnection.disconnect();
            }
            jp.gmotech.smaad.video.ad.b.a.b("BaseRequest", this.e != null ? "[exec] " + this.e : "[exec] response body is null");
            jp.gmotech.smaad.video.ad.b.a.b("BaseRequest", "[exec] Elapsed Time : " + this.g);
            jp.gmotech.smaad.video.ad.b.a.b("BaseRequest", "[exec] done");
        } catch (SocketTimeoutException e2) {
            jp.gmotech.smaad.video.ad.b.a.c("BaseRequest", "[exec] socketTimeoutException : " + e2.getMessage());
            if (httpsURLConnection != null) {
                try {
                    this.d = httpsURLConnection.getResponseCode();
                } catch (IOException e3) {
                }
                httpsURLConnection.disconnect();
            }
        } catch (Exception e4) {
            jp.gmotech.smaad.video.ad.b.a.c("BaseRequest", "[exec] Exception : " + e4.getMessage());
            if (httpsURLConnection != null) {
                try {
                    this.d = httpsURLConnection.getResponseCode();
                } catch (IOException e5) {
                }
                httpsURLConnection.disconnect();
            }
        } catch (Throwable th) {
            if (httpsURLConnection != null) {
                try {
                    this.d = httpsURLConnection.getResponseCode();
                } catch (IOException e6) {
                }
                httpsURLConnection.disconnect();
            }
        }
    }
}
