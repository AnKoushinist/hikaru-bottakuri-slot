package com.glossomads;

public class l {
    public static boolean a() {
        if ("prod".equals("stg")) {
            return true;
        }
        return false;
    }

    public static boolean b() {
        if ("prod".equals("dev")) {
            return true;
        }
        return false;
    }

    public static boolean c() {
        if ("prod".equals("prod")) {
            return true;
        }
        return false;
    }

    public static String d() {
        if (b()) {
            return "https://sandbox-sugar-adreq-v1.glossom.jp/load_ad";
        }
        if (c()) {
            return "https://sugar-adreq-v1.glossom.jp/load_ad";
        }
        if (a()) {
            return "https://stg-adsvr01-sugar-pascal-in.glossom.jp/load_ad";
        }
        return "https://sandbox-sugar-adreq-v1.glossom.jp/load_ad";
    }
}
