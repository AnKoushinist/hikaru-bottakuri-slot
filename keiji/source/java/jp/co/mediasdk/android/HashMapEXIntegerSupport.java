package jp.co.mediasdk.android;

public class HashMapEXIntegerSupport extends HashMapEXLongSupport {
    public String a(String str, int i) {
        return a(str, String.format("%d", new Object[]{Integer.valueOf(i)}));
    }

    public String a(int i, int i2) {
        return a(String.format("%d", new Object[]{Integer.valueOf(i)}), i2);
    }
}
