package com.tapjoy.internal;

import java.util.Collections;
import java.util.List;
import org.cocos2dx.lib.BuildConfig;

public final class ds {
    public static List a() {
        return new dt(Collections.emptyList());
    }

    public static List a(String str, List list) {
        if (list == null) {
            throw new NullPointerException(str + " == null");
        }
        if (list instanceof dt) {
            list = ((dt) list).a;
        }
        if (list == Collections.emptyList() || (list instanceof dr)) {
            return list;
        }
        dr drVar = new dr(list);
        if (!drVar.contains(null)) {
            return drVar;
        }
        throw new IllegalArgumentException(str + ".contains(null)");
    }

    public static boolean a(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    public static IllegalStateException a(Object... objArr) {
        StringBuilder stringBuilder = new StringBuilder();
        String str = BuildConfig.FLAVOR;
        int length = objArr.length;
        String str2 = str;
        for (int i = 0; i < length; i += 2) {
            if (objArr[i] == null) {
                if (stringBuilder.length() > 0) {
                    str2 = "s";
                }
                stringBuilder.append("\n  ");
                stringBuilder.append(objArr[i + 1]);
            }
        }
        throw new IllegalStateException("Required field" + str2 + " not set:" + stringBuilder);
    }
}
