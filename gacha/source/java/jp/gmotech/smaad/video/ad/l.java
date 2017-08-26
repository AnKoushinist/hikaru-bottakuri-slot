package jp.gmotech.smaad.video.ad;

import jp.gmotech.smaad.video.ad.a.h;
import jp.gmotech.smaad.video.ad.b.a;

class l implements h {
    final /* synthetic */ SmaAdVideoActivity a;

    l(SmaAdVideoActivity smaAdVideoActivity) {
        this.a = smaAdVideoActivity;
    }

    public void a(long j, String str, int i, String str2) {
        a.a("SmaAdVideoActivity", "[requestClickThroughUrl ResponseListener#onResponse] status : " + i);
        a.a("SmaAdVideoActivity", "[requestClickThroughUrl ResponseListener#onResponse] response : " + str2);
        this.a.t = false;
        this.a.s = false;
    }

    public void b(long j, String str, int i, String str2) {
    }
}
