package jp.reifrontier.silentlogsdkandroid;

import rx.functions.Action1;

final /* synthetic */ class SilentLogOperation$$Lambda$7 implements Action1 {
    private final SilentLogOperation arg$1;

    private SilentLogOperation$$Lambda$7(SilentLogOperation silentLogOperation) {
        this.arg$1 = silentLogOperation;
    }

    public static Action1 lambdaFactory$(SilentLogOperation silentLogOperation) {
        return new SilentLogOperation$$Lambda$7(silentLogOperation);
    }

    public void call(Object obj) {
        SilentLogOperation.lambda$observeProfileRxBus$4(this.arg$1, obj);
    }
}
