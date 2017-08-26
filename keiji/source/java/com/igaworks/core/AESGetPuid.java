package com.igaworks.core;

import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AESGetPuid {
    public static String encrypt(String str) {
        int i = 0;
        AlgorithmParameterSpec ivParameterSpec = new IvParameterSpec("igaworks1k0i1d4a6e2i5g0ajwyobrks".substring(0, 16).getBytes());
        Key secretKeySpec = new SecretKeySpec("igaworks1k0i1d4a6e2i5g0ajwyobrks".getBytes(), "AES");
        Cipher instance = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        instance.init(1, secretKeySpec, ivParameterSpec);
        byte[] doFinal = instance.doFinal(str.getBytes());
        if (doFinal == null || doFinal.length == 0) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer(doFinal.length * 2);
        while (i < doFinal.length) {
            String str2 = "0" + Integer.toHexString(doFinal[i] & 255);
            stringBuffer.append(str2.substring(str2.length() - 2));
            i++;
        }
        return stringBuffer.toString();
    }
}
