package jp.reifrontier.silentlogsdkandroid.data.activity;

import jp.reifrontier.silentlogsdkandroid.domain.Daily.RFLRawActivity;
import rx.Observable.OnSubscribe;
import rx.Subscriber;

final /* synthetic */ class RFLActivityLogger$$Lambda$2 implements OnSubscribe {
    private final RFLActivityLogger arg$1;
    private final RFLRawActivity arg$2;

    private RFLActivityLogger$$Lambda$2(RFLActivityLogger rFLActivityLogger, RFLRawActivity rFLRawActivity) {
        this.arg$1 = rFLActivityLogger;
        this.arg$2 = rFLRawActivity;
    }

    public static OnSubscribe lambdaFactory$(RFLActivityLogger rFLActivityLogger, RFLRawActivity rFLRawActivity) {
        return new RFLActivityLogger$$Lambda$2(rFLActivityLogger, rFLRawActivity);
    }

    public void call(Object obj) {
        RFLActivityLogger.lambda$saveActivity$1(this.arg$1, this.arg$2, (Subscriber) obj);
    }
}
