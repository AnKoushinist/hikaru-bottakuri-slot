package jp.reifrontier.silentlogsdkandroid.data.api;

import retrofit2.Response;
import rx.functions.Action1;

final /* synthetic */ class RFLResponseCommand$$Lambda$2 implements Action1 {
    private final RFLResponseCommand arg$1;

    private RFLResponseCommand$$Lambda$2(RFLResponseCommand rFLResponseCommand) {
        this.arg$1 = rFLResponseCommand;
    }

    public static Action1 lambdaFactory$(RFLResponseCommand rFLResponseCommand) {
        return new RFLResponseCommand$$Lambda$2(rFLResponseCommand);
    }

    public void call(Object obj) {
        RFLResponseCommand.lambda$execute$1(this.arg$1, (Response) obj);
    }
}
