package jp.reifrontier.silentlogsdkandroid.data.api;

import rx.functions.Action1;

final /* synthetic */ class RFLAuthCommand$$Lambda$3 implements Action1 {
    private final RFLAuthCommand arg$1;

    private RFLAuthCommand$$Lambda$3(RFLAuthCommand rFLAuthCommand) {
        this.arg$1 = rFLAuthCommand;
    }

    public static Action1 lambdaFactory$(RFLAuthCommand rFLAuthCommand) {
        return new RFLAuthCommand$$Lambda$3(rFLAuthCommand);
    }

    public void call(Object obj) {
        RFLAuthCommand.lambda$execute$2(this.arg$1, (Throwable) obj);
    }
}
