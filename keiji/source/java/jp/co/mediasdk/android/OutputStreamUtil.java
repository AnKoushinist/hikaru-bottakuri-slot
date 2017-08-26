package jp.co.mediasdk.android;

import java.io.OutputStream;

public class OutputStreamUtil {
    public static boolean a(OutputStream outputStream, byte[] bArr) {
        if (outputStream == null) {
            LoggerBase.a(StreamBase.class, "write", "stream is null.", new Object[0]);
            return false;
        }
        try {
            outputStream.write(bArr);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            LoggerBase.a(StreamBase.class, "write", "failed to write.", new Object[0]);
            return false;
        }
    }

    public static boolean a(OutputStream outputStream, byte[] bArr, int i, int i2) {
        if (outputStream == null) {
            LoggerBase.a(StreamBase.class, "write", "stream is null.", new Object[0]);
            return false;
        }
        try {
            outputStream.write(bArr, i, i2);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            LoggerBase.a(StreamBase.class, "write", "failed to write.", new Object[0]);
            return false;
        }
    }

    public static boolean a(OutputStream outputStream) {
        try {
            outputStream.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
