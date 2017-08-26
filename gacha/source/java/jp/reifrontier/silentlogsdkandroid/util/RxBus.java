package jp.reifrontier.silentlogsdkandroid.util;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

public class RxBus {
    private final Subject<Object, Object> _bus = new SerializedSubject(PublishSubject.create());

    public void send(Object obj) {
        this._bus.onNext(obj);
    }

    public Observable<Object> toObserverable() {
        return this._bus;
    }

    public boolean hasObservers() {
        return this._bus.hasObservers();
    }
}
