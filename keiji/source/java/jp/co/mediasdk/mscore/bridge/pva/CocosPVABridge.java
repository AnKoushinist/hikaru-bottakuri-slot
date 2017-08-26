package jp.co.mediasdk.mscore.bridge.pva;

import jp.co.mediasdk.mscore.listener.pva.MSPVAListener;

public class CocosPVABridge implements MSPVAListener {
    private static native void cocosOnPVAMessage(String str);

    public void onPVAMessage(final String str) {
        new Thread(new Runnable(this) {
            final /* synthetic */ CocosPVABridge b;

            public void run() {
                CocosPVABridge.cocosOnPVAMessage(str);
            }
        }).start();
    }
}
