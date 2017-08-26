package jp.reifrontier.silentlogsdkandroid.data.daily;

import rx.functions.Action1;

final /* synthetic */ class RFLDailyManager$$Lambda$9 implements Action1 {
    private static final RFLDailyManager$$Lambda$9 instance = new RFLDailyManager$$Lambda$9();

    private RFLDailyManager$$Lambda$9() {
    }

    public static Action1 lambdaFactory$() {
        return instance;
    }

    public void call(Object obj) {
        RFLDailyManager.lambda$loadLastRflDisplayDate$8((Throwable) obj);
    }
}
