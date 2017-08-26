package com.vungle.publisher;

import com.vungle.log.Logger;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public final class afo {
    ahs a = alw.c();

    @Inject
    afo() {
    }

    public final aii<ahp<? extends Throwable>, ahp<?>> a(int i, final String str) {
        final int i2 = i + 1;
        return new aii<ahp<? extends Throwable>, ahp<?>>(this) {
            final /* synthetic */ afo c;

            public final /* synthetic */ Object a(Object obj) {
                ahp com_vungle_publisher_ahp = (ahp) obj;
                int i = i2;
                if (i < 0) {
                    throw new IllegalArgumentException("Count can not be negative");
                }
                ahp a;
                if (i == 0) {
                    a = ain.a();
                } else if (1 > (Integer.MAX_VALUE - i) + 1) {
                    throw new IllegalArgumentException("start + count can not exceed Integer.MAX_VALUE");
                } else {
                    a = i == 1 ? akc.a(Integer.valueOf(1)) : ahp.a(new aiv((i - 1) + 1));
                }
                return com_vungle_publisher_ahp.a(a, new aij<Throwable, Integer, Integer>(this) {
                    final /* synthetic */ AnonymousClass1 a;

                    {
                        this.a = r1;
                    }

                    public final /* synthetic */ Object a(Object obj, Object obj2) {
                        Throwable th = (Throwable) obj;
                        Integer num = (Integer) obj2;
                        if (num.intValue() < i2) {
                            return num;
                        }
                        throw ahx.a(th);
                    }
                }).a(new aii<Integer, ahp<Long>>(this) {
                    final /* synthetic */ AnonymousClass1 a;

                    {
                        this.a = r1;
                    }

                    public final /* synthetic */ Object a(Object obj) {
                        int a = agv.a(((Integer) obj).intValue(), 2000, 300000);
                        Logger.d(Logger.PREPARE_TAG, "retry in " + a + " millis - " + str);
                        return ahp.a(new aix((long) a, TimeUnit.MILLISECONDS, this.a.c.a));
                    }
                });
            }
        };
    }
}
