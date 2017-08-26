package jp.gmotech.smaad.video.ad;

import jp.gmotech.smaad.video.ad.a.h;
import jp.gmotech.smaad.video.ad.b.a;

class m implements h {
    final /* synthetic */ SmaAdVideoActivity a;

    m(SmaAdVideoActivity smaAdVideoActivity) {
        this.a = smaAdVideoActivity;
    }

    public void a(long j, String str, int i, String str2) {
        a.a("SmaAdVideoActivity", "[requestStartUrl ResponseListener#onResponse] status : " + i);
        a.a("SmaAdVideoActivity", "[requestStartUrl ResponseListener#onResponse] response : " + str2);
        this.a.p = false;
        this.a.o = false;
    }

    public void b(long j, String str, int i, String str2) {
    }
}
