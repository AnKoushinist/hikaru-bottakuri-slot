package com.glossomads.Model;

import com.glossomads.Logger.SugarDebugLogger;
import com.glossomads.SugarUtil;
import com.glossomads.c;
import com.tapjoy.TJAdUnitConstants.String;
import java.io.File;
import org.cocos2dx.lib.BuildConfig;
import org.json.JSONObject;

public class d implements c {
    private String a;
    private e b = new e();
    private a c = a.INIT;

    public enum a {
        INIT,
        WAIT,
        DOWNLOADING,
        READY,
        DELETE,
        DOWNLOAD_ERROR
    }

    public /* synthetic */ c c(String str) {
        return b(str);
    }

    public void a(String str) {
        this.a = str;
    }

    public void a(a aVar) {
        this.c = aVar;
    }

    public a a() {
        return this.c;
    }

    public e b() {
        return this.b;
    }

    public String toString() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(String.URL, this.a);
            jSONObject.put("fileDir", this.b.a());
            jSONObject.put("fileExtension", this.b.b());
            jSONObject.put("state", this.c);
            return jSONObject.toString();
        } catch (Exception e) {
            return BuildConfig.FLAVOR;
        }
    }

    public d b(String str) {
        if (!SugarUtil.isEmptyValue(str)) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                this.a = jSONObject.optString(String.URL);
                this.b.a(jSONObject.optString("fileDir"));
                this.b.b(jSONObject.optString("fileExtension"));
                this.c = a.valueOf(jSONObject.optString("state"));
                String a = this.b.a();
                if (SugarUtil.isNotEmptyValue(a)) {
                    File file = new File(a);
                    if (!this.b.h() && this.c == a.READY) {
                        SugarDebugLogger.d("Integrity error status and fileDir");
                        this.c = a.INIT;
                    }
                }
                SugarDebugLogger.d("Asset from String: " + this.a + ": " + this.b.c() + ": " + this.c);
            } catch (Exception e) {
                SugarDebugLogger.e("cannot parse from string: " + str);
                SugarDebugLogger.d(SugarUtil.getStackTrace(e));
            }
        }
        return this;
    }
}
