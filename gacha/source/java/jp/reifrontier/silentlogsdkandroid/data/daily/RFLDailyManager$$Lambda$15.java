package jp.reifrontier.silentlogsdkandroid.data.daily;

import rx.functions.Action1;

final /* synthetic */ class RFLDailyManager$$Lambda$15 implements Action1 {
    private final RFLDailyManager arg$1;

    private RFLDailyManager$$Lambda$15(RFLDailyManager rFLDailyManager) {
        this.arg$1 = rFLDailyManager;
    }

    public static Action1 lambdaFactory$(RFLDailyManager rFLDailyManager) {
        return new RFLDailyManager$$Lambda$15(rFLDailyManager);
    }

    public void call(Object obj) {
        RFLDailyManager.lambda$uploadAllDaily$14(this.arg$1, obj);
    }
}
