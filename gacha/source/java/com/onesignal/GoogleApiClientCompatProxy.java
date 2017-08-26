package com.onesignal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.tapjoy.TapjoyConstants;

public class GoogleApiClientCompatProxy {
    private final GoogleApiClient googleApiClient;
    private final Class googleApiClientListenerClass;

    public GoogleApiClientCompatProxy(GoogleApiClient googleApiClient) {
        this.googleApiClient = googleApiClient;
        this.googleApiClientListenerClass = googleApiClient.getClass();
    }

    public void connect() {
        try {
            this.googleApiClientListenerClass.getMethod(TapjoyConstants.TJC_SDK_TYPE_CONNECT, new Class[0]).invoke(this.googleApiClient, new Object[0]);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void disconnect() {
        try {
            this.googleApiClientListenerClass.getMethod("disconnect", new Class[0]).invoke(this.googleApiClient, new Object[0]);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public GoogleApiClient realInstance() {
        return this.googleApiClient;
    }
}
