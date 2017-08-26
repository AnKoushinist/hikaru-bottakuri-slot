package jp.reifrontier.silentlogsdkandroid.data.api;

import rx.functions.Action1;

final /* synthetic */ class RFLAccountCreateCommand$$Lambda$5 implements Action1 {
    private final RFLAccountCreateCommand arg$1;

    private RFLAccountCreateCommand$$Lambda$5(RFLAccountCreateCommand rFLAccountCreateCommand) {
        this.arg$1 = rFLAccountCreateCommand;
    }

    public static Action1 lambdaFactory$(RFLAccountCreateCommand rFLAccountCreateCommand) {
        return new RFLAccountCreateCommand$$Lambda$5(rFLAccountCreateCommand);
    }

    public void call(Object obj) {
        RFLAccountCreateCommand.lambda$execute$2(this.arg$1, (Throwable) obj);
    }
}
