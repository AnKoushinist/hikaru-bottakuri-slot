package jp.reifrontier.silentlogsdkandroid.data.api;

import rx.functions.Action1;

final /* synthetic */ class RFLResponseCommand$$Lambda$3 implements Action1 {
    private final RFLResponseCommand arg$1;

    private RFLResponseCommand$$Lambda$3(RFLResponseCommand rFLResponseCommand) {
        this.arg$1 = rFLResponseCommand;
    }

    public static Action1 lambdaFactory$(RFLResponseCommand rFLResponseCommand) {
        return new RFLResponseCommand$$Lambda$3(rFLResponseCommand);
    }

    public void call(Object obj) {
        RFLResponseCommand.lambda$execute$2(this.arg$1, (Throwable) obj);
    }
}
