package jp.co.mediasdk.android;

import org.cocos2dx.lib.BuildConfig;

public class HashMapEXStringSupport extends HashMapEXBase {
    public String a(int i) {
        return a(i, BuildConfig.FLAVOR);
    }

    public String a(int i, String str) {
        return b(String.format("%d", new Object[]{Integer.valueOf(i)}), str);
    }

    public String j(String str) {
        return b(str, BuildConfig.FLAVOR);
    }

    public String b(String str, String str2) {
        return !containsKey(str) ? str2 : (String) get(str);
    }

    public String b(int i, String str) {
        return c(String.format("%d", new Object[]{Integer.valueOf(i)}), str);
    }

    public String c(String str, String str2) {
        if (str2 == null) {
            str2 = BuildConfig.FLAVOR;
        }
        return a(str, str2);
    }
}
