package com.vungle.publisher;

import com.vungle.publisher.ahs.a;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import org.cocos2dx.lib.GameControllerDelegate;

/* compiled from: vungle */
public class ajo extends a implements ahv {
    private static final boolean a;
    public static final int d = Integer.getInteger("rx.scheduler.jdk6.purge-frequency-millis", GameControllerDelegate.THUMBSTICK_LEFT_X).intValue();
    private static final ConcurrentHashMap<ScheduledThreadPoolExecutor, ScheduledThreadPoolExecutor> e = new ConcurrentHashMap();
    private static final AtomicReference<ScheduledExecutorService> f = new AtomicReference();
    private static volatile Object g;
    private static final Object h = new Object();
    final ScheduledExecutorService b;
    volatile boolean c;

    static {
        boolean z = Boolean.getBoolean("rx.scheduler.jdk6.purge-force");
        int b = ajz.b();
        z = !z && (b == 0 || b >= 21);
        a = z;
    }

    public static void a(ScheduledThreadPoolExecutor scheduledThreadPoolExecutor) {
        while (((ScheduledExecutorService) f.get()) == null) {
            ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(1, new akb("RxSchedulerPurge-"));
            if (f.compareAndSet(null, newScheduledThreadPool)) {
                newScheduledThreadPool.scheduleAtFixedRate(new Runnable() {
                    public final void run() {
                        ajo.a();
                    }
                }, (long) d, (long) d, TimeUnit.MILLISECONDS);
                break;
            }
            newScheduledThreadPool.shutdownNow();
        }
        e.putIfAbsent(scheduledThreadPoolExecutor, scheduledThreadPoolExecutor);
    }

    public static void a(ScheduledExecutorService scheduledExecutorService) {
        e.remove(scheduledExecutorService);
    }

    static void a() {
        try {
            Iterator it = e.keySet().iterator();
            while (it.hasNext()) {
                ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = (ScheduledThreadPoolExecutor) it.next();
                if (scheduledThreadPoolExecutor.isShutdown()) {
                    it.remove();
                } else {
                    scheduledThreadPoolExecutor.purge();
                }
            }
        } catch (Throwable th) {
            ahx.b(th);
            alp.a(th);
        }
    }

    public static boolean b(ScheduledExecutorService scheduledExecutorService) {
        if (a) {
            Method c;
            if (scheduledExecutorService instanceof ScheduledThreadPoolExecutor) {
                Object obj = g;
                if (obj == h) {
                    return false;
                }
                if (obj == null) {
                    c = c(scheduledExecutorService);
                    if (c != null) {
                        obj = c;
                    } else {
                        obj = h;
                    }
                    g = obj;
                } else {
                    c = (Method) obj;
                }
            } else {
                c = c(scheduledExecutorService);
            }
            if (c != null) {
                try {
                    c.invoke(scheduledExecutorService, new Object[]{Boolean.valueOf(true)});
                    return true;
                } catch (Throwable e) {
                    alp.a(e);
                } catch (Throwable e2) {
                    alp.a(e2);
                } catch (Throwable e22) {
                    alp.a(e22);
                }
            }
        }
        return false;
    }

    private static Method c(ScheduledExecutorService scheduledExecutorService) {
        for (Method method : scheduledExecutorService.getClass().getMethods()) {
            if (method.getName().equals("setRemoveOnCancelPolicy")) {
                Class[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length == 1 && parameterTypes[0] == Boolean.TYPE) {
                    return method;
                }
            }
        }
        return null;
    }

    public ajo(ThreadFactory threadFactory) {
        ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(1, threadFactory);
        if (!b(newScheduledThreadPool) && (newScheduledThreadPool instanceof ScheduledThreadPoolExecutor)) {
            a((ScheduledThreadPoolExecutor) newScheduledThreadPool);
        }
        this.b = newScheduledThreadPool;
    }

    public final ahv a(aie com_vungle_publisher_aie) {
        return a(com_vungle_publisher_aie, 0, null);
    }

    public final ahv a(aie com_vungle_publisher_aie, long j, TimeUnit timeUnit) {
        if (this.c) {
            return ame.a();
        }
        return b(com_vungle_publisher_aie, j, timeUnit);
    }

    public final ajp b(aie com_vungle_publisher_aie, long j, TimeUnit timeUnit) {
        Future submit;
        Runnable com_vungle_publisher_ajp = new ajp(alp.a(com_vungle_publisher_aie));
        if (j <= 0) {
            submit = this.b.submit(com_vungle_publisher_ajp);
        } else {
            submit = this.b.schedule(com_vungle_publisher_ajp, j, timeUnit);
        }
        com_vungle_publisher_ajp.a(submit);
        return com_vungle_publisher_ajp;
    }

    public final void b() {
        this.c = true;
        this.b.shutdownNow();
        a(this.b);
    }

    public final boolean c() {
        return this.c;
    }
}
