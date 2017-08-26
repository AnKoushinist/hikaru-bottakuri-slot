package com.applovin.sdk;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class AppLovinAdType {
    public static final AppLovinAdType a = new AppLovinAdType("REGULAR");
    public static final AppLovinAdType b = new AppLovinAdType("VIDEOA");
    private final String c;

    public AppLovinAdType(String str) {
        this.c = str;
    }

    public static AppLovinAdType a(String str) {
        return str.toUpperCase(Locale.ENGLISH).equals(b.a()) ? b : a;
    }

    public static Set b() {
        Set hashSet = new HashSet(2);
        hashSet.add(a);
        hashSet.add(b);
        return hashSet;
    }

    public String a() {
        return this.c.toUpperCase(Locale.ENGLISH);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AppLovinAdType appLovinAdType = (AppLovinAdType) obj;
        if (this.c != null) {
            if (this.c.equals(appLovinAdType.c)) {
                return true;
            }
        } else if (appLovinAdType.c == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return this.c != null ? this.c.hashCode() : 0;
    }

    public String toString() {
        return a();
    }
}
