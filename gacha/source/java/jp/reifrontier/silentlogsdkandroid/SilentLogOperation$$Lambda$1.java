package jp.reifrontier.silentlogsdkandroid;

import rx.functions.Action1;

final /* synthetic */ class SilentLogOperation$$Lambda$1 implements Action1 {
    private static final SilentLogOperation$$Lambda$1 instance = new SilentLogOperation$$Lambda$1();

    private SilentLogOperation$$Lambda$1() {
    }

    public static Action1 lambdaFactory$() {
        return instance;
    }

    public void call(Object obj) {
        SilentLogOperation.lambda$uploadForceTest$0((String) obj);
    }
}
