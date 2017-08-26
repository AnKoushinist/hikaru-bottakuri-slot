package com.applovin.adview;

import android.app.Activity;
import com.applovin.impl.adview.InterstitialAdDialogCreatorImpl;
import com.applovin.sdk.AppLovinSdk;

final class x implements Runnable {
    final /* synthetic */ AppLovinSdk a;
    final /* synthetic */ Activity b;
    final /* synthetic */ String c;

    public void run() {
        new InterstitialAdDialogCreatorImpl().a(this.a, this.b).a(this.c);
    }
}
