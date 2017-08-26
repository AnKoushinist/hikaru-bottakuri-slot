package jp.reifrontier.silentlogsdkandroid;

import rx.functions.Action1;

final /* synthetic */ class SilentLogOperation$$Lambda$6 implements Action1 {
    private final SilentLogOperation arg$1;

    private SilentLogOperation$$Lambda$6(SilentLogOperation silentLogOperation) {
        this.arg$1 = silentLogOperation;
    }

    public static Action1 lambdaFactory$(SilentLogOperation silentLogOperation) {
        return new SilentLogOperation$$Lambda$6(silentLogOperation);
    }

    public void call(Object obj) {
        SilentLogOperation.lambda$observeAccountRxBus$3(this.arg$1, obj);
    }
}
