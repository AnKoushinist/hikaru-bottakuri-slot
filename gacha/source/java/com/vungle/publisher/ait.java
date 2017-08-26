package com.vungle.publisher;

import com.vungle.publisher.ahp.a;
import com.vungle.publisher.ahp.b;

/* compiled from: vungle */
public final class ait<T, R> implements a<R> {
    final a<T> a;
    final b<? extends R, ? super T> b;

    public final /* synthetic */ void a(Object obj) {
        ahu com_vungle_publisher_ahu;
        ahu com_vungle_publisher_ahu2 = (ahu) obj;
        try {
            com_vungle_publisher_ahu = (ahu) alp.a(this.b).a(com_vungle_publisher_ahu2);
            com_vungle_publisher_ahu.k_();
            this.a.a(com_vungle_publisher_ahu);
        } catch (Throwable th) {
            ahx.b(th);
            com_vungle_publisher_ahu2.a(th);
        }
    }

    public ait(a<T> aVar, b<? extends R, ? super T> bVar) {
        this.a = aVar;
        this.b = bVar;
    }
}
