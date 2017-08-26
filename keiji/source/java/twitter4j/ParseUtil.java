package twitter4j;

import com.d.a.a.c;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.LinkedBlockingQueue;
import jp.co.vaz.creator.hikaru2.R;
import org.cocos2dx.lib.BuildConfig;

final class ParseUtil {
    private static final Map<String, LinkedBlockingQueue<SimpleDateFormat>> formatMapQueue = new HashMap();

    private ParseUtil() {
        throw new AssertionError();
    }

    static String getUnescapedString(String str, JSONObject jSONObject) {
        return HTMLEntity.unescape(getRawString(str, jSONObject));
    }

    public static String getRawString(String str, JSONObject jSONObject) {
        String str2 = null;
        try {
            if (!jSONObject.isNull(str)) {
                str2 = jSONObject.getString(str);
            }
        } catch (JSONException e) {
        }
        return str2;
    }

    static String getURLDecodedString(String str, JSONObject jSONObject) {
        String rawString = getRawString(str, jSONObject);
        if (rawString != null) {
            try {
                rawString = URLDecoder.decode(rawString, c.DEFAULT_CHARSET);
            } catch (UnsupportedEncodingException e) {
            }
        }
        return rawString;
    }

    public static Date parseTrendsDate(String str) {
        switch (str.length()) {
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                return new Date(Long.parseLong(str) * 1000);
            case R.styleable.Toolbar_maxButtonHeight /*20*/:
                return getDate(str, "yyyy-MM-dd'T'HH:mm:ss'Z'");
            default:
                return getDate(str, "EEE, d MMM yyyy HH:mm:ss z");
        }
    }

    public static Date getDate(String str, JSONObject jSONObject) {
        return getDate(str, jSONObject, "EEE MMM d HH:mm:ss z yyyy");
    }

    public static Date getDate(String str, JSONObject jSONObject, String str2) {
        String unescapedString = getUnescapedString(str, jSONObject);
        if ("null".equals(unescapedString) || unescapedString == null) {
            return null;
        }
        return getDate(unescapedString, str2);
    }

    public static Date getDate(String str, String str2) {
        LinkedBlockingQueue linkedBlockingQueue;
        SimpleDateFormat simpleDateFormat;
        LinkedBlockingQueue linkedBlockingQueue2 = (LinkedBlockingQueue) formatMapQueue.get(str2);
        if (linkedBlockingQueue2 == null) {
            linkedBlockingQueue2 = new LinkedBlockingQueue();
            formatMapQueue.put(str2, linkedBlockingQueue2);
            linkedBlockingQueue = linkedBlockingQueue2;
        } else {
            linkedBlockingQueue = linkedBlockingQueue2;
        }
        SimpleDateFormat simpleDateFormat2 = (SimpleDateFormat) linkedBlockingQueue.poll();
        if (simpleDateFormat2 == null) {
            simpleDateFormat2 = new SimpleDateFormat(str2, Locale.US);
            simpleDateFormat2.setTimeZone(TimeZone.getTimeZone("UTC"));
            simpleDateFormat = simpleDateFormat2;
        } else {
            simpleDateFormat = simpleDateFormat2;
        }
        try {
            Date parse = simpleDateFormat.parse(str);
            try {
                linkedBlockingQueue.put(simpleDateFormat);
            } catch (InterruptedException e) {
            }
            return parse;
        } catch (Throwable e2) {
            throw new TwitterException("Unexpected date format(" + str + ") returned from twitter.com", e2);
        } catch (Throwable th) {
            try {
                linkedBlockingQueue.put(simpleDateFormat);
            } catch (InterruptedException e3) {
            }
        }
    }

    public static int getInt(String str, JSONObject jSONObject) {
        return getInt(getRawString(str, jSONObject));
    }

    public static int getInt(String str) {
        int i = -1;
        if (!(str == null || BuildConfig.FLAVOR.equals(str) || "null".equals(str))) {
            try {
                i = Integer.valueOf(str).intValue();
            } catch (NumberFormatException e) {
            }
        }
        return i;
    }

    public static long getLong(String str, JSONObject jSONObject) {
        return getLong(getRawString(str, jSONObject));
    }

    public static long getLong(String str) {
        if (str == null || BuildConfig.FLAVOR.equals(str) || "null".equals(str)) {
            return -1;
        }
        if (str.endsWith("+")) {
            return Long.valueOf(str.substring(0, str.length() - 1)).longValue() + 1;
        }
        return Long.valueOf(str).longValue();
    }

    public static double getDouble(String str, JSONObject jSONObject) {
        String rawString = getRawString(str, jSONObject);
        if (rawString == null || BuildConfig.FLAVOR.equals(rawString) || "null".equals(rawString)) {
            return -1.0d;
        }
        return Double.valueOf(rawString).doubleValue();
    }

    public static boolean getBoolean(String str, JSONObject jSONObject) {
        String rawString = getRawString(str, jSONObject);
        if (rawString == null || "null".equals(rawString)) {
            return false;
        }
        return Boolean.valueOf(rawString).booleanValue();
    }

    public static int toAccessLevel(HttpResponse httpResponse) {
        if (httpResponse == null) {
            return -1;
        }
        String responseHeader = httpResponse.getResponseHeader("X-Access-Level");
        if (responseHeader == null) {
            return 0;
        }
        switch (responseHeader.length()) {
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsTextAppearance /*4*/:
                return 1;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                return 2;
            case R.styleable.Toolbar_navigationContentDescription /*25*/:
                return 3;
            case R.styleable.Toolbar_logoDescription /*26*/:
                return 3;
            default:
                return 0;
        }
    }
}
