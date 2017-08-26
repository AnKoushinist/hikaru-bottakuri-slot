package jp.co.mediasdk.android;

import java.util.ArrayList;
import org.cocos2dx.lib.BuildConfig;

public class HashMapEXQuerySupport extends HashMapEXJSONSupport {
    public String e() {
        return a(false);
    }

    public String a(boolean z) {
        ArrayList arrayList = new ArrayList();
        for (String j : a()) {
            String j2 = j(j);
            if (z) {
                j2 = StringUtil.b(j2);
            }
            arrayList.add(String.format("%s=%s", new Object[]{j, j2}));
        }
        return ArrayUtilJoinSupport.a("&", arrayList);
    }

    public boolean i(String str) {
        if (!StringUtilEmptySupport.c(str)) {
            String[] c = StringUtilRegexpSupport.c("&", str);
            if (!StringUtilEmptySupport.a(c)) {
                for (String str2 : c) {
                    ArrayList arrayList = new ArrayList();
                    if (StringUtilRegexpSupport.a("^(.*?)=(.*)$", str2, arrayList)) {
                        c((String) arrayList.get(1), (String) arrayList.get(2));
                    } else {
                        c(str2, BuildConfig.FLAVOR);
                    }
                }
            }
        }
        return true;
    }
}
