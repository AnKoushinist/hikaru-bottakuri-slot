package com.tapjoy.mraid.util;

import android.os.Bundle;
import com.applovin.sdk.AppLovinTargetingData;
import com.raizlabs.android.dbflow.sql.language.Condition.Operation;

public class Utils {
    public static String byteToHex(byte b) {
        char[] cArr = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', AppLovinTargetingData.GENDER_FEMALE};
        return new String(new char[]{cArr[(b >> 4) & 15], cArr[b & 15]});
    }

    public static String convert(String str) {
        try {
            byte[] bytes = str.getBytes();
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < bytes.length; i++) {
                if ((bytes[i] & 128) > 0) {
                    stringBuffer.append(new StringBuilder(Operation.MOD).append(byteToHex(bytes[i])).toString());
                } else {
                    stringBuffer.append((char) bytes[i]);
                }
            }
            return new String(stringBuffer.toString().getBytes(), "ISO-8859-1");
        } catch (Exception e) {
            return null;
        }
    }

    public static String getData(String str, Bundle bundle) {
        return bundle.getString(str);
    }
}