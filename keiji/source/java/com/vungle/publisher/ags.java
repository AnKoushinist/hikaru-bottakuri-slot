package com.vungle.publisher;

import android.net.Uri;
import android.util.Base64;
import com.tapjoy.TJAdUnitConstants.String;
import com.tapjoy.TapjoyConstants;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/* compiled from: vungle */
public final class ags {
    private static final char[] a = "0123456789abcdef".toCharArray();

    public static boolean a(String str) {
        return (str == null || str.trim().isEmpty()) ? false : true;
    }

    public static <T> String a(T... tArr) {
        return a(", ", (Object[]) tArr);
    }

    public static String a(Iterable<?> iterable) {
        return a(", ", (Iterable) iterable);
    }

    public static <T> String a(String str, T... tArr) {
        return a(str, tArr == null ? null : Arrays.asList(tArr));
    }

    public static String a(String str, Iterable<?> iterable) {
        if (iterable == null) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        Object obj = 1;
        for (Object next : iterable) {
            if (obj != null) {
                obj = null;
            } else {
                stringBuilder.append(str);
            }
            stringBuilder.append(next);
        }
        return stringBuilder.toString();
    }

    public static String a(Enum<?> enumR) {
        return enumR == null ? null : enumR.name();
    }

    public static String b(Object[] objArr) {
        return "[" + a(objArr) + "]";
    }

    private static Uri f(String str) {
        if (str == null) {
            throw new IllegalArgumentException("input text cannot be null");
        }
        try {
            return Uri.parse(str);
        } catch (Exception e) {
            throw new IllegalArgumentException("invalid url parameter: " + str);
        }
    }

    public static Uri a(String str, String str2) {
        if (str == null) {
            return f(str2);
        }
        return f(str);
    }

    public static boolean b(String str) {
        if (TapjoyConstants.TJC_TRUE.equals(str)) {
            return true;
        }
        if (TapjoyConstants.TJC_FALSE.equals(str)) {
            return false;
        }
        throw new IllegalArgumentException("invalid boolean parameter: " + str);
    }

    public static String c(String str) {
        if (str == null) {
            return str;
        }
        try {
            return new String(Base64.decode(str, 0));
        } catch (Exception e) {
            throw new IllegalArgumentException("error decoding base64 string: " + str);
        }
    }

    public static Set<String> a(Uri uri) {
        Set<String> hashSet = new HashSet();
        try {
            if (agl.a(pj.HONEYCOMB)) {
                return uri.getQueryParameterNames();
            }
            String query = uri.getQuery();
            if (query == null) {
                return hashSet;
            }
            for (String split : query.split("&")) {
                hashSet.add(split.split("=")[0]);
            }
            return hashSet;
        } catch (Exception e) {
            throw new IllegalArgumentException("error getting query param names");
        }
    }

    public static to d(String str) {
        if ("portrait".equals(str)) {
            return to.PORTRAIT;
        }
        if (String.LANDSCAPE.equals(str)) {
            return to.LANDSCAPE;
        }
        if ("none".equals(str)) {
            return to.NONE;
        }
        throw new IllegalArgumentException("invalid orientation: " + str);
    }

    public static boolean e(String str) {
        if (str == null || str.length() <= 0) {
            return true;
        }
        return false;
    }
}
