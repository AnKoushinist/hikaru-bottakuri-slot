package com.vungle.publisher;

/* compiled from: vungle */
public enum uo {
    wifi("WIFI", un.wifi),
    gprs("GPRS"),
    cdma("CDMA"),
    edge("EDGE"),
    rtt1x("1xRTT"),
    iden("IDEN"),
    umts("UMTS"),
    evdo0("EVDO_0"),
    evdoA("EVDO_A"),
    hsdpa("HSDPA"),
    hspa("HSPA"),
    hspap("HSPAP"),
    hsupa("HSUPA"),
    evdoB("EVDO_B"),
    ehrpd("EHRPD"),
    lte("LTE"),
    unknown("UNKNOWN", un.unknown);
    
    public final String r;
    public final un s;

    private uo(String str, un unVar) {
        this.r = str;
        this.s = unVar;
    }

    private uo(String str) {
        this(r2, r3, str, un.mobile);
    }
}
