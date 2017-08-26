package com.tapjoy.internal;

import com.tapjoy.TJAdUnitConstants.String;
import java.io.InputStream;
import java.net.URI;
import java.util.LinkedHashMap;
import java.util.Map;
import twitter4j.HttpResponseCode;

public abstract class cd extends ce {
    public abstract Object a(br brVar);

    public final Map a() {
        Map linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Accept", "application/json");
        return linkedHashMap;
    }

    public final Object a(URI uri, InputStream inputStream) {
        Object obj = null;
        br a = br.a(inputStream);
        a.a("BASE_URI", uri);
        try {
            a.h();
            int i = 0;
            String str = null;
            while (a.j()) {
                String l = a.l();
                if ("status".equals(l)) {
                    i = a.r();
                } else if (String.MESSAGE.equals(l)) {
                    str = a.m();
                } else if (String.DATA.equals(l)) {
                    obj = a(a);
                } else {
                    a.s();
                }
            }
            a.i();
            if (i == HttpResponseCode.OK) {
                return obj;
            }
            throw new cf(i, str);
        } finally {
            a.close();
        }
    }
}
