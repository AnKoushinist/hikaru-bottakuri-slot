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

class bi {
    private int a = Constants.IP_RETRY_TIME;
    private int b = Constants.IP_RETRY_TIME;

    bi() {
    }

    private String a() {
        String i = x.i();
        String h = x.h();
        try {
            i = URLEncoder.encode(i, c.DEFAULT_CHARSET);
            h = URLEncoder.encode(h, c.DEFAULT_CHARSET);
        } catch (Exception e) {
        }
        return String.format("&sdkv=%s&appid=%s&dvbrnd=%s&dvnm=%s&dpr=%s&gaid=%s&osv=%s&dpw=%s&dph=%s&nws=%s&lang=%s&plt=android&appv=%s", new Object[]{"1.0.3", x.d(), h, i, Float.valueOf(x.j()), x.f(), x.g(), Integer.valueOf(x.l()), Integer.valueOf(x.k()), x.m(), x.e(), Integer.valueOf(x.b())});
    }

    public Object a(String str, ResponseHandler responseHandler) {
        return a(new HttpGet(str), responseHandler);
    }

    public Object a(HttpUriRequest httpUriRequest, ResponseHandler responseHandler) {
        if (x.m().equals(BuildConfig.FLAVOR)) {
            ax.a("Network Condition.", "Disabled.", BuildConfig.FLAVOR, null);
            throw new ay(FailNotificationReason.NETWORK_NOT_READY);
        }
        ax.a("WebClient#request.", "Request uri:" + httpUriRequest.getURI().toString(), null);
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
            ax.a("Network Connection error.", "ClientProtocolException." + httpUriRequest.toString(), "Timeout.", e);
            throw new ay(FailNotificationReason.NETWORK);
        } catch (Throwable e2) {
            ax.a("WebClient#request Error", "IOException." + httpUriRequest.toString(), "Connection.", e2);
            throw new ay(FailNotificationReason.NETWORK);
        } catch (Throwable th) {
            defaultHttpClient.getConnectionManager().shutdown();
        }
    }
}
