package com.unity3d.ads.broadcast;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import com.unity3d.ads.properties.ClientProperties;
import java.util.HashMap;
import java.util.Map;

public class BroadcastMonitor {
    private static Map<String, BroadcastEventReceiver> _eventReceivers;

    public static void addBroadcastListener(String str, String str2, String[] strArr) {
        removeBroadcastListener(str);
        IntentFilter intentFilter = new IntentFilter();
        for (String addAction : strArr) {
            intentFilter.addAction(addAction);
        }
        if (str2 != null) {
            intentFilter.addDataScheme(str2);
        }
        if (_eventReceivers == null) {
            _eventReceivers = new HashMap();
        }
        BroadcastReceiver broadcastEventReceiver = new BroadcastEventReceiver(str);
        _eventReceivers.put(str, broadcastEventReceiver);
        ClientProperties.getApplicationContext().registerReceiver(broadcastEventReceiver, intentFilter);
    }

    public static void removeBroadcastListener(String str) {
        if (_eventReceivers != null && _eventReceivers.containsKey(str)) {
            ClientProperties.getApplicationContext().unregisterReceiver((BroadcastReceiver) _eventReceivers.get(str));
            _eventReceivers.remove(str);
        }
    }

    public static void removeAllBroadcastListeners() {
        if (_eventReceivers != null) {
            for (String str : _eventReceivers.keySet()) {
                ClientProperties.getApplicationContext().unregisterReceiver((BroadcastReceiver) _eventReceivers.get(str));
            }
            _eventReceivers = null;
        }
    }
}
