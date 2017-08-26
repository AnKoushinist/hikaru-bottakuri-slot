package com.vungle.publisher;

import com.vungle.publisher.akf.AnonymousClass1;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicLong;

/* compiled from: vungle */
public final class aim {
    public static long a(AtomicLong atomicLong, long j) {
        long j2;
        do {
            j2 = atomicLong.get();
        } while (!atomicLong.compareAndSet(j2, b(j2, j)));
        return j2;
    }

    public static long a(long j, long j2) {
        long j3 = j * j2;
        if (((j | j2) >>> 31) == 0 || j2 == 0 || j3 / j2 == j) {
            return j3;
        }
        return Long.MAX_VALUE;
    }

    public static long b(long j, long j2) {
        long j3 = j + j2;
        if (j3 < 0) {
            return Long.MAX_VALUE;
        }
        return j3;
    }

    public static <T> void a(AtomicLong atomicLong, Queue<T> queue, ahu<? super T> com_vungle_publisher_ahu__super_T) {
        long j;
        aii anonymousClass1 = new AnonymousClass1();
        do {
            j = atomicLong.get();
            if ((j & Long.MIN_VALUE) != 0) {
                return;
            }
        } while (!atomicLong.compareAndSet(j, j | Long.MIN_VALUE));
        if (j != 0) {
            a(atomicLong, queue, com_vungle_publisher_ahu__super_T, anonymousClass1);
        }
    }

    static <T, R> void a(AtomicLong atomicLong, Queue<T> queue, ahu<? super R> com_vungle_publisher_ahu__super_R, aii<? super T, ? extends R> com_vungle_publisher_aii__super_T___extends_R) {
        long j = atomicLong.get();
        if (j == Long.MAX_VALUE) {
            while (!com_vungle_publisher_ahu__super_R.a.b) {
                Object poll = queue.poll();
                if (poll == null) {
                    com_vungle_publisher_ahu__super_R.a();
                    return;
                }
                com_vungle_publisher_ahu__super_R.a(com_vungle_publisher_aii__super_T___extends_R.a(poll));
            }
            return;
        }
        long j2 = j;
        j = Long.MIN_VALUE;
        while (true) {
            if (j == j2) {
                if (j == j2) {
                    if (!com_vungle_publisher_ahu__super_R.a.b) {
                        if (queue.isEmpty()) {
                            com_vungle_publisher_ahu__super_R.a();
                            return;
                        }
                    }
                    return;
                }
                j2 = atomicLong.get();
                if (j2 == j) {
                    j = atomicLong.addAndGet(-(j & Long.MAX_VALUE));
                    if (j != Long.MIN_VALUE) {
                        j2 = j;
                        j = Long.MIN_VALUE;
                    } else {
                        return;
                    }
                }
                continue;
            } else if (!com_vungle_publisher_ahu__super_R.a.b) {
                Object poll2 = queue.poll();
                if (poll2 == null) {
                    com_vungle_publisher_ahu__super_R.a();
                    return;
                } else {
                    com_vungle_publisher_ahu__super_R.a(com_vungle_publisher_aii__super_T___extends_R.a(poll2));
                    j++;
                }
            } else {
                return;
            }
        }
    }

    public static long b(AtomicLong atomicLong, long j) {
        long j2;
        long j3;
        do {
            j3 = atomicLong.get();
            if (j3 == Long.MAX_VALUE) {
                return Long.MAX_VALUE;
            }
            j2 = j3 - j;
            if (j2 < 0) {
                throw new IllegalStateException("More produced than requested: " + j2);
            }
        } while (!atomicLong.compareAndSet(j3, j2));
        return j2;
    }
}
