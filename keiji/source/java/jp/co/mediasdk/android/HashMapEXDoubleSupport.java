package jp.co.mediasdk.android;

public class HashMapEXDoubleSupport extends HashMapEXFloatSupport {
    public String a(int i, double d) {
        return a(String.format("%d", new Object[]{Integer.valueOf(i)}), d);
    }

    public String a(String str, double d) {
        return a(str, String.format("%f", new Object[]{Double.valueOf(d)}));
    }
}
