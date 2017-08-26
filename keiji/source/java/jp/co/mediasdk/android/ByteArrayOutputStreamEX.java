package jp.co.mediasdk.android;

import java.io.ByteArrayOutputStream;

public class ByteArrayOutputStreamEX extends ByteArrayOutputStream {
    protected int a = 1000000;

    public void a(String str) {
        if (str != null) {
            super.write(str.getBytes(), 0, str.getBytes().length);
        }
    }

    public void write(byte[] bArr) {
        if (bArr != null) {
            if (size() + bArr.length > this.a) {
                LoggerBase.d(this, "write", "buffer is overflow.", new Object[0]);
                return;
            }
            try {
                super.write(bArr);
            } catch (Exception e) {
            }
        }
    }
}
