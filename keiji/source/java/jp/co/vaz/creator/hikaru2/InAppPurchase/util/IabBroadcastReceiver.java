package jp.co.vaz.creator.hikaru2.InAppPurchase.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class IabBroadcastReceiver extends BroadcastReceiver {
    private final IabBroadcastListener a;

    public interface IabBroadcastListener {
        void receivedBroadcast();
    }

    public IabBroadcastReceiver(IabBroadcastListener iabBroadcastListener) {
        this.a = iabBroadcastListener;
    }

    public void onReceive(Context context, Intent intent) {
        if (this.a != null) {
            this.a.receivedBroadcast();
        }
    }
}
