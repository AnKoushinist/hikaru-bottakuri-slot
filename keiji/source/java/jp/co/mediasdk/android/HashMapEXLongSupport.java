package jp.co.mediasdk.android;

public class HashMapEXLongSupport extends HashMapEXStringSupport {
    public long h(String str) {
        return a(str, 0);
    }

    public long a(String str, long j) {
        return (containsKey(str) && get(str) != null && MathUtilParseSupport.a((String) get(str))) ? MathUtilParseSupport.d((String) get(str)) : j;
    }

    public String b(String str, long j) {
        return a(str, String.format("%d", new Object[]{Long.valueOf(j)}));
    }
}
