package jp.tjkapp.adfurikunsdk.moviereward;

import java.util.Locale;

public class Util {
    public static String getCurrentCountryCode() {
        if (Constants.LOCALE_JA.equals(Locale.getDefault().getLanguage())) {
            return Constants.LOCALE_JA;
        }
        return Constants.LOCALE_EN;
    }
}
