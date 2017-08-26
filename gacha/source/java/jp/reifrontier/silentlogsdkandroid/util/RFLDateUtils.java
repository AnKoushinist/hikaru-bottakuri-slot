package jp.reifrontier.silentlogsdkandroid.util;

import android.text.format.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import org.cocos2dx.lib.BuildConfig;

public class RFLDateUtils {
    public static String getAPIDate(int i, String str) {
        Date date = new Date(((long) i) * 1000);
        Calendar instance = Calendar.getInstance();
        TimeZone timeZone = TimeZone.getDefault();
        instance.setTime(date);
        instance.setTimeZone(timeZone);
        return new SimpleDateFormat(str, Locale.US).format(instance.getTime());
    }

    public static String getAPIDateLong(long j, String str) {
        Date date = new Date(j);
        Calendar instance = Calendar.getInstance();
        TimeZone timeZone = TimeZone.getDefault();
        instance.setTime(date);
        instance.setTimeZone(timeZone);
        return (String) DateFormat.format(str, instance);
    }

    public static String getGMTAPIDate(double d, String str) {
        Date date = new Date(((long) d) * 1000);
        Calendar instance = Calendar.getInstance();
        TimeZone timeZone = TimeZone.getTimeZone("GMT");
        instance.setTime(date);
        instance.setTimeZone(timeZone);
        return (String) DateFormat.format(str, instance);
    }

    public static String getGMTAPIDateLong(long j, String str) {
        Date date = new Date(j);
        Calendar instance = Calendar.getInstance();
        TimeZone timeZone = TimeZone.getTimeZone("GMT");
        instance.setTime(date);
        instance.setTimeZone(timeZone);
        return (String) DateFormat.format(str, instance);
    }

    public static String getQueryDate(double d, String str) {
        Date date = new Date(((long) d) * 1000);
        Calendar instance = Calendar.getInstance();
        if (str != null) {
            instance.setTimeZone(TimeZone.getTimeZone(str));
        }
        instance.setTime(date);
        return (String) DateFormat.format("yyyy-MM-dd", instance);
    }

    public static String getQueryDateLong(long j, String str) {
        Date date = new Date(j);
        Calendar instance = Calendar.getInstance();
        if (str != null) {
            instance.setTimeZone(TimeZone.getTimeZone(str));
        }
        instance.setTime(date);
        return (String) DateFormat.format("yyyy-MM-dd", instance);
    }

    public static long getLocalDate(double d, String str) {
        Date date = new Date(((long) d) * 1000);
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        if (str != null) {
            instance.setTimeZone(TimeZone.getTimeZone(str));
        }
        return instance.getTime().getTime();
    }

    public static long getDayStart(double d, String str) {
        Date date = new Date(((long) d) * 1000);
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        if (str != null) {
            instance.setTimeZone(TimeZone.getTimeZone(str));
        }
        instance.set(11, 0);
        instance.set(12, 0);
        instance.set(13, 0);
        instance.set(14, 0);
        return instance.getTime().getTime();
    }

    public static long getDayStartLong(long j, String str) {
        Date date = new Date(j);
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        if (str != null) {
            instance.setTimeZone(TimeZone.getTimeZone(str));
        }
        instance.set(11, 0);
        instance.set(12, 0);
        instance.set(13, 0);
        instance.set(14, 0);
        return instance.getTime().getTime();
    }

    public static long getDayEnd(long j, String str) {
        Date date = new Date(j);
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        if (str != null) {
            instance.setTimeZone(TimeZone.getTimeZone(str));
        }
        instance.set(11, 23);
        instance.set(12, 59);
        instance.set(13, 59);
        instance.set(14, 59);
        return instance.getTime().getTime();
    }

    public static boolean isATimeAfterB(long j, long j2) {
        if (j - j2 > 0) {
            return true;
        }
        return false;
    }

    public static boolean isADayIsToday(long j) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(new Date(j));
        return isSameDay(instance, Calendar.getInstance());
    }

    public static boolean isSameDay(Calendar calendar, Calendar calendar2) {
        if (calendar == null || calendar2 == null) {
            throw new IllegalArgumentException("The dates must not be null");
        } else if (calendar.get(0) == calendar2.get(0) && calendar.get(1) == calendar2.get(1) && calendar.get(6) == calendar2.get(6)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isATimeBetweenBAndC(double d, double d2, double d3) {
        if (d - d2 <= 0.0d || d3 - d <= 0.0d) {
            return false;
        }
        return true;
    }

    public static String getFromToString(double d, double d2, String str) {
        TimeZone.getTimeZone(str);
        Date date = new Date((long) (d * 1000.0d));
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        int i = instance.get(11);
        int i2 = instance.get(12);
        String str2 = BuildConfig.FLAVOR + i2;
        if (i2 < 10) {
            str2 = "0" + i2;
        }
        Date date2 = new Date((long) (1000.0d * d2));
        Calendar instance2 = Calendar.getInstance();
        instance2.setTime(date2);
        int i3 = instance2.get(11);
        int i4 = instance2.get(12);
        String str3 = BuildConfig.FLAVOR + i4;
        if (i4 < 10) {
            str3 = "0" + i4;
        }
        return i + ":" + str2 + " - " + i3 + ":" + str3;
    }

    public static String getMonthDayString(long j) {
        String str = BuildConfig.FLAVOR;
        Calendar instance = Calendar.getInstance();
        instance.setTime(new Date(j));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        simpleDateFormat.applyPattern("yyyy,MMM dd");
        str = simpleDateFormat.format(instance.getTime());
        if (RFLUtils.isJaLocale()) {
            return str + "\u65e5";
        }
        return str;
    }

    public static String getTime(int i) {
        if (i / 60 < 1) {
            return "0:00";
        }
        String valueOf;
        if (i / 3600 < 1) {
            valueOf = String.valueOf((int) (((float) i) / 60.0f));
            if (valueOf.length() == 1) {
                return "0:0" + valueOf;
            }
            return "0:" + valueOf;
        }
        float f = ((float) i) / 3600.0f;
        String str = String.valueOf((int) f) + ":";
        valueOf = String.valueOf((int) (((float) (i - (((int) f) * 3600))) / 60.0f));
        if (valueOf.length() == 1) {
            valueOf = "0" + valueOf;
        }
        return str + valueOf;
    }

    public static Date randomMidnight() {
        Calendar instance = Calendar.getInstance();
        instance.set(11, randBetween(1, 5));
        instance.set(12, randBetween(0, 59));
        instance.set(13, 0);
        return instance.getTime();
    }

    public static int randBetween(int i, int i2) {
        return ((int) Math.round(Math.random() * ((double) (i2 - i)))) + i;
    }
}
