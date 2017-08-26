package jp.co.mediasdk.android;

import java.io.InputStream;

public class InputStreamUtil {
    public static int a(InputStream inputStream, byte[] bArr) {
        int i = -1;
        if (inputStream == null) {
            LoggerBase.a(InputStream.class, "read", "stream is null.", new Object[0]);
        } else {
            try {
                i = inputStream.read(bArr);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return i;
    }
}
