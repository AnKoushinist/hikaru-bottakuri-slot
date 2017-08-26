package com.tapjoy.internal;

import com.tapjoy.TJSpendCurrencyListener;

public class TJSpendCurrencyListenerNative implements TJSpendCurrencyListener {
    private final long a;

    private static native void onSpendCurrencyResponseFailureNative(long j, String str);

    private static native void onSpendCurrencyResponseNative(long j, String str, int i);

    private TJSpendCurrencyListenerNative(long j) {
        if (j == 0) {
            throw new IllegalArgumentException();
        }
        this.a = j;
    }

    public void onSpendCurrencyResponse(String str, int i) {
        onSpendCurrencyResponseNative(this.a, str, i);
    }

    public void onSpendCurrencyResponseFailure(String str) {
        onSpendCurrencyResponseFailureNative(this.a, str);
    }

    @ep
    static Object create(long j) {
        return new TJSpendCurrencyListenerNative(j);
    }
}
