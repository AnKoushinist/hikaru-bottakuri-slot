package com.tapjoy.internal;

import com.tapjoy.TJEarnedCurrencyListener;

public class TJEarnedCurrencyListenerNative implements TJEarnedCurrencyListener {
    private final long a;

    private static native void onEarnedCurrencyNative(long j, String str, int i);

    private TJEarnedCurrencyListenerNative(long j) {
        if (j == 0) {
            throw new IllegalArgumentException();
        }
        this.a = j;
    }

    public void onEarnedCurrency(String str, int i) {
        onEarnedCurrencyNative(this.a, str, i);
    }

    @eu
    static Object create(long j) {
        return new TJEarnedCurrencyListenerNative(j);
    }
}
