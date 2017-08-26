package jp.co.mediasdk.mscore.bridge.adformat;

import jp.co.mediasdk.mscore.ui.adformat.MSAdFormatListener;

public class CocosAdBridge implements MSAdFormatListener {
    private static native void cocosOnMessage(String str);

    public void a(final String str) {
        new Thread(new Runnable(this) {
            final /* synthetic */ CocosAdBridge b;

            public void run() {
                CocosAdBridge.cocosOnMessage(str);
            }
        }).start();
    }
}
