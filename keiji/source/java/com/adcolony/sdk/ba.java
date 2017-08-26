package com.adcolony.sdk;

class ba {
    private String a;
    private String b;
    private String c;
    private String d = "%s_%s_%s";

    public ba(String str, String str2, String str3) {
        this.a = a(str);
        this.b = a(str2);
        this.c = a(str3);
    }

    private String a(String str) {
        return str.replaceAll("\\.", "-");
    }

    public String a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public String c() {
        return this.c;
    }
}
