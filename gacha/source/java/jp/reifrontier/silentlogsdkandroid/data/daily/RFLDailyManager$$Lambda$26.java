package jp.reifrontier.silentlogsdkandroid.data.daily;

import rx.functions.Action1;

final /* synthetic */ class RFLDailyManager$$Lambda$26 implements Action1 {
    private static final RFLDailyManager$$Lambda$26 instance = new RFLDailyManager$$Lambda$26();

    private RFLDailyManager$$Lambda$26() {
    }

    public static Action1 lambdaFactory$() {
        return instance;
    }

    public void call(Object obj) {
        RFLDailyManager.lambda$observeResponseBus$25(obj);
    }
}
