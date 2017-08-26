package org.cocos2dx.lib;

import java.util.concurrent.CountDownLatch;

/* compiled from: Cocos2dxWebView */
class ShouldStartLoadingWorker implements Runnable {
    private CountDownLatch mLatch;
    private boolean[] mResult;
    private final String mUrlString;
    private final int mViewTag;

    ShouldStartLoadingWorker(CountDownLatch countDownLatch, boolean[] zArr, int i, String str) {
        this.mLatch = countDownLatch;
        this.mResult = zArr;
        this.mViewTag = i;
        this.mUrlString = str;
    }

    public void run() {
        this.mResult[0] = Cocos2dxWebViewHelper._shouldStartLoading(this.mViewTag, this.mUrlString);
        this.mLatch.countDown();
    }
}
