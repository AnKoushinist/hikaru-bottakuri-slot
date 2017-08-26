package jp.reifrontier.silentlogsdkandroid.data.daily;

import rx.functions.Action1;

final /* synthetic */ class RFLDailyManager$$Lambda$13 implements Action1 {
    private static final RFLDailyManager$$Lambda$13 instance = new RFLDailyManager$$Lambda$13();

    private RFLDailyManager$$Lambda$13() {
    }

    public static Action1 lambdaFactory$() {
        return instance;
    }

    public void call(Object obj) {
        RFLDailyManager.lambda$loadLatestRflDisplayData$12((Throwable) obj);
    }
}
