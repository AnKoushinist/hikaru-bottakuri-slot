package jp.reifrontier.silentlogsdkandroid.data.api;

import rx.functions.Action1;

final /* synthetic */ class RFLGetProfileCommand$$Lambda$3 implements Action1 {
    private final RFLGetProfileCommand arg$1;

    private RFLGetProfileCommand$$Lambda$3(RFLGetProfileCommand rFLGetProfileCommand) {
        this.arg$1 = rFLGetProfileCommand;
    }

    public static Action1 lambdaFactory$(RFLGetProfileCommand rFLGetProfileCommand) {
        return new RFLGetProfileCommand$$Lambda$3(rFLGetProfileCommand);
    }

    public void call(Object obj) {
        RFLGetProfileCommand.lambda$execute$2(this.arg$1, (Throwable) obj);
    }
}
