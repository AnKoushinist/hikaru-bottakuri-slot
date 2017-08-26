package jp.co.geniee.a.a.a;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;
import jp.co.geniee.gnadsdk.a.a;
import jp.co.geniee.gnadsdk.a.c;
import jp.co.geniee.gnadsdk.a.d;
import org.cocos2dx.lib.BuildConfig;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: GNNativeAdRequest */
public class b {
    private Context a;
    private c b;
    private c c = null;
    private final a d = new a("GNAdSDK", Integer.MAX_VALUE);
    private boolean e = false;

    public b(Context context, String str) {
        int i = 0;
        if (str == null || str.length() < 1) {
            throw new IllegalArgumentException("GNNativeAdRequest: zoneids argument is empty.[E003]");
        }
        String[] split = str.split(",");
        while (i < split.length) {
            try {
                Integer.parseInt(split[i]);
                i++;
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("GNNativeAdRequest: zoneid argument is not number.[E004]");
            }
        }
        this.a = context;
        this.b = new c(this.d, context, str);
        this.b.b("http://a-mobile.genieesspv.jp/yie/ld/nad");
        this.b.a(BuildConfig.FLAVOR);
    }

    public void a(Activity activity) {
        if (this.e) {
            this.d.b("GNNativeAdRequest", "ignore double launch!");
            return;
        }
        this.e = true;
        if (d.b(activity)) {
            final Handler handler = new Handler();
            new Thread(new Runnable(this) {
                final /* synthetic */ b a;

                public void run() {
                    final ArrayList arrayList = new ArrayList();
                    try {
                        String a = this.a.b.a();
                        this.a.d.a("GNNativeAdRequest", "willStartLoadURL : " + a);
                        a = d.a(a, 10, d.a(null));
                        this.a.d.a("GNNativeAdRequest", "didReceiveResponse : " + a);
                        if (a != null) {
                            JSONObject jSONObject = new JSONObject(a);
                            Iterator keys = jSONObject.keys();
                            while (keys.hasNext()) {
                                try {
                                    Object next = keys.next();
                                    if (next instanceof String) {
                                        try {
                                            Integer.parseInt((String) next);
                                            if (jSONObject.has((String) next)) {
                                                JSONObject jSONObject2 = jSONObject.getJSONObject((String) next);
                                                if (jSONObject2.length() >= 1) {
                                                    a aVar = new a(this.a.a, (String) next, jSONObject2, this.a);
                                                    if (aVar.c()) {
                                                        aVar.a(this.a.d.a());
                                                        arrayList.add(aVar);
                                                    }
                                                }
                                            }
                                        } catch (NumberFormatException e) {
                                            this.a.d.b("GNNativeAdRequest", "response zoneid is not number:" + ((String) next));
                                        }
                                    }
                                } catch (JSONException e2) {
                                    Log.e("GNNativeAdRequest", "json parse error : " + e2.toString());
                                }
                            }
                        }
                    } catch (Throwable e3) {
                        this.a.d.a("GNNativeAdRequest", "load error:", e3);
                    }
                    handler.post(new Runnable(this) {
                        final /* synthetic */ AnonymousClass1 a;

                        public void run() {
                            if (arrayList.size() > 0) {
                                this.a.a.a((a[]) arrayList.toArray(new a[arrayList.size()]));
                            } else {
                                this.a.a.c();
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
        return this.c;
    }

    public void a(c cVar) {
        this.c = cVar;
    }

    protected Context b() {
        return this.a;
    }

    private void c() {
        this.e = false;
        this.d.b("GNNativeAdRequest", "load Failed!");
        if (this.c != null) {
            this.c.onNativeAdsFailedToLoad();
        }
    }

    private void a(a[] aVarArr) {
        this.e = false;
        this.d.a("GNNativeAdRequest", "load Success");
        if (this.c != null) {
            this.c.onNativeAdsLoaded(aVarArr);
        }
    }
}
