package com.tapjoy.mediation;

import com.tapjoy.TapjoyErrorMessage;
import com.tapjoy.TapjoyErrorMessage.ErrorType;
import com.tapjoy.TapjoyLog;

public class TJMediationSettings {
    private static final String a = TJMediationSettings.class.getSimpleName();
    private static TJMediationSettings b;
    private long c;

    private TJMediationSettings() {
        a(20);
        TapjoyLog.d(a, "Default mediation timeout set to 20s");
    }

    public static TJMediationSettings getInstance() {
        if (b == null) {
            b = new TJMediationSettings();
        }
        return b;
    }

    public void setTimeout(String str) {
        try {
            a(Integer.parseInt(str));
        } catch (NumberFormatException e) {
            TapjoyLog.e(a, new TapjoyErrorMessage(ErrorType.INTEGRATION_ERROR, "Invalid type! Make sure to pass in an `int` type for the `TapjoyConnectFlag.MEDIATION_TIMEOUT` value"));
        }
    }

    private void a(int i) {
        if (i < 0) {
            TapjoyLog.e(a, new TapjoyErrorMessage(ErrorType.INTEGRATION_ERROR, "Invalid type! Make sure to pass in a positive value for the `TapjoyConnectFlag.MEDIATION_TIMEOUT`"));
        } else {
            this.c = ((long) i) * 1000;
        }
    }

    public long getTimeout() {
        TapjoyLog.d(a, "Mediation timeout set to: " + this.c + "ms");
        return this.c;
    }
}
