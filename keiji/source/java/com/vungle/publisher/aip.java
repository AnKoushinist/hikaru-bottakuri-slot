package com.vungle.publisher;

import com.vungle.publisher.ahp.a;

/* compiled from: vungle */
public final class aip<T> implements a<T> {
    final aih<? extends ahp<? extends T>> a;

    public final /* synthetic */ void a(Object obj) {
        ahq com_vungle_publisher_ahq = (ahu) obj;
        try {
            ((ahp) this.a.call()).a(alm.a(com_vungle_publisher_ahq));
        } catch (Throwable th) {
            ahx.a(th, com_vungle_publisher_ahq);
        }
    }

    public aip(aih<? extends ahp<? extends T>> com_vungle_publisher_aih__extends_com_vungle_publisher_ahp__extends_T) {
        this.a = com_vungle_publisher_aih__extends_com_vungle_publisher_ahp__extends_T;
    }
}
