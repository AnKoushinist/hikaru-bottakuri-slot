package jp.co.mediasdk.android;

import java.io.OutputStream;

public class StreamBase {
    public static boolean a(OutputStream outputStream) {
        if (outputStream == null) {
            LoggerBase.a(StreamBase.class, "flush", "stream is null.", new Object[0]);
            return false;
        }
        try {
            outputStream.flush();
            return true;
        } catch (Exception e) {
            LoggerBase.a(StreamBase.class, "flush", "failed to flush.", new Object[0]);
            return false;
        }
    }
}
