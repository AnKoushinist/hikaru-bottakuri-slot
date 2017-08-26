package com.applovin.adview;

class r implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ int b;
    final /* synthetic */ q c;

    r(q qVar, int i, int i2) {
        this.c = qVar;
        this.a = i;
        this.b = i2;
    }

    public void run() {
        this.c.a.e.d("AppLovinInterstitialActivity", "Video view error (" + this.a + "," + this.b + ").");
        this.c.a.k();
    }
}
