package com.tapjoy.mraid.controller;

import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import com.tapjoy.TapjoyConstants;
import com.tapjoy.TapjoyLog;
import com.tapjoy.mraid.util.NetworkBroadcastReceiver;
import com.tapjoy.mraid.view.MraidView;
import twitter4j.TwitterResponse;

public class Network extends Abstract {
    private ConnectivityManager c;
    private int d;
    private NetworkBroadcastReceiver e;
    private IntentFilter f;

    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[State.values().length];

        static {
            try {
                a[State.UNKNOWN.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[State.DISCONNECTED.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    public Network(MraidView mraidView, Context context) {
        super(mraidView, context);
        this.c = (ConnectivityManager) context.getSystemService("connectivity");
    }

    public String getNetwork() {
        String str;
        NetworkInfo networkInfo = null;
        try {
            networkInfo = this.c.getActiveNetworkInfo();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String str2 = "unknown";
        if (networkInfo != null) {
            switch (AnonymousClass1.a[networkInfo.getState().ordinal()]) {
                case TwitterResponse.READ /*1*/:
                    str = "unknown";
                    break;
                case TwitterResponse.READ_WRITE /*2*/:
                    str = "offline";
                    break;
                default:
                    int type = networkInfo.getType();
                    if (type != 0) {
                        if (type != 1) {
                            str = str2;
                            break;
                        }
                        str = TapjoyConstants.TJC_CONNECTION_TYPE_WIFI;
                        break;
                    }
                    str = "cell";
                    break;
            }
        }
        str = "offline";
        TapjoyLog.d("MRAID Network", "getNetwork: " + str);
        return str;
    }

    public void startNetworkListener() {
        if (this.d == 0) {
            this.e = new NetworkBroadcastReceiver(this);
            this.f = new IntentFilter();
            this.f.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        }
        this.d++;
        this.b.registerReceiver(this.e, this.f);
    }

    public void stopNetworkListener() {
        this.d--;
        if (this.d == 0) {
            this.b.unregisterReceiver(this.e);
            this.e = null;
            this.f = null;
        }
    }

    public void onConnectionChanged() {
        String str = "window.mraidview.fireChangeEvent({ network: '" + getNetwork() + "'});";
        TapjoyLog.d("MRAID Network", str);
        this.a.injectMraidJavaScript(str);
    }

    public void stopAllListeners() {
        this.d = 0;
        try {
            this.b.unregisterReceiver(this.e);
        } catch (Exception e) {
        }
    }
}
