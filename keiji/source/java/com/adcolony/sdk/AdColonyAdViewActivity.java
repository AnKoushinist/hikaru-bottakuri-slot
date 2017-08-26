package com.adcolony.sdk;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.ViewGroup;
import org.json.JSONArray;
import org.json.JSONObject;

public class AdColonyAdViewActivity extends ah {
    ae a;
    boolean b;

    public AdColonyAdViewActivity() {
        this.a = !n.b() ? null : n.a().n();
        this.b = this.a instanceof i;
    }

    public /* bridge */ /* synthetic */ void onBackPressed() {
        super.onBackPressed();
    }

    public /* bridge */ /* synthetic */ void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    public /* bridge */ /* synthetic */ void onDestroy() {
        super.onDestroy();
    }

    public /* bridge */ /* synthetic */ void onPause() {
        super.onPause();
    }

    public /* bridge */ /* synthetic */ void onResume() {
        super.onResume();
    }

    public /* bridge */ /* synthetic */ void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
    }

    public void onCreate(Bundle bundle) {
        this.f = this.a == null ? 0 : this.a.b;
        super.onCreate(bundle);
        if (n.b() && this.a != null) {
            n.a().d(true);
            an listener = this.a.getListener();
            if (listener != null && (listener instanceof j)) {
                ((j) listener).b((i) this.a);
            }
        }
    }

    void a(o oVar) {
        super.a(oVar);
        if (this.a.getExpandedContainer() != null) {
            JSONObject e = bb.e(oVar.b(), "v4iap");
            JSONArray f = bb.f(e, "product_ids");
            an listener = this.a.getListener();
            if (listener != null) {
                if (this.b) {
                    ((j) listener).c((i) this.a);
                    if (e != null && f.length() > 0) {
                        ((j) listener).a((i) this.a, bb.b(f, 0), bb.b(e, "engagement_type"));
                    }
                } else {
                    ((af) listener).b(this.a);
                    if (e != null && f.length() > 0) {
                        ((af) listener).a(this.a, bb.b(f, 0), bb.b(e, "engagement_type"));
                    }
                }
            }
            ((ViewGroup) this.a.getExpandedContainer().getParent()).removeView(this.a.getExpandedContainer());
            n.a().h().a(this.a.getExpandedContainer());
            this.a.setExpandedContainer(null);
            System.gc();
        }
    }
}
