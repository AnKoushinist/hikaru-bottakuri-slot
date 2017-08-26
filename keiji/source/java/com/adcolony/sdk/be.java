package com.adcolony.sdk;

import java.util.Date;
import jp.co.vaz.creator.hikaru2.R;
import twitter4j.TwitterResponse;

class be {
    private Date a;
    private int b;
    private String c;
    private String d;

    public be(Date date, int i, String str, String str2) {
        this.a = date;
        this.b = i;
        this.c = str;
        this.d = str2;
    }

    public String a() {
        switch (this.b) {
            case TwitterResponse.READ_WRITE /*2*/:
                return "Verbose";
            case TwitterResponse.READ_WRITE_DIRECTMESSAGES /*3*/:
                return "Debug";
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsTextAppearance /*4*/:
                return "Info";
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsHeaderTextAppearance /*5*/:
                return "Warn";
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                return "Error";
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                return "Assert";
            default:
                return "UNKNOWN LOG LEVEL";
        }
    }

    public String b() {
        return this.d;
    }

    public String c() {
        return this.c;
    }

    public Date d() {
        return this.a;
    }

    public String toString() {
        return d().toString() + " " + a() + "/" + c() + ": " + b();
    }
}
