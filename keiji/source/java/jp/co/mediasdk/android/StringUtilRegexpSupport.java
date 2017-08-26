package jp.co.mediasdk.android;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtilRegexpSupport extends StringUtilEmptySupport {
    public static boolean b(String str, String str2) {
        Matcher d = d(str, str2);
        if (d == null) {
            return false;
        }
        return d.matches();
    }

    public static boolean a(String str, String str2, ArrayList<String> arrayList) {
        int i = 0;
        if (arrayList == null) {
            LoggerBase.a(StringUtil.class, "preg_match", "groups is null.", new Object[0]);
            return false;
        }
        Matcher d = d(str, str2);
        if (d == null || !d.matches()) {
            return false;
        }
        arrayList.clear();
        while (i < d.groupCount() + 1) {
            arrayList.add(d.group(i));
            i++;
        }
        return true;
    }

    public static String a(String str, String str2, String str3) {
        return str3.replaceAll(str, str2);
    }

    public static String[] c(String str, String str2) {
        if (str == null) {
            LoggerBase.a(StringUtil.class, "preg_split", "regexp is null.", new Object[0]);
            return null;
        } else if (str2 != null) {
            return Pattern.compile(str).split(str2);
        } else {
            LoggerBase.a(StringUtil.class, "preg_split", "subject is null.", new Object[0]);
            return null;
        }
    }

    protected static Pattern d(String str) {
        Pattern pattern = null;
        if (str == null) {
            LoggerBase.a(StringUtil.class, "getPattern", "regexp is null.", new Object[0]);
        } else {
            if (str.indexOf(0) != 94) {
                str = String.format(".*%s", new Object[]{str});
            }
            if (str.lastIndexOf(0) != 36) {
                str = String.format("%s.*", new Object[]{str});
            }
            try {
                pattern = Pattern.compile(str);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return pattern;
    }

    protected static Matcher d(String str, String str2) {
        if (str2 == null) {
            LoggerBase.a(StringUtil.class, "match", "subject is null.", new Object[0]);
            return null;
        }
        Pattern d = d(str);
        if (d != null) {
            return d.matcher(str2);
        }
        LoggerBase.a(StringUtil.class, "match", "pattern is null.", new Object[0]);
        return null;
    }
}
