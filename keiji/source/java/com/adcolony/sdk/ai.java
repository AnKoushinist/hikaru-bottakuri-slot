package com.adcolony.sdk;

import java.security.MessageDigest;

class ai {
    private static String a(byte[] bArr) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bArr) {
            int i = (b >>> 4) & 15;
            int i2 = 0;
            while (true) {
                char c = (i < 0 || i > 9) ? (char) ((i - 10) + 97) : (char) (i + 48);
                stringBuilder.append(c);
                int i3 = b & 15;
                i = i2 + 1;
                if (i2 >= 1) {
                    break;
                }
                i2 = i;
                i = i3;
            }
        }
        return stringBuilder.toString();
    }

    public static String a(String str) {
        MessageDigest instance = MessageDigest.getInstance("SHA-1");
        instance.update(str.getBytes("iso-8859-1"), 0, str.length());
        return a(instance.digest());
    }
}
