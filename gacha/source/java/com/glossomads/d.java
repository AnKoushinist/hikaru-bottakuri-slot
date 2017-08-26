package com.glossomads;

import com.glossomads.Logger.SugarLogger;
import com.glossomads.Model.c;
import com.glossomads.Model.f;
import com.glossomads.c.d.b;
import com.glossomads.c.e;
import org.cocos2dx.lib.BuildConfig;
import org.json.JSONException;

public class d implements b {
    private f a;
    private a b;

    public interface a {
        void a(String str, c cVar);

        void a(String str, String str2, boolean z);
    }

    public d(f fVar) {
        this.a = fVar;
    }

    public void a() {
        if (com.glossomads.c.d.a(this, l.d(), com.glossomads.c.d.a.POST, this.a.c(), Constants.IP_RETRY_TIME, Constants.IP_RETRY_TIME)) {
            com.glossomads.Logger.a.g(com.glossomads.Logger.a.a.loadAdRequestParams, this.a.c().toString());
            return;
        }
        a(false, "loader failed  (reason = The number of threads exceeded the restriction value.)");
    }

    public void a(e eVar) {
        Object obj;
        String str = null;
        c cVar;
        if (eVar.b || !eVar.a) {
            String c;
            if (eVar.b) {
                c = eVar.c();
            } else {
                try {
                    cVar = new c(eVar.c);
                    if (!cVar.f().equals(BuildConfig.FLAVOR)) {
                        SugarLogger.d(cVar.f());
                    }
                    c = SugarUtil.isEmptyValue(cVar.f()) ? eVar.c() : cVar.f();
                } catch (com.glossomads.b.a e) {
                    c = e.getMessage();
                } catch (JSONException e2) {
                    c = e2.getMessage();
                }
            }
            a(eVar.b, c);
            return;
        }
        try {
            cVar = new c(eVar.c);
        } catch (com.glossomads.b.a e3) {
            str = e3.getMessage();
            obj = null;
        } catch (JSONException e22) {
            str = e22.getMessage();
            obj = null;
        }
        if (cVar == null) {
            a(false, str);
        } else {
            a(cVar);
        }
    }

    private void a(c cVar) {
        if (this.b != null) {
            this.b.a(this.a.a(), cVar);
        }
    }

    private void a(boolean z, String str) {
        if (this.b != null) {
            this.b.a(this.a.a(), str, z);
        }
    }

    public void a(a aVar) {
        this.b = aVar;
    }
}
