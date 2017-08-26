package jp.co.mediasdk.mscore.bridge.adformat;

import com.unity3d.player.UnityPlayer;
import jp.co.mediasdk.mscore.ui.adformat.MSAdFormatListener;
import jp.co.mediasdk.mscore.ui.adformat.MSAdParameterSupport;
import jp.co.mediasdk.mscore.ui.common.MSParameterSupport;

public class UnityAdBridge implements MSAdFormatListener {
    public void a(final String str) {
        new Thread(new Runnable(this) {
            final /* synthetic */ UnityAdBridge b;

            public void run() {
                UnityPlayer.UnitySendMessage(MSParameterSupport.a(MSAdParameterSupport.h), "MovieViewControllerOnMessage", str);
            }
        }).start();
    }
}
