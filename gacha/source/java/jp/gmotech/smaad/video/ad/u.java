package jp.gmotech.smaad.video.ad;

import jp.gmotech.smaad.video.ad.a.h;
import jp.gmotech.smaad.video.ad.b.a;
import twitter4j.HttpResponseCode;

class u implements h {
    final /* synthetic */ p a;
    final /* synthetic */ q b;

    u(q qVar, p pVar) {
        this.b = qVar;
        this.a = pVar;
    }

    public void a(long j, String str, int i, String str2) {
        if (i == HttpResponseCode.OK && j <= 400) {
            this.b.p = 0;
        }
        a.a("SmaAdVideoManager", "[checkPing] network status : " + (this.b.p == 0 ? "high" : "low"));
        try {
            this.b.b(this.a);
        } catch (Exception e) {
            a.a("SmaAdVideoManager", "[checkPing] Response " + e.getMessage());
        }
    }

    public void b(long j, String str, int i, String str2) {
    }
}
