package com.igaworks.adbrix.model;

public class Segment<T> {
    private String key;
    private String op;
    private String scheme;
    private T val;

    public String getScheme() {
        return this.scheme;
    }

    public String getKey() {
        return this.key;
    }

    public String getOp() {
        return this.op;
    }

    public T getVal() {
        return this.val;
    }
}
