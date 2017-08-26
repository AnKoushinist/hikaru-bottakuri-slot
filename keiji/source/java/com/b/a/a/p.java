package com.b.a.a;

import a.a.a.a.a.b.a;
import a.a.a.a.a.b.r;
import a.a.a.a.a.d.f;
import a.a.a.a.a.e.c;
import a.a.a.a.a.e.d;
import a.a.a.a.a.e.e;
import a.a.a.a.i;
import com.tapjoy.TapjoyConstants;
import java.io.File;
import java.util.List;

/* compiled from: SessionAnalyticsFilesSender */
class p extends a implements f {
    private final String b;

    public p(i iVar, String str, String str2, e eVar, String str3) {
        super(iVar, str, str2, eVar, c.POST);
        this.b = str3;
    }

    public boolean a(List<File> list) {
        d a = b().a("X-CRASHLYTICS-API-CLIENT-TYPE", TapjoyConstants.TJC_DEVICE_PLATFORM_TYPE).a("X-CRASHLYTICS-API-CLIENT-VERSION", this.a.a()).a("X-CRASHLYTICS-API-KEY", this.b);
        int i = 0;
        for (File file : list) {
            a.a("session_analytics_file_" + i, file.getName(), "application/vnd.crashlytics.android.events", file);
            i++;
        }
        a.a.a.a.c.h().a("Answers", "Sending " + list.size() + " analytics files to " + a());
        int b = a.b();
        a.a.a.a.c.h().a("Answers", "Response code for analytics file send is " + b);
        if (r.a(b) == 0) {
            return true;
        }
        return false;
    }
}
