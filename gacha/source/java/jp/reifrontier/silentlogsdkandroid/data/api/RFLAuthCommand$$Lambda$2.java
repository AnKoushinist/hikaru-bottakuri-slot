package jp.reifrontier.silentlogsdkandroid.data.api;

import retrofit2.Response;
import rx.functions.Action1;

final /* synthetic */ class RFLAuthCommand$$Lambda$2 implements Action1 {
    private final RFLAuthCommand arg$1;

    private RFLAuthCommand$$Lambda$2(RFLAuthCommand rFLAuthCommand) {
        this.arg$1 = rFLAuthCommand;
    }

    public static Action1 lambdaFactory$(RFLAuthCommand rFLAuthCommand) {
        return new RFLAuthCommand$$Lambda$2(rFLAuthCommand);
    }

    public void call(Object obj) {
        RFLAuthCommand.lambda$execute$1(this.arg$1, (Response) obj);
    }
}
