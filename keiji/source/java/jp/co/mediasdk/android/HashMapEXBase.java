package jp.co.mediasdk.android;

import java.util.LinkedHashMap;
import org.cocos2dx.lib.BuildConfig;

public class HashMapEXBase extends LinkedHashMap<String, String> {
    public /* synthetic */ Object put(Object obj, Object obj2) {
        return a((String) obj, (String) obj2);
    }

    public String[] a() {
        return (String[]) keySet().toArray(new String[0]);
    }

    public boolean a(String str) {
        if (str == null) {
            return false;
        }
        return super.containsKey(str);
    }

    public String a(String str, String str2) {
        if (str2 == null) {
            str2 = BuildConfig.FLAVOR;
        }
        return (String) super.put(str, str2);
    }
}
