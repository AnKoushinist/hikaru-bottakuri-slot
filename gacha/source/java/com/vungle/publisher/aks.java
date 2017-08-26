package com.vungle.publisher;

/* compiled from: vungle */
public final class aks {
    public static int a(int i) {
        return 1 << (32 - Integer.numberOfLeadingZeros(i - 1));
    }
}
