package com.vungle.publisher;

import java.util.ArrayList;
import java.util.List;

/* compiled from: vungle */
final class ahg {
    private static final List<ahg> d = new ArrayList();
    Object a;
    ahl b;
    ahg c;

    private ahg(Object obj, ahl com_vungle_publisher_ahl) {
        this.a = obj;
        this.b = com_vungle_publisher_ahl;
    }

    static ahg a(ahl com_vungle_publisher_ahl, Object obj) {
        synchronized (d) {
            int size = d.size();
            if (size > 0) {
                ahg com_vungle_publisher_ahg = (ahg) d.remove(size - 1);
                com_vungle_publisher_ahg.a = obj;
                com_vungle_publisher_ahg.b = com_vungle_publisher_ahl;
                com_vungle_publisher_ahg.c = null;
                return com_vungle_publisher_ahg;
            }
            return new ahg(obj, com_vungle_publisher_ahl);
        }
    }

    static void a(ahg com_vungle_publisher_ahg) {
        com_vungle_publisher_ahg.a = null;
        com_vungle_publisher_ahg.b = null;
        com_vungle_publisher_ahg.c = null;
        synchronized (d) {
            if (d.size() < 10000) {
                d.add(com_vungle_publisher_ahg);
            }
        }
    }
}
