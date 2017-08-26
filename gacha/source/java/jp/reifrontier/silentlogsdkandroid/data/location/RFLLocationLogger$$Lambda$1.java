package jp.reifrontier.silentlogsdkandroid.data.location;

import jp.reifrontier.silentlogsdkandroid.domain.Daily.RFLTrackPoint;
import rx.Observable.OnSubscribe;
import rx.Subscriber;

final /* synthetic */ class RFLLocationLogger$$Lambda$1 implements OnSubscribe {
    private final RFLLocationLogger arg$1;
    private final RFLTrackPoint arg$2;

    private RFLLocationLogger$$Lambda$1(RFLLocationLogger rFLLocationLogger, RFLTrackPoint rFLTrackPoint) {
        this.arg$1 = rFLLocationLogger;
        this.arg$2 = rFLTrackPoint;
    }

    public static OnSubscribe lambdaFactory$(RFLLocationLogger rFLLocationLogger, RFLTrackPoint rFLTrackPoint) {
        return new RFLLocationLogger$$Lambda$1(rFLLocationLogger, rFLTrackPoint);
    }

    public void call(Object obj) {
        RFLLocationLogger.lambda$saveLocation$0(this.arg$1, this.arg$2, (Subscriber) obj);
    }
}
