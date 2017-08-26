package com.b.a.c;

import a.a.a.a.a.b.a;
import a.a.a.a.a.b.r;
import a.a.a.a.a.e.c;
import a.a.a.a.a.e.d;
import a.a.a.a.a.e.e;
import a.a.a.a.i;
import com.tapjoy.TapjoyConstants;
import java.util.Map.Entry;

/* compiled from: DefaultCreateReportSpiCall */
class o extends a implements n {
    public o(i iVar, String str, String str2, e eVar) {
        super(iVar, str, str2, eVar, c.POST);
    }

    public boolean a(m mVar) {
        d b = b(a(b(), mVar), mVar);
        a.a.a.a.c.h().a("CrashlyticsCore", "Sending report to: " + a());
        int b2 = b.b();
        a.a.a.a.c.h().a("CrashlyticsCore", "Create report request ID: " + b.b("X-REQUEST-ID"));
        a.a.a.a.c.h().a("CrashlyticsCore", "Result was: " + b2);
        return r.a(b2) == 0;
    }

    private d a(d dVar, m mVar) {
        d a = dVar.a("X-CRASHLYTICS-API-KEY", mVar.a).a("X-CRASHLYTICS-API-CLIENT-TYPE", TapjoyConstants.TJC_DEVICE_PLATFORM_TYPE).a("X-CRASHLYTICS-API-CLIENT-VERSION", f.f().a());
        d dVar2 = a;
        for (Entry a2 : mVar.b.e().entrySet()) {
            dVar2 = dVar2.a(a2);
        }
        return dVar2;
    }

    private d b(d dVar, m mVar) {
        z zVar = mVar.b;
        return dVar.a("report[file]", zVar.b(), "application/octet-stream", zVar.d()).e("report[identifier]", zVar.c());
    }
}
