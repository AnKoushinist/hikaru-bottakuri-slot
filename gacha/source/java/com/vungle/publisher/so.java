package com.vungle.publisher;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.a;
import com.google.android.gms.common.api.GoogleApiClient.b;
import com.google.android.gms.common.api.GoogleApiClient.c;
import com.google.android.gms.location.h;
import com.vungle.log.Logger;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public final class so extends si<GoogleApiClient> implements b, c, sq {
    Context b;

    protected final /* synthetic */ boolean a(Object obj) {
        return ((GoogleApiClient) obj).isConnected();
    }

    public final /* bridge */ /* synthetic */ Location b() {
        return super.b();
    }

    protected final /* synthetic */ void b(Object obj) {
        ((GoogleApiClient) obj).connect();
    }

    protected final /* synthetic */ Location c(Object obj) {
        return h.b.getLastLocation((GoogleApiClient) obj);
    }

    protected final /* synthetic */ Object c() {
        Context context = this.b;
        return new a(context).a(h.a).a(this).a(this).b();
    }

    protected final /* synthetic */ void d(Object obj) {
        ((GoogleApiClient) obj).disconnect();
    }

    public so(Context context) {
        this.b = context;
    }

    protected final String a() {
        return "Google Play Services LocationServices";
    }

    public final void onConnected(Bundle bundle) {
        super.d();
    }

    public final void onConnectionSuspended(int i) {
        Logger.v(Logger.LOCATION_TAG, "connection suspended for Google Play Services LocationServices " + this.a);
    }

    public final void onConnectionFailed(ConnectionResult connectionResult) {
        super.onConnectionFailed(connectionResult);
    }
}
