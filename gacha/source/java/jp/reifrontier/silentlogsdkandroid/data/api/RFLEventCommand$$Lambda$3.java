package jp.reifrontier.silentlogsdkandroid.data.api;

import rx.functions.Action1;

final /* synthetic */ class RFLEventCommand$$Lambda$3 implements Action1 {
    private final RFLEventCommand arg$1;

    private RFLEventCommand$$Lambda$3(RFLEventCommand rFLEventCommand) {
        this.arg$1 = rFLEventCommand;
    }

    public static Action1 lambdaFactory$(RFLEventCommand rFLEventCommand) {
        return new RFLEventCommand$$Lambda$3(rFLEventCommand);
    }

    public void call(Object obj) {
        RFLEventCommand.lambda$execute$2(this.arg$1, (Throwable) obj);
    }
}
