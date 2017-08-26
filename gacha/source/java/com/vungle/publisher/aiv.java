package com.vungle.publisher;

import java.util.concurrent.atomic.AtomicLong;

/* compiled from: vungle */
public final class aiv implements com.vungle.publisher.ahp.a<Integer> {
    private final int a = 1;
    private final int b;

    /* compiled from: vungle */
    static final class a extends AtomicLong implements ahr {
        private final ahu<? super Integer> a;
        private final int b;
        private long c;

        a(ahu<? super Integer> com_vungle_publisher_ahu__super_java_lang_Integer, int i, int i2) {
            this.a = com_vungle_publisher_ahu__super_java_lang_Integer;
            this.c = (long) i;
            this.b = i2;
        }

        public final void a(long j) {
            if (get() != Long.MAX_VALUE) {
                long j2;
                long j3;
                if (j == Long.MAX_VALUE && compareAndSet(0, Long.MAX_VALUE)) {
                    j2 = ((long) this.b) + 1;
                    ahu com_vungle_publisher_ahu = this.a;
                    j3 = this.c;
                    while (j3 != j2) {
                        if (!com_vungle_publisher_ahu.a.b) {
                            com_vungle_publisher_ahu.a((Object) Integer.valueOf((int) j3));
                            j3++;
                        } else {
                            return;
                        }
                    }
                    if (!com_vungle_publisher_ahu.a.b) {
                        com_vungle_publisher_ahu.a();
                    }
                } else if (j > 0 && aim.a((AtomicLong) this, j) == 0) {
                    long j4 = ((long) this.b) + 1;
                    j3 = this.c;
                    ahu com_vungle_publisher_ahu2 = this.a;
                    j2 = 0;
                    while (true) {
                        if (j2 == j || j3 == j4) {
                            if (!com_vungle_publisher_ahu2.a.b) {
                                if (j3 == j4) {
                                    com_vungle_publisher_ahu2.a();
                                    return;
                                }
                                j = get();
                                if (j == j2) {
                                    this.c = j3;
                                    j = addAndGet(-j2);
                                    if (j != 0) {
                                        j2 = 0;
                                    } else {
                                        return;
                                    }
                                }
                                continue;
                            } else {
                                return;
                            }
                        } else if (!com_vungle_publisher_ahu2.a.b) {
                            com_vungle_publisher_ahu2.a((Object) Integer.valueOf((int) j3));
                            j3++;
                            j2++;
                        } else {
                            return;
                        }
                    }
                }
            }
        }
    }

    public final /* synthetic */ void a(Object obj) {
        ahu com_vungle_publisher_ahu = (ahu) obj;
        com_vungle_publisher_ahu.a(new a(com_vungle_publisher_ahu, this.a, this.b));
    }

    public aiv(int i) {
        this.b = i;
    }
}
