package com.applovin.impl.sdk;

import android.net.Uri;
import android.net.Uri.Builder;
import com.applovin.sdk.AppLovinEventService;
import com.applovin.sdk.AppLovinSdk;
import com.tapjoy.TapjoyConstants;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import jp.co.geniee.gnsrewardadapter.GNSAdapterAppLovinRewardVideoAd;
import jp.co.geniee.gnsrewardadapter.GNSAdapterTapjoyRewardVideoAd;

public class EventServiceImpl implements AppLovinEventService {
    private final AppLovinSdkImpl a;
    private final List b;

    public EventServiceImpl(AppLovinSdk appLovinSdk) {
        this.a = (AppLovinSdkImpl) appLovinSdk;
        this.b = Arrays.asList(((String) ((AppLovinSdkImpl) appLovinSdk).a(cb.bc)).split(","));
    }

    private Uri a(bx bxVar, s sVar) {
        r v = this.a.v();
        u a = v.a();
        t b = v.b();
        boolean contains = this.b.contains(bxVar.a());
        Builder appendQueryParameter = Uri.parse((String) this.a.a(cb.bb)).buildUpon().appendQueryParameter(TapjoyConstants.TJC_SDK_TYPE_DEFAULT, contains ? bxVar.a() : "postinstall").appendQueryParameter("ts", Long.toString(bxVar.c())).appendQueryParameter(TapjoyConstants.TJC_PLATFORM, "Android").appendQueryParameter("model", a.a).appendQueryParameter(GNSAdapterAppLovinRewardVideoAd.PACKAGE_NAME_COLUMN_NAME, b.c).appendQueryParameter(GNSAdapterTapjoyRewardVideoAd.SDK_KEY_COLUMN_NAME, this.a.a()).appendQueryParameter("idfa", sVar.b).appendQueryParameter("dnt", Boolean.toString(sVar.a)).appendQueryParameter("ia", Long.toString(b.d)).appendQueryParameter("api_did", (String) this.a.a(cb.c)).appendQueryParameter("brand", a.c).appendQueryParameter("model", a.a).appendQueryParameter("revision", a.d).appendQueryParameter("sdk_version", "6.3.2").appendQueryParameter("os", a.b).appendQueryParameter("orientation_lock", a.i).appendQueryParameter(TapjoyConstants.TJC_APP_VERSION_NAME, this.a.v().b().b).appendQueryParameter(TapjoyConstants.TJC_DEVICE_COUNTRY_CODE, a.f).appendQueryParameter("carrier", a.g).appendQueryParameter("tz_offset", String.valueOf(a.j));
        if (!contains) {
            appendQueryParameter = appendQueryParameter.appendQueryParameter("sub_event", bxVar.a());
        }
        return appendQueryParameter.build();
    }

    private Map a(Map map) {
        Map hashMap = new HashMap();
        if (map != null) {
            for (Entry entry : map.entrySet()) {
                Object key = entry.getKey();
                Object value = entry.getValue();
                if ((key instanceof String) && (value instanceof String)) {
                    hashMap.put((String) key, (String) value);
                } else {
                    this.a.h().c("EventServiceImpl", "Unexpected class type in trackEvent(); all keys and values passed as parameters must be String. Encountered " + key.getClass().getCanonicalName() + "/" + value.getClass().getCanonicalName() + "; will use toString() value instead, which may be unexpected...");
                    hashMap.put(key.toString(), value.toString());
                }
            }
        }
        return hashMap;
    }

    private void a(bx bxVar) {
        if (((Boolean) this.a.a(cb.bd)).booleanValue()) {
            this.a.h().a("EventServiceImpl", "Tracking event: " + bxVar);
            a(new v(this, bxVar));
        }
    }

    private void a(co coVar) {
        this.a.m().a(new cn(this.a, coVar), cw.BACKGROUND);
    }

    public void a(String str) {
        a(str, new HashMap());
    }

    public void a(String str, Map map) {
        a(new bx(str, a(map), System.currentTimeMillis(), dm.b(UUID.randomUUID().toString())));
    }
}
