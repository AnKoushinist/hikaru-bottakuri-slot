package jp.co.geniee.gnadsdk.video;

import android.os.AsyncTask;
import jp.co.geniee.gnadsdk.a.a;
import jp.co.geniee.gnadsdk.a.f;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpParams;
import org.cocos2dx.lib.BuildConfig;
import twitter4j.HttpResponseCode;

/* compiled from: GNTrackingRequest */
public class b extends AsyncTask<String, Void, String> {
    private final a a = new a("GNAdSDK", Integer.MAX_VALUE);

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return a((String[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        a((String) obj);
    }

    public void a(int i) {
        this.a.a(i);
    }

    protected String a(String... strArr) {
        try {
            for (String str : strArr) {
                this.a.a("GNTrackingRequest", str);
                HttpUriRequest httpGet = new HttpGet(str);
                DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
                HttpParams params = defaultHttpClient.getParams();
                String a = f.a(null);
                if (a != null) {
                    params.setParameter("http.useragent", a);
                }
                if (defaultHttpClient.execute(httpGet).getStatusLine().getStatusCode() != HttpResponseCode.OK) {
                    throw new Exception(BuildConfig.FLAVOR);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return BuildConfig.FLAVOR;
    }

    protected void a(String str) {
    }
}
