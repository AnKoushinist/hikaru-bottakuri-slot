package jp.reifrontier.silentlogsdkandroid.data.api;

import rx.functions.Action1;

final /* synthetic */ class RFLPostDailyCommand$$Lambda$3 implements Action1 {
    private final RFLPostDailyCommand arg$1;

    private RFLPostDailyCommand$$Lambda$3(RFLPostDailyCommand rFLPostDailyCommand) {
        this.arg$1 = rFLPostDailyCommand;
    }

    public static Action1 lambdaFactory$(RFLPostDailyCommand rFLPostDailyCommand) {
        return new RFLPostDailyCommand$$Lambda$3(rFLPostDailyCommand);
    }

    public void call(Object obj) {
        RFLPostDailyCommand.lambda$execute$2(this.arg$1, (Throwable) obj);
    }
}
