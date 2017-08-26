package com.unity3d.ads.request;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.moat.analytics.mobile.tjy.MoatAdEvent;
import com.tapjoy.TJAdUnitConstants.String;
import com.unity3d.ads.log.DeviceLog;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class WebRequestHandler extends Handler {
    private boolean _canceled = false;
    private WebRequest _currentRequest;

    public void handleMessage(Message message) {
        Bundle data = message.getData();
        String string = data.getString(String.URL);
        data.remove(String.URL);
        String string2 = data.getString(MoatAdEvent.EVENT_TYPE);
        data.remove(MoatAdEvent.EVENT_TYPE);
        String string3 = data.getString("body");
        data.remove("body");
        WebRequestResultReceiver webRequestResultReceiver = (WebRequestResultReceiver) data.getParcelable("receiver");
        data.remove("receiver");
        int i = data.getInt("connectTimeout");
        data.remove("connectTimeout");
        int i2 = data.getInt("readTimeout");
        data.remove("readTimeout");
        HashMap hashMap = null;
        if (data.size() > 0) {
            DeviceLog.debug("There are headers left in data, reading them");
            hashMap = new HashMap();
            for (String str : data.keySet()) {
                hashMap.put(str, Arrays.asList(data.getStringArray(str)));
            }
        }
        if (message.what == 1) {
            DeviceLog.debug("Handling request message: " + string + " type=" + string2);
            try {
                makeRequest(string, string2, hashMap, string3, i, i2, webRequestResultReceiver);
                return;
            } catch (Exception e) {
                DeviceLog.exception("Malformed URL", e);
                if (webRequestResultReceiver != null) {
                    webRequestResultReceiver.send(2, getBundleForFailResult(string, "Malformed URL", string2, string3));
                    return;
                }
                return;
            }
        }
        DeviceLog.error("No implementation for message: " + message.what);
        if (webRequestResultReceiver != null) {
            webRequestResultReceiver.send(2, getBundleForFailResult(string, "Invalid Thread Message", string2, string3));
        }
    }

    public void setCancelStatus(boolean z) {
        this._canceled = z;
        if (this._canceled && this._currentRequest != null) {
            this._currentRequest.cancel();
        }
    }

    private void makeRequest(String str, String str2, HashMap<String, List<String>> hashMap, String str3, int i, int i2, WebRequestResultReceiver webRequestResultReceiver) {
        Exception e;
        if (!this._canceled) {
            this._currentRequest = new WebRequest(str, str2, hashMap, i, i2);
            if (str3 != null) {
                this._currentRequest.setBody(str3);
            }
            try {
                String makeRequest = this._currentRequest.makeRequest();
                if (!this._currentRequest.isCanceled()) {
                    Bundle bundle = new Bundle();
                    bundle.putString("response", makeRequest);
                    bundle.putString(String.URL, str);
                    bundle.putInt("responseCode", this._currentRequest.getResponseCode());
                    for (String makeRequest2 : this._currentRequest.getResponseHeaders().keySet()) {
                        if (!(makeRequest2 == null || makeRequest2.contentEquals("null"))) {
                            String[] strArr = new String[((List) this._currentRequest.getResponseHeaders().get(makeRequest2)).size()];
                            for (int i3 = 0; i3 < ((List) this._currentRequest.getResponseHeaders().get(makeRequest2)).size(); i3++) {
                                strArr[i3] = (String) ((List) this._currentRequest.getResponseHeaders().get(makeRequest2)).get(i3);
                            }
                            bundle.putStringArray(makeRequest2, strArr);
                        }
                    }
                    webRequestResultReceiver.send(1, bundle);
                }
            } catch (IOException e2) {
                e = e2;
                DeviceLog.exception("Error completing request", e);
                webRequestResultReceiver.send(2, getBundleForFailResult(str, e.getMessage(), str2, str3));
            } catch (IllegalStateException e3) {
                e = e3;
                DeviceLog.exception("Error completing request", e);
                webRequestResultReceiver.send(2, getBundleForFailResult(str, e.getMessage(), str2, str3));
            }
        }
    }

    private Bundle getBundleForFailResult(String str, String str2, String str3, String str4) {
        Bundle bundle = new Bundle();
        bundle.putString(String.URL, str);
        bundle.putString(String.VIDEO_ERROR, str2);
        bundle.putString(MoatAdEvent.EVENT_TYPE, str3);
        bundle.putString("body", str4);
        return bundle;
    }
}
