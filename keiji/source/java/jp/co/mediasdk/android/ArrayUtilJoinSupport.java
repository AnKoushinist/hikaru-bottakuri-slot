package jp.co.mediasdk.android;

import java.util.ArrayList;
import org.cocos2dx.lib.BuildConfig;

public class ArrayUtilJoinSupport extends ArrayUtilSizeSupport {
    public static String a(String str, ArrayList<String> arrayList) {
        if (arrayList == null) {
            return null;
        }
        if (arrayList.size() == 0) {
            return BuildConfig.FLAVOR;
        }
        return a(str, (String[]) arrayList.toArray(new String[0]));
    }

    public static String a(String str, String[] strArr) {
        if (str == null) {
            str = BuildConfig.FLAVOR;
        }
        if (strArr == null) {
            return null;
        }
        if (strArr.length == 0) {
            return BuildConfig.FLAVOR;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < strArr.length - 1; i++) {
            stringBuilder.append(strArr[i]);
            stringBuilder.append(str);
        }
        stringBuilder.append(strArr[strArr.length - 1]);
        return stringBuilder.toString();
    }
}
