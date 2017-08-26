package com.jirbo.adcolony;

public class AdColonyV4VCReward {
    boolean a;
    String b;
    int c;

    AdColonyV4VCReward(boolean z, String str, int i) {
        this.a = z;
        this.b = str;
        this.c = i;
    }

    public boolean success() {
        return this.a;
    }

    public String name() {
        return this.b;
    }

    public int amount() {
        return this.c;
    }

    public String toString() {
        if (this.a) {
            return this.b + ":" + this.c;
        }
        return "no reward";
    }
}
