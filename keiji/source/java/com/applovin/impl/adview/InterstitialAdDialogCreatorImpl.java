package com.applovin.impl.adview;

import android.app.Activity;
import com.applovin.adview.AppLovinInterstitialAdDialog;
import com.applovin.adview.InterstitialAdDialogCreator;
import com.applovin.sdk.AppLovinSdk;
import java.lang.ref.WeakReference;

public class InterstitialAdDialogCreatorImpl implements InterstitialAdDialogCreator {
    private static final Object a = new Object();
    private static WeakReference b = new WeakReference(null);
    private static WeakReference c = new WeakReference(null);

    public AppLovinInterstitialAdDialog a(AppLovinSdk appLovinSdk, Activity activity) {
        AppLovinInterstitialAdDialog appLovinInterstitialAdDialog;
        if (appLovinSdk == null) {
            appLovinSdk = AppLovinSdk.c(activity);
        }
        synchronized (a) {
            appLovinInterstitialAdDialog = (ah) b.get();
            if (appLovinInterstitialAdDialog != null && appLovinInterstitialAdDialog.h() && c.get() == activity) {
                appLovinSdk.h().c("InterstitialAdDialogCreator", "An interstitial dialog is already showing, returning it");
            } else {
                appLovinInterstitialAdDialog = new ah(appLovinSdk, activity);
                b = new WeakReference(appLovinInterstitialAdDialog);
                c = new WeakReference(activity);
            }
        }
        return appLovinInterstitialAdDialog;
    }
}
