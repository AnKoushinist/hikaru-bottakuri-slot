package com.vungle.publisher;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/* compiled from: vungle */
public final class ake<T> implements Cloneable, Queue<T> {
    private final Queue<T> a;
    private final int b;

    public ake() {
        this.a = new LinkedList();
        this.b = -1;
    }

    public ake(int i) {
        this.a = new LinkedList();
        this.b = i;
    }

    public final synchronized boolean isEmpty() {
        return this.a.isEmpty();
    }

    public final synchronized boolean contains(Object obj) {
        return this.a.contains(obj);
    }

    public final synchronized Iterator<T> iterator() {
        return this.a.iterator();
    }

    public final synchronized int size() {
        return this.a.size();
    }

    public final synchronized boolean add(T t) {
        return this.a.add(t);
    }

    public final synchronized boolean remove(Object obj) {
        return this.a.remove(obj);
    }

    public final synchronized boolean containsAll(Collection<?> collection) {
        return this.a.containsAll(collection);
    }

    public final synchronized boolean addAll(Collection<? extends T> collection) {
        return this.a.addAll(collection);
    }

    public final synchronized boolean removeAll(Collection<?> collection) {
        return this.a.removeAll(collection);
    }

    public final synchronized boolean retainAll(Collection<?> collection) {
        return this.a.retainAll(collection);
    }

    public final synchronized void clear() {
        this.a.clear();
    }

    public final synchronized String toString() {
        return this.a.toString();
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.a.equals(((ake) obj).a);
    }

    public final synchronized T peek() {
        return this.a.peek();
    }

    public final synchronized T element() {
        return this.a.element();
    }

    public final synchronized T poll() {
        return this.a.poll();
    }

    public final synchronized T remove() {
        return this.a.remove();
    }

    public final synchronized boolean offer(T t) {
        boolean z;
        z = (this.b < 0 || this.a.size() + 1 <= this.b) && this.a.offer(t);
        return z;
    }

    public final synchronized Object clone() {
        ake com_vungle_publisher_ake;
        com_vungle_publisher_ake = new ake(this.b);
        com_vungle_publisher_ake.addAll(this.a);
        return com_vungle_publisher_ake;
    }

    public final synchronized Object[] toArray() {
        return this.a.toArray();
    }

    public final synchronized <R> R[] toArray(R[] rArr) {
        return this.a.toArray(rArr);
    }
}
