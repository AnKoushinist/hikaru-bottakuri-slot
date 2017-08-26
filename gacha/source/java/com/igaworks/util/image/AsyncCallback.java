package com.igaworks.util.image;

public interface AsyncCallback<T> {
    void cancelled();

    void exceptionOccured(Exception exception);

    void onResult(T t);
}
