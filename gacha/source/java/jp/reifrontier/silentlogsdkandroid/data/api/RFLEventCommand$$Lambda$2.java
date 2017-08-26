package jp.reifrontier.silentlogsdkandroid.data.api;

import retrofit2.Response;
import rx.functions.Action1;

final /* synthetic */ class RFLEventCommand$$Lambda$2 implements Action1 {
    private final RFLEventCommand arg$1;

    private RFLEventCommand$$Lambda$2(RFLEventCommand rFLEventCommand) {
        this.arg$1 = rFLEventCommand;
    }

    public static Action1 lambdaFactory$(RFLEventCommand rFLEventCommand) {
        return new RFLEventCommand$$Lambda$2(rFLEventCommand);
    }

    public void call(Object obj) {
        RFLEventCommand.lambda$execute$1(this.arg$1, (Response) obj);
    }
}
