package com.igaworks.adbrix.viral;

import java.util.ArrayList;
import java.util.List;

public class ViralConstant {
    public static final List<String> SHARING_CHANNEL = new ArrayList();

    static {
        SHARING_CHANNEL.add("com.kakao.talk");
        SHARING_CHANNEL.add("com.kakao.story");
        SHARING_CHANNEL.add("com.facebook.katana");
        SHARING_CHANNEL.add("com.twitter.android");
        SHARING_CHANNEL.add("com.nhn.android.band");
        SHARING_CHANNEL.add("jp.naver.line.android");
        SHARING_CHANNEL.add("net.daum.android.air");
        SHARING_CHANNEL.add("com.google.android.apps.plus");
        SHARING_CHANNEL.add("sms");
        SHARING_CHANNEL.add("etc");
    }

    public static String resetLineOfHtmlString(String str, int i) {
        int i2 = 0;
        String[] split = str.split("<br/>|<br>");
        StringBuilder stringBuilder = new StringBuilder();
        if (split.length <= i) {
            stringBuilder.append(str);
        } else if (i == 2) {
            r1 = (split.length / 2) - 1;
            r4 = split.length;
            while (i2 < r4) {
                r5 = split[i2];
                stringBuilder.append(r5);
                if (r5.equals(split[r1])) {
                    stringBuilder.append("<br/>");
                } else {
                    stringBuilder.append(" ");
                }
                i2++;
            }
        } else {
            for (String append : split) {
                stringBuilder.append(append);
                if (i2 < i - 1) {
                    stringBuilder.append("<br/>");
                    i2++;
                } else {
                    stringBuilder.append(" ");
                }
            }
        }
        return stringBuilder.toString();
    }
}
