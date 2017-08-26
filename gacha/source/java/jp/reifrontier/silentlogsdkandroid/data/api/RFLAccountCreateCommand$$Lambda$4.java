package jp.reifrontier.silentlogsdkandroid.data.api;

import retrofit2.Response;
import rx.functions.Action1;

final /* synthetic */ class RFLAccountCreateCommand$$Lambda$4 implements Action1 {
    private final RFLAccountCreateCommand arg$1;

    private RFLAccountCreateCommand$$Lambda$4(RFLAccountCreateCommand rFLAccountCreateCommand) {
        this.arg$1 = rFLAccountCreateCommand;
    }

    public static Action1 lambdaFactory$(RFLAccountCreateCommand rFLAccountCreateCommand) {
        return new RFLAccountCreateCommand$$Lambda$4(rFLAccountCreateCommand);
    }

    public void call(Object obj) {
        RFLAccountCreateCommand.lambda$execute$1(this.arg$1, (Response) obj);
    }
}
