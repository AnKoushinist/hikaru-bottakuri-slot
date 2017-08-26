package jp.co.mediasdk.android;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ListenerPool<T> {
    private final List<T> a = Collections.synchronizedList(new LinkedList());

    public interface Callback<T, E> {
    }
}
