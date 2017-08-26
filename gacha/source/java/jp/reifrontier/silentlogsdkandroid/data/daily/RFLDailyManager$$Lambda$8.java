package jp.reifrontier.silentlogsdkandroid.data.daily;

import rx.functions.Action1;

final /* synthetic */ class RFLDailyManager$$Lambda$8 implements Action1 {
    private final RFLDailyManager arg$1;
    private final long arg$2;

    private RFLDailyManager$$Lambda$8(RFLDailyManager rFLDailyManager, long j) {
        this.arg$1 = rFLDailyManager;
        this.arg$2 = j;
    }

    public static Action1 lambdaFactory$(RFLDailyManager rFLDailyManager, long j) {
        return new RFLDailyManager$$Lambda$8(rFLDailyManager, j);
    }

    public void call(Object obj) {
        RFLDailyManager.lambda$loadLastRflDisplayDate$7(this.arg$1, this.arg$2, (String) obj);
    }
}
