package com.igaworks.commerce.net;

import android.content.Context;
import android.os.Handler;
import com.d.a.a.c;
import com.igaworks.core.IgawLogger;
import com.igaworks.interfaces.HttpCallbackListener;
import com.igaworks.util.IgawBase64;
import java.io.IOException;
import java.net.SocketTimeoutException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.cocos2dx.lib.BuildConfig;

public class CommerceHttpThread extends Thread {
    private Context context;
    private String httpResponseString;
    private boolean isEncode;
    private HttpCallbackListener listener;
    private int method;
    private String query;
    private String url;

    public CommerceHttpThread(Context context, int i, String str, String str2, HttpCallbackListener httpCallbackListener) {
        this(context, i, str, str2, httpCallbackListener, false);
    }

    public CommerceHttpThread(Context context, int i, String str, String str2, HttpCallbackListener httpCallbackListener, boolean z) {
        this.url = BuildConfig.FLAVOR;
        this.httpResponseString = BuildConfig.FLAVOR;
        this.url = str;
        this.method = i;
        this.query = str2;
        this.listener = httpCallbackListener;
        this.context = context;
        this.isEncode = z;
    }

    public void run() {
        try {
            HttpEntity entity;
            Handler handler = new Handler(this.context.getMainLooper());
            HttpParams basicHttpParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(basicHttpParams, 15000);
            HttpConnectionParams.setSoTimeout(basicHttpParams, 30000);
            HttpClient defaultHttpClient = new DefaultHttpClient(basicHttpParams);
            HttpUriRequest httpGet;
            if (this.method == 0) {
                HttpResponse execute;
                if (this.url.contains("?")) {
                    this.url += "&";
                } else {
                    this.url += "?";
                }
                if (this.isEncode) {
                    this.url += "queryString=" + IgawBase64.encodeString(this.query);
                } else {
                    this.url += this.query;
                }
                httpGet = new HttpGet(this.url);
                IgawLogger.Logging(this.context, "IGAW_QA", "getPromotionInfo > url = " + this.url, 3, true);
                try {
                    execute = defaultHttpClient.execute(httpGet);
                } catch (ClientProtocolException e) {
                    e.printStackTrace();
                    execute = null;
                } catch (IOException e2) {
                    e2.printStackTrace();
                    execute = null;
                }
                entity = execute.getEntity();
            } else {
                httpGet = new HttpPost(this.url);
                IgawLogger.Logging(this.context, "IGAW_QA", "ADBrix HTTP thread > reqName : " + this.url + ", param : " + this.query, 3, true);
                httpGet.setEntity(new StringEntity(this.query, c.DEFAULT_CHARSET));
                httpGet.setHeader("Accept", "application/json");
                httpGet.setHeader("Content-type", "application/json");
                entity = defaultHttpClient.execute(httpGet).getEntity();
            }
            if (entity != null) {
                this.httpResponseString = EntityUtils.toString(entity);
                handler.post(new Runnable() {
                    public void run() {
                        CommerceHttpThread.this.listener.callback(CommerceHttpThread.this.httpResponseString);
                    }
                });
                return;
            }
            IgawLogger.Logging(this.context, "IGAW_QA", "HttpThreadForPostTracking >> httpEntity == null", 3);
            this.listener.callback(null);
        } catch (SocketTimeoutException e3) {
            IgawLogger.Logging(this.context, "IGAW_QA", "SocketTimeoutException", 0);
            this.listener.callback(null);
        } catch (ConnectTimeoutException e4) {
            IgawLogger.Logging(this.context, "IGAW_QA", "ConnectTimeoutException", 0);
            this.listener.callback(null);
        } catch (Exception e5) {
            IgawLogger.Logging(this.context, "IGAW_QA", "Exception : " + e5.getMessage(), 0);
            this.listener.callback(null);
        }
    }
}
