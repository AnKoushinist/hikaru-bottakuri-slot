package jp.co.mediasdk.android;

import org.cocos2dx.lib.BuildConfig;

public class HashMapEXEmptySupport extends HashMapEXIntentSupport {
    public boolean d(String str) {
        return StringUtilEmptySupport.c(b(str, BuildConfig.FLAVOR));
    }
}
