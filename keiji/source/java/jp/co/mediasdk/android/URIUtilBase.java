package jp.co.mediasdk.android;

import java.net.URI;
import java.util.ArrayList;
import org.cocos2dx.lib.BuildConfig;

public class URIUtilBase {
    public static String a(String str) {
        if (str == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        String[] c = StringUtilRegexpSupport.c("\\/", str);
        if (c == null || c.length == 0) {
            return str;
        }
        for (String b : c) {
            arrayList.add(StringUtil.b(b));
        }
        if (str.endsWith("/")) {
            arrayList.add(BuildConfig.FLAVOR);
        }
        return ArrayUtilJoinSupport.a("/", arrayList);
    }

    public static URI b(String str) {
        if (StringUtilEmptySupport.c(str)) {
            LoggerBase.a(URIUtil.class, "get", "url is empty.", new Object[0]);
            return null;
        }
        try {
            return new URI(str.replace(" ", "%20").replace("^", "%5E").replace("{", "%7B").replace("|", "%7C").replace("}", "%7D"));
        } catch (Exception e) {
            e.printStackTrace();
            LoggerBase.a(URIUtil.class, "get", "failed to create URI '%s'.", r2);
            return null;
        }
    }

    public static boolean a(URI uri) {
        if (uri == null) {
            return true;
        }
        return false;
    }

    public static String c(String str) {
        return b(b(str));
    }

    public static String b(URI uri) {
        if (a(uri)) {
            return null;
        }
        return uri.getQuery();
    }
}
