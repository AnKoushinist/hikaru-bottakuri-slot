package jp.maio.sdk.android;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

final class ak {
    private static final SimpleDateFormat a = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");

    public static Calendar a(String str) {
        String str2;
        int lastIndexOf = str.lastIndexOf(90);
        if (lastIndexOf >= 0) {
            str2 = str.substring(0, lastIndexOf) + "+0000";
        } else {
            lastIndexOf = str.lastIndexOf(58);
            if (lastIndexOf < 0) {
                throw new ParseException("Invalid length", 0);
            }
            str2 = str.substring(0, lastIndexOf) + str.substring(lastIndexOf + 1);
        }
        Date parse = a.parse(str2);
        Calendar instance = Calendar.getInstance();
        instance.setTime(parse);
        return instance;
    }
}
