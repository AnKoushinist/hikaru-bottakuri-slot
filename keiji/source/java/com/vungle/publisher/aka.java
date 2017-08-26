package com.vungle.publisher;

import java.util.Queue;

/* compiled from: vungle */
public final class aka implements ahv {
    public static final int b;
    public static final ajx<Queue<Object>> c = new ajx<Queue<Object>>() {
        protected final /* synthetic */ Object b() {
            return new alb(aka.b);
        }
    };
    public static final ajx<Queue<Object>> d = new ajx<Queue<Object>>() {
        protected final /* synthetic */ Object b() {
            return new akt(aka.b);
        }
    };
    private static final aio<Object> e = aio.a();
    public volatile Object a;
    private Queue<Object> f;
    private final int g;
    private final ajx<Queue<Object>> h;

    static {
        int i = 128;
        if (ajz.a()) {
            i = 16;
        }
        String property = System.getProperty("rx.ring-buffer.size");
        if (property != null) {
            try {
                i = Integer.parseInt(property);
            } catch (NumberFormatException e) {
                System.err.println("Failed to set 'rx.buffer.size' with value " + property + " => " + e.getMessage());
            }
        }
        b = i;
    }

    public static aka a() {
        if (ali.a()) {
            return new aka(c, b);
        }
        return new aka();
    }

    public static aka d() {
        if (ali.a()) {
            return new aka(d, b);
        }
        return new aka();
    }

    private aka(Queue<Object> queue, int i) {
        this.f = queue;
        this.h = null;
        this.g = i;
    }

    private aka(ajx<Queue<Object>> com_vungle_publisher_ajx_java_util_Queue_java_lang_Object, int i) {
        this.h = com_vungle_publisher_ajx_java_util_Queue_java_lang_Object;
        this.f = (Queue) com_vungle_publisher_ajx_java_util_Queue_java_lang_Object.a();
        this.g = i;
    }

    public final synchronized void e() {
        Queue queue = this.f;
        ajx com_vungle_publisher_ajx = this.h;
        if (!(com_vungle_publisher_ajx == null || queue == null)) {
            queue.clear();
            this.f = null;
            if (queue != null) {
                com_vungle_publisher_ajx.a.offer(queue);
            }
        }
    }

    public final void b() {
        e();
    }

    aka() {
        this(new ake(b), b);
    }

    public final void a(Object obj) {
        Object obj2 = 1;
        Object obj3 = null;
        synchronized (this) {
            Queue queue = this.f;
            if (queue == null) {
                int i = 1;
                obj2 = null;
            } else if (queue.offer(aio.a(obj))) {
                obj2 = null;
            }
        }
        if (obj3 != null) {
            throw new IllegalStateException("This instance has been unsubscribed and the queue is no longer usable.");
        } else if (obj2 != null) {
            throw new ahy();
        }
    }

    public final boolean f() {
        Queue queue = this.f;
        return queue == null || queue.isEmpty();
    }

    public final Object g() {
        Object obj = null;
        synchronized (this) {
            Queue queue = this.f;
            if (queue == null) {
            } else {
                Object poll = queue.poll();
                obj = this.a;
                if (poll == null && obj != null && queue.peek() == null) {
                    this.a = null;
                } else {
                    obj = poll;
                }
            }
        }
        return obj;
    }

    public final Object h() {
        Object obj;
        synchronized (this) {
            Queue queue = this.f;
            if (queue == null) {
                obj = null;
            } else {
                Object peek = queue.peek();
                obj = this.a;
                if (!(peek == null && obj != null && queue.peek() == null)) {
                    obj = peek;
                }
            }
        }
        return obj;
    }

    public static boolean b(Object obj) {
        return aio.b(obj);
    }

    public static Object c(Object obj) {
        return aio.c(obj);
    }

    public final boolean c() {
        return this.f == null;
    }
}
