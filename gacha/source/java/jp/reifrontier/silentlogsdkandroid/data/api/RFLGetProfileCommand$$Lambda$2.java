package jp.reifrontier.silentlogsdkandroid.data.api;

import retrofit2.Response;
import rx.functions.Action1;

final /* synthetic */ class RFLGetProfileCommand$$Lambda$2 implements Action1 {
    private final RFLGetProfileCommand arg$1;

    private RFLGetProfileCommand$$Lambda$2(RFLGetProfileCommand rFLGetProfileCommand) {
        this.arg$1 = rFLGetProfileCommand;
    }

    public static Action1 lambdaFactory$(RFLGetProfileCommand rFLGetProfileCommand) {
        return new RFLGetProfileCommand$$Lambda$2(rFLGetProfileCommand);
    }

    public void call(Object obj) {
        RFLGetProfileCommand.lambda$execute$1(this.arg$1, (Response) obj);
    }
}
