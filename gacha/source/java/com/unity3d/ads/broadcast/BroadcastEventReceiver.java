package com.unity3d.ads.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.unity3d.ads.log.DeviceLog;
import com.unity3d.ads.webview.WebViewApp;
import com.unity3d.ads.webview.WebViewEventCategory;
import org.cocos2dx.lib.BuildConfig;
import org.json.JSONException;
import org.json.JSONObject;

public class BroadcastEventReceiver extends BroadcastReceiver {
    private String _name;

    public BroadcastEventReceiver(String str) {
        this._name = str;
    }

    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action != null) {
            Object dataString;
            String str = BuildConfig.FLAVOR;
            if (intent.getDataString() != null) {
                dataString = intent.getDataString();
            } else {
                String str2 = str;
            }
            JSONObject jSONObject = new JSONObject();
            try {
                if (intent.getExtras() != null) {
                    Bundle extras = intent.getExtras();
                    for (String str3 : extras.keySet()) {
                        jSONObject.put(str3, extras.get(str3));
                    }
                }
            } catch (JSONException e) {
                DeviceLog.debug("JSONException when composing extras for broadcast action " + action + ": " + e.getMessage());
            }
            WebViewApp currentApp = WebViewApp.getCurrentApp();
            if (currentApp != null && currentApp.isWebAppLoaded()) {
                currentApp.sendEvent(WebViewEventCategory.BROADCAST, BroadcastEvent.ACTION, this._name, action, dataString, jSONObject);
            }
        }
    }
}
