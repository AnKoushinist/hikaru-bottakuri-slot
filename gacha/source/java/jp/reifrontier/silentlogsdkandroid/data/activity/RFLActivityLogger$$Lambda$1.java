package jp.reifrontier.silentlogsdkandroid.data.activity;

import rx.Observable.OnSubscribe;
import rx.Subscriber;

final /* synthetic */ class RFLActivityLogger$$Lambda$1 implements OnSubscribe {
    private final RFLActivityLogger arg$1;
    private final long arg$2;

    private RFLActivityLogger$$Lambda$1(RFLActivityLogger rFLActivityLogger, long j) {
        this.arg$1 = rFLActivityLogger;
        this.arg$2 = j;
    }

    public static OnSubscribe lambdaFactory$(RFLActivityLogger rFLActivityLogger, long j) {
        return new RFLActivityLogger$$Lambda$1(rFLActivityLogger, j);
    }

    public void call(Object obj) {
        RFLActivityLogger.lambda$savePedometer$0(this.arg$1, this.arg$2, (Subscriber) obj);
    }
}
