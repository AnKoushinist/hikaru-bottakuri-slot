package jp.co.mediasdk.mscore.bridge.pva;

import com.unity3d.player.UnityPlayer;
import jp.co.mediasdk.mscore.listener.pva.MSPVAListener;
import jp.co.mediasdk.mscore.ui.common.MSParameterSupport;

public class UnityPVABridge implements MSPVAListener {
    public void onPVAMessage(final String str) {
        new Thread(new Runnable(this) {
            final /* synthetic */ UnityPVABridge b;

            public void run() {
                UnityPlayer.UnitySendMessage(MSParameterSupport.a("game_object_name"), "PVAOnMessage", str);
            }
        }).start();
    }
}
