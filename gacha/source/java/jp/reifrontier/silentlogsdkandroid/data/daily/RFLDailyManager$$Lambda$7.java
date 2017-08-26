package jp.reifrontier.silentlogsdkandroid.data.daily;

import rx.functions.Action1;

final /* synthetic */ class RFLDailyManager$$Lambda$7 implements Action1 {
    private final RFLDailyManager arg$1;

    private RFLDailyManager$$Lambda$7(RFLDailyManager rFLDailyManager) {
        this.arg$1 = rFLDailyManager;
    }

    public static Action1 lambdaFactory$(RFLDailyManager rFLDailyManager) {
        return new RFLDailyManager$$Lambda$7(rFLDailyManager);
    }

    public void call(Object obj) {
        RFLDailyManager.lambda$observePostDailyBus$6(this.arg$1, obj);
    }
}
