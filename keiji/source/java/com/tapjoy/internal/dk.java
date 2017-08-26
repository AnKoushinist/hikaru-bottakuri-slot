package com.tapjoy.internal;

import jp.co.vaz.creator.hikaru2.R;
import twitter4j.TwitterResponse;

public enum dk {
    VARINT(0),
    FIXED64(1),
    LENGTH_DELIMITED(2),
    FIXED32(5);
    
    final int e;

    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = null;

        static {
            a = new int[dk.values().length];
            try {
                a[dk.VARINT.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[dk.FIXED32.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[dk.FIXED64.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[dk.LENGTH_DELIMITED.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    private dk(int i) {
        this.e = i;
    }

    public final dn a() {
        switch (AnonymousClass1.a[ordinal()]) {
            case TwitterResponse.READ /*1*/:
                return dn.j;
            case TwitterResponse.READ_WRITE /*2*/:
                return dn.g;
            case TwitterResponse.READ_WRITE_DIRECTMESSAGES /*3*/:
                return dn.l;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsTextAppearance /*4*/:
                return dn.q;
            default:
                throw new AssertionError();
        }
    }
}
