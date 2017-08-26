package com.unity3d.ads.api;

import com.unity3d.ads.UnityAds;
import com.unity3d.ads.UnityAds.FinishState;
import com.unity3d.ads.UnityAds.UnityAdsError;
import com.unity3d.ads.misc.Utilities;
import com.unity3d.ads.webview.bridge.WebViewCallback;
import com.unity3d.ads.webview.bridge.WebViewExposed;

public class Listener {
    @WebViewExposed
    public static void sendReadyEvent(final String str, WebViewCallback webViewCallback) {
        if (UnityAds.getListener() != null) {
            Utilities.runOnUiThread(new Runnable() {
                public void run() {
                    UnityAds.getListener().onUnityAdsReady(str);
                }
            });
        }
        webViewCallback.invoke(new Object[0]);
    }

    @WebViewExposed
    public static void sendStartEvent(final String str, WebViewCallback webViewCallback) {
        if (UnityAds.getListener() != null) {
            Utilities.runOnUiThread(new Runnable() {
                public void run() {
                    UnityAds.getListener().onUnityAdsStart(str);
                }
            });
        }
        webViewCallback.invoke(new Object[0]);
    }

    @WebViewExposed
    public static void sendFinishEvent(final String str, final String str2, WebViewCallback webViewCallback) {
        if (UnityAds.getListener() != null) {
            Utilities.runOnUiThread(new Runnable() {
                public void run() {
                    UnityAds.getListener().onUnityAdsFinish(str, FinishState.valueOf(str2));
                }
            });
        }
        webViewCallback.invoke(new Object[0]);
    }

    @WebViewExposed
    public static void sendErrorEvent(final String str, final String str2, WebViewCallback webViewCallback) {
        if (UnityAds.getListener() != null) {
            Utilities.runOnUiThread(new Runnable() {
                public void run() {
                    UnityAds.getListener().onUnityAdsError(UnityAdsError.valueOf(str), str2);
                }
            });
        }
        webViewCallback.invoke(new Object[0]);
    }
}
