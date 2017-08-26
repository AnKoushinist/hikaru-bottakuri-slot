package com.igaworks.adbrix.util;

import java.util.regex.Pattern;

public class ADBrixUtil {
    public static boolean validateEmailFormat(String str) {
        if (str == null) {
            return false;
        }
        return Pattern.matches("[\\w\\~\\-\\.]+@[\\w\\~\\-]+(\\.[\\w\\~\\-]+)+", str.trim());
    }
}
