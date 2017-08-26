package com.igaworks.interfaces;

import android.content.Context;

public interface CommonInterface {
    void endSession();

    void onReceiveReferral(Context context, String str);

    void startApplication(Context context);

    void startSession(Context context);
}
