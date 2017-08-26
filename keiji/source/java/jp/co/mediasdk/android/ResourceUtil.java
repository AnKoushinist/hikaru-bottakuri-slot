package jp.co.mediasdk.android;

import java.io.InputStream;

public class ResourceUtil {
    public static InputStream a(String str) {
        return ResourceUtil.class.getClassLoader().getResourceAsStream(str);
    }
}
