package com.vungle.publisher;

import com.vungle.log.Logger;
import com.vungle.publisher.cq.b;
import com.vungle.publisher.cq.c;
import com.vungle.publisher.fj.a;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public final class afy {
    @Inject
    ql a;
    @Inject
    public b b;
    @Inject
    public a c;
    @Inject
    kn.a d;
    @Inject
    hd.a e;
    @Inject
    public yc f;
    @Inject
    public py g;
    @Inject
    if.a h;
    @Inject
    gm.a i;

    @Inject
    afy() {
    }

    public final void a() {
        String z;
        try {
            List<cq> a = this.b.a();
            Logger.i(Logger.REPORT_TAG, "sending " + a.size() + " ad reports");
            for (cq cqVar : a) {
                z = cqVar.z();
                Logger.d(Logger.REPORT_TAG, "sending " + z);
                this.f.a(cqVar);
            }
            this.a.a(new ae());
        } catch (Throwable e) {
            this.i.a(Logger.REPORT_TAG, "error sending " + z, e);
            cqVar.a(c.failed);
            cqVar.b_();
        } catch (Throwable th) {
            this.a.a(new ae());
        }
    }
}
