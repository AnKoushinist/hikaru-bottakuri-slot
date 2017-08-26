package jp.reifrontier.silentlogsdkandroid.data.daily;

import java.util.List;
import jp.reifrontier.silentlogsdkandroid.domain.Event.RFLTiming.RFL_TIMING;
import rx.functions.Action1;

final /* synthetic */ class RFLDailyManager$$Lambda$19 implements Action1 {
    private final RFLDailyManager arg$1;
    private final RFL_TIMING arg$2;

    private RFLDailyManager$$Lambda$19(RFLDailyManager rFLDailyManager, RFL_TIMING rfl_timing) {
        this.arg$1 = rFLDailyManager;
        this.arg$2 = rfl_timing;
    }

    public static Action1 lambdaFactory$(RFLDailyManager rFLDailyManager, RFL_TIMING rfl_timing) {
        return new RFLDailyManager$$Lambda$19(rFLDailyManager, rfl_timing);
    }

    public void call(Object obj) {
        RFLDailyManager.lambda$checkActivity$18(this.arg$1, this.arg$2, (List) obj);
    }
}
