package com.b.a.a;

import android.annotation.TargetApi;
import android.os.Build.VERSION;
import b.a.a.a.a.d.a;
import com.d.a.a.c;
import com.tapjoy.TapjoyConstants;
import java.io.IOException;
import org.json.JSONObject;

/* compiled from: SessionEventTransform */
class u implements a<s> {
    u() {
    }

    public byte[] a(s sVar) {
        return b(sVar).toString().getBytes(c.DEFAULT_CHARSET);
    }

    @TargetApi(9)
    public JSONObject b(s sVar) {
        try {
            JSONObject jSONObject = new JSONObject();
            t tVar = sVar.a;
            jSONObject.put("appBundleId", tVar.a);
            jSONObject.put("executionId", tVar.b);
            jSONObject.put("installationId", tVar.c);
            jSONObject.put("androidId", tVar.d);
            jSONObject.put("advertisingId", tVar.e);
            jSONObject.put("limitAdTrackingEnabled", tVar.f);
            jSONObject.put("betaDeviceToken", tVar.g);
            jSONObject.put("buildId", tVar.h);
            jSONObject.put("osVersion", tVar.i);
            jSONObject.put("deviceModel", tVar.j);
            jSONObject.put("appVersionCode", tVar.k);
            jSONObject.put("appVersionName", tVar.l);
            jSONObject.put(TapjoyConstants.TJC_TIMESTAMP, sVar.b);
            jSONObject.put("type", sVar.c.toString());
            if (sVar.d != null) {
                jSONObject.put("details", new JSONObject(sVar.d));
            }
            jSONObject.put("customType", sVar.e);
            if (sVar.f != null) {
                jSONObject.put("customAttributes", new JSONObject(sVar.f));
            }
            jSONObject.put("predefinedType", sVar.g);
            if (sVar.h != null) {
                jSONObject.put("predefinedAttributes", new JSONObject(sVar.h));
            }
            return jSONObject;
        } catch (Throwable e) {
            if (VERSION.SDK_INT >= 9) {
                throw new IOException(e.getMessage(), e);
            }
            throw new IOException(e.getMessage());
        }
    }
}
