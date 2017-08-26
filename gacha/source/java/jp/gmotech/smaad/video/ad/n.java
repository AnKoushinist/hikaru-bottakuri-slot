package jp.gmotech.smaad.video.ad;

import jp.gmotech.smaad.video.ad.a.h;
import jp.gmotech.smaad.video.ad.b.a;

class n implements h {
    final /* synthetic */ SmaAdVideoActivity a;

    n(SmaAdVideoActivity smaAdVideoActivity) {
        this.a = smaAdVideoActivity;
    }

    public void a(long j, String str, int i, String str2) {
        a.a("SmaAdVideoActivity", "[requestCompleteUrl ResponseListener#onResponse] status : " + i);
        a.a("SmaAdVideoActivity", "[requestCompleteUrl ResponseListener#onResponse] response : " + str2);
        this.a.r = false;
        this.a.q = false;
    }

    public void b(long j, String str, int i, String str2) {
    }
}
