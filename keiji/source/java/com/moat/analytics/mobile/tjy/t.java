package com.moat.analytics.mobile.tjy;

import com.moat.analytics.mobile.tjy.base.exception.a;

class t implements Runnable {
    final /* synthetic */ s a;

    t(s sVar) {
        this.a = sVar;
    }

    public void run() {
        try {
            this.a.a.c();
        } catch (Exception e) {
            a.a(e);
        }
    }
}
