package jp.reifrontier.silentlogsdkandroid.data.daily;

import rx.functions.Action1;

final /* synthetic */ class RFLDailyManager$$Lambda$24 implements Action1 {
    private static final RFLDailyManager$$Lambda$24 instance = new RFLDailyManager$$Lambda$24();

    private RFLDailyManager$$Lambda$24() {
    }

    public static Action1 lambdaFactory$() {
        return instance;
    }

    public void call(Object obj) {
        RFLDailyManager.lambda$postResponse$23((Throwable) obj);
    }
}
