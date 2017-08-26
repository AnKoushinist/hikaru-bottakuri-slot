package jp.co.geniee.a.a.a;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;
import jp.co.geniee.gnadsdk.a.a;
import jp.co.geniee.gnadsdk.a.c;
import jp.co.geniee.gnadsdk.a.f;
import org.cocos2dx.lib.BuildConfig;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: GNNativeAdRequest */
public class b {
    final ArrayList<a> a = new ArrayList();
    private Context b;
    private c c;
    private c d = null;
    private final a e = new a("GNAdSDK", Integer.MAX_VALUE);
    private boolean f = false;
    private String[] g;

    public b(Context context, String str) {
        int i = 0;
        if (str == null || str.length() < 1) {
            throw new IllegalArgumentException("GNNativeAdRequest: zoneids argument is empty.[E003]");
        }
        this.g = str.split(",");
        while (i < this.g.length) {
            try {
                Integer.parseInt(this.g[i]);
                i++;
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("GNNativeAdRequest: zoneid argument is not number.[E004]");
            }
        }
        this.b = context;
        this.c = new c(this.e, context, str);
        this.c.b("http://a-mobile.genieesspv.jp/yie/ld/nad");
        this.c.a(BuildConfig.FLAVOR);
    }

    public void a(Activity activity) {
        if (this.f) {
            this.e.b("GNNativeAdRequest", "ignore double launch!");
            return;
        }
        this.f = true;
        if (f.b(activity)) {
            final Handler handler = new Handler();
            new Thread(new Runnable(this) {
                final /* synthetic */ b b;

                public void run() {
                    final ArrayList arrayList = new ArrayList();
                    try {
                        String a = this.b.c.a();
                        this.b.e.a("GNNativeAdRequest", "willStartLoadURL : " + a);
                        a = f.a(a, 10, f.a(null));
                        this.b.e.a("GNNativeAdRequest", "didReceiveResponse : " + a);
                        if (a != null) {
                            try {
                                JSONObject jSONObject = new JSONObject(a);
                                Iterator keys = jSONObject.keys();
                                while (keys.hasNext()) {
                                    Object next = keys.next();
                                    if (next instanceof String) {
                                        try {
                                            Integer.parseInt((String) next);
                                            if (jSONObject.has((String) next)) {
                                                JSONObject jSONObject2 = jSONObject.getJSONObject((String) next);
                                                if (jSONObject2.length() >= 1) {
                                                    a aVar = new a(this.b.b, (String) next, jSONObject2, this.b);
                                                    if (aVar.c()) {
                                                        aVar.a(this.b.e.a());
                                                        arrayList.add(aVar);
                                                    }
                                                }
                                            }
                                        } catch (NumberFormatException e) {
                                            this.b.e.b("GNNativeAdRequest", "response zoneid is not number:" + ((String) next));
                                        }
                                    }
                                }
                            } catch (JSONException e2) {
                                Log.e("GNNativeAdRequest", "json parse error : " + e2.toString());
                            }
                        }
                    } catch (Throwable e3) {
                        this.b.e.a("GNNativeAdRequest", "load error:", e3);
                    }
                    handler.post(new Runnable(this) {
                        final /* synthetic */ AnonymousClass1 b;

                        public void run() {
                            if (arrayList.size() > 0) {
                                this.b.b.a((a[]) arrayList.toArray(new a[arrayList.size()]));
                            } else {
                                this.b.b.c();
                            }
                        }
                    });
                }
            }).start();
            return;
        }
        c();
    }

    public c a() {
        return this.d;
    }

    public void a(c cVar) {
        this.d = cVar;
    }

    protected Context b() {
        return this.b;
    }

    private void c() {
        this.f = false;
        this.e.b("GNNativeAdRequest", "load Failed!");
        if (this.d != null) {
            this.d.onNativeAdsFailedToLoad();
        }
    }

    private void a(a[] aVarArr) {
        this.f = false;
        this.e.a("GNNativeAdRequest", "load Success");
        if (this.d != null) {
            this.d.onNativeAdsLoaded(aVarArr);
        }
    }
}
