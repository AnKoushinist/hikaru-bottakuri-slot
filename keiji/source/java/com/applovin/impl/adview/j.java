package com.applovin.impl.adview;

import org.cocos2dx.lib.BuildConfig;

class j implements Runnable {
    final /* synthetic */ AdViewControllerImpl a;

    private j(AdViewControllerImpl adViewControllerImpl) {
        this.a = adViewControllerImpl;
    }

    public void run() {
        try {
            this.a.i.loadDataWithBaseURL("/", "<html></html>", "text/html", null, BuildConfig.FLAVOR);
        } catch (Exception e) {
        }
    }
}
