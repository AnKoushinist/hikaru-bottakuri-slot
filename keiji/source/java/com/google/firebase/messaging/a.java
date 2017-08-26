package com.google.firebase.messaging;

import java.util.Locale;
import jp.co.vaz.creator.hikaru2.R;
import twitter4j.TwitterResponse;

public final class a extends Exception {
    private final int a;

    a(String str) {
        super(str);
        this.a = a(str);
    }

    private int a(String str) {
        if (str == null) {
            return 0;
        }
        String toLowerCase = str.toLowerCase(Locale.US);
        int i = -1;
        switch (toLowerCase.hashCode()) {
            case -1743242157:
                if (toLowerCase.equals("service_not_available")) {
                    i = 3;
                    break;
                }
                break;
            case -1290953729:
                if (toLowerCase.equals("toomanymessages")) {
                    i = 4;
                    break;
                }
                break;
            case -920906446:
                if (toLowerCase.equals("invalid_parameters")) {
                    i = 0;
                    break;
                }
                break;
            case -617027085:
                if (toLowerCase.equals("messagetoobig")) {
                    i = 2;
                    break;
                }
                break;
            case -95047692:
                if (toLowerCase.equals("missing_to")) {
                    i = 1;
                    break;
                }
                break;
        }
        switch (i) {
            case TwitterResponse.NONE /*0*/:
            case TwitterResponse.READ /*1*/:
                return 1;
            case TwitterResponse.READ_WRITE /*2*/:
                return 2;
            case TwitterResponse.READ_WRITE_DIRECTMESSAGES /*3*/:
                return 3;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsTextAppearance /*4*/:
                return 4;
            default:
                return 0;
        }
    }
}
