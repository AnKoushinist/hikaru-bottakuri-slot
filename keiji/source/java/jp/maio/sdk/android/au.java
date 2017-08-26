package jp.maio.sdk.android;

import java.io.Serializable;
import java.util.Date;

final class au implements Serializable {
    public String a;
    public Boolean b;
    public String c;
    public Boolean d;
    public Date e;
    private String f;
    private String g;
    private String h;
    private String i;

    public au(String str, String str2, String str3, String str4, Boolean bool, String str5, String str6, Boolean bool2, Date date) {
        this.f = str;
        this.a = str2;
        this.g = str3;
        this.h = str4;
        this.b = bool;
        this.i = str5;
        this.c = str6;
        this.d = bool2;
        this.e = date;
    }
}
