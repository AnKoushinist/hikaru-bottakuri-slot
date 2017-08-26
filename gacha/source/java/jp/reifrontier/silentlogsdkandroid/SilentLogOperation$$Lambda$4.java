package jp.reifrontier.silentlogsdkandroid;

import rx.functions.Action1;

final /* synthetic */ class SilentLogOperation$$Lambda$4 implements Action1 {
    private static final SilentLogOperation$$Lambda$4 instance = new SilentLogOperation$$Lambda$4();

    private SilentLogOperation$$Lambda$4() {
    }

    public static Action1 lambdaFactory$() {
        return instance;
    }

    public void call(Object obj) {
        SilentLogOperation.lambda$uploadForceTest$1((Throwable) obj);
    }
}
