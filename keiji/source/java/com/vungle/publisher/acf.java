package com.vungle.publisher;

import com.tapjoy.TapjoyConstants;
import java.util.Arrays;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import jp.co.geniee.gnsrewardadapter.GNSAdapterVungleRewardVideoAd;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: vungle */
public final class acf extends abd {
    public List<gm> a;
    @Inject
    pn b;
    @Inject
    pu c;

    @Singleton
    /* compiled from: vungle */
    public static class a {
        @Inject
        public Provider<acf> a;

        @Inject
        a() {
        }
    }

    public final /* synthetic */ Object b() {
        return a();
    }

    @Inject
    acf() {
    }

    public final JSONArray a() {
        JSONArray a = super.a();
        for (gm gmVar : this.a) {
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            String[] strArr = gmVar.b;
            Object obj = null;
            if (strArr != null) {
                obj = new JSONArray(Arrays.asList(strArr));
            }
            jSONObject.putOpt("code", Integer.valueOf(gmVar.c));
            jSONObject.putOpt(TapjoyConstants.TJC_TIMESTAMP, Long.valueOf(gmVar.a));
            jSONObject.putOpt("stack_trace", obj);
            jSONObject.putOpt("tag", gmVar.d);
            jSONObject.putOpt("log_message", gmVar.e);
            jSONObject.putOpt("exception_class", gmVar.f);
            jSONObject.putOpt(TapjoyConstants.TJC_PLATFORM, TapjoyConstants.TJC_DEVICE_PLATFORM_TYPE);
            jSONObject.putOpt("model", this.b.m());
            jSONObject.putOpt(TapjoyConstants.TJC_DEVICE_OS_VERSION_NAME, gmVar.g);
            jSONObject.putOpt("sdk_version", gmVar.h);
            jSONObject.putOpt(GNSAdapterVungleRewardVideoAd.APP_ID_COLUMN_NAME, this.c.b());
            jSONObject2.putOpt("play_services_version", gmVar.i);
            jSONObject.putOpt("platform_specific", jSONObject2);
            a.put(jSONObject);
        }
        return a;
    }
}
