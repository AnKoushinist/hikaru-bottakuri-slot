package com.tapjoy.internal;

import com.tapjoy.TJVideoListener;

public class TJVideoListenerNative implements TJVideoListener {
    private final long a;

    private static native void onVideoCompleteNative(long j);

    private static native void onVideoErrorNative(long j, int i);

    private static native void onVideoStartNative(long j);

    private TJVideoListenerNative(long j) {
        if (j == 0) {
            throw new IllegalArgumentException();
        }
        this.a = j;
    }

    public void onVideoStart() {
        onVideoStartNative(this.a);
    }

    public void onVideoError(int i) {
        onVideoErrorNative(this.a, i);
    }

    public void onVideoComplete() {
        onVideoCompleteNative(this.a);
    }

    @eu
    static Object create(long j) {
        return new TJVideoListenerNative(j);
    }
}
