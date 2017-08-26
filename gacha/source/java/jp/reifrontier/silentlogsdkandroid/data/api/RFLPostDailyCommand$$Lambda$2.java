package jp.reifrontier.silentlogsdkandroid.data.api;

import retrofit2.Response;
import rx.functions.Action1;

final /* synthetic */ class RFLPostDailyCommand$$Lambda$2 implements Action1 {
    private final RFLPostDailyCommand arg$1;

    private RFLPostDailyCommand$$Lambda$2(RFLPostDailyCommand rFLPostDailyCommand) {
        this.arg$1 = rFLPostDailyCommand;
    }

    public static Action1 lambdaFactory$(RFLPostDailyCommand rFLPostDailyCommand) {
        return new RFLPostDailyCommand$$Lambda$2(rFLPostDailyCommand);
    }

    public void call(Object obj) {
        RFLPostDailyCommand.lambda$execute$1(this.arg$1, (Response) obj);
    }
}
