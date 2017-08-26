package jp.reifrontier.silentlogsdkandroid.data.daily;

import jp.reifrontier.silentlogsdkandroid.domain.Event.RFLEvent;
import jp.reifrontier.silentlogsdkandroid.domain.Event.RFLNotifiedPoi;
import rx.functions.Action1;

final /* synthetic */ class RFLDailyManager$$Lambda$22 implements Action1 {
    private final RFLDailyManager arg$1;
    private final RFLEvent arg$2;
    private final RFLNotifiedPoi arg$3;
    private final String arg$4;

    private RFLDailyManager$$Lambda$22(RFLDailyManager rFLDailyManager, RFLEvent rFLEvent, RFLNotifiedPoi rFLNotifiedPoi, String str) {
        this.arg$1 = rFLDailyManager;
        this.arg$2 = rFLEvent;
        this.arg$3 = rFLNotifiedPoi;
        this.arg$4 = str;
    }

    public static Action1 lambdaFactory$(RFLDailyManager rFLDailyManager, RFLEvent rFLEvent, RFLNotifiedPoi rFLNotifiedPoi, String str) {
        return new RFLDailyManager$$Lambda$22(rFLDailyManager, rFLEvent, rFLNotifiedPoi, str);
    }

    public void call(Object obj) {
        this.arg$1.notifyOperation(this.arg$2, this.arg$3, this.arg$4);
    }
}
