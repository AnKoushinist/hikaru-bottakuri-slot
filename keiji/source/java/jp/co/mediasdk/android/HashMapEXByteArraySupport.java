package jp.co.mediasdk.android;

public class HashMapEXByteArraySupport extends HashMapEXHashMapSupport {
    public boolean b(String str) {
        if (!a(str)) {
            return false;
        }
        try {
            Base64.a(j(str));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public byte[] c(String str) {
        byte[] bArr = null;
        if (b(str)) {
            try {
                bArr = Base64.a(j(str));
            } catch (Exception e) {
            }
        }
        return bArr;
    }
}
