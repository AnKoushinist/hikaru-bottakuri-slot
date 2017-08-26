package com.tapjoy.internal;

import com.tapjoy.TJAwardCurrencyListener;

public class TJAwardCurrencyListenerNative implements TJAwardCurrencyListener {
    private final long a;

    private static native void onAwardCurrencyResponseFailureNative(long j, String str);

    private static native void onAwardCurrencyResponseNative(long j, String str, int i);

    private TJAwardCurrencyListenerNative(long j) {
        if (j == 0) {
            throw new IllegalArgumentException();
        }
        this.a = j;
    }

    public void onAwardCurrencyResponse(String str, int i) {
        onAwardCurrencyResponseNative(this.a, str, i);
    }

    public void onAwardCurrencyResponseFailure(String str) {
        onAwardCurrencyResponseFailureNative(this.a, str);
    }

    @ep
    static Object create(long j) {
        return new TJAwardCurrencyListenerNative(j);
    }
}
