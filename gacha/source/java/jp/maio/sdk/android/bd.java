package jp.maio.sdk.android;

class bd {
    public static String a(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer(bArr.length * 2);
        for (byte b : bArr) {
            String toHexString = Integer.toHexString(b & 255);
            if (toHexString.length() == 1) {
                stringBuffer.append("0");
            }
            stringBuffer.append(toHexString);
        }
        return stringBuffer.toString();
    }
}
