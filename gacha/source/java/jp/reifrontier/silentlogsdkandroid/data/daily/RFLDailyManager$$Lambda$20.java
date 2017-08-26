package jp.reifrontier.silentlogsdkandroid.data.daily;

import rx.functions.Action1;

final /* synthetic */ class RFLDailyManager$$Lambda$20 implements Action1 {
    private static final RFLDailyManager$$Lambda$20 instance = new RFLDailyManager$$Lambda$20();

    private RFLDailyManager$$Lambda$20() {
    }

    public static Action1 lambdaFactory$() {
        return instance;
    }

    public void call(Object obj) {
        RFLDailyManager.lambda$checkActivity$19((Throwable) obj);
    }
}
