package com.igaworks.core;

import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.cocos2dx.lib.BuildConfig;

public class AESGetTrackParam {
    private static String IGAW_COMPLETE_SUPER_KEY = BuildConfig.FLAVOR;
    private static String IGAW_TRACKING_SUPER_KEY = "srkterowgawrsozerruly82nfij625w9";

    public static String encrypt(String str, String str2) {
        int i = 0;
        AlgorithmParameterSpec ivParameterSpec = new IvParameterSpec(IGAW_TRACKING_SUPER_KEY.substring(0, 16).getBytes());
        Key secretKeySpec = new SecretKeySpec(IGAW_TRACKING_SUPER_KEY.getBytes(), "AES");
        Cipher instance = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        instance.init(1, secretKeySpec, ivParameterSpec);
        byte[] doFinal = instance.doFinal(str.getBytes());
        if (doFinal == null || doFinal.length == 0) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer(doFinal.length * 2);
        while (i < doFinal.length) {
            String str3 = "0" + Integer.toHexString(doFinal[i] & 255);
            stringBuffer.append(str3.substring(str3.length() - 2));
            i++;
        }
        return stringBuffer.toString();
    }

    public static String encrypt_hashkey(String str, String str2) {
        int i = 0;
        IGAW_COMPLETE_SUPER_KEY = new StringBuilder(String.valueOf(str2)).append(str2).toString();
        AlgorithmParameterSpec ivParameterSpec = new IvParameterSpec(IGAW_COMPLETE_SUPER_KEY.substring(0, 16).getBytes());
        Key secretKeySpec = new SecretKeySpec(IGAW_COMPLETE_SUPER_KEY.getBytes(), "AES");
        Cipher instance = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        instance.init(1, secretKeySpec, ivParameterSpec);
        byte[] doFinal = instance.doFinal(str.getBytes());
        if (doFinal == null || doFinal.length == 0) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer(doFinal.length * 2);
        while (i < doFinal.length) {
            String str3 = "0" + Integer.toHexString(doFinal[i] & 255);
            stringBuffer.append(str3.substring(str3.length() - 2));
            i++;
        }
        return stringBuffer.toString();
    }
}
