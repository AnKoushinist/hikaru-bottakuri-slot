package com.igaworks.core;

import com.igaworks.util.IgawBase64;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Mhows_AES_Util {
    public static String key = "k1t2m3h4o5w7s8kt9m8h7o6w5s4mhows";

    public static String encrypt(String str) {
        Key secretKeySpec = new SecretKeySpec(key.getBytes(), "AES");
        Cipher instance = Cipher.getInstance("AES/ECB/PKCS5PADDING");
        instance.init(1, secretKeySpec);
        return new String(IgawBase64.encode(instance.doFinal(str.getBytes())));
    }
}
