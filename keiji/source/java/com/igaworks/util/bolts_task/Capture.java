package com.igaworks.util.bolts_task;

public class Capture<T> {
    private T value;

    public T get() {
        return this.value;
    }

    public void set(T t) {
        this.value = t;
    }
}
