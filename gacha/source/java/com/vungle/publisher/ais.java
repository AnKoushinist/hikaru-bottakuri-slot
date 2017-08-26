package com.vungle.publisher;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicLong;

/* compiled from: vungle */
public final class ais<T> implements com.vungle.publisher.ahp.a<T> {
    final Iterable<? extends T> a;

    /* compiled from: vungle */
    static final class a<T> extends AtomicLong implements ahr {
        private final ahu<? super T> a;
        private final Iterator<? extends T> b;

        a(ahu<? super T> com_vungle_publisher_ahu__super_T, Iterator<? extends T> it) {
            this.a = com_vungle_publisher_ahu__super_T;
            this.b = it;
        }

        public final void a(long j) {
            if (get() != Long.MAX_VALUE) {
                if (j == Long.MAX_VALUE && compareAndSet(0, Long.MAX_VALUE)) {
                    ahq com_vungle_publisher_ahq = this.a;
                    Iterator it = this.b;
                    while (!com_vungle_publisher_ahq.a.b) {
                        try {
                            com_vungle_publisher_ahq.a(it.next());
                            if (!com_vungle_publisher_ahq.a.b) {
                                try {
                                    if (!it.hasNext()) {
                                        if (!com_vungle_publisher_ahq.a.b) {
                                            com_vungle_publisher_ahq.a();
                                            return;
                                        }
                                        return;
                                    }
                                } catch (Throwable th) {
                                    ahx.a(th, com_vungle_publisher_ahq);
                                    return;
                                }
                            }
                            return;
                        } catch (Throwable th2) {
                            ahx.a(th2, com_vungle_publisher_ahq);
                            return;
                        }
                    }
                } else if (j > 0 && aim.a((AtomicLong) this, j) == 0) {
                    ahq com_vungle_publisher_ahq2 = this.a;
                    Iterator it2 = this.b;
                    long j2 = 0;
                    while (true) {
                        if (j2 == j) {
                            j = get();
                            if (j2 == j) {
                                j = aim.b((AtomicLong) this, j2);
                                if (j != 0) {
                                    j2 = 0;
                                } else {
                                    return;
                                }
                            }
                            continue;
                        } else if (!com_vungle_publisher_ahq2.a.b) {
                            try {
                                com_vungle_publisher_ahq2.a(it2.next());
                                if (!com_vungle_publisher_ahq2.a.b) {
                                    try {
                                        if (!it2.hasNext()) {
                                            break;
                                        }
                                        j2++;
                                    } catch (Throwable th3) {
                                        ahx.a(th3, com_vungle_publisher_ahq2);
                                        return;
                                    }
                                }
                                return;
                            } catch (Throwable th32) {
                                ahx.a(th32, com_vungle_publisher_ahq2);
                                return;
                            }
                        } else {
                            return;
                        }
                    }
                    if (!com_vungle_publisher_ahq2.a.b) {
                        com_vungle_publisher_ahq2.a();
                    }
                }
            }
        }
    }

    public final /* synthetic */ void a(Object obj) {
        ahq com_vungle_publisher_ahq = (ahu) obj;
        try {
            Iterator it = this.a.iterator();
            boolean hasNext = it.hasNext();
            if (!com_vungle_publisher_ahq.a.b) {
                if (hasNext) {
                    com_vungle_publisher_ahq.a(new a(com_vungle_publisher_ahq, it));
                } else {
                    com_vungle_publisher_ahq.a();
                }
            }
        } catch (Throwable th) {
            ahx.a(th, com_vungle_publisher_ahq);
        }
    }

    public ais(Iterable<? extends T> iterable) {
        if (iterable == null) {
            throw new NullPointerException("iterable must not be null");
        }
        this.a = iterable;
    }
}
