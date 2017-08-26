package com.unity3d.ads.request;

import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import com.tapjoy.TJAdUnitConstants.String;
import com.unity3d.ads.log.DeviceLog;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WebRequestResultReceiver extends ResultReceiver {
    public static final int RESULT_FAILED = 2;
    public static final int RESULT_SUCCESS = 1;
    private IWebRequestListener _listener;

    public WebRequestResultReceiver(Handler handler, IWebRequestListener iWebRequestListener) {
        super(handler);
        this._listener = iWebRequestListener;
    }

    protected void onReceiveResult(int i, Bundle bundle) {
        DeviceLog.entered();
        if (this._listener != null) {
            switch (i) {
                case RESULT_SUCCESS /*1*/:
                    String string = bundle.getString(String.URL);
                    bundle.remove(String.URL);
                    String string2 = bundle.getString("response");
                    bundle.remove("response");
                    int i2 = bundle.getInt("responseCode");
                    bundle.remove("responseCode");
                    this._listener.onComplete(string, string2, i2, getResponseHeaders(bundle));
                    break;
                case RESULT_FAILED /*2*/:
                    this._listener.onFailed(bundle.getString(String.URL), bundle.getString(String.VIDEO_ERROR));
                    break;
                default:
                    DeviceLog.error("Unhandled resultCode: " + i);
                    this._listener.onFailed(bundle.getString(String.URL), "Invalid resultCode=" + i);
                    break;
            }
        }
        super.onReceiveResult(i, bundle);
    }

    private Map<String, List<String>> getResponseHeaders(Bundle bundle) {
        if (bundle.size() <= 0) {
            return null;
        }
        Map<String, List<String>> hashMap = new HashMap();
        for (String str : bundle.keySet()) {
            String[] stringArray = bundle.getStringArray(str);
            if (stringArray != null) {
                hashMap.put(str, new ArrayList(Arrays.asList(stringArray)));
            }
        }
        return hashMap;
    }
}
