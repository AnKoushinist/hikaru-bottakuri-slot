package jp.co.mediasdk.android;

public class HashMapEXHashMapSupport extends HashMapEXBooleanSupport {
    public HashMapEX e(String str) {
        return a(str, new HashMapEX());
    }

    public HashMapEX a(String str, HashMapEX hashMapEX) {
        return !a(str) ? hashMapEX : new HashMapEX(j(str));
    }

    public boolean f(String str) {
        if (a(str)) {
            return JSON.a(j(str));
        }
        return false;
    }

    public String b(String str, HashMapEX hashMapEX) {
        if (HashMapEX.b(hashMapEX)) {
            hashMapEX = new HashMapEX();
        }
        return c(str, hashMapEX.b());
    }
}
