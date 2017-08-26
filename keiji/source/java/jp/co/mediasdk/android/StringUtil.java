package jp.co.mediasdk.android;

import com.d.a.a.c;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class StringUtil extends StringUtilSizeSupport {
    public static String a(String str, Object... objArr) {
        try {
            str = String.format(str, objArr);
        } catch (Exception e) {
            LoggerBase.a(StringUtil.class, "format", "String.format error, format is '%s'.", str);
        }
        return str;
    }

    public static String a(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        return new String(bArr);
    }

    public static String a(String str) {
        try {
            str = URLDecoder.decode(str, c.DEFAULT_CHARSET);
        } catch (Exception e) {
            LoggerBase.a(StringUtil.class, "urldecode", "failed to decode.", new Object[0]);
        }
        return str;
    }

    public static String b(String str) {
        try {
            str = URLEncoder.encode(str, c.DEFAULT_CHARSET);
        } catch (Exception e) {
            e.printStackTrace();
            LoggerBase.a(StringUtil.class, "urlencode", "failed to encode.", new Object[0]);
        }
        return str;
    }
}
