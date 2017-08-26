package com.vungle.publisher;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* compiled from: vungle */
public final class ahx {
    public static RuntimeException a(Throwable th) {
        if (th instanceof RuntimeException) {
            throw ((RuntimeException) th);
        } else if (th instanceof Error) {
            throw ((Error) th);
        } else {
            throw new RuntimeException(th);
        }
    }

    public static void b(Throwable th) {
        if (th instanceof aib) {
            throw ((aib) th);
        } else if (th instanceof aia) {
            throw ((aia) th);
        } else if (th instanceof ahz) {
            throw ((ahz) th);
        } else if (th instanceof VirtualMachineError) {
            throw ((VirtualMachineError) th);
        } else if (th instanceof ThreadDeath) {
            throw ((ThreadDeath) th);
        } else if (th instanceof LinkageError) {
            throw ((LinkageError) th);
        }
    }

    public static void a(Throwable th, Throwable th2) {
        Set hashSet = new HashSet();
        int i = 0;
        while (th.getCause() != null) {
            int i2 = i + 1;
            if (i < 25) {
                th = th.getCause();
                if (!hashSet.contains(th.getCause())) {
                    hashSet.add(th.getCause());
                    i = i2;
                }
            } else {
                return;
            }
        }
        try {
            th.initCause(th2);
        } catch (Throwable th3) {
        }
    }

    public static Throwable c(Throwable th) {
        int i = 0;
        while (th.getCause() != null) {
            int i2 = i + 1;
            if (i >= 25) {
                return new RuntimeException("Stack too deep to get final cause");
            }
            th = th.getCause();
            i = i2;
        }
        return th;
    }

    public static void a(List<? extends Throwable> list) {
        if (list != null && !list.isEmpty()) {
            if (list.size() == 1) {
                Throwable th = (Throwable) list.get(0);
                if (th instanceof RuntimeException) {
                    throw ((RuntimeException) th);
                } else if (th instanceof Error) {
                    throw ((Error) th);
                } else {
                    throw new RuntimeException(th);
                }
            }
            throw new ahw(list, (byte) 0);
        }
    }

    public static void a(Throwable th, ahq<?> com_vungle_publisher_ahq_, Object obj) {
        b(th);
        com_vungle_publisher_ahq_.a(aic.a(th, obj));
    }

    public static void a(Throwable th, ahq<?> com_vungle_publisher_ahq_) {
        b(th);
        com_vungle_publisher_ahq_.a(th);
    }
}
