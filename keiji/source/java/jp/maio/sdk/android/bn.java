package jp.maio.sdk.android;

import com.d.a.a.c;
import java.net.URLEncoder;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.cocos2dx.lib.BuildConfig;

class bn {
    private int a = 60000;
    private int b = 60000;

    bn() {
    }

    private String a() {
        String i = aa.i();
        String h = aa.h();
        try {
            i = URLEncoder.encode(i, c.DEFAULT_CHARSET);
            h = URLEncoder.encode(h, c.DEFAULT_CHARSET);
        } catch (Exception e) {
        }
        return String.format("&sdkv=%s&appid=%s&dvbrnd=%s&dvnm=%s&dpr=%s&gaid=%s&osv=%s&dpw=%s&dph=%s&nws=%s&lang=%s&plt=android&appv=%s", new Object[]{"1.0.5", aa.d(), h, i, Float.valueOf(aa.j()), aa.f(), aa.g(), Integer.valueOf(aa.l()), Integer.valueOf(aa.k()), aa.m(), aa.e(), Integer.valueOf(aa.b())});
    }

    public Object a(String str, ResponseHandler responseHandler) {
        return a(new HttpGet(str), responseHandler);
    }

    public Object a(HttpUriRequest httpUriRequest, ResponseHandler responseHandler) {
        if (aa.m().equals(BuildConfig.FLAVOR)) {
            bc.a("Network Condition.", "Disabled.", BuildConfig.FLAVOR, null);
            throw new bd(FailNotificationReason.NETWORK_NOT_READY);
        }
        bc.a("WebClient#request.", "Request uri:" + httpUriRequest.getURI().toString(), null);
        httpUriRequest.addHeader("X-Maio-Params", a());
        HttpClient defaultHttpClient = new DefaultHttpClient();
        HttpParams params = defaultHttpClient.getParams();
        HttpConnectionParams.setConnectionTimeout(params, this.a);
        HttpConnectionParams.setSoTimeout(params, this.b);
        try {
            Object execute = defaultHttpClient.execute(httpUriRequest, responseHandler);
            defaultHttpClient.getConnectionManager().shutdown();
            return execute;
        } catch (Throwable e) {
            bc.a("Network Connection error.", "ClientProtocolException." + httpUriRequest.toString(), "Timeout.", e);
            throw new bd(FailNotificationReason.NETWORK);
        } catch (Throwable e2) {
            bc.a("WebClient#request Error", "IOException." + httpUriRequest.toString(), "Connection.", e2);
            throw new bd(FailNotificationReason.NETWORK);
        } catch (Throwable th) {
            defaultHttpClient.getConnectionManager().shutdown();
        }
    }
}
