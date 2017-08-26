package jp.maio.sdk.android;

import android.app.Activity;

final class al implements Runnable {
    final /* synthetic */ Activity a;
    final /* synthetic */ String b;
    final /* synthetic */ MaioAdsListenerInterface c;

    al(Activity activity, String str, MaioAdsListenerInterface maioAdsListenerInterface) {
        this.a = activity;
        this.b = str;
        this.c = maioAdsListenerInterface;
    }

    public void run() {
        MaioAds.b.a(this.a, this.b, this.c);
    }
}
