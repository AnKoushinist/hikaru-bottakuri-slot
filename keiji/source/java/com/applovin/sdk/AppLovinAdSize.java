package com.applovin.sdk;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import twitter4j.HttpResponseCode;

public class AppLovinAdSize {
    public static final AppLovinAdSize a = new AppLovinAdSize(-1, 50, "BANNER");
    public static final AppLovinAdSize b = new AppLovinAdSize(-1, 75, "LEADER");
    public static final AppLovinAdSize c = new AppLovinAdSize(-1, -1, "INTER");
    public static final AppLovinAdSize d = new AppLovinAdSize(HttpResponseCode.MULTIPLE_CHOICES, 250, "MREC");
    private final int e;
    private final int f;
    private final String g;

    AppLovinAdSize(int i, int i2, String str) {
        if (i < 0 && i != -1) {
            throw new IllegalArgumentException("Ad width must be a positive number. Number provided: " + i);
        } else if (i > 9999) {
            throw new IllegalArgumentException("Ad width must be less then 9999. Number provided: " + i);
        } else if (i2 < 0 && i2 != -1) {
            throw new IllegalArgumentException("Ad height must be a positive number. Number provided: " + i2);
        } else if (i2 > 9999) {
            throw new IllegalArgumentException("Ad height must be less then 9999. Number provided: " + i2);
        } else if (str == null) {
            throw new IllegalArgumentException("No label specified");
        } else if (str.length() > 9) {
            throw new IllegalArgumentException("Provided label is too long. Label provided: " + str);
        } else {
            this.e = i;
            this.f = i2;
            this.g = str;
        }
    }

    public AppLovinAdSize(String str) {
        this(0, 0, str);
    }

    public static AppLovinAdSize a(String str) {
        if (str == null || str.length() < 1) {
            return null;
        }
        String toLowerCase = str.toLowerCase(Locale.ENGLISH);
        if (toLowerCase.equals("banner")) {
            return a;
        }
        if (toLowerCase.equals("interstitial") || toLowerCase.equals("inter")) {
            return c;
        }
        if (toLowerCase.equals("mrec")) {
            return d;
        }
        if (toLowerCase.equals("leader")) {
            return b;
        }
        String[] split = str.split("x");
        return split.length == 2 ? new AppLovinAdSize(b(split[0]), b(split[1]), str) : new AppLovinAdSize(0, 0, str);
    }

    private static int b(String str) {
        if ("span".equalsIgnoreCase(str)) {
            return -1;
        }
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public static Set d() {
        Set hashSet = new HashSet(4);
        hashSet.add(a);
        hashSet.add(d);
        hashSet.add(c);
        hashSet.add(b);
        return hashSet;
    }

    public int a() {
        return this.e;
    }

    public int b() {
        return this.f;
    }

    public String c() {
        return this.g.toUpperCase(Locale.ENGLISH);
    }

    public String toString() {
        return c();
    }
}
