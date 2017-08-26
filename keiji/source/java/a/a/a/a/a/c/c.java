package a.a.a.a.a.c;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import twitter4j.TwitterResponse;

/* compiled from: DependencyPriorityBlockingQueue */
public class c<E extends b & l & i> extends PriorityBlockingQueue<E> {
    final Queue<E> a = new LinkedList();
    private final ReentrantLock b = new ReentrantLock();

    public /* synthetic */ Object peek() {
        return b();
    }

    public /* synthetic */ Object poll() {
        return c();
    }

    public /* synthetic */ Object poll(long j, TimeUnit timeUnit) {
        return a(j, timeUnit);
    }

    public /* synthetic */ Object take() {
        return a();
    }

    public E a() {
        return b(0, null, null);
    }

    public E b() {
        E e = null;
        try {
            e = b(1, null, null);
        } catch (InterruptedException e2) {
        }
        return e;
    }

    public E a(long j, TimeUnit timeUnit) {
        return b(3, Long.valueOf(j), timeUnit);
    }

    public E c() {
        E e = null;
        try {
            e = b(2, null, null);
        } catch (InterruptedException e2) {
        }
        return e;
    }

    public int size() {
        try {
            this.b.lock();
            int size = this.a.size() + super.size();
            return size;
        } finally {
            this.b.unlock();
        }
    }

    public <T> T[] toArray(T[] tArr) {
        try {
            this.b.lock();
            T[] a = a(super.toArray(tArr), this.a.toArray(tArr));
            return a;
        } finally {
            this.b.unlock();
        }
    }

    public Object[] toArray() {
        try {
            this.b.lock();
            Object[] a = a(super.toArray(), this.a.toArray());
            return a;
        } finally {
            this.b.unlock();
        }
    }

    public int drainTo(Collection<? super E> collection) {
        try {
            this.b.lock();
            int drainTo = super.drainTo(collection) + this.a.size();
            while (!this.a.isEmpty()) {
                collection.add(this.a.poll());
            }
            return drainTo;
        } finally {
            this.b.unlock();
        }
    }

    public int drainTo(Collection<? super E> collection, int i) {
        try {
            this.b.lock();
            int drainTo = super.drainTo(collection, i);
            while (!this.a.isEmpty() && drainTo <= i) {
                collection.add(this.a.poll());
                drainTo++;
            }
            this.b.unlock();
            return drainTo;
        } catch (Throwable th) {
            this.b.unlock();
        }
    }

    public boolean contains(Object obj) {
        try {
            this.b.lock();
            boolean z = super.contains(obj) || this.a.contains(obj);
            this.b.unlock();
            return z;
        } catch (Throwable th) {
            this.b.unlock();
        }
    }

    public void clear() {
        try {
            this.b.lock();
            this.a.clear();
            super.clear();
        } finally {
            this.b.unlock();
        }
    }

    public boolean remove(Object obj) {
        try {
            this.b.lock();
            boolean z = super.remove(obj) || this.a.remove(obj);
            this.b.unlock();
            return z;
        } catch (Throwable th) {
            this.b.unlock();
        }
    }

    public boolean removeAll(Collection<?> collection) {
        try {
            this.b.lock();
            boolean removeAll = super.removeAll(collection) | this.a.removeAll(collection);
            return removeAll;
        } finally {
            this.b.unlock();
        }
    }

    E a(int i, Long l, TimeUnit timeUnit) {
        switch (i) {
            case TwitterResponse.NONE /*0*/:
                return (b) super.take();
            case TwitterResponse.READ /*1*/:
                return (b) super.peek();
            case TwitterResponse.READ_WRITE /*2*/:
                return (b) super.poll();
            case TwitterResponse.READ_WRITE_DIRECTMESSAGES /*3*/:
                return (b) super.poll(l.longValue(), timeUnit);
            default:
                return null;
        }
    }

    boolean a(int i, E e) {
        try {
            this.b.lock();
            if (i == 1) {
                super.remove(e);
            }
            boolean offer = this.a.offer(e);
            return offer;
        } finally {
            this.b.unlock();
        }
    }

    E b(int i, Long l, TimeUnit timeUnit) {
        b a;
        while (true) {
            a = a(i, l, timeUnit);
            if (a == null || a(a)) {
                return a;
            }
            a(i, a);
        }
        return a;
    }

    boolean a(E e) {
        return e.d();
    }

    public void d() {
        try {
            this.b.lock();
            Iterator it = this.a.iterator();
            while (it.hasNext()) {
                b bVar = (b) it.next();
                if (a(bVar)) {
                    super.offer(bVar);
                    it.remove();
                }
            }
        } finally {
            this.b.unlock();
        }
    }

    <T> T[] a(T[] tArr, T[] tArr2) {
        int length = tArr.length;
        int length2 = tArr2.length;
        Object[] objArr = (Object[]) Array.newInstance(tArr.getClass().getComponentType(), length + length2);
        System.arraycopy(tArr, 0, objArr, 0, length);
        System.arraycopy(tArr2, 0, objArr, length, length2);
        return objArr;
    }
}
