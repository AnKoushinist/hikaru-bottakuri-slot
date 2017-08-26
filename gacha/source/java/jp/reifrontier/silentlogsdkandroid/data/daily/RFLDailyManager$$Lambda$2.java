package jp.reifrontier.silentlogsdkandroid.data.daily;

import jp.reifrontier.silentlogsdkandroid.domain.Daily.RFLDailyDisplayData;
import rx.functions.Func0;

final /* synthetic */ class RFLDailyManager$$Lambda$2 implements Func0 {
    private final RFLDailyManager arg$1;
    private final RFLDailyDisplayData arg$2;

    private RFLDailyManager$$Lambda$2(RFLDailyManager rFLDailyManager, RFLDailyDisplayData rFLDailyDisplayData) {
        this.arg$1 = rFLDailyManager;
        this.arg$2 = rFLDailyDisplayData;
    }

    public static Func0 lambdaFactory$(RFLDailyManager rFLDailyManager, RFLDailyDisplayData rFLDailyDisplayData) {
        return new RFLDailyManager$$Lambda$2(rFLDailyManager, rFLDailyDisplayData);
    }

    public Object call() {
        return this.arg$1.loadDailyFromDB(this.arg$2);
    }
}
