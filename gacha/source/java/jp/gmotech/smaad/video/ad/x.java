package jp.gmotech.smaad.video.ad;

import jp.gmotech.smaad.video.ad.b.a;

class x implements Runnable {
    final /* synthetic */ v a;

    private x(v vVar) {
        this.a = vVar;
    }

    public void run() {
        a.a("SmaAdVideoManager", "[GetAdThread] isPausing: " + this.a.a.v);
        this.a.a.g();
        a.a("SmaAdVideoManager", "[GetAdThread] END");
    }
}
