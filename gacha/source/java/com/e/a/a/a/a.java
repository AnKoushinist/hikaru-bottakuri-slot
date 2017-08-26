package com.e.a.a.a;

import java.util.HashMap;
import java.util.Map;

public class a {
    public static final Integer a = Integer.valueOf(Integer.MIN_VALUE);
    public static final Double b = Double.valueOf(Double.NaN);
    public Integer c;
    public Double d;
    public b e;
    private Long f;

    public a(b bVar, Integer num) {
        this(bVar, num, b);
    }

    public a(b bVar, Integer num, Double d) {
        this.f = Long.valueOf(System.currentTimeMillis());
        this.e = bVar;
        this.d = d;
        this.c = num;
    }

    public Map a() {
        Map hashMap = new HashMap();
        hashMap.put("adVolume", this.d);
        hashMap.put("playhead", this.c);
        hashMap.put("aTimeStamp", this.f);
        hashMap.put("type", this.e.toString());
        return hashMap;
    }
}
