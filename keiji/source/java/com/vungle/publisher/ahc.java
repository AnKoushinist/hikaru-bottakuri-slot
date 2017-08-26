package com.vungle.publisher;

import android.os.Looper;
import com.vungle.log.Logger;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import jp.co.vaz.creator.hikaru2.R;
import twitter4j.TwitterResponse;

/* compiled from: vungle */
public final class ahc {
    static ExecutorService a = Executors.newCachedThreadPool();
    private static final Map<Class<?>, List<Class<?>>> b = new HashMap();
    private final Map<Class<?>, CopyOnWriteArrayList<ahl>> c = new HashMap();
    private final Map<Object, List<Class<?>>> d = new HashMap();
    private final Map<Class<?>, Object> e = new ConcurrentHashMap();
    private final ThreadLocal<a> f = new ThreadLocal<a>(this) {
        final /* synthetic */ ahc a;

        {
            this.a = r1;
        }

        protected final /* synthetic */ Object initialValue() {
            return new a();
        }
    };
    private final ahe g = new ahe(this, Looper.getMainLooper());
    private final ahb h = new ahb(this);
    private final aha i = new aha(this);
    private final ahk j = new ahk();
    private boolean k;
    private boolean l = true;

    /* compiled from: vungle */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] a = new int[ahm.values().length];

        static {
            try {
                a[ahm.PostThread.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[ahm.MainThread.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[ahm.BackgroundThread.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[ahm.Async.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    /* compiled from: vungle */
    static final class a {
        List<Object> a = new ArrayList();
        boolean b;
        boolean c;
        ahl d;
        Object e;
        boolean f;

        a() {
        }
    }

    public final synchronized void a(Object obj, String str, boolean z) {
        for (ahj com_vungle_publisher_ahj : ahk.a(obj.getClass(), str)) {
            CopyOnWriteArrayList copyOnWriteArrayList;
            this.k = true;
            Class cls = com_vungle_publisher_ahj.c;
            CopyOnWriteArrayList copyOnWriteArrayList2 = (CopyOnWriteArrayList) this.c.get(cls);
            ahl com_vungle_publisher_ahl = new ahl(obj, com_vungle_publisher_ahj);
            if (copyOnWriteArrayList2 == null) {
                copyOnWriteArrayList2 = new CopyOnWriteArrayList();
                this.c.put(cls, copyOnWriteArrayList2);
                copyOnWriteArrayList = copyOnWriteArrayList2;
            } else {
                Iterator it = copyOnWriteArrayList2.iterator();
                while (it.hasNext()) {
                    if (((ahl) it.next()).equals(com_vungle_publisher_ahl)) {
                        throw new ahd("Subscriber " + obj.getClass() + " already registered to event " + cls);
                    }
                }
                copyOnWriteArrayList = copyOnWriteArrayList2;
            }
            int size = copyOnWriteArrayList.size();
            int i = 0;
            while (i <= size) {
                if (i == size || com_vungle_publisher_ahl.c > ((ahl) copyOnWriteArrayList.get(i)).c) {
                    copyOnWriteArrayList.add(i, com_vungle_publisher_ahl);
                    break;
                }
                i++;
            }
            List list = (List) this.d.get(obj);
            if (list == null) {
                list = new ArrayList();
                this.d.put(obj, list);
            }
            list.add(cls);
            if (z) {
                Object obj2;
                synchronized (this.e) {
                    obj2 = this.e.get(cls);
                }
                if (obj2 != null) {
                    a(com_vungle_publisher_ahl, obj2, Looper.getMainLooper() == Looper.myLooper());
                } else {
                    continue;
                }
            }
        }
    }

    public final synchronized void a(Object obj) {
        List<Class> list = (List) this.d.get(obj);
        if (list != null) {
            for (Class cls : list) {
                List list2 = (List) this.c.get(cls);
                if (list2 != null) {
                    int size = list2.size();
                    int i = 0;
                    while (i < size) {
                        int i2;
                        ahl com_vungle_publisher_ahl = (ahl) list2.get(i);
                        if (com_vungle_publisher_ahl.a == obj) {
                            com_vungle_publisher_ahl.d = false;
                            list2.remove(i);
                            i2 = i - 1;
                            i = size - 1;
                        } else {
                            i2 = i;
                            i = size;
                        }
                        size = i;
                        i = i2 + 1;
                    }
                }
            }
            this.d.remove(obj);
        } else {
            Logger.w(Logger.EVENT_TAG, "Subscriber to unregister was not registered before: " + obj.getClass());
        }
    }

    public final void b(Object obj) {
        a aVar = (a) this.f.get();
        List list = aVar.a;
        list.add(obj);
        if (!aVar.b) {
            boolean z;
            if (Looper.getMainLooper() == Looper.myLooper()) {
                z = true;
            } else {
                z = false;
            }
            aVar.c = z;
            aVar.b = true;
            if (aVar.f) {
                throw new ahd("Internal error. Abort state was not reset");
            }
            while (!list.isEmpty()) {
                Object remove = list.remove(0);
                Class cls = remove.getClass();
                List a = a(cls);
                int size = a.size();
                int i = 0;
                boolean z2 = false;
                while (i < size) {
                    CopyOnWriteArrayList copyOnWriteArrayList;
                    Class cls2 = (Class) a.get(i);
                    synchronized (this) {
                        copyOnWriteArrayList = (CopyOnWriteArrayList) this.c.get(cls2);
                    }
                    if (copyOnWriteArrayList != null) {
                        if (!copyOnWriteArrayList.isEmpty()) {
                            Iterator it = copyOnWriteArrayList.iterator();
                            while (it.hasNext()) {
                                ahl com_vungle_publisher_ahl = (ahl) it.next();
                                aVar.e = remove;
                                aVar.d = com_vungle_publisher_ahl;
                                try {
                                    a(com_vungle_publisher_ahl, remove, aVar.c);
                                    z = aVar.f;
                                    aVar.e = null;
                                    aVar.d = null;
                                    aVar.f = false;
                                    if (z) {
                                        break;
                                    }
                                } catch (Throwable th) {
                                    aVar.b = false;
                                    aVar.c = false;
                                }
                            }
                            z = true;
                            i++;
                            z2 = z;
                        }
                    }
                    z = z2;
                    i++;
                    z2 = z;
                }
                if (!z2) {
                    Logger.d(Logger.EVENT_TAG, "No subscribers registered for event " + cls);
                    if (!(cls == ahf.class || cls == ahi.class)) {
                        b(new ahf(this, remove));
                    }
                }
            }
            aVar.b = false;
            aVar.c = false;
        }
    }

    private void a(ahl com_vungle_publisher_ahl, Object obj, boolean z) {
        ahg a;
        switch (AnonymousClass2.a[com_vungle_publisher_ahl.b.b.ordinal()]) {
            case TwitterResponse.READ /*1*/:
                a(com_vungle_publisher_ahl, obj);
                return;
            case TwitterResponse.READ_WRITE /*2*/:
                if (z) {
                    a(com_vungle_publisher_ahl, obj);
                    return;
                }
                ahe com_vungle_publisher_ahe = this.g;
                a = ahg.a(com_vungle_publisher_ahl, obj);
                synchronized (com_vungle_publisher_ahe) {
                    com_vungle_publisher_ahe.a.a(a);
                    if (!com_vungle_publisher_ahe.b) {
                        com_vungle_publisher_ahe.b = true;
                        if (!com_vungle_publisher_ahe.sendMessage(com_vungle_publisher_ahe.obtainMessage())) {
                            throw new ahd("Could not send handler message");
                        }
                    }
                }
                return;
            case TwitterResponse.READ_WRITE_DIRECTMESSAGES /*3*/:
                if (z) {
                    Runnable runnable = this.h;
                    a = ahg.a(com_vungle_publisher_ahl, obj);
                    synchronized (runnable) {
                        runnable.a.a(a);
                        if (!runnable.b) {
                            runnable.b = true;
                            a.execute(runnable);
                        }
                    }
                    return;
                }
                a(com_vungle_publisher_ahl, obj);
                return;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsTextAppearance /*4*/:
                Runnable runnable2 = this.i;
                runnable2.a.a(ahg.a(com_vungle_publisher_ahl, obj));
                a.execute(runnable2);
                return;
            default:
                throw new IllegalStateException("Unknown thread mode: " + com_vungle_publisher_ahl.b.b);
        }
    }

    private static List<Class<?>> a(Class<?> cls) {
        List<Class<?>> list;
        synchronized (b) {
            list = (List) b.get(cls);
            if (list == null) {
                list = new ArrayList();
                for (Class cls2 = cls; cls2 != null; cls2 = cls2.getSuperclass()) {
                    list.add(cls2);
                    a((List) list, cls2.getInterfaces());
                }
                b.put(cls, list);
            }
        }
        return list;
    }

    private static void a(List<Class<?>> list, Class<?>[] clsArr) {
        for (Class cls : clsArr) {
            if (!list.contains(cls)) {
                list.add(cls);
                a((List) list, cls.getInterfaces());
            }
        }
    }

    final void a(ahg com_vungle_publisher_ahg) {
        Object obj = com_vungle_publisher_ahg.a;
        ahl com_vungle_publisher_ahl = com_vungle_publisher_ahg.b;
        ahg.a(com_vungle_publisher_ahg);
        if (com_vungle_publisher_ahl.d) {
            a(com_vungle_publisher_ahl, obj);
        }
    }

    private void a(ahl com_vungle_publisher_ahl, Object obj) {
        Throwable cause;
        try {
            com_vungle_publisher_ahl.b.a.invoke(com_vungle_publisher_ahl.a, new Object[]{obj});
        } catch (InvocationTargetException e) {
            cause = e.getCause();
            if (obj instanceof ahi) {
                Logger.e(Logger.EVENT_TAG, "SubscriberExceptionEvent subscriber " + com_vungle_publisher_ahl.a.getClass() + " threw an exception", cause);
                ahi com_vungle_publisher_ahi = (ahi) obj;
                Logger.e(Logger.EVENT_TAG, "Initial event " + com_vungle_publisher_ahi.c + " caused exception in " + com_vungle_publisher_ahi.d, com_vungle_publisher_ahi.b);
                return;
            }
            if (this.l) {
                Logger.e(Logger.EVENT_TAG, "Could not dispatch event: " + obj.getClass() + " to subscribing class " + com_vungle_publisher_ahl.a.getClass(), cause);
            }
            b(new ahi(this, cause, obj, com_vungle_publisher_ahl.a));
        } catch (Throwable cause2) {
            throw new IllegalStateException("Unexpected exception", cause2);
        }
    }
}
