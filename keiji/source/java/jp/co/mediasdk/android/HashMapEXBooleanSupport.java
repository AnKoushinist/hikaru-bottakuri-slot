package jp.co.mediasdk.android;

import com.tapjoy.TapjoyConstants;

public class HashMapEXBooleanSupport extends HashMapEXDoubleSupport {
    public String a(String str, boolean z) {
        return a(str, z ? TapjoyConstants.TJC_TRUE : TapjoyConstants.TJC_FALSE);
    }

    public String a(int i, boolean z) {
        return a(String.format("%d", new Object[]{Integer.valueOf(i)}), z);
    }
}
